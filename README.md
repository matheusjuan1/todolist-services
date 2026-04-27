<div align="center">
     <img src="https://github.com/user-attachments/assets/cee3accc-ea2d-4b32-84a5-7ee6966feb87" alt="Banner">
</div>


# To-Do List Services

Esta API foi desenvolvida em Spring Boot para gerenciar tarefas em um aplicativo To-Do List. Ela oferece endpoints seguros e eficientes para operações CRUD, permitindo a criação, leitura, atualização e exclusão de tarefas.

<div align="center">
     <img src="https://img.shields.io/badge/Java-v17-blue">
     <img src="https://img.shields.io/badge/Gradle-v8.13-blue">
     <img src="https://img.shields.io/badge/Spring%20Boot-v3.4.3-brightgreen">
     <img src="https://img.shields.io/badge/Lombok-v1.18.36-red">
     <img src="https://img.shields.io/badge/H2-v2.3.232-darkblue">
     <img src="https://img.shields.io/badge/PostgreSQL-v42.7.5-blue">
     <img src="https://img.shields.io/badge/Bcrypt-v0.10.2-lightgrey">
     <img src="https://img.shields.io/badge/JWT-v4.4.0-BA55D3">
     <img src="https://img.shields.io/badge/JUnit-v5.11.4-darkred">
     <img src="https://img.shields.io/badge/Mockito-v5.14.2-darkgreen">
</div>

## 🚀 Como executar a API
### Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas: **Java 17+**, **Gradle 8+**. Além disto é bom ter um editor para trabalhar com o código como [**VSCode**](https://code.visualstudio.com/)
<br></br>
```shell
# Clone este repositório
git clone https://github.com/matheusjuanf/todolist-services

# Acesse a pasta do projeto no terminal/cmd
cd todolist-services

# Execute a aplicação no perfil de desenvolvimento (escolha o comando conforme seu sistema operacional)

## Para Linux/macOS:
./gradlew bootRun --args='--spring.profiles.active=dev'

## Para Windows:
gradlew.bat bootRun --args="--spring.profiles.active=dev"

# O servidor iniciará na porta:8080 - acesse http://localhost:8080
```

## 🗂️ Documentação
Você pode acessar a documentação interativa da API utilizando o [Swagger UI](https://swagger.io/tools/swagger-ui/).
#### Passos
1. Certifique-se de que a API está em execução localmente
2. Abra seu navegador e acesse a URL:
     ```
     http://localhost:8080/swagger-ui.html
     ```
Isso irá exibir a interface gráfica do [Swagger](https://swagger.io/tools/swagger-ui/), onde você pode visualizar todos os endpoints da API, suas descrições e realizar chamadas diretamente a partir da interface.

## 📝 Licença

Este projeto está sob a licença MIT.

Feito por Matheus Juan. [Entre em contato](https://www.linkedin.com/in/matheusjuanf/)

<div align="center">
     <img width="60" alt="Image" src="https://github.com/user-attachments/assets/efd1d014-148c-4ae8-8dbd-81850fadf9ba" />
</div>
