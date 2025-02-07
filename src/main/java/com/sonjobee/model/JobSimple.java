package com.sonjobee.model;

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
public class JobSimple {
    private int id;
    private int companyId;		// 회사 ID
    private String location;	// 회사 위치
    private String jobCategory;	// 작업 카테고리
    private String salary;		// 급여
    private String schedule;	// 근무 일정
    
    
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

