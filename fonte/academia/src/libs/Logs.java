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
package libs;

import static app.view.atleta.AtletaView.matricula;
import conf.Global;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author WISE
 */
public class Logs {

    public void gravarLogError(String msg) {
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh = null;

        try {
            try {

                File gravar = new File(Global.DIRETORIO_LOGS + "\\Erros.log");

                if (!gravar.exists()) {

                    boolean success;
                    success = (new File(Global.DIRETORIO_LOGS)).mkdirs();

                }

                // This block configure the logger with handler and formatter
                fh = new FileHandler(System.getProperty("user.home") + "\\Logs\\Erros.log");
            } catch (IOException ex) {
                Logger.getLogger(Logs.class.getName()).log(Level.SEVERE, null, ex);
            }

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages  
            logger.info(msg);

        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    /**
     * Erros de SQL
     *
     * @param ex
     */
    public void gravarLogErrorSql(SQLException ex) {

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh = null;

        try {
            try {
                // This block configure the logger with handler and formatter
                fh = new FileHandler("user.home\\Logs\\Erros.log");
            } catch (IOException ex2) {
                Logger.getLogger(Logs.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean Diretorio = (new File(Global.DIRETORIO_FOTOS_ATLETAS)).mkdirs();

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages  
            logger.info("" + ex);

        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

}
