package resources.characters;

import javax.swing.*;

public class Priest extends Character {
    public Priest(String name) {
        super(name);
        this.lifes = 4;
        this.speed = 5;
        down = new ImageIcon("src/resources/images/priest/priest_down.gif");
        left = new ImageIcon("src/resources/images/priest/priest_left.gif");
        right = new ImageIcon("src/resources/images/priest/priest_right.gif");
        up = new ImageIcon("src/resources/images/priest/priest_up.gif");
    }
}
