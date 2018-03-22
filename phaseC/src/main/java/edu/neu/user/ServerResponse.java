package edu.neu.user;

public class ServerResponse {
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    String msg;
    public ServerResponse(String message){
        this.msg = message;
    }
}
