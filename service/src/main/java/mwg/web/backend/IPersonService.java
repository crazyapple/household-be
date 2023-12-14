package mwg.web.backend;

import mwg.web.backend.dto.PersonDTO;

import java.util.List;

public interface IPersonService {
    List<PersonDTO> getAllPersons();

    PersonDTO addPerson(PersonDTO personDTO);

    void deletePerson(Long personId);

    PersonDTO updatePerson(PersonDTO person);

    List<PersonDTO> findPersons(String key);
}
