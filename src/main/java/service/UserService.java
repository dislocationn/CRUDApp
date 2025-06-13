package service;

import model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void update (User user);

    User findUserById(long id);

    List<User> getAllUsers();

    void deleteUserById(Long id);
}
