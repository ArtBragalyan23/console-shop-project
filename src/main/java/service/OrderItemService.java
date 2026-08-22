package service;

import dao.OrderItemDAO;
import model.Order;
import model.OrderItem;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderItemService {
   private final OrderItemDAO orderItemDAO = new OrderItemDAO();
   HashMap<Integer, Integer> cart = new HashMap<>();
   public void createOrderItem(int orderId, HashMap<Integer, Integer> cart) throws SQLException{
    for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
        OrderItem orderItem = new OrderItem(
                0,
                orderId,
                entry.getKey(),
                entry.getValue()
        );
        orderItemDAO.save(orderItem);
    }
    }
    public List<OrderItem> getAllOrderItems() throws SQLException{
        return orderItemDAO.findAll();
    }
    public Optional<OrderItem> getOrderItemById(int id) throws SQLException{
        return orderItemDAO.findById(id);
    }
    public void updateOrderItem(OrderItem orderItem) throws SQLException{
        orderItemDAO.update(orderItem);
    }
    public void deleteOrderItem(int id) throws SQLException{
        orderItemDAO.delete(id);
    }
}

