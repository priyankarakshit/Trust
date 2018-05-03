/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.*; 

@WebServlet("/FileDecrypt")
@MultipartConfig

public class FileDecrypt extends HttpServlet {

    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                PrintWriter out = response.getWriter();
                String key = request.getParameter("Key");
                String FileName = request.getParameter("FileName");
		
		
		//get the InputStream to store the file somewhere
	    
	    
	    //for example, you can copy the uploaded file to the server
	    //note that you probably don't want to do this in real life!
	    //upload it to a file host like S3 or GCS instead
	   
                
                String FilePath = "E:\\" + FileName;
                             AESFileDecryption a;
        try{
        a = new AESFileDecryption();
        
       a.AESFileD(FilePath,key);
       out.print("<h1>DECRYPT succesful<h1>");
        }
        catch(Exception e){
        
        }
                
        //out.print("<head><meta http-equiv='refresh' content='1; URL=http://localhost:8080/Cloud/plainfile_decrypted.txt'><meta name='keywords' content='automatic redirection'></head>");
		//get the URL of the uploaded file
		//String fileUrl = "http://localhost:8080/uploaded-files/plainfile_decrypted.txt";
		
		//You can get other form data too
		//String name = request.getParameter("name");
		
		//create output HTML that uses the 
	/*	response.getOutputStream().println("<p>Thanks " + name + "! Here's a link to your uploaded file:</p>");
		response.getOutputStream().println("<p><a href=\"" + fileUrl + "\">" + fileUrl + "</a></p>");
		response.getOutputStream().println("<p>Upload another file <a href=\"http://localhost:8080/index.html\">here</a>.</p>");*/	
	

    }
}

