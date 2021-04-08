/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.connection;

import java.io.Serializable;
import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author DELL
 */
public class MyConnection implements Serializable {

    public Connection makeConnection() throws Exception {
        Context ctx = new InitialContext();
        Context envCtx = (Context) ctx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("DBCon");
        Connection con = ds.getConnection();
        return con;
    }
}
