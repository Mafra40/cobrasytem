package app.model.conta;

import app.model.DB;
import app.model.funcionario.Funcionario;
import app.model.login.LoginModel;
import app.model.atleta.AtletaAtividade;
import app.model.tablemodel.ContaTableModel;
import app.view.contas.ContasAdminCheck;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import libs.Logs;

/**
 *
 * @author WISE
 */
public class ContaModel {

    private String query;
    private Statement stm;
    private ResultSet rs;

    public List<AtletaAtividade> at = new ArrayList<AtletaAtividade>();
    public List<Conta> cl = new ArrayList<Conta>();
    private PreparedStatement pstm;
    private MessageDigest md;

    /*variaves de checagem*/
    private boolean aceito;
    private Funcionario f;
    private Conta c;

    /**
     * Verifica se o atleta é ativo ou não.
     *
     * @return
     */
    public boolean atletaAtivo(int idAtleta) {
        query = "SELECT ativo FROM atletas "
                + "WHERE id=" + idAtleta;

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                String ativo = rs.getString("ativo");
                if (ativo.equals("N")) {
                    return false;
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        DB.desconectar();
        return true;
    }

    /**
     * Retorna a lista de atividades de um atleta
     *
     * @param matricula
     * @return
     */
    public List<AtletaAtividade> retornaAtividades(int matricula) {
        query = "SELECT at.id as atividade_id, at.nome as nome_atividade, at.ativo, at.valor, at.ativo , a.nome as nome_atleta ,  a.matricula  , a.id as atleta_id \n"
                + "FROM atividade at, atletas a, atletas_has_atividade\n"
                + "WHERE a.id = atletas_id\n"
                + "AND atividade_id = at.id\n"
                + "AND at.ativo = 'S'\n"
                + "AND atletas_id = (SELECT id FROM atletas WHERE matricula=" + matricula + ")";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                AtletaAtividade a = new AtletaAtividade();

                a.setAtividadeId(rs.getInt("atividade_id"));
                a.setNomeAtividade(rs.getString("nome_atividade"));
                a.setValorAtividade(rs.getFloat("valor"));
                a.setMatricula(rs.getInt("matricula"));
                a.setNomeAtleta(rs.getString("nome_atleta"));
                at.add(a);

            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return at;
    }

    /**
     * Cadastra uma transação de conta.
     *
     * INSERT-SELECT
     *
     * @param c
     * @param matricula
     * @return
     */
    public boolean cadastrarContaPagamento(Conta c, int matricula) {

        query = "INSERT INTO `contas_receber` \n"
                + "SELECT ? as id , id as atletas_id, ? as vencimento , ? as valor_total , ? as situacao , ? as lacamento , ? as observacao , ? as datapago  \n"
                + "FROM atletas \n"
                + "WHERE matricula=" + matricula;

        DB.conectar();
        try {

            pstm = DB.con.prepareStatement(query);
            pstm.setInt(1, c.getId());
            pstm.setString(2, c.getVencimento());
            pstm.setFloat(3, c.getValor_total());
            pstm.setString(4, c.getSituacao());
            pstm.setString(5, c.getLancamento());
            pstm.setString(6, c.getObservacao());
            pstm.setString(7, "0000-00-00");

            pstm.execute();
            pstm.close();
            return true;

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);

        }
        DB.desconectar();
        return false;

    }

    /**
     * Retorna um último id valido para uma conta
     *
     * @return
     */
    public String retornaIdConta() {
        int id;
        query = "SELECT id+1 as id FROM `contas_receber` order by id desc limit 1";
        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                id = rs.getInt("id");
                return String.valueOf(id);
            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return "1";
    }
    
  
    
    

    public ContaTableModel listarContas(int matricula) {
        query = "SELECT id, atletas_id, date_format(vencimento ,  '%d/%m/%Y' ) as vencimento,"
                + "valor_total, situacao, date_format(lancamento ,  '%d/%m/%Y' ) as lancamento, observacao "
                + "FROM contas_receber "
                + "WHERE atletas_id=(SELECT id FROM atletas WHERE matricula= " + matricula + ") "
                + "ORDER BY vencimento desc "
                + "";
        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                Conta c = new Conta();

                c.setId(rs.getInt("id"));
                c.setId_atleta(rs.getInt("atletas_id"));
                c.setVencimento(rs.getString("vencimento"));
                c.setValor_total(rs.getFloat("valor_total"));

                switch (rs.getString("situacao")) {
                    case "PG":
                        c.setSituacao("Pago");
                        break;
                    case "P":
                        c.setSituacao("Pendente");
                        break;
                    case "A":
                        c.setSituacao("Aberto");
                        break;
                }

                //c.setSituacao(rs.getString("situacao"));
                c.setLancamento(rs.getString("lancamento"));
                c.setObservacao(rs.getString("observacao"));

                cl.add(c);

            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return new ContaTableModel(cl);
    }

    /**
     * Remove 1 conta do sistema. Porém para fazer isso VerificaAdm tem q
     * retornar true;
     *
     * @param idConta
     * @return
     */
    public boolean removerConta(int idConta) {

        query = "DELETE FROM contas_receber "
                + "WHERE id=?";

        DB.conectar();

        try {
            pstm = DB.con.prepareCall(query);
            pstm.setInt(1, idConta);
            pstm.execute();
            pstm.close();

            return true;

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return false;

    }

    /**
     * Verifica se realmente é o administrador que está deletando a
     * movimentação.
     *
     * @param login
     * @param senha
     * @return
     */
    public boolean VerificaAdm(String login, String senha) {

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(senha.getBytes(), 0, senha.length());
        senha = new BigInteger(1, md.digest()).toString(16);

        query = "SELECT senha, login FROM funcionario WHERE "
                + "senha='" + senha + "'"
                + " AND login='" + login + "' "
                + " AND master = 'S'";

        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (!rs.isBeforeFirst()) {
                return aceito = false;
            }

            if (rs != null && rs.next()) {
                f = new Funcionario(rs.getString("login").toString(), rs.getString("senha").toString());
            }

            if (f.getSenha().equals(senha) && f.getLogin().equalsIgnoreCase(login)) {
                ContasAdminCheck cav = new ContasAdminCheck(null, false);
                cav.setVisible(false);
                cav.dispose();
                return aceito = true;
            } else {
                return aceito = false;
            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return aceito;
    }

    public boolean quitarConta(int idConta) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); //data de hoje
        String data = dateFormat.format(date);
        
        query = "UPDATE `contas_receber` "
                + "SET "
                + "situacao = 'PG', \n"
                + "datapago = '" + data
                + "' \n"
                + "WHERE id = ? ";
        //System.err.println(idConta);
        //System.err.println(query);
        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setInt(1, idConta);

            pstm.execute();
            pstm.close();

            return true;

        } catch (SQLException ex) {
            System.out.println("Erro ao quitar: " + ex);
        }
        DB.desconectar();
        return false;

    }

    /**
     * Reabre uma conta.
     *
     * @param contaId
     * @return
     */
    public boolean reabrirConta(int contaId) {

        query = "UPDATE `contas_receber` "
                + "SET "
                + "situacao = 'A' , datapago = '0000-00-00' \n"
                + "WHERE id=? ";

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setInt(1, contaId);

            pstm.execute();
            pstm.close();

            return true;

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }
        DB.desconectar();
        return false;

    }

    /**
     * Retorna 1 conta para ser listada no form. para poder usar o
     * atualizarConta
     *
     * @param idConta
     * @return
     */
    public List<Conta> retornaContaSelecionada(int idConta) {

        query = "SELECT id, atletas_id, date_format(vencimento ,  '%d/%m/%Y' ) as vencimento,"
                + "valor_total, situacao, date_format(lancamento ,  '%d/%m/%Y' ) as lancamento, observacao "
                + " FROM contas_receber WHERE id=" + idConta;
        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                c = new Conta(rs.getInt("id"),
                        rs.getInt("atletas_id"),
                        rs.getString("vencimento"),
                        rs.getFloat("valor_total"),
                        rs.getString("situacao"),
                        rs.getString("lancamento"),
                        rs.getString("observacao"));
                cl.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Falha ao lista: " + ex);
        }

        DB.desconectar();
        return cl;
    }

    public boolean atualizarConta(Conta c) {
        query = "UPDATE `contas_receber` "
                + "SET "
                + "vencimento = ? , \n"
                + "valor_total = ? , \n"
                + "lancamento = ? , \n"
                + "observacao = ?  \n"
                + "WHERE id=? ";

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setString(1, c.getVencimento());
            pstm.setFloat(2, c.getValor_total());
            pstm.setString(3, c.getLancamento());
            pstm.setString(4, c.getObservacao());
            pstm.setInt(5, c.getId());

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
     * Lista todas a contas pendentes do dia.
     *
     */
    public List<Conta> listaContasReceber() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); //data de hoje
        String data = dateFormat.format(date);

        query = "SELECT cr.id, cr.atletas_id ,  date_format(cr.vencimento ,  '%d/%m/%Y' ) as vencimento,  cr.valor_total , cr.situacao, date_format(cr.lancamento ,  '%d/%m/%Y' ) as lancamento  , cr.observacao ,date_format(cr.datapago ,  '%d/%m/%Y' ) as datapago ,\n"
                + "a.nome , a.matricula  FROM contas_receber cr, atletas a \n"
                + "WHERE a.id = cr.atletas_id\n"
                + "AND cr.vencimento = '" + data + "' "
                + "ORDER BY  cr.vencimento desc "
                + "LIMIT 300";
        DB.conectar();
       // System.err.println(query);
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                c = new Conta(rs.getInt("id"),
                        rs.getInt("atletas_id"),
                        rs.getString("vencimento"),
                        rs.getFloat("valor_total"),
                        rs.getString("situacao"),
                        rs.getString("lancamento"),
                        rs.getString("observacao"));

                c.setNome(rs.getString("nome"));
                c.setMatricula(rs.getInt("matricula"));

                switch (rs.getString("situacao")) {
                    case "PG":
                        c.setSituacao("Pago");
                        break;
                    case "P":
                        c.setSituacao("Pendente");
                        break;
                    case "A":
                        c.setSituacao("Aberto");
                        break;
                }

                c.setDatapago(rs.getString("datapago"));

                cl.add(c);
            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return cl;
    }

    /*Lista todas as pendencias.*/
    public List<Conta> listaPendencias() {

        query = "SELECT cr.id, cr.atletas_id ,  date_format(cr.vencimento ,  '%d/%m/%Y' ) as vencimento,  cr.valor_total , cr.situacao, date_format(cr.lancamento ,  '%d/%m/%Y' ) as lancamento  , cr.observacao ,date_format(cr.datapago ,  '%d/%m/%Y' ) as datapago ,\n"
                + "a.nome, a.matricula  FROM contas_receber cr, atletas a \n"
                + "WHERE a.id = cr.atletas_id\n"
                + "AND situacao = 'P' "
                + "ORDER BY  cr.vencimento desc "
                + "LIMIT 300";
        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                c = new Conta(rs.getInt("id"),
                        rs.getInt("atletas_id"),
                        rs.getString("vencimento"),
                        rs.getFloat("valor_total"),
                        rs.getString("situacao"),
                        rs.getString("lancamento"),
                        rs.getString("observacao"));

                c.setNome(rs.getString("nome"));
                c.setMatricula(rs.getInt("matricula"));

                switch (rs.getString("situacao")) {
                    case "PG":
                        c.setSituacao("Pago");
                        break;
                    case "P":
                        c.setSituacao("Pendente");
                        break;
                    case "A":
                        c.setSituacao("Aberto");
                        break;
                }

                c.setDatapago(rs.getString("datapago"));

                cl.add(c);
            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return cl;
    }

    /*Lista movimentação de todas as contas*/
    public List<Conta> listaMovimentacao() {
        query = "SELECT cr.id, cr.atletas_id ,  date_format(cr.vencimento ,  '%d/%m/%Y' ) as vencimento,  cr.valor_total , cr.situacao, date_format(cr.lancamento ,  '%d/%m/%Y' ) as lancamento  , cr.observacao ,date_format(cr.datapago ,  '%d/%m/%Y' ) as datapago ,\n"
                + "a.nome, a.matricula  FROM contas_receber cr, atletas a \n"
                + "WHERE a.id = cr.atletas_id\n"
                + "ORDER BY  cr.vencimento desc "
                + "LIMIT 300";
        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                c = new Conta(rs.getInt("id"),
                        rs.getInt("atletas_id"),
                        rs.getString("vencimento"),
                        rs.getFloat("valor_total"),
                        rs.getString("situacao"),
                        rs.getString("lancamento"),
                        rs.getString("observacao"));

                c.setNome(rs.getString("nome"));
                c.setMatricula(rs.getInt("matricula"));

                switch (rs.getString("situacao")) {
                    case "PG":
                        c.setSituacao("Pago");
                        break;
                    case "P":
                        c.setSituacao("Pendente");
                        break;
                    case "A":
                        c.setSituacao("Aberto");
                        break;
                }
                c.setDatapago(rs.getString("datapago"));

                cl.add(c);
            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return cl;
    }

    /*ROTINA QUE ATUALIZARÁ OS CONTAS QUE ESTÃO ABERTA PARA VENCIDAS
     CASO PASSE DA DATA DE VENCIMENTO.*/
    public void rotinaAtualizarPendencias() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); //data de hoje
        String data = dateFormat.format(date);

        query
                = "UPDATE `contas_receber` "
                + "SET situacao = 'P', "
                + "situacao = CASE WHEN lancamento > '" + data + "' THEN 'A' ELSE 'P' END " //não deixa pendente se a data de lançamento for > que a data atual.
                + "WHERE '" + data + "' not between lancamento AND vencimento " //datas não estiver entre o vencimento e lançamento
                + "AND situacao = 'A' ";//onde todas as contas estiverem como aberto.

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.execute();
            pstm.close();

        } catch (SQLException ex) {
            System.out.println("ROTINA CONTA: Falha ao atualizar as contas " + ex);
        }
        DB.desconectar();
    }

    public List<Conta> filtrar(String tipo, String pesquisaTxt, String campo, String campo2) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); //data de hoje
        String data = dateFormat.format(date);

        if (tipo.equals("receber")) {

            query = "SELECT cr.id, cr.atletas_id ,  date_format(cr.vencimento ,  '%d/%m/%Y' ) as vencimento,  cr.valor_total , cr.situacao, date_format(cr.lancamento ,  '%d/%m/%Y' ) as lancamento  , cr.observacao ,date_format(cr.datapago ,  '%d/%m/%Y' ) as datapago ,\n"
                    + "a.nome, a.matricula  FROM contas_receber cr, atletas a \n"
                    + "WHERE a.id = cr.atletas_id\n"
                    + "AND vencimento = '" + data + "'"
                    + "AND cr.situacao ='" + campo2 + "' "
                    + "AND a.nome LIKE'%" + pesquisaTxt + "%' "
                    + "ORDER BY  cr.vencimento desc "
                    + "LIMIT 300";

            if (campo2.equals("NULL")) {
                query = "SELECT cr.id, cr.atletas_id ,  date_format(cr.vencimento ,  '%d/%m/%Y' ) as vencimento,  cr.valor_total , cr.situacao, date_format(cr.lancamento ,  '%d/%m/%Y' ) as lancamento  , cr.observacao ,date_format(cr.datapago ,  '%d/%m/%Y' ) as datapago ,\n"
                        + "a.nome , a.matricula  FROM contas_receber cr, atletas a \n"
                        + "WHERE a.id = cr.atletas_id\n"
                        + "AND vencimento = '" + data + "'"
                        + "AND a.nome LIKE'%" + pesquisaTxt + "%' "
                        + "ORDER BY  cr.vencimento desc "
                        + "LIMIT 300 ";
            }
        }

        if (tipo.equals("movimento")) {
            String campoSql = "";
            if (campo.equals("nome")) {
                campoSql = "AND a.nome LIKE'%" + pesquisaTxt + "%' ";
            }
            if (campo.equals("matricula")) {
                campoSql = "AND a.matricula = " + Integer.parseInt(pesquisaTxt) + " ";
            }
            query = "SELECT cr.id, cr.atletas_id ,  date_format(cr.vencimento ,  '%d/%m/%Y' ) as vencimento,  cr.valor_total , cr.situacao, date_format(cr.lancamento ,  '%d/%m/%Y' ) as lancamento  , cr.observacao ,date_format(cr.datapago ,  '%d/%m/%Y' ) as datapago ,\n"
                    + "a.nome , a.matricula  FROM contas_receber cr, atletas a \n"
                    + "WHERE a.id = cr.atletas_id\n"
                    + "AND cr.situacao ='" + campo2 + "' "
                    + campoSql
                    + "ORDER BY  cr.vencimento desc "
                    + "LIMIT 300 ";

            if (campo2.equals("NULL")) {
                query = "SELECT cr.id, cr.atletas_id ,  date_format(cr.vencimento ,  '%d/%m/%Y' ) as vencimento,  cr.valor_total , cr.situacao, date_format(cr.lancamento ,  '%d/%m/%Y' ) as lancamento  , cr.observacao ,date_format(cr.datapago ,  '%d/%m/%Y' ) as datapago ,\n"
                        + "a.nome , a.matricula  FROM contas_receber cr, atletas a \n"
                        + "WHERE a.id = cr.atletas_id\n"
                        + campoSql
                        + "ORDER BY  cr.vencimento desc "
                        + "LIMIT 300";
            }
        }

        if (tipo.equals("pendente")) {
            String campoSql = "";
            if (campo.equals("nome")) {
                campoSql = "AND a.nome LIKE'%" + pesquisaTxt + "%' ";
            }
            if (campo.equals("matricula")) {
                campoSql = "AND a.matricula = " + Integer.parseInt(pesquisaTxt) + " ";
            }

            query = "SELECT cr.id, cr.atletas_id ,  date_format(cr.vencimento ,  '%d/%m/%Y' ) as vencimento,  cr.valor_total , cr.situacao, date_format(cr.lancamento ,  '%d/%m/%Y' ) as lancamento  , cr.observacao ,date_format(cr.datapago ,  '%d/%m/%Y' ) as datapago ,\n"
                    + "a.nome , a.matricula  FROM contas_receber cr, atletas a \n"
                    + "WHERE a.id = cr.atletas_id\n"
                    + "AND cr.situacao ='" + campo2 + "' "
                    + campoSql
                    + "ORDER BY  cr.vencimento desc "
                    + "LIMIT 300 ";

        }

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                c = new Conta(rs.getInt("id"),
                        rs.getInt("atletas_id"),
                        rs.getString("vencimento"),
                        rs.getFloat("valor_total"),
                        rs.getString("situacao"),
                        rs.getString("lancamento"),
                        rs.getString("observacao"));

                c.setNome(rs.getString("nome"));
                c.setMatricula(rs.getInt("matricula"));
                switch (rs.getString("situacao")) {
                    case "PG":
                        c.setSituacao("Pago");
                        break;
                    case "P":
                        c.setSituacao("Pendente");
                        break;
                    case "A":
                        c.setSituacao("Aberto");
                        break;
                }

                c.setDatapago(rs.getString("datapago"));

                cl.add(c);
            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return cl;
    }

    /*Retorna matricula atleta pelo nome*/
    public int retornaMatriculaAtleta(String nome) {
        int matricula = 0;
        query = "SELECT matricula FROM atletas WHERE nome='" + nome + "'";
        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                matricula = rs.getInt("matricula");
            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return matricula;
    }

    public List<Conta> resumoMensal(String dataMesInicio, String dataMesFim) {

        query = "SELECT cr.id, cr.atletas_id ,  date_format(cr.vencimento ,  '%d/%m/%Y' ) as vencimento,  cr.valor_total , cr.situacao, date_format(cr.lancamento ,  '%d/%m/%Y' ) as lancamento  , cr.observacao ,date_format(cr.datapago ,  '%d/%m/%Y' ) as datapago ,\n"
                + "                a.nome, a.matricula  FROM contas_receber cr, atletas a \n"
                + "                WHERE a.id = cr.atletas_id\n"
                + "                AND cr.vencimento BETWEEN '" + dataMesInicio + "' AND '" + dataMesFim + "'\n"
                + "                ORDER BY  cr.vencimento asc";
        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                c = new Conta(rs.getInt("id"),
                        rs.getInt("atletas_id"),
                        rs.getString("vencimento"),
                        rs.getFloat("valor_total"),
                        rs.getString("situacao"),
                        rs.getString("lancamento"),
                        rs.getString("observacao"));

                c.setNome(rs.getString("nome"));
                c.setMatricula(rs.getInt("matricula"));

                switch (rs.getString("situacao")) {
                    case "PG":
                        c.setSituacao("Pago");
                        break;
                    case "P":
                        c.setSituacao("Pendente");
                        break;
                    case "A":
                        c.setSituacao("Aberto");
                        break;
                }
                c.setDatapago(rs.getString("datapago"));

                cl.add(c);
            }

        } catch (SQLException ex) {
            Logs LogError = new Logs();
            LogError.gravarLogError("" + ex);
        }

        DB.desconectar();
        return cl;

    }
}
