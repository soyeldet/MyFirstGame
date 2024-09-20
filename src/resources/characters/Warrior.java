package resources.characters;

import javax.swing.*;
import java.util.ArrayList;

public class Warrior extends Character {

    public Warrior(String name) {
        super(name);
        this.lifes = 5;
        this.speed = 3;
        down = new ImageIcon("src/resources/images/warrior/warrior_down.gif");
        left = new ImageIcon("src/resources/images/warrior/warrior_left.gif");
        right = new ImageIcon("src/resources/images/warrior/warrior_right.gif");
        up = new ImageIcon("src/resources/images/warrior/warrior_up.gif");
    }
}
