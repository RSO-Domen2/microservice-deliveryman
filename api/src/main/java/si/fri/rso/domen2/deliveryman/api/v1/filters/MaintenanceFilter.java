package si.fri.rso.domen2.deliveryman.api.v1.filters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import si.fri.rso.domen2.deliveryman.services.config.RestProperties;

@Provider
@ApplicationScoped
public class MaintenanceFilter implements ContainerRequestFilter {
    
    @Inject
    private RestProperties rp;

    @Override
    public void filter(ContainerRequestContext ctx) {
        if(rp.getMaintenanceMode()) {
            ctx.abortWith(
                Response.status(Response.Status.FORBIDDEN)
                .entity("{'message': 'Maintenance mode enabled'}")
                .build());
        }
    }
}
