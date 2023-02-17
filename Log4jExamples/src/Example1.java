import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Example1 {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Example1.class);
		Layout layout = new SimpleLayout();
		Appender appender = new ConsoleAppender(layout);

		logger.addAppender(appender);

		logger.info("Info message The Db connection Done!!");
		logger.debug("Debug message");
		logger.error("Error message");
		logger.warn("Warn message");
		logger.fatal("Fatal message");
	}

}
