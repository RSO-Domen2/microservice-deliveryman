package si.fri.rso.domen2.deliveryman.api.v1.resources;

import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.kumuluz.ee.logs.cdi.Log;

import si.fri.rso.domen2.deliveryman.services.beans.DeliverymanMetadataBean;
import si.fri.rso.domen2.deliveryman.lib.DeliverymanMetadata;
import si.fri.rso.domen2.deliveryman.lib.DeliverymanMetadataValidator;

@Log
@ApplicationScoped
@Tag(name = "Deliveryman Metadata", description = "Get, add, edit, and delete the Deliveryman metadata.")
@Path("/deliveryman")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeliverymanMetadataResource {
    
    private final Logger LOG = Logger.getLogger(DeliverymanMetadataResource.class.getName());

    @Inject
    private DeliverymanMetadataBean dmb;

    @Context
    protected UriInfo uriInfo;


    @Operation(summary = "Get all metadata", description = "Get all Deliveryman metadata")
    @APIResponses({
        @APIResponse(responseCode = "200",
            description = "List of Deliveryman metadata",
            content = @Content(schema = @Schema(implementation = DeliverymanMetadata.class, type = SchemaType.ARRAY)),
            headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
        )})
    @GET
    public Response getDeliverymanMetadata() {
        this.LOG.info("GET "+uriInfo.getRequestUri().toString());
        List<DeliverymanMetadata> listDm = dmb.getDeliverymanMetadataFilter(uriInfo);
        // List<DeliverymanMetadata> listDm = dmb.getDeliverymanMetadata();
        return Response.status(Response.Status.OK)
            .header("X-Total-Count", listDm.size())
            .entity(listDm).build();
    }


    @Operation(description = "Get metadata for a Deliveryman.", summary = "Get metadata")
    @APIResponses({
        @APIResponse(responseCode = "200",
            description = "Deliveryman metadata",
            content = @Content(schema = @Schema(implementation = DeliverymanMetadata.class))),
        @APIResponse(responseCode = "404", description = "Invalid deliverymanId")
    })
    @GET
    @Path("/{deliverymanId}")
    public Response getDeliverymanMetadata(
        @Parameter(description = "Deliveryman metadata ID.", required = true)
        @PathParam("deliverymanId") Integer deliverymanId) {
        
        this.LOG.info("GET "+uriInfo.getRequestUri().toString());

        DeliverymanMetadata dm = dmb.getDeliveryMetadata(deliverymanId);
        if(dm == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(dm).build();
    }


    @Operation(description = "Add Deliveryman metadata.", summary = "Add metadata")
    @APIResponses({
        @APIResponse(responseCode = "201",
        description = "Metadata successfully added.",
        content = @Content(schema = @Schema(implementation = DeliverymanMetadata.class))),
        @APIResponse(responseCode = "406", description = "Validation error.")
    })
    @POST
    public Response createDeliverymanMetadata(
        @RequestBody(description = "DTO object with deliveryman metadata.",
            required = true,
            content = @Content(schema = @Schema(implementation = DeliverymanMetadata.class)))
        DeliverymanMetadata dm) {

        this.LOG.info("POST "+uriInfo.getRequestUri().toString());

        if(!DeliverymanMetadataValidator.isValid(dm)) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        } else {
            dm.setCreated(Instant.now());
            dm = dmb.createDeliverymanMetadata(dm);
        }
        return Response.status(Response.Status.CREATED).entity(dm).build();
    }


    @Operation(description = "Update Deliveryman metadata.", summary = "Update metadata")
    @APIResponses({
        @APIResponse(responseCode = "200",
        description = "Metadata successfully updated.",
        content = @Content(schema = @Schema(implementation = DeliverymanMetadata.class))),
        @APIResponse(responseCode = "404", description = "Metadata not found."),
    })
    @PUT
    @Path("/{deliverymanId}")
    public Response putImageMetadata(
        @Parameter(description = "Metadata ID.", required = true)
        @PathParam("deliverymanId")
            Integer deliverymanId,
        @RequestBody(description = "DTO object with Deliveryman metadata.",
            required = true,
            content = @Content(schema = @Schema(implementation = DeliverymanMetadata.class)))
            DeliverymanMetadata dm) {
        
        this.LOG.info("PUT "+uriInfo.getRequestUri().toString());
        
        dm.setCreated(Instant.now());
        dm = dmb.putDeliverymanMetadata(deliverymanId, dm);

        if(dm == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(dm).build();
    }


    @Operation(description = "Delete Deliveryman metadata.", summary = "Delete metadata")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Metadata successfully deleted."),
            @APIResponse(responseCode = "404", description = "Metadata not found.")
    })
    @DELETE
    @Path("/{deliverymanId}")
    public Response deleteImageMetadata(
        @Parameter(description = "Metadata ID.", required = true)
        @PathParam("deliverymanId")
            Integer deliverymanId) {
        
        this.LOG.info("DELETE "+uriInfo.getRequestUri().toString());

        boolean deleted = dmb.deleteDeliverymanMetadata(deliverymanId);

        if(deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
