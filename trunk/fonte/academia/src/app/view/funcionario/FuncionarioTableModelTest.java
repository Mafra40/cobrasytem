package app.view.funcionario;


 
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
 
import javax.swing.JFrame;
import javax.swing.JTable;
import libs.TableModel;
import app.model.Funcionario;
import app.model.FuncionarioModel;
 
public class FuncionarioTableModelTest extends JFrame {
 
    private JTable tblFuncionarios;
    private TableModel tableModel;
     
    public FuncionarioTableModelTest() {
        super("FuncionarioTableModelTest");
        initialize();
    }
 
    private void initialize() {
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(getTblFuncionarios());
    }
 
    private JTable getTblFuncionarios() {
        if (tblFuncionarios == null) {
            tblFuncionarios = new JTable();
            tblFuncionarios.setModel(getTableModel());
        }
        return tblFuncionarios;
    }
 
    private TableModel getTableModel() {
        if (tableModel == null) {
            tableModel = new TableModel(criaFuncioario());
        }
        return tableModel;
    }
 
    // cria uma lista com 5 sócios meramente ilustrativos
   private List<Funcionario> criaFuncioario() {
        List<Funcionario> funcs = new ArrayList<Funcionario>();
        
        FuncionarioModel FM = new FuncionarioModel();
         FM.Listar();
        
         for(Funcionario f : FM.funcs){
              Funcionario funcionario = new Funcionario();
              funcionario.setNome(f.getNome());
              funcionario.setEndereco(f.getCpf());
             funcs.add(funcionario);
              }
       
        return funcs;
    }
 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FuncionarioTableModelTest().setVisible(true);
            }
        });
    }
 
}