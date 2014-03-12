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

import app.model.tablemodel.AtletaTableModel;
import app.view.atleta.AtletaEditar;
import conf.Global;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import libs.Validador;

/**
 *
 * @author WISE
 */
public class AtletaModel {

    private String query;
    private PreparedStatement pstm;
    private Statement stm;
    private ResultSet rs;
    private AtletaTableModel atm;

    public ArrayList<Atleta> al = new ArrayList<Atleta>();
    private Atleta a;

    private String file; /*imagem*/

    private int status;
    private AtletaModel am;
    private ImageIcon imIcon;

    public boolean cadastrar(Atleta a) {

        query = "INSERT INTO `academia`.`atletas`"
                + "(`matricula`,\n"
                + "`rg`,\n"
                + "`nome`,\n"
                + "`nascimento`,\n"
                + "`endereco`,\n"
                + "`cidade`,\n"
                + "`bairro`,\n"
                + "`cep`,\n"
                + "`sexo`,\n"
                + "`ativo`,\n"
                + "`observacao`,\n"
                + "`telefone`,\n"
                + "`foto`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setInt(1, a.getMatricula());
            pstm.setString(2, a.getRg());
            pstm.setString(3, a.getNome());
            pstm.setString(4, a.getData_nascimento());
            pstm.setString(5, a.getEndereco());
            pstm.setString(6, a.getCidade());
            pstm.setString(7, a.getBairro());
            pstm.setString(8, a.getCep());
            pstm.setString(9, a.getSexo());
            pstm.setString(10, a.getAtivo());
            pstm.setString(11, a.getObservacao());
            pstm.setString(12, a.getTelefone());
            pstm.setString(13, a.getFoto());

            pstm.execute();
            pstm.close();

            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();
        return false;
    }

    public String escolherImagem() {

        JFileChooser fc = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG arquivos de imagem.", new String[]{"jpg", "jpeg", "png"});

        fc.setFileFilter(filter);
        fc.addChoosableFileFilter(filter);
        status = fc.showOpenDialog(null);

        if (status == 1) {
            return "1"; //Imagem não selecionada.
        } else {

            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    file = fc.getSelectedFile().getPath();
                    // System.err.println(file);
                    return file;
                } catch (Exception e) {
                    System.err.println(e);
                }

            }
        }
        return null;

    }

    public ImageIcon setaImagem(String arquivoImagem) {
        imIcon = new ImageIcon(arquivoImagem);
        Image img = imIcon.getImage();
        Image newImg = img.getScaledInstance(196, 210, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        return newIcon;

    }

    public String salvarImagem(int matricula, String file) {
        String localGravacao = "";
        if (!"1".equals(file)) {
            try {
                BufferedImage bm = ImageIO.read(new File(file));
                File gravar = new File(Global.DIRETORIO_FOTOS_ATLETAS + "\\" + matricula + ".png");
                localGravacao = Global.DIRETORIO_FOTOS_ATLETAS + "\\" + matricula + ".png";
                if (!gravar.exists()) {

                    boolean success;
                    success = (new File(Global.DIRETORIO_FOTOS_ATLETAS)).mkdirs();

                }
                ImageIO.write(bm, "png", gravar);

            } catch (Exception e) {
                System.err.println("erro G: " + e);
            }
        }
        return localGravacao;

    }

    public String gerarMatricula() {
        int matricula;
        query = "SELECT matricula+1 as matricula FROM `academia`.`atletas` order by matricula desc limit 1";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                matricula = rs.getInt("matricula");
                return String.valueOf(matricula);
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();
        return "1";

    }

    public boolean checarMatriculaExistente(int matricula) {

        query = "SELECT matricula FROM atletas WHERE matricula =" + matricula;
        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);
            if (rs.isBeforeFirst()) {
                return false;
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();

        return true;
    }

    public boolean checarRgExistente(String rg) {
        query = "SELECT rg FROM atletas WHERE rg =" + rg;
        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);
            if (rs.isBeforeFirst()) {
                return false;
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();

        return true;
    }

    /**
     * FIm cadastro
     *
     * @return *
     */
    public AtletaTableModel listar() {

        query = "SELECT matricula, nome, rg, ativo FROM atletas ORDER BY nome";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                Atleta a = new Atleta();
                a.setMatricula(rs.getInt("matricula"));
                a.setNome(rs.getString("nome"));
                a.setRg(rs.getString("rg"));
                a.setAtivo(rs.getString("ativo"));
                al.add(a);

            }

        } catch (SQLException ex) {
            System.out.println("ERRO");
        }

        DB.desconectar();
        return atm = new AtletaTableModel(al);

    }

    /**
     * Edição
     *
     * @param matricula
     * @return
     */
    public Atleta retorna_atleta(int matricula) {
        query = "SELECT * FROM atletas "
                + " WHERE matricula=" + matricula;

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                a = new Atleta();
                a.setId(rs.getInt("id"));
                a.setMatricula(rs.getInt("matricula"));
                a.setRg(rs.getString("rg"));
                a.setNome(rs.getString("nome"));
                a.setData_nascimento(rs.getString("nascimento"));
                a.setEndereco(rs.getString("endereco"));
                a.setCidade(rs.getString("cidade"));
                a.setBairro(rs.getString("bairro"));
                a.setCep(rs.getString("cep"));
                a.setSexo(rs.getString("sexo"));
                a.setAtivo(rs.getString("ativo"));
                a.setObservacao(rs.getString("observacao"));
                a.setTelefone(rs.getString("telefone"));
                a.setFoto(rs.getString("foto"));

            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();
        return a;
    }

    public void mostrarImagem(String foto) {

        if (null != foto) {

            if (Validador.validaFormato(foto) == true) {
                AtletaEditar.imageLb.setIcon(new ImageIcon(foto));
                imIcon = new ImageIcon(foto);
                Image img = imIcon.getImage();
                Image newImg = img.getScaledInstance(196, 210, java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newImg);
                AtletaEditar.imageLb.setIcon(newIcon);

            } else {
                JOptionPane.showMessageDialog(null, "Formato de imagem não permitido. Formatos permitidos: jpg, jpeg, png", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public Boolean checarAtleta(int matriculaAntiga, int matricula) {

        if (matriculaAntiga == matricula) {
            return true;
        }
        return this.checarMatriculaExistente(matricula) != false;

    }

    public Boolean chegarAtletaRg(String rg, String rgAtingo) {

        if (rgAtingo == null ? rg == null : rgAtingo.equals(rg)) {
            return true;
        }
        return this.checarRgExistente(rg) != false;

    }

    public boolean editar(Atleta a, int matricula) {

        query = "UPDATE `atletas` "
                + "SET "
                + "matricula = ? , \n"
                + "rg = ? , \n"
                + "nome = ? , \n"
                + "nascimento = ? , \n"
                + "endereco = ? , \n"
                + "cidade = ? , \n"
                + "bairro = ? , \n"
                + "cep = ? , \n"
                + "sexo = ? , \n"
                + "ativo = ? , \n"
                + "observacao = ? , \n"
                + "telefone = ? , \n"
                + "foto = ? \n "
                + "WHERE matricula=? ";

        // System.err.println(query);
        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setInt(1, a.getMatricula());
            pstm.setString(2, a.getRg());
            pstm.setString(3, a.getNome());
            pstm.setString(4, a.getData_nascimento());
            pstm.setString(5, a.getEndereco());
            pstm.setString(6, a.getCidade());
            pstm.setString(7, a.getBairro());
            pstm.setString(8, a.getCep());
            pstm.setString(9, a.getSexo());
            pstm.setString(10, a.getAtivo());
            pstm.setString(11, a.getObservacao());
            pstm.setString(12, a.getTelefone());
            pstm.setString(13, a.getFoto());
            pstm.setInt(14, matricula);

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
     * Fim edição
     */
    public boolean deletar(int matricula) {

        query = "DELETE FROM atletas "
                + "WHERE matricula=?";

        DB.conectar();

        try {
            pstm = DB.con.prepareCall(query);
            pstm.setInt(1, matricula);
            pstm.execute();
            pstm.close();

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não é recomendado deletar um atleta que já possua movimentação no sistema. Recomendado: Desativar o atleta em 'Editar'", "Alerta", JOptionPane.ERROR_MESSAGE);
        }

        DB.desconectar();
        return false;
    }

    /*Filtrar pesquisa*/
    public List<Atleta> filtrar(String filtro, String valor) {
        query = "SELECT matricula, nome, rg, ativo FROM atletas "
                + " WHERE " + filtro + " LIKE '%" + valor + "%'";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                a = new Atleta();
                a.setMatricula(rs.getInt("matricula"));
                a.setNome(rs.getString("nome"));
                a.setRg(rs.getString("rg"));
                a.setAtivo(rs.getString("ativo"));

                al.add(a);

            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();
        return al;
    }

    /**
     * Ativa o atleta se ele estiver inativo na hora de fazer um lançamento de
     * contas.
     */
    public boolean ativarAtelta(Atleta a) {
        query = "UPDATE `atletas` "
                + "SET "
                + "ativo = 'S' "
                + "WHERE matricula=? ";

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setInt(1, a.getMatricula());

            pstm.execute();
            pstm.close();

            return true;

        } catch (SQLException ex) {
            System.out.println("Falha ao ativar na hora de lançar nota: " + ex);
        }
        DB.desconectar();
        return false;
    }

    /*Retorna se o atleta está ativo ou nao*/
    public String retornaAtivo(int mat) {
        query = "SELECT ativo FROM atletas "
                + " WHERE matricula = " + mat;
        //System.err.println(query);

        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                a = new Atleta();
                a.setAtivo(rs.getString("ativo"));

            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return a.getAtivo();
    }

    /**
     * Esta rotina desativa um atleta caso ele esteja com uma conta PENDENTE no
     * sistema.
     */
    public void rotinaSelecionaAtleta() {
        query = "SELECT a.id , a.nome  FROM atletas a, contas_receber c\n"
                + "WHERE a.id = c.atletas_id\n"
                + "AND c.situacao = \"P\";";
        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                a = new Atleta();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                al.add(a);
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        query = "UPDATE `atletas` "
                + "SET "
                + "ativo = 'N' "
                + "WHERE id=? ";

        try {
            pstm = DB.con.prepareStatement(query);

            for (int i = 0; i < al.size(); i++) {
                Atleta aa = al.get(i);
                pstm.setInt(1, aa.getId());

                pstm.addBatch();
            }

            pstm.executeBatch();
            pstm.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        DB.desconectar();

    }
}
