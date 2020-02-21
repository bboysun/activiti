package com.darryl.activiti.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Auther: Darryl
 * @Description: 用于创建zk client测试使用
 * @Date: created in 2020/2/21 16:31
 */

public class ZkClientDemo {

    private CuratorFramework zkClient;

    //private String connectPath = "localhost:2181";

    private static String path = "/darryl_test/go";

    private static int num = 0;

    public ZkClientDemo(String connectPath) {
        zkClient = CuratorFrameworkFactory.newClient(connectPath, new ExponentialBackoffRetry(5000,3));
    }

    public static void main(String[] args) {
        ZkClientDemo demo = new ZkClientDemo("localhost:2181");
        demo.zkClient.start();
        // nodecache test
        demo.nodeCacheDemo(demo.zkClient);
    }

    // nodecache 测试
    public void nodeCacheDemo(CuratorFramework zkClient) {
        try {
            NodeCache nodeCache = new NodeCache(zkClient, path);
            nodeCache.start();

            System.out.println(num++ +".原始节点信息： " + nodeCache.getCurrentData());

            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    if (nodeCache.getCurrentData() != null) {
                        System.out.println(num++ + ".当前节点路径：" + nodeCache.getCurrentData().getPath() +
                            ", 当前节点发生变化内容：" + new String(nodeCache.getCurrentData().getData()));
                    }
                }
            });

            // 节点创建
            zkClient.create().creatingParentsIfNeeded().forPath(path,"test".getBytes());
            System.out.println("创建节点完成");
            Thread.sleep(3000);

            // 修改节点内容
            zkClient.setData().forPath(path, "darryl".getBytes());
            System.out.println("修改节点完成");
            Thread.sleep(3000);

            // 删除节点
            zkClient.delete().deletingChildrenIfNeeded().forPath(path);
            System.out.println("删除节点完成");
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
