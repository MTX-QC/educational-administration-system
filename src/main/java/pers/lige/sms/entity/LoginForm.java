package pers.lige.sms.entity;
//登录信息
public class LoginForm {
    private String username;
    private String password;
    private String verifiCode;
    private String userType;

    public LoginForm() {
    }

    public LoginForm(String username, String password, String verifiCode, String userType) {
        this.username = username;
        this.password = password;
        this.verifiCode = verifiCode;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", verifiCode='" + verifiCode + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
