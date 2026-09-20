# SIGA — Atividade de Padrões Criacionais (código inicial)

**Técnicas de Programação II (TP2) · Aula 6** — CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Este é o **código inicial** da atividade prática da Aula 6. Ele contém, de forma **proposital**, três problemas de design que você deverá resolver aplicando, em conjunto, os padrões **Abstract Factory**, **Builder** e **Singleton**. O programa compila e executa — o problema não é o funcionamento, e sim a coerência, a legibilidade e o controle da criação de objetos.

## Estrutura do projeto

```
siga-criacionais/
└── src/
    └── siga/
        ├── Conexao.java              (interface — produto; pronta)
        ├── Comando.java              (interface — produto; pronta)
        ├── ObjetosAcessoDados.java   (implementações MySQL e PostgreSQL; prontas)
        ├── AcessoDados.java          (contém os três problemas a refatorar)
        └── Main.java                 (demonstra os problemas em execução)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior (`java -version` para verificar).

```bash
# 1. Compilar (a saída vai para a pasta "bin")
javac -d bin src/siga/*.java

# 2. Executar
java -cp bin siga.Main
```

## Os problemas propositais

| Local | Problema | Padrão que resolve |
|---|---|---|
| `AcessoDados.conectar` | Cria conexão e comando por `if` e `new` separados, sem garantir que sejam do mesmo fornecedor (dá para misturar MySQL e PostgreSQL). | **Abstract Factory** |
| `AcessoDados.montarConsulta` | Método com muitos parâmetros opcionais (construtor telescópico), ilegível e propenso a erro de ordem. | **Builder** |
| `AcessoDados` | Nada garante um único ponto de acesso ao banco no sistema. | **Singleton** |

## Sua tarefa

Siga as etapas da ficha de atividade prática:

1. **Analisar** o código inicial e identificar a possibilidade de misturar fornecedores e o método de consulta telescópico.
2. **Abstract Factory:** criar uma fábrica abstrata (por exemplo, `FabricaBanco`) com `FabricaMySQL` e `FabricaPostgreSQL`, cada uma produzindo uma `Conexao` e um `Comando` **do mesmo fornecedor**. O `AcessoDados` passa a receber uma fábrica e criar a família coerente a partir dela.
3. **Builder:** criar um `ConsultaBuilder` com métodos nomeados e encadeáveis para os parâmetros opcionais (`comFiltro`, `comOrdenacao`, `comLimite`, etc.) e um `construir()` que devolve a consulta. Substitui o método telescópico.
4. **Singleton:** transformar o `AcessoDados` em um Singleton, com construtor privado, instância estática e método de acesso.
5. **Desenhar** o diagrama de classes da solução (fábrica de banco, produtos, builder e acesso), evidenciando os três padrões.

## Critério de sucesso

Ao final: (a) deve ser **impossível** combinar uma conexão de um fornecedor com um comando de outro; (b) a montagem da consulta deve ser **legível**, com passos nomeados; e (c) deve existir **um único** ponto de acesso ao banco, obtido de forma controlada.

## Padrão de entrega

Conforme a ficha de atividade prática: identificadores em português, um arquivo `.java` por classe pública, código formatado, entrega no repositório Git com README e commits descritivos. O uso de IA para gerar o código é proibido nesta atividade (ver seção 5.3 da ficha).
