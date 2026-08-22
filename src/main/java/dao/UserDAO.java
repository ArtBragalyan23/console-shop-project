package dao;
import model.User;
import util.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class UserDAO {
    public void save(User user) throws SQLException {
        String sql = """
                INSERT INTO users(name, email, city) 
                VALUES (?, ?, ?)
                """;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCity());
            statement.executeUpdate();
        }
    }

    public Optional<User> findById(int id) throws SQLException {
        String sql = """
                Select *
                FROM users
                WHERE id=?
                """;
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("city")
                     );
                return Optional.of(user);
        }
            return Optional.empty();
    }
}
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        List <User> users = new ArrayList<>();
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement =connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
            User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("city")
            );
            users.add(user);
    }
    }
    return users;
}
    public void update(User user) throws SQLException{
        String sql = """
                UPDATE users
                Set name = ?, email = ?, city=?
                WHERE id =?
                """;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setString(1, user.getName());
             statement.setString(2, user.getEmail());
             statement.setString(3, user.getCity());
             statement.setInt(4, user.getId());
             statement.executeUpdate();
    }
}
        public void delete(int id)throws SQLException{
        String sql = "DELETE FROM users WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        }
}