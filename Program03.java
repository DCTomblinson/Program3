import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;;

/**
 * This class creates a JavaFx GUI where users can add items to a digital wheel, name the wheel, reverse the wheel, reload the wheel, clear the wheel, report on the wheel, and
 * spin the wheel
 * @author Devon Tomblinson
 */
public class Program03 extends Application {

    //Variables for maintaining a JavaFX timeline for the spin animation
    private int DURATION = 500;
    private int ITERATIONS = 10;
    private int animationCT = 0;
    
    //The wheel object that contains all the information
    private Wheel wheel = new Wheel();
    
    //Button and label for the spin animation
    private Button spin = new Button("Spin");
    private Label result = new Label("Spin Result: ");
    
    //VBoxfor holding the components of the GUI
    private VBox vbox = new VBox(60);;

    /**
     * start method is an override that creates all the components tht have eventhandlers, place those components into HBoxes, and places those HBoxes into the VBox and builds the scene
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Wheel Of Decision");

        //Create the menu items and then add them to a menubutton
        MenuItem[] menItems = menItems();
        MenuButton actions = new MenuButton("Actions", null, menItems[0], menItems[1], menItems[2], menItems[3]);
        
        //Create the buttons, text, and text fields as well as the label for the wheel that will be used in the GUI
        Button[] allButtons = allButtons();
        Text[] texts = texts();
        TextField[] txtFields = txtFields();
        Label wheelName = new Label("Wheel");

        //Handles all of the menuitem events
        menuHandler(menItems);

        //Handles all of the button events
        btnHandler(allButtons, txtFields, wheelName);

        //Uses the SimpleHandler class to handle the spin button event
        spin.setOnAction(new SimpleHandler());

        //Sets up the HBoxes with the components and then places the Hboxes into the Vbox.
        bxSetup(actions, allButtons, texts, txtFields, wheelName);
        
        //Set the Scene with the VBox and then show it
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Method menItems creates 4 menu items and then returns them in an array
     * @return an array of menuitem objects
     */
    public MenuItem[] menItems(){
        MenuItem reload = new MenuItem("Reload");
        MenuItem reverse = new MenuItem("Reverse");
        MenuItem report = new MenuItem("Report");
        MenuItem clear = new MenuItem("Clear");
        
        return new MenuItem[] { reload, reverse, report, clear };
    }

    /**
     * Method allButtons creates 4 button objects and then returns then in an array
     * @return an array of buttons
     */
    public Button[] allButtons(){
        Button about = new Button("About");
        Button exit = new Button("Exit");
        Button add = new Button("Add");
        Button name = new Button("Name");

        return new Button[] {about, exit, add, name};
    }

    /**
     * Method texts creates 2 text objects and returns them in an array
     * @return an array of texts
     */
    public Text[] texts(){
        Text addInstruction = new Text("What do you want to add?");
        Text nameInstruction = new Text("What do you want to name the Wheel?");

        return new Text[] {addInstruction, nameInstruction};
    }

    /**
     * Method txtFields creates 2 text fields and returns them in an array
     * @return an array of text fields
     */
    public TextField[] txtFields(){
        TextField txtBox = new TextField();
        TextField nmBox = new TextField();

        return new TextField[] {txtBox, nmBox};
    }

    /**
     * Method menuHandler uses lambda expressions to eventhandle clicks on the different menuitems
     * @param menItems an array of the menuitems to be handled
     */
    public void menuHandler(MenuItem[] menItems){
        
        //Reload the wheel
        menItems[0].setOnAction(event -> {
            wheel.reload();
        });

        //Reverse the order of the items in the wheel
        menItems[1].setOnAction(event -> {
            wheel.reverse();
        });

        //Create a .txt report of the status of the wheel
        menItems[2].setOnAction(event -> {
            
            //Create a report.txt, print an introductory line, print out the wheel items in order, the number of wheel items, the first and last item, and then close the file.
            try {
                PrintWriter out = new PrintWriter("report.txt");
                out.println("Wheel Report");
                int length = wheel.length();
                int i = 0;
                while (i < length) {
                    out.println(wheel.list.get(i));
                    i++;
                }
                out.println("Number of Wheel Items: " + length);
                out.println("First Item: " + wheel.list.getFirst());
                out.println("Last Item: " + wheel.list.getLast());
                out.close();
            } 
            
            //Catch block to cover FileNotFoundException
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        //Clear the items in the wheel
        menItems[3].setOnAction(event -> {
            wheel.clear();
        });
    }

    /**
     * Method btnHandler uses lambda expressions to eventhandle clicks on the different buttons in the GUI
     * @param allButtons array of buttons that are to be eventhandled
     * @param txtFields array of textfields for user input
     * @param wheelName label holding the name of the wheel
     */
    public void btnHandler(Button[] allButtons, TextField[] txtFields, Label wheelName){
        
        //Add whatever user input is in the textfield to the wheel and then clear the textfield
        allButtons[2].setOnAction(event -> {
            wheel.add(txtFields[0].getText());
            txtFields[0].clear();
        });

        //Create a pop-up alert about the program and show it
        allButtons[0].setOnAction(event -> {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("This is Program03 written by Devon Tomblinson for CSC210.");
            a.show();
        });

        //Exit the program
        allButtons[1].setOnAction(event -> {
            System.exit(0);
        });

        //Change the name of the wheel label frm the user input and then clear the textfield
        allButtons[3].setOnAction(event -> {
            wheelName.setText(txtFields[1].getText());
            txtFields[1].clear();
        });
    }

    /**
     * Method bxSetup takes all the different JavaFX components and organizes them into a single VBox to be the scene
     * @param actions A menubutton containing all the menuitems
     * @param allButtons array of all the buttons except for spin
     * @param texts array of all the text objects
     * @param txtFields array of all the text fields
     * @param wheelName label of the name of the wheel
     */
    public void bxSetup(MenuButton actions, Button[] allButtons, Text[] texts, TextField[] txtFields, Label wheelName){
        
        //Add the components in order so that each HBox is stacked with the name label and spin and result HBox being centered for styling
        HBox hbox1 = new HBox(actions, allButtons[0], allButtons[1]);
        HBox hbox2 = new HBox(texts[0], txtFields[0], allButtons[2]);
        HBox hbox3 = new HBox(texts[1], txtFields[1], allButtons[3]);
        HBox hbox4 = new HBox(wheelName);
        hbox4.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(spin, result);
        hbox5.setAlignment(Pos.CENTER);
        
        //Add all the HBoxes in order to the VBox and set the padding so there is space between them
        vbox.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5);
        vbox.setPadding(new Insets(10));
    }
   
    /**
     * Inner class user to set up a timeline for use with the spin button
     */
    class SimpleHandler implements EventHandler<ActionEvent> {
        
        /**
         * Handle override to set up a timeline for the clicking of the spin button
         */
        @Override
        public void handle(ActionEvent event) {
            //Create a KeyFrame for the timeline using an AnimationHandler
            Duration duration = new Duration(DURATION);
            EventHandler<ActionEvent> handler = new AnimationHandler();
            KeyFrame keyFrame = new KeyFrame(duration, handler);

            //Create a Timeline for this KeyFrame and run it ITERATIONS times
            Timeline timeLine = new Timeline(keyFrame);
            timeLine.setCycleCount(ITERATIONS);
            timeLine.playFromStart();
        }
    }

    /**
     * Inner class used to animate the spinning of the wheel
     */
    class AnimationHandler implements EventHandler<ActionEvent> {
        
        /**
         * Handle override to set up the animation when the spin button is clicked
         */
        @Override
        public void handle(ActionEvent event){
          
            //Increment the count and get a random string from the wheel
            animationCT++;
            String ranStr = wheel.random();
            if(animationCT < ITERATIONS) {
                //Set button text and random background color to the random item
                spin.setText(ranStr);
                spin.setStyle("-fx-background-color: " + randomColor() + ";");
            }
          
            //Set the button to the random string, set the color to normal, announce the result, remove the item from the wheel, 
            //and reset the spin button text and the animation count to 0 
            else {
                spin.setText(ranStr);
                spin.setStyle(null);
                result.setText("Spin Result: " + ranStr);
                wheel.remove(ranStr);
                spin.setText("Spin");
                animationCT = 0;
            }
        }
      }  
    
    /**
     * Method randomColor generates a random color from a set list of colors
     * @return the name of a random color
     */
      public String randomColor(){
        
        //List of colors
        String[] colors = {
            "red", "blue", "green", "hotpink", "orange", 
            "purple"
        };
        
        //Random object to randomize which number is picked
        Random randColor = new Random();
        int i = randColor.nextInt(colors.length);
        
        return colors[i];
    }

    /**
     * Runs the GUI
     * @param args command line arguement
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}