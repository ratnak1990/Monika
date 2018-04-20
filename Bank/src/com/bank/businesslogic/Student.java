package com.bank.businesslogic;

public class Student {

	
	String name1;
	String name2;
	int age;
	
	
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return super.toString();
//	}
	
	
	
	public String getName1() {
		return name1;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public void setName1(String name1) {
		this.name1 = name1;
	}



	public String getName2() {
		return name2;
	}



	public void setName2(String name2) {
		this.name2 = name2;
	}

	
	


	@Override
    public int hashCode()
    {
		String fullname= name1.hashCode()+ ""+name2.hashCode();
		 return fullname.hashCode();
    }
 
}
