package mwg.web.backend.controller;

import mwg.web.backend.APIResponse;
import mwg.web.backend.dto.HouseholdCreationDTO;
import mwg.web.backend.dto.HouseholdDTO;
import mwg.web.backend.impl.HouseholdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping("/households")
    public ResponseEntity<APIResponse<List<HouseholdDTO>>> getAllHouseholds() {
        APIResponse<List<HouseholdDTO>> response = APIResponse.<List<HouseholdDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all households successfully!")
                .metadata(householdService.getAllHouseholds())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/households")
    public ResponseEntity<APIResponse<HouseholdDTO>> addHousehold(@RequestBody HouseholdCreationDTO householdCreationDTO) {
        HouseholdDTO householdDTO = householdService.addHousehold(householdCreationDTO);

        APIResponse<HouseholdDTO> response = APIResponse.<HouseholdDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Household #%d is created successfully!", householdDTO.getHouseholdId()))
                .metadata(householdDTO)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/households/{id}")
    public ResponseEntity<APIResponse<String>> deleteHousehold(@PathVariable(name = "id") Long householdId) {
        householdService.deleteHousehold(householdId);

        APIResponse<String> response = APIResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Household #%d is deleted successfully!", householdId))
                .metadata("DELETED")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/households")
    public ResponseEntity<APIResponse<HouseholdDTO>> updateHousehold(@RequestBody HouseholdCreationDTO householdCreationDTO) {
        HouseholdDTO householdDTO = householdService.updateHousehold(householdCreationDTO);

        APIResponse<HouseholdDTO> response = APIResponse.<HouseholdDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Household #%d is updated successfully!", householdDTO.getHouseholdId()))
                .metadata(householdDTO)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/households/{address}")
    public ResponseEntity<APIResponse<List<HouseholdDTO>>> findHouseholds(@PathVariable(name = "address") String address) {
        APIResponse<List<HouseholdDTO>> response = APIResponse.<List<HouseholdDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Get all households with address %s successfully!", address))
                .metadata(householdService.findHouseholds(address))
                .build();

        return ResponseEntity.ok(response);
    }

}
