package view.panels.instellingen;

import controller.KassaController;
import database.PropertiesLoadSave;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Properties;

/**
 * @Author Thibault Magnini
 */
public class InstellingenDatabasePane extends GridPane {

    private RadioButton SQL;
    private RadioButton file;
    private KassaController controller;
    private ComboBox comboBox;
    private Label database = new Label("Database Instellingen:");
    private ObservableList<String> optionsDB = FXCollections.observableArrayList(
            "txt",
            "xls"
    );

    private Button saveKnop;

    public InstellingenDatabasePane(KassaController controller){
        setController(controller);

        //initialisaties van DB instellingen
        ToggleGroup groep = new ToggleGroup();
        SQL = new RadioButton("Sql");
        SQL.setToggleGroup(groep);
        file = new RadioButton("Db in memory");
        file.setToggleGroup(groep);
        comboBox = new ComboBox<>(optionsDB);

        //SAVE KNOP
        saveKnop = new Button("Save DB ");

        //Scherm indeling ALGEMEEN
        this.setPadding(new Insets(10,10,10,10));
        this.setVgap(8);
        this.setHgap(10);

        /*Lay-out voor DATABASE instellingen*/
        this.add(database, 1,1);
        this.add(SQL, 1 , 2);
        this.add(file, 1, 3);
        this.add(comboBox, 1,4);

        //SAVE KNOP VOOR ALLE INSTELLINGEN
        this.add(saveKnop,1,6);

        /*Lay-out voor KORTING instellingen*/

        //Alles wat in de Properties file aangeduid staat wordt aangeduid bij het opstarten
        if (controller.checkBestaat("DATABASE")) {
            comboBox.getSelectionModel().select(controller.loadProperty("DATABASE"));
        }

        file.setSelected(controller.loadProperty("DATABASE").equals("txt") || controller.loadProperty("DATABASE").equals("xls"));

        comboBox.setVisible(file.isSelected());

        SQL.setOnAction(onClick -> {
            comboBox.setVisible(false);
        });

        file.setOnAction(onClick -> {
            comboBox.setVisible(file.isSelected());
        });

        saveKnop.setOnAction(onClick -> {
            if (groep.getSelectedToggle() == SQL) {
                Alert information = new Alert(Alert.AlertType.INFORMATION);
                information.setTitle("INFO");
                information.setHeaderText("Nog niet geïmplementeerd");
                information.setContentText("Volgende keuze: sql, is nog niet geïmplementeerd als databank.");
                information.showAndWait();
            } else {
                String database = comboBox.getValue().toString();
                PropertiesLoadSave.save(database, "DATABASE");
                Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
                confirmation.setTitle("INFO");
                confirmation.setHeaderText("Wijzigingen opgeslagen");
                confirmation.setContentText("Wijzigingen opgeslagen, herstart het programma voor de aanpassingen.");
                confirmation.showAndWait();
            }
        });

    }


    public void setController(KassaController controller) {
        this.controller = controller;
    }
}
