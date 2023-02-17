import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Example2 {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Example2.class);
//		Layout layout = new PatternLayout("%d %p %m %C %M %l %n");
		Layout layout = new PatternLayout("%d{dd-MMM-yyyy}--> %p %m %C %M %l %n");
		Appender appender = new ConsoleAppender(layout);

		logger.addAppender(appender);

		logger.info("Hi");
	}

}
