package hadoop;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PrivilegedExceptionAction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.Tool;

public class HdfsCommon {
	private Configuration conf;
	private FileSystem fs;

	public HdfsCommon() throws IOException {
		conf = new Configuration();
		conf.set("user.name", "root");
		fs = FileSystem.get(conf);
	}

	/**
	 * 上传文件，
	 * 
	 * @param localFile
	 *            本地路径
	 * @param hdfsPath
	 *            格式为hdfs://ip:port/destination
	 * @throws IOException
	 */
	public void upFile(String localFile, String hdfsPath) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(localFile));
		OutputStream out = fs.create(new Path(hdfsPath));
		org.apache.hadoop.io.IOUtils.copyBytes(in, out, conf);
	}

	/**
	 * 附加文件
	 * 
	 * @param localFile
	 * @param hdfsPath
	 * @throws IOException
	 */
	public void appendFile(String localFile, String hdfsPath)
			throws IOException {
		InputStream in = new FileInputStream(localFile);
		OutputStream out = fs.append(new Path(hdfsPath));
		IOUtils.copyBytes(in, out, conf);
	}

	/**
	 * 下载文件
	 * 
	 * @param hdfsPath
	 * @param localPath
	 * @throws IOException
	 */
	public void downFile(String hdfsPath, String localPath) throws IOException {
		InputStream in = fs.open(new Path(hdfsPath));
		OutputStream out = new FileOutputStream(localPath);
		IOUtils.copyBytes(in, out, conf);
	}

	/**
	 * 删除文件或目录
	 * 
	 * @param hdfsPath
	 * @throws IOException
	 */
	public void delFile(String hdfsPath) throws IOException {
		fs.delete(new Path(hdfsPath), true);
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
//		System.setProperty("user.name", "root");
//		// System.setProperty("path.separator", ":");
//		HdfsCommon hdfs = new HdfsCommon();
//		// hdfs.upFile("D:\\tmp\\statlog.201409191647",
//		// "hdfs://HADOOPLOCAL:49000/tmp/statlog.201409191647");
//		hdfs.upFile("D:\\tmp\\statlog.201409191647",
//				"/storm/output/test/statlog.201409191647");

		UserGroupInformation ugi = UserGroupInformation
				.createRemoteUser("root");
		int code = ugi.doAs(new PrivilegedExceptionAction<Integer>() {
			@Override
			public Integer run() throws IOException {
				HdfsCommon hdfs = new HdfsCommon();
				//hdfs.delFile(hdfsPath);
				hdfs.upFile("D:\\tmp\\statlog.201409191648",
						"/storm/output/test/statlog.201409191648");
				return 1;
			}
		});

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
}