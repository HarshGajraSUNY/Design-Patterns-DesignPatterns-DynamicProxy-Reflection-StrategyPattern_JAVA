package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import genericCheckpointing.xmlStoreRestore.Results;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler {

	private String fileName;
	private Results rs;
	private String fileName2;

	public StoreRestoreHandler(Results rs, String fileName) {
		this.rs = rs;
		this.fileName = fileName;

	}

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		// TODO Auto-generated method stub
		HashMap<String, String> hmap = new HashMap<>();
		List<HashMap<String, String>> list = new ArrayList<>();
		if (arg1.getName().equalsIgnoreCase("writeObj")) {

			SerializableObject serObj = (SerializableObject) arg2[0];
			String wireF = arg2[2].toString();

			if (wireF.equalsIgnoreCase("XML")) {

				WriterClass wr = new WriterClass();
				wr.writer(serObj, rs, fileName);

			}

		}

		if (arg1.getName().equalsIgnoreCase("readObj")) {
			ArrayList arr = new ArrayList();

			FileProcessor fp = new FileProcessor(fileName);
			String str = "";
			// System.out.println("here");
			while ((str = fp.readLine()) != null) {

				if (!str.contains("</DPSerialization>")) {

					// if (str.contains("<complexType")) {
					// System.out.println(str);
					if (str.contains("genericCheckpointing.util.MyAllTypesFirst")) {
						hmap.put("complexType", (str.substring(str.indexOf("=") + 2, str.indexOf(">"))));
						// arr.add(+ (str.substring(str.indexOf("=") + 2,
						// str.indexOf(">"))));
					} else if (str.contains("<myInt")) {
						// System.out.println(str);
						hmap.put("myInt", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myInt=" + str.substring(str.indexOf(">") +
						// 1, str.lastIndexOf("<")).trim());

					} else if (str.contains("<myOtherInt")) {
						hmap.put("myOtherInt", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myOtherInt" + str.substring(str.indexOf(">")
						// + 1, str.lastIndexOf("<")).trim());
					} else if (str.contains("<myLong")) {
						hmap.put("myLong", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myLong=" + str.substring(str.indexOf(">") +
						// 1, str.lastIndexOf("<")).trim());
					} else if (str.contains("<myOtherLong")) {
						hmap.put("myOtherLong", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myOtherLong=" +
						// str.substring(str.indexOf(">") + 1,
						// str.lastIndexOf("<")).trim());
					} else if (str.contains("<myString")) {
						hmap.put("myString", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myString=" + str.substring(str.indexOf(">")
						// + 1, str.lastIndexOf("<")).trim());
					} else if (str.contains("<myBool")) {
						hmap.put("myBool", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myBool=" + str.substring(str.indexOf(">") +
						// 1, str.lastIndexOf("<")).trim());
					}

					else if (str.contains("genericCheckpointing.util.MyAllTypesSecond")) {
						hmap.put("complexType", (str.substring(str.indexOf("=") + 2, str.indexOf(">"))));
						// arr.add("complexType=" +
						// (str.substring(str.indexOf("=") + 2,
						// str.indexOf(">"))));
					} else if (str.contains("<myDoubleT")) {
						System.out.println(str);
						hmap.put("myDoubleT", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myDouble=" + str.substring(str.indexOf(">")
						// + 1, str.lastIndexOf("<")).trim());

					} else if (str.contains("<myOtherDoubleT")) {
						hmap.put("myOtherDoubleT", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myOtherDoubleT=" +
						// str.substring(str.indexOf(">") + 1,
						// str.lastIndexOf("<")).trim());
					} else if (str.contains("<myFloatT")) {
						hmap.put("myFloatT", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myFloat=" + str.substring(str.indexOf(">") +
						// 1, str.lastIndexOf("<")).trim());
					} else if (str.contains("<myShortT")) {
						hmap.put("myShortT", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myShortT=" + str.substring(str.indexOf(">")
						// + 1, str.lastIndexOf("<")).trim());
					} else if (str.contains("<myOtherShortT")) {
						hmap.put("myOtherShortT", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myOtherShortT=" +
						// str.substring(str.indexOf(">") + 1,
						// str.lastIndexOf("<")).trim());
					} else if (str.contains("<myCharT")) {
						hmap.put("myCharT", str.substring(str.indexOf(">") + 1, str.lastIndexOf("<")).trim());
						// arr.add("myCharT=" + str.substring(str.indexOf(">") +
						// 1, str.lastIndexOf("<")).trim());
					}

					// Class clsName =s.forName(clsName);
					//System.out.println();

				} else {
					list.add(hmap);
					hmap = new HashMap<>();
				}
				// }

			}
			for (HashMap<String, String> map : list) {
				System.out.println(map);
				Class<?> clsName = Class.forName(map.get("complexType"));
				System.out.println(clsName);
				Object obj = clsName.newInstance();
				map.remove("complexType");
				for (String s : map.keySet()) {
                   
                 
                    
					Method m = clsName.getMethod("set" + s.substring(0, 1).toUpperCase()+ s.substring(1), getSignature(s) );
					System.out.println("check"+getparams(s,map.get(s)));
					m.invoke(obj, getparams(s,map.get(s)));
				}
			}
			
		}

		return null;
	}

	public void setFileName(String fileName2) {
		this.fileName = fileName;
	}

	public static Object[] getparams(String type, String value) {
		// Object[] paramsObj = new Object[1];
			
		if (type.toLowerCase().contains(int.class.getTypeName().toLowerCase())) {
			return new Object[] { Integer.parseInt(value) };
		}
		if (type.toLowerCase().contains(short.class.getTypeName().toLowerCase())) {
			return new Object[] { Short.parseShort(value) };
		}
		if (type.toLowerCase().contains(long.class.getTypeName().toLowerCase())) {
			return new Object[] { Long.parseLong(value) };
		}
		if (type.toLowerCase().contains(double.class.getTypeName().toLowerCase())) {
			return new Object[] { Double.parseDouble(value) };
		}
		if (type.toLowerCase().contains(float.class.getTypeName().toLowerCase())) {
			return new Object[] { Float.parseFloat(value) };
		}
		if (type.toLowerCase().contains("bool")) {
			return new Object[] { Boolean.parseBoolean(value) };
		}
		if (type.toLowerCase().contains(char.class.getTypeName().toLowerCase())) {
			return new Object[] { value.charAt(0) };
		}
		if (type.toLowerCase().contains(String.class.getSimpleName().toLowerCase())) {
			return new Object[] { value };
		}

		return null;

	}

	public Class[] getSignature(String type) {
		System.out.println("getSignature "+type);
		if (type.toLowerCase().contains(int.class.getSimpleName().toLowerCase())) {
			return new Class[] { int.class };
		}
		if (type.toLowerCase().contains(float.class.getSimpleName().toLowerCase()	)) {
			return new Class[] { float.class };
		}
		if (type.toLowerCase().contains(String.class.getSimpleName().toLowerCase())) {
			return new Class[] { String.class };
		}
		if (type.toLowerCase().contains(long.class.getTypeName().toLowerCase())) {
			return new Class[] { long.class };
		}
		if (type.toLowerCase().contains(double.class.getTypeName().toLowerCase())) {
			return new Class[] { double.class };
		}
		if (type.toLowerCase().contains("bool")) {
			return new Class[] { boolean.class };
		}
		if (type.toLowerCase().contains(char.class.getTypeName().toLowerCase())) {
			return new Class[] { char.class };
		}
		if (type.toLowerCase().contains(short.class.getTypeName().toLowerCase())) {
			return new Class[] { short.class };
		}

		return null;
	}

	public void openFile() {

	}

}

