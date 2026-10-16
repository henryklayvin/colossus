# Metodologia

Status: Vigente

## Forma de trabalho

O Colossus será desenvolvido individualmente, em entregas pequenas e verificáveis. O planejamento mantém a visão do produto completo; o trabalho diário se concentra no próximo resultado que pode ser construído e demonstrado.

Cada entrega deve ajudar a responder três perguntas:

- O que mudou no projeto?
- Como foi verificado que funciona?
- O que foi aprendido ou ainda precisa ser esclarecido?

As tecnologias previstas continuam como referência. A presença de uma dependência no projeto não determina quando sua funcionalidade será implementada.

## Organização das tarefas

O acompanhamento utilizará uma lista curta:

| Lista | O que contém | Como utilizar |
|---|---|---|
| **Agora** | Uma entrega principal em andamento | Concentrar implementação, estudo e verificação nesse objetivo |
| **Depois** | As próximas poucas tarefas | Manter apenas o detalhamento necessário para escolher o próximo passo |
| **Futuro** | Capacidades previstas para o produto | Preservar a visão geral e detalhar quando se aproximarem do desenvolvimento |

Uma dúvida ou ideia surgida durante o trabalho pode ser registrada em **Depois** ou **Futuro**. Ela só altera a tarefa atual quando for necessária para concluir a entrega ou corrigir um problema que impeça seu avanço.

### Como definir uma tarefa

Antes de começar, registrar:

| Informação | Exemplo do Colossus |
|---|---|
| **Resultado esperado** | Consultar O desconto fantasma pela API |
| **Requisitos relacionados** | RF01 e a preparação inicial do conteúdo de RF17 |
| **Como verificar** | Consultar caso existente e inexistente; conferir os dados retornados e a ausência de conteúdo reservado |
| **Limite da tarefa** | Dados em memória; sem banco, interface, login ou execução de código |

Se uma tarefa reunir vários resultados independentes, ela deverá ser dividida.

## Ciclo de uma entrega

| Etapa | Ação | Resultado esperado |
|---|---|---|
| **1. Delimitar** | Escolher a tarefa e conferir os requisitos envolvidos | Saber o que será entregue e como verificar |
| **2. Estudar** | Investigar os conceitos necessários à próxima mudança | Compreender o suficiente para implementar e explicar a solução |
| **3. Implementar** | Construir em passos pequenos | Código ou documentação que atendam à tarefa |
| **4. Verificar** | Executar os testes e conferências pertinentes | Evidências do que funciona e identificação das falhas |
| **5. Revisar** | Conferir as alterações e atualizar os documentos afetados | Mudança coerente e pronta para registrar |
| **6. Registrar** | Fazer commit, enviar ao GitHub e registrar um checkpoint quando houver um marco relevante | Histórico e evidências disponíveis para consulta |

A verificação pode levar de volta ao estudo ou à implementação. Isso faz parte do trabalho.

## Estudo aplicado

O estudo acompanhará as necessidades da entrega.

Para consultar o primeiro caso, por exemplo, será necessário compreender o caminho da requisição HTTP, a construção da resposta JSON e a organização do conteúdo em memória. Os assuntos de persistência e execução isolada terão suas próprias tarefas.

Ao incorporar código, uma configuração ou uma solução obtida em material de apoio, o desenvolvedor deverá conseguir explicar:

- Qual problema ela resolve.
- Como participa do funcionamento da aplicação.
- Como seu comportamento foi verificado.

Ao investigar um bug, registrar o comportamento esperado e o observado antes de alterar o código. A correção deverá ser acompanhada de uma verificação capaz de detectar o problema original.

## Verificação proporcional à entrega

| Tipo de mudança | Verificação necessária |
|---|---|
| **Documentação** | Clareza, coerência com as decisões atuais, referências e funcionamento dos links e recursos visuais |
| **Regra de negócio** | Testes dos cenários relevantes, incluindo limites e situações inválidas |
| **Endpoint da API** | Requisição, resposta, erros previstos e proteção do conteúdo retornado |
| **Persistência** | Integridade dos dados, migrations e comportamento com PostgreSQL de teste |
| **Interface** | Fluxo do participante, mensagens, teclado e apresentação do conteúdo |
| **Execução de código** | Isolamento, limites, confiabilidade da avaliação e tratamento de falhas em ambiente controlado |

Somente os requisitos aplicáveis à entrega serão cobrados naquele momento. A consulta inicial em memória não depende da conclusão dos requisitos do executor.

As verificações devem observar o comportamento esperado. Um teste que passa ou uma aplicação que inicia não comprova, sozinho, o funcionamento de toda a funcionalidade.

## Uso do Git e do GitHub

O IntelliJ será utilizado para desenvolver. O GitHub Desktop apoiará a revisão das alterações, os commits e a sincronização com o GitHub.

### Branches

| Branch | Uso |
|---|---|
| **Principal** | Referência das entregas integradas e verificadas |
| **De trabalho** | Alterações que precisam de vários passos ou experimentação antes da integração |

Uma atualização pequena e já revisada, como corrigir um documento, pode ser registrada diretamente na principal. Uma funcionalidade desenvolvida em etapas pode utilizar uma branch como `feat/consulta-caso`.

Antes de integrar uma branch de trabalho, revisar as alterações e executar as verificações pertinentes. O uso de pull request fica disponível quando ajudar a revisar ou registrar a mudança.

### Commits

Cada commit deverá representar uma alteração coerente, com uma mensagem que explique o resultado.

| Alteração | Exemplo de mensagem |
|---|---|
| Criar a estrutura da documentação | `docs: adiciona estrutura inicial da documentação` |
| Documentar o processo de trabalho | `docs: define metodologia de desenvolvimento` |
| Implementar a consulta do caso | `feat: disponibiliza consulta do primeiro caso` |
| Corrigir a resposta para caso inexistente | `fix: trata consulta de caso inexistente` |

Um commit pode incluir vários arquivos relacionados. A quantidade de documentos ou pastas não determina a quantidade de commits.

Antes de confirmar:

- Revisar os arquivos e o conteúdo alterado.
- Separar mudanças sem relação entre si.
- Conferir se não há credenciais, arquivos temporários ou resultados de compilação.
- Executar as verificações necessárias à mudança.

Após o commit, realizar o push para disponibilizar o registro no GitHub.

## Quando considerar uma tarefa concluída

- [ ] O resultado esperado foi alcançado.
- [ ] Os critérios de aceitação e requisitos de qualidade aplicáveis foram verificados.
- [ ] As alterações foram revisadas e podem ser explicadas pelo desenvolvedor.
- [ ] A documentação afetada corresponde ao comportamento ou à decisão atual.
- [ ] Limitações e pendências relevantes estão registradas.
- [ ] O trabalho foi registrado em commit e enviado ao GitHub.
- [ ] Quando houver um marco relevante, sua evidência foi registrada.

Se o resultado esperado não foi alcançado, a tarefa permanece em andamento ou tem seu escopo revisto explicitamente. Uma entrega parcial deve ser identificada como parcial.

## Documentação e checkpoints

A documentação será atualizada junto das mudanças que a afetarem.

| Informação | Onde registrar |
|---|---|
| Comportamento esperado e critérios de aceitação | Requisitos Funcionais |
| Condições de qualidade e segurança | Requisitos Não Funcionais |
| Decisão técnica relevante e sua justificativa | Decisões Arquiteturais |
| Procedimento necessário para executar ou recuperar a aplicação | Operação e Recuperação |
| Resultado de uma verificação e suas evidências | Registros de Testes ou Checkpoints e Evidências |

Evitar copiar a mesma informação em vários documentos. Quando um assunto já estiver explicado, fazer referência ao local correspondente.

Um checkpoint registra um resultado relevante e pode reunir vários commits. Não é necessário criar um checkpoint para cada edição.

Exemplos de marcos:

- Aplicação iniciada localmente sem banco.
- Consulta do primeiro caso implementada e verificada.
- Investigação persistida e recuperada após reinício.
- Primeira execução isolada validada no ambiente de teste.

Cada registro deverá identificar o que foi verificado, em qual versão ou ambiente, o resultado observado e as limitações da evidência.

## Revisão da metodologia

Este processo será ajustado conforme a experiência de desenvolvimento mostrar o que ajuda ou atrapalha.

Uma prática será mantida quando contribuir para compreender o projeto, verificar as entregas ou recuperar decisões. Etapas que apenas repetem informações deverão ser simplificadas.