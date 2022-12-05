package si.fri.rso.domen2.deliveryman.services.beans;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.kumuluz.ee.rest.utils.QueryStringDefaults;

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


    public List<DeliverymanMetadata> getDeliverymanMetadataFilter(UriInfo uriInfo) {
        QueryParameters qp = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();
        return JPAUtils.queryEntities(em, DeliverymanMetadataEntity.class, qp).stream().map(DeliverymanMetadataConverter::toDto).collect(Collectors.toList());
    }


    public DeliverymanMetadata getDeliveryMetadata(Integer id) {
        DeliverymanMetadataEntity dme = em.find(DeliverymanMetadataEntity.class, id);
        if(dme == null) {
            // throw new NotFoundException();
            return null;
        }
        DeliverymanMetadata dm = DeliverymanMetadataConverter.toDto(dme);
        return dm;
    }


    public DeliverymanMetadata createDeliverymanMetadata(DeliverymanMetadata dm) {
        DeliverymanMetadataEntity dme = DeliverymanMetadataConverter.toEntity(dm);
        try {
            beginTx();
            em.persist(dme);
            commitTx();
        } catch(Exception e) {
            rollbackTx();
        }

        if(dme.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }
        return DeliverymanMetadataConverter.toDto(dme);
    }


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
            rollbackTx();
        }
        return DeliverymanMetadataConverter.toDto(updatedDme);
    }


    public boolean deleteDeliverymanMetadata(Integer id) {
        DeliverymanMetadataEntity dme = em.find(DeliverymanMetadataEntity.class, id);
        if(dme != null) {
            try {
                beginTx();
                em.remove(dme);
                commitTx();
            } catch(Exception e) {
                rollbackTx();
            }
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
