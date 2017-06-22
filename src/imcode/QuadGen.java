/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imcode;

import java.util.ArrayList;
import mpascal.Simbolo;
import mpascal.SymbolTable;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 *
 * @author jmlb
 */
public class QuadGen {
    QuadTable Quads = new QuadTable();
    SymbolTable TS = new SymbolTable();
    String currOp = "";
    String ambitoActual = "main";
    int tempCounter = 0;
    boolean debug = false;

    public QuadGen(SymbolTable TS) {
        this.TS = TS;
    }
    
    public QuadTable getQuadTable(){
        return this.Quads;
    }

    public void parseTree(Element rootNode) throws Exception {
        NodeList hijos = rootNode.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Element nodo = (Element) hijos.item(i);
            String nodeName = nodo.getNodeName();
            if (debug) {
                System.out.println("RecorrerArbol - " + nodeName);
            }
            switch (nodeName) {

                case "ProcedureDeclaration": {
                    String functionName = nodo.getAttribute("ID");
                    Quads.GEN_LABEL(functionName);
                    this.parseTree(nodo);
                    Quads.GEN("RET", "", "", "");
                    break;
                }
                case "FunctionDeclaration": {
                    String functionName = nodo.getAttribute("ID");
                    Quads.GEN_LABEL(functionName);
                    this.parseTree(nodo);
                    break;
                }
                case "Body": {
                    if (nodo.getParentNode().getNodeName().equals("Block")) {
                        Quads.GEN_LABEL("main");
                    }
                    parseTree(nodo);
                    break;
                }
                case "FunctionCall": {
                    functionCallQuad(nodo);
                    break;
                }
                case "IfStatement": {
                    ifQuad(nodo);
                    break;
                }
                case "WhileStatement": {
                    whileQuad(nodo);
                    break;
                }
                case "RepeatStatement": {
                    repeatQuad(nodo);
                    break;
                }
                case "ForStatement": {
                    forQuad(nodo);
                    break;
                }
                case "ReadStatement": {
                    Element arg = (Element) nodo.getFirstChild();
                    String argName = arg.getNodeName();
                    switch (argName) {
                        case "ID": {
                            String argValue = arg.getAttribute("Value");
                            Quads.GEN("READ", "", "", argValue);
                            break;
                        }
                        case "ARRAY": {
                            arrQuad(arg);
                            String temp = this.getTemp();
                            String argValue = arg.getAttribute("Value");
                            Quads.GEN("READ[]", temp, "", argValue);
                            break;
                        }
                    }

                    break;
                }
                case "WriteStatement": {
                    NodeList lista = nodo.getChildNodes();
                    Element arg1 = (Element) lista.item(0);
                    String arg1Value = arg1.getAttribute("Value");
                    Element arg2 = null;
                    String arg2Name = "";
                    if (lista.getLength() > 1) {
                        arg2 = (Element) lista.item(1);
                        arg2Name = arg2.getNodeName();
                        String argValue = "";
                        switch (arg2Name) {
                            case "ID": {
                                argValue = arg2.getAttribute("Value");
                                break;
                            }
                            case "ARRAY": {
                                arrQuad(arg2);
                                String temp = this.getTemp();
                                argValue = temp;
                                break;
                            }
                        }
                        Quads.GEN("WRITE", arg1Value, "", "");
                        Quads.GEN("WRITE", argValue, "", "");
                    } else {
                        Quads.GEN("WRITE", arg1Value, "", "");
                    }

                    break;
                }
                case "Assignment": {
                    assignQuad(nodo);
                    break;
                }
                case "AND":
                case "OR":
                case "NOT": {
                    relQuad(nodo);
                    break;
                }
                case "GreaterThan":
                case "LessThan":
                case "Equals":
                case "LessOrEqual":
                case "GreaterOrEqual":
                case "Different": {
                    this.exprQuad(nodo);
                    Element parent = (Element) nodo.getParentNode();
                    String listaV = nodo.getAttribute("listaV");
                    String listaF = nodo.getAttribute("listaF");
                    parent.setAttribute("listaV", listaV);
                    parent.setAttribute("listaF", listaF);
                    break;
                }
                case "Div":
                case "Minus":
                case "Times":
                case "Plus": {
                    arithQuad(nodo);
                    break;
                }
                default: {
                    parseTree(nodo);
                    break;
                }
            }
        }

    }

    public void arithQuad(Element nodo) throws Exception {
        String nodeName = nodo.getNodeName();
        if (debug) {
            System.out.println("cuadruploArit: " + nodeName);
        }
        switch (nodeName) {
            case "Div":
            case "Minus":
            case "Times":
            case "Plus": {
                Element arg1 = (Element) nodo.getFirstChild();
                Element arg2 = (Element) nodo.getLastChild();
                String arg1Name = arg1.getNodeName();
                String arg2Name = arg2.getNodeName();
                boolean arg1IsArray = arg1Name.equals("ARRAY");
                boolean arg2IsArray = arg2Name.equals("ARRAY");
                boolean arg1IsFinal = arg1Name.equals("Literal") || arg1Name.equals("ID") || arg1IsArray;
                boolean arg2IsFinal = arg2Name.equals("Literal") || arg2Name.equals("ID") || arg2IsArray;

                if (arg1IsFinal && arg2IsFinal) {
                    if (arg1IsArray && arg2IsArray) {
                        arithQuad(arg1);
                        String tempArg1 = this.getTemp();
                        arithQuad(arg2);
                        String tempArg2 = this.getTemp();

                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Quads.GEN(operacion, tempArg1, tempArg2, temp);
                    } else if (arg1IsArray) {
                        arithQuad(arg1);
                        String tempArg = this.getTemp();
                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Quads.GEN(operacion, tempArg, arg2.getAttribute("Value"), temp);
                    } else if (arg2IsArray) {
                        arithQuad(arg2);
                        String tempArg = this.getTemp();
                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Quads.GEN(operacion, arg1.getAttribute("Value"), tempArg, temp);
                    } else {
                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Quads.GEN(":=", arg1.getAttribute("Value"), temp);
                        String firstTemp = this.getTemp();
                        temp = newTemp();
                        Quads.GEN(":=", arg2.getAttribute("Value"), temp);
                        String secondTemp = this.getTemp();
                        temp = newTemp();
                        Quads.GEN(operacion, firstTemp, secondTemp, temp);
                    }
                } else if (arg1IsFinal) {
                    if (arg1IsArray) {
                        arithQuad(arg1);
                        String tempArg1 = this.getTemp();
                        arithQuad(arg2);
                        String tempArg2 = this.getTemp();

                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Quads.GEN(operacion, tempArg1, tempArg2, temp);
                    } else {
                        arithQuad(arg2);
                        String lastTemp = this.getTemp();
                        String newTemp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Quads.GEN(":=", arg1.getAttribute("Value"), newTemp);
                        String temp = this.getTemp();
                        newTemp = this.newTemp();
                        Quads.GEN(operacion, temp, lastTemp, newTemp);
                    }
                } else if (arg2IsFinal) {
                    if (arg2IsArray) {
                        arithQuad(arg1);
                        String tempArg1 = this.getTemp();
                        arithQuad(arg2);
                        String tempArg2 = this.getTemp();

                        String temp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Quads.GEN(operacion, tempArg1, tempArg2, temp);
                    } else {
                        arithQuad(arg1);
                        String lastTemp = this.getTemp();
                        String newTemp = this.newTemp();
                        String operacion = nodo.getAttribute("Value");
                        if (debug) {
                            System.out.println("Operacion: " + operacion);
                        }
                        Quads.GEN(":=", arg2.getAttribute("Value"), newTemp);
                        String temp = this.getTemp();
                        newTemp = this.newTemp();
                        Quads.GEN(operacion, lastTemp , temp, newTemp);
                    }
                } else { // Both are OPs
                    arithQuad(arg1);
                    String tempArg1 = this.getTemp();
                    arithQuad(arg2);
                    String tempArg2 = this.getTemp();

                    String temp = this.newTemp();
                    String operacion = nodo.getAttribute("Value");
                    if (debug) {
                        System.out.println("Operacion: " + operacion);
                    }
                    Quads.GEN(operacion, tempArg1, tempArg2, temp);
                }
                break;
            }
            case "ARRAY": {
                this.arrQuad(nodo);
                break;
            }
            default: {
                break;
            }
        }
    }

    public void assignQuad(Element nodo) throws Exception {
        Element arg1 = (Element) nodo.getFirstChild();
        Element arg2 = (Element) nodo.getLastChild();

        String temp1 = "";
        String temp2 = "";

        switch (arg2.getNodeName()) {
            case "ID":
            case "Literal": {
                String t = arg2.getAttribute("Value");
                temp2 = this.newTemp();
                this.Quads.GEN(":=", t, temp2);
                break;
            }
            case "FunctionCall": {
                this.functionCallQuad(arg2);
                temp2 = "RET";
                break;
            }
            case "AND":
            case "OR":
            case "NOT":
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different": {
                relQuad(arg2);
                String newTemp = this.newTemp();
                int M1 = Quads.getSize();
                Quads.GEN(":=", "1", "", newTemp);
                Quads.GEN_GOTO(Quads.getSize() + 2 + "");
                int M2 = Quads.getSize();
                Quads.GEN(":=", "0", "", newTemp);
                this.completa(M1, arg2.getAttribute("listaV"));
                this.completa(M2, arg2.getAttribute("listaF"));
                temp2 = this.getTemp();
                break;
            }
            default: {
                arithQuad(arg2);
                temp2 = this.getTemp();
                break;
            }
        }

        if (arg1.getNodeName().equals("ID")) {
            String isReturn = nodo.getAttribute("Return");
            if (isReturn.isEmpty()) {
                temp1 = arg1.getAttribute("Value");
                Quads.GEN(":=", temp2, temp1);
            } else {
                Quads.GEN("FRET", temp2, "", "");
            }

        } else if (arg1.getNodeName().equals("ARRAY")) {
            Element arg = (Element) arg1.getFirstChild();
            String argName = arg.getNodeName();
            String Valex = arg1.getAttribute("Value");
            Simbolo S = TS.getVariable(Valex, ambitoActual);
            String tipo = S.getType();
            String indiceInicial = tipo.split("\\.")[2];
            if (argName.equals("ID") || argName.equals("Literal")) {
                String Valex2 = arg.getAttribute("Value");
                String newTemp = this.newTemp();
                Quads.GEN("-", Valex2, indiceInicial, newTemp);
                String temp = this.getTemp();
                newTemp = this.newTemp();
                Quads.GEN("*", temp, this.getTypeSize(tipo), newTemp);
                temp = this.getTemp();
                Quads.GEN("[]=", temp, temp2, Valex);
            } else {
                arithQuad(arg);
                String temp = this.getTemp();
                String newTemp = this.newTemp();
                Quads.GEN("-", temp, indiceInicial, newTemp);
                temp = this.getTemp();
                newTemp = this.newTemp();
                Quads.GEN("*", temp, this.getTypeSize(tipo), newTemp);
                temp = this.getTemp();
                Quads.GEN("[]=", temp, temp2, Valex);
            }
        }
    }

    public void exprQuad(Element nodo) throws Exception {
        if (debug) {
            System.out.println("cuadruplosExpresion: " + nodo.getNodeName());
        }
        Element arg1 = (Element) nodo.getFirstChild();
        Element arg2 = (Element) nodo.getLastChild();
        Element parent = (Element) nodo.getParentNode();

        String arg1Name = arg1.getNodeName();
        String arg2Name = arg2.getNodeName();

        String t1 = "";
        String t2 = "";
        String tResultado = "";
        String op = nodo.getAttribute("Value");

        if (arg1Name.equals("ID") || arg1Name.equals("Literal")) {
            t1 = arg1.getAttribute("Value");
        } else if (arg1Name.equals("ARRAY")) {
            arrQuad(arg1);
            t1 = this.getTemp();
        } else {
            arithQuad(arg1);
            t1 = this.getTemp();
        }

        if (arg2Name.equals("ID") || arg2Name.equals("Literal")) {
            t2 = arg2.getAttribute("Value");
        } else if (arg2Name.equals("ARRAY")) {
            arrQuad(arg2);
            t2 = this.getTemp();
        } else {
            arithQuad(arg2);
            t2 = this.getTemp();
        }

        nodo.setAttribute("listaV", this.crearLista(Quads.getSize()));
        nodo.setAttribute("listaF", this.crearLista(Quads.getSize() + 1));

        Quads.GEN("if" + op, t1, t2, "@");
        Quads.GEN_GOTO("@");
    }

    public void arrQuad(Element nodo) throws Exception {
        Element arg = (Element) nodo.getFirstChild();
        String argName = arg.getNodeName();
        String operacion = "=[]";
        String IDArray = nodo.getAttribute("Value");
        Simbolo S = TS.getVariable(IDArray, ambitoActual);
        String tipo = S.getType();
        String indiceInicial = tipo.split("\\.")[2];
        System.out.println("------" + IDArray);
        if (argName.equals("ID") || argName.equals("Literal")) {
            String newTemp = this.newTemp();
            Quads.GEN("-", arg.getAttribute("Value"), indiceInicial, newTemp);
            String temp = this.getTemp();
            newTemp = this.newTemp();
            Quads.GEN("*", temp, getTypeSize(tipo.split("\\.")[1]), newTemp);
            temp = this.getTemp();
            newTemp = this.newTemp();
            Quads.GEN(operacion, IDArray, temp, newTemp);
        } else {
            arithQuad(arg);
            String temp = this.getTemp();
            String newTemp = this.newTemp();
            Quads.GEN("-", temp, indiceInicial, newTemp);
            temp = this.getTemp();
            newTemp = this.newTemp();
            Quads.GEN("*", temp, getTypeSize(tipo.split("\\.")[1]), newTemp);
            temp = this.getTemp();
            newTemp = this.newTemp();
            Quads.GEN(operacion, IDArray, temp, newTemp);
        }
    }

    private void andQuad(Element nodo) throws Exception {
        if (debug) {
            System.out.println("cuadruploAND: " + nodo.getNodeName());
        }
        Element arg1 = (Element) nodo.getFirstChild();
        Element arg2 = (Element) nodo.getLastChild();

        String arg1Name = arg1.getNodeName();
        String arg2Name = arg2.getNodeName();

        switch (arg1Name) {
            case "ID": {
                String arg1Value = arg1.getAttribute("Value");
                arg1.setAttribute("listaV", crearLista(Quads.getSize()));
                arg1.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg1Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "Literal": {
                String arg1Value = arg1.getAttribute("Value");
                arg1.setAttribute("listaV", crearLista(Quads.getSize()));
                arg1.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg1Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "ARRAY": {
                arrQuad(arg1);
                String temp = this.getTemp();
                arg1.setAttribute("listaV", crearLista(Quads.getSize()));
                arg1.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", temp, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different": {
                exprQuad(arg1);
                break;
            }
            default: {
                relQuad(arg1);
                break;
            }

        }

        int M1 = Quads.getSize();

        switch (arg2Name) {
            case "ID": {
                String arg2Value = arg2.getAttribute("Value");
                arg2.setAttribute("listaV", crearLista(Quads.getSize()));
                arg2.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg2Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "Literal": {
                String arg2Value = arg2.getAttribute("Value");
                arg2.setAttribute("listaV", crearLista(Quads.getSize()));
                arg2.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg2Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "ARRAY": {
                arrQuad(arg2);
                String temp = this.getTemp();
                arg2.setAttribute("listaV", crearLista(Quads.getSize()));
                arg2.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", temp, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different": {
                exprQuad(arg2);
                break;
            }
            default: {
                relQuad(arg2);
                break;
            }

        }

        this.completa(M1, arg1.getAttribute("listaV"));
        String listaFusionada = this.fusiona(arg1.getAttribute("listaF"), arg2.getAttribute("listaF"));
        nodo.setAttribute("listaF", listaFusionada);
        nodo.setAttribute("listaV", arg2.getAttribute("listaV"));
    }

    private void orQuad(Element nodo) throws Exception {
        if (debug) {
            System.out.println("cuadruploOR: " + nodo.getNodeName());
        }
        Element arg1 = (Element) nodo.getFirstChild();
        Element arg2 = (Element) nodo.getLastChild();

        String arg1Name = arg1.getNodeName();
        String arg2Name = arg2.getNodeName();

        switch (arg1Name) {
            case "ID": {
                String arg1Value = arg1.getAttribute("Value");
                arg1.setAttribute("listaV", crearLista(Quads.getSize()));
                arg1.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg1Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "Literal": {
                String arg1Value = arg1.getAttribute("Value");
                arg1.setAttribute("listaV", crearLista(Quads.getSize()));
                arg1.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg1Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "ARRAY": {
                arrQuad(arg1);
                String temp = this.getTemp();
                arg1.setAttribute("listaV", crearLista(Quads.getSize()));
                arg1.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", temp, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different": {
                exprQuad(arg1);
                break;
            }
            default: {
                relQuad(arg1);
                break;
            }

        }
        int M1 = Quads.getSize();

        switch (arg2Name) {
            case "ID": {
                String arg2Value = arg2.getAttribute("Value");
                arg2.setAttribute("listaV", crearLista(Quads.getSize()));
                arg2.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg2Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "Literal": {
                String arg2Value = arg2.getAttribute("Value");
                arg2.setAttribute("listaV", crearLista(Quads.getSize()));
                arg2.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg2Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "ARRAY": {
                arrQuad(arg2);
                String temp = this.getTemp();
                arg2.setAttribute("listaV", crearLista(Quads.getSize()));
                arg2.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", temp, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different": {
                exprQuad(arg2);
                break;
            }
            default: {
                relQuad(arg2);
                break;
            }
        }

        this.completa(M1, arg1.getAttribute("listaF"));
        String listaFusionada = this.fusiona(arg1.getAttribute("listaV"), arg2.getAttribute("listaV"));
        nodo.setAttribute("listaV", listaFusionada);
        nodo.setAttribute("listaF", arg2.getAttribute("listaF"));
    }

    private void notQuad(Element nodo) throws Exception {
        if (debug) {
            System.out.println("cuadruploNOT: " + nodo.getNodeName());
        }
        Element arg1Node = (Element) nodo.getFirstChild();
        String arg1Name = arg1Node.getNodeName();

        switch (arg1Name) {
            case "ID": {
                String arg1Value = arg1Node.getAttribute("Value");
                arg1Node.setAttribute("listaV", crearLista(Quads.getSize()));
                arg1Node.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg1Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "Literal": {
                String arg1Value = arg1Node.getAttribute("Value");
                arg1Node.setAttribute("listaV", crearLista(Quads.getSize()));
                arg1Node.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg1Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "ARRAY": {
                arrQuad(arg1Node);
                String temp = this.getTemp();
                arg1Node.setAttribute("listaV", crearLista(Quads.getSize()));
                arg1Node.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", temp, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different": {
                exprQuad(arg1Node);
                break;
            }
            default: {
                relQuad(arg1Node);
                break;
            }

        }
        nodo.setAttribute("listaV", arg1Node.getAttribute("listaF"));
        nodo.setAttribute("listaF", arg1Node.getAttribute("listaV"));
    }

    //Relational
    private void relQuad(Element node) throws Exception {
        String nodeName = node.getNodeName();
        if (debug) {
            System.out.println("cuadruploRelacional: " + node.getNodeName());
        }
        switch (nodeName) {
            case "AND": {
                andQuad(node);
                break;
            }
            case "OR": {
                orQuad(node);
                break;
            }
            case "NOT": {
                notQuad(node);
                break;
            }
            case "GreaterThan":
            case "LessThan":
            case "Equals":
            case "LessOrEqual":
            case "GreaterOrEqual":
            case "Different": {
                exprQuad(node);
                break;
            }

        }
    }

    private void ifQuad(Element nodo) throws Exception {
        if (debug) {
            System.out.println("cuadruploIf: " + nodo.getNodeName());
        }
        NodeList lista = nodo.getChildNodes();
        Element expression = (Element) lista.item(0);
        Element body = (Element) lista.item(1);
        Element elseIf = null;
        String elseIfName = "";
        if (lista.getLength() > 2) {
            elseIf = (Element) lista.item(2);
            elseIfName = elseIf.getNodeName();
        }
        this.exprQuad(expression);

        int M1 = Quads.getSize();

        parseTree(body);

        int N1 = Quads.GEN_GOTO("@");
        nodo.setAttribute("listaF", crearLista(N1));
        int M2 = Quads.getSize();

        this.completa(M1, expression.getAttribute("listaV"));
        this.completa(M2, expression.getAttribute("listaF"));

        switch (elseIfName) {
            case "IfStatement": {
                ifQuad(elseIf);
                String listaF = elseIf.getAttribute("listaF");
                nodo.setAttribute("listaF", fusiona(listaF, nodo.getAttribute("listaF")));
                break;
            }
            case "Body": {
                parseTree(elseIf);
                int M3 = Quads.GEN_GOTO("@");
                String listaF = nodo.getAttribute("listaF");
                nodo.setAttribute("listaF", fusiona(listaF, this.crearLista(M3)));
                break;
            }
        }

        int endOfIf = Quads.getSize();
        this.completa(endOfIf, nodo.getAttribute("listaF"));
    }

    private void whileQuad(Element nodo) throws Exception {
        if (debug) {
            System.out.println("cuadruploWhile: " + nodo.getNodeName());
        }
        Element expression = (Element) nodo.getFirstChild();
        Element body = (Element) nodo.getLastChild();

        String nodeName = expression.getNodeName();

        int M1 = Quads.getSize();
        switch (nodeName) {
            case "ID": {
                String arg1Value = expression.getAttribute("Value");
                expression.setAttribute("listaV", crearLista(Quads.getSize()));
                expression.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg1Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "Literal": {
                String arg1Value = expression.getAttribute("Value");
                expression.setAttribute("listaV", crearLista(Quads.getSize()));
                expression.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg1Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "ARRAY": {
                arrQuad(expression);
                String temp = this.getTemp();
                expression.setAttribute("listaV", crearLista(Quads.getSize()));
                expression.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", temp, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            default: {
                this.relQuad(expression);
                break;
            }
        }

        int M2 = Quads.getSize();
        parseTree(body);

        this.completa(M2, expression.getAttribute("listaV"));
        Quads.GEN_GOTO(M1 + "");
        int M3 = Quads.getSize();
        this.completa(M3, expression.getAttribute("listaF"));

    }

    private void repeatQuad(Element nodo) throws Exception {
        if (debug) {
            System.out.println("cuadruploRepeat: " + nodo.getNodeName());
        }
        Element body = (Element) nodo.getFirstChild();
        Element expression = (Element) nodo.getLastChild();
        String nodeName = expression.getNodeName();

        int M1 = Quads.getSize();
        parseTree(body);

        switch (nodeName) {
            case "ID": {
                String arg1Value = expression.getAttribute("Value");
                expression.setAttribute("listaV", crearLista(Quads.getSize()));
                expression.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg1Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "Literal": {
                String arg1Value = expression.getAttribute("Value");
                expression.setAttribute("listaV", crearLista(Quads.getSize()));
                expression.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", arg1Value, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "ARRAY": {
                arrQuad(expression);
                String temp = this.getTemp();
                expression.setAttribute("listaV", crearLista(Quads.getSize()));
                expression.setAttribute("listaF", crearLista(Quads.getSize() + 1));
                Quads.GEN("if=", temp, "1", "@");
                Quads.GEN_GOTO("@");
                break;
            }
            default: {
                this.relQuad(expression);
                break;
            }
        }

        this.completa(M1, expression.getAttribute("listaF"));
        int M2 = Quads.getSize();
        this.completa(M2, expression.getAttribute("listaV"));
    }

    private void forQuad(Element nodo) throws Exception {
        NodeList lista = nodo.getChildNodes();
        Element assignment = (Element) lista.item(0);
        Element end = (Element) lista.item(1);
        Element body = (Element) lista.item(2);

        this.assignQuad(assignment);
        Quad QUAD = Quads.item(Quads.getSize() - 1);
        String iterator = QUAD.res;

        int M1 = Quads.getSize();

        String endName = end.getNodeName();
        switch (endName) {
            case "Literal":
            case "ID": {
                String listaV = this.crearLista(Quads.getSize());
                String listaF = this.crearLista(Quads.getSize() + 1);
                nodo.setAttribute("listaV", listaV);
                nodo.setAttribute("listaF", listaF);
                String idValex = end.getAttribute("Value");
                Quads.GEN("if<=", iterator, idValex, "@");
                Quads.GEN_GOTO("@");
                break;
            }
            case "ARRAY": {
                this.arrQuad(end);
                String listaV = this.crearLista(Quads.getSize());
                String listaF = this.crearLista(Quads.getSize() + 1);
                nodo.setAttribute("listaV", listaV);
                nodo.setAttribute("listaF", listaF);
                String temp = this.getTemp();
                Quads.GEN("if<=", iterator, temp, "@");
                Quads.GEN_GOTO("@");
                break;
            }
            default: {
                this.arithQuad(end);
                String listaV = this.crearLista(Quads.getSize());
                String listaF = this.crearLista(Quads.getSize() + 1);
                nodo.setAttribute("listaV", listaV);
                nodo.setAttribute("listaF", listaF);
                String temp = this.getTemp();
                Quads.GEN("if<=", iterator, temp, "@");
                Quads.GEN_GOTO("@");
                break;
            }
        }
        int M2 = Quads.getSize();
        parseTree(body);
        Quads.GEN("+", iterator, "1", iterator);
        Quads.GEN_GOTO(M1 + "");
        int M3 = Quads.getSize();

        this.completa(M2, nodo.getAttribute("listaV"));
        this.completa(M3, nodo.getAttribute("listaF"));

    }

    private void functionCallQuad(Element functionNode) throws Exception {
        Element funcId = (Element) functionNode.getFirstChild();
        Element funcArgsNode = (Element) functionNode.getLastChild();
        if (funcArgsNode.getNodeName().equals("Arguments")) {
            NodeList funcArgs = funcArgsNode.getChildNodes();
            ArrayList<String> parameters = new ArrayList();
            for (int i = 0; i < funcArgs.getLength(); i++) {
                Element currentNode = (Element) funcArgs.item(i);
                String argumentName = currentNode.getNodeName();
                switch (argumentName) {
                    case "ID":
                    case "Literal": {
                        String Valex = currentNode.getAttribute("Value");
                        parameters.add(Valex);
                        break;
                    }
                    case "ARRAY": {
                        this.arrQuad(currentNode);
                        String temp = this.getTemp();
                        parameters.add(temp);
                        break;
                    }
                    case "AND":
                    case "OR":
                    case "NOT":
                    case "GreaterThan":
                    case "LessThan":
                    case "Equals":
                    case "LessOrEqual":
                    case "GreaterOrEqual":
                    case "Different": {
                        relQuad(currentNode);
                        String newTemp = this.newTemp();
                        int M1 = Quads.getSize();
                        Quads.GEN(":=", "1", "", newTemp);
                        Quads.GEN_GOTO(Quads.getSize() + 2 + "");
                        int M2 = Quads.getSize();
                        Quads.GEN(":=", "0", "", newTemp);
                        this.completa(M1, currentNode.getAttribute("listaV"));
                        this.completa(M2, currentNode.getAttribute("listaF"));
                        parameters.add(newTemp);
                        break;
                    }

                    case "Div":
                    case "Minus":
                    case "Times":
                    case "Plus": {
                        arithQuad(currentNode);
                        String temp = this.getTemp();
                        parameters.add(temp);
                        break;
                    }
                }
            }

            for (String Param : parameters) {
                Quads.GEN_PARAM(Param);
            }

            String functionName = funcId.getAttribute("Value");
            Quads.GEN_CALL(functionName);
        }

    }

    public String crearLista(int quadIndex) {
        return "" + quadIndex;
    }

    public String fusiona(String list1, String list2) {
        return list1 + "," + list2;
    }

    public void completa(int quadIndex, String lista) {
        String[] splitList = lista.split(",");
        for (String indexS : splitList) {
            int index = Integer.valueOf(indexS);
            Quad quad = Quads.quads.get(index);
            if (quad.op.equals("GOTO")) {
                quad.arg1 = quadIndex + "";
            } else {
                quad.res = quadIndex + "";
            }
        }
    }

    public void print() {
        Quads.print();
    }

    private String getTypeSize(String tipo) {
        if (tipo.equals("char") || tipo.equals("boolean")) {
            return "1";
        } else {
            return "4";
        }
    }

    private String getTemp() {
        return "$t" + this.tempCounter;
    }

    private String newTemp() {
        return "$t" + ++this.tempCounter;
    }
}
