package view;


import controller.KassaController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.instellingen.InstellingenDatabasePane;
import view.panels.KassaTabPane;
import view.panels.ProductOverviewPane;
import view.panels.instellingen.InstellingenMainPane;

public class KassaMainPane extends BorderPane {
    private KassaController controller;

	public KassaMainPane(KassaController controller){
		setController(controller);
	    TabPane tabPane = new TabPane(); 	    
        KassaTabPane tabKassa = new KassaTabPane(this.controller);
        Tab kassaTab = new Tab("Kassa", tabKassa);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(this.controller);
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        InstellingenMainPane instellingPane = new InstellingenMainPane(this.controller);
        Tab instellingenTab = new Tab("Instellingen", instellingPane);
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingenTab);
        tabPane.getTabs().add(logTab);



	    this.setCenter(tabPane);
	}

    private void setController(KassaController controller) {
        this.controller = controller;
    }
}
