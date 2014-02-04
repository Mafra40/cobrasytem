package app.model;

import app.model.tablemodel.ContaTableModel;
import app.view.contas.ContasAdminCheck;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            System.out.println(ex);
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
                + "SELECT ? as id , id as atletas_id, ? as vencimento , ? as valor_total , ? as situacao , ? as lacamento , ? as observacao  \n"
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
            System.out.println("Falha ao lista: " + ex);
        }

        DB.desconectar();
        return "1";
    }

    public ContaTableModel listarContas(int matricula) {
        query = "SELECT id, atletas_id, date_format(vencimento ,  '%d/%m/%Y' ) as vencimento,"
                + "valor_total, situacao, date_format(lancamento ,  '%d/%m/%Y' ) as lancamento, observacao "
                + "FROM contas_receber "
                + "WHERE atletas_id=(SELECT id FROM atletas WHERE matricula= " + matricula + ") ";
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
                if (rs.getString("situacao").equals("PG")) {
                    c.setSituacao("Pago");
                } else {
                    c.setSituacao("Pendente");
                }

                //c.setSituacao(rs.getString("situacao"));
                c.setLancamento(rs.getString("lancamento"));
                c.setObservacao(rs.getString("observacao"));

                cl.add(c);

            }

        } catch (SQLException ex) {
            System.out.println("Falha ao lista: " + ex);
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
            System.out.println("Erro ao deletar: " + ex);
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
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        DB.desconectar();
        return aceito;
    }

    public boolean quitarConta(int idConta) {

        query = "UPDATE `contas_receber` "
                + "SET "
                + "situacao = 'PG' \n"
                + "WHERE id=? ";

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

}
