package base;

import java.text.MessageFormat;

public class MessageFormatTest {
	public static void main(String[] args) {
		System.out.println(MessageFormat.format(" from App where packageName in({1})", "aaa"));
	}
}
