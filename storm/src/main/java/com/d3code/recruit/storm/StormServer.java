package com.d3code.recruit.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.spout.*;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.storm.generated.ComponentCommon._Fields.STREAMS;

/**
 * Created by jileilei on 2016/12/2.
 */
public class StormServer {

    static{

    }

    public void run(String[] args) throws Exception{
        if(args.length == 0){
            submitTopologyLocalCluster(getTopolgyKafkaSpout(), getConfig());
        }else{
            submitTopologyRemoteCluster(args[0], getTopolgyKafkaSpout(), getConfig());
        }
    }

    protected void submitTopologyLocalCluster(StormTopology topology, Config config) throws InterruptedIOException {
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("order", config, topology);
        stopWaitingForInput();
    }

    protected void submitTopologyRemoteCluster(String arg, StormTopology topology, Config config) throws Exception{
        StormSubmitter.submitTopology(arg, config, topology);
    }

    protected void stopWaitingForInput() {
        try {
            System.out.println("PRESS ENTER TO STOP");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Config getConfig(){
        Config config = new Config();
        config.setDebug(true);
        return config;
    }

    protected StormTopology getTopolgyKafkaSpout(){
        final TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafka_spout", new KafkaSpout<>(getKafkaSpoutConfig(getKafkaSpoutStreams())), 1);
        builder.setBolt("kafka_bolt", new KafkaSpoutTestBolt()).shuffleGrouping("kafka_spout", STREAMS[0]);
        return builder.createTopology();
    }

    protected KafkaSpoutConfig<String, String> getKafkaSpoutConfig(KafkaSpoutStreams kafkaSpoutStreams){
        return new KafkaSpoutConfig.Builder<String, String>(getKafkaConsumerProps(), kafkaSpoutStreams, getTuplesBuilder(), getRetryService())
                .setOffsetCommitPeriodMs(10_000)
                .setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.EARLIEST)
                .setMaxUncommittedOffsets(250)
                .build();
    }

    protected Map<String, Object> getKafkaConsumerProps(){
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(KafkaSpoutConfig.Consumer.BOOTSTRAP_SERVERS, "192.168.199.186:9092,192.168.199.164:9092,192.168.199.199:9092");
        props.put(KafkaSpoutConfig.Consumer.GROUP_ID, "kafkaSpoutTestGroup");
        props.put(KafkaSpoutConfig.Consumer.KEY_DESERIALIZER, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(KafkaSpoutConfig.Consumer.VALUE_DESERIALIZER, "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    protected KafkaSpoutTuplesBuilder<String, String> getTuplesBuilder(){
        return new KafkaSpoutTuplesBuilderNamedTopics.Builder<>(
                new TopicsTestTupleBuilder<String, String>(TOPICS[0])
        ).build();
    }

    protected KafkaSpoutRetryService getRetryService(){
        return new KafkaSpoutRetryExponentialBackoff(KafkaSpoutRetryExponentialBackoff.TimeInterval.microSeconds(500),
                KafkaSpoutRetryExponentialBackoff.TimeInterval.milliSeconds(2),
                Integer.MAX_VALUE, KafkaSpoutRetryExponentialBackoff.TimeInterval.seconds(10));

    }

    protected KafkaSpoutStreams getKafkaSpoutStreams() {
        final Fields outputFields = new Fields("topic", "partition", "offset", "key", "value");
        return new KafkaSpoutStreamsNamedTopics.Builder(outputFields, STREAMS[0], new String[]{})  // contents of topics test, test1, sent to test_stream
                .build();
    }
}
