package br.com.ab.contracts;

import java.util.HashMap;

/**
 *
 * @author drink Abstração da sql insert
 */
public class ISqlInsert extends ISqlInstruction {

    private HashMap<String, String> rowData;

    public ISqlInsert(String nomeDaTabela) {
        super(nomeDaTabela);
        this.rowData = new HashMap<>();
    }

    @Override
    public String getSql() {
        this.sql.append("INSERT INTO ");
        this.sql.append(this.tabName);
        this.sql.append(" (`");
        this.sql.append(String.join("`, `", this.rowData.keySet()));
        this.sql.append("`) VALUES ('");
        this.sql.append(String.join("', '", this.rowData.values()));
        this.sql.append("')");
        String str = this.sql.toString().replace("'NULL'", "NULL");
        return str.replace("'null'", "NULL");
    }

    @Override
    public void setCriterio(ICriteria criterio) {
        throw new RuntimeException("Não é necessário critérios para INSERT!");
    }

    public HashMap<String, String> getRowData() {
        return rowData;
    }

    public void setRowData(HashMap<String, String> rowData) {
        this.rowData = rowData;
    }

}
