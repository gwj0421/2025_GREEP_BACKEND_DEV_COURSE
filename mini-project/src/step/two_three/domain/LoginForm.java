package step.two_three.domain;

public class LoginForm {
    private String id;
    private String pw;

    public LoginForm(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
