package LearnerClass;
import java.util.HashMap;

import java.sql.*;
import com.prefengine.util.SQLConnection;

public class AQR {


	public static void main(String[] args) throws SQLException {
		Connection connection = SQLConnection.getConnection();
		// TODO Auto-generated method stub
		String name=""; 
		HashMap<Integer, String> AQR = new HashMap<>();
		
		AQR.put(1, "Emirates");
		AQR.put(2, "Qatar Airways");
		AQR.put(3, "Singapore Airlines");
		AQR.put(4, "Cathay Pacific");
		AQR.put(5, "ANAs");
		AQR.put(6, "Etihad Airways");
		AQR.put(7, "Turkish Airlines");
		AQR.put(8, "EVA Air");
		AQR.put(9, "Qantas Airways");
		AQR.put(10, "Lufthansa");
		AQR.put(11, "Garuda Indonesia");
		AQR.put(12, "Hainan Airlines");
		AQR.put(13, "Thai Airways");
		AQR.put(14, "Air France");
		AQR.put(15, "Swiss Int�l Air Lines");
		AQR.put(16, "Asiana Airlines");
		AQR.put(17, "Air New Zealand");
		AQR.put(18, "Virgin Australia");
		AQR.put(19, "Austrian");
		AQR.put(20, "Bangkok Airways");
		AQR.put(21, "Japan Airlines");
		AQR.put(22, "Dragonair");
		AQR.put(23, "AirAsia");
		AQR.put(24, "KLM");
		AQR.put(25, "Virgin America");
		AQR.put(26, "British Airways");
		AQR.put(27, "Finnair");
		AQR.put(28, "Virgin Atlantic");
		AQR.put(29, "Hong Kong Airlines");
		AQR.put(30, "Norwegian");
		AQR.put(31, "Air Canada");
		AQR.put(32, "China Southern");
		AQR.put(33, "Aegean Airlines");
		AQR.put(34, "Malaysia Airlines");
		AQR.put(35, "Delta Air Lines");
		AQR.put(36, "Korean Air");
		AQR.put(37, "China Airlines");
		AQR.put(38, "easyJet");
		AQR.put(39, "SilkAir");
		AQR.put(40, "Aeroflot");
		AQR.put(41, "South African Airways");
		AQR.put(42, "Oman Air");
		AQR.put(43, "Air Astana");
		AQR.put(44, "Vietnam Airlines");
		AQR.put(45, "LAN Airlines");
		AQR.put(46, "Jetstar Airways");
		AQR.put(47, "Porter Airlines");
		AQR.put(48, "AirAsiaX");
		AQR.put(49, "Aer Lingus");
		AQR.put(50, "WestJet");
		AQR.put(51, "Indigo");
		AQR.put(52, "Iberia");
		AQR.put(53, "jetBlue Airways");
		AQR.put(54, "Jetstar Asia");
		AQR.put(55, "Azul Airlines");
		AQR.put(56, "Avianca");
		AQR.put(57, "TAM Airlines");
		AQR.put(58, "Alitalia");
		AQR.put(59, "Brussels Airlines");
		AQR.put(60, "Alaska Airlines");
		AQR.put(61, "Scoot");
		AQR.put(62, "SAS Scandinavian");
		AQR.put(63, "Air Seychelles");
		AQR.put(64, "TAP Air Portugal");
		AQR.put(65, "Thomson Airways");
		AQR.put(66, "Southwest Airlines");
		AQR.put(67, "SriLankan Airlines");
		AQR.put(68, "United Airlines");
		AQR.put(69, "Copa Airlines");
		AQR.put(70, "Azerbaijan Airlines");
		AQR.put(71, "Jet Airways");
		AQR.put(72, "Hawaiian Airlines");
		AQR.put(73, "Air Mauritius");
		AQR.put(74, "Air Berlin");
		AQR.put(75, "Eurowings");
		AQR.put(76, "Ethiopian Airlines");
		AQR.put(77, "American Airlines");
		AQR.put(78, "Peach");
		AQR.put(79, "China Eastern");
		AQR.put(80, "Gulf Air");
		AQR.put(81, "Icelandair");
		AQR.put(82, "Saudi Arabian Airlines");
		AQR.put(83, "Philippine Airlines");
		AQR.put(84, "American Eagle");
		AQR.put(85, "Kenya Airways");
		AQR.put(86, "TAAG Angola");
		AQR.put(87, "Air China");
		AQR.put(88, "Air Transat");
		AQR.put(89, "Air Nostrum");
		AQR.put(90, "Juneyao Airlines");
		AQR.put(91, "Fiji Airways");
		AQR.put(92, "LOT Polish");
		AQR.put(93, "Kulula");
		AQR.put(94, "Aeromexico");
		AQR.put(95, "Royal Brunei Airlines");
		AQR.put(96, "Tianjin Airlines");
		AQR.put(97, "Tiger Airways");
		AQR.put(98, "Mango");
		AQR.put(99, "Royal Jordanian");
		AQR.put(100, "SpiceJet");
		String sql = "insert into ArilineName (ArilineName) values (?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		AQR.forEach((k,v)->{try {ps.setString(1, v);ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
		
		
		/*for(int i=0; i<AQR.size();i++){
			name = AQR.get(i);
			ps.setString(1, name);
			ps.execute();
		}
		*/
		
		
	}

}
