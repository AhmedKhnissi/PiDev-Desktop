/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Calendar;
import entities.User;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    String [] h={"09","10","11","12","14","15","16","17"};
    private ChoiceBox<String> cbvehicules;
   User me = new User();
   private int id_mec;
   Calendar t = new Calendar();
   String nomanimal ;
   String descriptions;

    /**
     * Initializes the controller class.
     */
    public void setData(User m ){
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
   
            for (int day = 1; day <= daysInMonth; day++) {
                Label label = new Label(String.valueOf(day));
                label.setFont(new Font(20));
                label.setMinSize(30,30);
                //on rend string la valeur du jour  
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
                        System.out.println("Date totale : "+date_final);
                        da=date_final;
                        date_entre.setValue(LocalDate.parse(date_choisi, ISO_LOCAL_DATE));
                        System.out.println("jour choisie : "+label.getText());
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
                List<Calendar> locationFait = es.recuperer();
                for (int i = 0; i < locationFait.size(); i++) {

                    Date dateDebut = locationFait.get(i).getStart();
                    System.out.println("DDDD"+dateDebut);
                    String s = dateDebut.toString();

                    String[] parts = s.split("-");
                    System.out.println(parts[2]);
                    System.out.println("AAAA");
                    int year = Integer.parseInt(parts[0]);
                    int month = Integer.parseInt(parts[1]);
                    
                    String[] parts_day_heure = parts[2].split(" ");
                    int dayy = Integer.parseInt(parts_day_heure[0]);
                    System.out.println("DDD"+dayy);
                    String dayEnString = dayy + "";
                    String moisL = month + "";
   
                    
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

    //@FXML
    //private void retourner(ActionEvent event) {
    //}

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
    private void ajouterRendezVous(ActionEvent event) throws SQLException, ParseException, IOException {
        int occ=0;
        List<Date> d=ms.veterinaireInDispo(me);
        System.out.println("INDISPO Veto : "+d);
        String dd=date_entre.getValue()+" "+heures.getValue().toString()+":00:00.0";
        System.out.println("hedhi e date d'entree : "+dd);
        for (Date i : d){
            if (String.valueOf(i).equals(dd)){
                occ=occ+1;
            }
        }if (occ>=1){
                Alert a3 = new Alert(Alert.AlertType.ERROR);
                a3.setHeaderText(null);
                a3.setContentText("La date est déja prise ");
                a3.showAndWait();
        }
        else {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dd);
        t.setStart(date);
        t.setUser_id(me.getId());
            System.out.println("id l vétérinaire li affectinelou l rdv : "+id_mec);
        
        nomanimal = monanimal.getText();
        descriptions = description.getText();
        t.setTitle(nomanimal);
        t.setDescription(descriptions);
            System.out.println("yyyyy"+descriptions);
        es.ajouter(t);
        
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("calendar ");
            alert.setHeaderText("Ajout De Rendez-Vous Avec Succées !");
            alert.setContentText("RendezVous Ajoutée !");
            alert.showAndWait();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
        Parent root1 = loader.load();
               BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeVeto.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            monanimal.getScene().setRoot(borderPane);    
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
        
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
