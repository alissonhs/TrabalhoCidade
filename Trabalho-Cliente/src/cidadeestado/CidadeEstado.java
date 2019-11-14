/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cidadeestado;

import br.com.ab.contracts.CrudInterface;
import br.com.ab.contracts.FormControllerInterface;
import br.com.ab.contracts.LookUpControllerInterface;
import br.com.ab.controller.LookUPController;
import br.com.ab.controller.TelaDePesquisaController;
import br.com.ab.dao.CidadeDao;
import br.com.ab.dao.ClienteDao;
import br.com.ab.dao.DaoInterface;
import br.com.ab.dao.EstadoDao;
import br.com.ab.dao.TableModelInterface;
import br.com.ab.services.Conexao;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author drink
 */
public class CidadeEstado extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loadFormEstado
                = new FXMLLoader(
                        getClass()
                                .getResource("/br/com/ab/view/FormEstado.fxml")
                );

        Parent formestado = loadFormEstado.load();
        FormControllerInterface ce = loadFormEstado.getController();
        
        FXMLLoader loadFormCidade
                = new FXMLLoader(
                        getClass()
                                .getResource("/br/com/ab/view/FormCidade.fxml")
                );

        Parent formcidade = loadFormCidade.load();
        FormControllerInterface cc = loadFormCidade.getController();
        
        FXMLLoader loadLookUp
                = new FXMLLoader(
                        getClass()
                                .getResource("/br/com/ab/view/LookUP.fxml")
                );

        Parent formlookup = loadLookUp.load();
        FormControllerInterface cl = loadLookUp.getController();
        
        FXMLLoader loadFormCliente
                = new FXMLLoader(
                        getClass()
                                .getResource("/br/com/ab/view/FormCliente.fxml")
                );

        Parent formcliente= loadFormCliente.load();
        FormControllerInterface cci = loadFormCliente.getController();

        FXMLLoader loadCrudView
                = new FXMLLoader(
                        getClass()
                                .getResource("/br/com/ab/view/CrudView.fxml")
                );
        

        Parent crudview = loadCrudView.load();
        CrudInterface crudController = loadCrudView.getController();
        
        

        // Carrega o leiaute e o controller Principal
        FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loaderPrincipal.load();
        FXMLDocumentController pc = loaderPrincipal.getController();
        // Carrega o leiaute e o controller de pesquisa
        FXMLLoader loaderPesquisa = new FXMLLoader(getClass().getResource("/br/com/ab/view/TelaDePesquisa.fxml"));
        Parent bp = loaderPesquisa.load();
        TelaDePesquisaController tpc = loaderPesquisa.getController();
        
        pc.getBtnCidade().addEventHandler(ActionEvent.ACTION, (event) -> {
            ((LookUPController)cl).configurar(new EstadoDao(Conexao.getInstance().getConn()),"Pesquisar Estado");
            TableModelInterface tm = new CidadeDao(Conexao.getInstance().getConn());
             tpc.configure(tm);
            ((LookUpControllerInterface)cc).setLookUp((LookUpControllerInterface)cl);
            
            crudController.configurar((DaoInterface)tm, tpc, cc, "Cidades");
            pc.getContainer().getChildren().clear();
            pc.getContainer().getChildren().add(crudview);
            //TableModelInterface tm = new FalseDaoCidade();
            
           
            stage.setTitle("Pesquisa de Cidade!");
        });
        
        pc.getBtnEstado().addEventHandler(ActionEvent.ACTION, (event) -> {
            TableModelInterface tm = new EstadoDao(Conexao.getInstance().getConn()); 
            tpc.configure(tm);
            crudController.configurar((DaoInterface) tm, tpc, ce, "Estados");
            pc.getContainer().getChildren().clear();
            pc.getContainer().getChildren().add(crudview);
            stage.setTitle("Pesquisa de estado!");
        });
        
        pc.getBtnCliente().addEventHandler(ActionEvent.ACTION, (event) -> {
            ((LookUPController)cl).configurar(new CidadeDao(Conexao.getInstance().getConn()),"Pesquisar Cidade");
            TableModelInterface tm = new ClienteDao(Conexao.getInstance().getConn()); 
            tpc.configure(tm);
            ((LookUpControllerInterface)cci).setLookUp((LookUpControllerInterface)cl);
            crudController.configurar((DaoInterface) tm, tpc, cci, "Clientes");
            pc.getContainer().getChildren().clear();
            pc.getContainer().getChildren().add(crudview);
            stage.setTitle("Pesquisa de Cliente");
        });

        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        /**
         * Estado e = new Estado(); e.setId(1); e.setNome("PARANÁ");
         * e.setUf("PR");
         *
         * Cidade c = new Cidade(); c.setId(1); c.setNome("Palotina");
         * c.setEstado(e);
         *
         * System.out.println(c.getEstado().getNome());
         * System.out.println(c.getEstado().getNome().replace("A", "I"));
         */
    }

}
