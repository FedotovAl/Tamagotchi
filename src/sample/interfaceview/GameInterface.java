package sample.interfaceview;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.Main;
import sample.models.Pet;

import static sample.files.FilesConstNames.TOMBSTONE;

public class GameInterface {
    private Pet pet;
    private Label hungLbl;
    private Label hapLbl;
    private Label chLbl;
    private Label ageLbl;

    private Button eatBtn;
    private Button playBtn;
    private Button sleepBtn;

    private Image petSkin0;
    private ImageView eggImg;
    private Image petSkin1;
    private ImageView happyImg;
    private Image petSkin2;
    private ImageView sadImg;


    public GameInterface(Pet pet){
        this.pet = pet;
        hungLbl = new Label();
        hapLbl = new Label();
        chLbl = new Label();
        ageLbl = new Label();

        eatBtn = new Button();
        playBtn = new Button();
        sleepBtn = new Button();

        petSkin0 = new Image(Main.class.getResourceAsStream(pet.getSkinEgg()));
        eggImg = new ImageView(petSkin0);
        {
            eggImg.setFitHeight(300);
            eggImg.setFitWidth(300);
            eggImg.setTranslateX(320);
            eggImg.setTranslateY(110);
        }
        petSkin1 = new Image(Main.class.getResourceAsStream(pet.getSkinHappy()));
        happyImg = new ImageView(petSkin1);
        {
            happyImg.setFitHeight(300);
            happyImg.setFitWidth(300);
            happyImg.setTranslateX(320);
            happyImg.setTranslateY(110);
        }
        petSkin2 = new Image(Main.class.getResourceAsStream(pet.getSkinSad()));
        sadImg = new ImageView(petSkin2);
        {
            sadImg.setFitHeight(300);
            sadImg.setFitWidth(300);
            sadImg.setTranslateX(320);
            sadImg.setTranslateY(110);
        }
    }

    public void createStatBar(Pane pane) {
        Rectangle rectangle = new Rectangle(135, 80, Color.LIGHTGRAY);
        rectangle.setTranslateX(825);
        hungLbl.setTranslateX(830);
        hungLbl.setTranslateY(0);
        hungLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        hungLbl.setText("Сытость: " + pet.getHunger() + " / " + "100");

        hapLbl.setTranslateX(830);
        hapLbl.setTranslateY(20);
        hapLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        hapLbl.setText("Счастье: " + pet.getHappiness() + " / " + "100");

        chLbl.setTranslateX(830);
        chLbl.setTranslateY(40);
        chLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        chLbl.setText("Бодрость: " + pet.getCheerfulness() + " / " + "100");

        ageLbl.setTranslateX(830);
        ageLbl.setTranslateY(60);
        ageLbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ageLbl.setText("Возраст: " + pet.getAge() + " / " + pet.getMaxAge());

        pane.getChildren().addAll(rectangle, hungLbl, hapLbl, chLbl, ageLbl);
    }

    public void updateStatBar() {
        hungLbl.setText("Сытость: " + pet.getHunger() + " / " + "100");
        hapLbl.setText("Счастье: " + pet.getHappiness() + " / " + "100");
        chLbl.setText("Бодрость: " + pet.getCheerfulness() + " / " + "100");
        ageLbl.setText("Возраст: " + pet.getAge() + " / " + pet.getMaxAge());
    }

    public void createButtons(Pane pane) {

        eatBtn.setText("ПОКОРМИТЬ");
        eatBtn.setPrefSize(150, 40);
        eatBtn.setStyle("-fx-base: lightgrey");
        eatBtn.setTranslateX(10);
        eatBtn.setTranslateY(20);

        playBtn.setText("ПОИГРАТЬ");
        playBtn.setPrefSize(150, 40);
        playBtn.setStyle("-fx-base: lightgrey");
        playBtn.setTranslateX(10);
        playBtn.setTranslateY(70);

        sleepBtn.setText("ПОСПАТЬ");
        sleepBtn.setPrefSize(150, 40);
        sleepBtn.setStyle("-fx-base: lightgrey");
        sleepBtn.setTranslateX(10);
        sleepBtn.setTranslateY(120);

        pane.getChildren().addAll(eatBtn, playBtn, sleepBtn);
    }
    public void drawTamo(Pane pane){
        if (pet.getAge() == -1){
            pane.getChildren().remove(eggImg);
            pane.getChildren().remove(happyImg);
            pane.getChildren().remove(sadImg);
            pane.getChildren().add(eggImg);
        } else if (pet.getHunger() >=30
                && pet.getHappiness() >= 30
                && pet.getCheerfulness() >= 30){
            pane.getChildren().remove(eggImg);
            pane.getChildren().remove(happyImg);
            pane.getChildren().remove(sadImg);
            pane.getChildren().add(happyImg);
        } else {
            pane.getChildren().remove(eggImg);
            pane.getChildren().remove(happyImg);
            pane.getChildren().remove(sadImg);
            pane.getChildren().add(sadImg);
        }
        if (!pet.getIsAlive()){
            pane.getChildren().remove(eggImg);
            pane.getChildren().remove(happyImg);
            pane.getChildren().remove(sadImg);
            Image imgTS = new Image(Main.class.getResourceAsStream(TOMBSTONE));
            ImageView tombImg = new ImageView(imgTS);
            tombImg.setFitHeight(300);
            tombImg.setFitWidth(300);
            tombImg.setTranslateX(320);
            tombImg.setTranslateY(110);
            pane.getChildren().add(tombImg);

        }

    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Label getHungLbl() {
        return hungLbl;
    }

    public void setHungLbl(Label hungLbl) {
        this.hungLbl = hungLbl;
    }

    public Label getHapLbl() {
        return hapLbl;
    }

    public void setHapLbl(Label hapLbl) {
        this.hapLbl = hapLbl;
    }

    public Label getChLbl() {
        return chLbl;
    }

    public void setChLbl(Label chLbl) {
        this.chLbl = chLbl;
    }

    public Label getAgeLbl() {
        return ageLbl;
    }

    public void setAgeLbl(Label ageLbl) {
        this.ageLbl = ageLbl;
    }

    public Button getEatBtn() {
        return eatBtn;
    }

    public void setEatBtn(Button eatBtn) {
        this.eatBtn = eatBtn;
    }

    public Button getPlayBtn() {
        return playBtn;
    }

    public void setPlayBtn(Button playBtn) {
        this.playBtn = playBtn;
    }

    public Button getSleepBtn() {
        return sleepBtn;
    }

    public void setSleepBtn(Button sleepBtn) {
        this.sleepBtn = sleepBtn;
    }

    public Image getPetSkin0() {
        return petSkin0;
    }

    public void setPetSkin0(Image petSkin0) {
        this.petSkin0 = petSkin0;
    }

    public ImageView getEggImg() {
        return eggImg;
    }

    public void setEggImg(ImageView eggImg) {
        this.eggImg = eggImg;
    }

    public Image getPetSkin1() {
        return petSkin1;
    }

    public void setPetSkin1(Image petSkin1) {
        this.petSkin1 = petSkin1;
    }

    public ImageView getHappyImg() {
        return happyImg;
    }

    public void setHappyImg(ImageView happyImg) {
        this.happyImg = happyImg;
    }

    public Image getPetSkin2() {
        return petSkin2;
    }

    public void setPetSkin2(Image petSkin2) {
        this.petSkin2 = petSkin2;
    }

    public ImageView getSadImg() {
        return sadImg;
    }

    public void setSadImg(ImageView sadImg) {
        this.sadImg = sadImg;
    }
}
