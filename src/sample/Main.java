package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.gameprocess.Game;
import sample.gameprocess.GameEvents;
import sample.interfaceview.*;
import sample.interfaceview.MenuItem;
import sample.models.GameSave;

import javax.tools.Tool;
import java.util.Date;

import static sample.files.FilesConstNames.*;
import static sample.models.CreatedGameObjects.tamo1;
import static sample.models.CreatedGameObjects.tamo2;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Tamagotchi");
        primaryStage.setHeight(560);
        primaryStage.setWidth(960);

        Pane root = new Pane();


        //background
        Image image = new Image(getClass().getResourceAsStream(BACKGROUND));
        ImageView img = new ImageView(image);
        img.setFitHeight(540);
        img.setFitWidth(960);
        root.getChildren().add(img);


        //main Menu
        MenuItem startGame = new MenuItem("ИГРАТЬ");
        MenuItem exitGame = new MenuItem("ВЫЙТИ ИЗ ИГРЫ");
        SubMenu mainSubMenu = new SubMenu(startGame, exitGame);

        MenuItem chooseTamo1 = new MenuItem("ТАМАГОЧИ 1");
        MenuItem chooseTamo2 = new MenuItem("ТАМАГОЧИ 2");
        MenuItem back = new MenuItem("НАЗАД");
        SubMenu newGameSubMenu = new SubMenu(chooseTamo1, chooseTamo2, back);

        MenuBox menuBox = new MenuBox(mainSubMenu);

        //mainGame
        Game game = new Game();
        game.setPane(root);
        startGame.setOnMouseClicked(event -> {

            GameSave loadedSave = GameEvents.loadSave();
            if (loadedSave == null){
                menuBox.setSubMenu(newGameSubMenu);
            } else if (loadedSave.getPet().getIsAlive() == false
                    &&
                    !loadedSave.canCreateNewTamo(new Date())){
                        ModalWindow window = new ModalWindow();
                        Label infoLbl = new Label("Ваш питомец недавно погиб. Пока что создать нового невозможно.");
                        infoLbl.setTranslateX(20);
                        infoLbl.setFont(Font.font("Arial", 18));
                        infoLbl.setTextAlignment(TextAlignment.CENTER);
                        window.getPane().getChildren().add(infoLbl);
                        window.newWindow("INFO", "OK", 600, 300);
            } else if (loadedSave.getPet().getIsAlive() == false
                    &&
                    loadedSave.canCreateNewTamo(new Date())){
                        menuBox.setSubMenu(newGameSubMenu);
            } else {
                game.setPet(loadedSave.getPet());
                game.setGameInterface(new GameInterface(loadedSave.getPet()));
                root.getChildren().remove(menuBox);
                game.startGame(root);
            }
        });
        exitGame.setOnMouseClicked(event -> {
            System.exit(0);
        });

        chooseTamo1.setOnMouseClicked(event -> {
            game.setPet(tamo1);
            game.setGameInterface(new GameInterface(tamo1));
            root.getChildren().remove(menuBox);
            game.startGame(root);
            GameEvents.setReadedInfo(false);
        });
        chooseTamo2.setOnMouseClicked(event -> {
            game.setPet(tamo2);
            game.setGameInterface(new GameInterface(tamo2));
            root.getChildren().remove(menuBox);
            game.startGame(root);
            GameEvents.setReadedInfo(false);
        });
        back.setOnMouseClicked(event -> {
            menuBox.setSubMenu(mainSubMenu);
        });
        root.getChildren().add(menuBox);

        //music
        Sound.playMusic();

        Image imageMute = new Image(getClass().getResourceAsStream(MUTE_BUTTON));
        ImageView imgM = new ImageView(imageMute);
        imgM.setFitHeight(50);
        imgM.setFitWidth(50);
        imgM.setTranslateX(905);
        imgM.setTranslateY(485);

        Image imageUnmute = new Image(getClass().getResourceAsStream(UNMUTE_BUTTON));
        ImageView imgUnm = new ImageView(imageUnmute);
        imgUnm.setFitHeight(50);
        imgUnm.setFitWidth(50);
        imgUnm.setTranslateX(905);
        imgUnm.setTranslateY(485);

        Tooltip soundTT = new Tooltip("Вкл/выкл музыку");
        Tooltip.install(imgM, soundTT);
        Tooltip.install(imgUnm, soundTT);

        root.getChildren().add(imgM);

        imgM.setOnMouseClicked(event -> {
            root.getChildren().remove(imgM);
            root.getChildren().add(imgUnm);
            Sound.muteMusic();
        });
        imgUnm.setOnMouseClicked(event -> {
            root.getChildren().remove(imgUnm);
            root.getChildren().add(imgM);
            Sound.muteMusic();
        });


        //howToPlayButton
        Image howToPlayImage = new Image(getClass().getResourceAsStream(FAQ_BUTTON));
        ImageView htpImg = new ImageView(howToPlayImage);
        htpImg.setFitHeight(50);
        htpImg.setFitWidth(50);
        htpImg.setTranslateX(845);
        htpImg.setTranslateY(485);

        Tooltip htpTT = new Tooltip("Небольшая информация об игре");
        Tooltip.install(htpImg, htpTT);
        root.getChildren().add(htpImg);

        htpImg.setOnMouseClicked(event -> {
            ModalWindow window = new ModalWindow();
            Text text = new Text();
            text.setText("В игре «Тамагочи» Вам предстоит растить своего питомца и следить\n"
                        + "за его показателями. Взаимодействие с питомцем реализовано с\n"
                        + "помощью функций кормления, игры и отдыха. Показатели каждого\n"
                        + "питомца уникальны - кто-то больше любит одну еду, а кто-то - \n"
                        + "другую,один быстрее устает, а другой быстрее становится голодным\n"
                        + "и так далее.\nВ случае смерти питомца, Вы какое-то время не сможете "
                        + "создать \nнового (по умолчанию - это 30 минут)");
            text.setFont(Font.font("Arial", 16));
            text.setTextAlignment(TextAlignment.LEFT);
            text.setTranslateY(15);
            text.setTranslateX(30);

            window.getPane().getChildren().addAll(text);
            window.newWindow("How to Play", "НАЗАД", 600, 300);

        });



        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            if (game.getPet() != null){
                GameEvents.savePet(game.getPet());
            }
            System.exit(0);
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
