package app.model;

import app.view.frequencia.FrequenciaManual;
import app.view.frequencia.FrequenciaView;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import libs.Validador;

/**
 *
 * @author WISE
 */
public class FrequenciaModel {

    /**
     * Gera uma ata diariamente de todos os atletas que estiverem ativo.
     *
     */
    private String query;
    private String query2;

    private Statement stm;
    private ResultSet rs;

    public List<Atleta> al = new ArrayList<Atleta>();
    public List<Frequencia> fl = new ArrayList<Frequencia>();

    private PreparedStatement pstm;
    private Atleta a;
    public ArrayList<Conta> cl = new ArrayList<Conta>();
    private Conta c;
    private ImageIcon imIcon;

    public List<Atleta> gerarAta() {
        /*Seleciona todos os atletas ativos.*/
        query = "SELECT id FROM atletas WHERE ativo = 'S'";
        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                Atleta a = new Atleta();

                a.setId(rs.getInt("id"));
                al.add(a);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        DB.desconectar();

        return al;

    }

    /**
     * FUNçÂO DE ROTINA..
     *
     * insere todos os atletas ATIVOS na ATA
     *
     * @param a
     * @return
     */
    public boolean insere(List<Atleta> a) {

        query = "INSERT INTO frequencia (atletas_id , presenca, data) VALUES (? , ? , curdate()) ";


        /*Insere todos na tabela de presença na data corrente.*/
        try {

            DB.conectar();

            pstm = DB.con.prepareStatement(query);

            for (int i = 0; i < al.size(); i++) {
                Atleta aa = al.get(i);

                pstm.setInt(1, aa.getId());
                pstm.setString(2, "F");

                pstm.addBatch();
            }

            pstm.executeBatch();
            pstm.close();
            return true;

        } catch (SQLException ex) {
            System.out.println("Falha ao inserir a frenquencia: " + ex);
        }
        DB.desconectar();
        return false;

    }

    public boolean insereFaltante(Atleta a) {
        query = "INSERT INTO frequencia (atletas_id , presenca, data) VALUES (? , ? , curdate()) ";


        /*Insere todos na tabela de presença na data corrente.*/
        try {

            DB.conectar();

            pstm = DB.con.prepareStatement(query);

            pstm.setInt(1, a.getId());
            pstm.setString(2, "P");

            pstm.execute();
            pstm.close();
            return true;

        } catch (SQLException ex) {
            System.out.println("Falha ao inserir a frenquencia: " + ex);
        }
        DB.desconectar();
        return false;
    }

    /**
     * Ao abrir o programa o sistema irá criar uma ata de freqûencia
     * automaticamente. Esta função irá checar se a ata já existe.
     *
     * @return TRUE se existir. , FALSE se não existir.
     *
     */
    public boolean verificaAta() {

        /*Verifica se existe 1 registro com a data atual.*/
        query = "SELECT data, count(data) as quantidade FROM frequencia WHERE data = curdate()  LIMIT 1;";
        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                int quantidade = rs.getInt("quantidade");
                if (quantidade > 0) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();
        return false;
    }

    /**
     * Lista a ata dos atletas ativos.
     */
    public List<Frequencia> listaAta() {

        query = "SELECT a.id , a.matricula, a.nome , f.presenca, date_format(f.data ,  '%d/%m/%Y' ) as data\n"
                + "FROM atletas a , frequencia f\n"
                + "WHERE a.id = f.atletas_id\n"
                + "AND f.data = curdate()\n"
                + "ORDER BY f.presenca DESC;";
        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                Frequencia f = new Frequencia();
                f.setId(rs.getInt("id"));
                f.setMatricula(rs.getInt("matricula"));
                f.setNome(rs.getString("nome"));
                f.setPrensenca(rs.getString("presenca"));
                f.setDataF(rs.getString("data"));
                fl.add(f);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();

        return fl;
    }

    /**
     * Função que fará um checkin do atleta. 1. selecionará o atleta pela sua
     * matricula. 1.1 irá carregar os dados dele no form.
     *
     * 2.1 Irá checar se ele está ativo. 2.1.1 se não estiver ativo. para por
     * aqui. - ACESSO NEGADO -
     *
     * 2.2 Irá checar se tem alguem pendencia 2.2.1 se não tiver pendencia -
     * ACESSO LIBERADO -
     *
     * 2.3 se tiver uma conta vencida: 2.3.1 Listará a conta vencida na tabela.
     * 2.3.2 Lançará uma msg de "ACESSO NEGADO". 2.3.3 O sistema não atualizará
     * a frequência.
     *
     * 3. Sistema atualiza a frenquencia.
     */
    public void checkin(Atleta a) {

    }

    /*Retorna 1 atleta.*/
    public Atleta retornaAtleta(int matricula) {
        query = "SELECT id as id_atleta, matricula , nome , ativo , foto  FROM atletas WHERE matricula =" + matricula;

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                a = new Atleta();
                a.setId(rs.getInt("id_atleta"));
                a.setMatricula(rs.getInt("matricula"));
                a.setNome(rs.getString("nome"));
                a.setAtivo(rs.getString("ativo"));
                a.setFoto(rs.getString("foto"));

            } else {
                return a = null;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();

        return a;
    }

    /*Checa alguma conta vencida.
     */
    public List<Conta> checarPendencia(Atleta a) {
        query = "SELECT id , atletas_id, situacao , observacao , valor_total ,  date_format(vencimento ,  '%d/%m/%Y' ) as vencimento, date_format(lancamento ,  '%d/%m/%Y' )  as lancamento "
                + "FROM contas_receber \n"
                + "WHERE atletas_id = " + a.getId() + "  \n"
                + "AND situacao = 'P'";
        // System.err.println(query);
        int quantidade = 0;
        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                c = new Conta();
                c.setId(rs.getInt("id"));
                c.setId_atleta(rs.getInt("atletas_id"));
                c.setVencimento(rs.getString("vencimento"));
                c.setValor_total(rs.getFloat("valor_total"));
                c.setLancamento(rs.getString("lancamento"));
                cl.add(c);

            }
            if (cl.isEmpty()) {
                cl.removeAll(cl);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();
       // System.err.println("N remove e retorna tudo");

        return cl;
    }

    /**
     * Antes de alterar verificar se atleta está na lista da ATA
     *
     * @param a
     * @return Retorna falso se não existir , true se existir.
     */
    public boolean verificarAteltaAta(Atleta a) {
        int count = 0;

        query = "SELECT count(atletas_id) as count "
                + "FROM frequencia "
                + "WHERE atletas_id = " + a.getId() + " "
                + "AND data = curdate()";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                count = rs.getInt("count");

                if (count > 0) {
                    return true;
                }
                return false;

            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();
        return false;
    }

    public Boolean alterarFrequencia(Atleta a) {

        query = "UPDATE frequencia SET presenca = ? "
                + "WHERE atletas_id = ? "
                + "AND data=curdate()";


        /*Insere todos na tabela de presença na data corrente.*/
        try {

            DB.conectar();

            pstm = DB.con.prepareStatement(query);

            pstm.setString(1, "P");
            pstm.setInt(2, a.getId());

            pstm.execute();
            pstm.close();
            return true;

        } catch (SQLException ex) {
            System.out.println("Falha ao atualizar a frenquencia: " + ex);
        }
        DB.desconectar();

        return false;

    }

    /*Mostrar FOTO*/
    public void mostrarImagem(String foto) {

        if (null != foto) {

            if (Validador.validaFormato(foto) == true) {
                //frequenciaView.imageLb.setIcon(new ImageIcon(foto));
                imIcon = new ImageIcon(foto);
                Image img = imIcon.getImage();
                Image newImg = img.getScaledInstance(196, 210, java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newImg);
                FrequenciaView.imageLb.setIcon(newIcon);
            } else {
                JOptionPane.showMessageDialog(null, "Formato de imagem não permitido. Formatos permitidos: jpg, jpeg, png", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void mostrarImagemManual(String foto) {

        if (null != foto) {

            if (Validador.validaFormato(foto) == true) {
                //    FrequenciaManual.imageLb.setIcon(new ImageIcon(foto));
                imIcon = new ImageIcon(foto);
                Image img = imIcon.getImage();
                Image newImg = img.getScaledInstance(196, 210, java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newImg);
                FrequenciaManual.imageLb.setIcon(newIcon);
            } else {
                JOptionPane.showMessageDialog(null, "Formato de imagem não permitido. Formatos permitidos: jpg, jpeg, png", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
