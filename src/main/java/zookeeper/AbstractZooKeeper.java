package zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO:add description of class here
 * @author lvzhouyang
 * @createDate 2014年12月11日
 */
public class AbstractZooKeeper implements Watcher{
    private static Logger log = LoggerFactory.getLogger(AbstractZooKeeper.class);
    
    // 缓存时间
    private static final int SESSION_TIME = 5000;
    protected ZooKeeper zooKeeper;
    protected CountDownLatch countDownLatch = new CountDownLatch(1);
    
    public void connect(String hosts) throws IOException, InterruptedException {
    
        connect(hosts, SESSION_TIME);
    }
    
    public void connect(String hosts, int sessionTime) throws IOException, InterruptedException {
    
        zooKeeper = new ZooKeeper(hosts, sessionTime, this);
        countDownLatch.await();
        log.debug("连接" + hosts + " 成功！");
    }
    
    /*
     * (non-Javadoc)
     * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
     */
    @Override
    public void process(WatchedEvent event) {
    
        // TODO Auto-generated method stub
        if (event.getState() == KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }
    
    public void close() throws InterruptedException {
    
        zooKeeper.close();
        log.debug("关闭连接");
    }
}
