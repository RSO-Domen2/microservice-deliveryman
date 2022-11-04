package si.fri.rso.domen2.deliveryman.models.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "deliveryman_metadata")
@NamedQueries(value = {
    @NamedQuery(name = "DeliverymanMetadataEntity.getAll",
        query = "SELECT dme FROM DeliverymanMetadataEntity dme")
})
public class DeliverymanMetadataEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    private String surname;

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "vehicle")
    private String vehicle;

    public String getVehicle() {
        return this.vehicle;
    }
    public void setVehicle(String vehicle) {
        // is check here?
        /* if(vehicle.equals("car") || vehicle.equals("bicycle") || vehicle.equals("none")) {
            
        } */
        this.vehicle = vehicle;
    }

    @Column(name = "lat")
    private Double lat;

    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Column(name = "lng")
    private Double lng;

    public Double getLng() {
        return this.lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    /* @Column(name = "created")
    private Instant created;

    public Instant getCreated() {
        return this.created;
    }
    public void setCreated(Instant created) {
        this.created = created;
    } */
}
