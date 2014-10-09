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
	
	public VOBase(Map<String, VOBase> facets){
		this.myFacets = facets;
	}
	
	public VOBase(){
		this.myFacets = new HashMap<String , VOBase>();
		this.payload = null;
	}
	
	//public JSON toJSON(){}
	
}
