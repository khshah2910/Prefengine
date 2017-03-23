package com.prefengine.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.prefengine.service.ServiceException;

import com.prefengine.dao.DbDAO;
import com.prefengine.dao.UserDAO;
import com.prefengine.domain.User;

public class UserService {
	private DbDAO dbDao;
	private UserDAO userDb;
	public static final Random RANDOM = new SecureRandom();
	public static final int ITERATIONS = 40000;
	public static final int KEY_LENGTH = 256;
	/**
	 * constructs a user service provider 
	 * @param dbDao
	 * @param userDao
	 */
	public UserService(DbDAO dbDao, UserDAO userDao) { //add every other DAO used by the user service
		this.dbDao = dbDao;	
		this.userDb = userDao;
	}
	//TODO test only. Delete after usage
	public static void main(String[] args) throws ServiceException, SQLException{
		DbDAO db = new DbDAO("jdbc:mysql://localhost:3306/prefengine", "root", "");
		UserDAO userDao = new UserDAO(db);
		UserService usrsrv = new UserService(db, userDao);
		User usr = usrsrv.checkLogin("JSmith", "test1");
		SDF.printline(usr.getEmail_address());
		//SDF.printline(usr);
		//comment out when done
		//DbDAO.checkDb("jdbc:mysql://localhost:3306/time_machine", "root", "");
		//Test hashing algorith
		/**byte[] salt = getNextSalt();
			
		char[] word = "test1".toCharArray();
		byte[] hash = hashPassword(word, salt);
			

		Base64.Encoder enc = Base64.getEncoder();
		Base64.Decoder dec = Base64.getDecoder();

		System.out.printf("salt: %s%n", new String(salt));
		System.out.printf("salt: %s%n", enc.encodeToString(salt));
		System.out.printf("hash: %s%n", enc.encodeToString(hash));
		System.out.printf("salt decode: %s%n", new String(dec.decode(enc.encodeToString(salt))));

		System.out.println(new String(dec.decode(enc.encodeToString(salt))));
		byte[] hash2 = hashPassword(word, dec.decode(enc.encodeToString(salt)));

		System.out.printf("hash2: %s%n", enc.encodeToString(hash2));
		System.out.printf("String: %s%n", compareHashedPasswords(enc.encodeToString(hash), enc.encodeToString(hash2)));
		**/
	}
	/**
	 * Check user login
	 * @param userName
	 * @param password
	 * @return User if username exists and password match, otherwise return null
	 * @throws ServiceException
	 */
	public User checkLogin(String userName, String password) throws ServiceException {
		User usr = null;
		try {
			if(userDb.checkLogin(userName,password))
				usr = userDb.findUserByEmail(userName);
		} catch (SQLException e){
			throw new ServiceException("Check login error: ", e);
		}
		return usr;
	}
	
	/**
	 * Hashes a password using it's salt
	 * @param password
	 * @param salt
	 * @param iterations
	 * @param keyLength
	 * @return a byte array of the hashed password
	 */
	public static byte[] hashPassword( final char[] password, final byte[] salt){
        PBEKeySpec spec = new PBEKeySpec( password, salt, ITERATIONS, KEY_LENGTH );
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
	        SecretKey key = skf.generateSecret( spec );
	        byte[] hash = key.getEncoded( );
	        return hash;
		}catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	        throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
	    } finally {
	        spec.clearPassword();
	    }
	}
	
	/**
	   * This returns a unique salt which is used for hashing a password.
	   * @return a 20 bytes random salt
	   */
	public static byte[] getNextSalt() {
		byte[] salt = new byte[20];
	    RANDOM.nextBytes(salt);
	    return salt;
	  }
	/**
	 * This compares two hashed passwords and returns true or false
	 * @param storedPassword
	 * @param submittedPassword
	 * @return true or false
	 */
	public static boolean compareHashedPasswords(String storedPassword, String submittedPassword) {
		return Objects.equals(storedPassword, submittedPassword);
	}
	/**
	 * Get user history
	 */
	/**
	 * Get user preferences
	 */
	/**
	 * Search for flights
	 */
	/**
	 * 
	 */
}
