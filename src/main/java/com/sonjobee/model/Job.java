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
    
    // schedule 값이 유효한지 확인하는 메서드

    public static boolean isValidSchedule(String schedule) {

        return schedule.equals("평일") || schedule.equals("주말") || schedule.equals("상관없음");
    }

    public void setSchedule(String schedule) {
        if (isValidSchedule(schedule)) {
            this.schedule = schedule;
        } else {
            this.schedule = "상관없음";  // 기본값 설정
        }
    }
}