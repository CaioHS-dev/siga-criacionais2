package siga;

/**
 * Sistema de Gestão Acadêmica Simplificado (SIGA)
 * Técnicas de Programação II - Fatec de Porto Ferreira
 * Atividade prática da Aula 6 (padrões criacionais).
 *
 * Contrato de conexão com o banco de dados. Junto com Comando, forma a
 * "família" de objetos de acesso a dados que deve pertencer a um mesmo
 * fornecedor (MySQL ou PostgreSQL). Interface pronta — é um "Produto".
 */
public interface Conexao {
    void abrir();
}
