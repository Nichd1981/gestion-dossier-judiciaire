package be.tftic.java.common.models.requests.update;

import be.tftic.java.domain.entities.Address;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

public record AddressUpdateRequest(

        @NotBlank
        String street,
        @NotBlank @NumberFormat
        String number,
        @NotBlank
        String city,
        @NotBlank @NumberFormat
        String postCode,
        @NotBlank
        String country,
        @NotBlank
        String label

){

    public Address toEntity(){

        return Address.builder()
                .street(street)
                .number(number)
                .postcode(postCode)
                .city(city)
                .country(country)
                .label(label)
                .build();
    }

}
