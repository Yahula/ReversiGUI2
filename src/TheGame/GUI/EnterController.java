package TheGame.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EnterController implements Initializable {
    @FXML
    public ToggleGroup toggleGroup;

    @FXML
    private RadioButton whiteFirst;

    @FXML
    private RadioButton blackFirst;

    @FXML
    private MenuButton blackCol;

    private Color blackColor;

    @FXML
    private MenuButton whiteCol;

    private Color whiteColor;

    private int firstPlayer;

    @FXML
    private MenuButton BoardSize;

    @FXML
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
            firstPlayer = Integer.parseInt(param1[1]);
            if (firstPlayer == -1) {
                blackFirst.setSelected(true);
                whiteFirst.setSelected(false);
            } else {
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
            BoardSize.setText(param4[1] + "X" + param4[1]);

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + settingsPath + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '" + settingsPath + "'");
        }
    }

    //set Black button
    public void setBlackRed() {
        setDefaultColor(-1, "RED");
    }

    public void setBlackGreen() {
        setDefaultColor(-1, "GREEN");
    }

    public void setBlackYellow() {
        setDefaultColor(-1, "YELLOW");
    }

    public void setBlackPurple() {
        setDefaultColor(-1, "PURPLE");
    }

    public void setBlackBlue() {
        setDefaultColor(-1, "BLUE");
    }

    public void setBlackGrey() {
        setDefaultColor(-1, "GREY");
    }

    public void setBlackBlack() {
        setDefaultColor(-1, "BLACK");
    }

    public void setBlackWhite() {
        setDefaultColor(-1, "WHITE");
    }

    //set White button
    public void setWhiteRed() {
        setDefaultColor(1, "RED");
    }

    public void setWhiteGreen() {
        setDefaultColor(1, "GREEN");
    }

    public void setWhiteYellow() {
        setDefaultColor(1, "Yellow");
    }

    public void setWhitePurple() {
        setDefaultColor(1, "PURPLE");
    }

    public void setWhiteBlue() {
        setDefaultColor(1, "BLUE");
    }

    public void setWhiteGrey() {
        setDefaultColor(1, "GREY");
    }

    public void setWhiteBlack() {
        setDefaultColor(1, "BLACK");
    }

    public void setWhiteWhite() {
        setDefaultColor(1, "WHITE");
    }

    public void setDefaultColor(int player, String color) {
        Color setColor;
        String newCol;

        switch (color) {
            case "RED":
                setColor = Color.RED;
                break;
            case "GREEN":
                setColor = Color.GREEN;
                break;
            case "YELLOW":
                setColor = Color.YELLOW;
                break;
            case "PURPLE":
                setColor = Color.PURPLE;
                break;
            case "BLUE":
                setColor = Color.BLUE;
                break;
            case "GREY":
                setColor = Color.GREY;
                break;
            case "BLACK":
                setColor = Color.BLACK;
                break;
            case "WHITE":
                setColor = Color.WHITE;
                break;
            default:
                if (player == 1) {
                    setColor = Color.WHITE;
                } else {
                    setColor = Color.BLACK;
                }
        }

        if (player == 1) {
            whiteColor = setColor;
            whiteCol.setText(color);
            whiteCol.setTextFill(whiteColor);
            newCol = "White=" + color;
        } else {
            blackColor = setColor;
            blackCol.setText(color);
            blackCol.setTextFill(blackColor);
            newCol = "Black=" + color;
        }

        File file = new File(settingsPath);
        String s = new String();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
                String[] param = line.split("=");
                if (player == 1) {
                    if (param[0].equals("White")) {
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
            String newtext = oldtext.replaceAll(s, newCol);

            FileWriter writer = new FileWriter(settingsPath);
            writer.write(newtext);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void letsGo() {
        GameController.getMenuStage().close();
    }

    public void four() {
        BoardSize.setText("4X4");
        setDefaultSize("4");
    }

    public void six() {
        BoardSize.setText("6X6");
        setDefaultSize("6");
    }

    public void eight() {
        BoardSize.setText("8X8");
        setDefaultSize("8");

    }

    public void ten() {
        BoardSize.setText("10X10");
        setDefaultSize("10");
    }

    public void twolve() {
        BoardSize.setText("12X12");
        setDefaultSize("12");
    }

    public void fourteen() {
        BoardSize.setText("14X14");
        setDefaultSize("14");
    }

    public void sixteen() {
        BoardSize.setText("16X16");
        setDefaultSize("16");
    }

    public void eighteen() {
        BoardSize.setText("18X18");
        setDefaultSize("18");

    }

    public void twenty() {
        BoardSize.setText("20X20");
        setDefaultSize("20");

    }

    public void setDefaultSize(String size) {
        File file = new File(settingsPath);
        String s = new String();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
                String[] param = line.split("=");
                if (param[0].equals("Size")) {
                    s = line;
                }
            }
            reader.close();

            size = "Size=" + size;

            //To replace a line in a file
            String newtext = oldtext.replaceAll(s, size);

            FileWriter writer = new FileWriter(settingsPath);
            writer.write(newtext);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void setDefaultFirst() {
        File file = new File(settingsPath);
        String s = new String();
        String first;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
                String[] param = line.split("=");
                if (param[0].equals("First")) {
                    s = line;
                }
            }
            reader.close();

            if (toggleGroup.getSelectedToggle() == whiteFirst) {
                first = "First=1";
            } else {
                first = "First=-1";
            }

            //To replace a line in a file
            String newtext = oldtext.replaceAll(s, first);

            FileWriter writer = new FileWriter(settingsPath);
            writer.write(newtext);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
