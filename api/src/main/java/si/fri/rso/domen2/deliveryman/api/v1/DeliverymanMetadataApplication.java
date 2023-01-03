package si.fri.rso.domen2.deliveryman.api.v1;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        title = "Deliveryman catalog API",
        version = "v1",
        contact = @Contact(name = "Domen Mohorcic", email = "dm4492@student.uni-lj.si"),
        license = @License(name = "dev"),
        description = "API for managing Deliveryman metadata."),
    servers = {
        @Server(url = "/", description = "Current server"),
        @Server(url = "http://localhost:8080/", description = "Localhost")
    })
@ApplicationPath("/v1")
public class DeliverymanMetadataApplication extends Application {

}
