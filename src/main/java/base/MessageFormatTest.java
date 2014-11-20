package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageFormatTest {
	static final Logger logger = LoggerFactory.getLogger(MessageFormatTest.class);
	public static void main(String[] args) {
		logger.info(" from App where packageName in({})", "aaa");
		//System.out.println(MessageFormat.format(" from App where packageName in({1})", "aaa"));
		Boolean istrue = true ;
	}
}
