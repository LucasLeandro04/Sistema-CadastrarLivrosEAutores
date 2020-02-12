/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cadastrodelivros.CadastrarLivro;
import cadastrodelivros.CadastroAutor;
import cadastrodelivros.MenuPrincipal;
import cadastrodelivros.PesquisarAutor;
import cadastrodelivros.PesquisarLivro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private Button btnCadastrarLivro;
    @FXML
    private Button btnPesquisarLivro;
    @FXML
    private Button btnCadastrarAutor;
    @FXML
    private Button btnPesquisarAutor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCadastrarLivro_Click(ActionEvent event) throws Exception {
       CadastrarLivro cl = new CadastrarLivro();
       cl.start(new Stage());
       fecharAnterior();
       
    }

    @FXML
    private void btnPesquisarLivro_Click(ActionEvent event) throws Exception {
        PesquisarLivro pl = new PesquisarLivro();
        pl.start(new Stage());
        fecharAnterior();
    }

    @FXML
    private void btnCadastrarAutor_Click(ActionEvent event) throws Exception {
        CadastroAutor ca = new CadastroAutor();
        ca.start(new Stage());
        fecharAnterior();
    }


    @FXML
    private void btnPesquisarAutor_Click(ActionEvent event) throws Exception {
        PesquisarAutor pa = new PesquisarAutor();
        pa.start(new Stage());
        MenuPrincipal.getStage().close();
        
    }
   
    public void fecharAnterior(){
        MenuPrincipal.getStage().close();
    }
    
}
