package lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class StringTest extends Assert {
	@Test
	public void testSplit() {
		// assertEquals("abcef;".split(";")[0], "abcef");
		// System.out.println(Arrays.toString(StringUtils.splitByWholeSeparator("32028@4!@#$%@",
		// "@4!@#$%@")));
		// System.out.println(Arrays.toString(StringUtils.splitPreserveAllTokens("32028@4!@#$%@",
		// "@4!@#$%@")));
		// System.out.println(Arrays.toString(StringUtils.split("32028@4!@#$%@",
		// "@4!@#$%@")));
//		String str = "沂水金仕顿大酒店-普通大床房/普通标准间（228）";
//		System.out.println(str.length());
//		System.out.println(str.getBytes().length);
//		System.out.println(StringUtils.replace(
//				StringUtils.abbreviate(str, str.length(), 19), "...", "…"));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("aaaa", "1111");
		List<String> list = new ArrayList<String>();
		list.add("asfdas");
		map.put("bbb", list);
		System.out.println(map);
	
	}
}
