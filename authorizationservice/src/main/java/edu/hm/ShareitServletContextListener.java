package edu.hm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import edu.hm.swa.sh.abc3.authorize.business.AuthManager;
import edu.hm.swa.sh.abc3.authorize.business.AuthManagerImpl;
import edu.hm.swa.sh.abc3.authorize.persistence.AuthPersistenceLayer;
import edu.hm.swa.sh.abc3.authorize.persistence.SimpleAuthPersistenceLayerImpl;
import edu.hm.swa.sh.abc3.authorize.persistence.transformer.UserTransformer;
import edu.hm.swa.sh.abc3.authorize.rest.AuthenticationService;
import edu.hm.swa.sh.abc3.authorize.rest.ServicePort;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Context Listener to enable usage of google guice together with jersey.
 *
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 */
public class ShareitServletContextListener extends GuiceServletContextListener {

    private static final Injector INJECTOR = Guice.createInjector(new ServletModule() {
        @Override
        protected void configureServlets() {
            bind(ServicePort.class);
            bind(UserTransformer.class);
            bind(AuthenticationService.class);
            bind(AuthManager.class).to(AuthManagerImpl.class);
            bind(AuthPersistenceLayer.class).to(SimpleAuthPersistenceLayerImpl.class);
            bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());
        }
    });

    @Override
    protected Injector getInjector() {
        return INJECTOR;
    }

    /**
     * This method is only required for the HK2-Guice-Bridge in the Application class.
     *
     * @return Injector instance.
     */
    static Injector getInjectorInstance() {
        return INJECTOR;
    }

}
