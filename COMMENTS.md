Claro Brasil Challenge - Android
===================

----------

Porque da arquitetura utilizada
-------------

Arquitetura MVVM utilizada, pois nesse padrão de arquitetura é realizada a separação das camadas muito bem, além de ser o padrão de arquitetura recomendado pela Google.
----------

Bibliotecas utilizadas
-------------
- Room - Para persistência de dados no dispositivo do cliente
- Coroutines - Para realizar tarefas assíncronas e não utilizar a Main Thread travando-a
- Navigation - Android Navigation para navegar entre os fragmentos de visualização ao invés de utilizar Activities
- Lifecycle - Para controlar o ciclo de vida da ViewModel e não perder os dados ao mudar a configuração do fragmento
- RecyclerView - Para gerenciar as listas de filmes a ser mostrado
- Picasso - Para gerenciar as imagens
- Retrofit - Para fazer o gerenciamento das requisições a API externa
- Moshi - Para gerenciar a deserialização da resposta JSON da API externa

-------------

O que melhoraria se tivesse mais tempo
-------------
- Implementaria todos os TODOS que não implementei
- Dividiria melhor as camadas
- Implementaria todos os requisitos que não foram implementados
- Implementaria a função de fazer review dos filmes pelo próprio aplicativo
- Adicionaria AdMob como forma de monetização
- Adicionaria Google Analytics para analizar a perfomance do aplicativo

--------------
Requisitos obrigatórios que não foram entregues e porquê
--------------
- Indicar para o usuário quando não há resultados na busca / filmes favoritados - Porquê: Não implementei a função de persistir o filme favoritado e utilizei muito tempo para alterar o fragment_movies_item_list para fragment_movies e muito tempo estudando a linguagem Kotlin e Architecture Components
- Realizar requisições assíncronamente (Coroutines) - Porquê: Utilizei muito tempo estudando a linguagem Kotlin e acabei não utilizando coroutines em todas as operações assíncronas
- Orientação landscape durante a exibição do trailer - Porquê:  Utilizei muito tempo estudando a linguagem Kotlin e não consegui implementar o player de vídeo
- Pelo menos um teste automatizado de interface - Porquê: Utilizei muito tempo estudando a linguagem Kotlin e não consegui implementar nenhum teste no projeto

----------------
Agradeço a oportunidade concedida e com a implementação desse projeto aprendi a implementar um projeto Android profissionalmente.