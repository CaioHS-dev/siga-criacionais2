package siga;

/**
 * Ponto de entrada do SIGA (código INICIAL da atividade da Aula 6).
 *
 * Demonstra o acesso a dados nos dois fornecedores. O programa FUNCIONA, mas:
 * (1) nada garante que conexão e comando sejam do mesmo fornecedor;
 * (2) a montagem da consulta usa um método com parâmetros demais;
 * (3) qualquer parte do sistema pode instanciar seu próprio AcessoDados.
 * Sua tarefa é aplicar Abstract Factory, Builder e Singleton.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Atividade de Padrões Criacionais (Refatorado) ===\n");
        //Singleton: AcessoDados acesso = new AcessoDados();
        AcessoDados acesso = AcessoDados.getInstancia();
        
        //Abstract Factory: Conexao conexao = new ConexaoMySQL(); Comando comando = new ComandoMySQL();
        System.out.println("--- Conectando ao MySQL ---");
        acesso.conectar(new FabricaMySQL());

        System.out.println("\n--- Conectando ao PostgreSQL ---");
        acesso.conectar(new FabricaPostgreSQL());

        System.out.println();

        // PROBLEMA 2 em ação: o que significa cada número nesta chamada?
        /*String consulta = acesso.montarConsulta("aluno", "curso = 'DSM'", "nome",
                50, 0, 30, true);
        System.out.println("Consulta montada: " + consulta);*/

        /*System.out.println("\nObserve: nada garante que conexão e comando sejam do mesmo");
        System.out.println("fornecedor (Abstract Factory resolve); o método de consulta tem");
        System.out.println("parâmetros demais (Builder resolve); e não há controle de");
        System.out.println("instância única do acesso ao banco (Singleton resolve).");*/

        //Builder: String consulta = acesso.montarConsulta("aluno", "curso = 'DSM'", "nome", 50, 0, 30, true);
        String consulta = new ConsultaBuilder("aluno")
                .comFiltro("curso = 'DSM'")
                .comOrdenacao("nome")
                .comLimite(50)
                .comTimeout(30)
                .somenteAtivos()
                .construir();

        System.out.println("Consulta montada via Builder:\n" + consulta);
    }
}
