package service;
import dao.OrderDAO;
import model.Order;
import model.Product;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderService {
   private final OrderDAO orderDAO = new OrderDAO();
    public int createOrder (Order order) throws SQLException {
         return orderDAO.save(order);
    }
    public List<Order> getAllOrders() throws SQLException{
        return orderDAO.findAll();
    }
    public Optional<Order> getOrderById(int id) throws SQLException{
        return orderDAO.findById(id);
    }
    public void updateOrder(Order order) throws SQLException{
        orderDAO.update(order);
    }
    public void deleteOrder(int id) throws SQLException{
        orderDAO.delete(id);
    }
}
