package step.two_three.domain;

public enum Function {
    ADD("add"),
    EDIT("edit"),
    REMOVE("remove"),
    VIEW("view"),
    SIGN_UP("signup"),
    SIGN_IN("signin"),
    SIGN_OUT("signout"),
    DETAIL("detail"),
    EMPTY("");
    private String command;

    Function(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Function getFunction(String input) {
        for (Function value : values()) {
            if (value.getCommand().equals(input)) {
                return value;
            }
        }
        return EMPTY;
    }
}
