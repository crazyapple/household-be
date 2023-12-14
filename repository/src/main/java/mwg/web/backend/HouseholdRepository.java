package mwg.web.backend;

import mwg.web.backend.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {
    List<Household> findAllByAddressContainsIgnoreCase(String address);
}
