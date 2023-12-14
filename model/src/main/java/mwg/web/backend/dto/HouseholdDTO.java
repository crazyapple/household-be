package mwg.web.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseholdDTO {

    private Long householdId;

    private String address;

    private List<PersonDTO> persons;

}
