package sample;

import javafx.scene.image.Image;

import java.io.File;

public class LogoCreator implements Logo {

    private Image image;

    LogoCreator(){
        File file = new File("src/img/logo.png");
        this.image=new Image(file.toURI().toString());
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void change(Image image) {
        this.image=image;
    }
}
