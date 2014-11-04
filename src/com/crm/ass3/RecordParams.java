package com.crm.ass3;

import java.util.HashMap;

public class RecordParams extends RecordVO{
	public RecordParams(String targetID){
		this.myFacets = new HashMap<String , VOBase>();
		IDVO iv = new IDVO(targetID);
		this.createFacet(default_facet[5], (VOBase)iv);
	}
	
	public RecordParams(){
		this.myFacets = new HashMap<String , VOBase>();
	}
}
