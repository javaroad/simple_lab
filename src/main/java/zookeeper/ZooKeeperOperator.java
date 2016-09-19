package zookeeper;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO:add description of class here
 * @author lvzhouyang
 * @createDate 2014年12月11日
 */
public class ZooKeeperOperator extends AbstractZooKeeper{
    
    private static Logger log = LoggerFactory.getLogger(ZooKeeperOperator.class);
    
    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
    
        this.zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
    
    public List<String> getChild(String path) throws KeeperException, InterruptedException {
    
        List<String> list = null;
        try {
            list = this.zooKeeper.getChildren(path, false);
            if (list.isEmpty()) {
                log.debug(path + "中没有节点");
            } else {
                log.debug(path + "中存在节点");
                for (String child : list) {
                    log.debug("节点为：" + child);
                }
            }
        } catch (KeeperException.NoNodeException e) {
            // TODO: handle exception
            throw e;
            
        }
        return list;
    }
    
    public byte[] getData(String path) throws KeeperException, InterruptedException {
    
        return this.zooKeeper.getData(path, false, null);
    }
    

}
