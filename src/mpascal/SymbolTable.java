/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpascal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author jmlb
 */
public class SymbolTable {
    public ArrayList<Simbolo> Simbolos = new ArrayList();
    String formatHeader = "%-20s %-20s %-60s %-15s %-15s %-15s %-15s %-15s %-18s";
    String formatBody = "%-20s %-20s %-60s %-15s %-15s %-15s %-15s %-15s %-18s";

    public int Add(Simbolo S) throws Exception {
        int index = this.getSymbolIndex(S);
        if(index > 0) {
            throw new Exception("Element " + S.getId() + " already exists in " + S.getAmbito());
        } else {
            Simbolos.add(S);
        }
        return Simbolos.size() -1;
    }
    
    public Simbolo getSimbolo(int index) throws Exception {
        if(index >= 0 && index < Simbolos.size()){
            return Simbolos.get(index);
        } else {
            throw new Exception("Symbol not found");
        }
    }
    
     public Simbolo getVariable(String Id) throws Exception {
        for(Simbolo S : Simbolos){
            if(S.getId().equals(Id) && S.isVariable()){
                return S;
            }
        }  
        return null;
    }
     
     public Simbolo getFunction(String Id) throws Exception {
        for(Simbolo S : Simbolos){
            if(S.getId().equals(Id) && S.isFunction()){
                return S;
            }
        }  
        return null;
    }
     
    public Simbolo getVariable(String Id, String ambito) throws Exception {
        for(Simbolo S : Simbolos){
            if(S.getId().equals(Id) && (S.isVariable() || S.isParameter()) && S.getAmbito().equals(ambito)){
                return S;
            }
        }  
        return null;
    }
    
    public int getSymbolIndex(Simbolo S){
        for (int i = 0; i < Simbolos.size(); i++) {
            Simbolo St = Simbolos.get(i);
            boolean hasSameName = S.getId().equals(St.getId());
            boolean hasSameScope = S.getAmbito().equals(St.getAmbito());
            if( hasSameName && hasSameScope){
                return i;
            }
        }
        return -1;
    } 
    
    public void replaceNode(Simbolo S, int index) {
        Simbolos.set(index, S);
    }
    

    public void clear(){
        Simbolos.clear();
    }
    
    @Override
    public String toString() {
        String headers = String.format(
                formatHeader,
                "Id",
                "Value",
                "DataType",
                "Scope",
                "Variable",
                "Function",
                "Parameter",
                "Reference",
                "Simulated Memory Position"
        );
//        System.out.println(headers);
//        for (Simbolo S: Simbolos) {
//            String output = String.format(
//                    formatBody,
//                    S.getId(),
//                    S.getValue(),
//                    S.getType(),
//                    S.getAmbito(),
//                    String.valueOf(S.isVariable()),
//                    String.valueOf(S.isFunction()),
//                    String.valueOf(S.isParameter()),
//                    String.valueOf(S.isByRef()),
//                    String.valueOf(S.getMemoryPos())
//            );
//            System.out.println(output);
//        }
        
        try (FileWriter fw = new FileWriter("./SymTable.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(headers);
            for (Simbolo S: Simbolos) {
                String output = String.format(
                formatBody,
                S.getId(),
                S.getValue(),
                S.getType(),
                S.getAmbito(),
                String.valueOf(S.isVariable()),
                String.valueOf(S.isFunction()),
                String.valueOf(S.isParameter()),
                String.valueOf(S.isByRef()),
                String.valueOf(S.getMemoryPos())
            );
            out.println(output);
        }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

        return "";
    }
}
