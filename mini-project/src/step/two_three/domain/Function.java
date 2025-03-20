package step.two_three.domain;

import base.exception.IncorrectInputException;

public enum Function {
    add,
    edit,
    remove,
    view,
    signup,
    signin,
    signout,
    detail;

    public static Function getFunction(String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new IncorrectInputException();
        }
    }
}
