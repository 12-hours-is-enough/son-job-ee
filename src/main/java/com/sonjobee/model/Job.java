package com.sonjobee.model;

import lombok.Data;
import java.util.Date;

@Data
public class Job {
    private int id;
    private int companyId;
    private String location;
    private String jobCategory;
    private String salary;
    private String schedule;
    private String additionalInfo;
    private Date applicationDeadline;
    private Date createdAt;
    private Date updatedAt;
}