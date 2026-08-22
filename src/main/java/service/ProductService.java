package service;
import dao.ProductDAO;
import model.Product;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAO();
    public void createProduct (Product product) throws SQLException{
        productDAO.save(product);
    }
    public List<Product> getAllProducts() throws SQLException{
        return productDAO.findAll();
    }
    public Optional<Product> getProductById(int id) throws SQLException{
        return productDAO.findById(id);
    }
    public void updateProduct(Product product) throws SQLException{
        productDAO.update(product);
    }
    public void deleteProduct(int id) throws SQLException{
        productDAO.delete(id);
    }
}
