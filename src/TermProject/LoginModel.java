package com.Dbms.Struts2.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.opensymphony.xwork2.ActionSupport;

public class LoginModel extends ActionSupport {

   private String user;
   private String password;
   private int Id;

   public String execute() {
      String ret = ERROR;
      Connection conn = null;

      try {
         String URL = "jdbc:mysql://localhost/final_project";
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(URL, "mgadgil09", "mgadgil09");
         String sql = "SELECT * FROM users WHERE";
         sql+=" Email = ? AND Password = ?";
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, user);
         ps.setString(2, password);
         ResultSet rs = ps.executeQuery();
         while (rs.next()) {
           	 Id = rs.getInt("User_Id");
             ret = SUCCESS;
         }
      } catch (Exception e) {
         ret = ERROR;
      } finally {
         if (conn != null) {
            try {
               conn.close();
            } catch (Exception e) {
            }
         }
      }
      return ret;
   }

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public int getId() {
      return Id;
   }

   public void setId(int id) {
      this.Id = id;
   }
}