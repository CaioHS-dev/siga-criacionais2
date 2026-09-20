package siga;

public interface FabricaBanco {
    Conexao criarConexao();
    Comando criarComando();
}