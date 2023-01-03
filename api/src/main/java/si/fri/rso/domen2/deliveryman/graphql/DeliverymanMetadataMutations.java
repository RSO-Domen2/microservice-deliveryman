package si.fri.rso.domen2.deliveryman.graphql;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import si.fri.rso.domen2.deliveryman.lib.DeliverymanMetadata;
import si.fri.rso.domen2.deliveryman.services.beans.DeliverymanMetadataBean;

@GraphQLClass
@ApplicationScoped
public class DeliverymanMetadataMutations {

    @Inject
    private DeliverymanMetadataBean dmb;

    @GraphQLMutation
    public DeliverymanMetadata addDeliverymanMetadata(
        @GraphQLArgument(name="deliverymanMetadata") DeliverymanMetadata dm
    ) {
        DeliverymanMetadata new_dm = dmb.createDeliverymanMetadata(dm);
        return new_dm;
    }

    @GraphQLMutation
    public DeleteResponse deleteDeliverymanMetadata(
        @GraphQLArgument(name="id") Integer id
    ) {
        return new DeleteResponse(dmb.deleteDeliverymanMetadata(id));
    }
}
