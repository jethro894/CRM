package com.crm.ass3;

public class NameVO extends VOBase{
	protected String firstname, middlename, lastname;
    protected String[] payloadArray;
	
	public NameVO(String n){
		this.payload = n;
        this.payloadArray = n.split(' ');
	}
	
	public NameVO(String fn, String mn, String ln){
		this.firstname = fn;
		this.lastname = ln;
		this.middlename = mn;
		this.payload = this.firstname + " " + this.middlename + " " + this.lastname;
        this.payloadArray = [fn,mn,ln]
	}
	public String getName(){
		return this.payload;
	}
    public String[] getNameArray(){
        return this.payloadArray;
    }
}
