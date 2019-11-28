package view.panels;


import controller.KassaController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class KassaMainPane extends BorderPane {
    private KassaController controller;

	public KassaMainPane(KassaController controller){
		setController(controller);
	    TabPane tabPane = new TabPane(); 	    
        Tab kassaTab = new Tab("Kassa");
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(this.controller);
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        Tab instellingTab = new Tab("Instellingen");
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}

    private void setController(KassaController controller) {
        this.controller = controller;
    }
}
