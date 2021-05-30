package sample;

import javafx.scene.image.Image;

public class LogoCreatorProxy implements Logo {

    private LogoCreator logo;
    private Image image;

    @Override
    public Image getImage() {
        if (logo == null) {
            logo = new LogoCreator();
        }
        return logo.getImage();
    }

    @Override
    public void change(Image image) {
        if (logo == null) {
            logo = new LogoCreator();
        }
        logo.change(image);
    }
}
