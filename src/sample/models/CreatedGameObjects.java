package sample.models;

import static sample.files.FilesConstNames.*;

public class CreatedGameObjects {

    public static Food meetFood = new Food("Meet", MEET_ICON);
    public static Food watermelonFood = new Food("Watermelon", WATERMELON_ICON);
    public static Food pumpkinFood = new Food("Pumpkin", PUMPKIN_ICON);

    public static Pet tamo1 = new Pet(
            "Tamo1",
            POK1_EGG,
            POK1_HAPPY,
            POK1_SAD,
            100,
            watermelonFood,
            1,
            2,
            1
    );
    public static Pet tamo2 = new Pet(
            "Tamo2",
            POK2_EGG,
            POK2_HAPPY,
            POK2_SAD,
            80,
            pumpkinFood,
            2,
            1,
            1
    );

}
