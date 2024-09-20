package resources.characters;

import javax.swing.*;

public class Enemy {

    private int speed;
    private ImageIcon down;
    private ImageIcon left;
    private ImageIcon right;
    private ImageIcon up;
    private String direction;

    public Enemy() {
        this.speed = 5;
        down = new ImageIcon("src/resources/images/skeleton/skeleton_down.gif");
        left = new ImageIcon("src/resources/images/skeleton/skeleton_left.gif");
        right = new ImageIcon("src/resources/images/skeleton/skeleton_right.gif");
        up = new ImageIcon("src/resources/images/skeleton/skeleton_up.gif");

    }

    public int getSpeed() {
        return speed;
    }

    public ImageIcon getDown() {
        return down;
    }

    public ImageIcon getLeft() {
        return left;
    }

    public ImageIcon getRight() {
        return right;
    }

    public ImageIcon getUp() {
        return up;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
