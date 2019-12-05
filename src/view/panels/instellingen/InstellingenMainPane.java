package view.panels.instellingen;

import controller.KassaController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class InstellingenMainPane extends BorderPane{


    private KassaController controller;

    public InstellingenMainPane(KassaController controller){
        setController(controller);
        TabPane tabPane = new TabPane();
        InstellingenDatabasePane db = new InstellingenDatabasePane(this.controller);
        Tab dbT = new Tab("Database", db);
        InstellingenKortingPane kortingPane = new InstellingenKortingPane(this.controller);
        Tab kortingT = new Tab("Kortingen", kortingPane);
        InstellingenTicketDecoraterPane instellingenTicketDecoraterPane = new InstellingenTicketDecoraterPane(this.controller);
        Tab instelTicket = new Tab("Ticket", instellingenTicketDecoraterPane);
        tabPane.getTabs().add(dbT);
        tabPane.getTabs().add(kortingT);
        tabPane.getTabs().add(instelTicket);

        this.setCenter(tabPane);
    }

    public void setController(KassaController controller) {
        this.controller = controller;
    }
}
