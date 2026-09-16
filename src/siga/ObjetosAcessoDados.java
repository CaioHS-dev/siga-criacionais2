package siga;

/**
 * Implementações concretas dos objetos de acesso a dados, por fornecedor.
 * (Código inicial — prontas. São os "Produtos Concretos".)
 *
 * Reunidas em um arquivo para facilitar a leitura do material inicial; são
 * classes package-private de apoio. Na sua refatoração, mantenha um arquivo
 * por classe pública, conforme as regras de produção da disciplina.
 */

// --- Fornecedor MySQL ---
class ConexaoMySQL implements Conexao {
    public void abrir() { System.out.println("[MySQL] conexão aberta"); }
}
class ComandoMySQL implements Comando {
    public void executar(String sql) { System.out.println("[MySQL] executando: " + sql); }
}

// --- Fornecedor PostgreSQL ---
class ConexaoPostgreSQL implements Conexao {
    public void abrir() { System.out.println("[PostgreSQL] conexão aberta"); }
}
class ComandoPostgreSQL implements Comando {
    public void executar(String sql) { System.out.println("[PostgreSQL] executando: " + sql); }
}
