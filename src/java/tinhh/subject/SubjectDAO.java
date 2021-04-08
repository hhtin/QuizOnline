/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.subject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import tinhh.connection.MyConnection;

/**
 *
 * @author DELL
 */
public class SubjectDAO implements Serializable {

    public ArrayList<SubjectDTO> getSubjectList() throws Exception {
        MyConnection mcn = new MyConnection();
        ArrayList<SubjectDTO> dtos=new ArrayList<>();
        String sql = "Select subjectId,subjectName\n"
                + "from tblSubject";
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            try (ResultSet rs = preStm.executeQuery()) {
                while (rs.next()) {
                    SubjectDTO dto=new SubjectDTO();
                    dto.setSubjectId(rs.getString("subjectId"));
                    dto.setSubjectName(rs.getString("subjectName"));
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }
}
