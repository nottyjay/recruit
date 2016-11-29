package com.d3code.recruit.web.bean;

/**
 * Created by Nottyjay on 2016/11/29 0029.
 */
public class CompanyInfo {

    private Integer id;
    private String name;
    private String shortname;
    private String advantage;
    private String financeStage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getFinanceStage() {
        return financeStage;
    }

    public void setFinanceStage(String financeStage) {
        this.financeStage = financeStage;
    }

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortname='" + shortname + '\'' +
                ", advantage='" + advantage + '\'' +
                ", financeStage='" + financeStage + '\'' +
                '}';
    }
}
