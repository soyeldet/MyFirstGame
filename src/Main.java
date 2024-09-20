import resources.characters.Mage;
import resources.characters.Priest;
import resources.characters.Warrior;
import resources.listeners.Enviorment;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JPanel panelMain;
    private JLabel gameName;
    private JLabel charactersTitle;
    private JLabel panelCharacters;
    private String playerName;
    private JPanel registrerZone;
    private JButton buttonStart;
    private JButton buttonWarrior;
    private JButton buttonMage;
    private JButton buttonPriest;
    private JTextField name;
    private JLabel introduceYourName;
    JPanel panelTitle = new JPanel();
    Font font = new Font("Times New Roman", Font.BOLD, 70);
    Font font2 = new Font("Arial", Font.BOLD, 14);

    public Main(JFrame frame) {
        panelMain = new JPanel();

        panelMain.setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        panelMain.setSize(screenSize.width, screenSize.height);
        panelMain.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        panelMain.setFocusable(true);

        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        nameSelector(centerX, centerY);
    }

    private void nameSelector(int centerX, int centerY) {

        JPanel selectName = new JPanel();
        selectName.setSize(panelMain.getSize());
        selectName.setLayout(null);
        selectName.setLocation(centerX - selectName.getWidth() / 2, centerY - selectName.getHeight() / 2);

        panelTitle = new JPanel();
        panelTitle.setOpaque(false);
        panelTitle.setSize(panelMain.getWidth(), 300);
        panelTitle.setLocation(0, 0);

        gameName = new JLabel("Juego de rol");
        gameName.setForeground(Color.black);
        gameName.setFont(font);
        gameName.setLayout(null);
        gameName.setLocation(gameName.getX(), 200);
        panelTitle.add(gameName);
        selectName.add(panelTitle);

        registrerZone = new JPanel();
        registrerZone.setSize(300, 400);
        registrerZone.setLocation(centerX - registrerZone.getWidth() / 2, centerY - registrerZone.getHeight() / 2);
        registrerZone.setLayout(null);
        selectName.add(registrerZone);

        introduceYourName = new JLabel("Introduce tu nombre");
        introduceYourName.setForeground(Color.black);
        introduceYourName.setFont(font2);
        introduceYourName.setBounds(75, 120, 200, 30);
        registrerZone.add(introduceYourName);

        name = new JTextField();
        name.setBounds(50, 150, 200, 30);
        name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void updateButtonState() {
                buttonStart.setEnabled(!name.getText().isEmpty());
            }
        });
        registrerZone.add(name);

        buttonStart = new JButton("PLAY");
        buttonStart.setBounds(50, 200, 200, 50);
        buttonStart.setEnabled(false);
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = name.getText();
                selectName.setVisible(false);
                characterSelector(centerX, centerY);
            }
        });
        buttonStart.setFocusPainted(true);
        registrerZone.add(buttonStart);

        panelMain.add(selectName);


    }

    private void characterSelector(int centerX, int centerY) {;
        JPanel selectCharacter = new JPanel();
        selectCharacter.setSize(panelMain.getSize());
        selectCharacter.setLayout(null);
        selectCharacter.setLocation(0, 0);

        panelTitle = new JPanel();
        panelTitle.setOpaque(false);
        panelTitle.setSize(panelMain.getWidth(), 300);
        panelTitle.setLocation(0, 0);

        charactersTitle = new JLabel("Selecciona tu personaje:");
        charactersTitle.setForeground(Color.black);
        charactersTitle.setFont(font);

        panelTitle.add(charactersTitle);
        selectCharacter.add(panelTitle);

        panelCharacters = new JLabel();
        panelCharacters.setSize(panelMain.getSize());
        panelCharacters.setLocation(centerX - 400, centerY - 100);

        buttonWarrior = new JButton();
        Warrior warrior = new Warrior("");
        ImageIcon imageIcon = warrior.getDown();
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT)
        );
        buttonWarrior.setIcon(icon);
        buttonWarrior.setSize(200, 200);
        buttonWarrior.setLocation(0, 0);
        buttonWarrior.addMouseListener(new Enviorment(this.playerName, "Warrior", panelMain));

        buttonMage = new JButton("");
        Mage mage = new Mage("");
        ImageIcon imageIcon2 = mage.getDown();
        Icon icon2 = new ImageIcon(
                imageIcon2.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT)
        );
        buttonMage.setIcon(icon2);
        buttonMage.setSize(200, 200);
        buttonMage.setLocation(300, 0);
        buttonMage.addMouseListener(new Enviorment(this.playerName,"Mage", panelMain));

        buttonPriest = new JButton("");
        Priest priest = new Priest("");
        ImageIcon imageIcon3 = priest.getDown();
        Icon icon3 = new ImageIcon(
                imageIcon3.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT)
        );
        buttonPriest.setIcon(icon3);
        buttonPriest.setSize(200, 200);
        buttonPriest.setLocation(600, 0);
        buttonPriest.addMouseListener(new Enviorment(this.playerName,"Priest", panelMain));

        panelCharacters.add(buttonWarrior);
        panelCharacters.add(buttonMage);
        panelCharacters.add(buttonPriest);
        selectCharacter.add(panelCharacters);

        panelMain.add(selectCharacter);

    }

    private static void fullScreenWindow(JFrame frame) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }



    public static void main(String[] args) {

        JFrame frame = new JFrame("Juego de rol");
        Main game = new Main(frame);
        frame.setContentPane(game.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        fullScreenWindow(frame);

    }
}
