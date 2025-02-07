package com.sonjobee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company {
	private Integer id;
	private String name;
	private String phone;
	private String email;
	private String password;
	private Date createdAt;
	private Date updatedAt;
}


/*
 	id              INT AUTO_INCREMENT PRIMARY KEY,  -- 기본 키 (자동 증가)
    name            VARCHAR(100) NOT NULL,          -- 회사 이름
    phone           VARCHAR(15) NOT NULL UNIQUE,    -- 회사 전화번호 (중복 방지)
    email           VARCHAR(100) NOT NULL UNIQUE,   -- 이메일 (로그인 ID)
    password        VARCHAR(255) NOT NULL,          -- 비밀번호 (해싱 필요)
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 날짜
    updated_at

*/
 