package com.runner;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A Scene extension used as an ending scene. It will be used to display the player's score as well as the possibility to play again or exit the game.
 */
public class LosingScene extends Scene {
    private GameScene gameScene;
    private Pane p;
    private MusicPlayer player;

    private Text scoreTxt;
    private VBox box;
    final String IDLE_BUTTON_STYLE = " -fx-font-size:20px; -fx-background-color: #525252; -fx-border-color: #000000; -fx-text-fill: #ffffff ";
    final String HOVERED_BUTTON_STYLE = "-fx-font-size:20px; -fx-background-color: #ffffffff; -fx-border-color: #000000; -fx-text-fill: #000000";

    /**
     * Not much to see here. Just the constructor of the losing scene. Though it can seem quite long, it is only filled with lines of code intended to improve the visual aspect of this scene.
     * @param primaryStage the primaryStage used to make a link with the GameScene when the player wants to play again
     * @param p the pane element associated with the scene
     * @param width the scene's width in pixels
     * @param height the scene's height in pixels
     */
    public LosingScene(Stage primaryStage, Pane p, double width, double height) {
        super(p, width, height);
        this.p = p;
        p.setStyle("-fx-background-color: black");

        Image dead_hero = new Image(".\\img\\dead_link.png",80, 80,false,true);
        ImageView link = new ImageView(dead_hero);
        link.setPreserveRatio(true);
        link.setFitHeight(250);
        link.setY(30);
        link.setX(195);

        player = new MusicPlayer("src\\music\\game_over.mp3",0,10);
        player.setRepeat(1);

        Text title = new Text(215,90,"GAME OVER");
        title.setFill(Color.RED);
        Font titleFont = Font.loadFont("file:src//fonts//hyrule_font.ttf", 45);
        title.setFont(titleFont);
        Effect glow = new Glow(1.0);
        title.setEffect(glow);

        Button reloadBtn = new Button("Rejouer");
        reloadBtn.setMinWidth(150);
        reloadBtn.setStyle("-fx-font-size:20px; -fx-background-color: #525252; -fx-border-color: #000000; -fx-text-fill: #ffffff ");
        reloadBtn.setOnMouseEntered(e -> reloadBtn.setStyle(HOVERED_BUTTON_STYLE));
        reloadBtn.setOnMouseExited(e -> reloadBtn.setStyle(IDLE_BUTTON_STYLE));
        reloadBtn.setOnAction(e -> {
            player.stopMusic();
            gameScene.reset();
            primaryStage.setScene(gameScene);
        });

        Button quitBtn = new Button("Quitter");
        quitBtn.setMinWidth(150);
        quitBtn.setStyle("-fx-font-size:20px; -fx-background-color: #525252; -fx-border-color: #000000; -fx-text-fill: #ffffff ");
        quitBtn.setOnMouseEntered(e -> quitBtn.setStyle(HOVERED_BUTTON_STYLE));
        quitBtn.setOnMouseExited(e -> quitBtn.setStyle(IDLE_BUTTON_STYLE));
        quitBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ce n'est qu'un au revoir");
            alert.setHeaderText("Vous êtes sur le point de fermer l'application");
            alert.setContentText("Vous êtes sûr de vouloir quitter le jeu ?");
            if(alert.showAndWait().get() == ButtonType.OK) {
                primaryStage.close();
            }
        });

        Font btnFont = Font.loadFont("file:src//fonts//minishcap.ttf", 45);
        quitBtn.setFont(btnFont);
        reloadBtn.setFont(btnFont);

        box = new VBox(5);
        box.setTranslateX(240);
        box.setTranslateY(260);
        box.getChildren().addAll(reloadBtn, quitBtn);

        scoreTxt = new Text(255,240,"0m");
        scoreTxt.setFill(Color.SNOW);
        scoreTxt.setStroke(Color.BLACK);
        scoreTxt.setFont(Font.font ("Helvetica", 60));

        p.getChildren().addAll( link, title, box, scoreTxt);
    }

    /**
     * Method used to make the link between the GameScene score and its display on the ending scene.
     * @param score the player's score
     */
    public void showScore(Integer score){
        scoreTxt.setText(score.toString()+"m");
    }

    /**
     * Method used to make the link between the LosingScene and the GameScene. If the GameScene is set before initializing it, it can cause error, hence this method.
     * @param gms the GameScene element to link to the LosingScene, in order to reload the game if the player wants to start again
     */
    public void setScene(GameScene gms){
        this.gameScene = gms;
    }

    /**
     * Start the scene, ie play the "game over" music and update the score.
     */
    public void start(int score){
        showScore(score);
        player.startMusic();
    }

}
