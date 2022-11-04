package si.fri.rso.domen2.deliveryman.lib;

import java.time.Instant;

public class DeliverymanMetadata {

    private Integer id;
    private String name;
    private String surname;
    private String vehicle;
    private Double lat;
    private Double lng;
    private Instant created;

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getVehicle() {
        return this.vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public Double getLat() {
        return this.lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return this.lng;
    }
    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Instant getCreated() {
        return this.created;
    }
    public void setCreated(Instant created) {
        this.created = created;
    }
}
