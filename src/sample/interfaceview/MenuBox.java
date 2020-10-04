package sample.interfaceview;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MenuBox extends Pane {
    private static SubMenu subMenu;
    public MenuBox(SubMenu subMenu){
        this.subMenu = subMenu;
        this.setVisible(true);
        Rectangle rectangle = new Rectangle(960, 560, Color.LIGHTBLUE);
        rectangle.setOpacity(0.7);
        this.getChildren().addAll(rectangle, subMenu);
    }
    public void setSubMenu(SubMenu subMenu){
        this.getChildren().remove(MenuBox.subMenu);
        this.subMenu = subMenu;
        getChildren().add(subMenu);
    }
}
