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






@WebServlet("/Register")
/**
 *
 * @author piku
 */
public class Register extends HttpServlet {

   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
                PrintWriter out = response.getWriter();
              String name = request.getParameter("name");
              String email = request.getParameter("email");
              String password = request.getParameter("password");
              String last_name = request.getParameter("last_name");
              String secret_answer = request.getParameter("secret_answer");
            
              
           
              
              
           
              // out.println(FName+" "+MName+" "+LName+" "+FAName+" "+MOName+" "+dob+" "+add+" "+city+" "+state+" "+pinno+" "+adhaar+" "+email+" "+Fphone+" "+Mphone+" "+Sphone+" "+Major);
            //  out.println();

    try{
        Class.forName("com.mysql.jdbc.Driver"); 
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/auth","root","root"); 
        Statement statement= conn.createStatement();
         //   out.println("<h1> Connected to Mysql </h1>");
    String Query2 = "insert into user values ('"+name+"','"+email+"','"+last_name+"','"+password+"')";
                                                   // out.println(Query2);  
                                                     statement = conn.createStatement();
                                                      statement.executeUpdate(Query2);
                                                    
                                                      out.println("<head><meta http-equiv='refresh' content='1; URL=http://localhost:8080/Cloud/index.html\'><meta name='keywords' content='automatic redirection'></head>");
    }
    catch(Exception e)
    {
        out.println(e.getMessage());
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    

    }

    
