package view.panels;

import controller.LogController;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Winkelwagentje;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @Author Jonas De Boeck
 */

public class LogTabPane extends GridPane {
    private LogController controller;
    private TableView tableView;
    private Label label;
    public LogTabPane (LogController controller) {
        setController(controller);
        controller.setView(this);
        label = new Label("Log van verkochte winkelkarretjes:");

        tableView = new TableView<>();

        TableColumn<Winkelwagentje, String> artikels = new TableColumn("Artikels");
        artikels.setCellValueFactory(new PropertyValueFactory<>("artikelen"));
        artikels.setPrefWidth(300);

        TableColumn<Winkelwagentje, LocalDate> datum = new TableColumn("Betaal datum");
        datum.setCellValueFactory(new PropertyValueFactory<>("betaalDatum"));

        TableColumn<Winkelwagentje, LocalTime> tijd = new TableColumn("Betaal tijd");
        tijd.setCellValueFactory(new PropertyValueFactory<>("betaalTijd"));

        tableView.getColumns().addAll(artikels, datum, tijd);

        this.add(label, 1, 1);
        this.add(tableView, 1, 2);
    }

    public void setController(LogController controller) {
        this.controller = controller;
    }

    public void update (ObservableList<Winkelwagentje> verkopen) {
        tableView.setItems(verkopen);
    }
}
