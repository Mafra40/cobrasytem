/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.contadetalhes;

import app.model.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import libs.Logs;

/**
 *
 * @author MafraWise
 */
public class ContaDetalhesModel implements ContasDetalhesIntarface {

    private String query;
    private Statement stm;
    private ResultSet rs;

    public List<ContaDetalhes> a = new ArrayList<ContaDetalhes>();
    private PreparedStatement pstm;

    /**
     *
     * @param idContasReceber
     * @return
     */
    @Override
    public List<ContaDetalhes> listar(int idContasReceber) {
        query = "SELECT d.id, a.nome, a.valor,  d.id_contas_receber FROM academia.detalhes_contas_receber d , atividade a \n"
                + "WHERE d.id_atividade = a.id\n"
                + "AND id_contas_receber =" + idContasReceber;
        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                ContaDetalhes c = new ContaDetalhes();

                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setValor(rs.getFloat("valor"));
                c.setId_contas_receber(rs.getInt("id_contas_receber"));
                a.add(c);

            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return a;
    }

    @Override
    public boolean cadastrar(List<ContaDetalhes> c) {
        query = "INSERT INTO detalhes_contas_receber (id_atividade, id_contas_receber)  value (?,?);";
        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            for (int i = 0; i < c.size(); i++) {
                pstm.setInt(1, c.get(i).getId_atividade());
                pstm.setInt(2, c.get(i).getId_contas_receber());
                pstm.addBatch();
            }

            pstm.executeBatch();
            pstm.close();

            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();
        return false;
    }

    @Override
    public boolean deletar() {
        return false;
    }

    @Override
    public boolean alterar() {
        return false;
    }

    /**
     * Função que retorna um ID de sua ultima conta registrada.
     *
     * @param idAtleta
     * @return ultimo ID
     */
    public int retornaUltimaContaDeUmAtleta(int idAtleta) {
        int ultimoId = 0;

        query = "SELECT id FROM contas_receber \n"
                + "WHERE atletas_id = " + idAtleta + " \n"
                + "ORDER by id DESC\n"
                + "limit 1";
        DB.conectar();
        
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                ultimoId = rs.getInt("id");

            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return ultimoId;

    }

    /**
     * Função que checa se existe algum detalhe de uma conta de um atleta.
     *
     * @param idUltimaContaReceberExistente Ultimo id da conta de um atleta.
     */
    public List<ContaDetalhes> checarDetalhes(int idUltimaContaReceberExistente) {

        query = "SELECT * FROM academia.detalhes_contas_receber d \n"
                + "WHERE id_contas_receber = " + idUltimaContaReceberExistente;
        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                ContaDetalhes c = new ContaDetalhes();

                c.setId(rs.getInt("id"));
                c.setId_atividade(rs.getInt("id_atividade"));
                c.setId_contas_receber(rs.getInt("id_contas_receber"));
                a.add(c);

            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return a;
    }

}
