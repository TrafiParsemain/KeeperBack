package KeeperGroup.KeeperBack.BasicAuth;

public class AuthenticationObject {

    private String message;

    public AuthenticationObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}