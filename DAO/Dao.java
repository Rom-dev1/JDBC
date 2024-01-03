package DAO;

import java.sql.*;

public class Dao {
    
    private Dao() {
    }

    public static Connection connectDatabase() {
        try {
            // driver class et obj connexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false", "root", "");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERREUR : " + e);
            return null;
        }
    }
    
    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("ERREUR : " + e);
        }
    }
}