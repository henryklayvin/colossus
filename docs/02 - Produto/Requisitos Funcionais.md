# Requisitos Funcionais

Status: Vigente

## Como ler este documento

Este documento descreve o que o Colossus deverá permitir fazer e como verificar cada comportamento. O fluxo geral da investigação está na Especificação do Projeto.

**Situação atual:** RF01 a RF17 estão planejados. RF18 também está previsto, mas suas regras específicas permanecem em definição. Nenhum requisito é considerado implementado apenas por constar nesta lista.

- **Inicial:** consulta de um caso pela API local, com dados em memória.
- **MVP:** experiência completa de investigação, submissão e validação.
- **Posterior:** evolução após o MVP.

A prioridade indica importância: **alta** para a experiência central, **média** para apoio e manutenção, **baixa** para capacidades complementares. A numeração identifica os requisitos e não determina a ordem de desenvolvimento.

## Catálogo de requisitos

| ID | Funcionalidade | Ator | Prioridade | Entrega |
|---|---|---|---|---|
| RF01 | Consultar um caso | Participante | Alta | Inicial |
| RF02 | Consultar o catálogo | Participante | Alta | MVP |
| RF03 | Autenticar o usuário | Participante ou administrador | Alta | MVP |
| RF04 | Encerrar a sessão | Participante ou administrador | Alta | MVP |
| RF05 | Iniciar e retomar uma investigação | Participante | Alta | MVP |
| RF06 | Registrar hipóteses | Participante | Média | MVP |
| RF07 | Consultar dicas progressivas | Participante | Alta | MVP |
| RF08 | Editar e salvar um rascunho | Participante | Alta | MVP |
| RF09 | Enviar uma solução | Participante | Alta | MVP |
| RF10 | Acompanhar a execução | Participante | Alta | MVP |
| RF11 | Compilar e validar o código | Sistema | Alta | MVP |
| RF12 | Apresentar os resultados | Participante | Alta | MVP |
| RF13 | Registrar a conclusão | Sistema | Alta | MVP |
| RF14 | Consultar a explicação | Participante | Alta | MVP |
| RF15 | Consultar histórico e progresso | Participante | Alta | MVP |
| RF16 | Controlar acesso aos dados e operações | Participante ou administrador | Alta | MVP |
| RF17 | Preparar e manter os casos | Autor ou administrador | Média | Inicial, parcialmente; ampliação no MVP |
| RF18 | Aplicar gamificação | Participante e sistema | Baixa | Posterior; regras em definição |

## Casos disponíveis

### RF01 — Consultar um caso

O participante deverá conseguir compreender o problema, o comportamento esperado e as evidências disponíveis. Na primeira entrega, a API disponibilizará **O desconto fantasma**, com conteúdo em memória.

**Critérios de aceitação:**

- A consulta de um caso existente retorna JSON com identificação, título, enunciado, requisitos, código inicial e evidências públicas.
- As evidências distinguem o resultado esperado do observado e são identificadas como conteúdo preparado pelo autor.
- A resposta não inclui a solução explicada nem testes reservados.
- Um identificador inexistente produz uma resposta de caso não encontrado.

### RF02 — Consultar o catálogo

O participante deverá encontrar os casos disponíveis e acessar seus detalhes.

**Critérios de aceitação:**

- A listagem apresenta apenas casos disponibilizados aos participantes, com identificação e acesso aos detalhes.
- Quando não há casos disponíveis, o sistema informa essa condição.
- O catálogo não revela soluções ou conteúdo reservado.

## Identidade e acesso

### RF03 — Autenticar o usuário

O sistema deverá verificar a identidade do usuário e estabelecer uma sessão.

**Critérios de aceitação:**

- Uma autenticação válida permite acessar as funcionalidades autorizadas para aquele usuário.
- Uma tentativa inválida é recusada e informa que o acesso não foi concedido.
- As operações autenticadas ficam associadas ao usuário da sessão.

A forma de criação das contas permanece pendente.

### RF04 — Encerrar a sessão

O usuário deverá conseguir sair da aplicação sem perder seus dados salvos.

**Critérios de aceitação:**

- A sessão encerrada deixa de autorizar novas operações.
- O acesso a funcionalidades protegidas exige nova autenticação.
- Após entrar novamente, investigações, rascunhos e resultados persistidos continuam disponíveis.

### RF16 — Controlar acesso aos dados e operações

O backend deverá verificar se o usuário pode realizar a operação solicitada sobre os dados envolvidos.

**Critérios de aceitação:**

- O participante consegue acessar e alterar seus próprios dados, conforme as operações permitidas.
- Outro participante não consegue consultar ou modificar suas investigações, rascunhos e submissões, mesmo conhecendo seus identificadores.
- Operações administrativas exigem autorização específica.
- Requisições feitas diretamente à API seguem as mesmas regras da interface.

## Investigação

### RF05 — Iniciar e retomar uma investigação

O participante deverá poder iniciar uma investigação vinculada a um caso e recuperar seu andamento posteriormente.

**Critérios de aceitação:**

- Um caso disponível permite criar uma investigação identificável, associada ao participante.
- Um caso inexistente não permite criar uma investigação.
- Ao retomar, o participante recupera hipóteses, dicas liberadas, rascunho e submissões existentes, sem apagar o andamento salvo.

### RF06 — Registrar hipóteses

O participante deverá poder salvar, consultar e revisar suas hipóteses dentro da investigação.

No caso **O desconto fantasma**, uma hipótese poderia ser: “A condição que decide quem recebe o desconto pode estar incorreta.”

**Critérios de aceitação:**

- Uma hipótese salva pode ser consultada posteriormente.
- Uma alteração salva é recuperada ao retomar a investigação.
- Registrar uma hipótese não concede aprovação nem conclusão do caso.

### RF07 — Consultar dicas progressivas

As dicas deverão apoiar a investigação na sequência definida pelo autor, mediante solicitação do participante.

| Situação ou ação | Resultado esperado |
|---|---|
| Pedir a primeira dica | Liberar a primeira dica da sequência |
| Pedir intencionalmente outra dica | Liberar apenas a próxima dica |
| Consultar dicas já liberadas | Exibi-las sem avançar a sequência |
| Retransmitir o mesmo pedido | Manter a liberação já realizada, sem liberar outra dica |
| Pedir uma dica após esgotar a sequência | Informar que não há mais dicas |

As respostas da investigação não deverão incluir dicas ainda bloqueadas. Nenhuma penalidade será aplicada sem uma regra de gamificação previamente definida e apresentada ao participante.

### RF08 — Editar e salvar um rascunho

O código inicial do caso deverá servir como ponto de partida para o participante desenvolver sua correção.

**Critérios de aceitação:**

- O participante consegue editar o código e salvar um rascunho vinculado à sua investigação.
- Ao retomar a investigação, o último conteúdo salvo é recuperado.
- Salvar o rascunho não executa o código nem aprova o caso.
- Alterações no rascunho não modificam o código de submissões anteriores.

## Submissão e validação

### RF09 — Enviar uma solução

O participante deverá conseguir submeter uma versão do código para validação.

**Contrato inicial:** código Java no formato definido pelo caso. Projetos completos, arquivos JAR e dependências arbitrárias não serão aceitos.

**Critérios de aceitação:**

- Um envio válido gera uma identificação para acompanhamento e preserva o código enviado.
- A submissão fica associada ao participante, à investigação e à versão do caso.
- Um envio incompatível com o contrato é recusado com indicação do problema.
- A retransmissão do mesmo pedido retorna a submissão existente, sem duplicar submissões ou execuções.
- Enquanto a execução real estiver indisponível, o sistema informa a limitação e não produz aprovação simulada.

### RF10 — Acompanhar a execução

O participante deverá consultar o andamento de uma execução identificada. Uma execução inexistente deverá ser informada como não encontrada.

A tabela define as distinções que devem aparecer no acompanhamento e nos resultados; os nomes técnicos dos estados serão definidos no contrato da API.

| Estado ou resultado | O que o sistema informa |
|---|---|
| Pendente | A execução aguarda processamento |
| Em andamento | O processamento ainda não terminou |
| Aprovada | Todos os testes obrigatórios foram concluídos com sucesso |
| Reprovada | O comportamento falhou em pelo menos um teste obrigatório |
| Erro de compilação | O código não pôde ser compilado |
| Limite excedido | A execução ultrapassou um limite permitido |
| Falha de infraestrutura | Um problema técnico impediu a avaliação; isso não equivale a uma solução incorreta |
| Cancelada ou expirada, quando ocorrer | A execução foi encerrada nessa condição, sem conceder aprovação |

O estado consultado deverá sempre corresponder à execução solicitada.

### RF11 — Compilar e validar o código

O sistema deverá avaliar o comportamento da solução com os testes do caso.

**Condições da validação:**

- Compilação e execução ocorrem em ambiente separado; a avaliação dos resultados ocorre fora do ambiente do código submetido.
- A aprovação exige a conclusão de todos os testes obrigatórios com os resultados esperados.
- Implementações diferentes podem ser aprovadas; não se exige igualdade textual com uma solução de referência.
- Testes reservados verificam requisitos apresentados no caso, sem introduzir regras secretas.

**Critérios de aceitação:**

- Falhas de compilação, de comportamento, de limites e de infraestrutura recebem as classificações descritas em RF10.
- Uma execução incompleta não é aprovada.
- Imprimir uma mensagem como “sucesso” não substitui a avaliação dos resultados.
- Uma solução que atende a todos os testes obrigatórios é aprovada.

Os mecanismos de isolamento e os limites serão detalhados nos requisitos não funcionais e na arquitetura.

### RF12 — Apresentar os resultados

O resultado deverá ajudar o participante a compreender o que aconteceu com a solução enviada e decidir o que revisar.

**Critérios de aceitação:**

- O resultado identifica a submissão, a execução e o código ao qual se refere.
- Uma reprovação em teste público permite comparar entrada, saída esperada e saída observada.
- Uma falha em teste reservado é informada sem revelar seus dados internos.
- Erros e falhas mantêm as distinções de RF10.
- Evidências preparadas pelo autor são distinguíveis de resultados de execução real.
- A resposta não antecipa a solução explicada, cuja liberação segue RF14.

## Conclusão e aprendizagem

### RF13 — Registrar a conclusão do caso

O sistema deverá concluir o caso para o participante somente após uma execução aprovada.

**Critérios de aceitação:**

- A conclusão fica associada ao participante e à execução que comprovou a aprovação.
- Consulta de evidências, hipóteses, rascunhos, simulações, reprovações e falhas técnicas não concedem conclusão.
- Processar novamente o mesmo resultado aprovado não duplica a conclusão nem seus efeitos sobre o progresso.

### RF14 — Consultar a explicação do caso

Após a conclusão, o participante deverá poder compreender a causa do defeito, sua relação com os requisitos e uma forma de correção.

**Critérios de aceitação:**

- A explicação é liberada após a conclusão e corresponde à versão do caso investigada.
- Antes disso, o backend não a retorna pelo fluxo de resolução, inclusive em consultas diretas à API.
- A solução de referência é apresentada como exemplo, sem invalidar outras implementações corretas.

### RF15 — Consultar histórico e progresso

O participante deverá conseguir acompanhar suas investigações, submissões e casos concluídos.

**Critérios de aceitação:**

- O histórico permite localizar e retomar investigações próprias.
- Submissões anteriores permanecem vinculadas à investigação e ao caso, com seus resultados consultáveis.
- As dicas utilizadas continuam acessíveis na investigação correspondente.
- O progresso distingue investigações em andamento e casos concluídos por aprovação.
- Os dados persistidos permanecem disponíveis após encerrar e iniciar outra sessão.

## Conteúdo e evolução do produto

### RF17 — Preparar e manter os casos

O autor deverá preparar o conteúdo que a aplicação disponibiliza aos participantes.

**Na primeira entrega**, isso será feito nos arquivos do projeto. Essa atividade não pressupõe uma tela administrativa ou um módulo de cadastro de casos. A interface administrativa também não é condição para o MVP.

**Critérios de aceitação:**

- Um caso preparado pelo autor pode ser consultado conforme RF01.
- O conteúdo reúne enunciado, requisitos, código inicial e evidências; dicas, testes e explicação acompanham a evolução das capacidades do produto.
- O participante recebe apenas conteúdo público ou já liberado para sua investigação.
- Dicas e testes correspondem ao caso investigado.
- Alterações posteriores no caso preservam a identificação da versão utilizada nas submissões e nos resultados anteriores.

Embora sua prioridade geral seja média, a preparação do conteúdo básico é necessária à primeira entrega de RF01.

### RF18 — Aplicar gamificação

**Planejado para depois do MVP, com regras específicas em definição.**

A evolução prevista inclui pontuação, níveis e conquistas associados a eventos verificáveis da participação.

| Regra já estabelecida | Verificação esperada |
|---|---|
| Regras apresentadas antes da aplicação | O participante consegue conhecer como os efeitos são atribuídos |
| Recompensa aplicada uma única vez por evento | Reprocessar o mesmo evento não duplica o benefício |
| Eventual penalidade por dica informada previamente | O participante conhece o efeito antes de solicitar a dica |
| Falha técnica sem penalização | Uma falha de infraestrutura não reduz a pontuação |
| Progresso de gamificação consultável | Pontuação, nível e conquistas atribuídos podem ser consultados |

Os valores, os eventos elegíveis e as regras de progressão ainda precisam ser definidos para completar os critérios de aceitação. Ranking público e gamificação avançada ficam fora do MVP.

## Recorte da primeira entrega

| Requisito | Parte que será entregue |
|---|---|
| RF01 | Consulta de O desconto fantasma por HTTP/JSON, com dados em memória |
| RF17 | Preparação do conteúdo público desse caso nos arquivos do projeto |

Autenticação, investigações individuais, dicas interativas, submissões, execução, persistência e progresso permanecem para etapas posteriores.

## Decisões pendentes

| Assunto | O que falta decidir |
|---|---|
| Contas | Forma de criação e recuperação de acesso |
| Investigações | Regras para reiniciar ou manter múltiplas investigações do mesmo caso |
| Visitantes | Quais casos poderão ser consultados sem autenticação no produto completo |
| Administração | Quais operações terão interface própria |
| Gamificação | Eventos elegíveis, pontuação, níveis, conquistas e eventuais penalidades |

Essas definições serão registradas antes da implementação das funcionalidades correspondentes.