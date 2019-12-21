package view;


import controller.KassaController;
import controller.LogController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.KassaTabPane;
import view.panels.LogTabPane;
import view.panels.ProductOverviewPane;
import view.panels.instellingen.InstellingenMainPane;

public class KassaMainPane extends BorderPane {
    private KassaController controller;
    private LogController logger;

	public KassaMainPane(KassaController controller, LogController logger){
		setController(controller);
	    TabPane tabPane = new TabPane(); 	    
        KassaTabPane tabKassa = new KassaTabPane(this.controller);
        Tab kassaTab = new Tab("Kassa", tabKassa);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(this.controller);
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        InstellingenMainPane instellingPane = new InstellingenMainPane(this.controller);
        Tab instellingenTab = new Tab("Instellingen", instellingPane);
        LogTabPane logTabPane = new LogTabPane(logger);
        Tab logTab = new Tab("Log", logTabPane);
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
