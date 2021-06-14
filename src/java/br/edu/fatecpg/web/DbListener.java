/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatecpg.web;

import br.edu.fatecpg.db.Disciplina;
import br.edu.fatecpg.db.User;
import java.sql.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author adria
 */
public class DbListener implements ServletContextListener {

    public static final String CLASS_NAME = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:myfin.db";
    
    public static String step = null;
    public static Exception exception = null;
    
    public static Connection getConnection() throws Exception {
         return DriverManager.getConnection(URL);
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        step = "Criando tabelas";
        try {
            step = "Conectando com o banco";
            Class.forName(CLASS_NAME);
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            step = "Criando tabela de usuário";
            String sql = "CREATE TABLE IF NOT EXISTS users("
                    + "nome VARCHAR(200) NOT NULL,"
                    + "login VARCHAR(50) UNIQUE NOT NULL,"
                    + "password_hash LONG"
                    + ")";
            stmt.execute(sql);
            step = "Criando tabela de disciplinas";
            sql = "CREATE TABLE IF NOT EXISTS disciplinas("
                    + "nome VARCHAR(50) UNIQUE NOT NULL,"
                    + "diaSemana VARCHAR(200) NOT NULL,"
                    + "horarioInicio VARCHAR(50) NOT NULL,"
                    + "horarioFim VARCHAR(50) NOT NULL,"
                    + "qtAula VARCHAR(20) NOT NULL,"
                    + "notaP1 NUMERIC(2,1) NOT NULL,"
                    + "notaP2 NUMERIC(2,1) NOT NULL"
                    + ")";
            stmt.execute(sql); 
            if(User.getUsers().isEmpty()){ 
                step = "Inserindo usuário padrão";
                sql = "INSERT INTO users(nome, login, password_hash)"
                    + " VALUES('Adriano', 'adriano', '"+ ("1234".hashCode()) + "')";            
                stmt.execute(sql);
            }                  
            if(Disciplina.getDisciplinas().isEmpty()){
                step = "Inserindo disciplina padrão";
                sql = "INSERT INTO disciplinas(nome, diaSemana, horarioInicio, horarioFim, qtAula, notaP1, notaP2)"
                    + " VALUES('Programação Orientada à Objetos', 'Segunda', '15:00', '18:30', '20', 8.5, 10)";
                stmt.execute(sql);                              
            }
            stmt.close();
            con.close();            
        } catch(Exception ex) {
            exception = ex;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
