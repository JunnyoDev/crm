📋 Sistema CRM em Java (Console + MySQL)

Projeto CRM simples em Java, executado via console, utilizando JDBC para conexão com MySQL.
O sistema permite realizar CRUD completo de usuários, com busca por CPF e criação automática do banco e tabela.

🚀 Funcionalidades

✅ Criar banco de dados automaticamente

✅ Criar tabela usuarios automaticamente

✅ Cadastrar usuários

✅ Listar usuários

✅ Atualizar usuários

✅ Excluir usuários

✅ Buscar usuário por CPF

✅ Validação de CPF e e-mail únicos no banco

🛠️ Tecnologias Utilizadas

Java (JDK 17 ou superior recomendado)

JDBC (MySQL Connector/J)

MySQL

IntelliJ IDEA / VS Code

Git & GitHub

📂 Estrutura do Projeto
CRM/
├── src/
│   └── Main.java
│   └── DatabaseConfig.java
├── lib/
│   └── mysql-connector-j-x.x.x.jar
├── config.properties
├── .gitignore
└── README.md

⚙️ Configuração do Banco de Dados
1️⃣ Criar o arquivo config.properties
db.urlBase=jdbc:mysql://localhost:3306/
db.database=sistema_java
db.user=root
db.password=sua_senha_aqui

2️⃣ Classe DatabaseConfig

O projeto utiliza uma classe responsável por ler o arquivo config.properties.

Ela carrega automaticamente as configurações do banco.

🔌 Dependência MySQL (Obrigatório)

Baixe o driver JDBC oficial:

🔗 https://dev.mysql.com/downloads/connector/j/

Adicione o .jar na pasta lib/ e configure como dependência no projeto.

▶️ Como Executar o Projeto

Certifique-se que o MySQL está rodando

Configure corretamente o config.properties

Execute a classe Main

O sistema exibirá o menu no console:

========== SISTEMA JAVA (CRM) ==========
1 - Cadastrar novo usuário
2 - Listar usuários
3 - Atualizar usuário
4 - Excluir usuário
5 - Buscar por CPF
6 - Sair

🧪 Exemplo de Tabela Criada
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE
);
