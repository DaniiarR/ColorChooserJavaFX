package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    private Pane pane;

    @FXML
    private Slider redS;

    @FXML
    private Slider greenS;

    @FXML
    private Slider blueS;

    @FXML
    private Slider opacityS;

    @FXML
    private Label hexLabel;

    @FXML
    void initialize(){
        ChangeListener<Number> listener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String hex = "";
                double d = opacityS.getValue();
                hex += doubleToHex(blueS.getValue(), d);
                hex += doubleToHex(redS.getValue(), d);
                hex += doubleToHex(greenS.getValue(), d);
                pane.setStyle("-fx-background-color: #" + hex);
                hexLabel.setText("#"+ hex);
            }
        };
        redS.valueProperty().addListener(listener);
        greenS.valueProperty().addListener(listener);
        opacityS.valueProperty().addListener(listener);
        blueS.valueProperty().addListener(listener);
    }

    public String doubleToHex(double num, double opacity){
        if(num == 0.0){
            return "00";
        }
        double o = (opacity / 100);
        int i = (int) (num * 2.55 * o);
        String result = Integer.toHexString(i);
        if(result.length() == 1){
            return "0" + result;
        }
        return result;
    }
}