/**
 * Classe de configuração de conexão ao banco MySQL
 *
 *
 */
package src.model;

import conf.Global;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author WISE
 */
public class DB {

    public static Connection con;

    /**
     * Abre conexão.
     */
    public static void conectar() {
        try {
            Class.forName(Global.DRIVER);
            con = DriverManager.getConnection(Global.URL, Global.USUARIO, Global.SENHA);

        } catch (ClassNotFoundException ex) {
            /*erro no driver de conexão*/
            JOptionPane.showMessageDialog(null, "Erro ao escolher a classe de conexão do banco de dados. A aplicação não poderá ser inicializada contate ao suporte do sistema.", "Inane error", JOptionPane.ERROR_MESSAGE);
            System.exit(2);
        } catch (SQLException ex) {
            /*Erro SQL, url, senha, usuário, */
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados. A aplicação não poderá ser inicializada contate ao suporte do sistema.", "Inane error", JOptionPane.ERROR_MESSAGE);
            System.exit(2);
        }
    }

    /**
     * Fecha conexão.
     */
    public static void desconectar() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao desconectar ao banco de dados. ", "Inane error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
