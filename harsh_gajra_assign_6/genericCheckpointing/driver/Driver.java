
package genericCheckpointing.driver;

import java.util.Random;
import java.util.Vector;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.Results;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class Driver {
	
	public static String randomString(int len) {
		StringBuilder sbr = new StringBuilder();
		Random ran = new Random();
		for (int i = 0; i < len; i++) {
			sbr.append((char) (ran.nextInt(26) + (int) 'a' + 1));
		}
		return sbr.toString();
	}

	public static void main(String[] args) {
		Vector<SerializableObject> vector1 = new Vector<>();
		Vector<SerializableObject> vector2 = new Vector<>();
		
		
		
		if (args.length<3){
			System.exit(1);
		}
		int NUM_OF_OBJECTS = Integer.parseInt(args[1]);
		// FIXME: read the value of checkpointFile from the command line

		ProxyCreator pc = new ProxyCreator();
		Results rs = new Results();
		String fileName = args[2];
		// create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler
		StoreRestoreHandler handler = new StoreRestoreHandler(rs, fileName);

		

		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] { StoreI.class, RestoreI.class }, handler);
		handler.setFileName("");

		// FIXME: invoke a method on the handler instance to set the file name
		// for checkpointFile and open the file

		
		MyAllTypesFirst myFirst;
		MyAllTypesSecond mySecond;

		// Use an if/switch to proceed according to the command line argument
		// For deser, just deserliaze the input file into the data structure and
		// then print the objects
		// The code below is for "serdeser" mode
		// For "serdeser" mode, both the serialize and deserialize functionality
		// should be called.
		String mode = "";
		mode = args[0];
		if (mode.equalsIgnoreCase("deser")) {

			SerializableObject myRecordRet;
			
			// create a data structure to store the returned ojects
			//for (int j = 0; j < 2 * NUM_OF_OBJECTS; j++) {

				myRecordRet = ((RestoreI) cpointRef).readObj("XML");
				vector2.add(myRecordRet); 
				
			//}
			
				// FIXME: store myRecordRet in the vector
			}

		 else if (mode.equalsIgnoreCase("serdeser")) {
 
			for (int i = 0; i < NUM_OF_OBJECTS; i++) {

				// FIXME: create these object instances correctly using an
				// explicit value constructor
				// use the index variable of this loop to change the values of
				// the arguments to these constructors
				Random rand = new Random();
				String complexType = randomString((i + i * 2) % 10);
				int myInt = rand.nextInt(i * 1000 + 1100);
				

				int myOtherInt = rand.nextInt(i + 100 / (i + 2));
				
				long myLong = rand.nextLong();
				

				long myOtherLong = (long) (100 * i +7);
				
				String myString = randomString(5);
				boolean myBool = rand.nextBoolean();

				String complexType1 = randomString((i * 2) % 10);
				double myDoubleT = rand.nextDouble() + i * 7;
				
				double myOtherDoubleT = rand.nextDouble() * 5 + i * 5;
				
				float myFloatT = rand.nextFloat() + i * 6;
				
				short myShortT = (short) rand.nextInt(128);
				
				short myOtherShortT = (short) rand.nextInt(100);
				
				char myCharT = randomString(i + 5).charAt(i);

				myFirst = new MyAllTypesFirst(complexType, myInt, myOtherInt, myLong, myOtherLong, myString, myBool);
				mySecond = new MyAllTypesSecond(complexType, myDoubleT, myOtherDoubleT, myFloatT, myShortT,
						myOtherShortT, myCharT);
				vector1.add(myFirst);
				vector1.add(mySecond);
				// FIXME: store myFirst and mySecond in the data structure
				((StoreI) cpointRef).writeObj(myFirst, 11, "XML");
				((StoreI) cpointRef).writeObj(mySecond, 11, "XML");
				
				
				for(int k=0;k<NUM_OF_OBJECTS;k++)
				{
				SerializableObject ob1 =((RestoreI) cpointRef).readObj("XML");
				vector2.addElement(ob1);
				}// rs.writeToFile(args[2]);
			} 

		}
		
		int mismatchObjects=0;
        for( int i=0;i< vector1.size();i++){
           if(!(vector1.get(i).equals(vector2.get(i))))
               mismatchObjects++;
        }
       
       if(mismatchObjects==0)
       System.out.print("\n\n mismatched objects"+ mismatchObjects);
       else
       System.out.print("\n\nTotal mismatched objects:"+ mismatchObjects);
		
	}//main class end

}//end of class
