package view.panels.instellingen;

import controller.KassaController;
import database.PropertiesLoadSave;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * @Author Thibault Magnini
 */

public class InstellingenKortingPane extends GridPane {
    private KassaController controller;
    private Label korting = new Label("Korting Instellingen:");
    private Label getalLabel = new Label("Kies hoevel korting u exact wenst te geven.");
    private Slider sliderGetal = new Slider();
    private Label getal;
    private Button saveKorting;
    private ComboBox categorieKeuze;
    private TextField invoerGrens;
    private ObservableList<String> categorien;
    private RadioButton groepKorting;
    private RadioButton grensKorting;
    private RadioButton duursteKorting;
    private RadioButton geenKorting;
    private Label getalKorting = new Label("Hoe wilt u de korting berekenen?");
    private ComboBox comboBoxKortingenSoorten;
    private ObservableList<String> optieKorting = FXCollections.observableArrayList(
            "Percentage",
            "Euro"
    );

    public InstellingenKortingPane(KassaController controller){
        setController(controller);

        //initialisaties
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

        this.add(korting, 1, 1);
        this.add(groepKorting,1,2);
        this.add(categorieKeuze, 2,2);
        this.add(grensKorting,1,3);
        this.add(duursteKorting,1,4);
        this.add(geenKorting, 1,5);
        this.add(getalKorting,1,7);
        this.add(comboBoxKortingenSoorten, 1,8);
        this.add(getalLabel, 1,10);
        this.add(sliderGetal,1,11);
        this.add(getal, 2,11);
        this.add(saveKorting, 1,13);
        this.add(invoerGrens, 2,3);

        this.setPadding(new Insets(10,10,10,10));
        this.setVgap(8);
        this.setHgap(10);

        //Alles wat in de Properties file aangeduid staat wordt aangeduid bij het opstartenb
        if (controller.checkBestaat("SOORT")) {
            String soort = controller.loadProperty("SOORT");/*PropertiesLoadSave.load("SOORT")*/;
            soort = soort.substring(0,1).toUpperCase() + soort.substring(1).toLowerCase();
            switch (soort) {
                case "Grens":
                    grensKorting.setSelected(true);
                    break;
                case "Groep":
                    groepKorting.setSelected(true);
                    break;
                case "Duurste":
                    duursteKorting.setSelected(true);
                    break;
                default:
                    geenKorting.setSelected(true);
            }
            if (!controller.loadProperty("SOORT").equals("GEEN")) {
                sliderGetal.setValue(Double.parseDouble(controller.loadProperty("GETAL")));
                getal.setText(controller.loadProperty("GETAL"));
                String type = controller.loadProperty("TYPE");
                type = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
                comboBoxKortingenSoorten.getSelectionModel().select(type);
            }
        }

        categorieKeuze.setVisible(groepKorting.isSelected());
        invoerGrens.setVisible(grensKorting.isSelected());

        if (groepKorting.isSelected()) {
            categorieKeuze.getSelectionModel().select(controller.loadProperty("CATEGORIE"));
        }

        if (grensKorting.isSelected()) {
            invoerGrens.setText(controller.loadProperty("DREMPEL"));
        }

        if (geenKorting.isSelected()) {
            comboBoxKortingenSoorten.setVisible(false);
            getalLabel.setVisible(false);
            getal.setVisible(false);
            sliderGetal.setVisible(false);
            getalKorting.setVisible(false);
            categorieKeuze.setVisible(false);
            invoerGrens.setVisible(false);
        }

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

        saveKorting.setOnAction(event -> {
            if (kortingGroep.getSelectedToggle() == groepKorting){
                controller.saveProperty("", "DREMPEL");
                controller.saveProperty(comboBoxKortingenSoorten.getValue().toString(), "TYPE");
                controller.saveProperty(Integer.toString((int) Math.round(sliderGetal.getValue())), "GETAL");
                controller.saveProperty(categorieKeuze.getValue().toString(), "CATEGORIE");
                controller.saveProperty("GROEP", "SOORT");
                showAlert();
            } else if (kortingGroep.getSelectedToggle() == grensKorting){
                controller.saveProperty(invoerGrens.getText(), "DREMPEL");
                controller.saveProperty(comboBoxKortingenSoorten.getValue().toString(), "TYPE");
                controller.saveProperty(Integer.toString((int) Math.round(sliderGetal.getValue())), "GETAL");
                controller.saveProperty("", "CATEGORIE");
                controller.saveProperty("GRENS", "SOORT");
                showAlert();
            } else if (kortingGroep.getSelectedToggle() == duursteKorting){
                controller.saveProperty(comboBoxKortingenSoorten.getValue().toString(), "TYPE");
                controller.saveProperty(Integer.toString(Math.round((int) sliderGetal.getValue())), "GETAL");
                controller.saveProperty("", "CATEGORIE");
                controller.saveProperty("", "DREMPEL");
                controller.saveProperty("DUURSTE", "SOORT");
                showAlert();
            } else if (kortingGroep.getSelectedToggle() == geenKorting){
                controller.saveProperty("", "TYPE");
                controller.saveProperty("", "GETAL");
                controller.saveProperty("", "CATEGORIE");
                controller.saveProperty("", "DREMPEL");
                controller.saveProperty("GEEN", "SOORT");
                showAlert();
            }
        });
    }

    public void showAlert(){
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("INFO");
        confirmation.setHeaderText("Kortingen opgeslagen");
        confirmation.setContentText("Kortingen opgeslagen, herstart het programma voor de aanpassingen.");
        confirmation.showAndWait();
    }

    public void setController(KassaController controller) {
        this.controller = controller;
    }
}
