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

package src.controller;

import javax.swing.JOptionPane;
import src.model.login.LoginModel;
import src.view.login.LoginView;

/**
 *
 * @author WISE 
 */
public class LoginController {
  private LoginView lv;
    
    public void  LoginView(){
        lv = new LoginView(null, true);
        lv.setVisible(true);
    }
    
    
    public void Login(String login, String senha){
        LoginModel lm = new LoginModel();
       
        if (lm.fazerLogin(login , senha) == true){
           JOptionPane.showMessageDialog(null, "Bem Vindo "+login, ".", JOptionPane.PLAIN_MESSAGE);
        }else {
           JOptionPane.showMessageDialog(null, "Acesso negado.", "Inane error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    
}
