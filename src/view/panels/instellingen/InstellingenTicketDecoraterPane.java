package view.panels.instellingen;

import controller.KassaController;
import database.PropertiesLoadSave;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class InstellingenTicketDecoraterPane extends GridPane {

    private Label toevoeging;
    private KassaController controller;
    private CheckBox header, footer;
    private Button saveSettings;
    private ComboBox headerAddons, footerAddons;
    private ObservableList<String> headerAddonsList, footerAddonsList;
    private TextField headerMessage, footerMessage;

    public InstellingenTicketDecoraterPane(KassaController controller){
        setController(controller);
        toevoeging = new Label("Toevoegingen aan kassabon instellingen:");
        header = new CheckBox("Kies een header toevoeging");
        footer = new CheckBox("Kies een footer toevoeging");
        headerAddonsList = FXCollections.observableArrayList(
                "Algemene boodschap",
                        "Datum en tijd van afprinten"
        );
        footerAddonsList = FXCollections.observableArrayList(
                "Totale prijs zonder en met korting",
                        "Betaalde prijs ex. BTW en totale BTW",
                        "Algemene boodschap"
        );
        headerAddons = new ComboBox<>(headerAddonsList);
        footerAddons = new ComboBox<>(footerAddonsList);
        saveSettings = new Button("Slaag settings op");
        headerMessage = new TextField();
        footerMessage = new TextField();
        this.add(toevoeging, 1, 1);
        this.add(header, 1, 2);
        this.add(footer, 1, 3);
        this.add(headerAddons, 2, 2);
        this.add(headerMessage,3,2);
        this.add(footerAddons, 2, 3);
        this.add(footerMessage,3,2);
        this.add(saveSettings, 1, 4);

        this.setPadding(new Insets(10,10,10,10));
        this.setVgap(8);
        this.setHgap(10);

        header.setSelected(propertyBestaat("HEADER"));
        footer.setSelected(propertyBestaat("FOOTER"));

        headerAddons.setVisible(header.isSelected());
        footerAddons.setVisible(footer.isSelected());
        headerMessage.setVisible(header.isSelected());
        footerMessage.setVisible(footer.isSelected());

        if (propertyBestaat("HBOODSCHAP")) {
            headerMessage.setText(PropertiesLoadSave.load("HBOODSCHAP"));
        }

        if (propertyBestaat("FBOODSCHAP")) {
            headerMessage.setText(PropertiesLoadSave.load("FBOODSCHAP"));
        }

        if (propertyBestaat("HEADER")) {
            headerAddons.getSelectionModel().select(PropertiesLoadSave.load("HEADER"));
        }

        if (propertyBestaat("FOOTER")) {
            footerAddons.getSelectionModel().select(PropertiesLoadSave.load("FOOTER"));
        }

        header.setOnAction(event -> {
            headerAddons.setVisible(header.isSelected());
            footerAddons.setVisible(footer.isSelected());
        });

        footer.setOnAction(event -> {
            footerAddons.setVisible(footer.isSelected());
            headerAddons.setVisible(header.isSelected());
        });

        saveSettings.setOnAction(event -> {
            if (header.isSelected()) {
                if (headerAddons.getValue().equals("Algemene boodschap")) {
                    PropertiesLoadSave.save(headerMessage.getText(), "HBOODSCHAP");
                }
                PropertiesLoadSave.save(headerAddons.getValue().toString(), "HEADER");
            }
            if (footer.isSelected()) {
                if (footerAddons.getValue().equals("Algemene boodschap")) {
                    PropertiesLoadSave.save(headerMessage.getText(), "FBOODSCHAP");
                }
                PropertiesLoadSave.save(footerAddons.getValue().toString(), "FOOTER");
            }
        });
    }

    public void setController(KassaController controller) {
        this.controller = controller;
    }

    public boolean propertyBestaat (String property) {
        return !PropertiesLoadSave.load(property).equals("");
    }
}
