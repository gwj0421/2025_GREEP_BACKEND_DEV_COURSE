package step.twoAndThree.domain;

import java.util.Map;

public class Url {
    private String category;
    private String function;
    private Map<String, String> parameters;

    public Url() {
    }

    public Url(String category, String function, Map<String, String> parameters) {
        this.category = category;
        this.function = function;
        this.parameters = parameters;
    }

    public String getCategory() {
        return category;
    }

    public String getFunction() {
        return function;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
