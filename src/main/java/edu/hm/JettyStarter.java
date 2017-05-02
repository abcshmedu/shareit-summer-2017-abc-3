package edu.hm;

import org.apache.tomee.embedded.Configuration;
import org.apache.tomee.embedded.Container;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.concurrent.CountDownLatch;

import static java.lang.Integer.parseInt;

/**
 * Start the application without an AppServer like tomcat.
 *
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 */
// CHECKSTYLE:OFF
public class JettyStarter {

//    private static final String APP_URL = "/";
//    private static final int PORT = 8082;
//    private static final String WEBAPP_DIR = "./src/main/webapp/";

    /**
     * Deploy local directories using Jetty without needing a container-based deployment.
     *
     * @param args unused
     * @throws Exception might throw for several reasons.
     */
    public static void main(String... args) throws Exception {
//        Server jetty = new Server(PORT);
//        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
//        jetty.start();
//        System.out.println("Jetty listening on port " + PORT);
//        jetty.join();

        String port = System.getenv("PORT");
//        String port = "8082";
        try (Container container = new Container(
                new Configuration().http(parseInt(port)))
                .deployClasspathAsWebApp("/", null)) {
            new CountDownLatch(1).await();
        }
    }

}
