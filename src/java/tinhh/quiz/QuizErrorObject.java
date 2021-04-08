/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.quiz;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class QuizErrorObject implements Serializable{
    private String subjectError,timeStartError,timeEndError;

    public QuizErrorObject(String subjectError, String timeStartError, String timeEndError) {
        this.subjectError = subjectError;
        this.timeStartError = timeStartError;
        this.timeEndError = timeEndError;
    }

    public QuizErrorObject() {
    }

    public String getSubjectError() {
        return subjectError;
    }

    public void setSubjectError(String subjectError) {
        this.subjectError = subjectError;
    }

    public String getTimeStartError() {
        return timeStartError;
    }

    public void setTimeStartError(String timeStartError) {
        this.timeStartError = timeStartError;
    }

    public String getTimeEndError() {
        return timeEndError;
    }

    public void setTimeEndError(String timeEndError) {
        this.timeEndError = timeEndError;
    }
    
}
