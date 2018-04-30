package fr.laas.om2m.ipe;


import fr.laas.mooc.helper.virtual.Platform;
import fr.laas.mooc.helper.virtual.SensorManager;
import fr.laas.mooc.helper.virtual.T_Room;
import fr.laas.mooc.helper.virtual.VirtualSensor;

/**
 * This is the interface of the sensor technology you want to connecect to OM2M in this activity
 */
public interface IVirtualSensor {
	/**
	 * This function creates an AE in OM2M associated to the created SensorManager object
	 * @param name is the id of the SensorManager
	 * @return the created SensorManager
	 */
	public SensorManager createSensorManager(String name);
	
	/**
	 * This function creates a Container under the sensor manager AE, associated to the platform created on the SensorManager
	 * @param sm is the id of the SensorManager
	 * @param platform is the platform 
	 */
	//name of the platform
	public void createPlatform(String sm, Platform platform);
	
	/**
	 * This function creates a Container under the platform Container, associated to the created virtual sensor object
	 * @param sm is the id of the SensorManager
	 * @param platform is the platform
	 * @param sensor is the id of the Container/virtual sensor
	 */
	public void addSensor(String sm, String platform, VirtualSensor sensor);
	
	/**
	 * This function creates a ContentInstance under the sensor Container, which content is the raw value of the observation.
	 * @param sm is the id of the SensorManager
	 * @param platform is the platform
	 * @param sensor is the id of the Container/virtual sensor
	 * @return the read value
	 */
	public float readSensor(String sm, String platform, String sensor);
	
}
