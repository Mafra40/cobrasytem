
package app.controller;

import app.model.Funcionario;
import app.model.FuncionarioModel;
import app.model.tablemodel.FuncionarioTableModel;
import app.view.funcionario.FuncionarioEditar;
import app.view.funcionario.FuncionarioView;
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
    private FuncionarioTableModel fModel;
    private Funcionario f;
    
    public void despachar(){
        fc = new FuncionarioView(null, true);
        fc.setVisible(true);
    }
    
    
    public void  cadastro(Funcionario func){
       fm = new FuncionarioModel();
       
       
       if (FuncionarioModel.isCPF(func.getCpf()) == false ){
           fm.cadastaFuncionario(func); //Cadastra.
          //atualiza a tabela.
           fm.Listar();
           fm.atualizar_tabela();
            
        
       }else {
            JOptionPane.showMessageDialog(null, "CPF inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
       }
       
       
       
      
      
    }
    
    
    public void deletar(String cpf){
        fm = new FuncionarioModel();
        fm.deletar(cpf);
        JOptionPane.showMessageDialog(null, "Deletado", "Sucess", JOptionPane.ERROR_MESSAGE);
        fm.atualizar_tabela();
    }
    
    
    public void editar( String cpf){
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
         
         fe.setVisible(true);
    }
    
    
    
}
