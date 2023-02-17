package log4j_maven_prc;

import org.apache.log4j.Logger;

public class Test1 {

	public static void main(String[] args) {
		
		Logger logger=Logger.getLogger(Test1.class);
		
		logger.info("Info message");
		logger.debug("Debug message");
		logger.error("Error message");
		logger.warn("Warn message");
		logger.fatal("Fatal message");

	}

}
