package step.two_three.domain;

public class Request {
    private step.two_three.domain.Url url;
    private step.two_three.domain.Session session;

    public Request() {
        this.url = new step.two_three.domain.Url();
        this.session = new step.two_three.domain.Session();
    }

    public step.two_three.domain.Url getUrl() {
        return url;
    }

    public step.two_three.domain.Session getSession() {
        return session;
    }

    public void setSession(step.two_three.domain.Session session) {
        this.session = session;
    }

    public void setUrl(step.two_three.domain.Url url) {
        this.url = url;
    }
}
