package civare.mastermind.resourceManagers.images;

import civare.mastermind.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.EnumSet;
import java.util.Objects;

public enum Image {

    VICTORY(Config.getButtonPath(), "victory"),
    DEFEAT(Config.getButtonPath(), "defeat"),
    PLAY_AGAIN(Config.getButtonPath(), "playAgain"),

    CLOSED_CELL(Config.getClosedTiles(), "closedCell"),
    FLAG(Config.getClosedTiles(), "flag"),
    NOT_SURE(Config.getClosedTiles(), "notSure"),

    RED(Config.getPegsPath(), "red"),
    BLUE(Config.getPegsPath(), "blue"),
    BLACK(Config.getPegsPath(), "black"),

    ZERO(Config.getOpenedTiles(), "0"),
    ONE(Config.getOpenedTiles(), "1"),
    TWO(Config.getOpenedTiles(), "2"),
    THREE(Config.getOpenedTiles(), "3"),
    FOUR(Config.getOpenedTiles(), "4"),
    FIVE(Config.getOpenedTiles(), "5"),
    SIX(Config.getOpenedTiles(), "6"),
    SEVEN(Config.getOpenedTiles(), "7"),
    EIGHT(Config.getOpenedTiles(), "8"),
    MINE(Config.getOpenedTiles(), "-1"),

    T_ZERO(Config.getTimeTiles(), "0"),
    T_ONE(Config.getTimeTiles(), "1"),
    T_TWO(Config.getTimeTiles(), "2"),
    T_THREE(Config.getTimeTiles(), "3"),
    T_FOUR(Config.getTimeTiles(), "4"),
    T_FIVE(Config.getTimeTiles(), "5"),
    T_SIX(Config.getTimeTiles(), "6"),
    T_SEVEN(Config.getTimeTiles(), "7"),
    T_EIGHT(Config.getTimeTiles(), "8"),
    T_NINE(Config.getTimeTiles(), "9"),

    STOP(Config.getStartStopImagesPath(), "pause"),
    START(Config.getStartStopImagesPath(), "play"),

    ;

    private final String path;
    private ImageIcon imageIcon;
    private ImageIcon defaultImageIcon;

    public URL getLocation(String location) {
        return Objects.requireNonNull(Main.class.getResource(location));
//        return location;
    }

    Image(String folder, String name) {
        this.path =
                Config.getCustomImagesPath() + Config.getBackslash() +
                        folder + Config.getBackslash() +
                        name + Config.getDOT() + Config.getImagesFormatName();

//        this is slower, do not know why
//        this.imageIcon = ImageManager.loadImage(this.path);

        try {

            imageIcon = new ImageIcon(getLocation("/errors.png"));
            defaultImageIcon = new ImageIcon(getLocation("/errors.png"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error in Image.java while loading images");

            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("alt text");
//        try {
//
//            button.setIcon(new ImageIcon(Main.class.getResource("/errors.png")));
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//            System.out.println(ex.getMessage());
//        }
        button.setIcon(BLUE.getImageIcon());

        button.addActionListener(e -> {
            System.out.println("pressed");
        });
        f.add(button);

        f.setBounds(300, 200, 400, 300);
        f.setVisible(true);
    }

    public String getPath() {
        return path;
    }

    public void flushToDefaultImage() {
        imageIcon = defaultImageIcon;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public String toString() {
        return
                this.name() +
                        " {" +
                "pathID='" +
                        path + '\'' +
                '}';
    }

}
