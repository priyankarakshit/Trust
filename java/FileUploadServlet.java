/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.nio.file.Files;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import javax.servlet.annotation.*; 
import java.math.BigInteger;
import javax.servlet.http.HttpSession;
import java.nio.ByteBuffer;
import javax.crypto.BadPaddingException;
import org.apache.commons.codec.binary.Base64;

@WebServlet("/FileUploadServlet")
@MultipartConfig

public class FileUploadServlet extends HttpServlet {

    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                String key = request.getParameter("Key");
                String email = request.getParameter("email");
		Part filePart = request.getPart("fileToUpload");
                PrintWriter out = response.getWriter();
                String puMod ="";
                String puExp ="";
                String prMod ="";
                String prExp ="";
                byte[] Encdata=null;
                String Dedata="";
                byte[] uEncdata=null;
                String cspemail="csp@gmail.com";
                 String upuMod ="";
                String upuExp ="";
                
                HttpSession session1 = request.getSession();
		session1.setAttribute("key", key);
		//get the InputStream to store the file somewhere
	    InputStream fileInputStream = filePart.getInputStream();
	    
	    //for example, you can copy the uploaded file to the server
	    //note that you probably don't want to do this in real life!
	    //upload it to a file host like S3 or GCS instead
	    File fileToSave = new File("E:\\" + filePart.getSubmittedFileName());
		Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                String FileName = filePart.getSubmittedFileName();
                String FilePath = "E:\\" + FileName;
                             AESFileEncryption a;
        try{
        a = new AESFileEncryption();
        
       a.AESFile(FilePath,key);
       out.print("<h1>succesful<h1>");
        }
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

            ps.setString(1,cspemail);
            ResultSet rs=ps.executeQuery();
            
            
            if(rs.next()){
                puMod=rs.getString(1);
                puExp=rs.getString(2);
            }
        }     
	catch(Exception e){
            
        }	
        RSAEncrytion r;
        try{
            BigInteger rpuMod = new BigInteger(puMod);
            BigInteger rpuExp = new BigInteger(puExp);
            r = new RSAEncrytion();
            Encdata=r.enc(key,rpuMod,rpuExp);
           
        }
	catch(Exception e){
            
        }
        out.println("<br>The Data Recieved at Cloud Service Provider : "+Encdata);
        try{
             Class.forName("com.mysql.jdbc.Driver");

//creating connection with the database
      Connection  con=DriverManager.getConnection
                ("jdbc:mysql://localhost/auth","root","root");
              
    PreparedStatement ps1=con.prepareStatement
              ("select prmod, prExp from ky where email=?");

            ps1.setString(1,cspemail);
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
        }
	catch(Exception e){
            
        }
        out.println("<br> Data retreived out of it : "+Dedata);
          try{
            //loading drivers for mysql
    Class.forName("com.mysql.jdbc.Driver");

//creating connection with the database
      Connection  con=DriverManager.getConnection
                ("jdbc:mysql://localhost/auth","root","root");
              
    PreparedStatement ps2=con.prepareStatement
              ("select pubmod, pubExp from ky where email=?");

            ps2.setString(1,email);
            ResultSet rs2=ps2.executeQuery();
            
            
            if(rs2.next()){
                upuMod=rs2.getString(1);
                upuExp=rs2.getString(2);
            }
        }     
	catch(Exception e){
            
        }	
        RSAEncrytion r1;
        try{
            BigInteger rupuMod = new BigInteger(upuMod);
            BigInteger rupuExp = new BigInteger(upuExp);
            r1 = new RSAEncrytion();
            uEncdata=r1.enc(Dedata,rupuMod,rupuExp);
             session1.setAttribute("uEncdata", uEncdata);
             
        }
	catch(Exception e){
            
        }
         try{
            //loading drivers for mysql
    Class.forName("com.mysql.jdbc.Driver");

//creating connection with the database
      Connection  con=DriverManager.getConnection
                ("jdbc:mysql://localhost/auth","root","root");
             Blob blob = new javax.sql.rowset.serial.SerialBlob(uEncdata);
             String s=new Base64().encodeToString(uEncdata);
             PreparedStatement ps4=con.prepareStatement("select * from bob where email=?");
             ps4.setString(1,email);
            ResultSet rss = ps4.executeQuery();
            if(rss.next()){
                 PreparedStatement ps3=con.prepareStatement
              ("update bob SET enc=? where email =?");

           
            ps3.setString(1,s);
            ps3.setString(2,email);
            ps3.executeQuery();
            
      }
      else {
       PreparedStatement ps5=con.prepareStatement
              ("insert into bob values(?,?)");

           String w="";
            ps5.setString(1,email);
            ps5.setString(2,w);
            ps5.executeQuery();
      
      }
           
        }     
	catch(Exception e){
            
        }
        out.println("<br> Cloud service provider encoded according to the user access"+uEncdata+"<br>");
        
        
        
                //get the URL of the uploaded file
		//String fileUrl = "http://localhost:8080/uploaded-files/" + filePart.getSubmittedFileName();
		
		//You can get other form data too
		//String name = request.getParameter("name");
		
		//create output HTML that uses the 
	/*	response.getOutputStream().println("<p>Thanks " + name + "! Here's a link to your uploaded file:</p>");
		response.getOutputStream().println("<p><a href=\"" + fileUrl + "\">" + fileUrl + "</a></p>");
		response.getOutputStream().println("<p>Upload another file <a href=\"http://localhost:8080/index.html\">here</a>.</p>");*/	
	

    }
}

