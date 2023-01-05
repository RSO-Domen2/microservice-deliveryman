package si.fri.rso.domen2.deliveryman.graphql;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.graphql.classes.Filter;
import com.kumuluz.ee.graphql.classes.Pagination;
import com.kumuluz.ee.graphql.classes.Sort;
import com.kumuluz.ee.graphql.utils.GraphQLUtils;
import com.kumuluz.ee.rest.utils.StreamUtils;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import si.fri.rso.domen2.deliveryman.lib.DeliverymanMetadata;
import si.fri.rso.domen2.deliveryman.services.beans.DeliverymanMetadataBean;

@GraphQLClass
@ApplicationScoped
public class DeliverymanMetadataQueries {
    
    @Inject
    private DeliverymanMetadataBean dmb;

    @GraphQLQuery
    public List<DeliverymanMetadata> allDeliverymanMetadata(
        @GraphQLArgument(name="pagination") Pagination pagination,
        @GraphQLArgument(name="sort") Sort sort,
        @GraphQLArgument(name="filter") Filter filter
    ) {
        return StreamUtils.queryEntities(dmb.getDeliverymanMetadata(), GraphQLUtils.queryParameters(pagination, sort, filter));
    }

    @GraphQLQuery
    public DeliverymanMetadata getDeliverymanMetadata(
        @GraphQLArgument(name="id") Integer id
    ) {
        return dmb.getDeliveryMetadata(id);
    }
}
