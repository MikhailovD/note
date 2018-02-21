package ru.mdg;
        import javafx.application.*;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.scene.*;
        import javafx.stage.*;
        import javafx.scene.layout.*;
        import javafx.fxml.FXMLLoader;
        import ru.mdg.model.DataAccess;
        import ru.mdg.model.Notation;
        import ru.mdg.view.NoteOverviewContr;

        import java.io.IOException;
        import java.sql.SQLException;

public class App extends Application {

    private Stage primaryStage;
    private BorderPane menuLayout;

    private ObservableList<Notation> noteData = FXCollections.observableArrayList();

    private DataAccess noteDataAccess;
    private final String DBURL = "jdbc:mysql://localhost:3306/dbnote?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "root";

    public void start(Stage primaryStage) {
        System.out.println("В теле метода start()");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("JavaFX Note");

        try {
            noteDataAccess = new DataAccess(DBURL, USER, PASSWORD);
            noteData = noteDataAccess.getNoteList();
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initMenuLayout();

        showNoteOverview();



    }
    public void initMenuLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/MenuLayout.fxml"));
            menuLayout = (BorderPane) loader.load();

            Scene scene = new Scene(menuLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNoteOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/NoteOverview.fxml"));
            AnchorPane noteOverview = (AnchorPane) loader.load();

            menuLayout.setCenter(noteOverview);

            NoteOverviewContr contr = loader.getController();
            contr.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Notation> getNoteData() { return noteData;}

    public void stop() throws Exception {
        if (noteDataAccess != null) {
            noteDataAccess.shutdown();
        }
    }

    public static void main( String[] args ) {
        launch(args);
    }
}
