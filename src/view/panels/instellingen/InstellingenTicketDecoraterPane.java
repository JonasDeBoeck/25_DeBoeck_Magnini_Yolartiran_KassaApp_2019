package view.panels.instellingen;

import controller.KassaController;
import javafx.scene.layout.GridPane;

public class InstellingenTicketDecoraterPane extends GridPane {

    private KassaController controller;

    public InstellingenTicketDecoraterPane(KassaController controller){
        setController(controller);
    }

    public void setController(KassaController controller) {
        this.controller = controller;
    }
}
