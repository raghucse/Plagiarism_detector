package edu.neu.user;

public class UserResponse {

    UserResponse(){

    }


    UserResponse(ApplicationUser user){
        this.userName = user.getUsername();
        this.role = user.getRole();
        this.userId = user.getId();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    String userName;
    int userId;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    Role role;
}

