package app.controller;

import app.model.atividade.AtividadeModel;
import app.model.atleta.Atleta;
import app.model.atleta.AtletaAtividade;
import app.model.atleta.AtletaModel;
import app.model.conta.Conta;
import app.model.conta.ContaModel;
import app.model.contadetalhes.ContaDetalhes;
import app.model.contadetalhes.ContaDetalhesModel;
import app.model.tablemodel.AtividadesTableModel;
import app.model.tablemodel.ContaTableModel;
import app.model.tablemodel.ContasReceberTableModel;
import app.view.atleta.AtletaView;
import app.view.contas.ContasAdminCheck;
import app.view.contas.ContasAtletasView;
import static app.view.contas.ContasAtletasView.tabelaAtividades;
import static app.view.contas.ContasAtletasView.tabelaMovimentacao;
import app.view.contas.ContasResumo;
import app.view.contas.ContasView;
import app.view.contas.ContasViewDetalhes;
import java.sql.CallableStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author WISE
 */
public class ContaController {

    private ContasAtletasView cv;
    private ContasViewDetalhes cvd;
    private ContasView csv;
    private ContaModel cm;
    private ContaTableModel ctm;

    private AtletaView av;
    private AtletaController ac;

    private AtividadeModel am;
    private AtividadesTableModel atm;
    private String query;
    private CallableStatement pstm;
    private ContasAdminCheck cac;

    /*variavel onde irá salvar se foi aceito o login de segurança para remover a conta */
    public static boolean aceito = false;
    private List<Conta> contas;
    public List<AtletaAtividade> al = new ArrayList<AtletaAtividade>();
    private List<ContaDetalhes> cdl = new ArrayList<ContaDetalhes>();

    private ContasReceberTableModel ctrm;
    private ContaDetalhesModel cdm;
    private AtletaModel atletam;
    private Atleta a;

    public void chamarView(int matricula) {
        cv = new ContasAtletasView(null, true);
        cm = new ContaModel();

        // if (cm.atletaAtivo(idAtleta) == true) {
        am = new AtividadeModel();

        List<AtletaAtividade> aa = cm.retornaAtividades(matricula);

        if (aa.size() > 0) {

            atm = new AtividadesTableModel(aa);
            ContasAtletasView.tabelaAtividades.setModel(atm);
            /*Seta os campos do form*/

            cv.nomeAtletaTxt.setText(aa.get(0).getNomeAtleta().toString());
            cv.matriculaTxt.setText(String.valueOf(aa.get(0).getMatricula()));


            /*Arruma a tabela*/
            tabelaAtividades.getColumnModel().getColumn(0).setPreferredWidth(5); //ID.
            tabelaAtividades.getColumnModel().getColumn(1).setPreferredWidth(200); //NOME.

            tabelaMovimentacao.setModel(listarContas(matricula));

            tabelaMovimentacao.getColumnModel().getColumn(0).setPreferredWidth(10); //ID.
            cv.render();
            cv.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Não existe atividades ativas para o atleta.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }

        /*  } else {
         int dialogResultado = JOptionPane.showConfirmDialog(null, "O atleta está inativo, "
         + "para faze esta operação o atleta precisa está ativo. Deseja editar o status do atleta? ");
         if (dialogResultado == JOptionPane.YES_OPTION) {
         ac = new AtletaController();
         ac.editar(matricula);
         } else {
         /**
         * ENCERRA TUDO.
                
         }
         } */
    }

    public String retornaIdConta() {
        cm = new ContaModel();
        return cm.retornaIdConta();
    }

    public boolean cadastrarConta(Conta c, int matricula) {
        cm = new ContaModel();
        AtletaModel am = new AtletaModel();
        Atleta a = am.retorna_atleta(matricula);

        if (cm.atletaAtivo(a.getId()) == true) {
            if (cm.cadastrarContaPagamento(c, matricula) == true) {
                JOptionPane.showMessageDialog(null, "Lançamento cadastrado.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                /*
                 ctm = new ContaTableModel();
                 ctm = (ContaTableModel) ContasAtletasView.tabelaMovimentacao.getModel();

                 String dataLan = null;
                 String dataVen = null;

                 try {/*Formata datas*//*

                 Date date = new SimpleDateFormat("yyyy-MM-dd").parse(c.getLancamento());
                 Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(c.getVencimento());
                 dataLan = new SimpleDateFormat("dd/MM/yyyy").format(date);
                 dataVen = new SimpleDateFormat("dd/MM/yyyy").format(date2);

                 } catch (ParseException ex) {

                 }

                 ctm.addRow(c.getId(), dataLan, dataVen, c.getValor_total(), "Aberto", 1);*/

                return true;
            }
        } else {
            int dialogResultado = JOptionPane.showConfirmDialog(null, "O atleta está inativo, "
                    + "para faze esta operação o atleta precisa está ativo. Deseja editar o status do atleta? ");
            if (dialogResultado == JOptionPane.YES_OPTION) {
                AtletaModel atletam = new AtletaModel();
                if (atletam.ativarAtelta(a) == true) {
                    JOptionPane.showMessageDialog(null, "Atleta ativado. Clique em Salvar novamente para concluir a operação.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                /**
                 * ENCERRA TUDO.
                 */
            }
        }

        return false;

    }

    public boolean quitarConta(int idConta) {
        cm = new ContaModel();
        if (cm.quitarConta(idConta) == true) {
            JOptionPane.showMessageDialog(null, "Conta quitada.", "Alerta", JOptionPane.INFORMATION_MESSAGE);

            return true;
        }
        return false;
    }

    public ContaTableModel listarContas(int matricula) {
        cm = new ContaModel();
        return cm.listarContas(matricula);

    }

    public boolean removerConta(int idConta) {
        cm = new ContaModel();
        chamarVerificador();

        if (aceito == true) {
            if (cm.removerConta(idConta) == true) {
                JOptionPane.showMessageDialog(null, "Conta removida.", "Alerta", JOptionPane.INFORMATION_MESSAGE);

                aceito = false;
                return true;
            }
        }
        return false;

    }
    /*Chama form de login
     */

    public void chamarVerificador() {
        cac = new ContasAdminCheck(null, true);
        cac.setVisible(true);
    }

    public boolean verificaAdm(String login, String senha) {
        cm = new ContaModel();

        if (cm.VerificaAdm(login, senha) == true) {
            aceito = true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuário e senha invalidos.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
        return aceito;
    }

    public boolean atualizar(Conta c) {
        cm = new ContaModel();
        if (cm.atualizarConta(c) == true) {
            JOptionPane.showMessageDialog(null, "Conta atualizada.", "Alerta", JOptionPane.INFORMATION_MESSAGE);

            return true;
        }
        return false;

    }

    public List<Conta> retornaContaSelecionada(int idConta) {
        cm = new ContaModel();
        List<Conta> cl = cm.retornaContaSelecionada(idConta);
        return cl;
    }

    public void chamarContasView(int index) {
        csv = new ContasView(null, true);
        csv.painel.setSelectedIndex(index);
        csv.setVisible(true);

    }

    /*Lista as contas pendentes do dia.*/
    public ContasReceberTableModel listaContasReceber() {
        cm = new ContaModel();

        List<Conta> cl = cm.listaContasReceber();
        ContasReceberTableModel crtm = new ContasReceberTableModel(cl);
        return crtm;
    }

    public ContasReceberTableModel listaPendencias() {
        cm = new ContaModel();

        List<Conta> cl = cm.listaPendencias();
        ContasReceberTableModel crtm = new ContasReceberTableModel(cl);
        return crtm;
    }

    public ContasReceberTableModel listaMovimentacao() {
        cm = new ContaModel();

        List<Conta> cl = cm.listaMovimentacao();
        ContasReceberTableModel crtm = new ContasReceberTableModel(cl);
        return crtm;
    }

    public void filtrar(String tipo, String pesquisaTxt, String campo, String campo2, JTable tabela) {
        cm = new ContaModel();

        contas = cm.filtrar(tipo, pesquisaTxt, campo, campo2);

        ctrm = (ContasReceberTableModel) tabela.getModel();

        ctrm.removeAll();

        if (contas.size() > 0) {

            ctrm.setList(contas);
            ctrm.fireTableDataChanged();

            /* for (int i = 0; i < contas.size(); i++) {
             ctrm.addRow(contas.get(i).getId(), contas.get(i).getMatricula(), contas.get(i).getNome(), contas.get(i).getLancamento(), contas.get(i).getVencimento(), contas.get(i).getDatapago(), contas.get(i).getValor_total(), contas.get(i).getSituacao(), i);
             ctrm.fireTableDataChanged();
             int totalRegistros = ctrm.getRowCount();
             ContasView.totalLabel.setText(String.valueOf(totalRegistros));

             }*/
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.", "Alerta.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean lancamentoRapido(String dataV, float valor, String nomeAtleta, String matriculaText, JTable tabela, String contasView) {
        int matricula = 0;
        String dataE = "";
        String dataVencimento = "";
        String mesS = "";

        atletam = new AtletaModel();
        a = new Atleta();
        cdm = new ContaDetalhesModel();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        String hoje = dateFormat.format(data);

        matricula = Integer.parseInt(matriculaText);  //essa matricula é pega para testa os detalhes.

        String[] split = dataV.split("/");

        int ano = Integer.parseInt(split[2]);
        int mes = Integer.parseInt(split[1]);
        int dia = Integer.parseInt(split[0]);

        dataE = ano + "-" + mes + "-" + dia;

        if (mes == 12) {
            mes = 01;
        } else {
            mes++;
        }

        if (mes == 02) {
            if (dia == 29 || dia == 30 || dia == 31) {
                dia = 28;
            }
        }

        if (mes == 04 || mes == 06 || mes == 9 || mes == 11) {
            if (dia == 31) {
                dia = 30;
            }
        }
        if (mes == 1 || mes == 2 || mes == 3 || mes == 4 || mes == 5 || mes == 6 || mes == 7 || mes == 8 || mes == 9) {
            mesS = "0" + mes;
        }

        dataVencimento = ano + "-" + mesS + "-" + dia;

        cm = new ContaModel();
        Conta c = new Conta();

        if (nomeAtleta == null) {
            matricula = Integer.parseInt(matriculaText);

        } else {
            matricula = cm.retornaMatriculaAtleta(nomeAtleta);
        }

        int idConta = Integer.parseInt(cm.retornaIdConta());
        c.setId(idConta);
        c.setVencimento(dataVencimento);
        c.setMatricula(matricula);
        c.setSituacao("A");
        c.setLancamento(dataE);
        c.setValor_total(valor);
        c.setObservacao("");
        c.setDatapago("0000-00-00");

        a = atletam.retorna_atleta(matricula);
        int idUltimaConta = cdm.retornaUltimaContaDeUmAtleta(a.getId());
        cdl = cdm.checarDetalhes(idUltimaConta);
        /*Destalhes do pagamento - Checa se ele tem algum histórico*/
        if (cdl.size() > 0) {/*Se tiver histórico carrega a ultima transação que ele fez e deixa como base*/

            //cdm.cadastrar(cdl);

            if (cm.cadastrarContaPagamento(c, matricula) == true) {

                int novaIdConta = cdm.retornaUltimaContaDeUmAtleta(a.getId());
                for (int i = 0; i < cdl.size(); i++) {
                    cdl.get(i).setId_contas_receber(novaIdConta);
                }
                cdm.cadastrar(cdl);

                if (nomeAtleta == null) {//se tiver null é pq ta na View ContasAtletasView
                    ctm = (ContaTableModel) tabela.getModel();

                    String dataLan = null;
                    String dataVen = null;

                    try {/*Formata datas*/

                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(c.getLancamento());
                        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(c.getVencimento());
                        dataLan = new SimpleDateFormat("dd/MM/yyyy").format(date);
                        dataVen = new SimpleDateFormat("dd/MM/yyyy").format(date2);

                    } catch (ParseException ex) {
                        Logger.getLogger(ContaController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    ctm.addRow(c.getId(), dataLan, dataVen, c.getValor_total(), "Aberto", 0);

                    return true;
                } else {//tabelas da conta View !! PAREI AQUI POR QUE NA HORA DE CONTAS A RECEBER ELE ESTÁ BUGADOO!!! nao atá atualizando o status.

                    switch (contasView) {
                        case "Receber":
                            ctrm = new ContasReceberTableModel();
                            ctrm = (ContasReceberTableModel) ContasView.contasReceberTabela.getModel();
                            ctrm.setValueAt(hoje, ContasView.linhaSelecionadaCR, 5); /*Atualiza*/

                            ctrm.setValueAt("Pago", ContasView.linhaSelecionadaCR, 7); /*Atualiza*/

                            this.chamarView(matricula);

                            return false;

                        case "Pendencias":
                            ctrm = new ContasReceberTableModel();
                            ctrm = (ContasReceberTableModel) ContasView.pendenciasTabela.getModel();
                            ctrm.setValueAt(hoje, ContasView.linhaSelecionadaP, 5); /*Atualiza*/

                            ctrm.setValueAt("Pago", ContasView.linhaSelecionadaP, 7); /*Atualiza*/

                            this.chamarView(matricula);
                            return false;

                        case "Movimentacao":
                            ctrm = new ContasReceberTableModel();
                            ctrm = (ContasReceberTableModel) ContasView.movimentacaoTabela.getModel();
                            ctrm.setValueAt(hoje, ContasView.linhaSelecionadaM, 5); /*Atualiza*/

                            ctrm.setValueAt("Pago", ContasView.linhaSelecionadaM, 7); /*Atualiza*/

                            this.chamarView(matricula);
                            return false;

                    }

                }

            } else {
                return false;
            }

        } else {
            /* Exibe uma menssagem para ele fazer uma conta detalhada manualmente para ficar salvo um histórico. */
            JOptionPane.showMessageDialog(null, "Precisa de uma conta criada na nova versão para ser processado o detalhamento da conta anterior.", "Alerta: Não foi possivel fazer um lançamento automático.", JOptionPane.INFORMATION_MESSAGE);

            switch (contasView) {
                case "Receber":
                    ctrm = new ContasReceberTableModel();
                    ctrm = (ContasReceberTableModel) ContasView.contasReceberTabela.getModel();
                    ctrm.setValueAt(hoje, ContasView.linhaSelecionadaCR, 5); /*Atualiza*/

                    ctrm.setValueAt("Pago", ContasView.linhaSelecionadaCR, 7); /*Atualiza*/

                    this.chamarView(matricula);

                    return false;

                case "Pendencias":
                    ctrm = new ContasReceberTableModel();
                    ctrm = (ContasReceberTableModel) ContasView.pendenciasTabela.getModel();
                     ctrm.setValueAt(hoje, ContasView.linhaSelecionadaP, 5); /*Atualiza*/
                    ctrm.setValueAt("Pago", ContasView.linhaSelecionadaP, 7); /*Atualiza*/

                    this.chamarView(matricula);
                    return false;

                case "Movimentacao":
                    ctrm = new ContasReceberTableModel();
                    ctrm = (ContasReceberTableModel) ContasView.movimentacaoTabela.getModel();
                     ctrm.setValueAt(hoje, ContasView.linhaSelecionadaM, 5); /*Atualiza*/
                    ctrm.setValueAt("Pago", ContasView.linhaSelecionadaM, 7); /*Atualiza*/

                    this.chamarView(matricula);
                    return false;

                case "AtletasView":
                    ctm = new ContaTableModel();
                    ctm = (ContaTableModel) ContasAtletasView.tabelaMovimentacao.getModel();
                    ctm.setValueAt("Pago", ContasAtletasView.linhaSelecionadaMov, 4); /*Atualiza*/

                    return false;

            }

        }
        return false;
    }

    public boolean reabrirConta(int contaId) {
        cm = new ContaModel();
        if (cm.reabrirConta(contaId) == true) {
            JOptionPane.showMessageDialog(null, "Conta reaberta.", "Alerta.", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            return false;
        }
    }

    public void resumoMensal(String dataMesIncio, String dataMesFim) {

        cm = new ContaModel();
        contas = cm.resumoMensal(dataMesIncio, dataMesFim);

        int qtdPg = 0, qtdVenc = 0, qtdAb = 0; /*Contadores*/

        float total = 0, vtPg = 0, vtVenc = 0, vtAb = 0; /*Valores totais*/


        for (int i = 0; i < contas.size(); i++) {

            total = total + contas.get(i).getValor_total();

            if (contas.get(i).getSituacao().equals("Pago")) { /*Quando for pago.*/

                qtdPg++;
                vtPg = vtPg + contas.get(i).getValor_total();
            }
            if (contas.get(i).getSituacao().equals("Pendente")) { /*Quando for vencido.*/

                qtdVenc++;
                vtVenc = vtVenc + contas.get(i).getValor_total();
            }
            if (contas.get(i).getSituacao().equals("Aberto")) { /*Quando for aberto.*/

                qtdAb++;
                vtAb = vtAb + contas.get(i).getValor_total();
            }
        }

        ContasResumo.registroPagos.setText(String.valueOf(qtdPg));
        ContasResumo.valorTotalRegistrosPagos.setText("R$ " + String.valueOf(vtPg));

        ContasResumo.registrosAbertos.setText(String.valueOf(qtdAb));
        ContasResumo.valorTotalregistrosAbertos.setText("R$ " + String.valueOf(vtAb));

        ContasResumo.registrosVencidos.setText(String.valueOf(qtdVenc));
        ContasResumo.valorTotalRegistrosVencidos.setText("R$ " + String.valueOf(vtVenc));

        ContasResumo.totalRegistros.setText(String.valueOf(contas.size()));
        ContasResumo.valorTotalTudo.setText("R$ " + String.valueOf(total));
        ContasResumo.valorTotalRecebido.setText("R$ " + String.valueOf(vtPg));

        ContasReceberTableModel crtm = new ContasReceberTableModel(contas);
        ContasResumo.detalhesTabela.setModel(crtm);

    }
    /*Fazer lacamento*/

    public void chamarViewLancamento() {
        cvd = new ContasViewDetalhes(null, true);
        cvd.setVisible(true);
    }

    public List<AtletaAtividade> retornaAtividadesAtleta(int matricula) {
        cm = new ContaModel();
        al = cm.retornaAtividades(matricula);
        return al;
    }

    /*Fim lancamento*/
    /**
     * Função de rotina
     */
    public void rotinaAtualizarConta() {
        cm = new ContaModel();
        cm.rotinaAtualizarPendencias();
    }

    /*Fim rotina*/
}
