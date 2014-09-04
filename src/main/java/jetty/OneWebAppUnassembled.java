package jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class OneWebAppUnassembled
{
    public static void main(String[] args) throws Exception
    {
    	String root = "E:\\codebk";
    	String jetty_home = System.getProperty("jetty.home",root);
        Server server = new Server(8080);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(jetty_home+"/mstore_api.war");
 
        server.setHandler(webapp);
 
        server.start();
        server.join();
    }
}