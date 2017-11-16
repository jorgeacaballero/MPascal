package imcode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author jose.lopez
 */
public class TablaCuadruplos {    
    ArrayList<Cuadruplo> cuadruplos = new ArrayList<>();
    
    public int GEN(String op, String arg1, String arg2, String resultado){
        int indice = cuadruplos.size();
        cuadruplos.add(new Cuadruplo(indice, op, arg1, arg2, resultado));
        return indice;
    }
    
    public int GEN(String op, String arg1, String resultado){
        int indice = cuadruplos.size();
        cuadruplos.add(new Cuadruplo(indice, op, arg1, resultado));
        return cuadruplos.size() - 1;
    }
    
    public int GEN_LABEL(String labelName){
        int indice = cuadruplos.size();
        cuadruplos.add(new Cuadruplo(indice, "LABEL", labelName, "", ""));
        return cuadruplos.size() - 1;
    }
    
    
    public int GEN_JUMP_TRUE(String destination, String condition){
        int indice = cuadruplos.size();
        cuadruplos.add(new Cuadruplo(indice,"JTRUE", destination, condition, ""));
        return indice;
    }
    
    public int GEN_JUMP_FALSE(String destination, String condition){
        int indice = cuadruplos.size();
        cuadruplos.add(new Cuadruplo(indice,"JFALSE", destination, condition, ""));
        return indice;
    }

    public int GEN_GOTO(String destination){
        int indice = cuadruplos.size();
        cuadruplos.add(new Cuadruplo(indice, "GOTO", destination, "", ""));
        return indice;
    }
    
    public int GEN_PARAM(String Param){
        int indice = cuadruplos.size();
        cuadruplos.add(new Cuadruplo(indice, "PARAM", Param, "", ""));
        return indice;
    }
    
     public int GEN_CALL(String FuncName){
        int indice = cuadruplos.size();
        cuadruplos.add(new Cuadruplo(indice, "CALL", FuncName, "", ""));
        return indice;
    }
    
    public void print(){
        String Headers = "%-10s %-10s %-10s %-10s %-10s";
//        System.out.println(String.format(Headers,"Cuadruplo", "Operacion", "Arg1", "Arg2", "Resultado"));
//        for (int i = 0; i < cuadruplos.size(); i++) {
//            Cuadruplo C = cuadruplos.get(i);
//            System.out.println(C);
//        }
//        System.out.println("");
        System.out.println("");
        try (FileWriter fw = new FileWriter("./Quads.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println("");
            out.println(String.format(Headers, "Quad", "Op", "Arg1", "Arg2", "Res"));
            for (int i = 0; i < cuadruplos.size(); i++) {
                Cuadruplo Q = cuadruplos.get(i);
                out.println(Q);
            }
            
        } catch (IOException e) {
            
        }
    }
    
    public int getSize(){
        return this.cuadruplos.size();
    }
    
    public Cuadruplo item(int index){
        return this.cuadruplos.get(index);
    }
}
