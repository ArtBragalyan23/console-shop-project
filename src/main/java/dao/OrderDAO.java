package dao;
import model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.OrderStatus;
import model.Product;
import util.DatabaseConnection;
import java.sql.Statement;

public class OrderDAO {
    public int save(Order order) throws SQLException {
        String sql = """
                INSERT INTO orders(user_id, status, created_at)
                VALUES (?, ?, ?)
                """;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getStatus().name());
            statement.setTimestamp(3, Timestamp.valueOf(order.getCreatedAt()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt("id");
        }
    }

    public Optional<Order> findById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id =?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setInt(1, id);
             ResultSet resultSet = statement.executeQuery();
             if(resultSet.next()){
                 Order order = new Order(
                         resultSet.getInt("id"),
                         resultSet.getInt("user_id"),
                         OrderStatus.valueOf(resultSet.getString("status")),
                         resultSet.getTimestamp("created_at").toLocalDateTime()
                 );
                return Optional.of(order);
             }
            return Optional.empty();
    }
    }
    public List<Order> findAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT* FROM orders";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        OrderStatus.valueOf(resultSet.getString("status")),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                );
                orders.add(order);
            }
            return orders;
        }
}
    public void update (Order order) throws SQLException{
        String sql = "UPDATE orders SET user_id=?, status=?, created_at=? WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getStatus().name());
            statement.setTimestamp(3, Timestamp.valueOf(order.getCreatedAt()));
            statement.setInt(4, order.getId());
            statement.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException{
        String sql = "DELETE FROM orders WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
