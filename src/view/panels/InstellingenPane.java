package view.panels;

import controller.KassaController;
import database.PropertiesLoadSave;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Properties;

public class InstellingenPane extends GridPane {

    private RadioButton SQL;
    private RadioButton file;
    private KassaController controller;
    private ComboBox comboBox;
    private ObservableList<String> options = FXCollections.observableArrayList(
            "txt",
            "xls"
    );
    private Button saveKnop;

    public InstellingenPane(KassaController controller){
        setController(controller);

        ToggleGroup groep = new ToggleGroup();
        SQL = new RadioButton("Sql");
        SQL.setToggleGroup(groep);
        file = new RadioButton("db in memory");
        file.setToggleGroup(groep);
        comboBox = new ComboBox<>(options);

        saveKnop = new Button("save");

        this.setPadding(new Insets(5,5,5,5));
        this.setHgap(5);
        this.setVgap(5);

        this.add(SQL, 1 , 1);
        this.add(file, 1, 2);
        this.add(comboBox, 1,3);
        this.add(saveKnop,1,4);

        comboBox.setVisible(false);

        SQL.setOnAction(onClick -> {
            comboBox.setVisible(false);
        });

        file.setOnAction(onClick -> {
            comboBox.setVisible(true);
        });

        saveKnop.setOnAction(onClick -> {
            String database = comboBox.getValue().toString();
            PropertiesLoadSave.save(database, "DATABASE");
            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setTitle("INFO");
            confirmation.setHeaderText("Wijzigingen opgeslagen");
            confirmation.setContentText("Wijzigingen opgeslagen, herstart het programma voor de aanpassingen.");
            confirmation.showAndWait();
        });

    }

    public void setController(KassaController controller) {
        this.controller = controller;
    }
}
