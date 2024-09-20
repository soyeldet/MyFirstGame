package resources.listeners;

import resources.characters.*;
import resources.characters.Character;
import resources.objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Enviorment extends MouseAdapter {
    private String playerName;
    private Character character;
    private String ch_class;
    private JPanel main;
    private JLabel labelWalls;
    private JLabel labelFloor;
    private JLabel labelGame = new JLabel();
    private JLabel labelCharacter = new JLabel();
    private Timer timer;
    private JLabel labelMitra = new JLabel();
    private JLabel labelPotion = new JLabel();
    private JLabel labelSword = new JLabel();
    private ArrayList<JLabel> enemiesLabel = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Items> items = new ArrayList<>();
    private ArrayList<JLabel> itemsLabel = new ArrayList<>();
    private JLabel labelGold = new JLabel();
    private JLabel labelHP = new JLabel();
    private JLabel door;

    public Enviorment(String playerName, String ch_class, JPanel main) {
        this.playerName = playerName;
        this.ch_class = ch_class;
        this.main = main;

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveEnemies();
            }
        });
        timer.start();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        if (this.ch_class.equals("Warrior")){
            this.character = new Warrior(playerName);
        } else if (this.ch_class.equals("Mage")) {
            this.character = new Mage(playerName);
        } else {
            this.character = new Priest(playerName);
        }

        main.removeAll();
        main.revalidate();
        main.repaint();

        setInterface(main);
        setMap(main);

        showCharacter(main);

        startGame(main);



    }

    private void startGame(JPanel main) {

        ImageIcon iamgeIcon = character.getRight();
        Icon icon = new ImageIcon(
                iamgeIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)
        );
        labelCharacter.setIcon(icon);
        labelGame.setFocusable(true);
        labelGame.addKeyListener(new CharacterMovement(labelGame, labelCharacter, character, itemsLabel, items, labelGold, labelMitra, labelPotion, labelSword, door));
        labelGame.requestFocusInWindow();

    }

    private void setMap(JPanel main) {

        labelWalls = new JLabel();
        labelWalls.setLocation(0, 0);
        labelWalls.setSize(main.getWidth(), main.getHeight());

        labelFloor = new JLabel();
        labelFloor.setLocation(150, 200);
        labelFloor.setSize(1600, 1200);

        int maxX = (int) (main.getWidth()/32);
        int maxY = (int) (main.getHeight()/32)+2;

        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                JLabel wallBlock = new JLabel();
                wallBlock.setSize(32, 32);
                ImageIcon icon = new ImageIcon("src/resources/images/dungeon/wall.png");
                Icon iconWall = new ImageIcon(icon.getImage().getScaledInstance(wallBlock.getWidth(), wallBlock.getHeight(), Image.SCALE_SMOOTH));
                wallBlock.setIcon(iconWall);
                wallBlock.setLocation((maxX*j), (maxY*i));
                labelWalls.add(wallBlock);
            }
            for (int k = 0; k < maxX; k++) {
                JLabel wallBlock = new JLabel();
                wallBlock.setSize(32, 32);
                ImageIcon icon = new ImageIcon("src/resources/images/dungeon/wall.png");
                Icon iconWall = new ImageIcon(icon.getImage().getScaledInstance(wallBlock.getWidth(), wallBlock.getHeight(), Image.SCALE_SMOOTH));
                wallBlock.setIcon(iconWall);
                wallBlock.setLocation((maxX*k+32), (maxY*i));
                labelWalls.add(wallBlock);
            }

        }

        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 50; j++) {
                JLabel floorBlock = new JLabel();
                floorBlock.setSize(32, 32);
                ImageIcon icon = new ImageIcon("src/resources/images/dungeon/floor.png");
                Icon iconFloor = new ImageIcon(icon.getImage().getScaledInstance(floorBlock.getWidth(), floorBlock.getHeight(), Image.SCALE_SMOOTH));
                floorBlock.setIcon(iconFloor);
                floorBlock.setLocation(j * 32, i * 32);
                labelFloor.add(floorBlock);
            }
        }

        door = new JLabel();
        door.setBounds(1710, 500, 128, 64);
        door.setOpaque(false);

        ImageIcon imageIconDoor = new ImageIcon("src/resources/images/dungeon/closed.png");
        Icon iconDoor = new ImageIcon(imageIconDoor.getImage().getScaledInstance(128, 64, Image.SCALE_DEFAULT));

        door.setIcon(iconDoor);

        main.add(door);
        main.add(labelFloor);
        main.add(labelWalls);

        createEnemies(main, labelFloor);

    }

    private void setInterface(JPanel main) {
        JLabel labelInterface = new JLabel();
        labelInterface.setSize(main.getWidth(), 100);
        labelInterface.setLocation(0, 0);
        labelInterface.setOpaque(true);
        labelInterface.setBackground(Color.BLACK);

        labelHP = new JLabel("Vidas restantes: " + character.getLifes());
        labelHP.setSize(150, 50);
        labelHP.setLocation(50, 25);
        labelHP.setOpaque(true);
        labelHP.setBackground(Color.WHITE);

        labelGold = new JLabel("Oro: " + character.getGold());
        labelGold.setSize(150, 50);
        labelGold.setLocation(100+labelHP.getWidth(), 25);
        labelGold.setOpaque(true);
        labelGold.setBackground(Color.WHITE);


        labelMitra = new JLabel("Mitra: 0");
        labelMitra.setSize(150, 50);
        labelMitra.setLocation(150+labelGold.getWidth()+labelHP.getWidth(), 25);
        labelMitra.setOpaque(true);
        labelMitra.setBackground(Color.WHITE);

        labelPotion = new JLabel("Pocion: 0");
        labelPotion.setSize(150, 50);
        labelPotion.setLocation(300+labelMitra.getWidth()+labelHP.getWidth()+ labelGold.getHeight(), 25);
        labelPotion.setOpaque(true);
        labelPotion.setBackground(Color.WHITE);

        labelSword = new JLabel("Espada: 0");
        labelSword.setSize(150, 50);
        labelSword.setLocation(350+labelMitra.getWidth()+labelHP.getWidth()+ labelGold.getHeight()+ labelPotion.getWidth(), 25);
        labelSword.setOpaque(true);
        labelSword.setBackground(Color.WHITE);

        labelInterface.add(labelHP);
        labelInterface.add(labelGold);
        labelInterface.add(labelMitra);
        labelInterface.add(labelPotion);
        labelInterface.add(labelSword);
        main.add(labelInterface);

    }

    private void createEnemies(JPanel main, JLabel labelFloor) {

        labelGame = new JLabel();
        labelGame.setSize(labelFloor.getWidth(), labelFloor.getHeight());
        labelGame.setLocation(0, 100);

        for (int i = 0; i < 4; i++) {

            JLabel labelEnemy = new JLabel();
            Enemy enemy = new Enemy();
            labelEnemy.setSize(64, 64);
            labelEnemy.setLocation((int) (300 + Math.random() * 1400), (int) (200 + Math.random() * 400));

            int pos = ((int) (Math.random() * 5));

            if (pos == 1){
                ImageIcon iconEnemy = enemy.getDown();
                labelEnemy.setIcon(iconEnemy);
                enemy.setDirection("down");
            } else if (pos == 2) {
                ImageIcon iconEnemy = enemy.getUp();
                labelEnemy.setIcon(iconEnemy);
                enemy.setDirection("up");
            } else if (pos == 3) {
                ImageIcon iconEnemy = enemy.getRight();
                labelEnemy.setIcon(iconEnemy);
                enemy.setDirection("right");
            } else {
                ImageIcon iconEnemy = enemy.getLeft();
                labelEnemy.setIcon(iconEnemy);
                enemy.setDirection("left");
            }

            enemies.add(enemy);
            enemiesLabel.add(labelEnemy);
            labelEnemy.setVisible(true);
            labelGame.add(labelEnemy);
            main.add(labelGame);
            main.setComponentZOrder(labelEnemy, 0);
        }


        main.repaint();
        setObjects(main);

    }

    private void moveEnemies() {

        for (int i = 0; i < enemiesLabel.size(); i++) {
            if (enemiesLabel.get(i).getIcon() == enemies.get(i).getDown()) {
                if (enemiesLabel.get(i).getY() >= 32*26){
                    enemiesLabel.get(i).setIcon(enemies.get(i).getUp());
                } else {
                    enemiesLabel.get(i).setLocation(enemiesLabel.get(i).getX(), enemiesLabel.get(i).getY() + 10);
                }
            } else if (enemiesLabel.get(i).getIcon() == enemies.get(i).getUp()) {
                if (enemiesLabel.get(i).getY() <= 32*6){
                    enemiesLabel.get(i).setIcon(enemies.get(i).getDown());
                } else {
                    enemiesLabel.get(i).setLocation(enemiesLabel.get(i).getX(), enemiesLabel.get(i).getY() - 10);
                }
            } else if (enemiesLabel.get(i).getIcon() == enemies.get(i).getRight()) {
                if (enemiesLabel.get(i).getX() >= 50*34){
                    enemiesLabel.get(i).setIcon(enemies.get(i).getLeft());
                } else {
                    enemiesLabel.get(i).setLocation(enemiesLabel.get(i).getX() + 10, enemiesLabel.get(i).getY());
                }
            } else {
                if (enemiesLabel.get(i).getX() <= 138){
                    enemiesLabel.get(i).setIcon(enemies.get(i).getRight());
                } else {
                    enemiesLabel.get(i).setLocation(enemiesLabel.get(i).getX() - 10, enemiesLabel.get(i).getY());
                }
            }

            checkCharacter();

        }
        main.repaint();
    }

    private void checkCharacter() {

        for (int i = 0; i < enemiesLabel.size(); i++) {
            if (enemiesLabel.get(i).getBounds().intersects(labelCharacter.getBounds())) {
                if (this.character.getLifes() <= 1) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Tus vidas han llegado a 0", "Has perdido", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION || respuesta == JOptionPane.NO_OPTION) {
                        Exit exit0 = new Exit(this.playerName, character.getGold(), false, 0);
                        exit0.finishGame();
                    }
                } else {
                    if (this.ch_class.equals("Warrior") && this.labelSword.getText().equals("Espada: 1")) {
                        enemiesLabel.get(i).setIcon(null);
                        enemiesLabel.get(i).removeAll();
                        this.labelSword.setText("Espada: 0");
                    } else if (this.ch_class.equals("Priest") && this.labelMitra.getText().equals("Mitra: 1")) {
                        labelCharacter.setLocation(200, 400);
                        this.labelMitra.setText("Mitra: 0");
                    } else if (this.ch_class.equals("Mage") && this.labelPotion.getText().equals("Pocion: 1")) {
                        labelCharacter.setLocation(200, 400);
                        this.character.setLifes(this.character.getLifes() + 1);
                        labelHP.setText("Vidas restantes: " + this.character.getLifes());
                        labelPotion.setText("Pocion: 0");
                    } else {
                        labelCharacter.setLocation(200, 400);
                        character.setLifes(character.getLifes() - 1);
                        labelHP.setText("Vidas restantes: " + character.getLifes());
                    }
                }
            }
        }
    }


    private void setObjects(JPanel main) {
        for (int i = 0; i < 4; i++) {
            JLabel labelItem = new JLabel();
            labelItem.setSize(32, 32);
            labelItem.setLocation((int) (150 + Math.random() * 1400), (int) (210 + Math.random() * 400));

            Items item;
            if (i == 0) {
                item = new Potion();
                ImageIcon icon = (ImageIcon) item.getPicture();
                Icon iconSkibidi = new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                labelItem.setIcon(iconSkibidi);
                items.add(item);
                itemsLabel.add(labelItem);
            } else if (i == 1){
                item = new Gold();
                ImageIcon icon = (ImageIcon) item.getPicture();
                Icon iconSkibidi = new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                labelItem.setIcon(iconSkibidi);
                items.add(item);
                itemsLabel.add(labelItem);
            } else if (i == 2){
                item = new Mitra();
                ImageIcon icon = (ImageIcon) item.getPicture();
                Icon iconSkibidi = new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                labelItem.setIcon(iconSkibidi);
                items.add(item);
                itemsLabel.add(labelItem);
            } else {
                item = new Sword();
                ImageIcon icon = (ImageIcon) item.getPicture();
                Icon iconSkibidi = new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                labelItem.setIcon(iconSkibidi);
                items.add(item);
                itemsLabel.add(labelItem);
            }

            labelItem.setVisible(true);

            main.add(labelItem);
            main.setComponentZOrder(labelItem, 0);

        }
        showCharacter(main);

        main.repaint();
    }

    private void showCharacter(JPanel main) {


        labelCharacter = new JLabel();
        labelCharacter.setSize(64, 64);
        labelCharacter.setLocation(200, 400);
        labelCharacter.setVisible(true);
        main.add(labelCharacter);
        main.setComponentZOrder(labelCharacter, 0);
        main.repaint();


    }

}
