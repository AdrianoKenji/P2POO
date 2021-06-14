/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatecpg.db;

import br.edu.fatecpg.web.DbListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author adria
 */
public class User {
    private String nome;
    private String login;
    private String password;
    
    public static ArrayList<User> getUsers() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        Connection con = DbListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        
        while(rs.next()) {
            list.add(new User(
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("password_hash")
            ));
        }
        
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    public static User getUser(String login, String password) throws Exception {
        User user = null;
        Connection con = DbListener.getConnection();
        String sql = "SELECT * FROM users WHERE login = ? and password_hash = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setLong(2, password.hashCode());
        ResultSet rs = stmt.executeQuery();        
        if(rs.next()) {
           user = new User(
                    rs.getString("nome"),
                    rs.getString("login"),  
                    rs.getString("password_hash")
            );
        }
        rs.close();
        stmt.close();
        con.close();
        return user;
    }

    public User(String nome, String login, String password) {
        this.nome = nome;
        this.login = login;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
