package step.two_three.domain;

public enum Category {
    BOARD("boards"),
    POST("posts"),
    ACCOUNT("accounts"),
    EMPTY("");
    private String command;

    Category(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Category getCategory(String input) {
        for (Category value : values()) {
            if (value.getCommand().equals(input)) {
                return value;
            }
        }
        return EMPTY;
    }
}
