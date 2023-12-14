package mwg.web.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseholdCreationDTO {

    private Long headId;

    private String address;
}
