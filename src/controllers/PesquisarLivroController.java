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
import cadastrodelivros.CadastroAutor;
import cadastrodelivros.MenuPrincipal;
import cadastrodelivros.PesquisarLivro;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.Util;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class PesquisarLivroController implements Initializable {

    @FXML
    private TableView<Livro> tabelaLivro;
    @FXML
    private TableColumn<Livro, Integer> colIdLivro;
    @FXML
    private TableColumn<Livro, String> colTítulo;
    @FXML
    private TableColumn<Livro, String> colEditora;
    @FXML
    private TableColumn<Livro, LocalDate> colDtPublic;
    @FXML
    private TableColumn<Livro, String> colAutor;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnPesquisar;
    @FXML
    private Button btnAlterar;

    private Livro selecionar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarTabela();
        tabelaLivro.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selecionar = (Livro) newValue;
                });
    }

    public void iniciarTabela() {
        colIdLivro.setCellValueFactory(new PropertyValueFactory("idLivro"));
        colTítulo.setCellValueFactory(new PropertyValueFactory("titulo"));
        colEditora.setCellValueFactory(new PropertyValueFactory("editora"));
        colDtPublic.setCellValueFactory(new PropertyValueFactory("dataPublic"));
        colAutor.setCellValueFactory(new PropertyValueFactory("idAutor"));

    }

    public ObservableList<Livro> atualizartabela() throws Exception {
        LivroDAO livdao = new LivroDAO();
        return FXCollections.observableArrayList(livdao.pesquisar());
    }

    @FXML
    private void btnVoltar_Click(ActionEvent event) throws Exception {
        PesquisarLivro.getStage().close();
        MenuPrincipal mp = new MenuPrincipal();
        mp.start(new Stage());

    }

    @FXML
    private void btnExcluir_Click(ActionEvent event) throws Exception {
        //Se nada estiver marcado, então ele irá emitir uma mensagem de aviso
        if (selecionar != null) {
            excluir();
        } else {
            Util.perigo("Selecione algum campo!");
        }
    }

    @FXML
    private void btnPesquisar_Click(ActionEvent event) throws Exception {
        tabelaLivro.setItems(atualizartabela());
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) throws Exception {
        CadastroLivroController.setlivselecionado(selecionar);
        CadastrarLivro cl = new CadastrarLivro();
        cl.start(new Stage());
    }

    public void excluir() throws Exception {
        LivroDAO livdao = new LivroDAO();
        livdao.Excluir(selecionar);
    }
}
