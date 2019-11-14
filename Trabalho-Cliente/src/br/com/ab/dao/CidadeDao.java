package br.com.ab.dao;

import br.com.ab.contracts.ICriteria;
import br.com.ab.contracts.IFilter;
import br.com.ab.contracts.ISqlInsert;
import br.com.ab.contracts.ISqlInstruction;
import br.com.ab.contracts.ISqlUpdate;
import br.com.ab.model.Cidade;
import br.com.ab.model.Estado;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author drink
 */
public class CidadeDao extends AbstractDao implements TableModelInterface {

    private EstadoDao daoEstado;

    public CidadeDao(Connection conn) {
        this.conn = conn;
        daoEstado = new EstadoDao(conn);
    }

    @Override
    protected String getTableName() {
        return "cidades";
    }

    @Override
    public ArrayList<Object> getByCriterios(ICriteria c) {
        // Cria a instrução sql
        ISqlInstruction sql = this.newInstruction(ISqlInstruction.QueryType.SELECT);
        // Parametriza a instrução SQL
        sql.setCriterio(c);
        ArrayList<Cidade> ests = new ArrayList<>();
        try {
            // Executa a sql
            ArrayList<HashMap<String, Object>> dados = this.executeSql(sql);
            if (!dados.isEmpty()) {
                for (HashMap<String, Object> row : dados) {
                    // Cria um estado para cada linha que retornou do banco
                    Cidade cid = new Cidade();
                    cid.setId(((BigInteger) row.get("id")).longValue());
                    cid.setNome((String) row.get("nome"));
                    if (((BigInteger) row.get("estado_id")).intValue() > 0) {
                        cid.setEstado(
                                ((ArrayList<Estado>) daoEstado.getById(
                                        ((BigInteger) row.get("estado_id")).longValue())).get(0)
                        );
                    }
                    ests.add(cid);
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (ArrayList) ests;
    }

    @Override
    public void salvar(Object o) {
        Cidade cid = (Cidade) o;
        ISqlInstruction sql = this.newInstruction(ISqlInstruction.QueryType.INSERT);
        if (cid.getId() > 0) {
            sql = this.newInstruction(ISqlInstruction.QueryType.UPDATE);
        }
        
        if (sql instanceof ISqlUpdate) {
        ((ISqlUpdate) sql).addRowData("nome", cid.getNome());
        ((ISqlUpdate) sql).addRowData("estado_id", Long.toString(cid.getEstado().getId()));
            //update
            ICriteria criterio = new ICriteria();
            criterio.addExpressions(
                    new IFilter(
                            "id",
                            "=",
                            Long.toString(cid.getId()) //String.valueOf()
                    )
            );
            sql.setCriterio(criterio);
        } else if (sql instanceof ISqlInsert) {
            //insert
            ((ISqlInsert) sql).getRowData().put("id", null);
            ((ISqlInsert) sql).getRowData().put("nome",cid.getNome());
            ((ISqlInsert) sql).getRowData().put("estado_id",Long.toString(cid.getEstado().getId()));
        }
        try {
            Object ret = this.executeSql(sql);
            if (sql instanceof ISqlInsert && ret instanceof Long) {
                cid.setId((Long) ret);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remover(Object o) {
        Cidade cid = (Cidade) o;
        if (cid.getId() > 0) {
            ISqlInstruction del = this.newInstruction(ISqlInstruction.QueryType.DELETE);
            ICriteria criterio = new ICriteria();
            String vlo = String.valueOf(cid.getId());
            IFilter filtro = new IFilter("id", "=", vlo);
            criterio.addExpressions(filtro);
            ///continuação
            del.setCriterio(criterio);
            try {
                executeSql(del);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public Object getById(long id) {
        ICriteria criterio = new ICriteria();
        criterio.addExpressions(new IFilter("id", "=", String.valueOf(id)));
        return this.getByCriterios(criterio);
    }

    @Override
    public ArrayList<TableColumn<Object, Object>> getCols() {
        ArrayList<TableColumn<Object, Object>> cols = new ArrayList<>();
        TableColumn<Object, Object> nome = new TableColumn<>("Cidade Nome");
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cols.add(nome);
        TableColumn<Object, Object> est = new TableColumn<>("Estado");
        est.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cols.add(est);
        return cols;
    }

    @Override
    public ArrayList<Object> pesquisar(String param) {
        ICriteria criterio = new ICriteria();
        criterio.addExpressions(new IFilter("nome", "like", "%" + param + "%"));
        return this.getByCriterios(criterio);
    }

}
