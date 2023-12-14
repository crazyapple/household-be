package mwg.web.backend.controller;

import mwg.web.backend.APIResponse;
import mwg.web.backend.dto.PersonDTO;
import mwg.web.backend.impl.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public ResponseEntity<APIResponse<List<PersonDTO>>> getAllPersons() {
        APIResponse<List<PersonDTO>> response = APIResponse.<List<PersonDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all persons successfully!")
                .metadata(personService.getAllPersons())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/persons")
    public ResponseEntity<APIResponse<PersonDTO>> addPerson(@RequestBody PersonDTO personDTO) {
        PersonDTO person = personService.addPerson(personDTO);

        APIResponse<PersonDTO> response = APIResponse.<PersonDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Person #%d is created successfully!", person.getPersonId()))
                .metadata(person)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<APIResponse<String>> deletePerson(@PathVariable(name = "id") Long personId) {
        personService.deletePerson(personId);

        APIResponse<String> response = APIResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Person #%d is deleted successfully!", personId))
                .metadata("DELETED")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/persons")
    public ResponseEntity<APIResponse<PersonDTO>> updatePerson(@RequestBody PersonDTO personDTO) {
        PersonDTO person = personService.updatePerson(personDTO);

        APIResponse<PersonDTO> response = APIResponse.<PersonDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Person #%d is updated successfully!", person.getPersonId()))
                .metadata(person)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/persons/{key}")
    public ResponseEntity<APIResponse<List<PersonDTO>>> findPersons(@PathVariable(name = "key") String key) {
        APIResponse<List<PersonDTO>> response = APIResponse.<List<PersonDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Get all persons with key %s successfully!", key))
                .metadata(personService.findPersons(key))
                .build();

        return ResponseEntity.ok(response);
    }
}
