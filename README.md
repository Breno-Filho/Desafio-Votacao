# 🗳️ Sistema de Votação Cooperativa

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot que permite gerenciar pautas de votação, sessões e votos de associados. Ele simula o funcionamento de um sistema de votação para cooperativas.

## 📌 Funcionalidades

- Cadastro de **pautas** para votação
- Abertura de **sessões de votação** com tempo determinado
- Registro de **votos** por CPF (válido e único por sessão)
- Cálculo e exibição do **resultado da votação**
- Integração com serviço externo de **validação de CPF**
- Documentação automática com **Swagger**

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (memória)
- OpenFeign
- Swagger (Springfox)
- Maven

## 🏗️ Estrutura do Projeto

```
src/
├── controller        # Controladores REST
├── dto              # Objetos de transferência de dados
├── model            # Entidades JPA
├── repository       # Interfaces JPA
├── service          # Regras de negócio
├── exception        # Tratamento de exceções
├── client           # Integração com serviços externos
└── config           # Configurações (ex: Swagger)
```

## 🔧 Execute o projeto com Maven

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/Desafio-Votacao.git
   cd "Desafio-Votacao/Back-end Votacao/project"

   ```

2. Execute o projeto com Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

## 🌐 Acesse a documentação da API no navegador

Depois de iniciar o projeto, acesse no navegador:
```
http://localhost:8080/swagger-ui/
```

## 🧪 Testes

O projeto pode ser testado via Swagger ou com ferramentas como Postman. Exemplo de fluxo:

1. **Criar pauta:** `POST /pautas`
2. **Abrir sessão:** `POST /sessao`
3. **Registrar voto:** `POST /votos`
4. **Consultar resultado:** `GET /pautas/{id}/resultado`

## ✅ Regras de Negócio

- O voto só é aceito dentro do período da sessão.
- Um CPF só pode votar uma vez por pauta.
- O CPF é validado por uma API externa (mockada/local).

## 📄 Requisitos do Desafio

✔️ Cadastro de pauta  
✔️ Abertura de sessão com duração configurável  
✔️ Registro de voto com verificação de unicidade por CPF  
✔️ Cálculo automático do resultado da votação  
✔️ Validação de CPF via serviço externo  
✔️ Swagger para documentação  

## 👨‍💻 Autor

Breno — Bacharelado em Sistemas de Informação  
Desenvolvedor do desafio técnico de API REST para sistema de votação cooperativa.
