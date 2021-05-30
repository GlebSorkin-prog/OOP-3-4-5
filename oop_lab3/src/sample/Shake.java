package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.util.Duration;


public class Shake {
    private TranslateTransition tt;

    public Shake(TextField textField) {
        tt = new TranslateTransition(Duration.millis(70),textField);
        tt.setFromX(0f);
        tt.setByX(7f);
        tt.setCycleCount(4);
        tt.setAutoReverse(true);
    }

    public void playAnimation(){
        tt.playFromStart();
    }
}
