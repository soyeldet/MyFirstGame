package resources.characters;

import resources.objects.Items;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Character {

    protected String name;
    protected int lifes;
    protected int gold;
    protected int speed;
    protected String direction;
    protected ArrayList<Items> inventory;
    protected ImageIcon down;
    protected ImageIcon left;
    protected ImageIcon right;
    protected ImageIcon up;

    public Character(String name) {
        this.name = name;
        this.gold = 0;
        this.inventory = new ArrayList<>();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSpeed() {
        return speed;
    }

    public ArrayList<Items> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Items> inventory) {
        this.inventory = inventory;
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

}
