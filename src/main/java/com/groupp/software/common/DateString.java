package com.groupp.software.common;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class DateString {
    private String submitDate;
    private String reviewDate;
    private String completionDate;
    private String faultOccurrenceDate;

    DateFormat formate1 = new SimpleDateFormat("yyyy-MM-dd");
    public void setSubmitDate(String date){
        submitDate=date;
    }
    public String getReviewDate(String date){
        return reviewDate;
    }
    public String getCompletionDate(String date){
        return completionDate;
    }
    public String getFaultOccurrenceDate(String date){
        return faultOccurrenceDate;
    }

}
