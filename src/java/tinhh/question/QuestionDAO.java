/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.question;

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
public class QuestionDAO implements Serializable {

    public boolean createQuestion(String questionId, String questionContent, String ansA, String ansB, String ansC, String ansD, String correctAns, String subjectId, boolean status) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "INSERT INTO tblQuestion(questionId,questionContent,ansA,ansB,ansC,ansD,correctAns,createDate,subjectId,status)\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?);";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, questionId);
            preStm.setString(2, questionContent);
            preStm.setString(3, ansA);
            preStm.setString(4, ansB);
            preStm.setString(5, ansC);
            preStm.setString(6, ansD);
            preStm.setString(7, correctAns);
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            preStm.setTimestamp(8, ts);
            preStm.setString(9, subjectId);
            preStm.setBoolean(10, status);
            if (preStm.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean removeQuestion(String questionId) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "Update tblQuestion\n"
                + "set status=0\n"
                + "where questionId=?";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, questionId);
            if (preStm.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean updateQuestion(String questionContent, String ansA, String ansB, String ansC, String ansD, String correctAns, String subjectId, boolean status, String questionId) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "Update tblQuestion\n"
                + "set questionContent=? , ansA=?,ansB=?,ansC=?,ansD=?,correctAns=?,subjectId=?,status=?\n"
                + "where questionId=?";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, questionContent);
            preStm.setString(2, ansA);
            preStm.setString(3, ansB);
            preStm.setString(4, ansC);
            preStm.setString(5, ansD);
            preStm.setString(6, correctAns);
            preStm.setString(7, subjectId);
            preStm.setBoolean(8, status);
            preStm.setString(9, questionId);
            if (preStm.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }

    public String getLastQuestionId(String subjectId) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "SELECT TOP 1 questionId\n"
                + "FROM tblQuestion\n"
                + "where subjectId=?\n"
                + "order by createDate DESC";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, subjectId);
            try (ResultSet rs = preStm.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("questionId");
                }
            }
        }
        return null;
    }

    public int getNumQuestionOfSbj(String subjectId) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "Select count(*)\n"
                + "from tblQuestion\n"
                + "where subjectId=?  and status=1";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, subjectId);
            try (ResultSet rs = preStm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public QuestionDTO getQuestion(String questionId) throws Exception {
        MyConnection mcn = new MyConnection();
        QuestionDTO dto = new QuestionDTO();
        String sql = "Select questionId,questionContent,ansA,ansB,ansC,ansD,correctAns,createDate,q.subjectId,subjectName, status\n"
                + "from tblQuestion AS q, tblSubject AS s\n"
                + "where q.subjectId=s.subjectId and questionId=?";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, questionId);
            try (ResultSet rs = preStm.executeQuery()) {
                if (rs.next()) {
                    dto.setQuestionId(questionId);
                    dto.setQuestionContent(rs.getString("questionContent"));
                    dto.setAnsA(rs.getString("ansA"));
                    dto.setAnsB(rs.getString("ansB"));
                    dto.setAnsC(rs.getString("ansC"));
                    dto.setAnsD(rs.getString("ansD"));
                    dto.setCorrectAns(rs.getString("correctAns"));
                    dto.setCreateDate(rs.getDate("createDate"));
                    dto.setSubjectId(rs.getString("subjectId"));
                    dto.setSubjectName(rs.getString("subjectName"));
                    dto.setStatus(rs.getBoolean("status"));
                }
            }
        }
        return dto;
    }

    public int getNumberPage(String questionContent, String subjectId, boolean status) throws Exception {
        MyConnection mcn = new MyConnection();
        int countPage = 0;
        String sql = "select COUNT(*)\n"
                + "from tblQuestion\n"
                + "where questionContent like ? and subjectId like ? and status=? ";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, questionContent);
            preStm.setString(2, subjectId);
            preStm.setBoolean(3, status);
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

    public ArrayList<QuestionDTO> getPaging(int index, String questionContent, String subjectId, boolean status) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "Select  questionId,questionContent,ansA,ansB,ansC,ansD,correctAns,createDate,q.subjectId, subjectName,status\n"
                + "from tblQuestion AS q, tblSubject AS s \n"
                + "where q.subjectId=s.subjectId and questionContent like ? and q.subjectId like ? and status=?\n"
                + "order by questionContent\n"
                + "OFFSET ? ROWS\n"
                + "FETCH FIRST 6 ROWS ONLY";
        ArrayList<QuestionDTO> dtos = new ArrayList<>();
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, questionContent);
            preStm.setString(2, subjectId);
            preStm.setBoolean(3, status);
            preStm.setInt(4, (index - 1) * 6);
            try (ResultSet rs = preStm.executeQuery()) {
                while (rs.next()) {
                    QuestionDTO dto = new QuestionDTO();
                    dto.setQuestionId(rs.getString("questionId"));
                    dto.setQuestionContent(rs.getString("questionContent"));
                    dto.setAnsA(rs.getString("ansA"));
                    dto.setAnsB(rs.getString("ansB"));
                    dto.setAnsC(rs.getString("ansC"));
                    dto.setAnsD(rs.getString("ansD"));
                    dto.setCorrectAns(rs.getString("correctAns"));
                    dto.setCreateDate(rs.getDate("createDate"));
                    dto.setSubjectId(rs.getString("subjectId"));
                    dto.setSubjectName(rs.getString("subjectName"));
                    dto.setStatus(rs.getBoolean("status"));
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }

    public int getNumberPageAllStatus(String questionContent, String subjectId) throws Exception {
        MyConnection mcn = new MyConnection();
        int countPage = 0;
        String sql = "select COUNT(*)\n"
                + "from tblQuestion\n"
                + "where questionContent like ? and subjectId like ? ";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, questionContent);
            preStm.setString(2, subjectId);
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

    public ArrayList<QuestionDTO> getPagingAllStatus(int index, String questionContent, String subjectId) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "Select  questionId,questionContent,ansA,ansB,ansC,ansD,correctAns,createDate,q.subjectId, subjectName,status\n"
                + "from tblQuestion AS q, tblSubject AS s \n"
                + "where q.subjectId=s.subjectId and questionContent like ? and q.subjectId like ?\n"
                + "order by questionContent\n"
                + "OFFSET ? ROWS\n"
                + "FETCH FIRST 6 ROWS ONLY";
        ArrayList<QuestionDTO> dtos = new ArrayList<>();
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, questionContent);
            preStm.setString(2, subjectId);
            preStm.setInt(3, (index - 1) * 6);
            try (ResultSet rs = preStm.executeQuery()) {
                while (rs.next()) {
                    QuestionDTO dto = new QuestionDTO();
                    dto.setQuestionId(rs.getString("questionId"));
                    dto.setQuestionContent(rs.getString("questionContent"));
                    dto.setAnsA(rs.getString("ansA"));
                    dto.setAnsB(rs.getString("ansB"));
                    dto.setAnsC(rs.getString("ansC"));
                    dto.setAnsD(rs.getString("ansD"));
                    dto.setCorrectAns(rs.getString("correctAns"));
                    dto.setCreateDate(rs.getDate("createDate"));
                    dto.setSubjectId(rs.getString("subjectId"));
                    dto.setSubjectName(rs.getString("subjectName"));
                    dto.setStatus(rs.getBoolean("status"));
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }

    public ArrayList<QuestionDTO> getRandomListQuestion(int numOfQues,String subjectId) throws Exception {
        MyConnection mcn = new MyConnection();
        ArrayList<QuestionDTO> dtos = new ArrayList<>();
        String sql = "select top (?) percent questionId,questionContent,ansA,ansB,ansC,ansD,correctAns,createDate,subjectId,status \n"
                + "from tblQuestion \n"
                + "where status=1 and subjectId=?\n"
                + "order by newid()";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setInt(1, numOfQues);
            preStm.setString(2, subjectId);
            try (ResultSet rs = preStm.executeQuery()) {
                while (rs.next()) {
                    QuestionDTO dto = new QuestionDTO();
                    dto.setQuestionId(rs.getString("questionId"));
                    dto.setQuestionContent(rs.getString("questionContent"));
                    dto.setAnsA(rs.getString("ansA"));
                    dto.setAnsB(rs.getString("ansB"));
                    dto.setAnsC(rs.getString("ansC"));
                    dto.setAnsD(rs.getString("ansD"));
                    dto.setCorrectAns(rs.getString("correctAns"));
                    dto.setCreateDate(rs.getDate("createDate"));
                    dto.setSubjectId(rs.getString("subjectId"));
                    dto.setStatus(rs.getBoolean("status"));
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }
}
