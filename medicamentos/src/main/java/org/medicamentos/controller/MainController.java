package org.medicamentos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.medicamentos.model.Medicamentos;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class MainController {

    @FXML private TextField nomeField;
    @FXML private DatePicker dataValidadePicker;
    @FXML private TextField estoqueField;
    @FXML private TextField precoField;
    @FXML private CheckBox controladoCheckBox;

    @FXML private TableView<Medicamentos> medicamentosTable;
    @FXML private TableColumn<Medicamentos, String> codigoColumn;
    @FXML private TableColumn<Medicamentos, String> nomeColumn;
    @FXML private TableColumn<Medicamentos, LocalDate> validadeColumn;

    private ObservableList<Medicamentos> lista = FXCollections.observableArrayList();
    private static final String CSV_PATH = "data/medicamentos.csv";

    @FXML
    public void initialize() {
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        validadeColumn.setCellValueFactory(new PropertyValueFactory<>("dataValidade"));

        medicamentosTable.setItems(lista);
        carregarCSV();
    }

    private void carregarCSV() {
        try {
            if (!Files.exists(Paths.get(CSV_PATH))) {
                Files.createDirectories(Paths.get("data"));
                Files.createFile(Paths.get(CSV_PATH));
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_PATH))) {
                    bw.write("codigo;nome;descricao;principioAtivo;dataValidade;quantidadeEstoque;preco;controlado");
                    bw.newLine();
                }
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
                lista.clear();
                br.readLine(); // pular cabeçalho
                String line;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(";");
                    Medicamentos m = new Medicamentos(
                            fields[0],
                            fields[1],
                            fields[2],
                            fields[3],
                            LocalDate.parse(fields[4]),
                            Integer.parseInt(fields[5]),
                            new BigDecimal(fields[6]),
                            Boolean.parseBoolean(fields[7])
                    );
                    lista.add(m);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_PATH))) {
            bw.write("codigo;nome;descricao;principioAtivo;dataValidade;quantidadeEstoque;preco;controlado");
            bw.newLine();
            for (Medicamentos m : lista) {
                bw.write(String.join(";",
                        m.getCodigo(),
                        m.getNome(),
                        m.getDescricao(),
                        m.getPrincipioAtivo(),
                        m.getDataValidade().toString(),
                        String.valueOf(m.getQuantidadeEstoque()),
                        m.getPreco().toString(),
                        String.valueOf(m.isControlado())
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSalvar() {
        try {
            Medicamentos m = new Medicamentos(
                    "", // código vazio
                    nomeField.getText(),
                    "", // descrição vazia
                    "", // princípio ativo vazio
                    dataValidadePicker.getValue(),
                    Integer.parseInt(estoqueField.getText()),
                    new BigDecimal(precoField.getText()),
                    controladoCheckBox.isSelected()
            );

            lista.add(m);
            salvarCSV();
            medicamentosTable.refresh();
            limparCampos();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Medicamento salvo com sucesso!");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Quantidade e preço devem ser números válidos!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao salvar medicamento: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void onListar() {
        medicamentosTable.setItems(lista);
        medicamentosTable.refresh();
    }

    @FXML
    public void onConsultar() {
        String nome = nomeField.getText();
        for (Medicamentos m : lista) {
            if (m.getNome().equalsIgnoreCase(nome)) {
                // Atualiza campos da tela
                nomeField.setText(m.getNome());
                dataValidadePicker.setValue(m.getDataValidade());
                estoqueField.setText(String.valueOf(m.getQuantidadeEstoque()));
                precoField.setText(m.getPreco().toString());
                controladoCheckBox.setSelected(m.isControlado());
                return;
            }
        }
        limparCampos();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Consulta");
        alert.setHeaderText(null);
        alert.setContentText("Medicamento não encontrado!");
        alert.showAndWait();
    }

    @FXML
    public void onExcluir() {
        String nome = nomeField.getText();
        boolean removed = lista.removeIf(m -> m.getNome().equalsIgnoreCase(nome));
        if (removed) {
            salvarCSV();
            medicamentosTable.refresh();
            limparCampos();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Excluir");
            alert.setHeaderText(null);
            alert.setContentText("Medicamento excluído com sucesso!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Excluir");
            alert.setHeaderText(null);
            alert.setContentText("Medicamento não encontrado para exclusão!");
            alert.showAndWait();
        }
    }

    private void limparCampos() {
        nomeField.clear();
        dataValidadePicker.setValue(null);
        estoqueField.clear();
        precoField.clear();
        controladoCheckBox.setSelected(false);
    }
}
