package LearnerClass;

import java.io.IOException;
import java.util.*;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.qpxExpress.QPXExpressRequestInitializer;
import com.google.api.services.qpxExpress.QPXExpress;
import com.google.api.services.qpxExpress.QPXExpress.Trips;
import com.google.api.services.qpxExpress.model.AirportData;
import com.google.api.services.qpxExpress.model.CarrierData;
import com.google.api.services.qpxExpress.model.CityData;
import com.google.api.services.qpxExpress.model.FlightInfo;
import com.google.api.services.qpxExpress.model.LegInfo;
import com.google.api.services.qpxExpress.model.PassengerCounts;
import com.google.api.services.qpxExpress.model.PricingInfo;
import com.google.api.services.qpxExpress.model.SegmentInfo;
import com.google.api.services.qpxExpress.model.SliceInfo;
import com.google.api.services.qpxExpress.model.TripOption;
import com.google.api.services.qpxExpress.model.TripOptionsRequest;
import com.google.api.services.qpxExpress.model.TripsSearchRequest;
import com.google.api.services.qpxExpress.model.SliceInput;
import com.google.api.services.qpxExpress.model.TripsSearchResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.prefengine.domain.Trip;
import com.prefengine.util.SQLConnection;

public class AirlineReservation {

	/**
	 * @param args
	 */private static final String APPLICATION_NAME = "Prefengine";

	private static final String API_KEY ="" ; //put your own key

	/** Global instance of the HTTP transport. */
	private static HttpTransport httpTransport;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			PassengerCounts passengers= new PassengerCounts();
			passengers.setAdultCount(1);
			List<SliceInput> slices = new ArrayList<SliceInput>();
			SliceInput slice = new SliceInput();
			String setOrigin = "BOS";
			String setDestination = "AMD";
			slice.setOrigin(setOrigin); 
			slice.setDestination(setDestination); 
			slice.setDate("2017-04-29");
			slices.add(slice);
			
			TripOptionsRequest request= new TripOptionsRequest();
			request.setSolutions(5);
			request.setPassengers(passengers);
			request.setSlice(slices);

			TripsSearchRequest parameters = new TripsSearchRequest();
			parameters.setRequest(request);
			QPXExpress qpXExpress= new QPXExpress.Builder(httpTransport, JSON_FACTORY, null).setApplicationName(APPLICATION_NAME)
					.setGoogleClientRequestInitializer(new QPXExpressRequestInitializer(API_KEY)).build();

			TripsSearchResponse list= qpXExpress.trips().search(parameters).execute();
			List<TripOption> tripResults=list.getTrips().getTripOption();
/*			List<CityData> a = list.getTrips().getData().getCity();
			List<AirportData> ap = list.getTrips().getData().getAirport();
		
			for(int i=0;i<a.size();i++){
				System.out.println("------> "+a.get(i).getName()+" :"+a.get(i).getCountry());
			}
			for(int i=0;i<ap.size();i++){
				System.out.println("------> "+ap.get(i).getName());
			}
			//List<CarrierData> c = list.getTrips().getData().getCarrier();
			//List<Integer> milage = new ArrayList<Integer>();
*/			String id;
			float duration = 0.0f;
			String dest = null;
			String origin = null;
			String route = null;
			int mil = 0;
			String carrier = "";
			String price = null;
			float finalPrice = 0;
			for(int i=0; i<tripResults.size(); i++){
				
				ArrayList<String> totalRoute = new ArrayList<String>();
				ArrayList<String> departureTime = new ArrayList<String>();
				ArrayList<String> arrivalTime = new ArrayList<String>();
				int totalMilage = 0; 
				String finalRoute = "";
				String cabin = "";
				int stops=0;
				
				//Slice
				List<SliceInfo> sliceInfo= tripResults.get(i).getSlice();
				 
				for(int j=0; j<sliceInfo.size(); j++){
					
					duration= sliceInfo.get(j).getDuration();
					List<SegmentInfo> segInfo= sliceInfo.get(j).getSegment();
					cabin = segInfo.get(j).getCabin();
					carrier = segInfo.get(j).getFlight().getCarrier();
					for(int k=0; k<segInfo.size(); k++){
						List<LegInfo> leg=segInfo.get(k).getLeg();
						for(int l=0; l<leg.size(); l++){
							arrivalTime.add(leg.get(l).getArrivalTime());
							departureTime.add(leg.get(l).getDepartureTime());
							dest=leg.get(l).getDestination();
							origin=leg.get(l).getOrigin();
							//milage.add(leg.get(l).getMileage());
							mil= leg.get(l).getMileage();
							route =  origin.concat("-->"+dest);
							totalRoute.add(route);
//							a.get(l).getName();
//							System.out.println("--=-=-=-=-=-=- > "+a.get(l).getName());
						}
						totalMilage = totalMilage+mil;
						stops++;
					}
					
				}
				System.out.println("Stops : "+(stops-1));
				List<PricingInfo> priceInfo= tripResults.get(i).getPricing();
				for(int l=0;l<totalRoute.size();l++){
					finalRoute = totalRoute.get(l);
					System.out.println("stop : "+l+" :" +finalRoute);
					//finalRoute = finalRoute+totalRoute.get(l).concat(", ");
				}
				
				for(int p=0; p<priceInfo.size(); p++){
					price= priceInfo.get(p).getSaleTotal();
					finalPrice = Float.parseFloat(price.substring(3));
					System.out.println("Price "+price);
				}
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				format.setTimeZone(TimeZone.getTimeZone("GMT"));
			
				id= tripResults.get(i).getId();
				System.out.println("Final route : "+finalRoute);
				System.out.println("id "+id);
				System.out.println("Departure "+setOrigin);
				System.out.println("Destination "+ setDestination);
				System.out.println("Departure Time"+departureTime.get(0));
				System.out.println("Arrival Time"+arrivalTime.get(arrivalTime.size()-1));
				
				System.out.println("Total Miles "+totalMilage);
				System.out.println("Total Duration = " + duration/60 +"hours");
				System.out.println("Cabin = "+cabin);
				System.out.println("Carrier  = "+ carrier);
				System.out.println("----------------------------------------");
			
				Connection connection = SQLConnection.getConnection();
				
				String query = "insert into flightRecord (tripId, departure, destination, route, stops, departureTime, arrivalTime, price, carrier, duration, milage,cabin, thisTrip, jsonData) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1,id);
				pst.setString(2, setOrigin);
				pst.setString(3, setDestination);
				pst.setString(4, finalRoute);
				pst.setInt(5, stops-1);
				pst.setString(6,departureTime.get(0));
				pst.setString(7,arrivalTime.get(arrivalTime.size()-1));
				pst.setFloat(8, finalPrice);
				pst.setString(9, carrier);
				pst.setFloat(10,duration );
				pst.setInt(11, totalMilage);
				pst.setString(12, cabin);
				pst.setString(13, null);
				pst.setString(14, null);
				
				pst.execute();

			}
			return;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		System.exit(1);

	}
	
}