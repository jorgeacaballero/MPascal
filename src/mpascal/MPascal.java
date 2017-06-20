/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpascal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmlb
 */
public class MPascal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LexerGenerator.main(args);
        CupGenerator.main(args);
        Reader reader;
        try {
            reader = new BufferedReader(new FileReader("./test/fun_procs.pas"));
            Flexer lexer = new Flexer(reader);
            
            Parser cupParser = new Parser(lexer);
            cupParser.parse();
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(MPascal.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("FNF");
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(MPascal.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("IO");
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(MPascal.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Otra Cosa");
        }
    }
    
}
