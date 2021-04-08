/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.quiz;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import tinhh.connection.MyConnection;

/**
 *
 * @author DELL
 */
public class QuizDAO implements Serializable {

    public boolean createQuiz(String quizId, String quizName, int limitTime, String email, String subjectId, int numberQuestion, Date timeStart, Date timeEnd, boolean status) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "INSERT INTO tblQuiz(quizId,quizName,limitTime,email,subjectId,createDate,numberQuestion,timeStart,timeEnd,status)\n"
                + "VALUES	(?,?,?,?,?,?,?,?,?,?);";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, quizId);
            preStm.setString(2, quizName);
            preStm.setInt(3, limitTime);
            preStm.setString(4, email);
            preStm.setString(5, subjectId);
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            preStm.setTimestamp(6, ts);
            preStm.setInt(7, numberQuestion);
            ts = new Timestamp(timeStart.getTime());
            preStm.setTimestamp(8, ts);
            ts = new Timestamp(timeEnd.getTime());
            preStm.setTimestamp(9, ts);
            preStm.setBoolean(10, status);
            if (preStm.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean updateStatus(String quizId, boolean status) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "Update tblQuiz\n"
                + "set status=?\n"
                + "where quizId=?";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setBoolean(1, status);
            preStm.setString(2, quizId);
            if (preStm.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }

    public String getLastQuizId() throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "SELECT TOP 1 quizId\n"
                + "FROM tblQuiz\n"
                + "order by createDate DESC";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            try (ResultSet rs = preStm.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("quizId");
                }
            }
        }
        return null;
    }

    public ArrayList<QuizDTO> getQuizList(String email) throws Exception {
        MyConnection mcn = new MyConnection();
        ArrayList<QuizDTO> dtos = new ArrayList<>();
        String sql = "Select quizId,quizName,limitTime,subjectName,numberQuestion,timeStart,timeEnd,status\n"
                + "from tblQuiz as q,tblSubject as s\n"
                + "where q.subjectId=s.subjectId and email=?";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, email);
            try (ResultSet rs = preStm.executeQuery()) {
                while (rs.next()) {
                    QuizDTO dto = new QuizDTO();
                    dto.setQuizId(rs.getString("quizId"));
                    dto.setQuizName(rs.getString("quizName"));
                    dto.setLimitTime(rs.getInt("limitTime"));
                    dto.setSubjectName(rs.getString("subjectName"));
                    dto.setNumberQuestion(rs.getInt("numberQuestion"));
                    dto.setTimeStart(rs.getTimestamp("timeStart"));
                    dto.setTimeEnd(rs.getTimestamp("timeEnd"));
                    dto.setStatus(rs.getBoolean("status"));
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }

    public ArrayList<QuizDTO> getQuizListStudent() throws Exception {
        MyConnection mcn = new MyConnection();
        ArrayList<QuizDTO> dtos = new ArrayList<>();
        String sql = "Select quizId,quizName,limitTime,subjectName,numberQuestion,timeStart,timeEnd,status,q.subjectId\n"
                + "from tblQuiz as q,tblSubject as s\n"
                + "where q.subjectId=s.subjectId and status=1";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            try (ResultSet rs = preStm.executeQuery()) {
                while (rs.next()) {
                    QuizDTO dto = new QuizDTO();
                    dto.setQuizId(rs.getString("quizId"));
                    dto.setQuizName(rs.getString("quizName"));
                    dto.setLimitTime(rs.getInt("limitTime"));
                    dto.setSubjectName(rs.getString("subjectName"));
                    dto.setNumberQuestion(rs.getInt("numberQuestion"));
                    dto.setTimeStart(rs.getTimestamp("timeStart"));
                    dto.setTimeEnd(rs.getTimestamp("timeEnd"));
                    dto.setStatus(rs.getBoolean("status"));
                    dto.setSubjectId(rs.getString("subjectId"));
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }

    public QuizDTO getQuizInfo(String quizId) throws Exception {
        MyConnection mcn = new MyConnection();
        QuizDTO dto = new QuizDTO();
        String sql = "Select quizId,quizName,limitTime,q.subjectId,numberQuestion,timeStart,timeEnd,status,subjectName\n"
                + "from tblQuiz as q, tblSubject as s\n"
                + "where q.subjectId=s.subjectId and quizId=? and status=1";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, quizId);
            try (ResultSet rs = preStm.executeQuery()) {
                if (rs.next()) {
                    dto.setQuizId(rs.getString("quizId"));
                    dto.setQuizName(rs.getString("quizName"));
                    dto.setLimitTime(rs.getInt("limitTime"));
                    dto.setNumberQuestion(rs.getInt("numberQuestion"));
                    dto.setTimeStart(rs.getTimestamp("timeStart"));
                    dto.setTimeEnd(rs.getTimestamp("timeEnd"));
                    dto.setStatus(rs.getBoolean("status"));
                    dto.setSubjectId(rs.getString("subjectId"));
                    dto.setSubjectName(rs.getString("subjectName"));
                }
            }
        }
        return dto;
    }

}
