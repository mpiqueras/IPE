A client for Eclipse OM2M basic functionnalities
========================================

# What is it, and what is it not ?

This client is targeted at Eclipse OM2M new users in priority. It exposes functionnalities of the platform required to deploy a basic application. The purpose is not to offer all the capabilities of Eclipse OM2M, but rather to allow the rapid development of test clients to assess the capabilities of the platform before going in depth into its actual set of functionnalities.

- __It is a REST client that will interact with the OM2M platform__
- __It is not a complete OM2M implementation, and only works with a deployed OM2M platform__
- __It is not fully compatible with the open-source version of OM2M, where the semantic functionnalities are not available yet.__

# Copy/paste, here you go

## Installation
- `git clone https://framagit.org/NSeydoux/om2m-client.git`
- `mvn install`

## pom.xml dependancy
```xml
<dependency>
    <groupId>fr.laas.om2m</groupId>
    <artifactId>om2m-client</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Basic example

### The main function
```java
OM2MClient client = ClientFactory.createClient(new File("example-config.json"));
AE ae = client.createAE("TEST_AE", "in-cse/", "appId");
Container cnt = client.createContainer("DATA", ae.getResourceID());
ContentInstance cin = client.createContentInstance("/in-cse/in-name/TEST_AE/DATA", "Some data");
client.createSemanticDescriptor("SMD", cnt.getResourceID(), "TODO : Replace with a well-formed RDF descriptor");
TestObserver obs = new TestObserver();
client.observe(obs);
Subscription subs = client.subscribe("SUBS", cnt.getResourceID());
client.createContentInstance("/in-cse/in-name/TEST_AE/DATA", "Some other data");
// List of the resources matching the query
System.out.println(client.performSemanticDiscovery("TODO : Replace with a well-formed SPARQL query"));
```

### The TestObserver class
```java
package test;

import org.eclipse.om2m.commons.resource.Notification;
import fr.laas.om2m.client.NotificationObserver;

public class TestObserver implements NotificationObserver{
    public void notify(Notification n) {
        System.out.println("Received a notification");  
    }
}
```

### The json configuration
```json
{
    "name":"test",
    "instanceURL":"http://localhost:8080/~/",
    "cseId":"in-cse/",
    "cseName":"in-name/",
    "notificationPort":9555 
}
```

# Functionalities

## Basic use

### Resource management
- _Managed resources:_ The client allows the management of four resource types: AE (Application Entity, representing an application), Container (where data is stored), Content Instance (resource representing the actual data), and Semantic Descriptor.
- _Allowed operations:_ The resources listed above can be created and deleted.

### Semantic discovery
- Any resource associated to a semantic descriptor can be identified through a semantic discovery mechanism. A SPARQL query is issued to a resource of the platform, and all its sub-resources having a descriptor will be tested against the query. The URI of the resources which descriptor matches the query are returned to the user.

## Medium use

### Subscription and notification
- The client offers a subscribe operation, assorted to an observer/observable pattern: the client subscribes to a oneM2M resource, and when a notification is received, it is redirected to any object implementing the NotificationObserver interface that registered to the "observe" method of the OM2MClient.
- _Generic resource retrieval:_ Any type of resource can be retrieved by the client. However, the user will need to look deeper into the API in order to see the available resource types and their respective attributes.

## Advanced use
- _Generic request issuing:_ The oneM2M standard has a generic request specification, that is bound to specific implementations by standard bindings (HTTP, COAP, MQTT...)

