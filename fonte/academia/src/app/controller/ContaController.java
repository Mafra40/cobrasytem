package app.controller;

import app.model.AtividadeModel;
import app.model.AtletaAtividade;
import app.model.Conta;
import app.model.ContaModel;
import app.model.tablemodel.AtividadesTableModel;
import app.model.tablemodel.ContaTableModel;
import app.view.atleta.AtletaView;
import app.view.contas.ContasAdminCheck;
import app.view.contas.ContasView;
import static app.view.contas.ContasView.tabelaAtividades;
import java.sql.CallableStatement;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author WISE
 */
public class ContaController {

    private ContasView cv;
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

    public void chamarView(int idAtleta, int matricula) {
        cv = new ContasView(null, true);
        cm = new ContaModel();

        if (cm.atletaAtivo(idAtleta) == true) {
            am = new AtividadeModel();

            List<AtletaAtividade> aa = cm.retornaAtividades(idAtleta);
            atm = new AtividadesTableModel(aa);
            ContasView.tabelaAtividades.setModel(atm);
            /*Seta os campos do form*/
            
            cv.nomeAtletaTxt.setText(aa.get(0).getNomeAtleta().toString());
            cv.matriculaTxt.setText(String.valueOf(aa.get(0).getMatricula()));


            /*Arruma a tabela*/
            tabelaAtividades.getColumnModel().getColumn(0).setPreferredWidth(5); //ID.
            tabelaAtividades.getColumnModel().getColumn(1).setPreferredWidth(200); //NOME.
            cv.setVisible(true);

        } else {
            int dialogResultado = JOptionPane.showConfirmDialog(null, "O atleta está inativo, "
                    + "para faze esta operação o atleta precisa está ativo. Deseja editar o status do atleta? ");
            if (dialogResultado == JOptionPane.YES_OPTION) {
                ac = new AtletaController();
                ac.editar(matricula);
            } else {
                /**
                 * ENCERRA TUDO.
                 */
            }
        }
    }

    public String retornaIdConta() {
        cm = new ContaModel();
        return cm.retornaIdConta();
    }

    public boolean cadastrarConta(Conta c, int matricula) {
        cm = new ContaModel();
        if (cm.cadastrarContaPagamento(c, matricula) == true) {
            JOptionPane.showMessageDialog(null, "Lançamento cadastrado.", "Alerta", JOptionPane.INFORMATION_MESSAGE);

            ctm = new ContaTableModel();
            ctm = (ContaTableModel) ContasView.tabelaMovimentacao.getModel();
            ctm.addRow(c.getId(), c.getLancamento(), c.getVencimento(), c.getValor_total(), "Pendente", 1);
            return true;
        }

        return false;

    }

    public boolean quitarConta(int idConta) {
        cm = new ContaModel();
        if (cm.quitarConta(idConta) == true) {
            JOptionPane.showMessageDialog(null, "Conta quitada.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
            ctm = new ContaTableModel();
            ctm = (ContaTableModel) ContasView.tabelaMovimentacao.getModel();
            ctm.setValueAt("Pago", ContasView.linhaSelecionadaMov, 4);
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
                ctm = (ContaTableModel) ContasView.tabelaMovimentacao.getModel();
                ctm.removeRow(ContasView.linhaSelecionadaMov);
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
            ctm = new ContaTableModel();
            ctm = (ContaTableModel) ContasView.tabelaMovimentacao.getModel();
            ctm.setValueAt(c.getId(), ContasView.linhaSelecionadaMov, 0);
            ctm.setValueAt(ContasView.dataEmissaoTxt.getText(), ContasView.linhaSelecionadaMov, 1);
            ctm.setValueAt(ContasView.dataVencimentoTxt.getText(), ContasView.linhaSelecionadaMov, 2);
            ctm.setValueAt(c.getValor_total(), ContasView.linhaSelecionadaMov, 3);
            return true;
        }
        return false;

    }

    public List<Conta> retornaContaSelecionada(int idConta) {
        cm = new ContaModel();
        List<Conta> cl = cm.retornaContaSelecionada(idConta);
        return cl;
    }
}
