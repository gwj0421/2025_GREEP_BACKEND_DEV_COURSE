package step_2_3.domain;

public class Request {
    private Url url;
    private Session session;

    public Request() {
        this.url = new Url();
        this.session = new Session();
    }

    public Url getUrl() {
        return url;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setUrl(Url url) {
        this.url = url;
    }
}
