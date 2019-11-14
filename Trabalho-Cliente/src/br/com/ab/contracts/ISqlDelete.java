
package br.com.ab.contracts;

/**
 *
 * @author drink
 */
public class ISqlDelete extends ISqlInstruction{

    public ISqlDelete(String nomeDaTabela) {
        super(nomeDaTabela);
    }

    @Override
    public String getSql() {
        if (criterio == null || !criterio.hasExpressions()) {
            throw new RuntimeException("é necessário o uso de pelo menos um critério!");
        }
        this.sql.append("DELETE FROM ");
        this.sql.append(this.tabName);
        this.sql.append(" WHERE ");
        this.sql.append(this.criterio.dump());
        return sql.toString();
    }
    
    
}
