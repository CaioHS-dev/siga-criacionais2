package siga;

public class FabricaMySQL implements FabricaBanco {
    @Override
    public Conexao criarConexao() {
        return new ConexaoMySQL();
    }

    @Override
    public Comando criarComando() {
        return new ComandoMySQL();
    }
}