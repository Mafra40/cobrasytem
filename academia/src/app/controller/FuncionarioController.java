package app.controller;

import app.model.Funcionario;
import app.model.FuncionarioModel;
import app.model.tablemodel.FuncionarioTableModel;
import app.view.funcionario.FuncionarioEditar;
import app.view.funcionario.FuncionarioForm;
import app.view.funcionario.FuncionarioSenha;
import app.view.funcionario.FuncionarioView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe Controladora da entidade Funcionário.
 *
 *
 * @author WISE
 */
public class FuncionarioController {

    private FuncionarioView fc;
    private FuncionarioEditar fe;
    private FuncionarioModel fm;
    private FuncionarioForm frm;
    private FuncionarioTableModel fModel;
    private Funcionario f;
    private FuncionarioSenha fs;

    public static int linhaSelecionadaTabelela;
    public static String loginPassado;
    public static String cpfAtingo;

    public void despachar() {
        fc = new FuncionarioView(null, true);
        fc.setVisible(true);
    }

    public boolean cadastro(Funcionario func) {
        fm = new FuncionarioModel();

        if (fm.checarCpfExistente(func.getCpf()) == true) {
            if (fm.checarLoginExistente(func.getLogin()) == true) {

                if (fm.cadastaFuncionario(func) == true) { //Cadastra.
                    JOptionPane.showMessageDialog(null, "Cadastro realizado.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                    fModel = (FuncionarioTableModel) FuncionarioView.funcionarioTable.getModel();
                    int count = fModel.getRowCount();
                    fModel.addRow(func.getNome().toString(), func.getCpf().toString(), count);
                    return true;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Login está em uso.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "CPF está em uso.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;

    }

    public void deletar(String cpf, int LinhaSelecionada) {
        fm = new FuncionarioModel();
        fm.deletar(cpf);
        JOptionPane.showMessageDialog(null, "Registro deletado.", "Alerta.", JOptionPane.INFORMATION_MESSAGE);
        fModel = (FuncionarioTableModel) FuncionarioView.funcionarioTable.getModel();
        fModel.removeRow(LinhaSelecionada);
    }

    /* Lança os dados no form.*/
    public void editar(String cpf) {
        fe = new FuncionarioEditar(null, true);
        fm = new FuncionarioModel();

        f = fm.retorna_funcionario(cpf);
        fe.baixoTxt.setText(f.getBairro());
        fe.cpfTxt.setText(f.getCpf());
        fe.nomeTxt.setText(f.getNome());
        fe.cepTxt.setText(f.getCep());
        fe.enderecoTxt.setText(f.getEndereco());
        fe.telefoneTxt.setText(f.getTelefone());
        fe.cidadeTxt.setText(f.getCidade());
        fe.loginTxt.setText(f.getLogin());
        loginPassado = f.getLogin();
        cpfAtingo = cpf;

        fe.setVisible(true);
    }
    /* salva a alteração */

    public boolean salvar_edicao(Funcionario f) {
        fm = new FuncionarioModel();

        if (fm.checarCpfEdicao(cpfAtingo, f.getCpf()) == true) {
            if (fm.checarLoginEdicao(loginPassado, f.getLogin()) == true) {

                if (fm.atualizar(f, cpfAtingo) == true) {

                    JOptionPane.showMessageDialog(null, "Registro atualizado.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                    fModel = (FuncionarioTableModel) FuncionarioView.funcionarioTable.getModel();

                    fModel.setValueAt(f.getNome(), linhaSelecionadaTabelela, 0);
                    fModel.setValueAt(f.getCpf(), linhaSelecionadaTabelela, 1);
                    fModel.fireTableDataChanged();
                    loginPassado = "";
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login está em uso.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "CPF está em uso.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }

    /**
     * Alterar Senha
     */
    public void alterar_senha_form() {
        fs = new FuncionarioSenha(null, true);
        fs.setVisible(true);
    }

    /*Fim senha*/
    /* Filtrar os dados*/
    public void filtrar(String valor, String filtro) {
        fm = new FuncionarioModel();
        List<Funcionario> lista = new ArrayList<Funcionario>();

        lista = fm.filtrar(filtro, valor);

        fModel = (FuncionarioTableModel) FuncionarioView.funcionarioTable.getModel();
        int count = fModel.getRowCount();

        fModel.removeAll();

        if (lista.size() > 0) {

            for (int i = 0; i < lista.size(); i++) {
                fModel.addRow(lista.get(i).getNome(), lista.get(i).getCpf(), i);
                fModel.fireTableDataChanged();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.", "Alerta.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean alterarSenha(String senhaAntiga, String senhaNova) {
        fm = new FuncionarioModel();
        
        if (fm.alterarSenha(LoginController.F_ID, LoginController.F_LOGIN, senhaAntiga, senhaNova) == true) {
            JOptionPane.showMessageDialog(null, "Senha alterada.", "Alerta.", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }

}
