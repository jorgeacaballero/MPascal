/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imcode;

import java.util.ArrayList;

/**
 *
 * @author jmlb
 */
public class QuadTable {
    ArrayList<Quad> cuadruplos = new ArrayList<>();
    
    /**
    * Used for generating full operations like Arithmetic Operations
    *
    * @param op Operation that will generate
    * @param arg1 quadruple first argument 
    * @param arg2 quadruple second argument
    * @param resultado Where the result will be stored
    * @return The index of the generated quadruple
    */
    public int GEN(String op, String arg1, String arg2, String resultado){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice, op, arg1, arg2, resultado));
        return indice;
    }
    
    /**
    * Used for generating Assignments and one argument operations.
    *
    * @param op Operation that will generate
    * @param arg1 Quadruple first argument 
    * @param resultado Where the result will be stored
    * @return The index of the generated quadruple
    */
    public int GEN(String op, String arg1, String resultado){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice, op, arg1, resultado));
        return cuadruplos.size() - 1;
    }
    
    /**
    * Generates label code.
    *
    * @param labelName Operation that will be generated
    * @return The index of the generated quadruple
    */
    public int GEN_LABEL(String labelName){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice, "LABEL", labelName, "", ""));
        return cuadruplos.size() - 1;
    }
    
    
    public int GEN_JUMP_TRUE(String destination, String condition){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice,"JTRUE", destination, condition, ""));
        return indice;
    }
    
    /**
    * Generates a jump if the condition is true.
    *
    * @param destination Quadruple index to jump
    * @param condition variable to be evaluated
    * @return The index of the generated quadruple
    */
    public int GEN_JUMP_FALSE(String destination, String condition){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice,"JFALSE", destination, condition, ""));
        return indice;
    }
    
    /**
    * Generates a jump if the condition is true.
    *
    * @param destination Quadruple index to jump
    * @return The index of the generated quadruple
    */
    public int GEN_GOTO(String destination){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice, "GOTO", destination, "", ""));
        return indice;
    }
    
    public int GEN_PARAM(String Param){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice, "PARAM", Param, "", ""));
        return indice;
    }
    
     public int GEN_CALL(String FuncName){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice, "CALL", FuncName, "", ""));
        return indice;
    }
    
    public void print(){
        String Headers = "%-10s %-10s %-10s %-10s %-10s";
        System.out.println(String.format(Headers,"Quad", "Operacion", "Arg1", "Arg2", "Resultado"));
        for (int i = 0; i < cuadruplos.size(); i++) {
            Quad C = cuadruplos.get(i);
            System.out.println(C);
        }
        System.out.println("");
    }
    
    public int getSize(){
        return this.cuadruplos.size();
    }
    
    public Quad item(int index){
        return this.cuadruplos.get(index);
    }
}
