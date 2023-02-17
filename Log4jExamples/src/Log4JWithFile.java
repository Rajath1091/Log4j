import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log4JWithFile {

	public static void main(String[] args) throws IOException {

		Logger logger = Logger.getLogger(Log4JWithFile.class);
		Layout layout = new PatternLayout("%d %p %m %C %M %l %n");
		Appender appender = new FileAppender(layout, "C:/Users/sanda/Desktop/Log4J/examplelogs.log");

		logger.addAppender(appender);

		logger.info("First log");
		logger.info("Second log");
	}

}
