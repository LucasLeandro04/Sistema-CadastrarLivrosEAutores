/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBanco;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Lucas
 */
public class ConexaoBanco {

    private Connection conexao = null;
    private final String BANCO = "dbCadastroDeLivros";
    private final String LOGIN = "root";
    private final String SENHA = "";
    private final String HOST = "localhost";

    public Connection getConnection() {
        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setServerName(HOST);
            ds.setDatabaseName(BANCO);
            ds.setUser(LOGIN);
            ds.setPassword(SENHA);
            ds.setConnectTimeout(2000);

            conexao = ds.getConnection();

            System.out.println("CONECTADO AO BANCO DE DADOS MYSQL!!!");
            return conexao;
        } catch (SQLException erro) {
            System.out.println("FALHA NA CONEXÃO COM O BANCO MYSQL!!" + erro.getMessage());
            return null;
        }
    }
}