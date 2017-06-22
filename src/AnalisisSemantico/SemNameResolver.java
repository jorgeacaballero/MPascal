package AnalisisSemantico;

import AST.*;
import Assets.*;

public class SemNameResolver implements AbsVisitor {
	
	public boolean error;
	public boolean isGlobal = false;
	
	public void insertFunction(String name) {
	    try {
                    SemTable.ins(name, new AbsFunDecl(new AbsDeclName(name), null, null, null, null));
		}catch (SemIllegalInsertException e) {
			e.printStackTrace();
		}
	}
	
	public void insertProcedure(String name) {
	    try {
			SemTable.ins(name, new AbsProcDecl(new AbsDeclName(name), null, null, null));
		} catch (SemIllegalInsertException e) {
			e.printStackTrace();
		}
	}
	
	//@Override
	public SemNameResolver() {
		insertProcedure("putint");
		insertProcedure("putch");
		insertFunction("getch");
		insertFunction("getint");
		insertFunction("ord");
		insertFunction("chr");
		insertProcedure("free");
	}
	
	@Override
	public void visit(AbsAlloc acceptor) {
		acceptor.type.accept(this);
	}

	@Override
	public void visit(AbsArrayType acceptor) {
		
		acceptor.hiBound.accept(this);
		acceptor.loBound.accept(this);
		
		Integer low = SemDesc.getActualConst(acceptor.loBound);
		Integer high = SemDesc.getActualConst(acceptor.hiBound);
		
		if(low != null && high != null) {
		} else error("Cannot get numerical values for low and high bound of array!", acceptor);
		
		
		if(low < 0 || high < 0 || high < low) {
			error("You can only use positive values for array bounds, and they must be in ascending order!", acceptor);
		}
		acceptor.type.accept(this);
	}

	@Override
	public void visit(AbsAssignStmt acceptor) {
		acceptor.dstExpr.accept(this);
		acceptor.srcExpr.accept(this);
	}

	@Override
	public void visit(AbsAtomConst acceptor) {
		 
		
		switch(acceptor.type) {
		case AbsAtomConst.INT:
			SemDesc.setActualConst(acceptor, Integer.parseInt(acceptor.value));
			break;
		case AbsAtomConst.BOOL:
			if(acceptor.value.equals("true"))
				SemDesc.setActualConst(acceptor, 1);
			else
				SemDesc.setActualConst(acceptor, 0);
			break;
		case AbsAtomConst.CHAR:
			SemDesc.setActualConst(acceptor, (int)acceptor.value.charAt(1));
			break;
		}
	}

	@Override
	public void visit(AbsAtomType acceptor) {
	}

	@Override
	public void visit(AbsBinExpr acceptor) {
		acceptor.fstExpr.accept(this);
		if(acceptor.oper == AbsBinExpr.RECACCESS)
			return;
		acceptor.sndExpr.accept(this);
		Integer fst = SemDesc.getActualConst(acceptor.fstExpr);
		Integer snd = SemDesc.getActualConst(acceptor.sndExpr);
		if (fst != null && snd != null){
			// if its a pointer it will be null
			switch (acceptor.oper) {
			case AbsBinExpr.ADD:
					SemDesc.setActualConst(acceptor, fst + snd);
				break;
			case AbsBinExpr.SUB:
				SemDesc.setActualConst(acceptor, fst - snd);
				break;
			case AbsBinExpr.MUL:
				SemDesc.setActualConst(acceptor, fst *	 snd);
				break;
			case AbsBinExpr.DIV:
				if (snd != 0)	SemDesc.setActualConst(acceptor, fst / snd);
				break;
			case AbsBinExpr.GTH:
				if(fst > snd)	SemDesc.setActualConst(acceptor, 1);
				else	SemDesc.setActualConst(acceptor, 0);
				break;
			case AbsBinExpr.LTH:
				if(fst < snd)	SemDesc.setActualConst(acceptor, 1);
				else	SemDesc.setActualConst(acceptor, 0);
				break;
			case AbsBinExpr.GEQ:
				if(fst >= snd)	SemDesc.setActualConst(acceptor, 1);
				else	SemDesc.setActualConst(acceptor, 0);
				break;
			case AbsBinExpr.LEQ:
				if(fst <= snd)	SemDesc.setActualConst(acceptor, 1);
				else	SemDesc.setActualConst(acceptor, 0);
				break;
			case AbsBinExpr.EQU:
				if(fst == snd)	SemDesc.setActualConst(acceptor, 1);
				else	SemDesc.setActualConst(acceptor, 0);
			}
		}
		
	}

	@Override
	public void visit(AbsBlockStmt acceptor) {
		acceptor.stmts.accept(this);
	}

	@Override
	public void visit(AbsCallExpr acceptor) {
		 
		
		AbsDecl decl = SemTable.fnd(acceptor.name.name);
		if(decl == null)
			error("Subprogram " + acceptor.name.name + " not declared!", acceptor);
		else {
			SemDesc.setNameDecl(acceptor.name, decl);
		}
		
		acceptor.args.accept(this);
	}

	@Override
	public void visit(AbsConstDecl acceptor) {
		 
		try {
			SemTable.ins(acceptor.name.name, acceptor);
		} catch(SemIllegalInsertException e) {
			error(acceptor.name+" already declared!", acceptor);
		}
		acceptor.value.accept(this);
		if(SemDesc.getActualConst(acceptor.value) == null)
			error("Cannot get actual constant from value " + ((AbsConstDecl)SemDesc.getNameDecl(acceptor.value)).name.name, acceptor);
		else 
			SemDesc.setActualConst(acceptor, SemDesc.getActualConst(acceptor.value));
	}

	@Override
	public void visit(AbsDeclName acceptor) {
		// do nothing
	}

	@Override
	public void visit(AbsDecls acceptor) {
		
		/**
		 * 
		 * 
		 * @see AbsConstDecl
		 * @see AbsDeclName
		 * @see AbsFunDecl
		 * @see AbsProcDecl
		 * @see AbsTypeDecl
		 * @see AbsVarDecl
		 */
		
		for(AbsDecl decl:acceptor.decls) {
			decl.accept(this);
		}
	}

	@Override
	public void visit(AbsExprStmt acceptor) {
		
		acceptor.expr.accept(this);
	}

	@Override
	public void visit(AbsForStmt acceptor) {
		
		AbsDecl decl = SemTable.fnd(acceptor.name.name);
		if(decl == null) {
			error("For loop variable " + acceptor.name.name + " not declared!", acceptor);
		}
		/
		
		acceptor.name.accept(this);
		acceptor.hiBound.accept(this);
		acceptor.loBound.accept(this);
		acceptor.stmt.accept(this);
	}

	@Override
	public void visit(AbsFunDecl acceptor) {
		
		
		acceptor.type.accept(this);
		
		
		
		SemTable.newScope();
		try {
			SemTable.ins(acceptor.name.name, acceptor);
		} catch(SemIllegalInsertException e){
			error("Function name "+acceptor.name.name+" already declared!", acceptor);
		}		
		acceptor.pars.accept(this);
		acceptor.decls.accept(this);
		acceptor.stmt.accept(this);
		SemTable.oldScope();
		
		try {
			SemTable.ins(acceptor.name.name, acceptor);
		} catch(SemIllegalInsertException e){
			error("Function name "+acceptor.name.name+" already declared!", acceptor);
		}
		
		
	}

	@Override
	public void visit(AbsIfStmt acceptor) {
		acceptor.cond.accept(this);
		acceptor.thenStmt.accept(this);
		acceptor.elseStmt.accept(this);
	}

	@Override
	public void visit(AbsNilConst acceptor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AbsPointerType acceptor) {
		acceptor.type.accept(this);
	}

	@Override
	public void visit(AbsProcDecl acceptor) {
		
		//acceptor.name.accept(this);
		
		try {
			SemTable.ins(acceptor.name.name, acceptor);
		} catch(SemIllegalInsertException e){
			error("Procedure name "+acceptor.name.name+" already declared!", acceptor);
		}
		
		SemTable.newScope();
		
		/*try {
			SemTable.ins(acceptor.name.name, acceptor);
		} catch(SemIllegalInsertException e){
			error("Procedure name "+acceptor.name.name+" already declared!", acceptor);
		}*/
		
		acceptor.pars.accept(this);
		acceptor.decls.accept(this);	
		acceptor.stmt.accept(this);
		SemTable.oldScope();
		
	}

	@Override
	public void visit(AbsProgram acceptor) {
		try {
			SemTable.ins(acceptor.name.name, acceptor.name);
		} catch(SemIllegalInsertException e){
			error("Program name cannot be equal to any of the function names.", acceptor);
		}
		
		//SemTable.newScope();
		isGlobal = true;
		acceptor.name.accept(this);
		
		for(AbsDecl d: acceptor.decls.decls) {
			if(d instanceof AbsVarDecl) {
				if(((AbsVarDecl)d).visType.type == VisibilityType.PRIVATE) {
					error("Variable " + ((AbsVarDecl)d).name.name + " is global, you cannot declare it as private!", acceptor);
				}
			} 
			d.accept(this);
		}
		
		
		acceptor.stmt.accept(this);
		isGlobal = false;
		//SemTable.oldScope();
	}

	@Override
	public void visit(AbsRecordType acceptor) {
		
		
		SemTable.newScope();
		acceptor.fields.accept(this);
		SemTable.oldScope();
		
		
		// ????
		for (AbsDecl d : acceptor.fields.decls)
			SemDesc.setScope(d, -1);
	}

	@Override
	public void visit(AbsStmts acceptor) {
		for(AbsStmt s: acceptor.stmts) {
			s.accept(this);
		}
		
	}

	@Override
	public void visit(AbsTypeDecl acceptor) {
		/* AbsTypeExpr:
		 * @see AbsAtomType
		 * @see AbsArrayType
		 * @see AbsRecordType
		 * @see AbsPointerType
		 * 
		*/
		try {
			SemTable.ins(acceptor.name.name, acceptor);
		} catch(SemIllegalInsertException e) {
			error("Type \"" + acceptor.name.name + "\" already declared!", acceptor);
		}
		//AbsTypeExpr
		acceptor.type.accept(this);
		SemDesc.setActualType(acceptor, SemDesc.getActualType(acceptor.type));
	}

	@Override
	public void visit(AbsTypeName acceptor) {
		AbsDecl decl = SemTable.fnd(acceptor.name);
		if(decl == null) {
			error("Type \"" + acceptor.name + "\" has not yet been declared!", acceptor);
		} else {
			SemDesc.setNameDecl(acceptor, decl);
		}
		
		
	}

	@Override
	public void visit(AbsUnExpr acceptor) {
		
		acceptor.expr.accept(this);
		
		Integer val = SemDesc.getActualConst(acceptor.expr);
		if(val == null)	return;
		switch(val) {
		case AbsUnExpr.ADD:
			SemDesc.setActualConst(acceptor, val);
			break;
		case AbsUnExpr.SUB:
			SemDesc.setActualConst(acceptor, -val);
			break;
		}
	}

	@Override
	public void visit(AbsValExprs acceptor) {
		/* * @see AbsConstExpr
		 * @see AbsUnExpr
		 * @see AbsBinExpr
		 * @see AbsValName
		 * @see AbsCallExpr
		 * @see AbsAlloc */
		for (AbsValExpr e: acceptor.exprs)
			e.accept(this);
	}

	@Override
	public void visit(AbsValName acceptor) {
		
		
		
		AbsDecl name = SemTable.fnd(acceptor.name);
		
		
		if(name == null){
			error("\""+acceptor.name+"\"" + " not declared!", acceptor); 
			try {
			SemTable.ins(acceptor.name, new AbsConstDecl(new AbsDeclName(acceptor.name), new AbsAtomConst("0", 2)));
			AbsDecl errorDecl = SemTable.fnd(acceptor.name);
			SemDesc.setNameDecl(acceptor, errorDecl);
			SemDesc.setActualConst(errorDecl, 0);
			SemDesc.setActualConst(acceptor, 0);
			SemDesc.setActualType(errorDecl, new SemAtomType(SemAtomType.INT));
			Integer a = SemDesc.getActualConst(errorDecl);
			
			} catch(Exception e) {
				error("Some weird error", acceptor);
			}
		}
		else {
			SemDesc.setNameDecl(acceptor, name);
			if(SemDesc.getActualConst(name) != null)
			SemDesc.setActualConst(acceptor, SemDesc.getActualConst(name));
		}
		
	}

	@Override
	public void visit(AbsVarDecl acceptor) {
		// insert variable name into semtable

		
		try {
				SemTable.ins(acceptor.name.name, acceptor);
				
		} catch (SemIllegalInsertException e) {
				error("Variable " + acceptor.name.name + " already declared!", acceptor);
		}
		
		acceptor.type.accept(this);
	}

	@Override
	public void visit(AbsWhileStmt acceptor) {
		acceptor.stmt.accept(this);
		acceptor.cond.accept(this);
		
	}
	
	private void error(String msg, AbsTree el) {
		Report.warning(msg, el.begLine, el.begColumn, el.endLine, el.endColumn);
		error = true;
	}

	@Override
	public void visit(QMarkStmt acceptor) {
		acceptor.cond.accept(this);
		acceptor.stmt2.accept(this);
		acceptor.stmt1.accept(this);
	}
}
