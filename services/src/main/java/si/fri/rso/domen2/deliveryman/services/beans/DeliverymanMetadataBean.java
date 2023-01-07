package si.fri.rso.domen2.deliveryman.services.beans;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import si.fri.rso.domen2.deliveryman.lib.DeliverymanMetadata;
import si.fri.rso.domen2.deliveryman.models.entities.DeliverymanMetadataEntity;
import si.fri.rso.domen2.deliveryman.models.converters.DeliverymanMetadataConverter;

@RequestScoped
public class DeliverymanMetadataBean {
    
    private Logger LOG = Logger.getLogger(DeliverymanMetadataBean.class.getName());

    @Inject
    private EntityManager em;

    public List<DeliverymanMetadata> getDeliverymanMetadata() {
        TypedQuery<DeliverymanMetadataEntity> query = em.createNamedQuery("DeliverymanMetadataEntity.getAll", DeliverymanMetadataEntity.class);
        List<DeliverymanMetadataEntity> rl = query.getResultList();
        return rl.stream().map(DeliverymanMetadataConverter::toDto).collect(Collectors.toList());
    }

    @Timed(name= "time-get-deliveryman-all")
    @Metered(name = "get-deliveryman-all")
    public List<DeliverymanMetadata> getDeliverymanMetadataFilter(UriInfo uriInfo) {
        this.LOG.info("Function call getDeliverymanMetadataFilter");
        QueryParameters qp = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();
        return JPAUtils.queryEntities(em, DeliverymanMetadataEntity.class, qp).stream().map(DeliverymanMetadataConverter::toDto).collect(Collectors.toList());
    }


    @Metered(name = "get-deliveryman")
    public DeliverymanMetadata getDeliveryMetadata(Integer id) {
        DeliverymanMetadataEntity dme = em.find(DeliverymanMetadataEntity.class, id);
        if(dme == null) {
            this.LOG.warning("Function call getDeliveryMetadata ID "+id.toString()+" FAILED");
            // throw new NotFoundException();
            return null;
        }
        this.LOG.info("Function call getDeliveryMetadata ID "+id.toString());
        DeliverymanMetadata dm = DeliverymanMetadataConverter.toDto(dme);
        return dm;
    }

    @Metered(name = "post-deliveryman")
    public DeliverymanMetadata createDeliverymanMetadata(DeliverymanMetadata dm) {
        DeliverymanMetadataEntity dme = DeliverymanMetadataConverter.toEntity(dm);
        try {
            beginTx();
            em.persist(dme);
            commitTx();
        } catch(Exception e) {
            this.LOG.warning("Function call createDeliverymanMetadata FAILED");
            rollbackTx();
        }

        if(dme.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }
        this.LOG.info("Function call createDeliverymanMetadata");
        return DeliverymanMetadataConverter.toDto(dme);
    }


    @Metered(name = "put-deliveryman")
    public DeliverymanMetadata putDeliverymanMetadata(Integer id, DeliverymanMetadata dm) {
        DeliverymanMetadataEntity dme = em.find(DeliverymanMetadataEntity.class, id);
        if(dme == null) {
            return null;
        }
        DeliverymanMetadataEntity updatedDme = DeliverymanMetadataConverter.toEntity(dm);
        try {
            beginTx();
            updatedDme.setId(dme.getId());
            updatedDme = em.merge(updatedDme);
            commitTx();
        } catch(Exception e) {
            this.LOG.warning("Function call putDeliverymanMetadata FAILED");
            rollbackTx();
        }
        this.LOG.info("Function call putDeliverymanMetadata");
        return DeliverymanMetadataConverter.toDto(updatedDme);
    }


    @Metered(name = "delete-deliveryman")
    public boolean deleteDeliverymanMetadata(Integer id) {
        DeliverymanMetadataEntity dme = em.find(DeliverymanMetadataEntity.class, id);
        if(dme != null) {
            try {
                beginTx();
                em.remove(dme);
                commitTx();
            } catch(Exception e) {
                this.LOG.warning("Function call deleteDeliverymanMetadata FAILED");
                rollbackTx();
                return false;
            }
            this.LOG.info("Function call deleteDeliverymanMetadata");
            return true;
        } else {
            return false;
        }
    }


    private void beginTx() {
        if(!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if(em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if(em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
