package be.tftic.java.bll.services;

import be.tftic.java.domain.entities.Person;

public interface PersonService {

    Person findById(Long id);

    Person findByNationalRegister(String nationalRegister);

    Long update(Long id, Person person);

}
