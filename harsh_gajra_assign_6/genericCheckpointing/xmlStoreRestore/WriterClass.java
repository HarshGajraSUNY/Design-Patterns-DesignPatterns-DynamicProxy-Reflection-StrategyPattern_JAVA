package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;

import genericCheckpointing.xmlStoreRestore.Results;
import genericCheckpointing.util.SerializableObject;

public class WriterClass {

	public void writer(SerializableObject o,  Results rs, String fileName) throws IllegalArgumentException, IllegalAccessException {
		System.out.println("<DPSerialization>");
		Class serClass = o.getClass();
		String nameType = o.getClass().getName();
		// System.out.println(nameType);
		System.out.println("\t<complexType xsi:type = " + nameType + ">");
		Field[] fieldList = serClass.getDeclaredFields();
		rs.output.add("<DPSerialization>");
		rs.output.add("\t<complexType xsi:type = " + nameType + ">");
		

		for (int j = 0; j < fieldList.length; j++) {

			Field f = fieldList[j];
			f.setAccessible(true);
			Class fieldType = fieldList[j].getType();
			

			String fieldName = fieldList[j].getName();
	
			Object fieldObject = fieldList[j].get(o);
             
		           if(f.getType().toString().equals("int") && Integer.parseInt(f.get(o).toString())> 10 )
		        	   rs.output.add(String.format("\t\t<%s xsi:type\"xsd:%s\"> %s </%s>", fieldName, fieldType,
		   					fieldObject.toString(), fieldName));
	
		            
		            else if(f.getType().toString().equals("float") && Float.parseFloat(f.get(o).toString())> 10)
		            	rs.output.add(String.format("\t\t<%s xsi:type\"xsd:%s\"> %s </%s>", fieldName, fieldType,
		    					fieldObject.toString(), fieldName));
		              
		            else  if(f.getType().toString().equals("long") && Long.parseLong(f.get(o).toString())> 10 )
		            	rs.output.add(String.format("\t\t<%s xsi:type\"xsd:%s\"> %s </%s>", fieldName, fieldType,
		    					fieldObject.toString(), fieldName));
		              
		            else if(f.getType().toString().equals("double") && Double.parseDouble(f.get(o).toString())>10 )
		            	rs.output.add(String.format("\t\t<%s xsi:type\"xsd:%s\"> %s </%s>", fieldName, fieldType,
		    					fieldObject.toString(), fieldName)); 
		            
		            else if(f.getType().toString().equals("short") && Short.parseShort(f.get(o).toString())>10 )
		            	rs.output.add(String.format("\t\t<%s xsi:type\"xsd:%s\"> %s </%s>", fieldName, fieldType,
		    					fieldObject.toString(), fieldName));
		           
		            else if(f.getType().toString().equals("char"))
		            	rs.output.add(String.format("\t\t<%s xsi:type\"xsd:%s\"> %s </%s>", fieldName, fieldType,
		    					fieldObject.toString(), fieldName)); 
		            else if(f.getType().toString().equals("boolean"))
		            	rs.output.add(String.format("\t\t<%s xsi:type\"xsd:%s\"> %s </%s>", fieldName, fieldType,
		    					fieldObject.toString(), fieldName));
		            else if(f.getType().toString().equals("class java.lang.String"))
		            	rs.output.add(String.format("\t\t<%s xsi:type\"xsd:%s\"> %s </%s>", fieldName, fieldType,
		    					fieldObject.toString(), fieldName)); 
			
		
		//	System.out.println(String.format("\t\t<%s xsi:type\"xsd:%s\"> %s </%s>", fieldName, fieldType.getSimpleName(),
			//  		fieldObject.toString(), fieldName));
			
			
			
		}
		System.out.println("\t</complexType>");
		System.out.println("</DPSerialization>");
		rs.output.add("\t</complexType>");
		 rs.output.add("</DPSerialization>");
		 rs.writeToFile(fileName);
		

	}

}

