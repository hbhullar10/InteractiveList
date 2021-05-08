import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class Calender extends Application {
    // BASICALLY INT ANOTHER ARRAY STORE IT IN EACH INDEX OF THE SELECTION ARRAY, AND USE THE COUNT VARIABLE TO INCREASE IN INDEX.
    private BorderPane root; // THE MAIN PANEL OF THE ROOT
    private Pane main; // THE MAIN PANE WHERE THE MAIN SCREEN IS ON
    public static int count = 0;

    private ArrayList<String> Selection;
    public Calender() {
        Selection = new ArrayList<>();
    }

    public void addList(String item) {
        Selection.add(item);
    }

    @Override
    public String toString() {
        return Selection.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        main = new Pane();
        Cosmetic();
        root = new BorderPane();
        root.setPrefHeight(600);
        root.setPrefWidth(300);
        root.getChildren().add(main);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Buttons.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Interactive List");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Button updateMessageButton;
    private TextField messageTextField;

    public void Cosmetic() { // FOR THE MAIN PANE CENTRE SCREEN ENTER BUTTON AND TEXTFIELD
        Rectangle mainPane = new Rectangle(25, 70, 260, 480);
        mainPane.getStyleClass().add("main");
        mainPane.setStroke(Color.BLACK);
        mainPane.setStrokeWidth(4);
        updateMessageButton = new Button("Enter");
        messageTextField = new TextField();
        messageTextField.setPrefWidth(200);
        HBox center = new HBox(10, messageTextField, updateMessageButton);
        center.setPadding(new Insets(27, 0, 0, 38));
        updateMessageButton.setOnAction(new EnterHandler());
        main.getChildren().addAll(center, mainPane);
    }

    private String user;
    private Rectangle first;
    private Rectangle top;
    private VBox msg;
    private int total = 0;
    private Text message;
    private String output;

    private class EnterHandler implements EventHandler<ActionEvent> {
        boolean flag = true;
        int x = 32;
        int y = 88;
        int numbers = 1;
        final int fontSize = 17;

        @Override
        public void handle(ActionEvent e) {
            user = messageTextField.getText();
            output = numbers + ". " + messageTextField.getText();
            if (total < 9) {
                if (flag) {
                    addList(user);
                    numbers++;
                    first = new Rectangle(x, y, 245, 35);
                    first.getStyleClass().add("my-rect");
                    first.setFill(Color.WHITE);
                    first.setStroke(Color.BLACK);
                    first.setStrokeWidth(2.5);
                    first.setOnMouseClicked(new CrossOut());
                    message = new Text(x, y, " "); // x and y coordinates and the initial message which is hello world
                    msg = new VBox(10, message);
                    msg.setPadding(new Insets(y + 5, 0, 0, 35));
                    message.setFont(Font.font("Vegan Style", fontSize));
                    message.setFill(Color.BLACK);
                        message.setText(Selection.get(total));
                        total++;
                        main.getChildren().addAll(first, msg);
                        messageTextField.setText("");
                        count++;
                        flag = false;
                } else {
                    addList(user);
                    numbers++;
                    top = new Rectangle(x, y += 50, 245, 35);
                    top.getStyleClass().add("my-rect");
                    top.setStroke(Color.BLACK);
                    top.setStrokeWidth(2.5);
                    top.setFill(Color.WHITE);
                    top.setOnMouseClicked(new CrossOut());
                    message = new Text(x, y, ""); // x and y coordinates and the initial message which is hello world\
                    msg = new VBox(10, message);
                    msg.setPadding(new Insets(y + 5, 0, 0, 35));
                    message.setFont(Font.font("Helvetica Now", fontSize));
                    message.setFill(Color.BLACK);
                        message.setText(Selection.get(total));
                        total++;
                        main.getChildren().addAll(top, msg);
                    messageTextField.setText("");
                }
            }

        }
    }
    private Line cross;
    private int linePositionXnY = 105;
    private class CrossOut implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            if (e.getSource() == first) {
                cross = new Line(35, 105, 275, 105);
                cross.setStroke(Color.BLACK);
                cross.setStrokeWidth(4);
                main.getChildren().add(cross);
                System.out.println(Selection.toString());
            } else {
                cross = new Line(35, linePositionXnY += 50, 275, linePositionXnY += 0);
                cross.setStroke(Color.BLACK);
                cross.setStrokeWidth(4);
                main.getChildren().add(cross);
            }
        }
    }
    }