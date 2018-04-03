package edu.neu.user;

/**
 * This class handles the server response
 */
public class ServerResponse {

    /**
     * @return returns the server message
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the server message
     * @param msg is the String value to be stored as the
     *            server message
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    String msg;

    /**
     * This constructor stores the server messgae
     * @param message is the message that is to be stored
     */
    public ServerResponse(String message){
        this.msg = message;
    }
}
