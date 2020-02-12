
package util;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Tela {
    
    public void abrirTela(String nomeTela) throws IOException {
        
        Stage stage = new Stage();        
        Parent root = FXMLLoader.load(getClass().getResource("/views/" + nomeTela + ".fxml"));  
        Scene scene = new Scene(root);        
        stage.initModality(Modality.APPLICATION_MODAL);        
        stage.setScene(scene);
        stage.show();
    }
    
}
