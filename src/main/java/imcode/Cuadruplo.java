package imcode;

/**
 *
 * @author jose.lopez
 */
public class Cuadruplo {
    int indice = -1;
    String operacion = "";
    String arg1 = "";
    String arg2 = "";
    String resultado = "";

    public Cuadruplo(int indice, String operacion, String arg1, String arg2, String resultado) {
        this.indice = indice;
        this.operacion = operacion;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultado = resultado;
    }

    public Cuadruplo(int indice, String operacion, String arg1, String resultado) {
        this.indice = indice;
        this.operacion = operacion;
        this.arg1 = arg1;
        this.resultado = resultado;
    }

    public Cuadruplo(int indice, String operacion, String resultado) {
        this.indice = indice;
        this.operacion = operacion;
        this.resultado = resultado;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    @Override
    public String toString(){
        
        String message = "%-10s %-10s %-10s %-10s %-10s";
        return String.format(message, indice+"", operacion, arg1, arg2, resultado);
    }
    
    
}
