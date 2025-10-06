# 💊 Sistema de Gerenciamento de Medicamentos - JavaFX

Este projeto foi desenvolvido para a disciplina de **Programação Orientada a Objetos** e tem como objetivo criar um **sistema de controle de estoque de medicamentos de uma farmácia**, utilizando **JavaFX**, **POO**, **manipulação de arquivos CSV** e **Stream API**.

---

## 🚀 Funcionalidades

✅ Cadastrar um novo medicamento  
✅ Excluir um medicamento existente  
✅ Consultar medicamento por código  
✅ Listar todos os medicamentos cadastrados  
✅ Relatórios usando Stream API:
  - Medicamentos próximos ao vencimento (30 dias)
  - Medicamentos com estoque baixo (menos de 5 unidades)
  - Valor total do estoque por fornecedor
  - Medicamentos controlados x não controlados  

---

## 🧩 Estrutura do Projeto

src/
└─ main/
├─ java/
│ ├─ org/medicamentos/controller/ → Controladores JavaFX
│ ├─ org/medicamentos/model/ → Classes de domínio (Medicamento, Fornecedor)
│ ├─ org/medicamentos/repository/ → Repositório e manipulação do CSV
│ └─ org/medicamentos/utils/ → Utilitários (CsvUtil, validações, etc.)
└─ resources/
├─ org/medicamentos/view/ → Telas FXML do JavaFX
└─ medicamentos.csv → Arquivo de dados (salvo automaticamente)


---

## ⚙️ Tecnologias Utilizadas

- **Java 17+**
- **JavaFX 21+**
- **IntelliJ IDEA** (ou Eclipse)
- **API Stream (Java 8+)**
- **CSV para persistência de dados**

---

## 📥 Como Clonar o Projeto

Abra o terminal e execute:

```bash
git clone https://github.com/SEU-USUARIO/sistema-farmacia.git


Em seguida:

cd sistema-farmacia
