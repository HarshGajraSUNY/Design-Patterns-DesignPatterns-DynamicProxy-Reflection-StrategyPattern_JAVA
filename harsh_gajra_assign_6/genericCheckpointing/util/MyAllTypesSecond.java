
package genericCheckpointing.util;


public class MyAllTypesSecond extends SerializableObject {

	//String complexType;
	double myDoubleT;
	double myOtherDoubleT;
	float myFloatT;
	short myShortT;
	short myOtherShortT;
	char myCharT;
	public MyAllTypesSecond(){};
	public MyAllTypesSecond(String complexType, double myDoubleT, double myOtherDoubleT, float myFloatT, short myShortT,
			short myOtherShortT, char myCharT) {
		super();
		//this.complexType = complexType;
		this.myDoubleT = myDoubleT;
		this.myOtherDoubleT = myOtherDoubleT;
		this.myFloatT = myFloatT;
		this.myShortT = myShortT;
		this.myOtherShortT = myOtherShortT;
		this.myCharT = myCharT;
	}
	
	
	//getters and setters
	//public String getComplexType() {
		//return complexType;
	//}
	//public void setComplexType(String complexType) {
		//this.complexType = complexType;
	//}
	public double getMyDoubleT() {
		return myDoubleT;
	}
	public void setMyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}
	public double getMyOtherDoubleT() {
		return myOtherDoubleT;
	}
	public void setMyOtherDoubleT(double myOtherDoubleT) {
		this.myOtherDoubleT = myOtherDoubleT;
	}
	public float getMyFloatT() {
		return myFloatT;
	}
	public void setMyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}
	public short getMyShortT() {
		return myShortT;
	}
	public void setMyShortT(short myShortT) {
		this.myShortT = myShortT;
	}
	public short getMyOtherShortT() {
		return myOtherShortT;
	}
	public void setMyOtherShortT(short myOtherShortT) {
		this.myOtherShortT = myOtherShortT;
	}
	public char getMyCharT() {
		return myCharT;
	}
	public void setMyCharT(char myCharT) {
		this.myCharT = myCharT;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((complexType == null) ? 0 : complexType.hashCode());
		result = prime * result + myCharT;
		long temp;
		temp = Double.doubleToLongBits(myDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(myFloatT);
		temp = Double.doubleToLongBits(myOtherDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + myOtherShortT;
		result = prime * result + myShortT;
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
		MyAllTypesSecond other = (MyAllTypesSecond) obj;
		//if (complexType == null) {
			//if (other.complexType != null)
				//return false;
		//} 
	//	if (!complexType.equals(other.complexType))
		//	return false;
		if (myCharT != other.myCharT)
			return false;
		if (Double.doubleToLongBits(myDoubleT) != Double.doubleToLongBits(other.myDoubleT))
			return false;
		if (Float.floatToIntBits(myFloatT) != Float.floatToIntBits(other.myFloatT))
			return false;
		if (Double.doubleToLongBits(myOtherDoubleT) != Double.doubleToLongBits(other.myOtherDoubleT))
			return false;
		if (myOtherShortT != other.myOtherShortT)
			return false;
		if (myShortT != other.myShortT)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "MyAllTypesSecond  [myDoubleT=" + myDoubleT + ", myOtherDoubleT="
				+ myOtherDoubleT + ", myFloatT=" + myFloatT + ", myShortT=" + myShortT + ", myOtherShortT="
				+ myOtherShortT + ", myCharT=" + myCharT + "]";
	}
	 	
}

