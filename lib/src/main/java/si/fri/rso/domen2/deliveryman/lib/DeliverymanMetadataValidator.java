package si.fri.rso.domen2.deliveryman.lib;

public class DeliverymanMetadataValidator {
    
    public static boolean isValid(DeliverymanMetadata dm) {
        String vehicle = dm.getVehicle();
        return (
            // dm.getName() != null &&
            // dm.getSurname() != null &&
            vehicle != null && (
                vehicle.equals("car") ||
                vehicle.equals("bike") ||
                vehicle.equals("none")
                ) &&
            dm.getLat() != null && dm.getLng() != null
        );
    }
}
