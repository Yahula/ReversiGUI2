package TheGame.GUI;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
    private MenuButton blackCol;

    private Color blackColor;

    @FXML
    private MenuButton whiteCol;

    private Color whiteColor;

    private int size;

    private int firstPlayer;

    @FXML private MenuButton BoardSize;

    @FXML
    private Label boardhere;

    private String settingsPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
            blackCol.setText(param2[1]);
            blackCol.setTextFill(Color.web(param2[1]));


            //third line: white color
            line = bufferedReader.readLine();
            String[] param3 = line.split("=");
            whiteColor = Color.web(param3[1]);
            whiteCol.setText(param3[1]);
            whiteCol.setTextFill(Color.web(param3[1]));



            //forth line: board size
            line = bufferedReader.readLine();
            String[] param4 = line.split("=");
            size = Integer.parseInt(param4[1]);
            BoardSize.setText(param4[1]+"X"+param4[1]);

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

    //set Black button
    public void setBlackRed(ActionEvent actionEvent){
        setDefaultColor(-1,"RED");
    }
    public void setBlackGreen(ActionEvent actionEvent){
        setDefaultColor(-1,"GREEN");
    }
    public void setBlackYellow(ActionEvent actionEvent){
        setDefaultColor(-1,"YELLOW");
    }
    public void setBlackPurple(ActionEvent actionEvent){
        setDefaultColor(-1,"PURPLE");
    }
    public void setBlackBlue(ActionEvent actionEvent){
        setDefaultColor(-1,"BLUE");
    }
    public void setBlackGrey(ActionEvent actionEvent){
        setDefaultColor(-1,"GREY");
    }
    public void setBlackBlack(ActionEvent actionEvent){
        setDefaultColor(-1, "BLACK");
    }
    public void setBlackWhite(ActionEvent actionEvent){
        setDefaultColor(-1,"WHITE");
    }

    //set White button
    public void setWhiteRed(ActionEvent actionEvent){
        setDefaultColor(1,"RED");
    }
    public void setWhiteGreen(ActionEvent actionEvent){
        setDefaultColor(1,"GREEN");
    }
    public void setWhiteYellow(ActionEvent actionEvent){
        setDefaultColor(1,"Yellow");
    }
    public void setWhitePurple(ActionEvent actionEvent){
        setDefaultColor(1,"PURPLE");
    }
    public void setWhiteBlue(ActionEvent actionEvent){
        setDefaultColor(1,"BLUE");
    }
    public void setWhiteGrey(ActionEvent actionEvent){
        setDefaultColor(1,"GREY");
    }
    public void setWhiteBlack(ActionEvent actionEvent){
        setDefaultColor(1,"BLACK");
    }
    public void setWhiteWhite(ActionEvent actionEvent){
        setDefaultColor(1,"WHITE");
    }
    public void setDefaultColor(int player, String color){
        Color setColor;
        String newCol;

        switch (color){
            case "RED":
                setColor = Color.RED;
                break;
            case "GREEN" :
                setColor = Color.GREEN;
                break;
            case "YELLOW" :
                setColor = Color.YELLOW;
                break;
            case "PURPLE" :
                setColor = Color.PURPLE;
                break;
            case "BLUE" :
                setColor = Color.BLUE;
                break;
            case "GREY" :
                setColor = Color.GREY;
                break;
            case "BLACK" :
                setColor = Color.BLACK;
                break;
            case "WHITE" :
                setColor = Color.WHITE;
                break;
            default:
                if (player == 1){
                    setColor = Color.WHITE;
                } else{
                    setColor = Color.BLACK;
                }
        }

        if (player == 1){
            whiteColor = setColor;
            whiteCol.setText(color);
            whiteCol.setTextFill(whiteColor);
            newCol = "White=" + color;
        } else{
            blackColor = setColor;
            blackCol.setText(color);
            blackCol.setTextFill(blackColor);
            newCol = "Black=" + color;

        }

        File file = new File(settingsPath);
        String s = new String();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
                String[] param = line.split("=");
                if(player ==1){
                    if (param[0].equals("White")){
                        s = line;
                    }
                } else {
                    if (param[0].equals("Black")) {
                        s = line;
                    }
                }
            }
            reader.close();

            //To replace a line in a file
            String newtext = oldtext.replaceAll(s,newCol);

            FileWriter writer = new FileWriter(settingsPath);
            writer.write(newtext);
            writer.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

    }


    public void letsGo(javafx.event.ActionEvent actionEvent) {
        GameController.getMenuStage().close();

    }



    public void four(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("4X4");
        size = 4;
        setDefaultSize("4");
    }
    public void six(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("6X6");
        size = 6;
        setDefaultSize("6");
    }
    public void eight(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("8X8");
        size = 8;
        setDefaultSize("8");

    }
    public void ten(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("10X10");
        size = 10;
        setDefaultSize("10");
    }
    public void twolve(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("12X12");
        size = 12;
        setDefaultSize("12");
    }
    public void fourteen(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("14X14");
        size = 14;
        setDefaultSize("14");
    }
    public void sixteen(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("16X16");
        size = 16;
        setDefaultSize("16");
    }
    public void eighteen(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("18X18");
        size = 18;
        setDefaultSize("18");

    }
    public void twenty(javafx.event.ActionEvent actionEvent) {
        BoardSize.setText("20X20");
        size = 20;
        setDefaultSize("20");

    }

    public void setDefaultSize(String size){
        File file = new File(settingsPath);
        String s = new String();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
                String[] param = line.split("=");
                if (param[0].equals("Size")){
                        s = line;
                }
            }
            reader.close();

            size = "Size=" + size;

            //To replace a line in a file
            String newtext = oldtext.replaceAll(s,size);

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
