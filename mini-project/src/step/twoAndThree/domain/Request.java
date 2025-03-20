package step.twoAndThree.domain;

public class Request {
    private step.twoAndThree.domain.Url url;
    private step.twoAndThree.domain.Session session;

    public Request() {
        this.url = new step.twoAndThree.domain.Url();
        this.session = new step.twoAndThree.domain.Session();
    }

    public step.twoAndThree.domain.Url getUrl() {
        return url;
    }

    public step.twoAndThree.domain.Session getSession() {
        return session;
    }

    public void setSession(step.twoAndThree.domain.Session session) {
        this.session = session;
    }

    public void setUrl(step.twoAndThree.domain.Url url) {
        this.url = url;
    }
}
