package database;


/**
 * @Author Jonas De Boeck
 * */
public class DatabaseException extends RuntimeException {
    public DatabaseException() {
        super();
    }

    public DatabaseException (String message) {
        super(message);
    }
}
