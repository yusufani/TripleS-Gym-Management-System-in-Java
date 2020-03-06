package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginSceneController {

    private static int whichLoginScreen;
    @FXML private JFXTextField ID;
    @FXML private JFXTextField Password;
    @FXML private Label hataMesaj;
    VeriTabani x = VeriTabani.getInstance();

    public int getWhichLoginScreen() {
        return whichLoginScreen;
    }

    public void setWhichLoginScreen(int whichLoginScreen) {
        this.whichLoginScreen = whichLoginScreen;
    }

    @FXML
    public void handleClose(){
        System.exit(0);
    }

    @FXML
    public void handleMusteriGirisiButton(ActionEvent event) throws IOException{
        setWhichLoginScreen(1);
        openLoginScreen(event);
    }

    @FXML
    public void handlePersonelGirisiButton(ActionEvent event) throws IOException{
        setWhichLoginScreen(0);
        openLoginScreen(event);
    }

    @FXML
    public void openLoginScreen(ActionEvent event) throws IOException{
        Parent loginScreen = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(loginScreen));
    }

    @FXML
    public void login(ActionEvent event) throws IOException{

        x.VeriTabaniniAc();
        if(whichLoginScreen==1) {
            Musteri tmpms = x.musteriyiBul(Integer.parseInt(ID.getText()), Password.getText());
            if(tmpms==null){
                hataMesaj.setText("Şifrenizi veya ID'nizi yanlış girdiniz.");
            }
            else{
                VeriTabani.ms = tmpms;
                Parent root = FXMLLoader.load(getClass().getResource("MusteriScene.fxml"));
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root,1400,800));
            }

        }
        else{
            Personel tmpps = x.personeliBul(Integer.parseInt(ID.getText()), Password.getText());
            if(tmpps==null){
                hataMesaj.setText("Şifrenizi veya ID'nizi yanlış girdiniz.");
            }
            else{
                VeriTabani.ps = tmpps;
                Parent root = FXMLLoader.load(getClass().getResource("PersonelScene.fxml"));
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
            }


        }
    }




}
