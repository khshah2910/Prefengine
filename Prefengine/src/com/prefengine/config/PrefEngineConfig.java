package com.prefengine.config;

//TODO import com.prefengine.dao.AdminDAO;
import com.prefengine.dao.UserDAO;
import com.prefengine.dao.DbDAO;
//TODO import com.prefengine.service.AdminService;
import com.prefengine.service.UserService;

/**
 * This class configures the service objects and also shuts down services
 * @author Yinka Martins
 */
public class PrefEngineConfig {
	//TODO add all other static links
	public static final String logFile = "/_logs/";
	
	//The service objects in use, representing all lower layers to the app
	//TODO private static AdminService adminService;
	private static UserService userService;
	//TODO private static AdminDAO adminDao;	
	private static DbDAO dbDAO;  // contains Connection
	
	// The lower-level service objects-- you can vary this as desired
	private static UserDAO userDao; 
	
	/**
	 * This sets up the service API and data access objects
	 * @param dbUrl
	 * @param usr
	 * @param pw
	 * @throws Exception
	 */
	public static void configureServices(String dbUrl, String usr, String pw)
			throws Exception {
		// configure service layer and DAO objects--
		// The service objects get what they need at creation-time
		// This is known as "constructor injection" 

		try {
			if (dbUrl == null){
				System.out.println("configuring Services: null dburl found (defaulting to HSQLDB)");
			}
			else{}
			//hide this statement from user
			System.out.println("configureServices: dbUrl = "+ dbUrl +", usr =" + usr + " pw = "+ pw);
			//initialize database DAO, user DAO and admin DAO
			dbDAO = new DbDAO(dbUrl, usr, pw);
			userDao = new UserDAO(dbDAO);
			userService = new UserService(dbDAO, userDao);
			//adminDao= new AdminDAO(dbDAO);				
			//Initialize other DAOs. Add more if required
			//exerciseDao = new ExerciseDAO(dbDAO);
			//workoutDao = new WorkoutDAO(dbDAO);
			//clientDao = new ClientDAO(dbDAO);
			//instantiate adminServices with all DAOs
			//adminService = new AdminService(dbDAO, adminDao);//exerciseDao, workoutDao, clientDao
			
		} catch (Exception e) {
			System.out.println(exceptionReport(e));
			// e.printStackTrace(); // causes lots of output
			System.out.println("Problem contacting DB: " + e);
			System.out.println("Please contact the developer: " + e);
			if (dbUrl == null || dbUrl.contains("hsqldb"))
				System.out
						.println("HSQLDB not available: may need server startup");	
			throw(e); // rethrow to notify caller (caller should print exception details)
		}
	}
	
	// Compose an exception report
	// and return the string for callers to use
	public static String exceptionReport(Exception e) {
		String message = e.toString(); // exception name + message
		if (e.getCause() != null) {
			message += "\n  cause: " + e.getCause().toString();
			if (e.getCause().getCause() != null)
				message += "\n    cause's cause: "
						+ e.getCause().getCause().toString();
		}
		e.printStackTrace(System.out); 
		return message;
	}
	// When the app exits, the shutdown happens automatically
	// For other cases, call this to free up the JDBC Connection
	public static void shutdownServices() throws Exception {
		dbDAO.close(); // close JDBC connection
	}
	// Let the apps get the business logic layer services
	//TODO
	/**public static AdminService getAdminService() {
		return adminService;
	}*/
	
	public static UserService getUserService() {
		return userService;
	}
}

	
	