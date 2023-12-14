package mwg.web.backend;

import mwg.web.backend.composite.ReceiptKey;
import mwg.web.backend.entity.Category;
import mwg.web.backend.entity.Person;
import mwg.web.backend.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, ReceiptKey> {

    List<Receipt> findAllByHead(Person head);

    List<Receipt> findAllByCategory(Category category);

}
