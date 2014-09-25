package jodatest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaTimeTest {
	private static AtomicReference<String> lastDate = new AtomicReference<String>();

	public JodaTimeTest() {
		/*ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1);
		scheduler.scheduleWithFixedDelay(new OutpoutTask(), 10, 10, TimeUnit.SECONDS);*/
	}

	public static void main(String[] args) {
		//new JodaTimeTest();
	    format2PerFiveMinite();
	}

    private static void format2PerFiveMinite() {
    
        long accessTimeLong = (Long.valueOf("201407292359" ) )
                / 5 * 5 ;
	    DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMddHHmm");  
        //时间解析    
        DateTime datetime = DateTime.parse(String.valueOf(accessTimeLong), format); 
        System.out.println(datetime.plusMinutes(5).toString("yyyyMMddHHmm"));
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.plusDays(1).toString("yyyyMMddHHmm"));
    }

    private static void format() {
    
        DateTime dateTime = new DateTime();
		String currentDateStr = dateTime.toString("yyyyMMddHHmm");
		System.out.println(currentDateStr);
    }

	public static class OutpoutTask implements Runnable {

		@Override
		public void run() {
			DateTime dateTime = new DateTime();
			String currentDateStr = dateTime.toString("yyyyMMddHHmm");
			if(lastDate == null){
				
			}
			// 日期不是同一天
			if (!StringUtils.equals(lastDate.get(), currentDateStr)) {
				// 创建文件
				if (lastDate.compareAndSet(lastDate.get(), currentDateStr)) {
					File file = new File("d:\\log." + currentDateStr);
					if (!file.exists()) {
						try {
							file.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}

	}
}
