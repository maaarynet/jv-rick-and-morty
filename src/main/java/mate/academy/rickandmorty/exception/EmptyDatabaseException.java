package mate.academy.rickandmorty.exception;

public class EmptyDatabaseException extends RuntimeException {
    public EmptyDatabaseException(String message) {
        super(message);
    }
}
