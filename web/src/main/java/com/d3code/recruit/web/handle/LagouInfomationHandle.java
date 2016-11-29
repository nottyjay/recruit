package com.d3code.recruit.web.handle;

import com.d3code.recruit.gather.handle.InfomationHandle;
import com.d3code.recruit.web.bean.CompanyInfo;
import com.d3code.recruit.web.bean.JobInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by Nottyjay on 2016/11/29 0029.
 */
public class LagouInfomationHandle implements InfomationHandle {

    private ObjectMapper mapper = new ObjectMapper();

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
                System.out.println(jobInfo.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
