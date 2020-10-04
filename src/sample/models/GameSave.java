package sample.models;

import java.io.Serializable;
import java.util.Date;

import static sample.gameprocess.GameSettings.TIME_UNCREATE_NEW_TAMO;

public class GameSave implements Serializable {
    private Date saveDate;
    private Pet pet;

    public boolean canCreateNewTamo(Date date){
        if (date.getTime() > saveDate.getTime() + TIME_UNCREATE_NEW_TAMO){
            return true;
        }
        return false;
    }

    public GameSave(Pet pet) {
        this.pet = pet;
        this.saveDate = new Date();
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
