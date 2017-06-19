package edu.hm;

import edu.hm.swa.sh.abc3.mediaservice.rest.RestService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;

/**
 * Application class to enable guice within jersey.
 *
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 */
// CHECKSTYLE:OFF
public class ShareItApplication extends ResourceConfig {

    @Inject
    public ShareItApplication(ServiceLocator serviceLocator) {
        packages(RestService.class.getPackage().getName());
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(ShareitServletContextListener.getInjectorInstance());
    }

}