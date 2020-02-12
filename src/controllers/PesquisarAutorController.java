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
import cadastrodelivros.PesquisarAutor;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.Util;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class PesquisarAutorController implements Initializable {

    @FXML
    private Button btnPesquisar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtFiltro;

    @FXML
    private TableColumn<Autor, Integer> colIdAutor;
    @FXML
    private TableColumn<Autor, String> colNome;
    @FXML
    private TableColumn<Autor, String> colSobrenome;
    @FXML
    private TableColumn<Autor, String> colPaís;
    @FXML
    private TableColumn<Autor, LocalDate> colDtNasc;

    private Autor selecionar;
    @FXML
    private TableView<Autor> tabelaAutor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTabela();

        tabelaAutor.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selecionar = (Autor) newValue;
                });
    }

    public void iniciarTabela() {
        colIdAutor.setCellValueFactory(new PropertyValueFactory("idAutor"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colSobrenome.setCellValueFactory(new PropertyValueFactory("sobrenome"));
        colPaís.setCellValueFactory(new PropertyValueFactory("país"));
        colDtNasc.setCellValueFactory(new PropertyValueFactory("dataNascimento"));

    }

    public ObservableList<Autor> atualizartabela() throws Exception {
        AutorDAO autDAO = new AutorDAO();
        return FXCollections.observableArrayList(autDAO.pesquisar());
    }

    @FXML
    private void btnPesquisar_Click(ActionEvent event) throws Exception {
        tabelaAutor.setItems(atualizartabela());

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

    private void addEventoTab() {
        tabelaAutor.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        btnAlterar.setDisable(true);
                        btnExcluir.setDisable(true);
                        selecionar = null;
                    } else {
                        btnAlterar.setDisable(false);
                        btnExcluir.setDisable(false);
                        selecionar = (Autor) newValue;
                    }
                });
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) throws Exception {
        CadastroAutorController.setcadselecionado(selecionar);
        CadastroAutor ca = new CadastroAutor();
        ca.start(new Stage());
      
    }

    @FXML
    private void btnVoltar_Click(ActionEvent event) throws Exception {
        PesquisarAutor.getStage().close();
        MenuPrincipal mp = new MenuPrincipal();
        mp.start(new Stage());
    }

    public void excluir() throws Exception {
        AutorDAO autdao = new AutorDAO();
        autdao.Excluir(selecionar);
    }
}
