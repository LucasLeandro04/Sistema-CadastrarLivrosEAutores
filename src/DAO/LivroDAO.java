/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConexaoBanco.ConexaoBanco;
import Models.Autor;
import Models.Livro;
import cadastrodelivros.CadastrarLivro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class LivroDAO {

    private Connection conexao;

    public LivroDAO() throws Exception {
        this.conexao = new ConexaoBanco().getConnection();

        if (this.conexao == null) {
            throw new Exception("Não há conexão com o banco de dados!!!");
        }
    }

    //INSERIR LIVROS
    public void Cadastrar(Livro livro) throws SQLException {

        String query;
        query = "INSERT INTO Livro (titulo, editora,dataPublic,idAutor)";
        query += "VALUES (?,?,?,?);";

        PreparedStatement st = conexao.prepareStatement(query);

        //Primeiro campo da descrição
        //SETAR VALORES NA QUERY
        st.setString(1, livro.getTitulo());
        st.setString(2, livro.getEditora());
        st.setDate(3, Date.valueOf(livro.getDataPublic()));
        st.setInt(4, livro.getAutor().getIdAutor());

        //EXECUTAR QUERY
        st.execute();
        st.close();
        conexao.close();
    }

    //LISTAR LIVROS
    public List<Livro> pesquisar() throws SQLException, Exception {

        List<Livro> lista = new ArrayList();

        String query = "SELECT * FROM Livro";

        PreparedStatement st = conexao.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Livro livro = new Livro();
            livro.setIdLivro(rs.getInt("idLivro"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setEditora(rs.getString("editora"));
            livro.setDataPublic(rs.getDate("dataPublic").toLocalDate());
            //Adiciona cada coluna na lista
            lista.add(livro);
        }
        return lista;
    }

    public void Excluir(Livro livro) throws SQLException {

        String query;
        query = "DELETE FROM Livro WHERE idLivro = ?";

        PreparedStatement st = conexao.prepareStatement(query);

        //Primeiro campo da descrição
        //SETAR VALORES NA QUERY
        st.setInt(1, livro.getIdLivro());

        //EXECUTAR QUERY
        st.execute();
        st.close();
        conexao.close();
    }

    //método para atualizar
    public void atualizar(Livro livro) throws SQLException {

        String query = "UPDATE Livro SET titulo = ?,dataPublic=?,editora=? WHERE idLivro=?";

        PreparedStatement st = conexao.prepareStatement(query);

        st.setString(1, livro.getTitulo());
        st.setDate(2, Date.valueOf(livro.getDataPublic()));
        st.setString(3, livro.getEditora());
        st.setInt(4, livro.getIdLivro());
        st.execute();
        st.close();
        conexao.close();
    }
}
