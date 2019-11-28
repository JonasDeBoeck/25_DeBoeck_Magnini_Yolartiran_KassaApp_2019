package view.panels;

import controller.KassaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

public class InstellingenPane extends GridPane {

    private KassaController controller;
    private ComboBox comboBox;
    private ObservableList<String> options = FXCollections.observableArrayList(
            "txt bestand",
            "xlsx bestand"
    );

    public InstellingenPane(KassaController controller){
        setController(controller);

        comboBox = new ComboBox<>(options);

        this.setPadding(new Insets(5,5,5,5));
        this.setHgap(5);
        this.setVgap(5);
        this.add(comboBox, 1,1);

    }

    public void setController(KassaController controller) {
        this.controller = controller;
    }
}
