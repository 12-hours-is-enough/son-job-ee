package com.sonjobee.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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