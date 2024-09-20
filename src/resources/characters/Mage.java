package resources.characters;

import javax.swing.*;

public class Mage extends Character {
    public Mage(String name) {
        super(name);
        this.lifes = 3;
        this.speed = 7;
        down = new ImageIcon("src/resources/images/mage/mage_down.gif");
        left = new ImageIcon("src/resources/images/mage/mage_left.gif");
        right = new ImageIcon("src/resources/images/mage/mage_right.gif");
        up = new ImageIcon("src/resources/images/mage/mage_up.gif");
    }
}
