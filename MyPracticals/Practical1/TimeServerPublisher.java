package MyPracticals.Practical1;
import javax.xml.ws.Endpoint;

/*
This application publishes the web service whose
SIB is ch01.ts.TimeServerImpl

For now the web service is published at network address 127.0.0.1
which is the localhost and at port number 9876 as this port 
is most likely available on any desktop machine

The publication path is /ts , an arbitrary name

The Endpoint class has an overloaded  publish method.
In this two argument-version, the publication URL as a string, the second argument is the instance
of the service Implementation Bean (SIB) which is ch01.ts.TimeServerImpl

The application runs indefinitely, awaiting service requests.
It needs to be terminated with a Ctrl-C command or likewise

Once the application is started, open the browser to the URL
http://127.0.0.1:9876/ts?wsdl
to view the service contract, the WSDL document.

This is an easy way to determine whether the service has deployed successfully.

If the test succeeds, then the client can be executed against the service.

*/

public class TimeServerPublisher{
	public static void main(String[] args){
		//1st argument is the publication URL
		//2nd argument is a SIB instance
		Endpoint.publish("http://127.0.0.1:9876/myts", new TimeServerImpl());
	}
}
