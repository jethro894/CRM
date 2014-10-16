package com.crm.ass3;

import java.util.HashMap;

public class CustomerParams extends CustomerVO{
	
	public CustomerParams(String targetID){
		this.myFacets = new HashMap<String , VOBase>();
		IDVO iv = new IDVO(targetID);
		this.createFacet(default_facet[0], (VOBase)iv);
	}

}
