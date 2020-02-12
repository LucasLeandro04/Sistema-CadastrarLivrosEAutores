/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.AutorDAO;
import Models.Autor;
import cadastrodelivros.CadastroAutor;
import cadastrodelivros.MenuPrincipal;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Util;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class CadastroAutorController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPaís;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private DatePicker dataNascimento;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnVoltar;

    private static Autor autselecionado;

    public static void setcadselecionado(Autor autAtual) {
        CadastroAutorController.autselecionado = autAtual;
    }
    @FXML
    private Button btnAlterar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (autselecionado == null) {
            btnCadastrar.setDisable(false);
        } else {
            txtNome.setText(autselecionado.getNome());
            txtSobrenome.setText(autselecionado.getSobrenome());
            txtPaís.setText(autselecionado.getPaís());
            dataNascimento.setValue(autselecionado.getDataNascimento());
        }

    }

    @FXML
    private void btnCadastrar_Click(ActionEvent event) throws Exception {
        AutorDAO autDao = new AutorDAO();
        Autor autor = new Autor();
        autor.setNome(txtNome.getText());
        autor.setSobrenome(txtSobrenome.getText());
        autor.setPaís(txtPaís.getText());
        autor.setDataNascimento(dataNascimento.getValue());

        autDao.Cadastrar(autor);

    }

    @FXML
    private void btnVoltar_Click(ActionEvent event) throws Exception {
        CadastroAutor.getStage().close();
        MenuPrincipal mp = new MenuPrincipal();
        mp.start(new Stage());

    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) throws Exception {
        try {
            autselecionado.setNome(txtNome.getText());
            autselecionado.setSobrenome(txtSobrenome.getText());
            autselecionado.setPaís(txtPaís.getText());
            autselecionado.setDataNascimento(dataNascimento.getValue());
            AutorDAO autdao = new AutorDAO();
            autdao.atualizar(autselecionado);

            ((Stage) btnAlterar.getScene().getWindow()).close();
        } catch (SQLException ex) {
            Util.mensagem("Erro ao alterar! : \n" + ex.getMessage());
        }
    }

}
