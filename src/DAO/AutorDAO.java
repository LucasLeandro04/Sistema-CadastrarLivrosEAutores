/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConexaoBanco.ConexaoBanco;
import Models.Autor;
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
public class AutorDAO {

    private Connection conexao;

    public AutorDAO() throws Exception {
        this.conexao = new ConexaoBanco().getConnection();

        if (this.conexao == null) {
            throw new Exception("Não há conexão com o banco de dados!!!");
        }
    }

    //INSERIR AUTORES
    public void Cadastrar(Autor autor) throws SQLException {

        String query;
        query = "INSERT INTO Autor (nome, sobrenome, país, dataNascimento)";
        query += "VALUES (?,?,?,?);";

        PreparedStatement st = conexao.prepareStatement(query);

        //Primeiro campo da descrição
        //SETAR VALORES NA QUERY
        st.setString(1, autor.getNome());
        st.setString(2, autor.getSobrenome());
        st.setString(3, autor.getPaís());
        st.setDate(4, Date.valueOf(autor.getDataNascimento()));
        //EXECUTAR QUERY
        st.execute();
        st.close();
        conexao.close();
    }

    //listar autores
    public List<Autor> pesquisar() throws SQLException, Exception {

        List<Autor> lista = new ArrayList();

        String query = "SELECT * FROM Autor";

        PreparedStatement st = conexao.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Autor autor = new Autor();
            autor.setIdAutor(rs.getInt("idAutor"));
            autor.setNome(rs.getString("nome"));
            autor.setSobrenome(rs.getString("sobrenome"));
            autor.setPaís(rs.getString("país"));
            autor.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());

            //Adiciona cada coluna na lista
            lista.add(autor);
        }
        return lista;

    }

    public void Excluir(Autor autor) throws SQLException {

        String query;
        query = "DELETE FROM Autor WHERE idAutor = ?";

        PreparedStatement st = conexao.prepareStatement(query);

        //Primeiro campo da descrição
        //SETAR VALORES NA QUERY
        st.setInt(1, autor.getIdAutor());

        //EXECUTAR QUERY
        st.execute();
        st.close();
        conexao.close();
    }

    //método para alterar
    public void atualizar(Autor autor) throws SQLException {

        String query = "UPDATE Autor SET nome = ?,sobrenome=?,país=?,dataNascimento=? WHERE idAutor=?";

        PreparedStatement st = conexao.prepareStatement(query);

        st.setString(1, autor.getNome());
        st.setString(2, autor.getSobrenome());
        st.setString(3, autor.getPaís());
        st.setDate(4, Date.valueOf(autor.getDataNascimento()));
        st.setInt(5, autor.getIdAutor());
        st.execute();
        st.close();
        conexao.close();
    }
}
