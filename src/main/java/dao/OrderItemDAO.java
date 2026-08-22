package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Order;
import model.OrderStatus;
import util.DatabaseConnection;
import model.OrderItem;
public class OrderItemDAO {
    public void save (OrderItem orderItem) throws SQLException{
        String sql = "INSERT INTO order_items(order_id, product_id, quantity) " +
                "VALUES(?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1, orderItem.getOrderId());
        statement.setInt(2, orderItem.getProductId());
        statement.setInt(3, orderItem.getQuantity());
        statement.executeUpdate();
        }
    }
    public Optional<OrderItem> findById(int id) throws SQLException{
        String sql = "SELECT * FROM order_items WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                OrderItem orderItem = new OrderItem(
                        resultSet.getInt("id"),
                                resultSet.getInt("order_id"),
                                resultSet.getInt("product_id"),
                                resultSet.getInt("quantity")
                );
                return Optional.of(orderItem);
            }
            return Optional.empty();
        }
    }
    public List<OrderItem> findAll() throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT* FROM order_items";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity")
                );
                orderItems.add(orderItem);
            }
            return orderItems;
        }
    }
    public void update (OrderItem orderItem) throws SQLException{
        String sql = "UPDATE order_items SET order_id=?, product_id=?, quantity=? WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, orderItem.getOrderId());
            statement.setInt(2, orderItem.getProductId());
            statement.setInt(3, orderItem.getQuantity());
            statement.setInt(4, orderItem.getId());
            statement.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException{
        String sql = "DELETE FROM order_items WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
