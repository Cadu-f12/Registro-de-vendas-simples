# 🧾 Registro de Vendas Simples

Este projeto está sendo desenvolvido com o objetivo de treinar e consolidar conhecimentos em:

Arquitetura de Software (MVC)

Interfaces Humano-Computador (IHC)

Back-end (Java)

A proposta inicial é criar um software simples para registro de vendas básicas de um estabelecimento, permitindo armazenar e organizar informações como produtos, valores e vendedores.

# 🤝 Contribuições são muito bem-vindas!
Se você quiser ajudar:

Sugerindo melhorias no código

Apontando boas práticas

Identificando possíveis bugs, falhas de integridade ou melhorias de arquitetura

Fique à vontade para abrir uma issue. Todas serão lidas com atenção e resolvidas na medida do possível 🚀

Este é um projeto em constante aprendizado, então toda ajuda é bem-vinda!

---

## 🛠️ Como executar o programa de Registro de Vendas

Antes de iniciar, **leia atentamente os avisos abaixo**. O funcionamento correto do programa depende de uma configuração específica do banco de dados.

---

### ⚠️ Avisos importantes

* O programa **depende do MySQL Server** para funcionar.
* O **nome do banco de dados** e da **tabela** devem ser **exatamente iguais** aos descritos neste tutorial.
* Caso haja qualquer diferença nos nomes ou na estrutura, o programa **não irá funcionar corretamente**.

---

## 📥 1. Clonando o repositório

Primeiro, clone o repositório em uma pasta de sua preferência:

```bash
git clone https://github.com/Cadu-f12/Registro-de-vendas-simples.git
```

---

## 🐬 2. Instalando o MySQL

Certifique-se de que o **MySQL Server** esteja instalado no seu sistema operacional e em funcionamento.

> 💡 Você pode verificar isso acessando o MySQL pelo terminal ou por uma ferramenta gráfica como **MySQL Workbench**.

---

## 🗄️ 3. Criando o banco de dados e a tabela

No MySQL, crie o banco de dados e a tabela **exatamente como abaixo**.

⚠️ **Apenas copie e cole o código SQL e rode de uma vez só**:

```sql
CREATE DATABASE registrodevendas;

USE registrodevendas;

CREATE TABLE vendas (
	id_venda INT PRIMARY KEY AUTO_INCREMENT,
    data_registro DATE,
    forma_pagamento ENUM('pix', 'dinheiro', 'cartao_credito', 'cartao_debito'),
    nome_vendedor ENUM('carlos', 'viviane', 'helena'),
    quantidade INT,
    nome_produto VARCHAR(75),
    total DECIMAL(10, 2)
);
```

---

## 🔐 4. Configurando a conexão com o banco de dados

Agora vamos configurar as credenciais de acesso ao banco.

### 📁 Criando o arquivo de configuração

1. Dentro da pasta `src/` do projeto, crie um arquivo chamado:

```
bd.properties
```

O caminho final deve ficar assim:

```
src/bd.properties
```

---

### ✏️ Preenchendo o arquivo bd.properties

Dentro do arquivo bd.properties, adicione as seguintes chaves (apenas copie e cole):

```
url=jdbc:mysql://localhost:3306/registrodevendas
user=
password=
```

### 📌 Observações importantes:

A url já está configurada para o banco registrodevendas

Preencha:

user → usuário do MySQL (ex: root)

password → senha do MySQL

Caso seu MySQL esteja rodando em outra porta ou host, ajuste a url conforme necessário.

---

## ▶️ 5. Executando o programa

Com o banco configurado e as credenciais corretas, o programa já estará pronto para ser executado 🎉
Basta rodar a aplicação normalmente pela sua IDE.