# Requisitos Não Funcionais

Status: Vigente

## Finalidade

Definir as condições de qualidade, segurança e operação do Colossus.

Os Requisitos Funcionais descrevem o que o participante pode fazer. Este documento estabelece em quais condições essas funcionalidades devem funcionar: sem expor dados, perder progresso, aceitar resultados falsos ou comprometer a aplicação ao executar código.

## Como ler este documento

**Situação atual:** todos os requisitos estão planejados. A inicialização local do Spring Boot já foi verificada, mas isso não comprova o atendimento integral dos requisitos abaixo.

- **Alta:** necessária à segurança, à integridade ou ao funcionamento da entrega correspondente.
- **Média:** importante para manutenção, diagnóstico e qualidade de uso.
- **Aplicação:** indica quando o requisito precisa ser verificado, sem estabelecer um cronograma rígido.

Metas ainda não definidas estão registradas como pendentes. Um requisito com parâmetros essenciais em aberto não poderá ser considerado integralmente atendido.

## Catálogo de requisitos

| ID | Qualidade ou restrição | Prioridade | Aplicação |
|---|---|---|---|
| RNF01 | Execução reproduzível | Alta | Primeira entrega em diante |
| RNF02 | Testabilidade e proteção contra regressões | Alta | Primeira entrega em diante |
| RNF03 | Integridade e evolução dos dados | Alta | Introdução da persistência |
| RNF04 | Segurança de acesso e sessão | Alta | Introdução da autenticação |
| RNF05 | Proteção de dados e segredos | Alta | Desde o início; ampliação com dados reais |
| RNF06 | Tratamento seguro de entradas e saídas | Alta | API; ampliação na interface e nas submissões |
| RNF07 | Isolamento do código submetido | Alta | Antes de habilitar execução |
| RNF08 | Limites de recursos e contenção de abuso | Alta | Antes de habilitar execução ou acesso de terceiros |
| RNF09 | Integridade da avaliação | Alta | Antes de habilitar validação real |
| RNF10 | Tratamento de falhas e repetições | Alta | Operações persistentes e execução |
| RNF11 | Diagnóstico e observabilidade | Média | Desde a primeira entrega |
| RNF12 | Desempenho e capacidade conhecidos | Média | Medição gradual; metas antes da publicação |
| RNF13 | Usabilidade, acessibilidade e compatibilidade | Média | Introdução da interface |
| RNF14 | Recuperação e continuidade operacional | Alta | Persistência; validação antes do uso por terceiros |

## Base de desenvolvimento

### RNF01 — Execução reproduzível

O projeto deverá ser compilado, testado e iniciado a partir dos arquivos versionados, para que seu funcionamento não dependa de configurações particulares do IntelliJ.

**Critérios verificáveis:**

- Uma cópia limpa do repositório permite executar o projeto com o JDK 21, o Maven Wrapper e os pré-requisitos documentados.
- A primeira entrega inicia sem banco, com as configurações necessárias registradas.
- O ambiente inicial utiliza dados didáticos e acesso local pelo endereço de loopback.
- Pré-requisitos, comandos e variáveis necessárias estão documentados; credenciais reais permanecem fora do repositório.

**Forma de verificação:** obter uma cópia limpa, seguir as instruções fora da IDE e registrar os resultados do build, dos testes e da inicialização.

### RNF02 — Testabilidade e proteção contra regressões

As regras e os contratos da aplicação deverão possuir verificações automatizadas que detectem falhas relevantes, permitindo evoluir o Colossus sem depender apenas de testes manuais.

**Critérios verificáveis:**

- A primeira consulta possui testes para caso existente, identificador inexistente e ausência de conteúdo reservado na resposta.
- Regras de investigação podem ser testadas sem executar código submetido por participantes.
- Novas funcionalidades acrescentam testes dos comportamentos e riscos que introduzem.
- A suíte pertinente à entrega passa pelo Maven fora da IDE.
- Uma alteração deliberadamente incorreta em uma regra coberta faz o teste correspondente falhar.

**Forma de verificação:** executar a suíte e realizar uma alteração controlada para confirmar que uma regressão é detectada, desfazendo-a após a verificação.

Não será adotada uma porcentagem arbitrária de cobertura como prova de qualidade.

### RNF03 — Integridade e evolução dos dados

Quando a persistência for introduzida, os dados confirmados deverão permanecer coerentes e recuperáveis após reinícios. A estrutura do PostgreSQL deverá evoluir por migrations do Flyway.

**Critérios verificáveis:**

- Reiniciar a aplicação preserva investigações, rascunhos e resultados confirmados.
- As migrations criam a estrutura em um banco vazio e atualizam uma versão anterior suportada.
- A evolução do banco não depende de alterações manuais não registradas nem de `ddl-auto=update`.
- Operações que precisam ser concluídas em conjunto não deixam registros parcialmente confirmados.
- Restrições de integridade impedem associações inválidas conforme o modelo de dados.

**Forma de verificação:** testar migrations, reinício e falhas durante operações persistentes em um PostgreSQL de teste.

## Segurança e proteção das informações

### RNF04 — Segurança de acesso e sessão

As sessões deverão proteger os dados individuais e as operações restritas, sustentando RF03, RF04 e RF16.

**Critérios verificáveis:**

- A autorização é aplicada pelo backend, inclusive quando a API é chamada diretamente.
- Uma sessão encerrada ou expirada deixa de autorizar operações.
- A disponibilização a terceiros utiliza HTTPS.
- Cookies de sessão utilizam `HttpOnly`, `Secure` no ambiente HTTPS e uma política `SameSite` compatível com o fluxo.
- Operações que alteram estado e utilizam autenticação por cookie possuem proteção contra CSRF.
- Caso sejam adotadas senhas locais, elas são armazenadas com hash apropriado pelo mecanismo do framework.

**Forma de verificação:** testar acessos com dois participantes e um administrador, inspecionar cookies e verificar rejeição de sessões inválidas e de operações sem a proteção exigida.

### RNF05 — Proteção de dados e segredos

Código salvo, credenciais e conteúdo reservado dos casos deverão ser protegidos contra exposição indevida.

**Critérios verificáveis:**

- Credenciais reais não são incluídas nos arquivos versionados.
- Respostas e logs não expõem senhas, identificadores de sessão ou segredos de infraestrutura.
- Soluções explicadas e testes reservados não aparecem em respostas antes da liberação prevista.
- Código e hipóteses de um participante não são expostos a outro.
- A política de retenção de código, resultados e logs é definida antes de receber dados reais de terceiros.

**Forma de verificação:** revisar arquivos, respostas e logs usando dados de teste identificáveis; executar os testes de acesso indevido de RF16.

**Pendente:** períodos de retenção e procedimentos de descarte.

### RNF06 — Tratamento seguro de entradas e saídas

O conteúdo recebido deverá ser tratado como dado não confiável, inclusive quando vier de código executado pela plataforma.

**Critérios verificáveis:**

- Entradas com formato inválido ou tamanho acima do limite definido são recusadas com resposta controlada.
- Código, hipóteses, logs e resultados são exibidos como texto, sem executar HTML ou JavaScript contido neles.
- Conteúdo enviado não pode determinar comandos de shell, caminhos de arquivos ou dependências do executor.
- Falhas de validação não expõem stack traces internos ou detalhes de infraestrutura ao participante.
- Saídas truncadas são identificadas; truncar a apresentação não transforma uma execução inválida em aprovação.

**Forma de verificação:** enviar formatos inválidos, conteúdo com marcação HTML, textos acima dos limites e saídas extensas, conferindo API e interface.

**Pendente:** limites de tamanho por tipo de entrada e saída.

## Execução de código e confiabilidade

### RNF07 — Isolamento do código submetido

A compilação e a execução deverão ocorrer em infraestrutura separada da API e do banco, para que o código de um caso não alcance recursos do produto.

**Critérios verificáveis:**

- Código submetido não é compilado nem carregado na JVM da API.
- O executor utiliza host separado da API e do banco.
- O ambiente do código não recebe credenciais, dados de outros participantes, socket Docker ou diretórios sensíveis do host.
- A rede do código submetido permanece bloqueada, inclusive para serviços internos.
- Cada execução utiliza ambiente descartável, sem reutilizar arquivos deixados por outra execução.
- A avaliação confiável permanece fora do ambiente do código submetido.

**Forma de verificação:** realizar ensaios controlados de acesso a arquivos, rede e recursos de outras execuções em ambiente descartável; registrar as restrições observadas e os limites da validação.

**Pendente:** escolha definitiva e validação do mecanismo de isolamento. A presença de um container, isoladamente, não comprova este requisito.

### RNF08 — Limites de recursos e contenção de abuso

Uma submissão não deverá consumir recursos sem controle nem impedir o funcionamento da API para os demais usuários.

Os limites devem estar configurados antes de habilitar a capacidade correspondente.

| Recurso ou operação | Critério verificável |
|---|---|
| Compilação e execução | Encerradas ao ultrapassar o tempo permitido |
| CPU e memória | Consumo contido pelos limites do ambiente |
| Processos e threads | Criação limitada; encerramento inclui processos descendentes |
| Arquivos e armazenamento temporário | Uso limitado e resíduos removidos após a execução |
| Saída do programa | Volume limitado; excesso não concede aprovação |
| Código e requisições recebidas | Tamanho máximo verificado antes do processamento |
| Fila e execuções simultâneas | Admissão controlada, com resposta quando a capacidade estiver esgotada |
| Solicitações por usuário | Frequência controlada conforme a política definida |

**Forma de verificação:** utilizar programas de teste com loop infinito, saída contínua e consumo excessivo de recursos; saturar a capacidade configurada e verificar contenção, limpeza e disponibilidade da API.

**Pendente:** valores, unidades e políticas de cada limite.

### RNF09 — Integridade da avaliação

O resultado deverá corresponder ao comportamento do código efetivamente enviado. O programa do participante não poderá escolher o próprio veredito.

**Critérios verificáveis:**

- O avaliador compara os resultados fora do ambiente submetido.
- O resultado permanece vinculado à execução, à submissão e à versão do caso.
- Mensagens como `PASS` ou “sucesso”, produzidas pelo próprio programa, não concedem aprovação.
- Saída incompleta, inválida ou acima dos limites não é aceita como resultado correto.
- Somente uma execução real, completa e aprovada pode sustentar a conclusão de RF13.
- Resultados incompatíveis com a execução correspondente são rejeitados.

**Forma de verificação:** usar submissões com resultados conhecidos, mensagens falsas de aprovação e saídas incompletas; conferir também os vínculos entre código, caso e resultado.

### RNF10 — Tratamento de falhas e repetições

Interrupções e retransmissões deverão produzir resultados previsíveis, sem perda silenciosa de operações confirmadas ou duplicação de efeitos.

**Critérios verificáveis:**

- Repetir o mesmo pedido de dica ou submissão preserva o efeito único definido em RF07 e RF09.
- Receber novamente um resultado não duplica conclusão ou recompensas.
- Falhas do executor são identificadas sem registrar a solução como aprovada ou incorreta por esse motivo.
- Uma execução interrompida é reconciliada ou encerrada conforme um prazo definido, sem permanecer indefinidamente em andamento.
- Resultados atrasados de uma tentativa expirada não substituem o resultado válido de outra tentativa.
- A indisponibilidade do executor não impede a consulta dos casos pela API.

**Forma de verificação:** repetir pedidos, interromper processos e entregar resultados atrasados em testes de integração, verificando estados e efeitos persistidos.

**Pendente:** prazos de reconciliação e política de novas tentativas.

## Uso, diagnóstico e operação

### RNF11 — Diagnóstico e observabilidade

Os registros operacionais deverão permitir descobrir onde uma falha ocorreu, sem confundir defeito da solução do participante com defeito da plataforma.

**Critérios verificáveis:**

- Erros da API possuem contexto suficiente para localizar a operação afetada.
- Quando houver submissões, é possível correlacionar requisição, submissão e execução.
- Erros de compilação, reprovações e falhas de infraestrutura são distinguíveis nos registros.
- Informações de saúde e métricas são disponibilizadas de acordo com sua necessidade de acesso.
- Logs e métricas respeitam RNF05; código submetido e segredos não são usados como rótulos de métricas.

**Forma de verificação:** provocar uma falha controlada e reconstruir seu percurso pelos registros, sem depender de informações privadas expostas na resposta.

### RNF12 — Desempenho e capacidade conhecidos

O comportamento sob carga deverá ser medido antes de estabelecer compromissos de desempenho ou disponibilizar o produto a terceiros.

**Critérios verificáveis:**

- As medições identificam versão do código, ambiente, volume de dados e carga aplicada.
- O tempo da API é medido separadamente do tempo de fila, compilação e execução.
- Os resultados registram distribuição dos tempos, erros e consumo de recursos.
- Os cenários e critérios de aprovação são definidos antes dos testes de aceitação de desempenho.
- A capacidade divulgada corresponde ao ambiente e à carga efetivamente verificados.

**Forma de verificação:** executar testes de carga reproduzíveis e comparar os resultados com as metas aprovadas.

**Pendentes:** carga esperada, metas de tempo de resposta, capacidade simultânea e eventual compromisso de disponibilidade. Nenhum valor numérico é assumido nesta versão.

### RNF13 — Usabilidade, acessibilidade e compatibilidade

A interface deverá permitir investigar o caso sem criar obstáculos desnecessários à leitura, à edição e à interpretação dos resultados.

**Critérios verificáveis:**

- O fluxo principal pode ser utilizado pelo teclado, com foco visível e controles identificáveis.
- Erros e estados de execução são apresentados em texto, sem depender apenas de cores.
- Código, evidências e resultados extensos continuam acessíveis, com rolagem quando necessária.
- Nos navegadores e tamanhos de tela definidos como suportados, os controles essenciais permanecem utilizáveis.
- Estados de carregamento, ausência de dados e falha de rede possuem mensagens compreensíveis.
- A interface distingue evidências preparadas de resultados de uma submissão real.

**Forma de verificação:** percorrer o fluxo com teclado, conteúdo extenso e falhas simuladas; conferir os ambientes suportados e registrar observações nos testes de usabilidade.

**Pendente:** conjunto de navegadores e dimensões de tela suportados.

### RNF14 — Recuperação e continuidade operacional

A operação deverá contar com procedimentos executáveis de inicialização, encerramento, diagnóstico e recuperação.

**Critérios verificáveis:**

- Os procedimentos documentados permitem iniciar e encerrar os componentes utilizados no ambiente.
- Quando houver persistência, um backup pode ser restaurado em ambiente de teste.
- Após a restauração, os dados abrangidos pelo backup permanecem íntegros e consultáveis.
- Atualizações possuem procedimento de recuperação compatível com a versão da aplicação e do banco.
- A restauração é verificada antes da disponibilização a terceiros; a existência de um arquivo de backup, sozinha, não comprova recuperação.

**Forma de verificação:** realizar um exercício de restauração e recuperação, registrando duração, dados recuperados e limitações encontradas.

**Pendentes:** frequência dos backups, perda máxima aceitável de dados e tempo máximo de recuperação.

## O que verificar em cada marco

| Marco | Verificações necessárias |
|---|---|
| **Primeira API local** | Execução reproduzível, testes da consulta, tratamento dos identificadores recebidos, ausência de conteúdo reservado e diagnóstico básico |
| **Habilitação da execução de código** | Isolamento, limites, avaliação confiável e tratamento de interrupções e repetições |
| **Disponibilização a terceiros** | Requisitos aplicáveis às funcionalidades expostas, metas operacionais definidas, proteção de acesso e recuperação verificadas |

Persistência, autenticação e interface devem atender aos respectivos requisitos quando forem introduzidas. Não é necessário implementar essas capacidades para concluir a primeira consulta local.

## Registro de atendimento

O atendimento de um requisito deverá apontar para evidências nos registros de testes ou em Checkpoints e Evidências, informando:

- Versão ou commit avaliado.
- Ambiente e procedimento utilizados.
- Resultado observado.
- Limitações e pendências.

A aprovação vale para o escopo verificado. Mudanças relevantes na arquitetura, nas dependências ou no ambiente exigem revisar as verificações afetadas.