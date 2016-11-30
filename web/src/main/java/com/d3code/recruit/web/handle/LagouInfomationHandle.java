package com.d3code.recruit.web.handle;

import com.d3code.recruit.gather.handle.InfomationHandle;
import com.d3code.recruit.kafka.config.KafkaConfig;
import com.d3code.recruit.web.bean.CompanyInfo;
import com.d3code.recruit.web.bean.JobInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Created by Nottyjay on 2016/11/29 0029.
 */
@Component("lagouInfomationHandle")
public class LagouInfomationHandle implements InfomationHandle {

    private ObjectMapper mapper = new ObjectMapper();
    private Producer producer;
    @Autowired
    private KafkaConfig config;

    @PostConstruct
    public void init(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
        properties.put(ProducerConfig.ACKS_CONFIG, config.getAcks());
        properties.put(ProducerConfig.RETRIES_CONFIG, config.getRetries());
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, config.getBatchSize());
        properties.put(ProducerConfig.LINGER_MS_CONFIG, config.getLingerMs());
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, config.getBufferMemory());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, config.getKeySerializer());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, config.getValueSerializer());

        producer = new KafkaProducer<>(properties);
    }

    @Override
    public void handleInfo(String content) {
        try {
            Map<String, Object> responseJsonMap = mapper.readValue(content, Map.class);
            List<Map<String, Object>> jobJsonMap = (List<Map<String,Object>>) ((Map<String, Object>) ((Map<String, Object>) responseJsonMap.get("content")).get("positionResult")).get("result");
            for (Map<String, Object> job : jobJsonMap){
                JobInfo jobInfo = new JobInfo();
                CompanyInfo companyInfo = new CompanyInfo();
                companyInfo.setId((Integer)job.get("companyId"));
                companyInfo.setAdvantage((String)job.get("positionAdvantage"));
                companyInfo.setFinanceStage((String)job.get("financeStage"));
                companyInfo.setName((String)job.get("companyFullName"));
                companyInfo.setShortname((String)job.get("companyShortName"));
                jobInfo.setCompany(companyInfo);
                jobInfo.setName((String)job.get("positionName"));
                jobInfo.setCity((String)job.get("city"));
                jobInfo.setSalary((String)job.get("salary"));
                jobInfo.setCreateTime((String)job.get("createTime"));
                jobInfo.setUrl("https://www.lagou.com/jobs/" + job.get("positionId") + ".html");
                producer.send(new ProducerRecord<>("lagou", "job", jobInfo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
