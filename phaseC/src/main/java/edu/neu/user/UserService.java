package edu.neu.user;

public interface UserService {
    void save(User user);

    public User findByUsername(String username);
}