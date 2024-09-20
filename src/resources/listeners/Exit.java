package resources.listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exit {

    String playerName;
    int gold;
    Boolean win;
    int lifes;

    public Exit(String playerName, int gold, Boolean win, int lifes) {
        this.playerName = playerName;
        this.gold = gold;
        this.win = win;
        this.lifes = lifes;
    }

    public void finishGame() {
        String db_url = "jdbc:mysql://localhost:3307/game";
        String user = "root";
        String passwd = "MySQL";
        String insertQy = "insert into plays (playerName, gold, win, lifes) values (?,?,?,?)";

        try {
            Connection connection = DriverManager.getConnection(db_url, user, passwd);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQy);
            preparedStatement.setString(1, this.playerName);
            preparedStatement.setInt(2, gold);
            preparedStatement.setBoolean(3, win);
            preparedStatement.setInt(4, lifes);
            int addRows = preparedStatement.executeUpdate();
            if (addRows > 0) {
                System.out.println("Connection successful");
            }
            preparedStatement.close();
            connection.close();
        } catch (
                SQLException ex) {
            System.out.println("Connection failed");
        }
        System.exit(0);
    }
}
