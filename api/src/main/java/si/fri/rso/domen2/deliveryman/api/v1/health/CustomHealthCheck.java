package si.fri.rso.domen2.deliveryman.api.v1.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import si.fri.rso.domen2.deliveryman.services.config.RestProperties;

@Liveness
@ApplicationScoped
public class CustomHealthCheck implements HealthCheck {

    @Inject
    private RestProperties rp;

    @Override
    public HealthCheckResponse call() {
        if(rp.getBroken()) {
            return HealthCheckResponse.down(CustomHealthCheck.class.getSimpleName());
        }
        else {
            return HealthCheckResponse.up(CustomHealthCheck.class.getSimpleName());
        }
    }
}