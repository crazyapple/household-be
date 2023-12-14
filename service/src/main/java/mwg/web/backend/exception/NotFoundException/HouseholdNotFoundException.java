package mwg.web.backend.exception.NotFoundException;

public class HouseholdNotFoundException extends NotFoundException {
    public HouseholdNotFoundException(Long householdId) {
        super("Household not found with ID: " + householdId);
    }
}
