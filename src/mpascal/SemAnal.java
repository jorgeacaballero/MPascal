
package mpascal;

import java.util.ArrayList;
import imcode.QuadGen;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author jmlb
 */
public class SemAnal {
    static String currentScope;
    static SymbolTable symtable = new SymbolTable();
    static int offset;
    static String tempType = "";
    static ArrayList<Element> leafNodes = new ArrayList();
    static String currentType = "";
    static String functionType = "";
    static int errorNumber = 0;
    static boolean inAFunction = false;
    static boolean debug = false;

    public static SymbolTable fillSymTable(Element fatherNode) throws Exception {
        currentScope = "main";
        errorNumber = 0;
        readTree(fatherNode, "0", "0");
        if (errorNumber > 0) {
            System.err.println("------------------------------------------------------------------------");
            String message = "Errors found, aborting. \n";
            message = String.format(message, errorNumber);
            System.err.println(message);
        } else {
            symtable.toString();
            System.err.println("------------------------------------------------------------------------");
            QuadGen G = new QuadGen(symtable);
            G.parseTree(fatherNode);
            G.print();
            System.err.println("------------------------------------------------------------------------");
        }
        return symtable;
    }

    private static void readTree(Element fatherNode, String Linea, String Columna) throws Exception {
        NodeList childNodes = fatherNode.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Element nodo = (Element) childNodes.item(i);
            String nodeName = nodo.getNodeName();
            if (debug) {
                System.out.println("RecorrerArbol - " + nodeName);
            }
            switch (nodeName) {
                case "ReadStatement": {
                    currentType = "";
                    readTree(nodo, Linea, Columna);
                    if (currentType.equals("boolean") || currentType.startsWith("Array")) {
                        String tipo = currentType;
                        currentType = "[string, integer, char]";
                        Element arg = (Element) nodo.getFirstChild();
                        Linea = arg.getAttribute("Line");
                        Columna = arg.getAttribute("Column");
                        throwIncompatibleTypeError(Linea, Columna, tipo);
                        currentType = tipo;
                    }
                    break;
                }
                case "WriteStatement": {
                    currentType = "";
                    NodeList lista = nodo.getChildNodes();
                    if (lista.getLength() > 1) {
                        readTree(nodo, Linea, Columna);
                        if (currentType.equals("boolean") || currentType.startsWith("Array")) {
                            String tipo = currentType;
                            currentType = "[string, integer, char]";
                            Element arg = (Element) nodo.getFirstChild();
                            Linea = arg.getAttribute("Line");
                            Columna = arg.getAttribute("Column");
                            throwIncompatibleTypeError(Linea, Columna, tipo);
                            currentType = tipo;
                        }
                    }

                    break;
                }
                case "VarDeclaration": {
                    String type = ((Element) nodo.getLastChild()).getAttribute("Value");
                    int size = Integer.parseInt(
                            ((Element) nodo.getLastChild()).getAttribute("Size")
                    );
                    NodeList idList = nodo.getElementsByTagName("ID");
                    for (int j = 0; j < idList.getLength(); j++) {
                        String ID = ((Element) idList.item(j)).getAttribute("Value");
                        Simbolo S = new Simbolo(ID, null, type, currentScope, true, false, false, offset);
                        symtable.Add(S);
                        offset += size;
                    }
                    break;
                }
                case "inlineArg": {
                    String type = ((Element) nodo.getLastChild()).getAttribute("Value");
                    String strSize = ((Element) nodo.getLastChild()).getAttribute("Size");
                    String isPointer = ((Element) nodo.getLastChild()).getAttribute("isPointer");

                    int size = Integer.parseInt(strSize.isEmpty() ? "0" : strSize);
                    NodeList idList = nodo.getElementsByTagName("ID");
                    for (int j = 0; j < idList.getLength(); j++) {
                        if (tempType.isEmpty()) {
                            tempType += type;
                        } else {
                            tempType += "X" + type;
                        }
                        String ID = ((Element) idList.item(j)).getAttribute("Value");
                        Simbolo S = new Simbolo(ID, null, type, currentScope, false, false, true, offset);
                        if (isPointer.equals("true")) {
                            S.setByRef(true);
                        } else {
                            S.setByRef(false);
                        }
                        symtable.Add(S);
                        offset += size;
                    }
                    break;
                }
                case "ProcedureDeclaration": {
                    String ID = nodo.getAttribute("ID");
                    Simbolo S = new Simbolo(ID, null, "void -> void", "main", false, true, false, offset);
                    int indice = symtable.Add(S);
                    currentScope = nodo.getAttribute("ID");
                    int backupOffset = offset;
                    offset = 0;
                    inAFunction = true;
                    readTree(nodo, Linea, Columna);
                    inAFunction = false;
                    if (!tempType.isEmpty()) {
                        tempType += " -> void";
                        S.setType(tempType);
                        symtable.replaceNode(S, indice);
                    }
                    tempType = "";
                    currentScope = "main";
                    offset = backupOffset;
                    break;
                }
                case "FunctionDeclaration": {
                    String ID = nodo.getAttribute("ID");
                    String type = nodo.getAttribute("Type");
                    Simbolo S = new Simbolo(ID, null, type, "main", false, true, false, offset);
                    int indice = symtable.Add(S);
                    int backupOffset = offset;
                    offset = 0;
                    currentScope = nodo.getAttribute("ID");
                    inAFunction = true;
                    readTree(nodo, Linea, Columna);
                    inAFunction = false;
                    if (!tempType.isEmpty()) {
                        tempType += " -> " + type;
                        S.setType(tempType);
                    } else {
                        S.setType("void -> " + type);
                    }
                    symtable.replaceNode(S, indice);
                    tempType = "";
                    currentScope = "main";
                    offset = backupOffset;
                    break;
                }
                case "Literal": {
                    String type = nodo.getAttribute("Type");
                    Linea = nodo.getAttribute("Line");
                    Columna = nodo.getAttribute("Column");
                    if (!currentType.isEmpty() && !currentType.equals(type)) {
                        throwIncompatibleTypeError(Linea, Columna, type);
                    }
                    break;
                }
                case "Assignment": {
                    Element IdNode = (Element) nodo.getFirstChild();
                    String IdValex = IdNode.getAttribute("Value");
                    Simbolo S = symtable.getVariable(IdValex, currentScope);
                    if(!currentScope.equals("main") && S == null){
                        S = symtable.getFunction(IdValex);
                    }
                    if(S == null){
                        S = symtable.getVariable(IdValex, "main");
                    }
                    Linea = IdNode.getAttribute("Line");
                    Columna = IdNode.getAttribute("Column");
                    if (S == null) {
                        throwNotFoundError(Linea, Columna, IdValex);
                    }
                    currentType = "";
                    readTree(nodo, Linea, Columna);
                    currentType = "";
                    
                    break;
                }
                case "ID": {
                    String idValex = nodo.getAttribute("Value");
                    String parentName = nodo.getParentNode().getNodeName();
                    boolean programIsParent = parentName.equals("Program");
                    Linea = nodo.getAttribute("Line");
                    Columna = nodo.getAttribute("Column");
                    if (programIsParent) {
                        readTree(nodo, nodo.getAttribute("Line"), nodo.getAttribute("Column"));
                        break;
                    }

                    Simbolo S = symtable.getVariable(idValex, currentScope);
                    
                    if(S == null){
                        S = symtable.getVariable(idValex, "main");
                    }
                    
                    if(inAFunction && currentType.isEmpty() && S == null){
                        S = symtable.getFunction(idValex);
                        currentType = S.getType();
                        Element parent = (Element)nodo.getParentNode();
                        parent.setAttribute("Return", "true");
                    }

                    if (S == null) {
                        throwNotFoundError(Linea, Columna, idValex);
                    }
                    
                    boolean isSameType = S.getType().equals(currentType);
                    if (!currentType.isEmpty() && !isSameType) {
                        String currentType = S.getType().split("\\.")[0];
                        throwIncompatibleTypeError(Linea, Columna, currentType);
                    } else {
                        if(currentType.isEmpty())
                            currentType = S.getType();
                    }
                    readTree(nodo, nodo.getAttribute("Line"), nodo.getAttribute("Column"));
                    break;
                }
                case "FunctionCall": {
                    String tipoBKP = functionType;
                    functionType = "";
                    Element functionId = (Element) nodo.getFirstChild();
                    String id = functionId.getAttribute("Value");
                    Linea = functionId.getAttribute("Line");
                    Columna = functionId.getAttribute("Column");
                    Simbolo S = symtable.getFunction(id);
                    String tipoRetorno = "";
                    if (S == null) {
                        throwNotFoundError(Linea, Columna, id);

                    }
                    tipoRetorno = S.getType().split(" -> ")[1];

                    if (nodo.getChildNodes().getLength() > 1) {
                        verifyFunction((Element) nodo.getLastChild());
                        functionType = functionType + " -> " + tipoRetorno;
                    } else {
                        functionType = "void -> " + tipoRetorno;
                    }
                    if (!currentType.isEmpty() && !currentType.equals(tipoRetorno)) {
                        throwIncompatibleTypeError(Linea, Columna, tipoRetorno);
                    }
                    if (!functionType.equals(S.getType())) {
                        throwFunctionArgsError(Linea, Columna, id);
                    }
                    functionType = tipoBKP;

                    break;
                }
                case "GreaterThan":
                case "LessThan":
                case "Equals":
                case "LessOrEqual":
                case "GreaterOrEqual":
                case "Different": {
                    if (!currentType.isEmpty() && currentType.equals("boolean")) {
                        String tipoActualBKP = currentType;
                        currentType = "";
                        typeChecking(nodo);
                        currentType = tipoActualBKP;
                    } else if (currentType.isEmpty()) {
                        typeChecking(nodo);
                        currentType = "";
                    } else {
                        throwIncompatibleTypeError(Linea, Columna, "boolean");
                    }

                    break;
                }
                case "AND":
                case "OR":
                case "NOT": {
                    if (!currentType.isEmpty() && currentType.equals("boolean")) {
                        readTree(nodo, Linea, Columna);
                        currentType = "";
                    } else if (currentType.isEmpty()) {
                        readTree(nodo, Linea, Columna);
                    } else {
                        throwIncompatibleTypeError(Linea, Columna, "boolean");
                    }
                    break;
                }
                case "IfStatement": {
                    Linea = nodo.getAttribute("Line");
                    Columna = nodo.getAttribute("Column");
                    String tipoBKP = currentType;
                    currentType = "boolean";
                    readTree(nodo, Linea, Columna);
                    currentType = tipoBKP;
                    break;
                }
                case "ARRAY": {
                    String id = nodo.getAttribute("Value");
                    Simbolo S = symtable.getVariable(id, currentScope);
                    Linea = nodo.getAttribute("Line");
                    Columna = nodo.getAttribute("Column");

                    if (S == null) {
                        throwNotFoundError(Linea, Columna, id);
                    } else if (!S.getType().startsWith("Array")) {
                        throwIlegalExpresionError(Linea, Columna);
                    } else {
                        String tipo = S.getType().split("\\.")[1];
                        String tipoBKP = currentType;
                        if (currentType.isEmpty()) {
                            System.out.println("1");
                            currentType = "integer";
                            typeChecking(nodo);
                            currentType = tipo;
                        } else if (currentType.equals(tipo)) {
                            System.out.println("2");
                            currentType = "integer";
                            typeChecking(nodo);
                            currentType = tipoBKP;
                        } else {
                            throwIncompatibleTypeError(Linea, Columna, tipo);
                        }

                    }
                    break;
                }
                default: {
                    readTree(nodo, Linea, Columna);
                    break;
                }
            }
        }

    }

    private static void typeChecking(Element fatherNode) throws Exception {

        NodeList hijos = fatherNode.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element) hijos.item(i);
            String nodeName = nodo.getNodeName();
            if (debug) {
                System.out.println("Comprobar Tipos - " + nodeName);
            }
            switch (nodeName) {
                case "Literal": {
                    String type = nodo.getAttribute("Type");
                    if (currentType.isEmpty()) {
                        currentType = type;
                    }
                    if (!currentType.equals(type)) {
                        String Line = nodo.getAttribute("Line");
                        String Column = nodo.getAttribute("Column");
                        throwIncompatibleTypeError(Line, Column, type);
                    }
                    break;
                }
                case "ID": {
                    String id = nodo.getAttribute("Value");
                    Simbolo S = symtable.getVariable(id, currentScope);
                    if (S == null) {
                        String Line = nodo.getAttribute("Line");
                        String Column = nodo.getAttribute("Column");
                        throwNotFoundError(Line, Column, id);

                    } else {
                        boolean isSameType = S.getType().equals(currentType);
                        if (!currentType.isEmpty() && !isSameType) {

                            String Line = nodo.getAttribute("Line");
                            String Column = nodo.getAttribute("Column");
                            String currentType = S.getType().split("\\.")[0];
                            throwIncompatibleTypeError(Line, Column, currentType);

                        } else {
                            currentType = S.getType();
                        }
                    }
                    break;
                }
                case "Minus":
                case "Times":
                case "Div": {
                    verifyArithmetic(nodo);
                    break;
                }
                case "Plus": {
                    typeChecking(nodo);
                    break;
                }
                case "ARRAY": {
                    String id = nodo.getAttribute("Value");
                    Simbolo S = symtable.getVariable(id, currentScope);
                    String Linea = nodo.getAttribute("Line");
                    String Columna = nodo.getAttribute("Column");
                    if (S == null) {
                        throwNotFoundError(Linea, Columna, id);
                    } else if (!S.getType().startsWith("Array")) {
                        throwIlegalExpresionError(Linea, Columna);
                    } else {
                        String tipo = S.getType().split("\\.")[1];
                        String tipoBKP = currentType;
                        if (currentType.isEmpty()) {
                            currentType = "integer";
                            typeChecking(nodo);
                        } else if (currentType.equals(tipo)) {
                            currentType = "integer";
                            typeChecking(nodo);

                        } else {
                            throwIncompatibleTypeError(Linea, Columna, tipo);
                        }
                        currentType = tipoBKP;
                    }
                    break;
                }
                default: {
                    typeChecking(nodo);
                }
            }

        }
    }

    //Verifies arithmetic expressions
    private static void verifyArithmetic(Element fatherNode) throws Exception {
        NodeList hijos = fatherNode.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element) hijos.item(i);
            String nodeName = nodo.getNodeName();
            if (debug) {
                System.out.println("Comprobar Arit - " + nodeName);
            }
            switch (nodeName) {
                case "Literal": {
                    String type = nodo.getAttribute("Type");
                    if (currentType.isEmpty()) {
                        currentType = type;
                    }
                    boolean isInteger = type.equals("integer");
                    if (!isInteger) {
                        String Line = nodo.getAttribute("Line");
                        String Column = nodo.getAttribute("Column");
                        throwIncompatibleTypeError(Line, Column, type);
                    }
                    break;
                }
                case "ID": {
                    String id = nodo.getAttribute("Value");
                    Simbolo S = symtable.getVariable(id, currentScope);
                    if (S == null) {
                        String Line = nodo.getAttribute("Line");
                        String Column = nodo.getAttribute("Column");
                        throwNotFoundError(Line, Column, id);

                    } else {
                        boolean isInteger = S.getType().equals("integer");
                        if (!currentType.isEmpty() && !isInteger) {

                            String Line = nodo.getAttribute("Line");
                            String Column = nodo.getAttribute("Column");
                            String currentType = S.getType().split("\\.")[0];
                            throwIncompatibleTypeError(Line, Column, currentType);

                        } else {
                            currentType = "integer";
                        }
                    }
                    break;
                }
                case "ARRAY": {
                    String id = nodo.getAttribute("Value");
                    Simbolo S = symtable.getVariable(id, currentScope);
                    String Linea = nodo.getAttribute("Line");
                    String Columna = nodo.getAttribute("Column");
                    if (S == null) {
                        throwNotFoundError(Linea, Columna, id);
                    } else if (!S.getType().startsWith("Array")) {
                        throwIlegalExpresionError(Linea, Columna);
                    } else {
                        String tipo = S.getType().split("\\.")[1];
                        String tipoBKP = currentType;
                        boolean isInteger = tipo.equals("integer");
                        if (currentType.isEmpty()) {
                            currentType = "integer";
                            typeChecking(nodo);
                        } else if (isInteger) {
                            currentType = "integer";
                            typeChecking(nodo);

                        } else {
                            throwIncompatibleTypeError(Linea, Columna, tipo);
                        }
                        currentType = tipoBKP;
                    }
                    break;
                }
                default: {
                    verifyArithmetic(nodo);
                    break;
                }
            }

        }
    }

    private static void verifyFunction(Element fatherNode) throws Exception {
        NodeList hijos = fatherNode.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element) hijos.item(i);
            String nodeName = nodo.getNodeName();
            switch (nodeName) {
                case "ID": {
                    String id = nodo.getAttribute("Value");
                    Simbolo S = symtable.getVariable(id, currentScope);
                    if (S == null) {
                        String Line = nodo.getAttribute("Line");
                        String Column = nodo.getAttribute("Column");
                        throwNotFoundError(Line, Column, id);

                    }
                    if (functionType.isEmpty()) {
                        functionType += S.getType();
                    } else {
                        functionType += "X" + S.getType();
                    }
                    break;
                }
                case "Literal": {
                    String tipo = nodo.getAttribute("Type");
                    if (functionType.isEmpty()) {
                        functionType += tipo;
                    } else {
                        functionType += "X" + tipo;
                    }
                    break;
                }
                case "Minus":
                case "Times":
                case "Div": {
                    String tipoBKP = currentType;
                    currentType = "integer";
                    typeChecking(nodo);
                    currentType = tipoBKP;
                    if (functionType.isEmpty()) {
                        functionType += "integer";
                    } else {
                        functionType += "Xinteger";
                    }
                    break;
                }
                case "Plus": {
                    String tipoBKP = currentType;
                    currentType = "";
                    typeChecking(nodo);

                    if (functionType.isEmpty()) {
                        functionType += currentType;
                    } else {
                        functionType += "X" + currentType;
                    }
                    currentType = tipoBKP;
                    break;
                }
                case "GreaterThan":
                case "LessThan":
                case "Equals":
                case "LessOrEqual":
                case "GreaterOrEqual":
                case "Different": {
                    String tipoActualBKP = currentType;
                    currentType = "";
                    typeChecking(nodo);
                    currentType = tipoActualBKP;
                    if (functionType.isEmpty()) {
                        functionType += "boolean";
                    } else {
                        functionType += "Xboolean";
                    }
                    break;
                }
                case "AND":
                case "OR":
                case "NOT": {
                    String tipoActualBKP = currentType;
                    currentType = "boolean";
                    readTree(nodo, "0", "0");
                    currentType = tipoActualBKP;
                    if (functionType.isEmpty()) {
                        functionType += "boolean";
                    } else {
                        functionType += "Xboolean";
                    }
                    break;
                }
                case "FunctionCall": {
                    String tipoBKP = functionType;
                    functionType = "";
                    Element functionId = (Element) nodo.getFirstChild();
                    String id = functionId.getAttribute("Value");
                    Simbolo S = symtable.getFunction(id);
                    String tipoRetorno = "";
                    String Linea = functionId.getAttribute("Line");
                    String Columna = functionId.getAttribute("Column");
                    if (S == null) {
                        throwNotFoundError(Linea, Columna, id);
                    }
                    tipoRetorno = S.getType().split(" -> ")[1];

                    if (nodo.getChildNodes().getLength() > 1) {
                        verifyFunction((Element) nodo.getLastChild());
                        functionType = functionType + " -> " + tipoRetorno;
                    } else {
                        functionType = "void -> " + tipoRetorno;
                    }
                    if (!currentType.equals(tipoRetorno)) {
                        throwIncompatibleTypeError(Linea, Columna, tipoRetorno);
                    }
                    if (!functionType.equals(S.getType())) {
                        throwFunctionArgsError(Linea, Columna, id);
                    }
                    functionType = tipoBKP;
                    if (functionType.isEmpty()) {
                        functionType += tipoRetorno;
                    } else {
                        functionType += "X" + tipoRetorno;
                    }
                }
                default: {
                    verifyFunction(nodo);
                }
            }
        }

    }

    private static void throwNotFoundError(String Linea, String Columna, String ID) throws Exception {
        String errorMessage = "(%s,%s) Error: ID not found '%s'";
        errorMessage = String.format(errorMessage, Linea, Columna, ID);
        if (debug) {
            throw new Exception(errorMessage);
        } else {
            System.err.println(errorMessage);
        }
        errorNumber++;
    }

    private static void throwIncompatibleTypeError(String Linea, String Columna, String tipo) throws Exception {
        String errorMessage = "";
        if(tipo.equals("void")){
            errorMessage = "(%s,%s) Error: Invalid Assignment, procedures don't have a return value";
        } else {
            errorMessage = "(%s,%s) Error: Incompatible types, expecting '%s', found '%s'";
        }
        
        errorMessage = String.format(errorMessage, Linea, Columna, currentType, tipo);
        
        if (debug) {
            throw new Exception(errorMessage);
        } else {
            System.err.println(errorMessage);
        }
        errorNumber++;
    }

    private static void throwFunctionArgsError(String Linea, String Columna, String id) throws Exception {
        String errorMessage = "(%s,%s) Error: Function not found '%s' with specified parameters";
        errorMessage = String.format(errorMessage, Linea, Columna, id);
        if (debug) {
            throw new Exception(errorMessage);
        } else {
            System.err.println(errorMessage);
        }
        errorNumber++;
    }

    private static void throwIlegalExpresionError(String Linea, String Columna) throws Exception {
        String errorMessage = "(%s,%s) Error: Illegal Expression";
        errorMessage = String.format(errorMessage, Linea, Columna);
        if (debug) {
            throw new Exception(errorMessage);
        } else {
            System.err.println(errorMessage);
        }
        errorNumber++;
    }

}
