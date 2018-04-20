# MyPet
Projeto android arquiteturado com base em MVP-Clean
# Clean
Além da estruturação pela arquitetura MVP, é implementada a arquitetura [Clean](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) de forma que a junção desta com o modelo MVP, faz nascer uma nova camada que chamaremos de "Domain".
![Diagrama](clean_architecture.png)
# Arquitetura
O projeto é estruturado em três camadas: Data, Domain e View. O diagrama demonstra as camadas e alguns elementos que exemplificam sua composição.
![Diagrama](LP3-Diagrama.png)
### Data
Esta camada é responsável pela persistência dos dados. Aqui se encontram as classes CRUD, DAO e o servidor de banco de dados. Além disso, o projeto prevê cacheamento de dados que, junto com a memoria principal, compoem os dados a serem manipulados pelo sistema.
### Domain
Esta camada é responsável pela lógica do sistema. Aqui se encontram as implementações do caso de uso, modelos de entidades e classes de processamento de dados.
### View
Esta camada é responsável pela apresentação das interfaces com o usuário. Aqui se encontram as interfaces gráficas, adaptadores de dispositivos e os apresentadores de interfaces.

