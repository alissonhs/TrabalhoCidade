package br.com.ab.contracts;

/**
 *
 * @author drink
 */
public abstract class IExpression {
    
    public static final String AND_OPERATOR = " AND ";
    public static final String OR_OPERATOR = " OR ";
    
    public abstract String dump();
    
}
