# Oficina — orientação a objetos em Java

Aplicação de terminal para cadastro de **peças** e **ordens de serviço**. O objetivo é demonstrar orientação a objetos com **SOLID**, **GRASP** e os **quatro modificadores de acesso** do Java (`public`, `protected`, `private` e package-private).

## Como executar

No IntelliJ, rode a classe `Main`.

Pelo terminal, a partir da raiz do projeto:

```bash
javac -encoding UTF-8 -d out -sourcepath src $(find src -name "*.java")
java -cp out Main
```

No PowerShell:

```powershell
$files = Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out -sourcepath src $files
java -cp out Main
```

## Menu

```
--- Peças ---
1 - Incluir nova peça
2 - Alterar preço de peça
3 - Listar peças
4 - Visualizar detalhes de peça

--- Ordens de serviço ---
5 - Criar ordem de serviço
6 - Adicionar peça à ordem de serviço
7 - Remover peça da ordem de serviço
8 - Listar ordens de serviço
9 - Visualizar detalhes de ordem de serviço

--- Sistema ---
0 - Sair
```

As listas ficam **em memória** (não há banco de dados). O preço da peça é ajustado por comportamento (`ajustarPreco`), não por setter. Ao incluir uma peça na ordem, o valor unitário é **congelado** naquele momento.

## Estrutura do projeto

```
src/
  Main.java                         ponto de entrada
  dominio/                          regras de negócio
    Peca.java
    OrdeServico.java
    ItemOrdemServico.java           package-private
    Validador.java                  package-private
    Formatador.java                 package-private
    SequenciaOrdem.java             package-private
  oficina/                          orquestração e persistência em memória
    OficinaController.java          público (API da aplicação)
    PecaRepositorio.java            package-private
    OrdeServicoRepositorio.java     package-private
  infra/
    Console.java                    leitura/escrita do terminal
  menu/                             framework de menu
    Menu.java
    MenuFormulario.java
    MenuAction.java
    MenuItem.java
    MenuPrincipal.java              só registra os itens por domínio
    acao/                           ações isoladas (Command)
      AbrirMenuAction.java
      SairAction.java
      peca/
      ordem/
    item/                           telas e montagem das opções
      VisualizacaoOficina.java
      peca/
      ordem/
      sistema/
```

| Pacote | Responsabilidade |
|---|---|
| `dominio` | Entidades e regras. Information Expert: `Peca` conhece o preço; `OrdeServico` conhece itens e totais. |
| `oficina` | GRASP Controller. Menus não acessam repositórios; só o controller. |
| `menu` | Navegação e Template Method (`Menu` / `MenuFormulario`). |
| `menu.acao.*` | Uma classe por operação (SRP). |
| `menu.item.*` | Uma tela por operação; `ItensPeca`, `ItensOrdem` e `ItensSistema` montam as opções. |
| `infra` | Isola o `Scanner` (Pure Fabrication). |

O `MenuPrincipal` não conhece cada tela: ele só adiciona o que cada domínio entrega.

```java
adicionarItens(ItensPeca.criar(controller, console));
adicionarItens(ItensOrdem.criar(controller, console));
adicionarItens(ItensSistema.criar(this, console));
```

## Modificadores de acesso

| Modificador | Onde aparece | Por quê |
|---|---|---|
| `private` | Campos de `Peca` e `OrdeServico`, `validarPreco()`, busca interna do `Menu` | Encapsulamento: o estado não vaza. |
| `public` | `Menu.exibir()`, `Peca.ajustarPreco()`, `OficinaController` | Contrato entre pacotes. |
| `protected` | `Menu.executar()`, `Peca.calcularPrecoAjustado()`, `MenuAction.sucesso()` | Ganchos para especialização por herança. |
| package-private | `ItemOrdemServico`, `Validador`, repositórios | Visível só no pacote. Outros pacotes veem a ordem pelo método `detalhes()`. |

## SOLID e GRASP (resumo)

- **SRP**: menu pergunta, action executa, controller orquestra, entidade calcula.
- **OCP**: nova operação = novo `MenuFormulario` + nova `MenuAction`, sem alterar `Menu`.
- **LSP**: qualquer `Menu` responde a `exibir()`; qualquer `MenuAction` responde a `executar()`.
- **DIP**: menus dependem do controller, não dos repositórios.
- **Information Expert**: total na `OrdeServico`, preço na `Peca`.
- **Creator**: a ordem cria seus `ItemOrdemServico`.
- **Controller / Indirection**: `OficinaController` isola a memória das telas.

---

## Diagrama de classes UML

### Visão geral dos pacotes

```mermaid
classDiagram
    class Main {
        +main(args)
    }

    Main --> OficinaController : cria
    Main --> Console : cria
    Main --> MenuPrincipal : inicia

    MenuPrincipal --> ItensPeca : registra
    MenuPrincipal --> ItensOrdem : registra
    MenuPrincipal --> ItensSistema : registra

    ItensPeca --> MenuAction : abre telas de peça
    ItensOrdem --> MenuAction : abre telas de ordem

    MenuAction --> OficinaController : executa caso de uso
    OficinaController --> PecaRepositorio : package-private
    OficinaController --> OrdeServicoRepositorio : package-private
    OficinaController --> Peca : cria / ajusta
    OficinaController --> OrdeServico : cria / altera itens
    OrdeServico --> ItemOrdemServico : cria
    Menu ..> Console : usa
```

### Domínio

```mermaid
classDiagram
    class Peca {
        -String codigo
        -String descricao
        -double precoBasico
        -double precoAtual
        +ajustarPreco(percentual)$
        #calcularPrecoAjustado(percentual)
        #aplicarAjuste(novoPreco)
        -validarPreco(preco)
        +resumo() String
        +detalhes() String
    }

    class OrdeServico {
        -int numero
        -String descricao
        -LocalDate data
        -List~ItemOrdemServico~ itens
        +adicionarPeca(peca, quantidade)
        +removerPeca(codigoPeca)
        +getTotal() double
        #calcularTotal() double
        +resumo() String
        +detalhes() String
    }

    class ItemOrdemServico {
        <<package-private>>
        -String codigoPeca
        -String descricaoPeca
        -double valorUnitario
        -int quantidade
        ~subtotal() double
        ~formatar() String
    }

    class Validador {
        <<package-private>>
        ~exigirTexto(valor, campo)$
        ~exigirNaoNegativo(valor, campo)$
        ~exigirPositivo(valor, campo)$
    }

    class SequenciaOrdem {
        <<package-private>>
        ~proxima() int$
    }

    OrdeServico "1" *-- "0..*" ItemOrdemServico : contém
    OrdeServico ..> Peca : consulta preço atual na inclusão
    OrdeServico ..> SequenciaOrdem : gera número
    Peca ..> Validador
    OrdeServico ..> Validador
    ItemOrdemServico ..> Validador
```

`ItemOrdemServico` guarda uma **cópia** do código, da descrição e do valor unitário no instante da inclusão. Mudanças posteriores no preço da peça não alteram ordens já existentes.

### Menu e actions

```mermaid
classDiagram
    class Menu {
        -String titulo
        -List~MenuItem~ itens
        #Console console
        +exibir()
        #executar()*
        #adicionarItens(itens)
        +encerrar()
    }

    class MenuFormulario {
        #executar()
        #coletarEExecutar()*
    }

    class MenuPrincipal {
        +MenuPrincipal(controller, console)
    }

    class MenuItem {
        -Integer opcao
        -String rotulo
        -MenuAction acao
        +MenuItem(opcao, rotulo, acao)
        +secao(titulo)$ MenuItem
    }

    class MenuAction {
        #Console console
        +executar()*
        #sucesso(mensagem)
        #erro(mensagem)
    }

    Menu <|-- MenuFormulario
    Menu <|-- MenuPrincipal
    Menu o-- MenuItem
    MenuItem --> MenuAction

    MenuFormulario <|-- MenuIncluirPeca
    MenuFormulario <|-- MenuAlterarPreco
    MenuFormulario <|-- MenuListarPecas
    MenuFormulario <|-- MenuVisualizarDetalhesPeca
    MenuFormulario <|-- MenuCriarOrdem
    MenuFormulario <|-- MenuAdicionarItem
    MenuFormulario <|-- MenuRemoverItem
    MenuFormulario <|-- MenuListarOrdens
    MenuFormulario <|-- MenuVisualizarDetalhesOrdem

    MenuAction <|-- IncluirPecaAction
    MenuAction <|-- AlterarPrecoAction
    MenuAction <|-- ListarPecasAction
    MenuAction <|-- VisualizarDetalhesPecaAction
    MenuAction <|-- CriarOrdemAction
    MenuAction <|-- AdicionarItemAction
    MenuAction <|-- RemoverItemAction
    MenuAction <|-- ListarOrdensAction
    MenuAction <|-- VisualizarDetalhesOrdemAction
    MenuAction <|-- AbrirMenuAction
    MenuAction <|-- SairAction

    AbrirMenuAction --> Menu : abre submenu
```

### Controller e repositórios

```mermaid
classDiagram
    class OficinaController {
        -PecaRepositorio pecas
        -OrdeServicoRepositorio ordens
        +incluirPeca(codigo, descricao, preco)
        +alterarPreco(codigo, percentual)
        +criarOrdem(descricao, data)
        +adicionarItem(numero, codigoPeca, qtd)
        +removerItem(numero, codigoPeca)
        +listarPecas()
        +listarOrdens()
        +buscarPeca(codigo)
        +buscarOrdem(numero)
    }

    class PecaRepositorio {
        <<package-private>>
        -List~Peca~ pecas
        ~salvar(peca)
        ~buscarPorCodigo(codigo)
        ~listar()
    }

    class OrdeServicoRepositorio {
        <<package-private>>
        -List~OrdeServico~ ordens
        ~salvar(ordem)
        ~buscarPorNumero(numero)
        ~listar()
    }

    OficinaController --> PecaRepositorio
    OficinaController --> OrdeServicoRepositorio
```

Classes fora do pacote `oficina` **não compilam** se tentarem usar os repositórios: eles são package-private.

---

## Diagramas de sequência das principais operações

Fluxo comum de toda opção do menu:

1. `MenuPrincipal` recebe o número.
2. `AbrirMenuAction` abre o menu especializado.
3. O `MenuFormulario` coleta os dados.
4. A `MenuAction` correspondente chama o `OficinaController`.
5. O controller aplica a regra e persiste em memória.

### 1. Incluir nova peça

```mermaid
sequenceDiagram
    actor Usuario
    participant MP as MenuPrincipal
    participant Abrir as AbrirMenuAction
    participant Tela as MenuIncluirPeca
    participant Acao as IncluirPecaAction
    participant Ctrl as OficinaController
    participant Repo as PecaRepositorio
    participant Peca as Peca

    Usuario->>MP: opção 1
    MP->>Abrir: executar()
    Abrir->>Tela: exibir()
    Tela->>Usuario: código, descrição, preço básico
    Usuario-->>Tela: dados
    Tela->>Acao: executar()
    Acao->>Ctrl: incluirPeca(...)
    Ctrl->>Repo: existe(codigo)?
    Repo-->>Ctrl: false
    Ctrl->>Peca: new Peca(...)
    Peca->>Peca: validar campos (private)
    Ctrl->>Repo: salvar(peca)
    Ctrl-->>Acao: peça incluída
    Acao-->>Usuario: [OK] Peça incluída
```

### 2. Alterar preço de peça

O percentual pode ser positivo (aumento) ou negativo (desconto). O cálculo e a validação ficam na `Peca`.

```mermaid
sequenceDiagram
    actor Usuario
    participant Tela as MenuAlterarPreco
    participant Acao as AlterarPrecoAction
    participant Ctrl as OficinaController
    participant Repo as PecaRepositorio
    participant Peca as Peca

    Usuario->>Tela: opção 2 + código + percentual
    Tela->>Acao: executar()
    Acao->>Ctrl: alterarPreco(codigo, percentual)
    Ctrl->>Repo: buscarPorCodigo(codigo)
    Repo-->>Ctrl: peca
    Ctrl->>Peca: ajustarPreco(percentual)
    Peca->>Peca: calcularPrecoAjustado() (protected)
    Peca->>Peca: aplicarAjuste() (protected)
    Peca->>Peca: validarPreco() (private)
    Ctrl-->>Acao: peca atualizada
    Acao-->>Usuario: resumo com preço básico e atual
```

### 3. Criar ordem de serviço

```mermaid
sequenceDiagram
    actor Usuario
    participant Tela as MenuCriarOrdem
    participant Acao as CriarOrdemAction
    participant Ctrl as OficinaController
    participant OS as OrdeServico
    participant Seq as SequenciaOrdem
    participant Repo as OrdeServicoRepositorio

    Usuario->>Tela: opção 5 + descrição + data
    Tela->>Acao: executar()
    Acao->>Ctrl: criarOrdem(descricao, data)
    Ctrl->>OS: new OrdeServico(...)
    OS->>Seq: proxima()
    Seq-->>OS: número
    Ctrl->>Repo: salvar(ordem)
    Ctrl-->>Acao: OS-n criada
    Acao-->>Usuario: [OK] Ordem criada
```

### 4. Adicionar peça à ordem de serviço

O valor cobrado é o `precoAtual` da peça **no momento da inclusão**.

```mermaid
sequenceDiagram
    actor Usuario
    participant Tela as MenuAdicionarItem
    participant Acao as AdicionarItemAction
    participant Ctrl as OficinaController
    participant RepoOS as OrdeServicoRepositorio
    participant RepoPeca as PecaRepositorio
    participant OS as OrdeServico
    participant Item as ItemOrdemServico
    participant Peca as Peca

    Usuario->>Tela: opção 6 + nº OS + código + qtd
    Tela->>Acao: executar()
    Acao->>Ctrl: adicionarItem(numero, codigo, qtd)
    Ctrl->>RepoOS: buscarPorNumero(numero)
    Ctrl->>RepoPeca: buscarPorCodigo(codigo)
    Ctrl->>OS: adicionarPeca(peca, qtd)
    OS->>Peca: getCodigo() / getDescricao() / getPrecoAtual()
    alt peça já está na ordem
        OS->>Item: adicionarQuantidade(qtd)
    else peça nova
        OS->>Item: new ItemOrdemServico(peca, qtd)
        Note over Item: congela valorUnitario
    end
    Ctrl-->>Acao: ordem atualizada
    Acao-->>Usuario: detalhes da OS (itens e total)
```

### 5. Remover peça da ordem de serviço

```mermaid
sequenceDiagram
    actor Usuario
    participant Tela as MenuRemoverItem
    participant Ctrl as OficinaController
    participant OS as OrdeServico
    participant Acao as RemoverItemAction

    Usuario->>Tela: opção 7 + nº da OS
    Tela->>Ctrl: buscarOrdem(numero)
    Ctrl-->>Tela: ordem
    Tela-->>Usuario: detalhes atuais
    Usuario->>Tela: código da peça
    Tela->>Acao: executar()
    Acao->>Ctrl: removerItem(numero, codigo)
    Ctrl->>OS: removerPeca(codigo)
    OS-->>Ctrl: item removido
    Acao-->>Usuario: detalhes atualizados
```

### 6. Listar e visualizar detalhes

Listagens pedem o **resumo**. A visualização pede um identificador e mostra o **detalhe** completo (Information Expert na própria entidade).

```mermaid
sequenceDiagram
    actor Usuario
    participant TelaLista as MenuListarPecas
    participant AcaoLista as ListarPecasAction
    participant TelaDet as MenuVisualizarDetalhesPeca
    participant AcaoDet as VisualizarDetalhesPecaAction
    participant Ctrl as OficinaController
    participant Peca as Peca

    Usuario->>TelaLista: opção 3
    TelaLista->>AcaoLista: executar()
    AcaoLista->>Ctrl: listarPecas()
    Ctrl-->>AcaoLista: lista
    loop cada peça
        AcaoLista->>Peca: resumo()
    end
    AcaoLista-->>Usuario: códigos, descrições e preços

    Usuario->>TelaDet: opção 4 + código
    TelaDet->>AcaoDet: executar()
    AcaoDet->>Ctrl: buscarPeca(codigo)
    Ctrl-->>AcaoDet: peca
    AcaoDet->>Peca: detalhes()
    AcaoDet-->>Usuario: código, descrição, preço básico e atual
```

O fluxo de **listar / detalhar ordem** (opções 8 e 9) é o mesmo, trocando `Peca` por `OrdeServico` (`resumo()` e `detalhes()` com peças, valores e total).
