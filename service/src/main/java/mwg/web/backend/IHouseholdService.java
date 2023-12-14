package mwg.web.backend;

import mwg.web.backend.dto.HouseholdCreationDTO;
import mwg.web.backend.dto.HouseholdDTO;

import java.util.List;

public interface IHouseholdService {
    List<HouseholdDTO> getAllHouseholds();

    HouseholdDTO addHousehold(HouseholdCreationDTO householdDTO);

    void deleteHousehold(Long householdId);

    HouseholdDTO updateHousehold(HouseholdCreationDTO householdDTO);

    List<HouseholdDTO> findHouseholds(String address);
}
