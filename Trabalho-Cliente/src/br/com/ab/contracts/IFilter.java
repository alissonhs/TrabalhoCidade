package br.com.ab.contracts;

/**
 *
 * @author drink
 */
public class IFilter extends IExpression {
    
    private String variavel;
    private String operador;
    private String valor;
    
    @Override
    public String dump() {
        return "`"+variavel+"` "+operador+" '"+valor+"'";
    }

    public IFilter(String variavel, String operador, String valor) {
        this.variavel = variavel;
        this.operador = operador;
        this.valor = valor;
    }
    
    
    
}
