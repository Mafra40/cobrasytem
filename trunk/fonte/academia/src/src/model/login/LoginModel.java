package src.model.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.model.DB;
import src.model.Funcionario;

/**
 *
 * @author WISE
 */
public class LoginModel {

    private Statement stm;
    private ResultSet rs;
    Funcionario f;
    private boolean logado = false;

    public Boolean fazerLogin(String login, String senha) {
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

}
