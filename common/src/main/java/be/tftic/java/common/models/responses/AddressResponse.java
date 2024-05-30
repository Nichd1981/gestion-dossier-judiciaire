package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Address;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressResponse {

    String street;
    String number;
    String city;
    String postCode;
    String country;
    String label;

    public static AddressResponse fromEntity(Address address) {
        return AddressResponse.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .city(address.getCity())
                .postCode(address.getPostcode())
                .country(address.getCountry())
                .label(address.getLabel())
                .build();
    }

}
