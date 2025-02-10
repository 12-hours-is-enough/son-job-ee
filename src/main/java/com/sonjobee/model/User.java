package com.sonjobee.model;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;					// 기본 키
    private String name;				// 이름
    private String phone;				// 전화번호
    private Date birthDate;				// 생년월일
    private String email;				// 이메일
    private String password;			// 비밀번호
    private String gender;				// 성별
    private String experience;			// 경력
    private String preferredLocation;		// 선호 지역
    private String preferredSchedule;		// 근무 일정
    private String preferredJobCategory;	// 직업 분
    private List<Integer> appliedJobIds;		// 지원한 공고
    private String additionalInfo;				// 기타 사항
    private Timestamp createdAt;				// 생성 날짜
    private Timestamp updatedAt;				// 수정 날짜

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // JSON 문자열을 List<String>으로 변환
    public static List<String> convertJsonToList(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // JSON 문자열을 List<Integer>로 변환
    public static List<Integer> convertJsonToIntegerList(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Integer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
