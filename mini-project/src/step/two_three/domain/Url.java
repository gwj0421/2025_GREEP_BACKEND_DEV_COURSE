package step.two_three.domain;

import java.util.Map;

public class Url {
    private Category category;
    private Function function;
    private Map<String, String> parameters;

    public Url() {
    }


    public Url(Category category, Function function, Map<String, String> parameters) {
        this.category = category;
        this.function = function;
        this.parameters = parameters;
    }

    public Category getCategory() {
        return category;
    }

    public Function getFunction() {
        return function;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
