package model.Global;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

public class GlobalService {

	public static final int RECORDS_PER_PAGE = 2;
	public static final String SYSTEM_NAME = "�d�l�ʪ���";
	public static final int IMAGE_FILENAME_LENGTH = 20;
	public static final String JNDI_DB_NAME = "java:comp/env/jdbc/KarpyDataSQL";
	public static final String KEY = "KittySnoopyGarfieldMicky"; // 16, 24, 32
	public static final int ORDER_AMOUNT_LIMIT = 10000;
	public String getSystemName() { // systemName  ${SYSTEM.systemName}
		return SYSTEM_NAME;
	}

	public static String getMD5Endocing(String message) {
		final StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(message.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; ++i) {
				final byte b = digest[i];
				final int value = Byte.toUnsignedInt(b);
				buffer.append(value < 16 ? "0" : "");
				buffer.append(Integer.toHexString(value));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}
	
	public static String getSHA1Endocing(String message) {
		final StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(message.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; ++i) {
				final byte b = digest[i];
				final int value = Byte.toUnsignedInt(b);
				buffer.append(value < 16 ? "0" : "");
				buffer.append(Integer.toHexString(value));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}

	/**
	 * ��MD5�t��k�N�ɮ�file�ഫ��128�줸(16�Ӧ줸��)����ơC
	 * 
	 * @param message
	 *            : �n�[�K���r��
	 * @return : 128�줸��ƪ�16�i���ܪk�Һc�����r��
	 */
	public static String getMD5Endocing(File file) throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		try (FileInputStream fis = new FileInputStream(file);) {
			byte[] ba = new byte[1024];
			int len = 0;
			while ((len = fis.read(ba)) != -1) {
				md.update(ba, 0, len);
			}
		}
		byte[] digest = md.digest();
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < digest.length; ++i) {
			byte b = digest[i];
			final int value = Byte.toUnsignedInt(b);
			buffer.append(value < 16 ? "0" : "");
			buffer.append(Integer.toHexString(value));

		}
		return buffer.toString();
	}
	
	
	public static String getSHA1Endocing(File file) throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		try (FileInputStream fis = new FileInputStream(file);) {
			byte[] ba = new byte[1024];
			int len = 0;
			while ((len = fis.read(ba)) != -1) {
				md.update(ba, 0, len);
			}
		}
		byte[] digest = md.digest();
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < digest.length; ++i) {
			byte b = digest[i];
//			int value = (b & 0x7F) + (b < 0 ? 128 : 0);
			final int value = Byte.toUnsignedInt(b);
			buffer.append(value < 16 ? "0" : "");
			buffer.append(Integer.toHexString(value));

		}
		return buffer.toString();
	}
	//d0ea71ac4fd61c62e393d493fff442f83c4f780e
	//d0ea71ac4fd61c62e393d493fff442f83c4f780e
	// ���F���ե����O����L��k�ӷǳƪ�main()��k�C
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\user\\Downloads\\apache-tomcat-8.0.52-windows-x64.zip");
		//
		String t1 = getSHA1Endocing(file);
		System.out.println("SHA1:" + t1);
	}

	// ����k�վ�fileName�����פp��ε���maxLength�C
	// �p�GfileName�����פp��ε���maxLength�A�����Ǧ^fileName
	// �_�h�O�d�̫�@�ӥy�I�P��᪺���ɦW�A�Y�u�D�ɦW�ϱofileName���`����
	// ����maxLength�C
	
	/* �ȵ���
	public static String adjustFileName(String fileName, int maxLength) {
		int length = fileName.length();
		if (length <= maxLength) {
			return fileName;
		}
		int n = fileName.lastIndexOf(".");
		int sub = fileName.length() - n - 1;
		fileName = fileName.substring(0, maxLength - 1 - sub) + "." + fileName.substring(n + 1);
		return fileName;
	}

	public static String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
	*/
	
	// ����k�i�˵��W�Ǹ�ƪ��C�����P�C���ɮסA
	
	/* �ȵ��� 
	public static void exploreParts(Collection<Part> parts, HttpServletRequest req) {
		try {
			System.out.println("=============================");
			for (Part part : parts) {
				String name = part.getName();
				String contentType = part.getContentType();
				String value = "";
				long size = part.getSize(); // �W�Ǹ�ƪ��j�p�A�Y�W�Ǹ�ƪ��줸�ռ�
				//InputStream is = part.getInputStream();
				if (contentType != null) { // ��ܸ�part���ɮ�
					// ���X�W���ɮת��ɦW
					String filename = GlobalService.getFileName(part);
					// �N�W�Ǫ��ɮ׼g�J��location�ݩʩҫ��w����Ƨ�
					if (filename != null && filename.trim().length() > 0) {
						part.write(filename);
						System.out.println(part.getClass().getName());
					}
				} else { // ��ܸ�part���@�몺���
					// �N�W�Ǫ�����Ƽg�J��location�ݩʩҫ��w����Ƨ��A�ɦW��"part_"+ name
					part.write("part_" + name);
					value = req.getParameter(name);
				}
				System.out.printf("%-50s %-15s %8d  %-20s \n", name, contentType, size, value);
			}
			System.out.println("=============================");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/

	/**
	 * ����k�i��r��message(Plaintext, ����)�[�K�A�M��N�[�K�᪺�r�� (Ciphertext, �K��)�Ǧ^�C
	 * 
	 * @param key
	 *            : �[�K���_
	 * @param message
	 *            : ����A�Y�n�[�K���r��
	 * @return �[�K�᪺
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Throwable
	 */
	public static String encryptString(String message) {
		// DES : Data Encryption Standard, �@�ع�٦��[�K�t��k�C
		// �����p���F����1976�~�w���p����ƳB�z�з�(FIPS)�A����
		// ���_�h�����O7�Ӧ줸�աB�[�K�϶�(Cipher Block)�T�w��8�Ӧ줸�աC
		// DES�ثe�w�Q�����O�@�ؤ��w�����t��k�C
		// AES : Advanced Encryption Standard, �@�ع�٦��[�K�t��k�C
		// (�����p���F����2001�~�ǤJFIPS 140-2�з�)�A���غt��k
		// ��Cipher Block�T�w��16�Ӧ줸�աC���_�h�����O16�Ӧ줸�աB
		// 24�Ӧ줸�թ�32�Ӧ줸��(�Y128�Ӧ줸�B192�Ӧ줸��256�Ӧ줸)�C
		// ECB : Electronic CookBook, �@�ظ�ƪ��[�K�覡�A�o�إ[�K�覡�Ĩ�
		// �C�Ӱ϶�(�p8�ө�16�Ӧ줸��)�W�ߥ[�K�A�Y�[�K�����϶��ɻP�䥦�϶�
		// �L���C�W�����Y���u�I�]�����I�C
		// �u�I���i�H�Ѧh�ӳB�z���ӥ���B�z���ӫܤj����ơC���I���p�G���
		// �����e�����ƥX�{�������A�ӥB���Ƹ�ƪ����׭�n�P�[�K�϶������A
		// �h�o�ǭ��ƥX�{�������g�L�[�K��|�X�{�ۦP�����G�C
		// PKCS5Padding: �p�G�n�[�K����Ƥ��O8��(�pDES�[�K�t��k)��
		// 16��(�pAES�[�K�t��k)�줸�ժ���ƭ��A�h�����b���[�K��ƪ�
		// ���ݥ[�J�Y�z�Ӧ줸�ըӴꦨ��ƭ��CPKCS5Padding�O�@��
		// �ɨ������줸�ժ���k�C
		String encryptedString = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encryptedString = DatatypeConverter.printBase64Binary(cipher.doFinal(message.getBytes()));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	/**
	 * ����k�i��[�K���r��(Ciphertext)�ѱK�Akey�����[�K�ɪ����_ �Ǧ^�Ȭ��ѱK�᪺�r��(Plaintext)
	 * 
	 */
	public static String decryptString(String key, String stringToDecrypt) {
		String decryptedString = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] b = DatatypeConverter.parseBase64Binary(stringToDecrypt);
			decryptedString = new String(cipher.doFinal(b));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return decryptedString;
	}
}