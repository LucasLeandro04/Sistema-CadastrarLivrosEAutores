
package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class Util {
    
    public static void mensagem(String txt) {        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("AVISO!");
        alert.setContentText(txt);
        alert.showAndWait();
    }
        public static void perigo(String txt) {        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText("ATENÇÃO!");
        alert.setContentText(txt);
        alert.showAndWait();
    }
    
    public static boolean pergunta(String msg){
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText("IFSP");
      alert.setContentText(msg);
      
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        
        alert.getButtonTypes().setAll(btnSim,btnNao);
        
        return (alert.showAndWait().get()==btnSim);
    }
    
}
