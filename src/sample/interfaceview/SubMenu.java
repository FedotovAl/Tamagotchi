package sample.interfaceview;

import javafx.scene.layout.VBox;

public class SubMenu extends VBox {
    public SubMenu(MenuItem...items){
        setSpacing(15);
        this.setTranslateX(330);
        this.setTranslateY(150);
        for (MenuItem item : items){
            this.getChildren().add(item);
        }
    }
}
