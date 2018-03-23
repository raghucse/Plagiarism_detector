package edu.neu.user;

public interface UserService {
    void save(ApplicationUser user);

    public ApplicationUser findByUsername(String username);
}