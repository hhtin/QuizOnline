/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.quiz;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class QuizDTO implements Serializable{
    private String quizId,quizName,subjectId,subjectName;
    private int limitTime,numberQuestion;
    private Date createTime,timeStart,timeEnd;
    private boolean status;

    public QuizDTO() {
    }

    public QuizDTO(String quizId, String subjectId, int limitTime, int numberQuestion, Date createTime, Date timeStart, Date timeEnd) {
        this.quizId = quizId;
        this.subjectId = subjectId;
        this.limitTime = limitTime;
        this.numberQuestion = numberQuestion;
        this.createTime = createTime;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    
    
    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    public Date formatDateValue(String str) throws  Exception{
        String[] spStr=str.split("T");
        String strDate=spStr[0];
        String strHour=spStr[1];
        
        String date=strDate+" "+strHour;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm"); 
        Date rightDate= dateFormat.parse(date);
        return  rightDate;
    }
}
