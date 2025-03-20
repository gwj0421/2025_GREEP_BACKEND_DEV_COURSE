package step.two_three.domain;

import base.exception.IncorrectInputException;

public enum Category {
    boards,
    posts,
    accounts;

    public static Category getCategory(String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new IncorrectInputException();
        }
    }
}
