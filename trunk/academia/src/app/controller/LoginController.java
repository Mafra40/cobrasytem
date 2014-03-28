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
package app.controller;

import static app.controller.LoginController.F_LOGIN;
import app.model.login.LoginModel;
import app.view.login.LoginView;
import app.view.principal.MenuView;
import javax.swing.JOptionPane;

/**
 *
 * @author WISE
 */
public class LoginController {

    private LoginView lv;

    public static int F_ID;
    public static String F_LOGIN;

    public void LoginView() {
        lv = new LoginView(null, true);
        lv.setVisible(true);
    }

    public boolean Login(String login, String senha) {
        LoginModel lm = new LoginModel();

        if (lm.fazerLogin(login, senha) == true) {

            F_LOGIN = login;
            F_ID = lm.retornaIdFuncionario(login);

            MenuView mf = new MenuView();
            mf.setVisible(true); //abre menu principal
            return true;
        } else {

            JOptionPane.showMessageDialog(null, "Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

}
