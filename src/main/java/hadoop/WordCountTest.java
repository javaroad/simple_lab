package hadoop;

import java.io.IOException;
import java.security.PrivilegedExceptionAction;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;


/**
 * 运行测试程序
 * 
 * @author yongboy
 * @date 2012-04-16
 */
public class WordCountTest{
    private static final Logger log = Logger.getLogger(WordCountTest.class);
    
    public static class TokenizerMapper extends
            Mapper<Object, Text, Text, IntWritable>{
       
        
        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {

            log.info("Map key : " + key);
            log.info("Map value : " + value);
            String[] arrs1 = StringUtils.splitPreserveAllTokens(value.toString(), "\t");
            context.write(new Text( arrs1[0]),new IntWritable(1));
        }
    }
    
    public static class IntSumReducer extends
            Reducer<Text, IntWritable, Text, IntWritable>{
        private IntWritable result = new IntWritable();
        
        public void reduce(Text key, Iterable<IntWritable> values,
                Context context) throws IOException, InterruptedException {

            log.info("Reduce key : " + key);
            log.info("Reduce value : " + values);
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            log.info("Reduce sum : " + sum);
            context.write(key, result);
        }
    }
    
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
//        conf.set("mapred.jar",
//                "D:/dev/hadoop/firsthadooptest/target/firsthadooptest-0.0.1.jar");
        conf.set("mapred.job.tracker", "HADOOPLOCAL:49001");
        conf.set("mapred.mapper.new-api", "true");
        conf.set("mapred.reducer.new-api", "true");
        conf.set("mapreduce.map.class", "WordCountTest$TokenizerMapper");
        conf.set("mapreduce.reduce.class", "WordCountTest$IntSumReducer");
        conf.set("mapred.mapoutput.key.class", "org.apache.hadoop.io.Text");
        conf.set("mapred.mapoutput.value.class", "org.apache.hadoop.io.IntWritable");
//        conf.set("mapred.child.tmp", "/hadoop/childtmp");
        
        args = new String[] { "hdfs://HADOOPLOCAL:49000/user/tmp/10.dat",
                "hdfs://HADOOPLOCAL:49000/user/tmp/output" };
        conf.set("mapred.input.dir",args[0]) ;
        conf.set("mapred.output.dir",args[1]) ;
        
        System.setProperty("path.separator", ":");
        ToolRunner.run(conf, new MapredTool(), new String[] {});
        
    }
}


class MapredTool extends Configured implements Tool {

    @Override
    public int run(String[] arg0) throws Exception {
        UserGroupInformation ugi = UserGroupInformation.createRemoteUser("root"); ;
        int code = ugi.doAs(new PrivilegedExceptionAction<Integer>() {
            @Override
            public Integer run() throws Exception {
                Job job = new Job(getConf());
                boolean success = job.waitForCompletion(true);
                return 0;
            }
        });
        return code;
    }
}
