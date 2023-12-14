package mwg.web.backend.exception.NotFoundException;

public class PersonNotFoundException extends NotFoundException {
    public PersonNotFoundException(Long personId) {
        super("Person not found with ID: " + personId);
    }
}
