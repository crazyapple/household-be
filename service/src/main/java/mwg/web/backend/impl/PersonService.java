package mwg.web.backend.impl;

import mwg.web.backend.*;
import mwg.web.backend.dto.Mapper;
import mwg.web.backend.dto.PersonDTO;
import mwg.web.backend.entity.Household;
import mwg.web.backend.entity.Person;
import mwg.web.backend.exception.NotFoundException.HouseholdNotFoundException;
import mwg.web.backend.exception.NotFoundException.PersonNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements IPersonService {

    private final PersonRepository personRepository;

    private final HouseholdRepository householdRepository;

    private final Mapper mapper;

    public PersonService(PersonRepository personRepository, HouseholdRepository householdRepository, Mapper mapper) {
        this.personRepository = personRepository;
        this.householdRepository = householdRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        return personRepository
                .findAll()
                .stream().map(mapper::toPersonDTO)
                .toList();
    }

    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {
        Household household = householdRepository.findById(personDTO.getHouseholdId()).orElseThrow(() -> new HouseholdNotFoundException(personDTO.getHouseholdId()));

        Person person = Person.builder()
                .identification(personDTO.getIdentification())
                .name(personDTO.getName())
                .age(personDTO.getAge())
                .phoneNumber(personDTO.getPhoneNumber())
                .isHead(personDTO.getIsHead())
                .relationship(personDTO.getRelationship())
                .household(household)
                .build();

        return mapper.toPersonDTO(personRepository.save(person));
    }

    @Override
    public void deletePerson(Long personId) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
        personRepository.delete(person);
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        Person person = personRepository.findById(personDTO.getPersonId()).orElseThrow(() -> new PersonNotFoundException(personDTO.getPersonId()));
        person.setAge(personDTO.getAge());
        person.setName(personDTO.getName());
        person.setIdentification(personDTO.getIdentification());
        person.setPhoneNumber(personDTO.getPhoneNumber());
        person.setIsHead(personDTO.getIsHead());
        person.setRelationship(personDTO.getRelationship());

        return mapper.toPersonDTO(personRepository.save(person));
    }

    @Override
    public List<PersonDTO> findPersons(String key) {
        List<Person> persons = personRepository.findAllByNameContainsIgnoreCaseOrIdentificationContainsIgnoreCase(key, key);
        if (persons.isEmpty()) return new ArrayList<>();
        return persons.stream().map(mapper::toPersonDTO).toList();
    }
}
