/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.net.*;
import java.io.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author piku
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
         String name = request.getParameter("name");
              String lemail = request.getParameter("lemail");
              String lpassword = request.getParameter("lpassword");
              //Session s = new Session();
              HttpSession session = request.getSession(); 
              
              try{

    //loading drivers for mysql
    Class.forName("com.mysql.jdbc.Driver");

//creating connection with the database
      Connection  con=DriverManager.getConnection
                ("jdbc:mysql://localhost/auth","root","root");

    PreparedStatement ps=con.prepareStatement
              ("select * from user where email=? and passcode=? ");

    ps.setString(1,lemail);
    ps.setString(2,lpassword);
   





    ResultSet rs=ps.executeQuery();

     if(rs.next()) {
    	 
    	 session.setAttribute("lemail", lemail);
         
    	 out.print("<head><meta http-equiv='refresh' content='1; URL=http://localhost:8080/Cloud/Selectoption.html\'><meta name='keywords' content='automatic redirection'></head>");
    	 
    	 
     }
     else {
    	 out.print("<head><meta http-equiv='refresh' content='1; URL=http://localhost:8080/Cloud/index.html\'><meta name='keywords' content='automatic redirection'></head>");
         out.print("Login Failure");
     }
    }
    catch(Exception se)
    {
        se.printStackTrace();
    }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 

}
