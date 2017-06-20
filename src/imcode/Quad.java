/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imcode;

/**
 *
 * @author jmlb
 */
public class Quad {
    int index = -1;
    String op = "";
    String arg1 = "";
    String arg2 = "";
    String res = "";
    
public Quad(int index, String op, String arg1, String arg2, String res) {
        this.index = index;
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.res = res;
    }

public Quad(int index, String op, String arg1, String res) {
        this.index = index;
        this.op = op;
        this.arg1 = arg1;
        this.res = res;
    }

public Quad(int index, String op, String res) {
        this.index = index;
        this.op = op;
        this.res = res;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public String toString(){
        
        String message = "%-10s %-10s %-10s %-10s %-10s";
        return String.format(message, index+"", op, arg1, arg2, res);
    }
}


