package com.prefengine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;

import com.prefengine.service.SDF;


import com.prefengine.domain.User;
//TODO import com.prefengine.domain.UserLog;
import com.prefengine.service.UserService;

import static com.prefengine.dao.DbConstants.*;

/**
 * 
 * Access User table through this class. 
 * @author Yinka Martins
 */
public class UserDAO {
	private Connection connection;
	private Timestamp ts;
	//TODO User Log Information
	//private UserLog usrlog;
	/**
	 * A Data Access Object for user table 
	 * @param db the database connection
	 * @throws SQLException
	 */
	public UserDAO(DbDAO db) throws SQLException {
		connection = db.getConnection();
	}
	
	//TODO Delete. Just for testing code
	public static void main(String[] args) throws SQLException{
		DbDAO db = new DbDAO("jdbc:mysql://localhost:3306/prefengine", "root", "");
		UserDAO userDao = new UserDAO(db);
		//SDF.printline(userDao.checkLogin("jsmith@gmail.com", "test1"));
		User usr = new User(0, "a", "jasdf", "aM7UBIpLd5EYkVJfEK5JdIvn38QSnz8kl/Pr2JFga5c=", 
	"iDomC0oHKq5cnXpqKFOewLUAxBc=", "What is your best drink", "Java");
		userDao.addUser(usr);
		//User usr = userDao.findUserByEmail("jsmith@gmail.com");
        //SDF.printline(usr);
	}
	
	/**
	 * This authenticates the user email and password
	 * @param email
	 * @param pwd
	 * @return
	 * @throws SQLException
	 */
	public Boolean checkLogin(String userName, String pwd) throws SQLException {
		PreparedStatement pstmt = null;
		String salt = null;
		ResultSet set = null;
		ts = new Timestamp(System.currentTimeMillis());
		//TODO Check login from unknown locations
		try {
			String getSaltQuery = "SELECT saltkey FROM " + USER_TABLE +
					" WHERE LOWER(userName) = LOWER(?)";
			pstmt = connection.prepareStatement(getSaltQuery);
			pstmt.setString(1, "" + userName + "");
			set= pstmt.executeQuery();
			
			if(set.next()!=false){
				salt = set.getString("saltkey");
				pstmt.close();
				
				String loginMatchQuery = "SELECT * FROM " + USER_TABLE +
						" where username = ? and password = ?";
				pstmt = connection.prepareStatement( loginMatchQuery );
				//Hash inputted password
				Base64.Encoder enc = Base64.getEncoder();
				Base64.Decoder dec = Base64.getDecoder();
				byte[] hashedPwd = UserService.hashPassword(pwd.toCharArray(), dec.decode(salt.getBytes()));
				
				pstmt.setString(1, userName); 
				pstmt.setBytes(2, enc.encodeToString(hashedPwd).getBytes()); 
				set = pstmt.executeQuery();

				if (set.next()){ // if the result is not empty
					set.close();
					SDF.printline(userName + " successfully logged in at " + ts);
					//TODO
					/**usrlog = new UserLog(0, "Successful Log in", "The user, " + email + ", successful logged" 
							+ " in at " + ts, ts, Level.FINEST, email, this.getClass().getName());
					usrlog.logToFile(null); **/
					return true;
				}
				else {
					SDF.printline(userName + " was unsuccessful while logging in at " + ts);
					//TODO 
					/**usrlog = new UserLog(0, "Unsuccessful Log in", "There was a login attempt failure. The user "
							+ email + " was unsuccessful " + "while logging in at " + ts, ts, Level.WARNING, email, 
							this.getClass().getName());
					usrlog.logToFile(null); **/
				}
			}else{
				SDF.printline("No such email address exist");
				SDF.printline(userName + " Email does not exist" + ts);
				//TODO 
				/**usrlog = new UserLog(0, "User does not exist", "There was a login attempt failure. The user "
						+ email + " does not exist. " + "Occurred at " + ts, ts, Level.WARNING, email, 
						this.getClass().getName());
				usrlog.logToFile(null);**/
			}
			
		}finally {
			pstmt.close();
		}
		return false;
	}
	
	
	/**
	 * Find a user by email address
	 * @param email_address
	 * @return the user found, return null otherwise
	 */
	public User findUserByEmail(String email_address) throws SQLException 
	{
		User usr = null;
		PreparedStatement pstmt = null;
		try {
			 String query = "SELECT * FROM " + USER_TABLE + " WHERE LOWER(username) "
			 		+ " = LOWER(?)";

			 pstmt = connection.prepareStatement(query);
			 pstmt.setString(1, "" + email_address + "");
			 ResultSet set= pstmt.executeQuery();
			 if(set.next())
			 //TODO Replace with user class
				usr = new User(set.getInt("id"), set.getString("userName"),
							 set.getString("email"), set.getString("Password"), 
							 set.getString("saltKey"), set.getString("security_Question"),
							 set.getString("security_question_answer"));
			
		} catch(SQLException e){
			ts = new Timestamp(System.currentTimeMillis());
			SDF.printline("SQL Exception: " + e);
			//TODO
			/**usrlog = new UserLog(0, "SQL Exception", e.getMessage() + ". Occurred at " + ts, ts, 
					Level.SEVERE, email_address, this.getClass().getName());
			usrlog.logToFile(null);**/
		}
		finally {
			pstmt.close();
		}
		return usr;
		//TODO add to log
	}
	/**
	 * Adds a new user. 
	 * @param user
	 */
	//Code was copied from Kush's RegistrationDAO sa user has his specific actions within 
	//the userDAO rather than individual DAOs for each action.
	public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(username,email,password,saltkey,security_question,security_question_answer) values ( ?,?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail_address());
            preparedStatement.setString(3, user.getHashedPassword());
            preparedStatement.setString(4, user.getSaltKey());
            preparedStatement.setString(5, user.getSecQue());
            preparedStatement.setString(6, user.getSecAns());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}