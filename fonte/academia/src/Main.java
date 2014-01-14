/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package academia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.controller.LoginController;
import src.model.Funcionario;
import src.model.FuncionarioModel;

/**
 *
 * @author WISE
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LoginController start = new LoginController();
        start.LoginView();
        
    }
    
}
