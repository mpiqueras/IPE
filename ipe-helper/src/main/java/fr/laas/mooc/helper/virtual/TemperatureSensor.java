package fr.laas.mooc.helper.virtual;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TemperatureSensor extends VirtualSensor{
	
	private static String TEMPERATURE_SENSOR_DESC = 
			"<?xml version='1.0'?>"
			+ "<rdf:RDF xmlns:rdf='http://www.w3.org/1999/02/22-rdf-syntax-ns#'"
			+ " xmlns:rdfs='http://www.w3.org/2000/01/rdf-schema#'"
			+ " xmlns:dogont='http://elite.polito.it/ontologies/dogont.owl#'"
			+ " xmlns:ssn='http://www.w3.org/ns/ssn/'"
			+ "	xmlns:sosa=\"http://www.w3.org/ns/sosa/\""
			+ "	xmlns:mooc=\"http://w3id.org/laas-iot/mooc#\""
			+ " xmlns:iot-o='http://www.irit.fr/recherches/MELODI/ontologies/IoT-O#'"
			+ " xmlns:ex='http://example.org/ns#'"
			+ " xmlns:owl='http://www.w3.org/2002/07/owl#'>"
			+ "<sosa:Sensor rdf:about='http://w3id.org/laas-iot/mooc#SENSOR_URI'>"
			+ "<rdf:type rdf:resource='http://www.w3.org/ns/sosa/Sensor'/>"
			+ "<rdfs:label>A temperature sensor</rdfs:label>"
			+ "<sosa:observes rdf:resource='PROPERTY_URI'/>"
			+ "<iot-o:hasId>SENSOR_ID</iot-o:hasId>"
			+ "</sosa:Sensor>"
			+ "<owl:NamedIndividual rdf:about='PROPERTY_URI'>"
			+ "<rdf:type rdf:resource='http://w3id.org/laas-iot/mooc#Temperature'/>"
			+ "<ssn:isPropertyOf rdf:resource='FOI_URI'/>"
			+ "</owl:NamedIndividual>"
			+ "</rdf:RDF>";

	public TemperatureSensor(String id, T_Room foi) {
		super(id, foi);
	}

	@Override
	public float readValue() {
		return this.rand.nextFloat()*35.0f;
	}

	@Override
	public String getSensorDescriptor() {
		try {
			String descInstance = TemperatureSensor.TEMPERATURE_SENSOR_DESC
					.replaceAll("SENSOR_ID", this.getId())
					.replaceAll("SENSOR_URI", URLEncoder.encode(this.getId(), "utf-8"));
			if(this.getFoI().equals(T_Room.BEDROOM)){
				descInstance=descInstance
						.replaceAll("PROPERTY_URI", "http://w3id.org/laas-iot/mooc#BedroomTemperature")
						.replaceAll("FOI_URI", "http://w3id.org/laas-iot/mooc#Bedroom");
			} else if (this.getFoI().equals(T_Room.LIVINGROOM)){
				descInstance=descInstance
						.replaceAll("PROPERTY_URI", "http://w3id.org/laas-iot/mooc#LivingroomTemperature")
						.replaceAll("FOI_URI", "http://w3id.org/laas-iot/mooc#Livingroom");
			} else if (this.getFoI().equals(T_Room.GARDEN)){
				descInstance=descInstance
						.replaceAll("PROPERTY_URI", "http://w3id.org/laas-iot/mooc#GardenTemperature")
						.replaceAll("FOI_URI", "http://w3id.org/laas-iot/mooc#Garden");
			}
			return descInstance;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
