package lab;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class ObjectUtilsTest extends Assert {
	@Test
	public void testSplit() {
		Object[] array2 = new Object[]{200l,300l};
		System.out.println(Arrays.toString(ArrayUtils.addAll(new Object[]{100l,400l}, array2)));
	}
}
