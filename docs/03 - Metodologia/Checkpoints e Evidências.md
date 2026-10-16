# Checkpoints e Evidências

Status: Vigente

## Finalidade

Registrar o que o Colossus já conseguiu demonstrar, em quais condições e com quais limitações.

Um checkpoint pode reunir vários commits. Seu valor está no resultado verificável: a aplicação iniciou, uma consulta respondeu corretamente ou uma investigação foi recuperada após um reinício.

## Como interpretar os registros

| Situação | Significado |
|---|---|
| **Verificado** | Existe evidência do resultado descrito, dentro do ambiente e do escopo registrados |
| **Parcial** | Parte do objetivo foi alcançada; ainda há verificações ou entregas pendentes |
| **Planejado** | O resultado ainda será produzido e não conta como entrega concluída |

As datas podem indicar a execução ou a consolidação do registro, conforme identificado em cada checkpoint.

## Índice de checkpoints

| ID | Marco | Data de referência | Situação | Evidência disponível |
|---|---|---|---|---|
| CP01 | Inicialização local sem banco | 14/10/2026 | Verificado | Log de inicialização enviado pelo desenvolvedor |
| CP02 | Preparação da documentação de base | 16/10/2026 | Parcial | Textos elaborados e revisados nesta conversa |

## CP01 — Inicialização local sem banco

| Campo | Registro |
|---|---|
| **Data da execução** | 14/10/2026 |
| **Objetivo** | Confirmar que a estrutura inicial do Spring Boot inicia sem exigir um banco de dados |
| **Ambiente informado** | Windows, IntelliJ IDEA, Java 21.0.12.1 e Spring Boot 4.1.1 |
| **Trabalho realizado** | Preparação do projeto e configuração temporária para inicialização sem banco |
| **Resultado observado** | Tomcat iniciado na porta 8080 e aplicação inicializada |
| **Commit ou versão do código** | Hash do commit não informado |

### Evidência

Trecho do log enviado pelo desenvolvedor:

```text
Tomcat started on port 8080 (http) with context path '/'
Started ColossusApplication in 1.943 seconds (process running for 2.54)
```

A primeira tentativa havia falhado porque a conexão com o banco não estava configurada. Após o ajuste para execução sem banco, a inicialização foi concluída.

### Alcance da verificação

O registro comprova a inicialização no ambiente informado.

Ele não comprova:

- Funcionamento de um endpoint de negócio.
- Execução bem-sucedida de testes automatizados.
- Persistência ou recuperação de dados.
- Execução e validação de código submetido.
- Reprodução do resultado a partir de uma cópia limpa do repositório.

O tempo exibido no log corresponde a essa execução; não representa uma meta de desempenho.

### Pendências e próximo passo

- Associar o registro ao commit correspondente, caso ele possa ser identificado.
- Verificar a execução fora da IDE conforme RNF01.
- Implementar e verificar a consulta do primeiro caso.

## CP02 — Preparação da documentação de base

| Campo | Registro |
|---|---|
| **Data de consolidação** | 16/10/2026 |
| **Objetivo** | Organizar as definições do produto e o processo de desenvolvimento |
| **Trabalho realizado** | Elaboração dos textos de Contexto, Especificação, Requisitos Funcionais, Requisitos Não Funcionais e Metodologia |
| **Resultado observado** | Conteúdo disponível para revisão e incorporação aos documentos do projeto |
| **Evidência disponível** | Textos apresentados nesta conversa; fluxo da especificação entregue em SVG e arquivo editável do draw.io |
| **Commit ou publicação no GitHub** | Não confirmados neste registro |

### Decisões consolidadas nos textos

- A proposta e as tecnologias do Colossus permanecem como referência.
- O desenvolvimento será organizado em entregas menores.
- A primeira funcionalidade será a consulta de **O desconto fantasma**, com dados em memória.
- A execução de código pertence a uma entrega posterior, com requisitos próprios de isolamento e validação.
- O acompanhamento utilizará **Agora, Depois e Futuro**.

### Limite da evidência

A elaboração dos textos não comprova que todas as versões foram aprovadas, salvas no projeto ou enviadas ao GitHub. Também não representa implementação das funcionalidades descritas.

### Pendências e próximo passo

- Conferir as versões incorporadas ao projeto e seus respectivos estados de revisão.
- Verificar os links e a exibição do diagrama na especificação.
- Registrar os commits correspondentes quando disponíveis.
- Atualizar este checkpoint com a evidência da versão efetivamente incorporada ao repositório.

## Próximo checkpoint — Consulta de O desconto fantasma

**Situação: Planejado.**

| O que demonstrar | Evidência esperada |
|---|---|
| Consulta de um caso existente | Requisição e resposta JSON com os dados públicos previstos em RF01 |
| Consulta de um identificador inexistente | Resposta informando que o caso não foi encontrado |
| Proteção do conteúdo reservado | Verificação de que solução explicada e testes reservados não aparecem na resposta |
| Funcionamento reproduzível | Comando utilizado, ambiente e resultado da execução |
| Verificação automatizada | Resultado dos testes pertinentes e identificação do commit testado |

A data, os resultados e as referências serão preenchidos após a execução. Falhas encontradas também poderão ser registradas, mantendo o checkpoint como parcial.

## Como acrescentar evidências

Para cada novo marco:

1. Identificar a entrega e os requisitos relacionados.
2. Registrar data, ambiente e commit, quando disponíveis.
3. Descrever o procedimento realizado e o resultado observado.
4. Incluir a evidência que permite conferir o resultado.
5. Explicitar limitações, pendências e próximo passo.

Preferir evidências diretamente consultáveis:

| Evidência | Quando ajuda |
|---|---|
| **Commit ou pull request** | Identificar exatamente qual alteração foi avaliada |
| **Relatório de testes** | Demonstrar cenários executados e resultados |
| **Requisição e resposta da API** | Mostrar um comportamento observável |
| **Trecho de log** | Comprovar inicialização, falha ou processamento específico |
| **Captura de tela** | Demonstrar interface, interação ou problema visual |

Registrar apenas o trecho necessário à compreensão, sem credenciais ou dados privados. Links para arquivos e relatórios devem ser adicionados quando esses materiais realmente existirem.

Quando uma nova verificação alterar a conclusão anterior, acrescentar uma atualização com data e referência. Preservar o histórico do que havia sido observado.