import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.scheduler.Cluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * App
 */
public class App {
    private String call;

    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        //设置Spout
        builder.setSpout("spout", new CallLogSpout());
        //设置creator-Bolt  shuffleGrouping表示随机分组
        builder.setBolt("creator-bolt", new CallLogCreatorBolt()).shuffleGrouping("spout");
        //设置counter-Bolt  fieldsGrouping根据字段分组 相同字段进入同一组
        builder.setBolt("counter-bolt", new CallLogCounterBolt()).fieldsGrouping("creator-bolt", new Fields("call"));

        Config conf = new Config();
        conf.setDebug(true);

        /**
         * 本地模式storm
         */
//        LocalCluster cluster = new LocalCluster();
//        cluster.submitTopology("LogAnalyserStorm", conf, builder.createTopology());
//        Thread.sleep(10000);
        StormSubmitter.submitTopology("mytop", conf, builder.createTopology());
    }
}
