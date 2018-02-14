package TheGame.GUI;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EnterController implements Initializable{
    @FXML
    private RadioButton whiteFirst;

    @FXML
    private  RadioButton blackFirst;

    @FXML
    private BooleanProperty isBlackFirst;

    @FXML
    private BooleanProperty isWhiteFirst;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private ColorPicker blackPick;

    private Color blackColor;

    @FXML
    private ColorPicker whitePick;

    private Color whiteColor;

    private int size;

    private int firstPlayer;

    @FXML private MenuButton BoardSize;

    @FXML
    private Label boardhere;

    @FXML
    private Button startGame;

    private String settingsPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        blackFirst = new RadioButton();
        whiteFirst = new RadioButton();
        whitePick = new ColorPicker();

        settingsPath = new File("").getAbsolutePath() + "/src/TheGame/GameSettings.txt";

        String line;

        try {
            FileReader fileReader = new FileReader(settingsPath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //first line: who is the first player
            line = bufferedReader.readLine();
            String[] param1 = line.split("=");
            firstPlayer =Integer.parseInt(param1[1]);
            if (firstPlayer == -1){
                blackFirst.setSelected(true);
                whiteFirst.setSelected(false);
            }else{
                blackFirst.setSelected(false);
                whiteFirst.setSelected(true);
            }

            //second line: black color
            line = bufferedReader.readLine();
            String[] param2 = line.split("=");
            blackColor = Color.web(param2[1]);
            blackPick = new ColorPicker(blackColor);


            //third line: white color
            line = bufferedReader.readLine();
            String[] param3 = line.split("=");
            whiteColor = Color.web(param3[1]);
            whitePick.setValue(whiteColor);

            //forth line: board size
            line = bufferedReader.readLine();
            String[] param4 = line.split("=");
            size = Integer.parseInt(param4[1]);

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + settingsPath + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '" + settingsPath + "'");
        }
    }

    public void letsGo(javafx.event.ActionEvent actionEvent) {
        if(blackFirst.isSelected()){
            firstPlayer = -1;
        } else{
            firstPlayer = 1;
        }


        if (size == 0) {
            boardhere.setText("forgot me :)");
            boardhere.setTextFill(Color.RED);
                } else {
                    boardhere.setText("this one is good :)");
                    boardhere.setTextFill(Color.BLUE);
                }
        GameController.getMenuStage().close();

    }



    public void four(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("4X4");
        size = 4;
    }
    public void six(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("6X6");
        size = 6;
    }
    public void eight(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("8X8");
        size = 8;
    }
    public void ten(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("10X10");
        size = 10;
    }
    public void twolve(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("12X12");
        size = 12;
    }
    public void fourteen(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("14X14");
        size = 14;

    }
    public void sixteen(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("16X16");
        size = 16;

    }
    public void eighteen(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("18X18");
        size = 18;

    }
    public void twenty(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("20X20");
        size = 20;

    }


    public void setBlackColor(ActionEvent actionEvent) {
        blackColor = blackPick.getValue();
        File file = new File(settingsPath);
        String color = new String();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while((line = reader.readLine()) != null)
            {
                oldtext += line + "\r\n";
                String[] param = line.split("=");
                if(param[0].equals("Black")) {
                    color = line;
                }

            }
            reader.close();

            //To replace a line in a file
            String newtext = oldtext.replaceAll(color,"Black=" + blackColor.toString());

            FileWriter writer = new FileWriter(settingsPath);
            writer.write(newtext);
            writer.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void setWhiteColor(ActionEvent actionEvent) {
        whiteColor = whitePick.getValue();
        File file = new File(settingsPath);
        String color = new String();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while((line = reader.readLine()) != null)
            {
                oldtext += line + "\r\n";
                String[] param = line.split("=");
                if(param[0].equals("White")) {
                    color = line;
                }

            }
            reader.close();

            //To replace a line in a file
            String newtext = oldtext.replaceAll(color,"White=" + whiteColor.toString());

            FileWriter writer = new FileWriter(settingsPath);
            writer.write(newtext);
            writer.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

    }
}
