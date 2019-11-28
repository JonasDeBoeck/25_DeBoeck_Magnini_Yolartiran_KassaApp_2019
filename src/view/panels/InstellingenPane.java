package view.panels;

import controller.KassaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class InstellingenPane extends GridPane {

    private RadioButton SQL;
    private RadioButton file;
    private KassaController controller;
    private ComboBox comboBox;
    private ObservableList<String> options = FXCollections.observableArrayList(
            "txt bestand",
            "xlsx bestand"
    );

    public InstellingenPane(KassaController controller){
        setController(controller);

        ToggleGroup groep = new ToggleGroup();
        SQL = new RadioButton("Sql");
        SQL.setToggleGroup(groep);
        file = new RadioButton("db in memory");
        file.setToggleGroup(groep);
        comboBox = new ComboBox<>(options);

        this.setPadding(new Insets(5,5,5,5));
        this.setHgap(5);
        this.setVgap(5);

        this.add(SQL, 1 , 1);
        this.add(file, 1, 2);

        SQL.setOnAction(onClick -> {
            this.getChildren().remove(comboBox);
        });
        file.setOnAction(onClick -> {
            this.add(comboBox, 1,3);
        });

    }

    public void setController(KassaController controller) {
        this.controller = controller;
    }
}
