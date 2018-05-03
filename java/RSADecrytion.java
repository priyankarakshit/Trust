import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
public class RSADecrytion {
    public static String dec(byte [] data, BigInteger prMod, BigInteger prExp) throws IOException {
		Base64.Encoder encoder = Base64.getEncoder();
                Base64.Decoder decoder = Base64.getDecoder();
                     byte[] tode= data ;
                     
                    
                    String sdecryptedData="";
		try {
			
                        //Pullingout parameters which makes up Key
			
                        
//                        BigInteger prMod = rsaPrivKeySpec.getModulus();
//                        BigInteger prExp = rsaPrivKeySpec.getPrivateExponent();
//			String spuMod = puMod.toString();
//                        String spuExp = puExp.toString();
//                        String sprMod = prMod.toString();
//                        String sprExp = prExp.toString();
                        System.out.println("\n"+prMod+"\n"+prExp+"\n");
                        
//                        BigInteger rpuMod = new BigInteger(spuMod);
//                        BigInteger rpuExp = new BigInteger(spuExp);
//                        KeyFactory keyFactory1 = KeyFactory.getInstance("RSA");
//			RSAPublicKeySpec rsaPubKeySpec1 = new RSAPublicKeySpec(rpuMod, rpuExp);
//                        PublicKey pk = keyFactory1.generatePublic(rsaPubKeySpec1);
//                        System.out.println("\nPublic Key regenerated - " + pk+"\n");
                        
//                         BigInteger rprMod = new BigInteger(sprMod);
//                        BigInteger rprExp = new BigInteger(sprExp);
                        KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");
			RSAPrivateKeySpec rsaPrivateKeySpec1 = new RSAPrivateKeySpec(prMod, prExp);
                        PrivateKey prk = keyFactory2.generatePrivate(rsaPrivateKeySpec1);
                        System.out.println("\nPrivate Key regenerated - " + prk+"\n");
                        //int length = sprMod.length();
			//System.out.println("\nPrivate Key regenerated length - " +length+"\n");
			
			//Share public key with other so they can encrypt data and decrypt thoses using private key(Don't share with Other
			RSADecrytion rsaObj = new RSADecrytion();
			
			
			//Encrypt Data using Public Key
//			byte[] encryptedData = rsaObj.encryptData(toen,pk);
//			sencryptedData = encryptedData.toString();
			//Descypt Data using Private Key
                        
			sdecryptedData=rsaObj.decryptData(tode,prk);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
                
               return sdecryptedData;
	}
	
	
//	public byte[] encryptData(String data, PublicKey publicKey) throws IOException {
//		System.out.println("\n----------------ENCRYPTION STARTED------------");
//		
//		System.out.println("Data Before Encryption :" + data);
//		byte[] dataToEncrypt = data.getBytes();
//		byte[] encryptedData = null;
//		try {
//			PublicKey pubKey = publicKey;
//			Cipher cipher = Cipher.getInstance("RSA");
//			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
//			encryptedData = cipher.doFinal(dataToEncrypt);
//			System.out.println("Encryted Data: " + encryptedData);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//		
//		System.out.println("----------------ENCRYPTION COMPLETED------------");		
//		return encryptedData;
//	}

	/**
	 * Encrypt Data
	 * @param data
	 * @throws IOException
	 */
	public String decryptData(byte[] data, PrivateKey privateKey) throws IOException {
		System.out.println("\n----------------DECRYPTION STARTED------------");
		byte[] descryptedData = null;
		String sdescryptedData="";
		try {
			PrivateKey privateKey1 = privateKey;
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey1);
			descryptedData = cipher.doFinal(data);
                         sdescryptedData = new String(descryptedData);
			//System.out.println("Decrypted Data: " + new String(descryptedData));
                }
                catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("----------------DECRYPTION COMPLETED------------");
                return sdescryptedData;
	}
	
}
