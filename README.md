# 🚨 SafeAlert

**SafeAlert** é uma plataforma de comunicação para reportar e acompanhar eventos extremos em tempo real, como desastres naturais, acidentes e situações de emergência. A solução visa auxiliar cidadãos e autoridades com informações rápidas e confiáveis.

---

## 📌 Funcionalidades Principais

- Cadastro e autenticação de usuários
- Criação de alertas com título, descrição, local e tipo do evento
- Listagem de alertas públicos em tempo real
- Edição e exclusão de alertas por usuários autorizados
- Interface web responsiva

---

## 🧪 Tecnologias Utilizadas

- **Back-end:** Java 17 + Spring Boot
- **Segurança:** Spring Security + JWT
- **Banco de Dados:** PostgreSQL (Utilizado no DEPLOY) ou Oracle Database (Utilizado para testes em localhost)
- **Deploy:** Render.com
- **Gerenciamento de dependências:** Maven
- **Documentação da API:** Swagger

---

## 🚀 Deploy da Aplicação

A aplicação está disponível no seguinte link:  
🔗 [https://gs-javaadvanced.onrender.com](https://gs-javaadvanced.onrender.com)

---

## 📁 Como Rodar Localmente

### Pré-requisitos

- Java 17
- Maven
- Oracle Database)
- IDE (Eclipse ou VS Code)

---

# 🔧 Passos para execução

## ✅ Opção 1: Terminal

### 1. [Clone o repositório](https://github.com/thejaobiell/GS-JavaAdvanced)
```bash
git clone https://github.com/thejaobiell/GS-JavaAdvanced.git
```

### 2. Acesse o diretório do projeto
```bash
cd GS-JavaAdvanced/safealert
```

### 3. Configure o arquivo application.properties para rodar com Oracle:
```bash

spring.application.name=safealert

spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521/orcl
spring.datasource.username=<SEU RM>
spring.datasource.password=<SUA SENHA>
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=update

management.endpoints.web.exposure.include=health
```

### 4. Rode o projeto
```bash
./mvnw spring-boot:run
```

---

## ✅ Opção 2: Importar o projeto no Eclipse IDE

### 1. Abra o Eclipse IDE

### 2. Vá em: File > Import...

### 3. Selecione: Maven > Existing Maven Projects

### 4. Clique em "Browse" e selecione a pasta do projeto (ex: GS-JavaAdvanced/safealert)

### 5. Marque o arquivo pom.xml

### 6. Clique em "Finish" para concluir a importação

### 7. Configure o arquivo application.properties com os dados do Oracle (veja passo anterior)

### 8. Execute o projeto com: Run As > Spring Boot App

---

## 🔐 Credenciais para TOKEN JWT

* **username:** `admin@safealert.com`
* **password:** `2tdsb-2025`

> Use o método **POST** caso for utilizar POSTMAN/INSOMNIA para conseguir o TOKEN JWT

---

## 🎥 Vídeo de Demonstração

[Link para o vídeo explicativo da solução — até 10 minutos](https://youtu.be/fBCf_oPNepI)

---

## 👨‍💻 Membros do Grupo

* **João Gabriel Boaventura Marques e Silva** – RM554874 – 2TDSB2025
* **Léo Mota Lima** – RM557851 – 2TDSB2025
* **Lucas Leal das Chagas** – RM551124 – 2TDSB2025
