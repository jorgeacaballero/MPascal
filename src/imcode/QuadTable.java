package imcode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author jmlb
 */
public class QuadTable {

    ArrayList<Quad> quads = new ArrayList<>();

    public int GEN(String op, String arg1, String arg2, String resultado) {
        int indice = quads.size();
        quads.add(new Quad(indice, op, arg1, arg2, resultado));
        return indice;
    }

    public int GEN(String op, String arg1, String resultado) {
        int indice = quads.size();
        quads.add(new Quad(indice, op, arg1, resultado));
        return quads.size() - 1;
    }

    public int GEN_LABEL(String labelName) {
        int indice = quads.size();
        quads.add(new Quad(indice, "LABEL", labelName, "", ""));
        return quads.size() - 1;
    }

    public int GEN_JUMP_TRUE(String destination, String condition) {
        int indice = quads.size();
        quads.add(new Quad(indice, "JTRUE", destination, condition, ""));
        return indice;
    }

    public int GEN_JUMP_FALSE(String destination, String condition) {
        int indice = quads.size();
        quads.add(new Quad(indice, "JFALSE", destination, condition, ""));
        return indice;
    }

    public int GEN_GOTO(String destination) {
        int indice = quads.size();
        quads.add(new Quad(indice, "GOTO", destination, "", ""));
        return indice;
    }

    public int GEN_PARAM(String Param) {
        int indice = quads.size();
        quads.add(new Quad(indice, "PARAM", Param, "", ""));
        return indice;
    }

    public int GEN_CALL(String FuncName) {
        int indice = quads.size();
        quads.add(new Quad(indice, "CALL", FuncName, "", ""));
        return indice;
    }

    public void print() {
        String Headers = "%-10s %-10s %-10s %-10s %-10s";
//        System.out.println("");
//        System.out.println(String.format(Headers, "Quad", "Op", "Arg1", "Arg2", "Res"));
//        for (int i = 0; i < quads.size(); i++) {
//            Quad Q = quads.get(i);
//            System.out.println(Q);
//        }
        System.out.println("");
        try (FileWriter fw = new FileWriter("./Quads.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println("");
            out.println(String.format(Headers, "Quad", "Op", "Arg1", "Arg2", "Res"));
            for (int i = 0; i < quads.size(); i++) {
                Quad Q = quads.get(i);
                out.println(Q);
            }
            
        } catch (IOException e) {
            
        }
    }

    public int getSize() {
        return this.quads.size();
    }

    public Quad item(int index) {
        return this.quads.get(index);
    }
}
