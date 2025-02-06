package com.sonjobee.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConverter {
    public static Timestamp convertStringToTimestamp(String dateStr) {
        try {
            // 원하는 날짜 형식에 맞는 SimpleDateFormat 객체 생성
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            // String을 Date 객체로 변환
            Date parsedDate = sdf.parse(dateStr);
            
            // Date를 Timestamp로 변환하여 반환
            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

}
