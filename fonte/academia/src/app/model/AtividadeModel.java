/*
 * Copyright (c) 2014 WISE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    WISE - initial API and implementation and/or initial documentation
 */
package app.model;

import app.model.tablemodel.AtividadeTableModel;
import app.model.tablemodel.AtletaAtividadeTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author WISE
 */
public class AtividadeModel {

    private String query;
    private PreparedStatement pstm;
    private Statement stm;
    private ResultSet rs;
    private AtividadeTableModel atm;

    public List<Atividade> al = new ArrayList<Atividade>();
    private Atividade a;
    public List<AtletaAtividade> at = new ArrayList<AtletaAtividade>();
    private AtletaAtividadeTableModel attm;

    private DefaultListModel list;

    public Boolean cadastrar(Atividade a) {

        query = "INSERT INTO `academia`.`atividade`"
                + "(`nome`,\n"
                + "`ativo`,\n"
                + "`valor`)\n"
                + "VALUES\n"
                + "(?,?,?)";

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setString(1, a.getNome());
            pstm.setString(2, a.getAtivo());
            pstm.setFloat(3, a.getValor());

            pstm.execute();
            pstm.close();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();
        return false;
    }

    public AtividadeTableModel listar() {

        query = "SELECT * FROM atividade";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                Atividade a = new Atividade();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setAtivo(rs.getString("ativo"));
                a.setValor(rs.getFloat("valor"));
                al.add(a);

            }

        } catch (SQLException ex) {
            System.out.println("ERRO");
        }

        DB.desconectar();
        return atm = new AtividadeTableModel(al);

    }

    public List<Atividade> listarAtividade() {

        query = "SELECT * FROM atividade";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                Atividade a = new Atividade();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setAtivo(rs.getString("ativo"));
                a.setValor(rs.getFloat("valor"));
                al.add(a);

            }

        } catch (SQLException ex) {
            System.out.println("ERRO");
        }

        DB.desconectar();
        return al;

    }

    /*Filtrar pesquisa*/
    public List<Atividade> filtrar(String valor) {
        query = "SELECT id, nome, valor, ativo FROM atividade "
                + " WHERE nome LIKE '%" + valor + "%'";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                a = new Atividade();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setValor(rs.getFloat("valor"));
                a.setAtivo(rs.getString("ativo"));

                al.add(a);

            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();
        return al;
    }

    public boolean deletar(int id) {
        query = "DELETE FROM atividade "
                + "WHERE id=?";

        DB.conectar();

        try {
            pstm = DB.con.prepareCall(query);
            pstm.setInt(1, id);
            pstm.execute();
            pstm.close();

            return true;
        } catch (SQLException ex) {
            return false;
        }

        
    }

    public Atividade retorna_atividade(int id) {
        query = "SELECT * FROM atividade "
                + " WHERE id=" + id;

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                a = new Atividade();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setValor(rs.getFloat("valor"));
                a.setAtivo(rs.getString("ativo"));

            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();
        return a;
    }

    public void atualizar(Atividade a, int id) {
        query = "UPDATE academia.atividade SET nome = ? , valor = ? , ativo = ? "
                + " WHERE id = ? ";
        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setString(1, a.getNome());
            pstm.setFloat(2, a.getValor());
            pstm.setString(3, a.getAtivo());
            pstm.setInt(4, id);

            pstm.execute();
            pstm.close();

        } catch (SQLException ex) {

            System.err.println(ex);
        }
        DB.desconectar();
    }

    /**
     * Cadastrando atividades a um atleta
     *
     * @param atletaId
     * @param ativididadeId
     * @return
     */
    public boolean cadastrarAtividade(Atleta atletaId, Atividade ativididadeId) {

        query = "INSERT INTO `academia.atletas_has_atividade`"
                + "(`atletas_id`,\n"
                + "`atividade_id`)\n"
                + "VALUES\n"
                + "(?,?)";

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setInt(1, atletaId.getId());
            pstm.setInt(1, ativididadeId.getId());

            pstm.execute();
            pstm.close();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();
        return false;

    }

    /**
     * Lista as atividades de 1 atleta.
     *
     */
    public AtletaAtividadeTableModel listaAtletaAtividades(AtletaAtividade atv) {
        query = "SELECT at.id as atividade_id, at.nome as nome_atividade, at.ativo, at.valor, at.ativo , a.nome as nome_atleta , a.id as atleta_id \n"
                + "FROM atividade at, atletas a, atletas_has_atividade\n"
                + "WHERE a.id = atletas_id\n"
                + "AND atividade_id = at.id\n"
                + "AND atletas_id = " + atv.getAtletaId();

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                AtletaAtividade a = new AtletaAtividade();

                a.setAtletaId(rs.getInt("atleta_id"));
                a.setAtividadeId(rs.getInt("atividade_id"));
                a.setNomeAtividade(rs.getString("nome_atividade"));
                a.setValorAtividade(rs.getFloat("valor"));
                a.setAtivoAtividade(rs.getString("ativo"));
                at.add(a);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        DB.desconectar();
        return attm = new AtletaAtividadeTableModel(at);

    }

    /**
     * Retorna uma lista de Atividades que o atleta está cadastrado.
     *
     * @param id do atleta.
     * @return
     */
    public List<AtletaAtividade> listaAtletaAtividades2(int id) {
        query = "SELECT at.id as atividade_id, at.nome as nome_atividade, at.ativo, at.valor, at.ativo , a.nome as nome_atleta , a.id as atleta_id \n"
                + "FROM atividade at, atletas a, atletas_has_atividade\n"
                + "WHERE a.id = atletas_id\n"
                + "AND atividade_id = at.id\n"
                + "AND atletas_id = " + id;

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                AtletaAtividade a = new AtletaAtividade();

                a.setAtletaId(rs.getInt("atleta_id"));
                a.setAtividadeId(rs.getInt("atividade_id"));
                a.setNomeAtividade(rs.getString("nome_atividade"));
                a.setValorAtividade(rs.getFloat("valor"));
                a.setAtivoAtividade(rs.getString("ativo"));
                at.add(a);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        DB.desconectar();
        return at;
    }

    public int recuperaIdAtleta(int matricula) {
        query = "SELECT id from atletas WHERE matricula = " + matricula;

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                int idAtleta;
                idAtleta = rs.getInt("id");

                return idAtleta;
            }

        } catch (SQLException ex) {
            System.out.println("ERRO");
        }

        DB.desconectar();
        return 0;
    }

    public List<Atividade> listarAtletaAtividade() {
        query = "SELECT id, nome, valor FROM atividade WHERE ativo='S' ";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                Atividade a = new Atividade();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setValor(rs.getFloat("valor"));
                al.add(a);

            }

        } catch (SQLException ex) {
            System.out.println("ERRO");
        }

        DB.desconectar();
        return al;

    }

    public boolean cadastrarAtletaAtividade(int atividadeId, int atletaId) {

        query = "INSERT INTO atletas_has_atividade "
                + "(`atletas_id`,\n"
                + "`atividade_id`)\n"
                + "VALUES\n"
                + "(?,?)";

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setInt(2, atividadeId);
            pstm.setInt(1, atletaId);

            pstm.execute();
            pstm.close();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();
        return false;
    }

    /*Remove uma atividade de 1 atleta.*/
    public boolean removerAtividade(int atletasId, int atividadeId) {
        query = "DELETE FROM atletas_has_atividade "
                + "WHERE atletas_id=? "
                + "AND atividade_id=? ";

        DB.conectar();

        try {
            pstm = DB.con.prepareCall(query);
            pstm.setInt(1, atletasId);
            pstm.setInt(2, atividadeId);
            pstm.execute();
            pstm.close();

            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        DB.desconectar();
        return false;
    }

    public int retornaIdAtividade(String nomeAtividade) {
        query = "SELECT id FROM atividade WHERE nome='" + nomeAtividade + "'";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);
            if (rs.next()) {
                int id = rs.getInt("id");
                return id;

            }

        } catch (SQLException ex) {
            System.out.println("ERRO");
        }

        DB.desconectar();
        return 0;
    }

    /**
     * FIM Atleta tem atividade *
     */
}
