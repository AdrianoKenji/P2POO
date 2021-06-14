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
public class Disciplina {
    private long rowId;
    private String nome;
    private String diaSemana;
    private String horarioInicio;
    private String horarioFim;
    private String qtAula;
    private Double notaP1;
    private Double notaP2;
    
    public static ArrayList<Disciplina> getDisciplinas() throws Exception {
        ArrayList<Disciplina> list = new ArrayList<>();
        Connection con = DbListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT rowId, * FROM disciplinas");
        
        while(rs.next()) {
            list.add(new Disciplina(
                    rs.getLong("rowId"),
                    rs.getString("nome"),
                    rs.getString("diaSemana"),
                    rs.getString("horarioInicio"),
                    rs.getString("horarioFim"),
                    rs.getString("qtAula"),
                    rs.getDouble("notaP1"),
                    rs.getDouble("notaP2")
            ));
        }
        
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    public static Disciplina getDisciplina(long rowId) throws Exception {
        Disciplina disciplina = null;
        Connection con = DbListener.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT rowId, * FROM disciplinas WHERE rowId = ?");
        stmt.setLong(1, rowId);
        ResultSet rs = stmt.executeQuery();        
        if(rs.next()) {
           disciplina = new Disciplina(
                    rs.getLong("rowId"),
                    rs.getString("nome"),
                    rs.getString("diaSemana"),
                    rs.getString("horarioInicio"),
                    rs.getString("horarioFim"),
                    rs.getString("qtAula"),
                    rs.getDouble("notaP1"),
                    rs.getDouble("notaP2")
            );
        }
        rs.close();
        stmt.close();
        con.close();
        return disciplina;
    }

    public static void addDisciplina(String nome, String diaSemana, String horarioInicio, String horarioFim, String qtAula, Double notaP1, Double notaP2) throws Exception {
        Connection con = DbListener.getConnection();
        String sql = "INSERT INTO disciplinas(nome, diaSemana, horarioInicio, horarioFim, qtAula, notaP1, notaP2)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, diaSemana);
        stmt.setString(3, horarioInicio);
        stmt.setString(4, horarioFim);
        stmt.setString(5, qtAula);
        stmt.setDouble(6, notaP1);
        stmt.setDouble(7, notaP2);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void updateDisciplina(long rowId, String nome, String diaSemana, String horarioInicio, String horarioFim, String qtAula, Double notaP1, Double notaP2) throws Exception {
        Connection con = DbListener.getConnection();
        String sql = "UPDATE disciplinas "
                + "SET nome = ?, diaSemana = ?, horarioInicio = ?, horarioFim = ?, qtAula = ?, notaP1 = ?, notaP2 = ?)"
                + "WHERE rowId = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, diaSemana);
        stmt.setString(3, horarioInicio);
        stmt.setString(4, horarioFim);
        stmt.setString(5, qtAula);
        stmt.setDouble(6, notaP1);
        stmt.setDouble(7, notaP2);
        stmt.setLong(8, rowId);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void removeDisciplina(long rowId) throws Exception {
        Connection con = DbListener.getConnection();
        String sql = "DELETE FROM disciplinas WHERE rowId = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, rowId);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public Disciplina(long rowId, String nome, String diaSemana, String horarioInicio, String horarioFim, String qtAula, Double notaP1, Double notaP2) {
        this.rowId = rowId;
        this.nome = nome;
        this.diaSemana = diaSemana;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.qtAula = qtAula;
        this.notaP1 = notaP1;
        this.notaP2 = notaP2;
    }

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getQtAula() {
        return qtAula;
    }

    public void setQtAula(String qtAula) {
        this.qtAula = qtAula;
    }

    public Double getNotaP1() {
        return notaP1;
    }

    public void setNotaP1(Double notaP1) {
        this.notaP1 = notaP1;
    }

    public Double getNotaP2() {
        return notaP2;
    }

    public void setNotaP2(Double notaP2) {
        this.notaP2 = notaP2;
    }
    
}
