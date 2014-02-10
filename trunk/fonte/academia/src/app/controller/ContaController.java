package app.controller;

import app.model.AtividadeModel;
import app.model.Atleta;
import app.model.AtletaAtividade;
import app.model.AtletaModel;
import app.model.Conta;
import app.model.ContaModel;
import app.model.tablemodel.AtividadesTableModel;
import app.model.tablemodel.ContaTableModel;
import app.model.tablemodel.ContasReceberTableModel;
import app.view.atleta.AtletaView;
import app.view.contas.ContasAdminCheck;
import app.view.contas.ContasAtletasView;
import static app.view.contas.ContasAtletasView.tabelaAtividades;
import app.view.contas.ContasView;
import java.sql.CallableStatement;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author WISE
 */
public class ContaController {

    private ContasAtletasView cv;
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
    private ContasReceberTableModel ctrm;

    public void chamarView(int idAtleta, int matricula) {
        cv = new ContasAtletasView(null, true);
        cm = new ContaModel();

        // if (cm.atletaAtivo(idAtleta) == true) {
        am = new AtividadeModel();

        List<AtletaAtividade> aa = cm.retornaAtividades(idAtleta);

        if (aa.size() > 0) {
            atm = new AtividadesTableModel(aa);
            ContasAtletasView.tabelaAtividades.setModel(atm);
            /*Seta os campos do form*/

            cv.nomeAtletaTxt.setText(aa.get(0).getNomeAtleta().toString());
            cv.matriculaTxt.setText(String.valueOf(aa.get(0).getMatricula()));


            /*Arruma a tabela*/
            tabelaAtividades.getColumnModel().getColumn(0).setPreferredWidth(5); //ID.
            tabelaAtividades.getColumnModel().getColumn(1).setPreferredWidth(200); //NOME.
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

                ctm = new ContaTableModel();
                ctm = (ContaTableModel) ContasAtletasView.tabelaMovimentacao.getModel();
                ctm.addRow(c.getId(), c.getLancamento(), c.getVencimento(), c.getValor_total(), "Aberto", 1);
                return true;
            }
        } else {
            int dialogResultado = JOptionPane.showConfirmDialog(null, "O atleta está inativo, "
                    + "para faze esta operação o atleta precisa está ativo. Deseja editar o status do atleta? ");
            if (dialogResultado == JOptionPane.YES_OPTION) {
                AtletaModel atletam = new AtletaModel();
                if (atletam.ativarAtelta(a) == true) {
                    JOptionPane.showMessageDialog(null, "Atleta ativado.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
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

            for (int i = 0; i < contas.size(); i++) {
                ctrm.addRow(contas.get(i).getId(), contas.get(i).getNome(),contas.get(i).getLancamento(), contas.get(i).getVencimento(), contas.get(i).getDatapago(), contas.get(i).getValor_total(), contas.get(i).getSituacao(), i);
                ctrm.fireTableDataChanged();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.", "Alerta.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Função de rotina
     */
    public void rotinaAtualizarConta() {
        cm = new ContaModel();
        cm.rotinaAtualizarPendencias();
    }

    /*Fim rotina*/
}
