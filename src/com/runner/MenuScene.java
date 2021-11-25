
package com.runner;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;


public class MenuScene extends Scene {
    private GameScene game;
    private OptionScene options;
    private Pane p;
    private VBox box;
    private Hero myhero;
    final String IDLE_BUTTON_STYLE = " -fx-font-size:20px; -fx-background-color: #525252; -fx-border-color: #000000; -fx-text-fill: #ffffff ";
    final String HOVERED_BUTTON_STYLE = "-fx-font-size:20px; -fx-background-color: #ffffffff; -fx-border-color: #000000; -fx-text-fill: #000000";

    public MenuScene(Stage primaryStage, Pane p, double width, double height, boolean b) {
        super(p, width, height, b);
        this.p = p;
        Image back = new Image(".\\img\\menuback.jpg",width, height,false,true);
        ImageView background = new ImageView(back);

        myhero = new Hero(300, 100, 0, 0,100_000_000,6,85,100,85);
        myhero.getImgview().setPreserveRatio(true);
        myhero.getImgview().setFitHeight(250);

        Text title = new Text(50,90,"JavaFX Runner");
        title.setFill(Color.SNOW);
        title.setStroke(Color.BLACK);
        title.setFont(Font.font ("Impact", 60));

        Rectangle backBox1 = new Rectangle(150, 50);
        backBox1.setFill(Color.WHITESMOKE);
        backBox1.setOpacity(0.5);
        backBox1.setX(60);
        backBox1.setY(160);

        Button playBtn = new Button("Jouer");
        playBtn.setMinWidth(150);
        playBtn.setStyle(IDLE_BUTTON_STYLE);
        playBtn.setOnMouseEntered(e -> playBtn.setStyle(HOVERED_BUTTON_STYLE));
        playBtn.setOnMouseExited(e -> playBtn.setStyle(IDLE_BUTTON_STYLE));
        playBtn.setOnAction(e -> {
            primaryStage.setScene(game);
            game.Start();
            timerAnim.stop();
        });

        Rectangle backBox2 = new Rectangle(150, 50);
        backBox2.setFill(Color.WHITESMOKE);
        backBox2.setOpacity(0.5);
        backBox2.setX(60);
        backBox2.setY(225);

        Button optionsBtn = new Button("Options");
        optionsBtn.setMinWidth(150);
        optionsBtn.setStyle(IDLE_BUTTON_STYLE);
        optionsBtn.setOnMouseEntered(e -> optionsBtn.setStyle(HOVERED_BUTTON_STYLE));
        optionsBtn.setOnMouseExited(e -> optionsBtn.setStyle(IDLE_BUTTON_STYLE));
        optionsBtn.setOnAction(e -> {
            primaryStage.setScene(options);
        });

        Rectangle backBox3 = new Rectangle(150, 50);
        backBox3.setFill(Color.WHITESMOKE);
        backBox3.setOpacity(0.5);
        backBox3.setX(60);
        backBox3.setY(290);

        Button quitBtn = new Button("Quitter");
        quitBtn.setMinWidth(150);
        quitBtn.setStyle(IDLE_BUTTON_STYLE);
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

        box = new VBox(20);
        box.setTranslateX(70);
        box.setTranslateY(170);
        box.getChildren().addAll(playBtn,optionsBtn,quitBtn);

        p.getChildren().addAll( background,myhero.getImgview(),title, backBox1, backBox2, backBox3,box);
        timerAnim.start();
    }

    public void setScene(GameScene game, OptionScene options){
        this.game = game;
        this.options = options;
    }


    public void render() {
        myhero.getImgview().setX(myhero.getXcoor());
        myhero.getImgview().setY(myhero.getYcoor());
    }


    private AnimationTimer timerAnim = new AnimationTimer() {
        private long lastUpdate = 0 ;

        @Override
        public void handle(long now) {
                myhero.update(now, 0);
                render();
                lastUpdate = now ;
            }
    };
}

