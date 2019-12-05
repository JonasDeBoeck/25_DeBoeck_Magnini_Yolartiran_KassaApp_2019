package view.panels.instellingen;

import controller.KassaController;
import database.PropertiesLoadSave;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


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
        file = new RadioButton("db in memory");
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
