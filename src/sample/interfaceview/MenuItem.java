package sample.interfaceview;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuItem extends StackPane {
    public MenuItem(String name){
        Rectangle rectangle = new Rectangle(300, 50, Color.BLACK);
        rectangle.setOpacity(0.5);

        Text text = new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(rectangle, text);

        FillTransition fillTransition = new FillTransition(Duration.seconds(0.5), rectangle);
        this.setOnMouseEntered(event -> {
            fillTransition.setFromValue(Color.BLACK);
            fillTransition.setToValue(Color.DARKGOLDENROD);
            fillTransition.setCycleCount(Animation.INDEFINITE);
            fillTransition.setAutoReverse(true);
            fillTransition.play();
        });
        this.setOnMouseExited(event -> {
            fillTransition.stop();
            rectangle.setFill(Color.BLACK);
        });


    }

}
