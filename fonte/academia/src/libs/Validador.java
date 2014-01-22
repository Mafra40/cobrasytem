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

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author WISE
 */
public class Validador extends InputVerifier {

    /*indices dos arrays 
     public final static int CPF = 1;
     public final static int NOME = 0;
     public final static int ENDERECO = 2;
     public final static int CIDADE = 3;
     public final static int BAIRRO = 4;
     public final static int CEP = 5;
     public final static int SENHA = 6;
     public final static int TELEFONE = 7;
     public final static int LOGIN = 8;
     */
    public static String[][] arrayErros = new String[15][10];
    public static int erros = 0;

    @Override
    public boolean verify(JComponent input) {
        return false;

    }

    public static void valida_textField(JTextField Campo, int tamanho_maximo, boolean obrigatorio, int index_campo, String campoMsg) {

        /*Obrigatório*/
        if (obrigatorio == true) {
            if ("".equals(Campo.getText())) {
                arrayErros[index_campo][0] = campoMsg + ": Campo obrigatório. \n";
                erros++;
            }
        }

        /*Tamanho Máximo */
        if (Campo.getText().length() > tamanho_maximo) {
            arrayErros[index_campo][1] = campoMsg + ": Estouro de limite de caracteres. Máximo permitido: " + tamanho_maximo + " \n";
            erros++;
        }

        //arrayErros[index_campo][2] = "";
    }

    public static void validaSenha(JPasswordField senha, JPasswordField rSenha, int tamanho_maximo, boolean obrigatorio, int index_campo, String campoMsg) {

        /*Obrigatório*/
        if (obrigatorio == true) {
            if ("".equals(senha.getText()) || "".equals(rSenha.getText())) {
                arrayErros[index_campo][0] = campoMsg + ": Campo obrigatório. \n";
                erros++;
            }
        }

        if (!senha.getText().equals(rSenha.getText())) {
            arrayErros[index_campo][1] = campoMsg + ":  As senhas estão diferentes. \n";
            erros++;
        }

        /*Tamanho Máximo */
        if (senha.getText().length() > tamanho_maximo || rSenha.getText().length() > tamanho_maximo) {
            arrayErros[index_campo][2] = campoMsg + ": Estouro de limite de caracteres. Máximo permitido: " + tamanho_maximo + " \n";
            erros++;
        }

    }

    /**
     * Valida um cpf.
     *
     * @param strCpf
     * @return
     */
    public static boolean validaCPF(String strCpf) {

        int iDigito1Aux = 0, iDigito2Aux = 0, iDigitoCPF;
        int iDigito1 = 0, iDigito2 = 0, iRestoDivisao = 0;
        String strDigitoVerificador, strDigitoResultado;

        if (!strCpf.substring(0, 1).equals("")) {

            if (strCpf.equals("111.111.111-11") || strCpf.equals("222.222.222-22") || strCpf.equals("333.333.333-33") || strCpf.equals("444.444.444-44") || strCpf.equals("555.555.555-55") || strCpf.equals("666.666.666-66") || strCpf.equals("777.777.777-77") || strCpf.equals("888.888.888-88") || strCpf.equals("999.999.999-99")) {
                erros++;
                arrayErros[0][0] = "CPF: inválido. \n";
                return false;

            }
            try {
                strCpf = strCpf.replace('.', ' ');
                strCpf = strCpf.replace('-', ' ');
                strCpf = strCpf.replaceAll(" ", "");
                for (int iCont = 1; iCont < strCpf.length() - 1; iCont++) {
                    iDigitoCPF = Integer.valueOf(strCpf.substring(iCont - 1, iCont)).intValue();
                    iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
                    iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
                }
                iRestoDivisao = (iDigito1Aux % 11);
                if (iRestoDivisao < 2) {
                    iDigito1 = 0;
                } else {
                    iDigito1 = 11 - iRestoDivisao;
                }
                iDigito2Aux += 2 * iDigito1;
                iRestoDivisao = (iDigito2Aux % 11);
                if (iRestoDivisao < 2) {
                    iDigito2 = 0;
                } else {
                    iDigito2 = 11 - iRestoDivisao;
                }
                strDigitoVerificador = strCpf.substring(strCpf.length() - 2, strCpf.length());
                strDigitoResultado = String.valueOf(iDigito1) + String.valueOf(iDigito2);

                return strDigitoVerificador.equals(strDigitoResultado);
            } catch (Exception e) {
                erros++;
                arrayErros[0][0] = "CPF: inválido. \n";
                return false;
            }
        } else {
            arrayErros[0][0] = "CPF: Campo obrigatório \n";
            erros++;

            return false;
        }
    }

}
