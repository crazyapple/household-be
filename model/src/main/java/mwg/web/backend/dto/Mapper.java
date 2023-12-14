package mwg.web.backend.dto;

import mwg.web.backend.entity.Category;
import mwg.web.backend.entity.Household;
import mwg.web.backend.entity.Person;
import mwg.web.backend.entity.Receipt;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public PersonDTO toPersonDTO(Person person) {
        return PersonDTO.builder()
                .personId(person.getPersonId())
                .name(person.getName())
                .identification(person.getIdentification())
                .phoneNumber(person.getPhoneNumber())
                .age(person.getAge())
                .isHead(person.getIsHead())
                .relationship(person.getRelationship())
                .householdId(person.getHousehold().getHouseholdId())
                .build();
    }

    public HouseholdDTO toHouseholdDTO(Household household) {
        return HouseholdDTO.builder()
                .householdId(household.getHouseholdId())
                .address(household.getAddress())
                .persons(household.getMembers().stream().map(this::toPersonDTO).toList())
                .build();
    }

    public CategoryDTO toCategoryDTO(Category category) {
        return CategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .amount(category.getAmount())
                .voluntary(category.isVoluntary())
                .startDate(category.getStartDate())
                .endDate(category.getEndDate())
                .build();
    }

    public ReceiptDTO toReceiptDTO(Receipt receipt) {
        return ReceiptDTO.builder()
                .headId(receipt.getHead().getPersonId())
                .categoryId(receipt.getCategory().getCategoryId())
                .paymentStatus(receipt.getPaymentStatus())
                .completeTime(receipt.getCompleteTime())
                .build();
    }

}
