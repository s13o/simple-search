package s13o.test.search.server;

/**
 * @author {@link "mailto:roman.solodovnichenko@playtech.com" "romanso"}
 * @since 2/20/2018
 */

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Api.class);
    }

}