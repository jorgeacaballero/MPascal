/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpascal;

/**
 *

 */
public class CupGenerator {
    public static void main(String[] args) {
        String params[] = new String[5];
        
        params[0] = "-destdir";
        params[1] = "src/mpascal/";
        params[2] = "-parser";
        params[3] = "Parser";
        params[4] = "src/mpascal/Gram2.cup";
        try {
            java_cup.Main.main(params);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
