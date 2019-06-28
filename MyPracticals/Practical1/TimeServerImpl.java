package MyPracticals.Practical1; //time server

import java.util.Date;
import javax.jws.WebService;

/*
The @WebService property endpointInterface links SIB (this class) to the
SEI (MyPracticals.Practical1.TimeServer)

Note that these methods in the implementation are not annotated as @WebMethods
*/

@WebService(endpointInterface="MyPracticals.Practical1.TimeServer")
public class TimeServerImpl implements TimeServer{
	public String getTimeAsString() { return new Date().toString(); }
	public long getTimeAsElapsed(){ return new Date().getTime(); }
}

