package TheGame.GUI;

import TheGame.GameCode.Board;
import TheGame.GameCode.GameRules;
import TheGame.GameCode.Reversi_rules;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.*;


public class GameBoardController extends GridPane {
    private static Board board;
    private static GameRules rules;
    private static int currnetPlayer;
    private static int[] score = new int[2];
    private static GameController game;
    private static Color blackColor;
    private static Color whiteColor;
    private static Tile[][] boardTiles;
    public static int TILE_SIZE;
//    private static final int FREE = 0;
//    private static final int WHITE = 1;
//    private static final int BLACK = -1;

    private Group tileGroup = new Group();
    private Group diskGroup = new Group();


    public GameBoardController(GameController g) {
        int size = 8;
        String fileName = new File("").getAbsolutePath() + "/src/TheGame/GameSettings.txt";


        try {

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //first line: who is the first player
            String line = bufferedReader.readLine();
            String[] param1 = line.split("=");
            currnetPlayer = Integer.parseInt(param1[1]);

            //second line: black color
            line = bufferedReader.readLine();
            String[] param2 = line.split("=");
            blackColor = Color.web(param2[1]);

            //third line: white color
            line = bufferedReader.readLine();
            String[] param3 = line.split("=");
            whiteColor = Color.web(param3[1]);

            //forth line: board size
            line = bufferedReader.readLine();
            String[] param4 = line.split("=");
            size = Integer.parseInt(param4[1]);

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '" + fileName + "'");
        }

        board = new Board(size, size);
        game = g;
        boardTiles = new Tile[board.getRow()][board.getColumn()];
        rules = new Reversi_rules();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


    }

    public void draw() {
        this.getChildren().clear();
        tileGroup.getChildren().clear();
        this.getChildren().addAll(tileGroup);
        int height = (int) this.getPrefHeight();
        TILE_SIZE = height / board.getRow();


        for (int i = 1; i <= board.getRow(); i++) {
            for (int j = 1; j <= board.getColumn(); j++) {

                boardTiles[i - 1][j - 1] = new Tile(TILE_SIZE, j, i, blackColor, whiteColor);
                tileGroup.getChildren().add(boardTiles[i - 1][j - 1]);


                if (board.getCell(i, j) == 1) {
                    boardTiles[i - 1][j - 1].setColor(1);
                } else if (board.getCell(i, j) == -1) {
                    boardTiles[i - 1][j - 1].setColor(-1);

                }
            }
        }
    }

    public static void updateTiles() {
        for (int i = 1; i <= board.getRow(); i++) {
            for (int j = 1; j <= board.getColumn(); j++) {

                if (board.getCell(i, j) == 1) {
                    boardTiles[i - 1][j - 1].setColor(1);
                } else if (board.getCell(i, j) == -1) {
                    boardTiles[i - 1][j - 1].setColor(-1);

                }
            }
        }
        getRules().updateScore(GameBoardController.getBoard());
        score = rules.getScore();
        game.updateVisibleScore(score);

    }


    public static GameRules getRules() {
        return rules;
    }

    public static Board getBoard() {
        return board;
    }

    public static int getCurrnetPlayer() {
        return currnetPlayer;
    }

    public static void setCurrnetPlayer(int turn) {
        currnetPlayer = turn;
        game.setTurn(currnetPlayer);
    }

    public static Color getBlackColor() {
        return blackColor;
    }
    public static Color getWhiteColor() {
        return whiteColor;
    }
}