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
import java.math.BigInteger;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.codec.binary.Base64;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.crypto.BadPaddingException;
/**
 *
 * @author piku
 */
@WebServlet("/DK")
public class DK extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String prMod ="";
                String prExp ="";
                byte[] Encdata=null;
                String Dedata="";
                PrintWriter out = response.getWriter();
                HttpSession session = request.getSession(); 
                String email=(String)session.getAttribute("lemail");
                String key=(String)session.getAttribute("key");
                String upuMod ="";
                String upuExp ="";
                int i=0;
                  
                byte[] uEncdata=null;
                           try{
            //loading drivers for mysql
    Class.forName("com.mysql.jdbc.Driver");

//creating connection with the database
      Connection  con=DriverManager.getConnection
                ("jdbc:mysql://localhost/auth","root","root");
   
    PreparedStatement ps=con.prepareStatement
              ("select enc from bob where email=?");

            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            
            
            if(rs.next()){
               String s=rs.getString(1);
                i=1;
            }
            else{
            i=0;
            out.println("<head><meta http-equiv='refresh' content='2; URL=http://localhost:8080/Cloud/Selectoption.html\'><meta name='keywords' content='automatic redirection'></head><h1>You not have Key<h1>");
            }        }     
	catch(Exception e){
            
        }
                
          try{
            //loading drivers for mysql
    Class.forName("com.mysql.jdbc.Driver");

//creating connection with the database
      Connection  con=DriverManager.getConnection
                ("jdbc:mysql://localhost/auth","root","root");
   
    PreparedStatement ps=con.prepareStatement
              ("select pubmod, pubExp from ky where email=?");

            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            
            
            if(rs.next()){
                upuMod=rs.getString(1);
                upuExp=rs.getString(2);
            }
        }     
	catch(Exception e){
            
        }	
        RSAEncrytion r1;
        try{
            BigInteger rupuMod = new BigInteger(upuMod);
            BigInteger rupuExp = new BigInteger(upuExp);
            r1 = new RSAEncrytion();
            Encdata=r1.enc(key,rupuMod,rupuExp);
             //out.println(Encdata);
             
        }
	catch(Exception e){
            
        }
        try{
             Class.forName("com.mysql.jdbc.Driver");

//creating connection with the database
      Connection  con=DriverManager.getConnection
                ("jdbc:mysql://localhost/auth","root","root");
    PreparedStatement ps1=con.prepareStatement
              ("select prmod, prExp from ky where email=?");
              
              
            ps1.setString(1,email);
            ResultSet rs1=ps1.executeQuery();
            
            
            if(rs1.next()){
                prMod=rs1.getString(1);
                prExp=rs1.getString(2);
            }
        }
        catch(Exception e){
            
        }
         RSADecrytion rd;
        try{
            BigInteger rprMod = new BigInteger(prMod);
            BigInteger rprExp = new BigInteger(prExp);
            rd = new RSADecrytion();
            Dedata=rd.dec(Encdata,rprMod,rprExp);
             //out.println(Dedata);
        }
	catch(Exception e){
            
        }
        if(i==1){
        out.println("<head><meta http-equiv='refresh' content='5; URL=http://localhost:8080/Cloud/Decrypt.html\'><meta name='keywords' content='automatic redirection'></head>");
        out.println(Dedata);
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
