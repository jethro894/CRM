package com.crm.ass3;

import java.util.HashMap;
import java.util.Map;

public class VOBase {

	protected Map<String, VOBase> myFacets;
	protected String payload;
	
	public void createFacet(String facetName, VOBase v){
		myFacets.put(facetName, v);
	}
	
	public void deleteFacet(String facetName){
		myFacets.remove(facetName);
	}
	
	public VOBase retrieveFacet(String facetName){
		VOBase result = null;
		result = myFacets.get(facetName);
		return result;
	}
	
	public String getPayload(){
		return this.payload;
	}
	
	public void print(){
		for(VOBase v : myFacets.values()){
			System.out.println(v.payload);
		}
	}
	
	public VOBase(Map<String, VOBase> facets){
		this.myFacets = facets;
	}
	
	public VOBase(){
		this.myFacets = new HashMap<String , VOBase>();
		this.payload = null;
	}

	//public toJSON(){
	//	for(String facet : myFacets.keySet()){
	//		this.retrievefacet().getPayload();
	
	//public JSON toJSON(){}
/*	public void toJSON(){
		for(String facet : myFacets.keySet()){
			if (facet.equals("AddressVO")){
				AddressVO av = (AddressVO)this.retrieveFacet(facet);
				=av.AddressLine1
}
			
	
	//public JSON toJSON(){}
	
}
*/
	
}
