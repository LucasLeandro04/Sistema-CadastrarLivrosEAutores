/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodelivros;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lucas
 */
public class MenuPrincipal extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MenuPrincipal.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        setStage(stage);
        //Deixa a janela do Menu Principal fixa
        stage.setResizable(false);

    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        MenuPrincipal.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
