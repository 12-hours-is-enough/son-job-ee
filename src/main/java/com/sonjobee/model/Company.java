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
