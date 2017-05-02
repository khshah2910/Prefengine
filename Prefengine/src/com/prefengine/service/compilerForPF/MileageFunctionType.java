package com.prefengine.service.compilerForPF;


/**  
 * sub-class of FunctionType with ServiceProperty.MILEAGE	
 */
public class MileageFunctionType extends AbstractFunctionType{

	 /** store mileage  */
		 private float[] mileage;
		 
		 /** store mileage in Possibility */
		 private float mileageInPossibility;
		 
		 /** when user only define possibility and no specific mileage number, turn true */
		 private boolean hasPossibility;
		 
		 /**  
		  * constructor for MileageFunctionType
		  * 
		  * @param serviceProperty
		  * 			basic property
		  */
		public MileageFunctionType(ServiceProperty serviceProperty) {
			
			super(serviceProperty);
			// TODO Auto-generated constructor stub
			hasPossibility = false;
		}
		
		 /**  
		  * set up mileage in Possibility
		  * @param mileageInPossibility
		  * 			float type			
		  */
		public void setMileageInPossibility(float mileageInPossibility)
		{
			this.mileageInPossibility = mileageInPossibility;
			hasPossibility = true;
		}
		 /**  
		  * get if the information stored in possibility or specific mileage number 
		  * @return when stored in possibility return true			
		  */
		public boolean hasPossibility()
		{
			return this.hasPossibility;
		}
		
		 /**  
		  * get mileage in Possibility
		  * @return float type			
		  */
		public float getMileageInPossibility()
		{
			return this.mileageInPossibility ;
		}	
		 /**  
		  * set up mileage 
		  * @param mileage
		  * 			float type			
		  */
		public void setMileage(float[] mileage)
		{
			this.mileage = mileage;
		}
		
		 /**  
		  * get mileage 
		  * @return float type			
		  */
		public float[] getMileage()
		{
			return this.mileage ;
		}

		/**  
		  * function method for this class leave blank for now 
		  * @Override
		  */
		public void functionMethod() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public ServiceProperty getProperty() {
			// TODO Auto-generated method stub
			return ServiceProperty.MILEAGE;
		}	 
	}


