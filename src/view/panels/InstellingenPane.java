package view.panels;

import controller.KassaController;
import database.PropertiesLoadSave;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.ArtikelCategorie;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

public class InstellingenPane extends GridPane {

    private RadioButton SQL;
    private RadioButton file;
    private RadioButton groepKorting;
    private RadioButton grensKorting;
    private RadioButton duursteKorting;
    private RadioButton geenKorting;
    private Label getalKorting = new Label("Hoe wilt u de korting berekenen?");
    private KassaController controller;
    private ComboBox comboBox;
    private Label database = new Label("Database Instellingen:");
    private Label korting = new Label("Korting Instellingen:");
    private Label getalLabel = new Label("Kies hoevel korting u exact wenst te geven.");
    private Slider sliderGetal = new Slider();
    private Label getal;
    private Button saveKorting;
    private ComboBox categorieKeuze;
    private TextField invoerGrens;

    private ObservableList<String> categorien;

    private ObservableList<String> optionsDB = FXCollections.observableArrayList(
            "txt",
            "xls"
    );

    private ComboBox comboBoxKortingenSoorten;
    private ObservableList<String> optieKorting = FXCollections.observableArrayList(
            "Percentage",
            "Euro"
    );

    private Button saveKnop;


    public InstellingenPane(KassaController controller){
        setController(controller);

        //initialisaties van DB instellingen
        ToggleGroup groep = new ToggleGroup();
        SQL = new RadioButton("Sql");
        SQL.setToggleGroup(groep);
        file = new RadioButton("db in memory");
        file.setToggleGroup(groep);
        comboBox = new ComboBox<>(optionsDB);


        //initialisaties van Korting instellingen
        ToggleGroup kortingGroep = new ToggleGroup();
        comboBoxKortingenSoorten = new ComboBox<>(optieKorting);
        groepKorting = new RadioButton("Groeps Korting");
        grensKorting = new RadioButton("Grens Korting");
        duursteKorting = new RadioButton("Duurste Korting");
        grensKorting.setToggleGroup(kortingGroep);
        groepKorting.setToggleGroup(kortingGroep);
        duursteKorting.setToggleGroup(kortingGroep);
        geenKorting = new RadioButton("Geen Korting");
        geenKorting.setToggleGroup(kortingGroep);
        sliderGetal.setMin(0);
        sliderGetal.setMax(100);
        sliderGetal.setBlockIncrement(1);
        getal = new Label(sliderGetal.getValue()+"");
        saveKorting= new Button("Save Korting");
        categorien = FXCollections.observableArrayList("GR1", "GR2");
        categorieKeuze = new ComboBox<>(categorien);
        invoerGrens = new TextField("Vul hier de drempel in");


        //SAVE KNOP
        saveKnop = new Button("Save DB ");

        //Scherm indeling ALGEMEEN
        this.setPadding(new Insets(5,5,5,5));
        this.setHgap(5);
        this.setVgap(5);

        /*Lay-out voor DATABASE instellingen*/
        this.add(database, 1,1);
        this.add(SQL, 1 , 2);
        this.add(file, 1, 3);
        this.add(comboBox, 1,4);

        //SAVE KNOP VOOR ALLE INSTELLINGEN
        this.add(saveKnop,1,6);

        /*Lay-out voor KORTING instellingen*/
        this.add(korting, 3, 1);
        this.add(groepKorting,3,2);
        this.add(categorieKeuze, 4,2);
        this.add(grensKorting,3,3);
        this.add(duursteKorting,3,4);
        this.add(geenKorting, 3,5);
        this.add(getalKorting,3,6);
        this.add(comboBoxKortingenSoorten, 3,7);
        this.add(getalLabel, 3,8);
        this.add(sliderGetal,3,9);
        this.add(getal, 4,9);
        this.add(saveKorting, 3,10);
        this.add(invoerGrens, 4,3);

        categorieKeuze.setVisible(false);
        invoerGrens.setVisible(false);

        comboBox.setVisible(false);

        sliderGetal.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                getal.setText(String.format("%.0f",new_val));
            }
        });

        geenKorting.setOnAction(event -> {
            comboBoxKortingenSoorten.setVisible(false);
            getalLabel.setVisible(false);
            getal.setVisible(false);
            sliderGetal.setVisible(false);
            getalKorting.setVisible(false);
            saveKorting.setVisible(false);
            categorieKeuze.setVisible(false);
            invoerGrens.setVisible(false);
        });

        groepKorting.setOnAction(event -> {
            categorieKeuze.setVisible(true);
            comboBoxKortingenSoorten.setVisible(true);
            getalLabel.setVisible(true);
            getal.setVisible(true);
            sliderGetal.setVisible(true);
            getalKorting.setVisible(true);
            saveKorting.setVisible(true);
            invoerGrens.setVisible(false);
        });

        grensKorting.setOnAction(event -> {
            categorieKeuze.setVisible(false);
            comboBoxKortingenSoorten.setVisible(true);
            getalLabel.setVisible(true);
            getal.setVisible(true);
            sliderGetal.setVisible(true);
            getalKorting.setVisible(true);
            saveKorting.setVisible(true);
            invoerGrens.setVisible(true);
        });

        invoerGrens.setOnMouseClicked(event -> {
            invoerGrens.clear();
        });

        duursteKorting.setOnAction(event -> {
            categorieKeuze.setVisible(false);
            comboBoxKortingenSoorten.setVisible(true);
            getalLabel.setVisible(true);
            getal.setVisible(true);
            sliderGetal.setVisible(true);
            getalKorting.setVisible(true);
            saveKorting.setVisible(true);
            invoerGrens.setVisible(false);
        });

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
