/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.AutorDAO;
import DAO.LivroDAO;
import Models.Autor;
import Models.Livro;
import cadastrodelivros.CadastrarLivro;
import cadastrodelivros.MenuPrincipal;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Util;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class CadastroLivroController implements Initializable {

    @FXML
    private TextField txtTítulo;
    @FXML
    private TextField txtEditora;
    @FXML
    private Button btnCadastrar;
    @FXML
    private DatePicker txtDataPublic;
    @FXML
    private ComboBox<Autor> txtComboBoxAutor;

    private ObservableList<Autor> obsAutores;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnAlterar;

    private static Livro livselecionado;

    public static void setlivselecionado(Livro livAtual) {
        CadastroLivroController.livselecionado = livAtual;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCombo();
        if (livselecionado == null) {
            btnCadastrar.setDisable(false);
        } else {
            txtTítulo.setText(livselecionado.getTitulo());
            txtEditora.setText(livselecionado.getEditora());
            txtDataPublic.setValue(livselecionado.getDataPublic());
        }

    }

    public void carregarCombo() {
        try {
            AutorDAO autdao = new AutorDAO();
            List<Autor> lista = autdao.pesquisar();

            //txtComboBoxAutor.getSelectionModel().getSelectedItem();
            txtComboBoxAutor.setItems(FXCollections.observableArrayList(lista));

        } catch (Exception ex) {
            Logger.getLogger(CadastroLivroController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnCadastrar_Click(ActionEvent event) throws Exception {
        LivroDAO livDao = new LivroDAO();
        Livro livro = new Livro();
        livro.setTitulo(txtTítulo.getText());
        livro.setEditora(txtEditora.getText());
        livro.setDataPublic(txtDataPublic.getValue());
        livro.setAutor(txtComboBoxAutor.getValue());

        livDao.Cadastrar(livro);

    }

    @FXML
    private void btnVoltar_Click(ActionEvent event) throws Exception {
        CadastrarLivro.getStage().close();
        MenuPrincipal mp = new MenuPrincipal();
        mp.start(new Stage());

    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) throws Exception {
        try {
            livselecionado.setTitulo(txtTítulo.getText());
            livselecionado.setEditora(txtEditora.getText());
            livselecionado.setDataPublic(txtDataPublic.getValue());
            LivroDAO livdao = new LivroDAO();
            livdao.atualizar(livselecionado);

            ((Stage) btnAlterar.getScene().getWindow()).close();
        } catch (SQLException ex) {
            Util.mensagem("Erro ao alterar ! : \n" + ex.getMessage());
        }
    }
}
