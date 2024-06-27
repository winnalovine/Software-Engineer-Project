package com.groupp.software.common;

import org.springframework.stereotype.Component;

import java.io.Serializable;
//实现序列化 json格式
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateString implements Serializable {
    private String submitDate=null;
    private String reviewDate=null;
    private String completionDate=null;
    private String faultOccurrenceDate=null;

    DateFormat formate1 = new SimpleDateFormat("yyyy-MM-dd");
    public void setSubmitDate(Date date){
        if(date!=null){
            submitDate = formate1.format(date);
        }

    }
    public void setReviewDate(Date date){
        if(date!=null){
            reviewDate = formate1.format(date);
        }

    }
    public void setCompletionDate(Date date){
        if(date!=null){
            completionDate = formate1.format(date);
        }

    }
    public void setFaultOccurrenceDate(Date date){
        if(date!=null){
            faultOccurrenceDate = formate1.format(date);
        }


    }
    public String getSubmitDate(){
        return submitDate;
    }
    public String getReviewDate(){
        return reviewDate;
    }
    public String getCompletionDate(){
        return completionDate;
    }
    public String getFaultOccurrenceDate(){
        return faultOccurrenceDate;
    }



    public void printAll(){
        System.out.print("所有时间为：submitDate   "+submitDate+"\nreviewDate   "
                +reviewDate+"\ncompletionDate   "+completionDate
                +"\nfaultOccurrenceDate   "+faultOccurrenceDate);
    }
    @Override
    public String toString() {
        return "DateString{" +
                "submitDate='" + submitDate + '\'' +
                ", reviewDate='" + reviewDate + '\'' +
                ", completionDate='" + completionDate + '\'' +
                ", faultOccurrenceDate='" + faultOccurrenceDate + '\'' +
                '}';
    }

}
