package com.businessLogic;

import java.util.Iterator;
import java.util.List;

import com.omertron.thetvdbapi.model.Series;

public class ItemListScan {
	private List<Series> listSeries;

	public List<Series> getListSeries() {
		return listSeries;
	}

	public void setListSeries(List<Series> listSeries) {
		
		// Remove all fakes episodes
		Iterator<Series> ite = listSeries.iterator();
		while(ite.hasNext()) {
			Series serie = ite.next();
			if(serie.getSeriesName().startsWith("*")){
				ite.remove();
			}
		}
		
		this.listSeries = listSeries;
	}
}
