package mwg.web.backend;

import mwg.web.backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByNameContainsIgnoreCaseOrIdentificationContainsIgnoreCase(String name, String identification);
}
