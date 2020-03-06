package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PersonelSceneController implements Initializable {

    @FXML private PieChart memnuniyetPie;
    @FXML private BarChart<String,Integer> aletDolulukChart;
    @FXML private Label personelName;
    @FXML private Label personelID;
    @FXML private StackedBarChart<String,Integer> salonDolulukChart;
    @FXML private Label uyariMesaji;
    VeriTabani x = VeriTabani.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Personel personel = VeriTabani.ps;
        personelName.setText("Giriş Yapan Personel: " + personel.getIsim() + " " + personel.getSoyisim());
        personelID.setText("ID: " + personel.getId());
        memnuniyetGoster();
        aletDolulukGoster();
        salonDolulukGoster();
    }

    public void memnuniyetGoster(){
        double mem = x.ortalamaMemnuniyetDuzeyi();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Memnun",mem),
                        new PieChart.Data("Memnun Değil",5-mem)
                );
        memnuniyetPie.setData(pieChartData);
        System.out.println(mem);
    }

    public void aletDolulukGoster(){
        HashMap<String,Integer> aletler = x.aletdolulukOranlari(x.getSporIndisAlet());

        //aletDolulukChart.getXAxis().setLabel("Aletler");
        aletDolulukChart.getYAxis().setLabel("Doluluk");
        XYChart.Series seri = new XYChart.Series();
        for(String key: aletler.keySet()){
            //System.out.println(key);
            //System.out.println(aletler.get(key));
            if(!key.equals("null"))
            seri.getData().add(new XYChart.Data<>(key,aletler.get(key)));
        }
        aletDolulukChart.getData().add(seri);
    }

    public void salonDolulukGoster(){
        int[][] salonDoluluk = x.salonDolulukHistgorami();
        String[] gunler = new String[7];
        gunler[0] = "Pazartesi";
        gunler[1] = "Salı";
        gunler[2] = "Çarşamba";
        gunler[3] = "Perşembe";
        gunler[4] = "Cuma";
        gunler[5] = "Cumartesi";
        gunler[6] = "Pazar";

        uyariMesaji.setText("");
        salonDolulukChart.getYAxis().setLabel("Doluluk");
        int total=0;
        for(int i=0;i<7;i++){
            total=0;
            for(int j =1;j<5;j++){
                total += salonDoluluk[j][i];
            }
            if(total>=400){
                uyariMesaji.setText(uyariMesaji.getText() + "Uyarı: " +gunler[i] + " günü için antrenör sayısı yetersiz.Antrenör alımı gerekli.\n");
            }
            else if(total<=100){
                uyariMesaji.setText(uyariMesaji.getText() + "Uyarı: " + gunler[i] + " günü için antrenör sayısı gereğinden fazla.\n");
            }
        }
        uyariMesaji.setTextFill(Color.web("#c92e2e"));



        XYChart.Series<String,Integer>[] seriler = new XYChart.Series[4];
        seriler[0] = new XYChart.Series<>();
        seriler[1] = new XYChart.Series<>();
        seriler[2] = new XYChart.Series<>();
        seriler[3] = new XYChart.Series<>();

        seriler[0].setName("07.00-\n11.00");
        seriler[1].setName("11.00-\n15.00");
        seriler[2].setName("15.00-\n19.00");
        seriler[3].setName("19.00-\n23.00");
        for(int i=1;i<5;i++){
                seriler[i-1].getData().add(new XYChart.Data<>("Pazartesi",salonDoluluk[i][0]));
                seriler[i-1].getData().add(new XYChart.Data<>("Salı",salonDoluluk[i][1]));
                seriler[i-1].getData().add(new XYChart.Data<>("Çarşamba",salonDoluluk[i][2]));
                seriler[i-1].getData().add(new XYChart.Data<>("Perşembe",salonDoluluk[i][3]));
                seriler[i-1].getData().add(new XYChart.Data<>("Cuma",salonDoluluk[i][4]));
                seriler[i-1].getData().add(new XYChart.Data<>("Cumartesi",salonDoluluk[i][5]));
                seriler[i-1].getData().add(new XYChart.Data<>("Pazar",salonDoluluk[i][6]));
        }
        salonDolulukChart.getData().addAll(seriler);


        for(int i=1;i<5;i++){
            for(int j=0;j<7;j++){
                System.out.println(salonDoluluk[i][j]);
            }
        }


    }

    @FXML
    public void musteriEkleEkrani(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MusteriEklemeScene.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Müşteri Ekleme");
        stage.setScene(new Scene(root));
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    public void anaMenuyeDon(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }



}
