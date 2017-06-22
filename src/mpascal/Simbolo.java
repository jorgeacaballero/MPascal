/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpascal;

/**
 *
 * @author jmlb
 */
public class Simbolo {
    private String id;
    private String value;
    private String type;
    private String ambito;
    private int refIndex = -1;
    private boolean variable = false;
    private boolean function = false;
    private boolean parameter = false;
    private boolean byRef = false;
    private int memoryPos;

    public int getRefIndex() {
        return refIndex;
    }

    public void setRefIndex(int refIndex) {
        this.refIndex = refIndex;
    }
    
    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public boolean isByRef() {
        return byRef;
    }

    public void setByRef(boolean byRef) {
        this.byRef = byRef;
    }

    
    public Simbolo() {
        this.id = "";
        this.value = "";
        this.type = "";
        this.variable = false;
        this.function = false;
        this.parameter = false;
        this.memoryPos = 0;
        
    }
    
     public Simbolo(String id, String value, String type) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.ambito = null;
    }
    
    public Simbolo(String id, String value, String type, String ambito) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.ambito = ambito;
    }
    
    
    
    public Simbolo(String id, String value, String type, String ambito, boolean variable, boolean function, boolean parameter, int memoryPos) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.ambito = ambito;
        this.variable = variable;
        this.function = function;
        this.parameter = parameter;
        this.memoryPos = memoryPos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean variable) {
        this.variable = variable;
    }

    public boolean isFunction() {
        return function;
    }

    public void setFunction(boolean function) {
        this.function = function;
    }

    public boolean isParameter() {
        return parameter;
    }

    public void setParameter(boolean parameter) {
        this.parameter = parameter;
    }

    public int getMemoryPos() {
        return memoryPos;
    }

    public void setMemoryPos(int memoryPos) {
        this.memoryPos = memoryPos;
    }

}
