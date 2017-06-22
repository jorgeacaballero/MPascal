
package imcode;

import java.util.ArrayList;

/**
 *
 * @author jmlb
 */
public class QuadTable {
    ArrayList<Quad> cuadruplos = new ArrayList<>();
    
    public int GEN(String op, String arg1, String arg2, String resultado){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice, op, arg1, arg2, resultado));
        return indice;
    }
    
    public int GEN(String op, String arg1, String resultado){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice, op, arg1, resultado));
        return cuadruplos.size() - 1;
    }
    
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
    
    public int GEN_JUMP_FALSE(String destination, String condition){
        int indice = cuadruplos.size();
        cuadruplos.add(new Quad(indice,"JFALSE", destination, condition, ""));
        return indice;
    }
    
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
        System.out.println("");
        System.out.println(String.format(Headers,"Quad", "Op", "Arg1", "Arg2", "Res"));
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
