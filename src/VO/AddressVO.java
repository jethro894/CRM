package VO;

public class AddressVO extends VOBase{
	protected final String[] default_facet = {"ID"};
	protected String AddressLine1, AddressLine2, City, State, Country, Zip;
	protected String[] payloadArray;
	
	public AddressVO(String a1, String a2, String ct, String s, String c, String z){
		this.createFacet(default_facet[0], new IDVO());
		this.AddressLine1 = a1;
		this.AddressLine2 = a2;
		this.City = ct;
		this.State = s;
		this.Country = c;
		this.Zip = z;
		this.payload = this.AddressLine1 + "\n" + this.AddressLine2 + "\n" + this.City + ", " + this.State + ", " + this.Zip + "\n" + this.Country;
		this.payloadArray = new String[] {a1,a2,ct,s,z,c};
	}
	
	/*public AddressVO(String addr){
		this.createFacet(default_facet[0], new IDVO());
		this.payload = addr;
		this.payloadArray = addr.split(",|\n");
	}*/
	public IDVO retrieveID(){
		return (IDVO)this.retrieveFacet(default_facet[0]);
	}
	
	public String getAddress(){
		return this.payload;
	}
	
	public String[] getAddressArray(){
        return this.payloadArray;
    }
}
