import org.apache.logging.log4j.LogManager;

public class Logger {
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger("MyLogger");
    private static org.apache.logging.log4j.Logger log2 = LogManager.getLogger("ErrorLog");

    public static org.apache.logging.log4j.Logger getErrorLog() {
        return log2;
    }

    public static org.apache.logging.log4j.Logger getLog() {
        return log;
    }
}
