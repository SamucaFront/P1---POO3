module org.medicamentos {
    requires javafx.controls;
    requires javafx.fxml;

    // Abrir pacotes que contêm classes Java para reflexão do JavaFX
    opens org.medicamentos to javafx.graphics, javafx.fxml;
    opens org.medicamentos.controller to javafx.fxml;
    opens org.medicamentos.model to javafx.fxml;
    opens org.medicamentos.utils to javafx.fxml;

    // Exportar pacotes que precisam ser acessíveis fora do módulo
    exports org.medicamentos.controller;
    exports org.medicamentos.model;
    exports org.medicamentos.utils;
}
