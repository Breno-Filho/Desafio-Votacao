# API de Votação Cooperativa

API REST para gerenciamento de pautas e sessões de votação em cooperativas, desenvolvida com Spring Boot.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 2.7.10
- Spring Data JPA
- Banco de dados H2 (em memória)
- Maven
- Swagger para documentação da API

## Funcionalidades

- Cadastro de pautas
- Abertura de sessões de votação com tempo configurável
- Registro de votos (apenas "Sim" ou "Não")
- Contabilização de resultados de votação
- Validação de CPF para votação

## Como Executar o Projeto

1. Clone o repositório
2. Execute o comando: `./mvnw spring-boot:run`
3. A aplicação estará disponível em `http://localhost:8080`
4. Acesse a documentação Swagger em `http://localhost:8080/swagger-ui/`
5. Acesse o console H2 em `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:votingdb`, Usuário: `sa`, Senha: em branco)

## Endpoints da API

### Pautas

- `POST /api/v1/pautas` - Cria uma nova pauta
- `GET /api/v1/pautas` - Lista todas as pautas
- `GET /api/v1/pautas/{id}` - Busca uma pauta pelo ID

### Sessões de Votação

- `POST /api/v1/sessoes` - Abre uma sessão de votação
- `GET /api/v1/sessoes/{pautaId}/resultado` - Obtém resultado da votação

### Votos

- `POST /api/v1/votos` - Registra um voto

## Exemplo de Requisições

### Criar Pauta

```
POST /api/v1/pautas
{
  "titulo": "Aprovação de orçamento anual",
  "descricao": "Votação para aprovação do orçamento anual da cooperativa"
}
```

### Abrir Sessão de Votação

```
POST /api/v1/sessoes
{
  "pautaId": 1,
  "minutosValidade": 5
}
```

### Registrar Voto

```
POST /api/v1/votos
{
  "pautaId": 1,
  "cpfAssociado": "12345678901",
  "opcaoVoto": "SIM"
}
```

### Obter Resultado

```
GET /api/v1/sessoes/1/resultado
```
