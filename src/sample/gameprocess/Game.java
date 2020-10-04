package sample.gameprocess;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.Main;
import sample.interfaceview.GameInterface;
import sample.interfaceview.ModalWindow;
import sample.models.CreatedGameObjects;
import sample.models.Pet;

import static sample.gameprocess.GameSettings.TIME_AGE_UPDATE;
import static sample.gameprocess.GameSettings.TIME_UPDATE_INDEX;

public class Game {
    private Pet pet;
    private Pane pane;
    private GameInterface gameInterface;

    private final Timeline timelineAllUpdate = new Timeline(
            new KeyFrame(
                    Duration.millis( TIME_UPDATE_INDEX ),
                    event -> {
                        GameEvents.updatePetStats(pet);
                        gameInterface.drawTamo(pane);
                        gameInterface.updateStatBar();
                        if (!pet.getIsAlive()){
                            stopGame();
                        }
                    }
            )
    );

    private final Timeline timelineAgeUpdate = new Timeline(
            new KeyFrame(
                    Duration.millis( TIME_AGE_UPDATE ),
                    event -> {
                        GameEvents.growUpPet(pet);
                        gameInterface.drawTamo(pane);
                        gameInterface.updateStatBar();
                        if (!pet.getIsAlive()){
                            stopGame();
                        }
                    }
            )
    );

    public void startGame(Pane pane){
        gameInterface.createStatBar(pane);
        gameInterface.createButtons(pane);
        gameInterface.drawTamo(pane);

        Image img1 = new Image(Main.class.getResourceAsStream(CreatedGameObjects.meetFood.getSkin()));
        ImageView meetFood = new ImageView(img1);
        meetFood.setFitWidth(100);
        meetFood.setFitHeight(100);
        meetFood.setTranslateX(75);
        meetFood.setTranslateY(50);

        Image img2 = new Image(Main.class.getResourceAsStream(CreatedGameObjects.watermelonFood.getSkin()));
        ImageView wtrmlFood = new ImageView(img2);
        wtrmlFood.setFitWidth(100);
        wtrmlFood.setFitHeight(100);
        wtrmlFood.setTranslateX(250);
        wtrmlFood.setTranslateY(50);

        Image img3 = new Image(Main.class.getResourceAsStream(CreatedGameObjects.pumpkinFood.getSkin()));
        ImageView pmkFood = new ImageView(img3);
        pmkFood.setFitWidth(100);
        pmkFood.setFitHeight(100);
        pmkFood.setTranslateX(425);
        pmkFood.setTranslateY(50);


        gameInterface.getEatBtn().setOnAction(event -> {
            if (pet.getAge() != -1 && pet.getIsAlive()) {

                ModalWindow window = new ModalWindow();
                window.getPane().getChildren().addAll(meetFood, wtrmlFood, pmkFood);
                window.newWindow("Выбор еды", "ОТМЕНА", 600, 300);

                meetFood.setOnMouseClicked(event1 -> {
                    GameEvents.feedPet(pet, CreatedGameObjects.meetFood);
                    if (!pet.getIsAlive()){
                        stopGame();
                    }
                    gameInterface.drawTamo(pane);
                    gameInterface.updateStatBar();
                    window.closeWindow();
                });
                wtrmlFood.setOnMouseClicked(event1 -> {
                    GameEvents.feedPet(pet, CreatedGameObjects.watermelonFood);
                    if (!pet.getIsAlive()){
                        stopGame();
                    }
                    gameInterface.drawTamo(pane);
                    gameInterface.updateStatBar();
                    window.closeWindow();
                });
                pmkFood.setOnMouseClicked(event1 -> {
                    GameEvents.feedPet(pet, CreatedGameObjects.pumpkinFood);
                    if (!pet.getIsAlive()){
                        stopGame();
                    }
                    gameInterface.drawTamo(pane);
                    gameInterface.updateStatBar();
                    window.closeWindow();
                });

                meetFood.setOnMouseEntered(event2 -> {
                    ScaleTransition st = new ScaleTransition(Duration.millis(500), meetFood);
                    st.setToX(1.5);
                    st.setToY(1.5);
                    st.play();
                });
                meetFood.setOnMouseExited(event2 -> {
                    ScaleTransition st = new ScaleTransition(Duration.millis(500), meetFood);
                    st.setFromX(1.5);
                    st.setFromY(1.5);
                    st.setToX(1);
                    st.setToY(1);
                    st.play();
                });

                wtrmlFood.setOnMouseEntered(event2 -> {
                    ScaleTransition st = new ScaleTransition(Duration.millis(500), wtrmlFood);
                    st.setToX(1.5);
                    st.setToY(1.5);
                    st.play();
                });
                wtrmlFood.setOnMouseExited(event2 -> {
                    ScaleTransition st = new ScaleTransition(Duration.millis(500), wtrmlFood);
                    st.setFromX(1.5);
                    st.setFromY(1.5);
                    st.setToX(1);
                    st.setToY(1);
                    st.play();
                });

                pmkFood.setOnMouseEntered(event2 -> {
                    ScaleTransition st = new ScaleTransition(Duration.millis(500), pmkFood);
                    st.setToX(1.5);
                    st.setToY(1.5);
                    st.play();
                });
                pmkFood.setOnMouseExited(event2 -> {
                    ScaleTransition st = new ScaleTransition(Duration.millis(500), pmkFood);
                    st.setFromX(1.5);
                    st.setFromY(1.5);
                    st.setToX(1);
                    st.setToY(1);
                    st.play();
                });
            }
        });
        gameInterface.getPlayBtn().setOnAction(event -> {
            if (pet.getAge() != -1 && pet.getIsAlive()) {
                GameEvents.playWithPet(pet);
                if (!pet.getIsAlive()){
                    stopGame();
                }
                gameInterface.drawTamo(pane);
                gameInterface.updateStatBar();
            }
        });
        gameInterface.getSleepBtn().setOnAction(event -> {
            if (pet.getAge() != -1 && pet.getIsAlive()) {
                GameEvents.sleepPet(pet);
                if (!pet.getIsAlive()){
                    stopGame();
                }
                gameInterface.drawTamo(pane);
                gameInterface.updateStatBar();
            }
        });



        timelineAllUpdate.setCycleCount( Animation.INDEFINITE );
        timelineAgeUpdate.setCycleCount( Animation.INDEFINITE );
        timelineAllUpdate.play();
        timelineAgeUpdate.play();
    }

    public void stopGame(){
        timelineAllUpdate.stop();
        timelineAgeUpdate.stop();
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Timeline getTimelineAllUpdate() {
        return timelineAllUpdate;
    }

    public Timeline getTimelineAgeUpdate() {
        return timelineAgeUpdate;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public GameInterface getGameInterface() {
        return gameInterface;
    }

    public void setGameInterface(GameInterface gameInterface) {
        this.gameInterface = gameInterface;
    }
}
