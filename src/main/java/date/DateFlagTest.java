package date;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DateFlagTest {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        // System.out.println(DateFlag.valueOf("HISTORY").getDate("2014-07-01"));
        //
        // String yby = new DateTime().minusDays(2).toString("yyyyMMdd");
        // System.out.println(yby);
        // System.out.println(yby.compareTo("20150419"));
        // System.out.println(yby.compareTo("20150318"));
        // System.out.println(yby.compareTo("20150520"));
        //
        // DateTime dateTime = new DateTime(new Date());
        // DateTime startOfWeek = dateTime.dayOfWeek().withMinimumValue();
        // DateTime endOfWeek = dateTime.dayOfWeek().withMaximumValue();
        // System.out.println(startOfWeek.toString("yyyyMMdd"));
        // System.out.println(endOfWeek.toString("yyyyMMdd"));
        // System.out.println(new DateTime(new Date()).getHourOfDay()) ;
        // Integer i = null;
        // System.out.println(new DateTime("2016-01-01").toDate().getTime());
        //
        // Date date = Time.valueOf("00:00:01");
        // Date date1 = Time.valueOf(new DateTime().toString("HH:mm:ss"));
        // System.out.println(date + "\n" + date1);
        // }
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ConfigDateJson.class);

        List<ConfigDateJson> dates =
                mapper.readValue("[{\"beginTime\":\"00:00:00\",\"endTime\":\"01:00:00\"}]", javaType);
        System.out.println(dates);
    }

}
