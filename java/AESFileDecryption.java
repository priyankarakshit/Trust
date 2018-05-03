/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piku
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESFileDecryption {
	public static void AESFileD(String FilePath, String password) throws Exception {

		

		// reading the salt
		// user should have secure mechanism to transfer the
		// salt, iv and password to the recipient
		FileInputStream saltFis = new FileInputStream("E:\\salt.enc");
		byte[] salt = new byte[8];
		saltFis.read(salt);
		saltFis.close();

		// reading the iv
		FileInputStream ivFis = new FileInputStream("E:\\iv.enc");
		byte[] iv = new byte[16];
		ivFis.read(iv);
		ivFis.close();

		SecretKeyFactory factory = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1");
		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536,
				256);
		SecretKey tmp = factory.generateSecret(keySpec);
		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

		// file decryption
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
		FileInputStream fis = new FileInputStream(FilePath);
		FileOutputStream fos = new FileOutputStream("E:\\plainfile_decrypted.txt");
		byte[] in = new byte[64];
		int read;
		while ((read = fis.read(in)) != -1) {
			byte[] output = cipher.update(in, 0, read);
			if (output != null)
				fos.write(output);
		}

		byte[] output = cipher.doFinal();
		if (output != null)
			fos.write(output);
		fis.close();
		fos.flush();
		fos.close();
                File file = new File(FilePath);
                file.delete();
               


		System.out.println("File Decrypted.");
	}
}