package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FileCommands.FillList();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("JavaCommander");
        primaryStage.setScene(new Scene(root));
        Controller ctrl = (Controller)(loader.getController());
        ctrl.open();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
