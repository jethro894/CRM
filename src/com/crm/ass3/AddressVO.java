package com.crm.ass3;

public class AddressVO extends VOBase{
	protected final String[] default_facet = {"ID"};
	protected String AddressLine1, AddressLine2, City, State, Country, Zip;
	
	public AddressVO(String a1, String a2, String ct, String s, String c, String z){
		this.createFacet(default_facet[0], new IDVO());
		this.AddressLine1 = a1;
		this.AddressLine2 = a2;
		this.City = ct;
		this.State = s;
		this.Country = c;
		this.Zip = z;
		this.payload = this.AddressLine1 + "\n" + this.AddressLine2 + "\n" + this.City + ", " + this.State + ", " + this.Zip + "\n" + this.Country;
	}
	
	public AddressVO(String addr){
		this.createFacet(default_facet[0], new IDVO());
		this.payload = addr;
	}
	
	public String getAddress(){
		return this.payload;
	}
}
