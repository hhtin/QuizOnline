/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.quizresult;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class QuizResultDTO implements Serializable{
    private String takeQuizId,email,quizId,subjectId,subjectName,quizName;
    private int numOfCorrectAns,score;
    private Date timeStart,timeEnd;

    public QuizResultDTO() {
    }

    public QuizResultDTO(String takeQuizId, String email, String quizId, String subjectId, String subjectName, int numOfCorrectAns, int score, Date timeStart, Date timeEnd) {
        this.takeQuizId = takeQuizId;
        this.email = email;
        this.quizId = quizId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.numOfCorrectAns = numOfCorrectAns;
        this.score = score;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getTakeQuizId() {
        return takeQuizId;
    }

    public void setTakeQuizId(String takeQuizId) {
        this.takeQuizId = takeQuizId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumOfCorrectAns() {
        return numOfCorrectAns;
    }

    public void setNumOfCorrectAns(int numOfCorrectAns) {
        this.numOfCorrectAns = numOfCorrectAns;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    
    
    
    
}
