/*
 * 
 */
package conf;

/**
 *
 * @author WISE
 *
 * Class de configurações de variavies globais.
 */
public class Global {

    /**
     * @var tema
     *
     * Armazena o tema do interface do usuário.
     */
    public static final String TEMA = "Windows";
    
    
    /**
     * Aplicação
     */
    
    public static final String NOME = "COBRA SYSTEM";
    public static final String VERSAO = "1.0.7";
    

    /**
     * Configuração com banco de dados Mysql.
     *
     *jr123cobra
     */
    public static final String USUARIO = "root";
    public static final String SENHA = "ro160290ro";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://127.0.0.1/academia";

    
    
    /**
     * Configuração de diretorio;
     * 
     * 
     * 
     */
    
    
    public static final String DIRETORIO_FOTOS_ATLETAS = System.getProperty("user.home")+"\\Atletas";
    public static final String DIRETORIO_LOGS = System.getProperty("user.home")+"\\Logs";
}
