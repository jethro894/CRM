package com.crm.ass3;

public class AddressVO extends VOBase{
	protected String AddressLine1, AddressLine2, City, State, Country, Zip;
	
	public AddressVO(String a1, String a2, String ct, String s, String c, String z){
		this.AddressLine1 = a1;
		this.AddressLine2 = a2;
		this.City = ct;
		this.State = s;
		this.Country = c;
		this.Zip = z;
		this.payload = this.AddressLine1 + "\n" + this.AddressLine2 + "\n" + this.City + ", " + this.State + ", " + this.Zip + "\n" + this.Country;
	}
	
	public String getAddress(){
		return this.payload;
	}
}
