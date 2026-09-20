# Documentação siga-criacionais

## 1. Abstract Factory

* No método `conectar`, o código usava `if` e `else` para criar a conexão e o comando do banco de dados separados.
* Nada no código impede que alguém instancie uma ConexaoMySQL e um ComandoPostgreSQL.
* Criei uma interface (`FabricaBanco`) e uma classe para cada tipo (`FabricaMySQL` e `FabricaPostgreSQL`) que implementa e sobrescreve os métodos da interface. Agora, quem cria a conexão e o comando juntos é essa interface. Se eu chamo a classe do MySQL, ela me devolve tudo do MySQL. Fica impossível misturar as coisas por engano.

---

## 2. Builder

* O método `montarConsulta` recebe 7 argumentos seguidos: String tabela, String filtro, String ordenacao, int limite, int offset, int timeoutSegundos, boolean somenteAtivos.   
* O código ficava ilegível. Quando olhava no `Main`, via números como `50, 0, 30, true` e ninguém entendia o que cada número fazia sem abrir o outro arquivo para conferir. Além disso, era possível trocar a ordem de dois números sem querer e não acusaria erros.
* Criei o `ConsultaBuilder`, agora eu digo claramente o que quero com métodos que dão para ler em português: `.comFiltro()`, `.comLimite()`, `.somenteAtivos()`, etc. Se eu não precisar de um campo, eu simplesmente não chamo, em vez de ficar passando `null` ou `0` manualmente.

---

## 3. Singleton

* Qualquer parte do programa podia mandar um `new AcessoDados()` e criar quantos pontos de acesso ao banco quisesse.
* Deixar um monte de conexão e gerenciador solto pelo sistema consome memória à toa e faz a gente perder o controle de quem tá mexendo no banco. Tinha que ser uma porta de entrada só para todo mundo.
* Tranquei o construtor dele deixando `private` para ninguém de fora conseguir dar `new`. No lugar disso, guardei uma cópia única dentro da própria classe e criei o método `getInstancia()`. Agora, o sistema inteiro compartilha sempre a mesma instância controlada.

---

# Diagrama de Classes UML

```mermaid
classDiagram
    direction TB

    %% SINGLETON
    class AcessoDados {
        -AcessoDados instancia$
        -AcessoDados()
        +getInstancia()$ AcessoDados
        +conectar(fabrica: FabricaBanco) void
    }

    %% ABSTRACT FACTORY
    class FabricaBanco {
        <<interface>>
        +criarConexao() Conexao
        +criarComando() Comando
    }

    class FabricaMySQL {
        +criarConexao() Conexao
        +criarComando() Comando
    }

    class FabricaPostgreSQL {
        +criarConexao() Conexao
        +criarComando() Comando
    }

    %% PRODUTOS
    class Conexao {
        <<interface>>
        +abrir() void
    }

    class Comando {
        <<interface>>
        +executar(sql: String) void
    }

    class ConexaoMySQL { +abrir() void }
    class ComandoMySQL { +executar(sql: String) void }
    class ConexaoPostgreSQL { +abrir() void }
    class ComandoPostgreSQL { +executar(sql: String) void }

    %% BUILDER
    class ConsultaBuilder {
        -String tabela
        -String filtro
        -String ordenacao
        -int limite
        -int offset
        -int timeoutSegundos
        -boolean somenteAtivos
        +ConsultaBuilder(tabela: String)
        +comFiltro(filtro: String) ConsultaBuilder
        +comOrdenacao(ordenacao: String) ConsultaBuilder
        +comLimite(limite: int) ConsultaBuilder
        +comOffset(offset: int) ConsultaBuilder
        +comTimeout(timeout: int) ConsultaBuilder
        +somenteAtivos() ConsultaBuilder
        +construir() String
    }

    %% RELACIONAMENTOS ABSTRACT FACTORY
    FabricaBanco <|.. FabricaMySQL
    FabricaBanco <|.. FabricaPostgreSQL

    Conexao <|.. ConexaoMySQL
    Conexao <|.. ConexaoPostgreSQL

    Comando <|.. ComandoMySQL
    Comando <|.. ComandoPostgreSQL

    FabricaMySQL ..> ConexaoMySQL : produz
    FabricaMySQL ..> ComandoMySQL : produz
    FabricaPostgreSQL ..> ConexaoPostgreSQL : produz
    FabricaPostgreSQL ..> ComandoPostgreSQL : produz

    %% RELACIONAMENTOS SINGLETON E DEPENDÊNCIAS
    AcessoDados --> AcessoDados : instancia única
    AcessoDados ..> FabricaBanco : consome
    AcessoDados ..> Conexao : usa
    AcessoDados ..> Comando : usa