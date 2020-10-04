package sample.interfaceview;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalWindow {

    private Pane pane;
    private Stage stage;

    public ModalWindow(){
        pane = new Pane();
        stage = new Stage();
    }

    public void newWindow(String title, String btnName, int xSize, int ySize){
        stage.initModality(Modality.APPLICATION_MODAL);

        Button closeBtn = new Button(btnName);
        closeBtn.setPrefSize(100, 40);
        closeBtn.setTranslateX((xSize - 100) / 2);
        closeBtn.setTranslateY(200);
        closeBtn.setOnAction(event -> {
            stage.close();
        });
        pane.getChildren().add(closeBtn);

        Scene scene = new Scene(pane, xSize, ySize);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();
    }
    public void closeWindow(){
        stage.close();
    }

    public Pane getPane() {
        return pane;
    }

    public Stage getStage() {
        return stage;
    }
}
