/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import app.model.tablemodel.FuncionarioTableModel;
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
public class FuncionarioModel {

    private Funcionario func;
    private PreparedStatement pstm;
    private Statement stm;
    private ResultSet rs;
    private String query;
    public List<Funcionario> funcs = new ArrayList<Funcionario>();
    private FuncionarioTableModel fModel;
    private MessageDigest md;
    private MessageDigest md2;

    /*Lista o usuários do sistema. FUncionários.
     */
    public List<Funcionario> Listar() {
        query = "SELECT cpf, nome, cidade, bairro, login, telefone FROM funcionario";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setCpf(rs.getString("cpf"));
                func.setNome(rs.getString("nome"));
                // func.setCidade(rs.getString("cidade"));
                func.setBairro(rs.getString("bairro"));
                func.setLogin(rs.getString("login"));
                func.setTelefone(rs.getString("telefone"));
                funcs.add(func);

            }

        } catch (SQLException ex) {
            System.out.println("ERRO");
        }

        DB.desconectar();

        return funcs;
    }

    public boolean cadastaFuncionario(Funcionario func) {

        /**
         * MD5 *
         */
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FuncionarioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(func.getSenha().getBytes(), 0, func.getSenha().length());
        func.setSenha(new BigInteger(1, md.digest()).toString(16));
        /**
         * *
         */

        query = "INSERT INTO `academia`.`funcionario`"
                + "(`cpf`,\n"
                + "`nome`,\n"
                + "`endereco`,\n"
                + "`cidade`,\n"
                + "`bairro`,\n"
                + "`cep`,\n"
                + "`senha`,\n"
                + "`telefone`,\n"
                + "`login`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?)";

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setString(1, func.getCpf());
            pstm.setString(2, func.getNome());
            pstm.setString(3, func.getEndereco());
            pstm.setString(4, func.getCidade());
            pstm.setString(5, func.getBairro());
            pstm.setString(6, func.getCep());
            pstm.setString(7, func.getSenha());
            pstm.setString(8, func.getTelefone());
            pstm.setString(9, func.getLogin());

            pstm.execute();
            pstm.close();
            return true;

        } catch (SQLException ex) {
            System.err.println(ex);

        }
        DB.desconectar();
        return false;

    }

    public boolean atualizar(Funcionario f, String cpf) {

        query = "UPDATE academia.funcionario SET cpf = ? , nome = ? , endereco = ? , cidade = ? , bairro = ? , cep = ? , telefone = ? , login = ? "
                + " WHERE cpf = ? ";
        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setString(1, f.getCpf());
            pstm.setString(2, f.getNome());
            pstm.setString(3, f.getEndereco());
            pstm.setString(4, f.getCidade());
            pstm.setString(5, f.getBairro());
            pstm.setString(6, f.getCep());
            pstm.setString(7, f.getTelefone());
            pstm.setString(8, f.getLogin());
            pstm.setString(9, cpf);

            pstm.execute();
            pstm.close();

            return true;

        } catch (SQLException ex) {

            System.err.println(ex);
        }
        DB.desconectar();
        return false;
    }

    /**
     * Deleta um funcionário.
     *
     * @param cpf
     */
    public void deletar(String cpf) {
        query = "DELETE FROM funcionario "
                + "WHERE cpf=?";

        DB.conectar();

        try {
            pstm = DB.con.prepareCall(query);
            pstm.setString(1, cpf);
            pstm.execute();
            pstm.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        DB.desconectar();

    }

    public Funcionario retorna_funcionario(String cpf) {

        query = "SELECT * FROM funcionario "
                + " WHERE cpf='" + cpf + "'";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                func = new Funcionario();
                func.setCpf(rs.getString("cpf"));
                func.setNome(rs.getString("nome"));
                func.setCidade(rs.getString("cidade"));
                func.setBairro(rs.getString("bairro"));
                func.setCep(rs.getString("cep"));
                func.setEndereco(rs.getString("endereco"));
                func.setTelefone(rs.getString("telefone"));
                func.setLogin(rs.getString("login"));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();
        return func;
    }

    public Boolean checarCpfEdicao(String cpfAtingo, String cpf) {
        if (cpfAtingo == null ? cpf == null : cpfAtingo.equals(cpf)) {
            return true;
        }
        return this.checarCpfExistente(cpf) != false;

    }

    public Boolean checarLoginEdicao(String loginAtingo, String login) {

        if (loginAtingo == null ? login == null : loginAtingo.equals(login)) {
            return true;
        }
        return this.checarLoginExistente(login) != false;

    }

    public List<Funcionario> filtrar(String filtro, String valor) {
        query = "SELECT * FROM funcionario "
                + " WHERE " + filtro + " LIKE '%" + valor + "%'";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                func = new Funcionario();
                func.setCpf(rs.getString("cpf"));
                func.setNome(rs.getString("nome"));
                func.setCidade(rs.getString("cidade"));
                func.setBairro(rs.getString("bairro"));
                func.setCep(rs.getString("cep"));
                func.setEndereco(rs.getString("endereco"));
                func.setTelefone(rs.getString("telefone"));
                func.setLogin(rs.getString("login"));
                funcs.add(func);

            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();
        return funcs;

    }

    /**
     * Valida CPF
     *
     * @param CPF
     * @return
     */
    public boolean checarCpfExistente(String CPF) {

        query = "SELECT cpf FROM funcionario WHERE cpf ='" + CPF + "'";
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

    public boolean checarLoginExistente(String login) {

        query = "SELECT login FROM funcionario WHERE login ='" + login + "'";
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
     * Alterar senha de um funcionário.
     * @param id
     */
    public boolean alterarSenha(int id, String login, String senhaAtinga, String senhaNova) {
        /**
         * MD5 *
         */
        try {
            md = MessageDigest.getInstance("MD5");
            md2 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(senhaAtinga.getBytes(), 0, senhaAtinga.length());
        String senhaA = new BigInteger(1, md.digest()).toString(16);

        md2.update(senhaNova.getBytes(), 0, senhaNova.length());
        String senhaN = new BigInteger(1, md2.digest()).toString(16);

        query = "UPDATE funcionario SET senha=? "
                + " WHERE id = ? "
                + "AND login = ? "
                + "AND senha = ? ";

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setString(1, senhaN);
            pstm.setInt(2, id);
            pstm.setString(3, login);
            pstm.setString(4, senhaA);

            pstm.execute();
            pstm.close();

            return true;

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();
        return false;
    }

}
