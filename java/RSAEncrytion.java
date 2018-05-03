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
public class RSAEncrytion {
    public static byte[] enc(String key, BigInteger puMod, BigInteger puExp) throws IOException {
		Base64.Encoder encoder = Base64.getEncoder();
                Base64.Decoder decoder = Base64.getDecoder();
                    String toen = key;
                    byte[] encryptedData=null;
		try {
			
                        //Pullingout parameters which makes up Key
			
                        
//                        BigInteger prMod = rsaPrivKeySpec.getModulus();
//                        BigInteger prExp = rsaPrivKeySpec.getPrivateExponent();
//			String spuMod = puMod.toString();
//                        String spuExp = puExp.toString();
//                        String sprMod = prMod.toString();
//                        String sprExp = prExp.toString();
                        System.out.println("\n"+puMod+"\n"+puExp+"\n");
                        
//                        BigInteger rpuMod = new BigInteger(spuMod);
//                        BigInteger rpuExp = new BigInteger(spuExp);
                        KeyFactory keyFactory1 = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec rsaPubKeySpec1 = new RSAPublicKeySpec(puMod, puExp);
                        PublicKey pk = keyFactory1.generatePublic(rsaPubKeySpec1);
                        System.out.println("\nPublic Key regenerated - " + pk+"\n");
                        
//                         BigInteger rprMod = new BigInteger(sprMod);
//                        BigInteger rprExp = new BigInteger(sprExp);
//                        KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");
//			RSAPrivateKeySpec rsaPrivateKeySpec1 = new RSAPrivateKeySpec(rprMod, rprExp);
//                        PrivateKey prk = keyFactory2.generatePrivate(rsaPrivateKeySpec1);
//                        System.out.println("\nPrivate Key regenerated - " + prk+"\n");
//                        int length = sprMod.length();
//			System.out.println("\nPrivate Key regenerated length - " +length+"\n");
			
			//Share public key with other so they can encrypt data and decrypt thoses using private key(Don't share with Other
			RSAEncrytion rsaObj = new RSAEncrytion();
			
			
			//Encrypt Data using Public Key
			encryptedData = rsaObj.encryptData(toen,pk);
			//sencryptedData = encryptedData.toString();
			//Descypt Data using Private Key
			//rsaObj.decryptData(encryptedData,prk);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
                
               return encryptedData;
	}
	
	
	public byte[] encryptData(String data, PublicKey publicKey) throws IOException {
		System.out.println("\n----------------ENCRYPTION STARTED------------");
		
		System.out.println("Data Before Encryption :" + data);
		byte[] dataToEncrypt = data.getBytes();
		byte[] encryptedData = null;
		try {
			PublicKey pubKey = publicKey;
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			encryptedData = cipher.doFinal(dataToEncrypt);
			System.out.println("Encryted Data: " + encryptedData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		System.out.println("----------------ENCRYPTION COMPLETED------------");		
		return encryptedData;
	}

	/**
	 * Encrypt Data
	 * @param data
	 * @throws IOException
	 */
	private void decryptData(byte[] data, PrivateKey privateKey) throws IOException {
		System.out.println("\n----------------DECRYPTION STARTED------------");
		byte[] descryptedData = null;
		
		try {
			PrivateKey privateKey1 = privateKey;
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey1);
			descryptedData = cipher.doFinal(data);
			System.out.println("Decrypted Data: " + new String(descryptedData));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		System.out.println("----------------DECRYPTION COMPLETED------------");		
	}
	
}
