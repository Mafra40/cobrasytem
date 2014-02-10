package app.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.view.login.LoginView;

/**
 *
 * @author WISE
 */
public class LoginModel {

    private Statement stm;
    private ResultSet rs;
    Funcionario f;
    private boolean logado = false;
    MessageDigest md;

    public Boolean fazerLogin(String login, String senha) {
        /**
         * MD5 *
         */
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(senha.getBytes(), 0, senha.length());
        senha = new BigInteger(1, md.digest()).toString(16);

        String query = "SELECT senha, login FROM funcionario WHERE "
                + "senha='" + senha + "'"
                + " and login='" + login + "' ";

        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

            if (!rs.isBeforeFirst()) {
                return logado = false;
            }

            if (rs != null && rs.next()) {
                f = new Funcionario(rs.getString("login").toString(), rs.getString("senha").toString());
            }

            if (f.getSenha().equals(senha) && f.getLogin().equalsIgnoreCase(login)) {
                LoginView lv = new LoginView(null, true);
                lv.setVisible(false);
                lv.dispose();
                return logado = true;
            } else {
                return logado = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        DB.desconectar();
        return logado;

    }

    public int retornaIdFuncionario(String login) {
        String query = "SELECT id FROM funcionario WHERE login='" + login + "'";

        DB.conectar();
        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);

             if (rs.next()) {
                int id = rs.getInt("id");
                return id;

            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        DB.desconectar();
        return 0;
    }

}
