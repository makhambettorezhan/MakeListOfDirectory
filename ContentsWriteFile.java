import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContentsWriteFile extends Application {
    @Override
    public void start(Stage primaryStage) {

        Filewalker fileWalker = new Filewalker();

        Font fontLabel = Font.font("Times New Roman", 18);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(0, 0, 10, 0));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        //gridPane.setStyle("-fx-border: 1px solid black");

        Label lblDir = new Label("Specify the DIRECTORY you want the list of:"); lblDir.setFont(fontLabel);
        TextField tfDir = new TextField();
        tfDir.setPrefWidth(150);
        
        Label lblFile = new Label("Enter the FILE name where you want the list to be placed:"); lblFile.setFont(fontLabel);
        TextField tfFile = new TextField();
        tfFile.setText("c://Users//Lenovo//Desktop//AllMoviesIHave.txt");
        tfFile.setPrefWidth(150);

        Label lblOrder = new Label("Order: (By Name or By Directories)"); lblOrder.setFont(fontLabel);
        ComboBox<String> cboOrder = new ComboBox<>();
        cboOrder.getItems().addAll("By Name", "By Directory");
        cboOrder.setStyle("-fx-border: red");
        cboOrder.setValue("By Name");

        TextArea ta = new TextArea();
        ta.setPrefColumnCount(50);
        ta.setPrefRowCount(20);

        Button btn = new Button("Show and Print");

        btn.setOnAction(e -> {
            fileWalker.walk(tfDir.getText(), cboOrder.getValue().equals("By Name"));
            ArrayList<String> list = fileWalker.getList();
            String moviesString = "";

            try {
                PrintWriter fileWriter = new PrintWriter(tfFile.getText());
                    
                for ( String movie: list) {
                    fileWriter.print(movie);
                    moviesString += movie;
                }
                fileWriter.close();
            } catch(IOException ex) {
    
            }

            ta.setText(moviesString);
        });



        
        gridPane.add(lblDir, 0, 0);
        gridPane.add(tfDir, 1, 0);

        gridPane.add(lblFile, 0, 1);
        gridPane.add(tfFile, 1, 1);

        gridPane.add(lblOrder, 0, 2);
        gridPane.add(cboOrder, 1, 2);

        gridPane.add(btn, 0, 3);
        //gridPane.add(ta, 0, 4);

        pane.setTop(gridPane);
        pane.setCenter(ta);

        Scene scene = new Scene(pane, 700, 800);
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }
}