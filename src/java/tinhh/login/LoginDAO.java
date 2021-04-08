/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.login;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import tinhh.connection.MyConnection;

/**
 *
 * @author DELL
 */
public class LoginDAO implements Serializable {

    public LoginDTO checkLogin(String email, String password) throws Exception {
        MyConnection mcn = new MyConnection();
        String sql = "select email,password,fullName,sex,phoneNumber,role,status\n"
                + "from tblAccount\n"
                + "where email=? and password=? and status = 1";
        LoginDTO dto = new LoginDTO();
        try (Connection conn = mcn.makeConnection();
                PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, email);
            preStm.setString(2, password);
            try (ResultSet rs = preStm.executeQuery()) {
                if (rs.next()) {
                    dto.setFullName(rs.getString("fullName"));
                    dto.setSex(rs.getBoolean("sex"));
                    dto.setPhoneNumber(rs.getString("phoneNumber"));
                    dto.setRole(rs.getString("role"));
                    dto.setStatus(true);
                    dto.setEmail(email);
                    dto.setPassword(password);
                } else {
                    dto.setRole("invalid");
                }
            }
        }
        return dto;
    }
}
