/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ab.dao;

import br.com.ab.contracts.ICriteria;
import br.com.ab.contracts.IFilter;
import br.com.ab.contracts.ISqlInsert;
import br.com.ab.contracts.ISqlInstruction;
import br.com.ab.contracts.ISqlUpdate;
import br.com.ab.model.Cidade;
import br.com.ab.model.Cliente;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author alissonhs
 */
public class ClienteDao extends AbstractDao implements TableModelInterface{
    
    private CidadeDao daoCidade;
    
     public ClienteDao(Connection conn) {
        this.conn = conn;
        daoCidade = new CidadeDao(conn);
    }

    @Override
    protected String getTableName() {
        return "clientes";
    }

    @Override
    public ArrayList<Object> getByCriterios(ICriteria c) {
        // Cria a instrução sql
        ISqlInstruction sql = this.newInstruction(ISqlInstruction.QueryType.SELECT);
        // Parametriza a instrução SQL
        sql.setCriterio(c);
        ArrayList<Cliente> clis = new ArrayList<>();
        try {
            // Executa a sql
            ArrayList<HashMap<String, Object>> dados = this.executeSql(sql);
            if (!dados.isEmpty()) {

                for (HashMap<String, Object> row : dados) {
                    // Cria um estado para cada linha que retornou do banco
                    Cliente cli = new Cliente();
                    cli.setId(((Long) row.get("id")));
                    cli.setNome((String) row.get("nome"));
                    if (((BigInteger) row.get("cidade")).intValue() > 0) {
                        cli.setCidade(
                                ((ArrayList<Cidade>) daoCidade.getById(
                                        ((BigInteger) row.get("cidade")).longValue())).get(0)
                        );
                    }
                    clis.add(cli);
                

            }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (ArrayList) clis;
        }
    

    @Override
    public void salvar(Object cs) {
        Cliente cliente = (Cliente) cs;
        ISqlInstruction sql = this.newInstruction(ISqlInstruction.QueryType.INSERT);
        if (cliente.getId() > 0) {
            sql = this.newInstruction(ISqlInstruction.QueryType.UPDATE);
        }

        if (sql instanceof ISqlUpdate) {
            ((ISqlUpdate) sql).addRowData("nome", cliente.getNome());
            ((ISqlUpdate) sql).addRowData("cidade", Long.toString(cliente.getCidade().getId()));
            //update
            ICriteria criterio = new ICriteria();
            criterio.addExpressions(
                    new IFilter(
                            "id",
                            "=",
                            Long.toString(cliente.getId()) //String.valueOf()
                    )
            );
            sql.setCriterio(criterio);
        } else if (sql instanceof ISqlInsert) {
            //insert
            ((ISqlInsert) sql).getRowData().put("id", null);
            ((ISqlInsert) sql).getRowData().put("nome", cliente.getNome());
            ((ISqlInsert) sql).getRowData().put("cidade",Long.toString(cliente.getCidade().getId()));
        }
        try {
            Object ret = this.executeSql(sql);
            if (sql instanceof ISqlInsert && ret instanceof Long) {
                cliente.setId((Long) ret);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remover(Object cs) {
        Cliente cliente = (Cliente) cs;
        if (cliente.getId() > 0) {
            ISqlInstruction del = this.newInstruction(ISqlInstruction.QueryType.DELETE);
            ICriteria criterio = new ICriteria();
            String vlo = String.valueOf(cliente.getId());
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
        TableColumn<Object, Object> nome = new TableColumn<>("Nome cliente");
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cols.add(nome);
        TableColumn<Object, Object> est = new TableColumn<>("Cidade");
        est.setCellValueFactory(new PropertyValueFactory<>("cidade"));
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


