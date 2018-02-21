package ru.mdg.view;

import javafx.fxml.FXML;
import ru.mdg.App;
import ru.mdg.model.Notation;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;

public class NoteOverviewContr {
    @FXML
    private TableView<Notation> notationTable;
    @FXML
    private TableColumn<Notation, LocalDate> dateColumn;
    @FXML
    private TableColumn<Notation, String> textColumn;

    private App app;

    public NoteOverviewContr () {}

    @FXML
    private void initialize() {
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        textColumn.setCellValueFactory(cellData -> cellData.getValue().textProperty());
    }

    public void setApp(App app) {
        this.app = app;
        notationTable.setItems(app.getNoteData());

    }
}
