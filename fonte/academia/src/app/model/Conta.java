package app.model;

/**
 *
 * @author WISE
 */
public class Conta extends Atleta{

    private int id;
    private int id_atleta;
    private String vencimento;
    private float valor_total;
    private String situacao;
    private String lancamento;
    private String observacao;
    private String datapago;
    
    private String nome;

    public Conta()  {

    }

    public Conta(int id, int id_atleta, String vencimento, float valor_total, String situacao, String lancamento, String observacao) {
        this.id = id;
        this.id_atleta = id_atleta;
        this.vencimento = vencimento;
        this.valor_total = valor_total;
        this.situacao = situacao;
        this.lancamento = lancamento;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_atleta() {
        return id_atleta;
    }

    public void setId_atleta(int id_atleta) {
        this.id_atleta = id_atleta;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDatapago() {
        return datapago;
    }

    public void setDatapago(String datapago) {
        this.datapago = datapago;
    }

  
    
    
}
