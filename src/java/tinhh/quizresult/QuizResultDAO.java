/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.quizresult;

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
public class QuizResultDAO implements Serializable {

    public int getNumberPage(String email, String subjectName) throws Exception {
        MyConnection mcn = new MyConnection();
        int countPage = 0;
        String sql = "select COUNT(*)\n"
                + "from tblTakeQuiz as t,tblSubject as s\n"
                + "where t.subjectId = s.subjectId and email=? and subjectName like ? ";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, email);
            preStm.setString(2, subjectName);
            try (ResultSet rs = preStm.executeQuery()) {
                if (rs.next()) {
                    int number = rs.getInt(1);
                    countPage = number / 6;
                    if (number % 6 != 0) {
                        countPage++;
                    }
                }
            }
        }
        return countPage;
    }

    public ArrayList<QuizResultDTO> getPaging(int index, String email, String subjectName) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "Select takeQuizId,t.email,t.quizId,numOfCorrectAns,score,t.timeStart,t.timeEnd,t.subjectId,subjectName,quizName\n"
                + "from tblTakeQuiz as t,tblSubject as s,tblQuiz as q\n"
                + "where t.subjectId = s.subjectId and t.quizId=q.quizId and t.email=? and subjectName like ?\n"
                + "order by quizId\n"
                + "OFFSET ? ROWS\n"
                + "FETCH FIRST 6 ROWS ONLY";
        ArrayList<QuizResultDTO> dtos = new ArrayList<>();
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, email);
            preStm.setString(2, subjectName);
            preStm.setInt(3, (index - 1) * 6);
            try (ResultSet rs = preStm.executeQuery()) {
                while (rs.next()) {
                    QuizResultDTO dto = new QuizResultDTO();
                    dto.setTakeQuizId(rs.getString("takeQuizId"));
                    dto.setEmail(rs.getString("email"));
                    dto.setQuizId(rs.getString("quizId"));
                    dto.setNumOfCorrectAns(rs.getInt("numOfCorrectAns"));
                    dto.setScore(rs.getInt("score"));
                    dto.setTimeStart(rs.getTimestamp("timeStart"));
                    dto.setTimeEnd(rs.getTimestamp("timeEnd"));
                    dto.setSubjectId(rs.getString("subjectId"));
                    dto.setSubjectName(rs.getString("subjectName"));
                    dto.setQuizName(rs.getString("quizName"));
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }

    public boolean insertQuizResult(String takeQuizId, String email, String quizId, int numOfCorrectAns, int score, Date timeStart, Date timeEnd, String subjectId) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "INSERT INTO tblTakeQuiz(takeQuizId,email,quizId,numOfCorrectAns,score,timeStart,timeEnd,subjectId)\n"
                + "VALUES	(?,?,?,?,?,?,?,?);";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, takeQuizId);
            preStm.setString(2, email);
            preStm.setString(3, quizId);
            preStm.setInt(4, numOfCorrectAns);
            preStm.setInt(5, score);
            Timestamp ts = new Timestamp(timeStart.getTime());
            preStm.setTimestamp(6, ts);
            ts = new Timestamp(timeEnd.getTime());
            preStm.setTimestamp(7, ts);
            preStm.setString(8, subjectId);
            if (preStm.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }

    public String getLastIdQuizResult(String email) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "Select TOP 1 takeQuizId\n"
                + "from tblTakeQuiz\n"
                + "where email=?\n"
                + "order by timeStart DESC";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, email);
            try (ResultSet rs = preStm.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("takeQuizId");
                }
            }
        }
        return null;
    }

}
