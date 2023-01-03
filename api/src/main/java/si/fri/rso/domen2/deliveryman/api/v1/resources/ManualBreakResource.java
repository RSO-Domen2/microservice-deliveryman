package si.fri.rso.domen2.deliveryman.api.v1.resources;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import si.fri.rso.domen2.deliveryman.services.config.RestProperties;

@ApplicationScoped
@Tag(name = "Manual operations", description = "Set certain parameters manually.")
@Path("/manual")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ManualBreakResource {
    
    private final Logger LOG = Logger.getLogger(ManualBreakResource.class.getName());

    @Inject
    private RestProperties rp;

    @GET
    public Response getHealthy() {
        return Response.status(Response.Status.OK).entity(String.format("{isBroken: %b}", rp.getBroken())).build();
    }

    @POST
    @Path("break")
    public Response makeUnhealthy() {
        rp.setBroken(true);
        return Response.status(Response.Status.OK).entity("{isBroken: true}").build();
    }

    @POST
    @Path("unbreak")
    public Response makeHealthy() {
        rp.setBroken(false);
        return Response.status(Response.Status.OK).entity("{isBroken: false}").build();
    }
}
