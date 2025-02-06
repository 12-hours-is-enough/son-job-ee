package com.sonjobee.model;

import lombok.Data;
import java.util.Date;

@Data
public class JobDetail {
	private String additionalInfo;
    private Date applicationDeadline;
    private Date createdAt;
    private Date updatedAt;
}
