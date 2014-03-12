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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public static String[][] arrayErros = new String[15][15];
    public static int erros = 0;

    @Override
    public boolean verify(JComponent input) {
        return false;

    }

    /**
     *
     *
     * public static void validarCampoObrigatorio(JTextField campo){
     *
     * if ("".equals(Campo.getText())) { arrayErros[index_campo][0] = campoMsg +
     * ": Campo obrigatório. \n"; erros++; } }
     *
     * }
     */
    public static void valida_textField(JTextField Campo, int tamanho_maximo, boolean obrigatorio, boolean soNumeros, int index_campo, String campoMsg) {

        /*Obrigatório*/
        if (obrigatorio == true) {
            if ("".equals(Campo.getText())) {
                arrayErros[index_campo][0] = campoMsg + ": Campo obrigatório. \n";
                erros++;
            }
        }

        /*Tamanho Máximo */
        if (tamanho_maximo != 0) {
            if (Campo.getText().length() > tamanho_maximo) {
                arrayErros[index_campo][1] = campoMsg + ": Estouro de limite de caracteres. Máximo permitido: " + tamanho_maximo + " \n";
                erros++;
            }
        }

        if (soNumeros == true) {
            if (!Campo.getText().matches("[0-9]+")) {
                arrayErros[index_campo][2] = campoMsg + ": Somente números. Máximo permitido: " + tamanho_maximo + " \n";
                erros++;
            }
        }

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
        if (!strCpf.equals("")) {

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

    /**
     * Validar imagem e o formato
     *
     * @param file
     * @return *
     */
    public static Boolean validaFormato(String file) {
        int IndexFile = file.lastIndexOf(".");

        String formato = "";

        String[] formatosPermitidos = new String[]{"jpg", "jpeg", "png", ""};

        if (IndexFile > 0 && IndexFile < file.length() - 1) {
            formato = file.substring(IndexFile + 1);
        }

        for (int i = 0; i < formatosPermitidos.length; i++) {
            if (formato.equals(formatosPermitidos[i])) {
                return true;
            }

        }

        return false;
    }

    public static void cpf(String cpf) {
        if (validaCPF(cpf) == false) {
            erros++;
            arrayErros[0][0] = "CPF: inválido. \n";
        }
    }

    /**
     * Salva data no formato SQL.
     *
     * @param data
     * @param indiceCampo
     * @return
     */
    public static String data(String data, int indiceCampo) {
        String[] split;
        String dia, mes, ano;
        StringBuilder sb = new StringBuilder();
        if (data.equalsIgnoreCase("  /  /    ") || data.equals("")) {
            erros++;
            arrayErros[indiceCampo][0] = "Data: Obrigatória. \n";
            return null;
        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);

            try {

                Date date = sdf.parse(data);
                split = data.split("/");

                dia = split[0];
                mes = split[1];
                ano = split[2];

                String novaData = ano + "-" + mes + "-" + dia;
                return novaData;

            } catch (ParseException e) {

                erros++;
                arrayErros[indiceCampo][0] = "Data: Invalida. \n";
                return null;
            }

        }

    }

    /**
     * Valida 2 datas se a data de emissão é maior do que a do vencimento.
     */
    public static boolean validaData(String dataE, String dataV, int indiceCampo) {
        StringBuilder sb = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {

            Date dateE = sdf.parse(dataE);
            Date dateV = sdf.parse(dataV);

            if (dateE.after(dateV)) {
                erros++;
                arrayErros[indiceCampo][0] = "A data de emissão não pode ser maior do que a data de vencimento. \n";
                return false;
            } else {
                return true;
            }

        } catch (ParseException e) {
            System.err.println(e);

        }
        return false;

    }

    public static String dataFormatada(String data) {

        String[] split = data.split("-");

        String ano = split[0];
        String mes = split[1];
        String dia = split[2];

        String novaData = dia + "/" + mes + "/" + ano;
        return novaData;

    }

}
