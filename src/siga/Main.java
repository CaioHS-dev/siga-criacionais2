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
        System.out.println("=== SIGA - Atividade de Padrões Criacionais (código inicial) ===\n");

        AcessoDados acesso = new AcessoDados();
        acesso.conectar("MYSQL");
        System.out.println();
        acesso.conectar("POSTGRESQL");

        System.out.println();
        // PROBLEMA 2 em ação: o que significa cada número nesta chamada?
        String consulta = acesso.montarConsulta("aluno", "curso = 'DSM'", "nome",
                50, 0, 30, true);
        System.out.println("Consulta montada: " + consulta);

        System.out.println("\nObserve: nada garante que conexão e comando sejam do mesmo");
        System.out.println("fornecedor (Abstract Factory resolve); o método de consulta tem");
        System.out.println("parâmetros demais (Builder resolve); e não há controle de");
        System.out.println("instância única do acesso ao banco (Singleton resolve).");
    }
}
