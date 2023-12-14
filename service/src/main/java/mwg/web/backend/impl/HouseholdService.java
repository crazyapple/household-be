package mwg.web.backend.impl;

import mwg.web.backend.*;
import mwg.web.backend.dto.HouseholdCreationDTO;
import mwg.web.backend.dto.HouseholdDTO;
import mwg.web.backend.dto.Mapper;
import mwg.web.backend.entity.Household;
import mwg.web.backend.entity.Person;
import mwg.web.backend.exception.NotFoundException.HouseholdNotFoundException;
import mwg.web.backend.exception.NotFoundException.PersonNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseholdService implements IHouseholdService {

    private final HouseholdRepository householdRepository;

    private final PersonRepository personRepository;

    private final Mapper mapper;

    public HouseholdService(HouseholdRepository householdRepository, PersonRepository personRepository, Mapper mapper) {
        this.householdRepository = householdRepository;
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    @Override
    public List<HouseholdDTO> getAllHouseholds() {
        return householdRepository.findAll()
                .stream()
                .map(mapper::toHouseholdDTO)
                .toList();
    }

    @Override
    public HouseholdDTO addHousehold(HouseholdCreationDTO householdDTO) {
        Person person = personRepository.findById(householdDTO.getHeadId()).orElseThrow(() -> new PersonNotFoundException(householdDTO.getHeadId()));
        if (person.getIsHead()) {
            Household household = Household.builder()
                    .address(householdDTO.getAddress())
                    .members(List.of(person))
                    .build();

            return mapper.toHouseholdDTO(householdRepository.save(household));
        } else {
            throw new RuntimeException("Hello");
        }
    }

    @Override
    public void deleteHousehold(Long householdId) {
        Household household = householdRepository.findById(householdId).orElseThrow(() -> new HouseholdNotFoundException(householdId));
        householdRepository.delete(household);
    }

    @Override
    public HouseholdDTO updateHousehold(HouseholdCreationDTO householdDTO) {
        Household household = householdRepository.findById(householdDTO.getHeadId()).orElseThrow(() -> new HouseholdNotFoundException(householdDTO.getHeadId()));
        household.setAddress(householdDTO.getAddress());
        return mapper.toHouseholdDTO(householdRepository.save(household));
    }

    @Override
    public List<HouseholdDTO> findHouseholds(String address) {
        List<Household> households = householdRepository.findAllByAddressContainsIgnoreCase(address);
        if (households.isEmpty()) return new ArrayList<>();
        return households.stream().map(mapper::toHouseholdDTO).toList();
    }
}
