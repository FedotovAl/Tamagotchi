package sample.models;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private String skin;

    public Food(String name, String skin) {
        this.name = name;
        this.skin = skin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
