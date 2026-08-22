package service;
import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public void createUser(User user) throws SQLException{
        userDAO.save(user);
    }
    public List<User> getAllUsers() throws SQLException{
        return userDAO.findAll();
    }
public Optional<User> findUserById (int id) throws SQLException{
        return userDAO.findById(id);
}
public void updateUser(User user) throws SQLException{
        userDAO.update(user);
}
public void deleteUser(int id) throws SQLException{
        userDAO.delete(id);
}
}
