package base.exception;

public class IncorrectInputException extends RuntimeException {
    public IncorrectInputException() {
        super("잘못된 입력");
    }
}
