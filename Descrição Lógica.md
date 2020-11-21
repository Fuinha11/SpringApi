# Descrição Lógica
Este arquivo visa explicar a lógica usada nas tomadas de decisão para todo o desenvolvimento da aplicação 
e também explicar seu funcionamento.

## Tecnologias
Uma breve explicação do motivo do uso de cada uma das tecnologias utilizadas.
- Java 
    - Java é uma das mais populares linguageens de programação e se mostra muito 
    consistente em aplicações. Simples e poderosa e possui uma série de Frameworks 
    confiáveis que auxiliam no desenvolvimento de aplicações.
- Maven 
    - Maven é um excelente gerenciador de projetos, dependências e builds e ainda 
    proporciona uma série de facilidades para o desenvolvedor.
- Spring Boot 
    - Spring Boot é um rei no mercado de aplicações em Java, sendo uma plataforma muito
    confiável e versátil onde podemos adicionar módulos de acordo com a necessidade 
    da aplicação.
- H2 database
    - O banco de dados H2 permite de uma forma muito simples temos um banco de dados que
    inicia juntamente com a aplicação. Para testes e POCs é rápido e de fácil aplicação.
- ActiveMQ
    - Da mesma forma que o banco H2 podemos ter um gerenciador de filas em memória, 
    de forma que estará sempre disponível com a aplicação sem precisarmos de nenhum
    fator externo. Facilitando o desenvolvimento e testes.
- Lombok
    - Um grande auxiliador no desenvolvimento de código em java, possibilita com o uso de anotações,
    evitar escrever uma grande quantidade de código padrão e repetitivo. 
- Swagger UI
    - Outro gigante do mercado, esse em documentações dinâmicas para APIs. 
    Swagger permite muito facilmente disponibilizar uma interface, que ao mesmo tempo 
    que documenta os Endpoints, também proporciona uma forma de interagir
    com os mesmo.
    
## Organização
O sistema foi organizado em 3 camadas, cada uma com uma pacote correlato, 
E um pacote que atravessa as 3 camadas.

### Data
O pacote Data visa armazenar todas as entidades lógicas do sistema, como também
proporcionar uma forma de acesso a essas entidades via Repositories. Utilizamos 
esse formato com intúito de tornar a manutenção de qualquér entidade mais simples
no futuro.

### Service
O pacote Service representa a camada de negócio da aplicação. Toda a lógica de negócio
fica armazenada nessa camada, e temos um arquivo para cada "Área de negócio". Isso
torna fácil localizar e alterar ou consertar algum requerimento.

### Web 
Esse pacote representa a camada de entrada e saída de informação da aplicação via Web.
Aqui tratamos e validamos requisições e também trabalhamos os dados que serão 
enviados para fontes externas.

### Exception
O pacote Exception é o único pacote que age em todas as camadas, ele visa armazenar
todo tipo de excessão que possa ser levantada no funcionamento da aplicação. Isso 
inclui exeções de negócio, de forma a tornar o manejo de situações fora do esperado 
mais fácil. Quando algo inesperado acontece levantamos uma exceção que contem uma 
mensagem amigável que informará o usuário qual o problema com a requisição, e até
mesmo se há algum problema com o próprio sistema.
 
    
## Funcionamento
O funcionamento da Aplicação foi conforme discrito no documento de requisitos, 
tentando torna-la o mais simples possível.

De forma muito simples temos 4 Entidades de Domínio:

- Associado
- Pautas
- Sessões de Votação
- Votos

O Intuito do sistema é permitir que uma Pauta seja criada. Uma, ou mais, 
Sessões de votação sejam criadas por Pauta. E que cada Associado possa votar
uma única vez por sessão de votação.

Votos só podem ser feitos enquanto a Sessão está aberta. Quando criada,
cada Sessão determina uma duração da janela onde os fotos podem ser feitos.

Ao Final de uma Votação o seu resultado pode ser visualizado.

## Uso Simples
Para utilizar o sistema, assumindo que ja tenha seguido as instruções em 
READ-ME para coloca-lo em operação, comece acessando o 
Swagger-ui em: _**http://localhost:8080/springapi/swagger-ui.html**_.

Então crie uma Pauta com: _**/api/pautas**_.

Com tal Pauta criada use seu Id, fornecido como retorno da requisição, 
para abrir uma sessão de votação em: _**/api/sessoes**_. 

Então vote nessa Sessão, utilize o Id obtido na chamada anterior.
Cada Associado pode votar uma vez, e contanto que ele seja ABLE_TO_VOTE
de acordo com o sistema de validação externo. 
Votos são criados em: _**/api/sessao/{id da sessao}/vote**_

Note que os Associados já estão cadastrados e é preciso usar
um Id válido para fazer seu voto. Você pode ver, alterar e criar 
Associados utilizando a interface do banco de dados. 
Ou utilize os ids 1, 2, 3, 4, 5, 6, 7, 8, 9, 10.

Quando a Sessão for encerrada, ou por tempo, ou porque todos os 
Associados já votaram, faça uma chamada em: _**/api/sessao/{id da sessao}/resultados**_
para obter o resultado da Sessão
    
    
## Tarefas Bonus 1
Para a tarefa bonus 1, simplesmente adicionei um estágio de validação durante
a criação do Voto, onde, utilizando um serviço dedicado para tanto, fazemos
uma chamada ao endpoint externo e verificamos o status do associado.

Duas Exeções dedicadas foram criadas para lidar com esse passo.

## Tarefa Bonus 2
Como mencionado acima utilizei o ActiveMQ em memória para simular um
serviço de filas. 

Foi Criado um produtor que posta o ID da sessão de votação
quando ela foi concluída e não foi sincronizada. Para o mesmo utilizei uma tarefa
programada na própris aplicação, que faz a verificação das sessões. Esta não é 
necessariamente a melhor forma de fazer algo do gênero, talvez essa task devesse
ficar em uma aplicação a parte ou até mesmo no banco de dados, mas essa é 
uma forma incrivelmente simples de completar esse requisito.

Também criei um Consumidor que recebe da fila o ID sa sessão 
e então a marca como sincronizada.

## Tarefa Bonus 4
Para o versionamento da Aplicação utilizei o padrão GitFlow, com pull requests entre as brachs.
Esse padrão é bem robusto e faz com que não tenhamos branchs mal identificadas
ou que passemos código legado para produção.

Mantendo sempre a branch main (antiga master) com o código em produção, 
e a branch dev atualizada com todas as features simultâneas quando terminadas.

O desenvolvimento é sempre feito em um branch filha de dev feature/{nome-da-feature},
e atualizada de dev sempre que ele (dev) recebe um PR. Quando a feature está pronta
e testada, então criamos um PR de feature para dev.

Para Releases utilizamos uma branch release/x.x.x sendo esse número o numero de versionamento
da aplicação. Seguindo o padrão: release.feature.fix.

