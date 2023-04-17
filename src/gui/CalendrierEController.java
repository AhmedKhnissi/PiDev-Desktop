/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Calendar;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import services.CalendarService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class CalendrierEController implements Initializable {

    @FXML
    private TextField description;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private TextField localDate;
    @FXML
    private GridPane daysGrid;
    @FXML
    private Button btnConfirmer;
    @FXML
    private ChoiceBox<String> heures;
    @FXML
    private DatePicker date_entre;
    @FXML
    private TextField monanimal;
    private LocalDate currentDate;
    CalendarService es = new CalendarService();
    Date da;
    String [] h={"9","10","11","12","14","15","16","17"};
    private ChoiceBox<String> cbvehicules;
   user me = new user();
   private int id_mec;
   Calendar t = new Calendar();
   String nomanimal ;
   String descriptions;

    /**
     * Initializes the controller class.
     */
    public void setData(user m ){
        me.setId(m.getId());
        id_mec = m.getId();
    }
    UserService ms = new UserService();
    public void getDate(Date d) throws SQLException{
        List<Date> date_entr = ms.veterinaireIsDispo(me, d);
    }
    
     private void updateDaysGrid() throws ParseException {

        
        
        try {
            BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, null, null);
            Background background = new Background(backgroundFill);
            // Créer une liste de boutons pour chaque jour du mois en cours
            List<AnchorPane> buttons = new ArrayList<>();
            //afficher le mois
            YearMonth yearMonth = YearMonth.from(currentDate);
            //afficher combien de jour dans le mois
            int daysInMonth = yearMonth.getMonth().length(yearMonth.isLeapYear());
            //List<Date> locationFait = ms.mecaniciensInDispo(me);
            //System.out.println("EEE"+locationFait);
            for (int day = 1; day <= daysInMonth; day++) {
                Label label = new Label(String.valueOf(day));
                label.setFont(new Font(20));
                label.setMinSize(30,30);
                String d=String.valueOf(day);
                if (d.length()==1){
                            d="0"+d;
                        }
                Date date_final = new SimpleDateFormat("yyyy-MM-dd").parse(yearMonth+"-"+d);
                String date_choisi=yearMonth+"-"+d;
                AnchorPane button = new AnchorPane();
                label.setTextFill(Color.BLACK);
                button.setOnMouseClicked(e -> {
                    try {
                        heures.getItems().removeAll(h);
                        heures.getItems().addAll(h);
                        System.out.println("Date Final"+date_final);
                        da=date_final;
                        date_entre.setValue(LocalDate.parse(date_choisi, ISO_LOCAL_DATE));
                        System.out.println("eeeee"+label.getText());
                        this.getDate(date_final);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String daa=sdf.format(da);
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(daa);
                        List<Date> date_entr = ms.veterinaireIsDispo(me,date);
                        for (int i=0 ;i<date_entr.size();i++){
                            String[] parts = String.valueOf(date_entr.get(i)).split(" ");
                            System.out.println(parts[1]);
                            String[] parts_day_heure = parts[1].split(":");
                            int heu = Integer.parseInt(parts_day_heure[0]);
                            System.out.println("DDD"+heu);
                            heures.getItems().remove(String.valueOf(heu));
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(CalendrierEController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(CalendrierEController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                button.setBackground(background);
                button.getChildren().add(label);
                buttons.add(button);

                /**
                 * ****************************************************************************************
                 */
                String mois = yearMonth.toString();
                String[] parts1 = mois.split("-");
                int month1 = Integer.parseInt(parts1[1]);

                String moisString = month1 + "";
                //List<Location> locationFait = ls.recupererAllByIdVehicule(2);
                List<Calendar> locationFait = es.recuperer();
                //List<Date> locationFait = ms.mecaniciensInDispo(me);
                for (int i = 0; i < locationFait.size(); i++) {

                    Date dateDebut = locationFait.get(i).getStart();
                    System.out.println("DDDD"+dateDebut);
                    //Date dateDebut = locationFait.get(i);
                    //System.out.println(dateDebut);
                    //Date dateFin = locationFait.get(i).getDate_fin();
                    String s = dateDebut.toString();
                    //String s2 = dateFin.toString();

                    String[] parts = s.split("-");
                    System.out.println(parts[2]);
                    System.out.println("AAAA");
                    //String[] parts3 = s2.split("-");
                    int year = Integer.parseInt(parts[0]);
                    int month = Integer.parseInt(parts[1]);
                    //int dayy_heure = Integer.parseInt(parts[2]);
                    //System.out.println("day:"+dayy_heure);
                    //int dayy = dayy_heure
                    String[] parts_day_heure = parts[2].split(" ");
                    int dayy = Integer.parseInt(parts_day_heure[0]);
                    System.out.println("DDD"+dayy);
                    String dayEnString = dayy + "";
                    String moisL = month + "";
   
                    /*if (dayEnString.equals(label.getText()) && moisString.equals(moisL)) {

                        label.setTextFill(Color.RED);

                    }*/
                }

            }

            //Ajouter les boutons à la grille des jours
            daysGrid.getChildren().clear();
            int row = 0;
            int column = 0;
            for (AnchorPane button : buttons) {
                daysGrid.add(button, column, row);
                column++;
                if (column > 6) {
                    column = 0;
                    row++;
                }
            }

            // zina afffichage
            daysGrid.getColumnConstraints().clear();
            daysGrid.getRowConstraints().clear();
            for (int i = 0; i < 7; i++) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(50.0);
                daysGrid.getColumnConstraints().add(columnConstraints);
            }
            for (int i = 0; i <= row; i++) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setPrefHeight(50.0);
                daysGrid.getRowConstraints().add(rowConstraints);
            }

            daysGrid.setPrefSize(50.0 * 7, 50.0 * (row + 1));

        } catch (SQLException ex) {
            Logger.getLogger(CalendrierEController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" MMMM yyyy");
        localDate.setText(currentDate.format(formatter));

        try {
            // Afficher les jours du mois en cours
            updateDaysGrid();
            // TODO
        } catch (ParseException ex) {
            Logger.getLogger(CalendrierEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void next(ActionEvent event) throws ParseException {
        currentDate = currentDate.plusMonths(1);

        // Afficher la nouvelle date et les jours du mois en cours
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" MMMM yyyy");
        localDate.setText(currentDate.format(formatter));
        updateDaysGrid();
    }

    @FXML
    private void retourner(ActionEvent event) {
    }

    @FXML
    private void prev(ActionEvent event)  throws ParseException {
         // Revenir au mois précédent
        currentDate = currentDate.minusMonths(1);

        // Afficher la nouvelle date et les jours du mois en cours
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        localDate.setText(currentDate.format(formatter));
        updateDaysGrid();
    }

    @FXML
    private void ajouterEntretien(ActionEvent event) throws SQLException, ParseException, IOException {
        int occ=0;
        List<Date> d=ms.veterinaireInDispo(me);
        System.out.println("INDISPO"+d);
        String dd=date_entre.getValue()+" "+heures.getValue().toString()+":00:00.0";
        System.out.println(dd);
        for (Date i : d){
            if (String.valueOf(i).equals(dd)){
                /*Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("La date est déja prise ");
                al.showAndWait();*/
                occ=occ+1;
            }
        }
        if (Integer.valueOf(heures.getValue().toString())>=18){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Le mécanicien ferme a 18h ");
            al.showAndWait();
        }
        else if (Integer.valueOf(heures.getValue().toString())<8){
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Le mécanicien ouvre a 8h");
            a2.showAndWait();
        }
        else if (occ>=1){
                Alert a3 = new Alert(Alert.AlertType.ERROR);
                a3.setHeaderText(null);
                a3.setContentText("La date est déja prise ");
                a3.showAndWait();
        }
        else {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dd);
        t.setStart(date);
        t.setUser_id(me.getId());
            System.out.println("ffffffffffff"+id_mec);
        //t.setId_vehicule(id);
        nomanimal = monanimal.getText();
        descriptions = description.getText();
        t.setTitle(nomanimal);
        t.setDescription(descriptions);
            System.out.println("yyyyy"+descriptions);
        es.ajouter(t);
//        String bienvenue="Vous avez un rendez-vous d'entretient le "+t.getDate_entretien();
//            System.out.println("NUMTEL"+ms.recupererById(me));
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//            Message message = Message.creator(
//                    new com.twilio.type.PhoneNumber("+216"+String.valueOf(me.getContact())),
//                    new com.twilio.type.PhoneNumber("+13157918497"), bienvenue).create();
//
//            System.out.println(message.getSid());
        /////////////////////
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("calendar ");
            alert.setHeaderText("Ajout De Rendez-Vous Avec Succées");
            alert.setContentText("tmarech");
            alert.showAndWait();
        
        
        
        
        }
    }

    @FXML
    private void maj(MouseEvent event) throws ParseException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String daa=sdf.format(da);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(daa);
        List<Date> date_entr = ms.veterinaireIsDispo(me, da);
    }
    
}
