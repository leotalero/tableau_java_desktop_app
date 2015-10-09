package co.sistemcobro.com.clases;



import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class CorbaTester {

    private static int run(ORB orb) {
        int retVal = 0;
        // works
        // String corbaName = "corbaname:iiop:127.0.0.1:44444/NameService";

        // everything gets a org.omg.CORBA.Transient exception
        // attempt to establish connection failed: java.net.ConnectException:
        // Connection refused: connect

        // works now!
        // String corbaName = "corbaname:iiop:10.10.20.194:2809/NameService";

        String corbaName = "corbaname:iiop:http://srvapp05.sistemcobro.com:28080/rrhh#nodes/co/sistemcobro/rrhh/ejb/EmpleadoEJB";

        // dies on the narrow
        // String corbaName =
        // "corbaname:iiop:10.10.20.194:2809/NameServiceServerRoot";

        // dies on the narrow
        // String corbaName = "corbaloc::10.10.20.194:2809/NameService";

        // dies on the string_to_object
        // org.omg.CORBA.BAD_PARAM: other: corbaname evaluation error vmcid: OMG
        // minor code: 10 completed: No
        // at
        // com.ooc.OB.CorbanameURLScheme_impl.parse_url(CorbanameURLScheme_impl.java:230)
        // at com.ooc.OB.URLRegistry_impl.parse_url(URLRegistry_impl.java:69)
        // at com.ooc.OB.ObjectFactory.stringToObject(ObjectFactory.java:107)
        // at com.ooc.OBCORBA.ORB_impl.string_to_object(ORB_impl.java:1349)
        // at org.wayland.test.CORBA.CorbaTester.run(CorbaTester.java:43)
        // at org.wayland.test.CORBA.CorbaTester.main(CorbaTester.java:61)
        // String corbaName =
        // "corbaname:iiop:10.10.20.194:2809/NameService#gov/fhie/dod2/GraphCollectionMgr";

        // String corbaName =
        // "corbaname::10.10.20.194:2809/NameService#gov/fhie/dod2/GraphCollectionMgr";

        // dies on the narrow with a different exception
        // org.omg.CORBA.OBJ_ADAPTER: vmcid: 0x4942f000 minor code: 2945
        // completed:
        // No
        // at com.ooc.OB.Util.unmarshalSystemException(Util.java:215)
        // at com.ooc.OB.GIOPConnection.processReply(GIOPConnection.java:652)
        // at com.ooc.OB.GIOPConnection.processMessage(GIOPConnection.java:424)
        // at
        // com.ooc.OB.GIOPConnectionThreaded.execReceive(GIOPConnectionThreaded.java:548)
        // at
        // com.ooc.OB.GIOPConnectionThreaded$ReceiverThread.run(GIOPConnectionThreaded.java:70)
        // String corbaName = "corbaname:iiop:10.10.20.194:2809/";

        // no good through tcp tunnel
        // String corbaName = "corbaname:iiop:127.0.0.1:8070/NameService";

        org.omg.CORBA.Object corbaObj = orb.string_to_object(corbaName);
        System.out.println(corbaObj.toString());

        // NamingContextExt namingContext =
        // NamingContextExtHelper.narrow(corbaObj);
        // System.out.println(namingContext.toString());
        return retVal;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Properties props = System.getProperties();
        props.put("org.omg.CORBA.ORBClass", "com.ooc.CORBA.ORB");
        props.put("org.omg.CORBA.ORBSingletonClass", "com.ooc.CORBA.ORBSingleton");

        int status = 0;
        ORB orb = null;
        try {
            orb = ORB.init(args, props);
            status = run(orb);
        }
        catch (Exception e) {
            e.printStackTrace();
            status = 1;
        }
        if (orb != null) {
            try {
                orb.destroy();
            }
            catch (Exception e) {
                e.printStackTrace();
                status = 1;
            }
        }
        System.exit(status);

    }

}
