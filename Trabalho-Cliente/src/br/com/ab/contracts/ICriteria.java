package br.com.ab.contracts;

import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author drink
 */
public class ICriteria extends IExpression {

    private Properties properties;
    private ArrayList<IExpression> expressions;
    private ArrayList<String> operators;
    
    @Override
    public String dump() {
        String sql = "";
        for (int i = 0; i < expressions.size();i++) {
            sql += operators.get(i) + expressions.get(i).dump();
        }
        return "("+sql+")";
    }
    
    public boolean hasExpressions(){
        return !expressions.isEmpty();
        //return expression.size()>0;
    }

    public ICriteria() {
        properties = new Properties();
        expressions = new ArrayList<>();
        operators = new ArrayList<>();        
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void addExpressions(IExpression exp, String ope) {        
        
        if (expressions.isEmpty()){
            this.operators.add("");
        } else {
            this.operators.add(ope);
        }
        this.expressions.add(exp);
    }
    
    public void addExpressions(IExpression exp) {
        this.addExpressions(exp, AND_OPERATOR);
    }
    
    
}
