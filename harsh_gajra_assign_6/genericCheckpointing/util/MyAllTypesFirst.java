package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{

	//String complexType;
	int myInt;
	int myOtherInt;
	long myLong;
	long myOtherLong;
	String myString;
	boolean myBool;
	
	
	@Override
	public String toString() {
		return "MyAllTypesFirst  [myInt=" + myInt + ", myOtherInt=" + myOtherInt
				+ ", myLong=" + myLong + ", myOtherLong=" + myOtherLong + ", myString=" + myString + ", myBool="
				+ myBool + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	//	result = prime * result + ((complexType == null) ? 0 : complexType.hashCode());
		result = prime * result + (myBool ? 1231 : 1237);
		result = prime * result + myInt;
		result = prime * result + (int) (myLong ^ (myLong >>> 32));
		result = prime * result + myOtherInt;
		result = prime * result + (int) (myOtherLong ^ (myOtherLong >>> 32));
		result = prime * result + ((myString == null) ? 0 : myString.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAllTypesFirst other = (MyAllTypesFirst) obj;
		//if (complexType == null) {
		//	if (other.complexType != null)
		//		return false;
		//} else if (!complexType.equals(other.complexType))
			//return false;
		if (myBool != other.myBool)
			return false;
		if (myInt != other.myInt)
			return false;
		if (myLong != other.myLong)
			return false;
		if (myOtherInt != other.myOtherInt)
			return false;
		if (myOtherLong != other.myOtherLong)
			return false;
		if (myString == null) {
			if (other.myString != null)
				return false;
		} else if (!myString.equals(other.myString))
			return false;
		return true;
	}



	public MyAllTypesFirst(){}
	public MyAllTypesFirst(String complexType, int myInt, int myOtherInt, long myLong2, long myOtherLong2, String myString2,
			boolean myBool) {
		super();
		//this.complexType = complexType;
		this.myInt = myInt;
		this.myOtherInt = myOtherInt;
		this.myLong = myLong2;
		this.myOtherLong = myOtherLong2;
		this.myString = myString2;
		this.myBool = myBool;
	}
	
	
	

	//getters and setters
	//public String getComplexType() {
		//	return complexType;
	//}
		
	//public void setComplexType(String complexType) {
		//this.complexType = complexType;
	//}
	public int getMyInt() {
		return myInt;
	}
	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}
	public int getMyOtherInt() {
		return myOtherInt;
	}
	public void setMyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
	}
	public long getMyLong() {
		return myLong;
	}
	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}
	public long getMyOtherLong() {
		return myOtherLong;
	}
	public void setMyOtherLong(long myOtherLong) {
		this.myOtherLong = myOtherLong;
	}
	public String getMyString() {
		return myString;
	}
	public void setMyString(String myString) {
		this.myString = myString;
	}
	public boolean isMyBool() {
		return myBool;
	}
	public void setMyBool(boolean myBool) {
		this.myBool = myBool;
	}
	

}

