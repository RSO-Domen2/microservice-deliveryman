package si.fri.rso.domen2.deliveryman.models.converters;

import si.fri.rso.domen2.deliveryman.lib.DeliverymanMetadata;
import si.fri.rso.domen2.deliveryman.models.entities.DeliverymanMetadataEntity;

public class DeliverymanMetadataConverter {

    public static DeliverymanMetadata toDto(DeliverymanMetadataEntity entity) {
        DeliverymanMetadata dto = new DeliverymanMetadata();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setVehicle(entity.getVehicle());
        dto.setLat(entity.getLat());
        dto.setLng(entity.getLng());
        dto.setHourlyPay(entity.getHourlyPay());
        dto.setTransportPrice(entity.getTransportPrice());
        dto.setCreated(entity.getCreated());
        return dto;
    }
    
    public static DeliverymanMetadataEntity toEntity(DeliverymanMetadata dto) {
        DeliverymanMetadataEntity entity = new DeliverymanMetadataEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setVehicle(dto.getVehicle());
        entity.setLat(dto.getLat());
        entity.setLng(dto.getLng());
        entity.setHourlyPay(dto.getHourlyPay());
        entity.setTransportPrice(dto.getTransportPrice());
        entity.setCreated(dto.getCreated());
        return entity;
    }
}
