package fr.laas.mooc.helper.sparql;

import java.util.HashMap;
import java.util.Map;

import fr.irit.melodi.sparql.exceptions.IncompleteSubstitutionException;
import fr.irit.melodi.sparql.exceptions.NotAFolderException;
import fr.irit.melodi.sparql.files.FolderManager;
import fr.laas.mooc.helper.virtual.Platform;

public class SparqlQueries {
	private FolderManager queries;
	
	public SparqlQueries(){
		try {
			this.queries = new FolderManager("queries");
			this.queries.loadQueries();
		} catch (NotAFolderException e) {
			e.printStackTrace();
		}
	}
	
	public String getSensorsQuery(){
		return this.queries.getQueries().get("get_sensors");
	}
	
	public String getBedroomSensorsQuery(){
		return this.queries.getQueries().get("get_sensors_bedroom");
	}
	
	public String getTemperatureSensorsQuery(){
		return this.queries.getQueries().get("get_temperature_sensors");
	}
}
