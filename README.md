# 🧾 Sistema de Controle de Medicamentos

## 📘 Descrição
O **Sistema de Controle de Medicamentos** é uma aplicação desenvolvida em **Java com JavaFX**, que permite gerenciar o estoque de medicamentos de uma farmácia.  
O sistema utiliza **interface gráfica**, **persistência em arquivo CSV** e segue o padrão **MVC (Model-View-Controller)** para melhor organização e manutenção do código.

---

## ⚙️ Funcionalidades

- **Cadastrar medicamentos:** Permite inserir um novo medicamento informando nome, validade, quantidade, preço e se é controlado.  
- **Listar medicamentos:** Exibe todos os medicamentos cadastrados em uma tabela.  
- **Consultar medicamentos:** Busca um medicamento pelo nome e exibe suas informações.  
- **Excluir medicamentos:** Remove um medicamento do sistema.  
- **Persistência de dados:** Todos os registros são armazenados no arquivo `data/medicamentos.csv`, sendo carregados automaticamente ao iniciar o programa.

---

## 🧩 Estrutura do Projeto

```bash
medicamentos/
│
├── data/
│   └── medicamentos.csv
│
├── src/
│   └── main/
│       ├── java/
│       │   └── org/medicamentos/
│       │       ├── controller/
│       │       │   └── MainController.java
│       │       ├── model/
│       │       │   ├── Medicamentos.java
│       │       │   └── Fornecedor.java
│       │       ├── utils/
│       │       │   └── PathFXML.java
│       │       ├── view/
│       │       │   └── Main.java
│       │       └── module-info.java
│       │
│       └── resources/
│           └── view/
│               └── MainView.fxml
│
└── pom.xml

## ▶️ Como Executar o Projeto

### 1️⃣ Abrir o projeto
Abra o projeto no **IntelliJ IDEA** ou na IDE de sua preferência.

---

### 2️⃣ Configurar o SDK
- Vá em `File > Project Structure > Project SDK`  
- Selecione o **JDK 17** (ou superior)

---

### 3️⃣ Configurar o JavaFX (caso necessário)
Se o JavaFX não estiver configurado, adicione as bibliotecas manualmente  
ou utilize o **Maven** para gerenciar dependências.

---

### 4️⃣ Executar o programa
No **IntelliJ IDEA**:

1. Abra a classe `Main.java` no pacote `org.medicamentos.view`  
2. Clique no botão **Run ▶️**  
   ou execute o comando no terminal:

   ```bash
   mvn clean javafx:run
