package AnalisisSemantico;

import AST.*;
import Assets.*;

public class SemTypeChecker implements AbsVisitor {

	public boolean error;
	
	public void addTypeToFunction(String name, SemType par, SemType ret) {
		// in semTable we have stored the declarations, now we fetch it
		AbsTree imaginaryDeclarationNode = SemTable.fnd(name);
		SemSubprogramType type = new SemSubprogramType(ret);
		if(par != null)	type.addParType(par);
		AbsFunDecl fun = null;
		AbsProcDecl proc = null;
		if(imaginaryDeclarationNode instanceof AbsFunDecl) {
			fun = (AbsFunDecl) imaginaryDeclarationNode;
			SemDesc.setActualType(fun, type);
		} else if(imaginaryDeclarationNode instanceof AbsProcDecl) {
			proc = (AbsProcDecl) imaginaryDeclarationNode;
			SemDesc.setActualType(proc, type);
		}
	}

	public SemTypeChecker() {
		// procedure, return type void
		addTypeToFunction("putint", new SemAtomType(SemAtomType.INT), new SemAtomType(SemAtomType.VOID));
		addTypeToFunction("putch", new SemAtomType(SemAtomType.CHAR), new SemAtomType(SemAtomType.VOID));
		// function, return type not void
		addTypeToFunction("getch", null, new SemAtomType(SemAtomType.CHAR));
		addTypeToFunction("getint", null, new SemAtomType(SemAtomType.INT));
		addTypeToFunction("ord", new SemAtomType(SemAtomType.CHAR), new SemAtomType(SemAtomType.INT));
		addTypeToFunction("chr", new SemAtomType(SemAtomType.INT), new SemAtomType(SemAtomType.CHAR));
		addTypeToFunction("free", new SemPointerType(new SemAtomType(SemAtomType.VOID)), new SemAtomType(SemAtomType.VOID));
	}
	
	
	@Override
	public void visit(AbsAlloc acceptor) {
		acceptor.type.accept(this);
		SemType type = SemDesc.getActualType(acceptor.type);
		if(type != null)
			SemDesc.setActualType(acceptor, new SemPointerType(type));
		else {
			error("Cannot find the type you are trying to allocate the memory for!", acceptor);
			SemDesc.setActualType(acceptor, new SemPointerType(new SemAtomType(SemAtomType.VOID)));
		}
	}

	@Override
	public void visit(AbsArrayType acceptor) {
		// AbsTypeExpr
		acceptor.type.accept(this);
		
		// AbsValExpr: loBound mora biti tipa integer  ??
		acceptor.loBound.accept(this);

		// AbsValExpr: hiBound mora biti tipa integer  ??
		acceptor.hiBound.accept(this);
		
		SemDesc.setActualType(acceptor, new SemArrayType(SemDesc.getActualType(acceptor.type), SemDesc.getActualConst(acceptor.loBound), SemDesc.getActualConst(acceptor.hiBound)));
	}

	@Override
	public void visit(AbsAssignStmt acceptor) {
		acceptor.dstExpr.accept(this);
		acceptor.srcExpr.accept(this);
		SemType src = SemDesc.getActualType(acceptor.srcExpr);
		SemType dst = SemDesc.getActualType(acceptor.dstExpr);
		if(src != null && dst != null) {
			if(src instanceof SemSubprogramType)
				src = ((SemSubprogramType)dst).getResultType();
			if(dst instanceof SemSubprogramType)
				dst = ((SemSubprogramType)dst).getResultType();
			if(src.coercesTo(dst)) {
				// razen atomarnih tipov in kazalcev ne moremo prirejati med sabo
				if(src instanceof SemAtomType || src instanceof SemPointerType) {
					SemDesc.setActualType(acceptor, src);
				} else error("You can only assign items of equal atom or equal pointer types!", acceptor);				
			} else {
				error("You can only assign items of equal atom or equal pointer types !", acceptor);
			}
		}
	}

	@Override
	public void visit(AbsAtomConst acceptor) {
		switch(acceptor.type) {
		case	AbsAtomConst.BOOL:
			SemDesc.setActualType(acceptor, new SemAtomType(SemAtomType.BOOL));
			break;
		case AbsAtomConst.CHAR:
			SemDesc.setActualType(acceptor, new SemAtomType(SemAtomType.CHAR));
			break;
		case AbsAtomConst.INT:
			SemDesc.setActualType(acceptor, new SemAtomType(SemAtomType.INT));
		}
	}

	@Override
	public void visit(AbsAtomType acceptor) {
		
		SemAtomType type = new SemAtomType(acceptor.type);
		SemDesc.setActualType(acceptor, type);
	}

	@Override
	public void visit(AbsBinExpr acceptor) {
		acceptor.fstExpr.accept(this);
		// AbsValExpr
		acceptor.sndExpr.accept(this);
		SemType type1 = SemDesc.getActualType(acceptor.fstExpr);
		SemType type2 = SemDesc.getActualType(acceptor.sndExpr);
		
		switch(acceptor.oper) {
		case AbsBinExpr.ARRACCESS:
			if(type1 != null && type2 != null) {
				if(type1 instanceof SemArrayType) {
					if(type2 instanceof SemAtomType) {
						if(((SemAtomType)type2).type == SemAtomType.INT) {
							SemDesc.setActualType(acceptor, ((SemArrayType) type1).type);
						} else error("Wrong type of index being accessed in the array!", acceptor);
					} else error("Wrong type of index being accessed in the array!", acceptor);
				} else error("Not an array!", acceptor);
			} //else error("Cannot get the type of the array!", acceptor);
			break;
		case AbsBinExpr.RECACCESS:
			// type of the second can be null
			if (type1 instanceof SemRecordType){
				SemRecordType f = (SemRecordType) type1;
				if (acceptor.sndExpr instanceof AbsValName){
					//error("Second is instance of AbsValName ", acceptor);
					AbsValName valname = (AbsValName) acceptor.sndExpr;
					for(int i=0; i < f.getNumFields(); i++) {
						AbsDeclName name = f.getFieldName(i);
						if(name.name.equals(valname.name)) {
							SemDesc.setActualType(acceptor, f.getFieldType(i));
							break;
						}
					}
				}else error("Cannot resolve field name in record. ", acceptor);
			}else error("first expression in record is not a record type" + acceptor.fstExpr.toString(),acceptor);
			break;
		case AbsBinExpr.LTH:
		case AbsBinExpr.GTH:
		case AbsBinExpr.GEQ:
		case AbsBinExpr.LEQ:
		case AbsBinExpr.EQU:
		case AbsBinExpr.NEQ:
			if(type1 != null && type2 != null) {
				if(type1.coercesTo(type2)) {
					if(type1 instanceof SemAtomType || type1 instanceof SemPointerType) {
						SemDesc.setActualType(acceptor, new SemAtomType(SemAtomType.BOOL));
					} else error("You can only compare items of equal atom or equal pointer types!", acceptor);
				} else error("You can only compare items of equal atom or equal pointer types!", acceptor);
			} //else error("Cannot get types for these items.", acceptor); // NOT NEEDED
			break;
		case AbsBinExpr.OR:
		case AbsBinExpr.AND:
			if(type1 != null && type2 != null) {
				if(type1.coercesTo(type2)) {
					if(type1 instanceof SemAtomType) {
						if(((SemAtomType)type1).type == SemAtomType.BOOL) {
							SemDesc.setActualType(acceptor, new SemAtomType(SemAtomType.BOOL));
						} else error("Can only OR / AND booleans!",acceptor);
					}else error("Cannot OR / AND these types!", acceptor);
				} else error("Type mismatch!", acceptor);
			} //else error("Cannot get types for these items.", acceptor); // NOT NEDED
			break;
		case AbsBinExpr.ADD:
		case AbsBinExpr.DIV:
		case AbsBinExpr.MUL:
		case AbsBinExpr.SUB:
			if(type1 != null && type2 != null) {
				if(type1.coercesTo(type2)) {
					if(type1 instanceof SemAtomType) {
						if(((SemAtomType)type1).type == SemAtomType.INT) {
							SemDesc.setActualType(acceptor, new SemAtomType(SemAtomType.INT));
						} else error("Can only perform the *, +, - and / operations on integers!",acceptor);
					}else error("Can only perform the *, +, - and / operations on integers!", acceptor);
				} else if(type1 instanceof SemAtomType && type2 instanceof SemAtomType) {
					if(((SemAtomType)type1).type == SemAtomType.INT && ((SemAtomType)type2).type == SemAtomType.BOOL) {
						
					} else if(((SemAtomType)type2).type == SemAtomType.INT && ((SemAtomType)type1).type == SemAtomType.BOOL) {
						
					}
					
				} else error("Can only perform the *, +, - and / operations on integers!", acceptor);
			}
			break;	
		}
		
	}

	@Override
	public void visit(AbsBlockStmt acceptor) {
		acceptor.stmts.accept(this);
	}

	@Override
	public void visit(AbsCallExpr acceptor) {
		//error("a", acceptor);
		acceptor.args.accept(this);
		AbsDecl decl = SemDesc.getNameDecl(acceptor.name);
		SemType type = SemDesc.getActualType(decl);
		if (type instanceof SemSubprogramType){
			SemSubprogramType thisType = new SemSubprogramType(((SemSubprogramType) type).getResultType());
			for (AbsValExpr args: acceptor.args.exprs){
				SemType argType = SemDesc.getActualType(args);
				if (argType != null){
					thisType.addParType(argType);
					if(!(argType instanceof SemAtomType)) {
						SemDesc.setActualType(acceptor, ((SemSubprogramType) type).getResultType());
						return;
					}
				}else{
					error("Problem with subprogram call parameters!", acceptor);
				}
			}
			if (type.coercesTo(thisType)){
				SemSubprogramType sub = (SemSubprogramType) type;
				SemDesc.setActualType(acceptor, sub.getResultType());
				
			}else{
				error("Subprogram call parameters do not match the declared function parameters!", acceptor);
			}
		} else error("Not a subprogram!", acceptor);
	}

	@Override
	public void visit(AbsConstDecl acceptor) {
				
		acceptor.value.accept(this);
		SemType type = SemDesc.getActualType(acceptor.value);
		if(type != null) {
			SemDesc.setActualType(acceptor, type);
		} //else error("Cannot get type of the constant \"" + acceptor.name + "\"", acceptor);
	}

	@Override
	public void visit(AbsDeclName acceptor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AbsDecls acceptor) {
		/*
 * @see AbsConstDecl
 * @see AbsDeclName
 * @see AbsFunDecl
 * @see AbsProcDecl
 * @see AbsTypeDecl
 * @see AbsVarDecl
		 */
		for(AbsDecl d : acceptor.decls)
			d.accept(this);
		
	}

	@Override
	public void visit(AbsExprStmt acceptor) {
		// stavek ki je zgolj opis vrednosti, mora biti klic procedure
		
		if(acceptor.expr instanceof AbsCallExpr) {
			// check if AbsCallExpr is a procedure and not a function call
			AbsDecl decl = SemDesc.getNameDecl(((AbsCallExpr)acceptor.expr).name);
			if(decl == null)
				error("Cannot find subprogram with the name \"" +((AbsCallExpr)acceptor.expr).name.name + "\"",acceptor);
			else if(decl instanceof AbsProcDecl)
				acceptor.expr.accept(this);
			else
				error("Supposedly you cannot call functions like this. They have types.", acceptor);
			
		} else {
			error("Statement invalid, procedure call expected.", acceptor);
		}
		acceptor.expr.accept(this);
	}

	@Override
	public void visit(AbsForStmt acceptor) {
		acceptor.name.accept(this);
		SemType type0 = SemDesc.getActualType(acceptor.name);
		if(type0 == null) {
			
		} else if(!(type0 instanceof SemAtomType)) {
			error("For loop variable must be an integer!", acceptor);
		} else if(((SemAtomType)type0).type != SemAtomType.INT) {
			error("For loop variable must be an integer!", acceptor);
		}
		
		acceptor.hiBound.accept(this);
		acceptor.loBound.accept(this);
		
		SemType type1 = SemDesc.getActualType(acceptor.loBound);
		SemType type2 = SemDesc.getActualType(acceptor.hiBound);
		if(type1 != null && type2 != null) {
			if(type1.coercesTo(type2)) {
				if(type1 instanceof SemAtomType) {
					if(((SemAtomType)type1).type != SemAtomType.INT) {
						error("For loop bounds must be integers!", acceptor);
					}
				}
			} else error("For loop bound type mismatch!", acceptor);
		} //else error("Cannot get types for bounds!", acceptor);
		
		acceptor.stmt.accept(this);
		
	}

	@Override
	public void visit(AbsFunDecl acceptor) {
		acceptor.pars.accept(this);
		acceptor.type.accept(this);
		SemType res = SemDesc.getActualType(acceptor.type);
		SemSubprogramType type = new SemSubprogramType(res);
		for(AbsDecl d : acceptor.pars.decls) {
			SemType parType = SemDesc.getActualType(d);
			if(parType != null)	type.addParType(parType);
			else error("Incorrect function parameter types!", acceptor);
		}
		SemDesc.setActualType(acceptor, type);
		acceptor.decls.accept(this);
		acceptor.stmt.accept(this);
		
	}

	@Override
	public void visit(AbsIfStmt acceptor) {
		acceptor.cond.accept(this);
		
		SemType type = SemDesc.getActualType(acceptor.cond);
		if(type == null)
			error("Cannot get condition type!", acceptor);
		else if(!(type instanceof SemAtomType && ((SemAtomType)type).type == SemAtomType.BOOL))
			error("Wrong if statement condition type!", acceptor);
		
		acceptor.thenStmt.accept(this);
		acceptor.elseStmt.accept(this);
	}

	@Override
	public void visit(AbsNilConst acceptor) {
		SemDesc.setActualType(acceptor, new SemPointerType(new SemAtomType(SemAtomType.VOID)));
	}

	@Override
	public void visit(AbsPointerType acceptor) {
		acceptor.type.accept(this);
		SemType type = SemDesc.getActualType(acceptor.type);
		SemDesc.setActualType(acceptor, new SemPointerType(type));
	}

	@Override
	public void visit(AbsProcDecl acceptor) {
		acceptor.pars.accept(this);
		
		SemSubprogramType type = new SemSubprogramType(new SemAtomType(SemAtomType.VOID));
		SemDesc.setActualType(acceptor, type);
		
		
		for (AbsDecl decl: acceptor.pars.decls){
			SemType paramType = SemDesc.getActualType(decl);
			if (paramType != null){
				type.addParType(paramType);
			}else{
				error("Cannot get parameter type!", acceptor);
			}
		}
		
		acceptor.decls.accept(this);
		acceptor.stmt.accept(this);
	}

	@Override
	public void visit(AbsProgram acceptor) {
		acceptor.decls.accept(this);
		acceptor.stmt.accept(this);
	}

	@Override
	public void visit(AbsRecordType acceptor) {
		acceptor.fields.accept(this);
		SemRecordType type = new SemRecordType();
		
		for(AbsDecl d: acceptor.fields.decls) {
			if(d instanceof AbsVarDecl) {
				type.addField(((AbsVarDecl) d).name, SemDesc.getActualType(d));
			}
		}
		SemDesc.setActualType(acceptor, type);
	}

	@Override
	public void visit(AbsStmts acceptor) {
		/*
 * @see AbsAssignStmt
 * @see AbsBeginStmt
 * @see AbsExprStmt
 * @see AbsForStmt
 * @see AbsIfStmt
 * @see AbsWhileStmt
		 */
		for(AbsStmt s : acceptor.stmts) {
			s.accept(this);
		}
		
	}

	@Override
	public void visit(AbsTypeDecl acceptor) {
		// AbsTypeExpr
		/*
 * @see AbsAtomType 
 * @see AbsArrayType
 * @see AbsRecortType 
 * @see AbsPointerType
		 */
		acceptor.type.accept(this);
		AbsDecl decl = SemDesc.getNameDecl(acceptor);
		if(SemDesc.getActualType(acceptor.type) != null){
			SemDesc.setActualType(acceptor, SemDesc.getActualType(acceptor.type));
		}
		else
			error("I can't get the type in AbsTypeDecl for :" + acceptor.name.name, acceptor);
		
	}

	@Override
	public void visit(AbsTypeName acceptor) {
		
		AbsDecl decl = SemDesc.getNameDecl(acceptor);
		
		if(decl == null) return;
		SemType type = SemDesc.getActualType(decl);
		if(type == null) return;
		SemDesc.setActualType(acceptor, type);
		
	}

	@Override
	public void visit(AbsUnExpr acceptor) {
		acceptor.expr.accept(this);
		SemType type = SemDesc.getActualType(acceptor.expr);
		if(type == null){
			return;
		}
		
		switch(acceptor.oper) {
		case AbsUnExpr.ADD:
		case AbsUnExpr.SUB:
			if (type instanceof SemAtomType) {
				if (((SemAtomType) type).type == SemAtomType.INT) {
					SemDesc.setActualType(acceptor, type);
				} else error("Wrong type for unary +, -, must be an integer!",acceptor);
			} else error("Wrong type for unary +, -, must be an integer!", acceptor);
			break;
		case AbsUnExpr.NOT:
			if(type instanceof SemAtomType) {
				if(((SemAtomType)type).type == AbsAtomType.BOOL) {
					SemDesc.setActualType(acceptor, type);
				} else error("Wrong type! Can only NOT a boolean.", acceptor);
			} else error("Wrong type!", acceptor);
			break;
		case AbsUnExpr.MEM:
			// prefiksni 
			SemPointerType ptype = new SemPointerType(type);
			SemDesc.setActualType(acceptor, ptype);
			break;
		case AbsUnExpr.VAL:
			// postfiksni - 
			if(type instanceof SemPointerType) {
				SemDesc.setActualType(acceptor, ((SemPointerType)type).type);
			} else error("Cannot resolve a non-pointer!", acceptor);
		}
		
	}

	@Override
	public void visit(AbsValExprs acceptor) {
		for(AbsValExpr e: acceptor.exprs) {
			/* * @see AbsConstExpr (AbsAtomConst, AbsNilConst)
			 * @see AbsUnExpr
			 * @see AbsBinExpr
			 * @see AbsValName
			 * @see AbsCallExpr
			 * @see AbsAlloc */
			e.accept(this);
		}
	}

	@Override
	public void visit(AbsValName acceptor) {
		AbsDecl decl = SemDesc.getNameDecl(acceptor);

		// in this node the SemNameResolver should have created a name declaration
		SemType type = SemDesc.getActualType(decl);
		if (decl instanceof AbsFunDecl){

			type = SemDesc.getActualType(((AbsFunDecl)decl).type);
		}
		if (type != null) {
			SemDesc.setActualType(acceptor, SemDesc.getActualType(decl));
		}
		
	}

	@Override
	public void visit(AbsVarDecl acceptor) {
		// AbsTypeExpr - acceptor.type
		/* * @see AbsAtomType (check)
		 *  ++++ AbsTypeName
		 * @see AbsArrayType
		 * @see AbsRecortType
		 * @see AbsPointerType */
		acceptor.type.accept(this);
		acceptor.name.accept(this);
		
		SemType type = SemDesc.getActualType(acceptor.type);
		if(type == null) {
			return;
		}
		SemDesc.setActualType(acceptor.name, type);
		SemDesc.setActualType(acceptor, type);

	}

	@Override
	public void visit(AbsWhileStmt acceptor) {

		acceptor.cond.accept(this);
		if((SemDesc.getActualType(acceptor.cond)) == null) {
			//error("Cannot get type for while statement condition!", acceptor);
			return;
		}
		
		SemType type = SemDesc.getActualType(acceptor.cond);
		if(type instanceof SemAtomType)
			if(((SemAtomType)type).type == SemAtomType.BOOL)
				acceptor.cond.accept(this);
		else
			error("While statement condition must be a boolean value!", acceptor);
		acceptor.stmt.accept(this);
		
	}
	
	private void error(String msg, AbsTree el) {
		Report.warning(msg, el.begLine, el.begColumn, el.endLine, el.endColumn);
		error = true;
	}

	@Override
	public void visit(QMarkStmt acceptor) {
		//if(acceptor.cond)
		acceptor.cond.accept(this);
		
		SemType type = SemDesc.getActualType(acceptor.cond);
		if(type == null)
			error("Cannot get condition type!", acceptor);
		else if(!(type instanceof SemAtomType && ((SemAtomType)type).type == SemAtomType.BOOL))
			error("Wrong question mark statement condition type!", acceptor);
		
		
		acceptor.stmt1.accept(this);
		acceptor.stmt2.accept(this);
		
		SemType type1 = SemDesc.getActualType(acceptor.stmt1);
		SemType type2 = SemDesc.getActualType(acceptor.stmt2);
		

		if(!type1.coercesTo(type2)) {
			error("Question mark statements have to be of the same type!", acceptor);
		}
	}

}
