package ro.pata.test.ejb.jndi.lookup;

import javax.naming.*;
import java.util.Properties;

public class LookerUp {

    private Properties prop = new Properties();
    private String jndiPrefix;

    // Same WAR
    public LookerUp(){
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    }

    // For Remote Views.
    // Client and EJBs are :
    // either in different Containers
    // either in the same container but packaged in different applications
    public LookerUp(String address, int httpPort){

        // If Client and EJBs are in different Containers then
        // We must setup userName and password in jboss-ejb-client.xml file
        // This file must be configured in the client side.

        String protocol;
        protocol = "http-remoting";
        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        prop.put(Context.PROVIDER_URL, protocol+"://"+address+":"+httpPort+"/");
        prop.put("jboss.naming.client.ejb.context", true);
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiPrefix="ejb";

    }

    // ...
    public Object findRemoteSessionBean(SessionBeanType beanType, String earName, String moduleName, String deploymentDistinctName, String beanName, String interfaceFullQualifiedName) throws NamingException{

        String suffix = "";
        if(beanType.equals(SessionBeanType.STATEFUL)){
            suffix = "?stateful";
        }

        final Context context = new InitialContext(prop);

        //List all available JNDI resources
        /*
        NamingEnumeration<NameClassPair> list = context.list("");
        while (list.hasMore()) {
            System.out.println(" -- "+list.next().getName());
        }
        */

        Object object = context.lookup(jndiPrefix+":"+earName+"/"+moduleName+"/"+deploymentDistinctName+"/"+beanName+"!"+interfaceFullQualifiedName+suffix);
        context.close();

        return object;
    }

    public Object findLocalSessionBean(String moduleName, String beanName, String interfaceFullQualifiedName) throws NamingException{

        final Context context = new InitialContext(prop);
        Object object = context.lookup("java:global/"+moduleName+"/"+beanName+"!"+interfaceFullQualifiedName);
        context.close();

        return object;
    }


    public Object findSessionBean(String jndiName) throws NamingException{

        final Context context = new InitialContext(prop);
        Object object = context.lookup(jndiName);
        context.close();

        return object;
    }

    public enum SessionBeanType {
        STATEFUL,STATELESS,SINGLETON
    }
}
