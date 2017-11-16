package mpascal2;

import java.io.BufferedReader;
import java.io.FileReader;
import java_cup.runtime.Symbol;

/**
 *
 * @author jose.lopez
 */
public class Main {
       public static void main(String[] args) {
        SemAnal.ts.clear();
        SemAnal.offset=0;
        //System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        PascalFlexer scanner;
        Parser parser = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/Bueno2.pas"));
            scanner = new PascalFlexer(br);
            parser = new Parser(scanner);
            Symbol Sym = parser.parse();
            if(Sym.sym == 0)
                System.out.println("Se Compilo correctamente el archivo!");
            else 
                System.out.println("Se encontraron erroes en el archivo!");
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Se encontraron errores en el archivo!"); 
        }

    }
    public TablaSimbolos ts = new TablaSimbolos();
}
