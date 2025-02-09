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
    private int companyId;		// 회사 ID
    private String jobTitle;    // 공고 제목
    private String jobContent;  // 공고내
    private String location;	// 회사 위치
    private String jobCategory;	// 작업 카테고리
    private String salary;		// 급여
    private String schedule;	// 근무 일정
    private String additionalInfo;		// 기타 정보
    private Date applicationDeadline;	// 공고 기한 날짜
    private Date createdAt;		// 공고 생성 날짜
    private Date updatedAt;		// 마지막 수정 날짜
    
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