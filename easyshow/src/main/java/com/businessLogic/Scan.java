package com.businessLogic;

import java.util.HashMap;

public class Scan {
	
	private HashMap<String, ItemListScan> listShows = new HashMap<String, ItemListScan>();

	public HashMap<String, ItemListScan> getListShows() {
		return listShows;
	}

	public void setListShows(HashMap<String, ItemListScan> listShows) {
		this.listShows = listShows;
	}
}
