package fr.laas.om2m.ipe;

import java.util.Collection;

import org.eclipse.om2m.commons.resource.AE;
import org.eclipse.om2m.commons.resource.Container;
import org.eclipse.om2m.commons.resource.ContentInstance;
import org.eclipse.om2m.commons.resource.SemanticDescriptor;

import fr.laas.mooc.helper.http.HTTPPost;
import fr.laas.mooc.helper.om2m.ResourceCreator;
import fr.laas.mooc.helper.om2m.Serializer;
import fr.laas.mooc.helper.virtual.Platform;
import fr.laas.mooc.helper.virtual.SensorManager;
import fr.laas.mooc.helper.virtual.T_Room;
import fr.laas.mooc.helper.virtual.VirtualSensor;

public class IPE implements IVirtualSensor {
	static String AE_ID = "IPE_Jarvinsa";
	
	private SensorManager sm;
	
	@Override
	public SensorManager createSensorManager(String name) {
		this.sm = new SensorManager(name);
		// TODO Stub to be completed, create the appropriate resource in the OM2M platform
		AE ae = ResourceCreator.createAE(name, AE_ID); 
		return sm;
	}

	@Override
	public void createPlatform(String sm, Platform platform) {
		this.sm.addPlatform(platform);
		// TODO Stub to be completed
		//Container plateforme = ResourceCreator.createContainer(platform.getName());
		//plateforme.setParentID(sm);
		ResourceCreator.createContainer(platform.getName()).setParentID(sm);
	}

	@Override
	public void addSensor(String sm, String platform, VirtualSensor sensor) {
		this.sm.addSensor(platform, sensor);
		// TODO Stub to be completed
		ResourceCreator.createContainer(sensor.getId()).setParentID(platform);
		//container doit être sous container plateforme?
	}

	@Override
	public float readSensor(String sm, String platform, String sensor) {
		float value=0.0f;
		// TODO Stub to be completed
		return value;
	}
	
}
