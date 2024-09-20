package resources.listeners;

import resources.characters.Character;
import resources.objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CharacterMovement extends KeyAdapter {

    protected JLabel labelCharacter;
    protected JLabel labelGame;
    protected Character character;
    protected ImageIcon icon;
    protected ArrayList<Items> items;
    protected ArrayList<JLabel> itemsLabel;
    protected JLabel labelGold;
    protected JLabel labelMitra;
    protected JLabel labelPotion;
    protected JLabel labelSword;
    protected ImageIcon imageIcon;
    private JLabel labelDoor;

    public CharacterMovement(JLabel labelGame, JLabel labelCharacter, Character character, ArrayList<JLabel> itemsLabel, ArrayList<Items> items, JLabel labelGold, JLabel labelMitra, JLabel labelPotion, JLabel labelSword, JLabel labelDoor) {
        this.labelCharacter = labelCharacter;
        this.labelGame = labelGame;
        this.character = character;
        this.itemsLabel = itemsLabel;
        this.items = items;
        this.labelGold = labelGold;
        this.labelMitra = labelMitra;
        this.labelPotion = labelPotion;
        this.labelSword = labelSword;
        this.labelDoor = labelDoor;

    }

    @Override
    public void keyPressed(KeyEvent e) {

        super.keyPressed(e);
        int x = labelCharacter.getX();
        int y = labelCharacter.getY();

        switch (e.getKeyCode()) {

            case KeyEvent.VK_RIGHT -> {

                character.setDirection("right");

                if (labelCharacter.getX() >= (50*34) ){
                    labelCharacter.setLocation(x, y);

                    imageIcon = character.getRight();
                    icon = new ImageIcon(
                            imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)
                    );


                } else {
                    labelCharacter.setLocation(x + character.getSpeed(), y);

                    imageIcon = character.getRight();
                    icon = new ImageIcon(
                            imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)
                    );

                }
            }
            case KeyEvent.VK_LEFT -> {
                character.setDirection("left");
                if (labelCharacter.getX() <= (138) ){
                    labelCharacter.setLocation(x, y);
                    imageIcon = character.getLeft();
                    icon = new ImageIcon(
                            imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)
                    );

                } else {
                    labelCharacter.setLocation(x - character.getSpeed(), y);
                    imageIcon = character.getLeft();
                    icon = new ImageIcon(
                            imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)
                    );

                }
            }
            case KeyEvent.VK_DOWN -> {
                character.setDirection("down");
                if (labelCharacter.getY() >= (850) ){
                    labelCharacter.setLocation(x, y);
                    imageIcon = character.getDown();
                    icon = new ImageIcon(
                            imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)
                    );

                } else {
                    labelCharacter.setLocation(x, y + character.getSpeed());
                    imageIcon = character.getDown();
                    icon = new ImageIcon(
                            imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)
                    );

                }
            }
            case KeyEvent.VK_UP -> {
                character.setDirection("up");
                if (labelCharacter.getY() <= (32*6) ){
                    labelCharacter.setLocation(x, y);
                    imageIcon = character.getUp();
                    icon = new ImageIcon(
                            imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)
                    );

                } else {
                    labelCharacter.setLocation(x, y - character.getSpeed());
                    imageIcon = character.getUp();
                    icon = new ImageIcon(
                            imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)
                    );

                }
            }
        }
        labelCharacter.setIcon(icon);
        checkCoins();
        checkItems();

    }

    private void checkItems() {
        for (int i = 0; i < itemsLabel.size(); i++) {
            if (items.get(i) instanceof Mitra && itemsLabel.get(i).getBounds().intersects(labelCharacter.getBounds())) {
                Mitra mitra = new Mitra();
                this.character.getInventory().add(mitra);
                labelMitra.setText("Mitra: 1");
                itemsLabel.get(i).setIcon(null);
                itemsLabel.get(i).removeAll();
            }
        }

        for (int i = 0; i < itemsLabel.size(); i++) {
            if (items.get(i) instanceof Sword && itemsLabel.get(i).getBounds().intersects(labelCharacter.getBounds())) {
                Sword sword = new Sword();
                this.character.getInventory().add(sword);
                labelSword.setText("Espada: 1");
                itemsLabel.get(i).setIcon(null);
                itemsLabel.get(i).removeAll();
            }
        }

        for (int i = 0; i < itemsLabel.size(); i++) {
            if (items.get(i) instanceof Potion && itemsLabel.get(i).getBounds().intersects(labelCharacter.getBounds())) {
                Potion potion = new Potion();
                this.character.getInventory().add(potion);
                labelPotion.setText("Pocion: 1");
                itemsLabel.get(i).setIcon(null);
                itemsLabel.get(i).removeAll();
            }
        }

    }

    private void checkCoins() {

        if (this.character.getGold() >= 50) {
            ImageIcon imageIconDoor = new ImageIcon("src/resources/images/dungeon/opened.png");
            Icon iconDoor = new ImageIcon(imageIconDoor.getImage().getScaledInstance(128, 64, Image.SCALE_DEFAULT));
            labelDoor.setIcon(iconDoor);
            labelCharacter.setIcon(icon);

            if (labelDoor.getBounds().intersects(labelCharacter.getBounds())) {
                int respuesta = JOptionPane.showConfirmDialog(null, "Has conseguido " + this.character.getGold() + " monedas sin morir!", "Has ganado", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION || respuesta == JOptionPane.NO_OPTION) {
                    Exit exit0 = new Exit(this.character.getName(), character.getGold(), true, character.getLifes());
                    exit0.finishGame();
                }
            }
        }

            for (int i = 0; i < itemsLabel.size(); i++) {
                if (items.get(i) instanceof Gold && itemsLabel.get(i).getBounds().intersects(labelCharacter.getBounds())) {
                    itemsLabel.get(i).setLocation((int) (150 + Math.random() * 1400), (int) (210 + Math.random() * 400));
                    this.character.setGold(this.character.getGold() + 10);
                    labelGold.setText("Oro: " + character.getGold());
                    labelGold.repaint();
                }
            }
        }
    }

