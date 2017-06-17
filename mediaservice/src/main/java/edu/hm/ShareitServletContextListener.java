package edu.hm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import edu.hm.swa.sh.abc3.mediaservice.business.MediaService;
import edu.hm.swa.sh.abc3.mediaservice.business.MediaServiceBean;
import edu.hm.swa.sh.abc3.mediaservice.persistence.PersistenceLayer;
import edu.hm.swa.sh.abc3.mediaservice.persistence.simplepersistence.PersistenceLayerBean;
import edu.hm.swa.sh.abc3.mediaservice.rest.BookService;
import edu.hm.swa.sh.abc3.mediaservice.rest.DiscService;
import edu.hm.swa.sh.abc3.mediaservice.rest.RestService;

/**
 * Context Listener to enable usage of google guice together with jersey.
 *
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 */
public class ShareitServletContextListener extends GuiceServletContextListener {

    private static final Injector INJECTOR = Guice.createInjector(new ServletModule() {
        @Override
        protected void configureServlets() {
            bind(RestService.class);
            bind(MediaService.class).to(MediaServiceBean.class);
            bind(BookService.class);
            bind(DiscService.class);
            bind(PersistenceLayer.class).to(PersistenceLayerBean.class);
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
