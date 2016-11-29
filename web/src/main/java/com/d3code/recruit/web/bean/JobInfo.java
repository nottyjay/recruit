package com.d3code.recruit.web.bean;

/**
 * Created by Nottyjay on 2016/11/29 0029.
 */
public class JobInfo {
    private String url;// 超链接地址
    private String name;// 职位名称
    private String city;// 所在城市
    private String salary;// 薪资范围
    private String createTime;// 发布时间

    private CompanyInfo company;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public CompanyInfo getCompany() {
        return company;
    }

    public void setCompany(CompanyInfo company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", salary='" + salary + '\'' +
                ", createTime='" + createTime + '\'' +
                ", company=" + company.toString() +
                '}';
    }
}
