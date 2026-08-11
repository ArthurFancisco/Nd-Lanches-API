# ND Lanches API

![Java 21](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?logo=docker&logoColor=white)

API REST do cardápio digital ND Lanches. O backend organiza produtos, adicionais, banners, informações da loja, pedidos, uploads de imagens e validação do painel administrativo.

> **Status:** projeto de portfólio em evolução.

## Recursos

- catálogo de produtos e adicionais;
- gerenciamento de banners e dados da loja;
- fluxo de pedidos;
- upload de imagens com Cloudinary;
- persistência com PostgreSQL e Spring Data JPA;
- cache com Caffeine;
- endpoint de saúde;
- configuração por variáveis de ambiente;
- execução com Docker Compose.

## Arquitetura

~~~text
controller → service → repository → entity
                 ↘ DTO e config
~~~

## Stack

- Java 21
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- PostgreSQL
- Caffeine Cache
- Cloudinary
- Maven
- Docker

## Como executar

~~~bash
git clone https://github.com/ArthurFancisco/Nd-Lanches-API.git
cd Nd-Lanches-API
cp .env.example .env
docker compose up --build
~~~

A API será iniciada em http://localhost:8080.

## Variáveis de ambiente

| Variável | Descrição |
|---|---|
| POSTGRES_USER | usuário local do PostgreSQL |
| POSTGRES_PASSWORD | senha local do PostgreSQL |
| ADMIN_KEY | chave exclusiva do painel administrativo |
| CLOUDINARY_URL | credencial de integração com Cloudinary |
| PORT | porta HTTP, padrão 8080 |

Nunca publique valores reais dessas variáveis.

## Frontend

A interface que consome esta API está em [ArthurFancisco/NdLanches](https://github.com/ArthurFancisco/NdLanches).

## Licença

Distribuído sob a licença Apache 2.0.

## Autor

Desenvolvido por [Arthur Amancio Francisco](https://www.linkedin.com/in/arthur-amancio-francisco/) como projeto de estudo e portfólio.
