# RestAssured - [petStore API|https://petstore.swagger.io/]
Test Cases for petstore API using RestAssured and Java

# Requisitos do projeto:
- Cadastrar novo pedido de pet com sucesso (POST /store/order)
- Pesquisar por um pet inexistente (GET /pet/{petId})    
- Atualizar dados de um pet existente (PUT /pet)  
- Pesquisar por pets com status “pending” (GET /pet/findByStatus)

# Development
- Linguagem de programação: Java
- Biblioteca para os testes API: RestAssured
- Biblioteca para geração dos dados para teste: Faker
- Como uma boa prática de testes, que considera que o estado final do software deve ser igual ao inicial, em teste cases onde cadastrar ou atualizar um novo objeto no software era preciso, foi realizado a deleção do objeto ao final do teste.
