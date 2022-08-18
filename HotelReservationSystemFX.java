package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

public class HotelReservationSystemFX extends Application {

    // Instance of FloorTicketSystem class
    HotelReservationSystem arrayList = new HotelReservationSystem();

    //----------------Floor Room-------------------
    static String[][] sampleRoomID = {{"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10"},
    {"B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10"}};

    // Four movies
    String[][] roomFloorID1 = new String[2][10];
    String[][] roomFloorID2 = new String[2][10];
    String[][] roomFloorID3 = new String[2][10];
    String[][] roomFloorID4 = new String[2][10];
    static String[][] roomID = new String[2][10];
    // Constraint
    final int roomNumber = 20;

    //-----------------UI control--------------------
    Button btnDis = new Button("Display"),
            btnBuy = new Button("Reserve"),
            btnFind = new Button("Find"),
            btnDlt = new Button("Delete");
    TextArea taTable = new TextArea(),
            taTable2 = new TextArea(),
            taTable3 = new TextArea();
    TextField tfCustID = new TextField(),
            tfCustName = new TextField();
    RadioButton tfPCustType = new RadioButton("Member"),
            tfRCustType = new RadioButton("Non-Member");
    TextField tfRoomID = new TextField(),
            tfStay = new TextField(),
            tfTotal = new TextField(),
            tfDisc = new TextField(),
            tfdelCustID = new TextField(),
            tffindID = new TextField();
    ComboBox<String> tfFloor = new ComboBox<String>(),
            tfFindFloor = new ComboBox<String>();
    TabPane tabPane = new TabPane();
    Label lbTitle = new Label("The SKYE Hotel Reservation System"),
            lbbuy = new Label("Reserve Room"),
            lbdis = new Label("Display Room"),
            lbdlt = new Label("Checkout Room"),
            lbfind = new Label("Find Customers");
    ScrollPane sp = new ScrollPane(taTable),
            sp2 = new ScrollPane(taTable2),
            sp3 = new ScrollPane(taTable3);
    ToggleGroup group = new ToggleGroup();
    //constraint
    final int numID = 100;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //---------------------------------------------------------------------------------//
        //GridPane
        //Add tab (GridPane)
        GridPane gp = new GridPane();
        gp.setHgap(15);
        gp.setVgap(15);
        gp.setPadding(new Insets(15, 50, 15, 50));
        //1st row
        gp.add(new Label("Customer ID:"), 0, 0);
        gp.add(tfCustID, 1, 0);
        //2nd row
        gp.add(new Label("Customer Name:"), 0, 1);
        gp.add(tfCustName, 1, 1);
        //3rd row
        gp.add(new Label("Customer Type:"), 0, 2);
        HBox pCenter = new HBox(20);
        pCenter.getChildren().addAll(tfPCustType, tfRCustType);
        gp.add((pCenter), 1, 2);
        tfPCustType.setToggleGroup(group);
        tfRCustType.setToggleGroup(group);
        //4th row
        gp.add(new Label("Floor:"), 0, 3);
        gp.add(tfFloor, 1, 3);
        //box
        tfFloor.getItems().add("1st Floor (Single Room)");
        tfFloor.getItems().add("2nd Floor (Twin Room)");
        tfFloor.getItems().add("3rd Floor (Twin Room");
        tfFloor.getItems().add("4th Floor (President Suite)");

        //5th row
        gp.add(new Label("Stay Duration(Days):"),0, 4);
        gp.add(tfStay, 1, 4);
        
        //6th row
        gp.add(new Label("Room Display:"), 0, 5);
        gp.add(sp, 1, 5);
        //7th row
        gp.add(new Label("Room ID:"), 0, 6);
        gp.add(tfRoomID, 1, 6);
        //8th row
        gp.add(new Label("Discount:"), 0, 7);
        gp.add(tfDisc, 1, 7);
        //9th row
        gp.add(new Label("Total Price:"), 0, 8);
        gp.add(tfTotal, 1, 8);
        tfDisc.setEditable(false);
        tfTotal.setEditable(false);

        //Delete Tab(GridPane)
        GridPane gp2 = new GridPane();
        gp2.setHgap(15);
        gp2.setVgap(15);
        gp2.setPadding(new Insets(15, 50, 15, 50));
        //1st row
        gp2.add(new Label("Customer ID:"), 0, 0);
        gp2.add(tfdelCustID, 1, 0);
        gp2.add(btnDlt, 2, 0);

        //Find Tab(GridPane)
        GridPane gp3 = new GridPane();
        gp3.setHgap(15);
        gp3.setVgap(15);
        gp3.setPadding(new Insets(15, 50, 15, 50));
        //1st row
        gp3.add(new Label("Customer ID:"), 0, 0);
        gp3.add(tffindID, 1, 0);
        gp3.add(btnFind, 2, 0);

        //---------------------------------------------------------------------------------------//
        //HBOX
        //Add tab
        //HBox (Button)
        HBox hb = new HBox();
        hb.getChildren().addAll(btnBuy);
        hb.setAlignment(Pos.BASELINE_RIGHT);
        hb.setSpacing(20);
        hb.setPadding(new Insets(10, 20, 10, 20));

        //Delete/Display tab
        //Hbox(combobox, button)
        HBox dishb = new HBox();
        tfFindFloor.getItems().add("1st Floor (Single Room)");
        tfFindFloor.getItems().add("2nd Floor (Twin Room)");
        tfFindFloor.getItems().add("3rd Floor (Twin Room)");
        tfFindFloor.getItems().add("4th Floor (President Suite)");
        tfFindFloor.setValue("1st Floor (Single Room)");
        dishb.getChildren().addAll(tfFindFloor, btnDis);
        dishb.setAlignment(Pos.BASELINE_RIGHT);
        dishb.setSpacing(20);
        dishb.setPadding(new Insets(10, 20, 10, 20));

        //---------------------------------------------------------------------------------------------//
        //Style
        //Textfield BG
        tfCustID.setStyle("-fx-background-color: rgba(53,89,319,0.2);");
        tfCustName.setStyle("-fx-background-color: rgba(53,89,319,0.2);");
        tfCustID.setStyle("-fx-background-color: rgba(53,89,319,0.2);");
        tfStay.setStyle("-fx-background-color: rgba(53,89,319,0.2);");
        tfFloor.setStyle("-fx-background-color: rgba(53,89,319,0.2);");
        tfRoomID.setStyle("-fx-background-color: rgba(53,89,319,0.2);");
        tfdelCustID.setStyle("-fx-background-color: rgba(53,89,319,0.2);");
        tffindID.setStyle("-fx-background-color: rgba(53,89,319,0.2);");

        //Textfield BG dark
        tfDisc.setStyle("-fx-background-color: rgba(53,89,119,0.2);");
        tfTotal.setStyle("-fx-background-color: rgba(53,89,119,0.2);");

        //Button Style
        btnBuy.setStyle("-fx-background-radius:30, 30, 30, 30; "
                + "-fx-background-color:#d1e2ff");
        btnDlt.setStyle("-fx-background-radius:30, 30, 30, 30; "
                + "-fx-background-color:#d1e2ff");
        btnDis.setStyle("-fx-background-radius:30, 30, 30, 30; "
                + "-fx-background-color:#d1e2ff");
        btnFind.setStyle("-fx-background-radius:30, 30, 30, 30; "
                + "-fx-background-color:#d1e2ff");

        //Title Style
        lbTitle.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        lbTitle.setTextFill(Color.BLUE);
        lbTitle.setStyle("-fx-background-color:  ");
        lbTitle.setAlignment(Pos.CENTER);

        lbbuy.setFont(Font.font("Time New Romans", FontWeight.BOLD, 20));
        lbbuy.setTextFill(Color.BLUE);
        lbbuy.setPadding(new Insets(10, 20, 0, 20));

        lbdlt.setFont(Font.font("Time New Romans", FontWeight.BOLD, 20));
        lbdlt.setTextFill(Color.BLUE);
        lbdlt.setPadding(new Insets(10, 20, 0, 20));

        lbdis.setFont(Font.font("Time New Romans", FontWeight.BOLD, 20));
        lbdis.setTextFill(Color.BLUE);
        lbdis.setPadding(new Insets(10, 20, 0, 20));

        lbfind.setFont(Font.font("Time New Romans", FontWeight.BOLD, 20));
        lbfind.setTextFill(Color.BLUE);
        lbfind.setPadding(new Insets(10, 20, 0, 20));

        //----------------------------------------------------------------------
        //ScrollPane
        //add tab
        sp.setStyle("-fx-background: #B1FFFF; -fx-border-color: #90EE90;");
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setPrefViewportWidth(500);
        sp.setPrefViewportHeight(250);
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        taTable.setEditable(false);
        //delete/display tab
        sp2.setStyle("-fx-background: #B1FFFF; -fx-border-color: #90EE90;");
        sp2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp2.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp2.setPrefViewportWidth(750);
        sp2.setPrefViewportHeight(200);
        sp2.setFitToWidth(true);
        sp2.setFitToHeight(true);
        taTable2.setEditable(false);
        //find tab
        sp3.setStyle("-fx-background: #B1FFFF; -fx-border-color: #90EE90;");
        sp3.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp3.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp3.setPrefViewportWidth(750);
        sp3.setFitToWidth(true);
        taTable3.setEditable(false);

        //----------------------------------------------------------------------------------------------//
        // VBox & HBox
        //add
        VBox vb = new VBox(10);
        vb.getChildren().addAll(lbbuy, gp, hb);
        vb.setStyle("-fx-background-color: #a5c6ff ");
        HBox add = new HBox();
        add.getChildren().addAll(vb);
        //delete
        VBox vb2 = new VBox(10);
        vb2.getChildren().addAll(lbdis, dishb, sp2, lbdlt, gp2);
        vb2.setStyle("-fx-background-color: #a5c6ff ");
        HBox dlt = new HBox();
        dlt.getChildren().addAll(vb2);
        // Find tab
        VBox vb3 = new VBox(10);
        vb3.getChildren().addAll(lbfind, gp3, sp3);
        vb3.setStyle("-fx-background-color: #a5c6ff ");
        HBox find = new HBox();
        find.getChildren().addAll(vb3);

        //----------------------------------------------------------------------------------------------------------------------
        //Process event
        btnBuy.setOnAction(e -> Add());
        btnDis.setOnAction(e -> Display(taTable2));
        btnDlt.setOnAction(e -> Delete());
        btnFind.setOnAction(e -> Find());
        tfFloor.setOnAction(e -> DisplayRoom(taTable));

        //Key Press event
        tfCustID.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.BACK_SPACE || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.TAB || e.getCode().isDigitKey()) {
            } else {
                Error("Invalid data", "Enter digits only!");
            }
        });
        tfdelCustID.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.BACK_SPACE || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.TAB || e.getCode().isDigitKey()) {
            } else {
                Error("Invalid data", "Enter digits only!");
            }
        });
        tffindID.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.BACK_SPACE || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.TAB || e.getCode() == KeyCode.SHIFT || e.getCode().isDigitKey()) {
            } else {
                Error("Invalid data", "Enter digits only!");
            }
        });
        tfCustName.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.BACK_SPACE || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.TAB || e.getCode() == KeyCode.SHIFT || e.getCode() == KeyCode.SPACE || e.getCode().isLetterKey()) {
            } else {
                Error("Error in Customer Name", "Name can only be Letter.");
            }
        });
       
        tfRoomID.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.BACK_SPACE || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.TAB || e.getCode() == KeyCode.SHIFT || e.getCode() == KeyCode.COMMA || e.getCode().isLetterKey() || e.getCode().isDigitKey()) {
            } else {
                Error("Invalid data", "Please Insert RoomID into RoomID text field with comma between roomIDs.");
            }
        });

        //------------------------------------------------------------------------------------------------//
        //Tab(Main)
        Tab tab1 = new Tab("Reserve Room");
        Tab tab2 = new Tab("Display/Checkout Room");
        Tab tab3 = new Tab("Find Customer");
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        tabPane.setStyle("-fx-background-color: yellow");
        tabPane.setStyle("-fx-background-color: #a5c6ff ");
        tabPane.getTabs().add(tab1);
        tab1.setContent(add);
        tabPane.getTabs().add(tab2);
        tab2.setContent(dlt);
        tabPane.getTabs().add(tab3);
        tab3.setContent(find);

        //---------------------------------------------------------------------------------------------------//
        //Scene setting
        Scene scene = new Scene(tabPane);
        primaryStage.setTitle("SKYE Hotel Reservation System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void Add() {

        try {
            int custId = Integer.parseInt(tfCustID.getText());
            String custName = tfCustName.getText();
            String custFloor = tfFloor.getValue();
            int custStay = Integer.parseInt(tfStay.getText());

            String str = "";
            String[] custRoom = tfRoomID.getText().split(",");

            //check room duplication
            for (int i = 0; i < custRoom.length; i++) {
                for (int j = i + 1; j < custRoom.length; j++) {
                    if (custRoom[i].equalsIgnoreCase(custRoom[j])) {
                        Error("Error in Room ID", "Duplicate the room ID.");
                        Clear(4);
                        return;
                    }
                }
            }
            //check room availability 
            int found = 0;
            for (int i = 0; i < custRoom.length; i++) {
                for (int j = 0; j <= 4; j++) {
                    for (int k = 0; k <= 4; k++) {
                        if (custRoom[i].equalsIgnoreCase(roomID[j][k])) {
                            found++;
                        }
                    }
                }
            }

            if (custId < numID) {
                Error("Error in Customer ID", "Enter atleast three integers!");
                Clear(1);
                return;

            }
            if (custName.length() < 1) {
                Error("Error in Customer Name", "Please enter your name!");
                Clear(2);
                return;
            }
            if (tfPCustType.isSelected() && tfRCustType.isSelected()) {
                Error("Error in Customer Type", "Please select one customer type.");
                return;
            }
            if (custFloor != null) {

            } else {
                Error("Error in Floor", "Please select one Floor!");
                return;
            }
            
            if (found == custRoom.length) {

                if (tfPCustType.isSelected()) {
                    Info("Added Successfully");
                    str = arrayList.add(new Member(custId, custName, custFloor, custRoom,custStay));
                    Clear(7);
                } else if (tfRCustType.isSelected()) {
                    Info("Added Successfully");
                    str = arrayList.add(new Non_Member(custId, custName, custFloor, custRoom, custStay));
                    Clear(7);
                }

                String[] temp;
                temp = str.split(",", 2);
                String disc = temp[0];
                String total = temp[1];
                tfDisc.setText(disc);
                tfTotal.setText(total);

                for (int i = 0; i < custRoom.length; i++) {
                    for (int j = 0; j <= 4; j++) {
                        for (int k = 0; k <= 4; k++) {
                            if (custRoom[i].equalsIgnoreCase(roomID[j][k])) {
                                roomID[j][k] = "XX";
                            }
                        }
                    }
                }

            } else if (!(found == custRoom.length)) {
                Error("Error in Room ID", "The selected room(s) is or are not found");
                tfRoomID.setText("");
            }

        } catch (NumberFormatException ex) {

            Error("Invalid Data", "Please enter integer number in Customer ID, Adult Quantity, and Kid Quantity!");
        }

    }

    public void Find() {
        String info = "";
        int custId = Integer.parseInt(tffindID.getText());

        if (custId < 100) { //at least three integer.
            Error("Invalid data in Customer ID", "Enter atleast three integers!");
            Clear(5);
        } else {
            info = arrayList.search(custId);
            taTable3.setText(info);
        }
    }

    public void Delete() {
        int custId = Integer.parseInt(tfdelCustID.getText());
        String[] custRoom;
        String custFloor;
        String[][] theRoomFloor = null;
        if (custId < 100) {
            Error("Invalid data in Customer ID", "Enter atleast three integers!");
            Clear(6);
        } else {
            custFloor = arrayList.getFloor(custId);
            custRoom = arrayList.remove(custId);

            if (custRoom[0].equals("null")) {
                Info("Remove Unsuccessfully, cannot find the customer id.");

            } else {
                Info("Remove Successfully");

                if (custFloor.equals("1st Floor")) {
                    theRoomFloor = roomFloorID1;
                } else if (custFloor.equals("2nd Floor")) {
                    theRoomFloor = roomFloorID2;
                } else if (custFloor.equals("3rd Floor")) {
                    theRoomFloor = roomFloorID3;
                } else if (custFloor.equals("4th Floor")) {
                    theRoomFloor = roomFloorID4;
                } else {
                    //empty
                }

                char p;
                char q;
                int r = 0;
                int s = 0;

                for (int i = 0; i < custRoom.length; i++) {
                    p = custRoom[i].charAt(0);
                    if (p == 'A') {
                        r = 0;
                    } else if (p == 'B') {
                        r = 1;
                    } else if (p == 'C') {
                        r = 2;
                    } else if (p == 'D') {
                        r = 3;
                    } else if (p == 'E') {
                        r = 4;
                    }
                    q = custRoom[i].charAt(1);
                    s = Integer.parseInt(String.valueOf(q)) - 1;
                    theRoomFloor[r][s] = custRoom[i];

                }
            }
        }
    }

    public void Display(TextArea a) {
        String custFloor = tfFindFloor.getValue().toString();
        if (custFloor.equals("1st Floor")) {
            createRoomID(roomFloorID1);
        } else if (custFloor.equals("2nd Floor")) {
            createRoomID(roomFloorID2);
        } else if (custFloor.equals("3rd Floor")) {
            createRoomID(roomFloorID3);
        } else if (custFloor.equals("4th Floor")) {
            createRoomID(roomFloorID4);
        } else {
            //empty
        }
        String output = "\n\t\t\t\t\t\t|\t\t\tFloor Screen\t\t\t\t|\n\n";

        for (int i = 0; i <= 4; i++) {
            output += "\t\t\t\t\t\t";
            for (int j = 0; j <= 4; j++) {
                output += " | " + roomID[i][j] + " | \t";

            }
            output += "\n";
        }
        a.setText(output);
    }

    public void DisplayRoom(TextArea a) {
        String custFloor = tfFloor.getValue().toString();

        if (custFloor.equals("1st Floor")) {
            createRoomID(roomFloorID1);
        } else if (custFloor.equals("2nd Floor")) {
            createRoomID(roomFloorID2);
        } else if (custFloor.equals("3rd Floor")) {
            createRoomID(roomFloorID3);
        } else if (custFloor.equals("4th Floor")) {
            createRoomID(roomFloorID4);
        } else {
            //empty
        }

        String output = "Please Insert RoomID into RoomID text field for reservation\n";
        for (int i = 0; i <= 1; i++) {
            output += "\t\n________________________________________________________________________________________________________\n";
            for (int j = 0; j <= 9; j++) {
                output += " | " + roomID[i][j] + " | \t";
            }
            output += " \t\n_______________________________________________________________________________________________________\n";
        }
        a.setText(output);

    }

    public static void createRoomID(String[][] id) {
        boolean empty = true;

        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 9; j++) {
                if (id[i][j] != null) {
                    empty = false;
                    break;
                }
            }
        }

        if (empty) {
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 9; j++) {
                    id[i][j] = sampleRoomID[i][j];
                }
            }
        } else {
            // empty	
        }
        roomID = id;

    }

    // Error Method
    public void Error(String e1, String e2) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(e1);
        alert.setHeaderText(null);
        alert.setContentText(e2);
        alert.show();
    }

    // Inform Method
    public void Info(String e) {
        JOptionPane.showMessageDialog(null, e);
    }

    // Clear Method
    public void Clear(int option) {
        switch (option) {
            case 1:
                tfCustID.clear();
                tfCustID.requestFocus();
                break;
            case 2:
                tfCustName.clear();
                tfCustName.requestFocus();
                break;
            case 3:
                break;
            case 4:
                tfRoomID.clear();
                tfRoomID.requestFocus();
                break;
            case 5:
                tffindID.clear();
                tffindID.requestFocus();
                break;
            case 6:
                tfdelCustID.clear();
                tfdelCustID.requestFocus();
                break;
            case 7:
                tfCustID.clear();
                tfCustName.clear();
                tfRoomID.clear();
                tfFloor.setValue("");
                group.selectToggle(null);
                taTable.setText("");
                break;
            default:
                break;
        }
    }

    //Spinner Coverter
    class MyConverter extends StringConverter<Integer> {

        @Override
        public String toString(Integer object) {
            return object + "";
        }

        @Override
        public Integer fromString(String string) {
            return Integer.parseInt(string);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
