package sample.gameprocess;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sample.interfaceview.ModalWindow;
import sample.models.Food;
import sample.models.GameSave;
import sample.models.Pet;

import java.io.*;
import java.util.Date;

import static sample.gameprocess.GameSettings.TIME_AGE_UPDATE;
import static sample.gameprocess.GameSettings.TIME_UPDATE_INDEX;

public class GameEvents {
    static boolean readedInfo = false;
    public static void playWithPet(Pet pet){
        pet.letsPlay();
        if (pet.getHunger() == 0 || pet.getCheerfulness() == 0){
            deathOfPet(pet);
        }
    }
    public static void feedPet(Pet pet,Food food){
        pet.letsEat(food);
    }
    public static void sleepPet(Pet pet){
        pet.letsSleep();
        if (pet.getHunger() == 0){
            deathOfPet(pet);
        }
    }
    public static void windowAfterDeath(Pet pet){
        ModalWindow window = new ModalWindow();
        Label label = new Label("Ваша неосмотрительность привела к гибели милого существа.\n" +
                "Ну, или оно погибло от старости, и Вы большой молодец,\n что разделили с ним его" +
                " крохотную жизнь.");
        label.setFont(Font.font("Arial", 20));
        label.setTextAlignment(TextAlignment.CENTER);
        window.getPane().getChildren().add(label);
        window.newWindow("Pet's death", "OK", 600, 300);
    }
    public static void deathOfPet(Pet pet){
        pet.setIsAlive(false);
        savePet(pet);
        windowAfterDeath(pet);
    }
    public static void growUpPet(Pet pet){
        pet.setAge(pet.getAge() + 1);
        if (pet.getAge() > pet.getMaxAge()){
            deathOfPet(pet);
        }
    }
    public static void updatePetStats(Pet pet){
        pet.setHunger(pet.getHunger() - 1 * pet.getHungerIndex());
        if (pet.getHunger() < 0){
            pet.setHunger(0);
        }
        pet.setHappiness(pet.getHappiness() - 1 * pet.getHappinessIndex());
        if (pet.getHappiness() < 0){
            pet.setHappiness(0);
        }
        pet.setCheerfulness(pet.getCheerfulness() - 1 * pet.getCheerfulnessIndex());
        if (pet.getCheerfulness() < 0){
            pet.setCheerfulness(0);
        }
        if (pet.getCheerfulness() == 0 || pet.getHunger() == 0 || pet.getHappiness() == 0){
            deathOfPet(pet);
        }
    }

    public static GameSave loadSave(){
        String filename = "Pet.save";
        GameSave gameSave = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try
        {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            gameSave = (GameSave) in.readObject();
            in.close();
            if (gameSave != null){
                if (!gameSave.getPet().getIsAlive() && readedInfo == false){
                    windowAfterDeath(gameSave.getPet());
                    readedInfo = true;
                } else {
                    long countOfStatUpdates = (new Date().getTime() - gameSave.getSaveDate().getTime()) / TIME_UPDATE_INDEX;
                    for (long i = 0; i <= countOfStatUpdates; i++) {
                        if (!gameSave.getPet().getIsAlive()){
                            break;
                        }
                        updatePetStats(gameSave.getPet());
                    }
                    long countOfAgeUpdates = (new Date().getTime() - gameSave.getSaveDate().getTime()) / TIME_AGE_UPDATE;
                    for (long i = 0; i <= countOfAgeUpdates; i++){
                        if (!gameSave.getPet().getIsAlive()){
                            break;
                        }
                        growUpPet(gameSave.getPet());
                    }
                }

            }
            }
        catch(IOException ex)
        {
            ex.printStackTrace();
            gameSave = null;
        }
        catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
            gameSave = null;
        }
        return gameSave;
    }

    public static void savePet(Pet pet){
        GameSave gameSave = new GameSave(pet);
        String filename = "Pet.save";
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try
        {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(gameSave);
            out.close();
            }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }

    public static boolean isReadedInfo() {
        return readedInfo;
    }

    public static void setReadedInfo(boolean readedInfo) {
        GameEvents.readedInfo = readedInfo;
    }
}
