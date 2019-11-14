package br.com.ab.contracts;

import java.util.ArrayList;

/**
 *
 * @author drink
 */
public class ISqlSelect extends ISqlInstruction {

    public ISqlSelect(String nomeDaTabela) {
        super(nomeDaTabela);
        this.cols = new ArrayList<>();
    }
    private ArrayList<String> cols;

    @Override
    public String getSql() {
        this.sql.append("SELECT ");
        if (this.cols.size()>0) {
            this.sql.append(String.join(", ", cols));
        } else {
            this.sql.append("*");
        }
        this.sql.append(" FROM ");
        this.sql.append(this.tabName);
        
        if (this.criterio != null 
                && this.criterio.hasExpressions()) {
            this.sql.append(" WHERE ");
            this.sql.append(this.criterio.dump());
        }
        
        if (this.criterio != null) {
            if (this.criterio.getProperties().containsKey("order")) {
                this.sql.append(" ORDER BY ");
                this.sql.append(this.criterio.getProperties().get("order"));
            }
            if (this.criterio.getProperties().containsKey("limit")) {
                this.sql.append(" limit ");
                this.sql.append(this.criterio.getProperties().get("limit"));
            }
            if (this.criterio.getProperties().containsKey("offset")) {
                this.sql.append(" offset ");
                this.sql.append(this.criterio.getProperties().get("offset"));
            }
        }
            
            
       return this.sql.toString();
    }

    public void setCols(ArrayList<String> cols) {
        this.cols = cols;
    }

    public ArrayList<String> getCols() {
        return cols;
    }

    
    
}
