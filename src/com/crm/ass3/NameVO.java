package com.crm.ass3;

public class NameVO extends VOBase{
	protected final String[] default_facet = {"ID"};
	protected String firstname, middlename, lastname; 
	protected String[] payloadArray;
	
	/*public NameVO(String n){
		this.createFacet(default_facet[0], new IDVO());
		this.payload = n;
		this.payloadArray = n.split(n, ' ');
	}*/
	
	public NameVO(String fn, String mn, String ln){
		this.createFacet(default_facet[0], new IDVO());
		this.firstname = fn;
		this.lastname = ln;
		this.middlename = mn;
		this.payload = this.firstname + " " + this.middlename + " " + this.lastname;
		this.payloadArray = new String[] {fn,mn,ln};
	}
	public String getName(){
		return this.payload;
	}
	
	public IDVO retrieveID(){
		return (IDVO)this.retrieveFacet(default_facet[0]);
	}
	
	public String[] getNameArray(){
        return this.payloadArray;
    }
}
