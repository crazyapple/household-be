package mwg.web.backend.impl;

import mwg.web.backend.CategoryRepository;
import mwg.web.backend.IReceiptService;
import mwg.web.backend.PersonRepository;
import mwg.web.backend.ReceiptRepository;
import mwg.web.backend.composite.ReceiptKey;
import mwg.web.backend.dto.Mapper;
import mwg.web.backend.dto.ReceiptDTO;
import mwg.web.backend.entity.Category;
import mwg.web.backend.entity.Person;
import mwg.web.backend.entity.Receipt;
import mwg.web.backend.enums.PaymentStatus;
import mwg.web.backend.exception.NotFoundException.CategoryNotFoundException;
import mwg.web.backend.exception.NotFoundException.PersonNotFoundException;
import mwg.web.backend.exception.NotFoundException.ReceiptNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService implements IReceiptService {

    private final ReceiptRepository receiptRepository;

    private final PersonRepository personRepository;

    private final CategoryRepository categoryRepository;

    private final Mapper mapper;

    public ReceiptService(ReceiptRepository receiptRepository, PersonRepository personRepository, CategoryRepository categoryRepository, Mapper mapper) {
        this.receiptRepository = receiptRepository;
        this.personRepository = personRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ReceiptDTO> getAllReceipts() {
        return receiptRepository.findAll()
                .stream()
                .map(mapper::toReceiptDTO)
                .toList();
    }

    @Override
    public ReceiptDTO addReceipt(ReceiptKey receiptId) {
        Person head = personRepository.findById(receiptId.getHeadId()).orElseThrow(() -> new PersonNotFoundException(receiptId.getHeadId()));
        Category category = categoryRepository.findById(receiptId.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(receiptId.getCategoryId()));
        Receipt receipt = Receipt.builder()
                .head(head)
                .category(category)
                .paymentStatus(PaymentStatus.AWAITING)
                .completeTime(LocalDateTime.now())
                .build();

        return mapper.toReceiptDTO(receiptRepository.save(receipt));
    }

    @Override
    public void deleteReceipt(ReceiptKey receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId).orElseThrow(ReceiptNotFoundException::new);
        receiptRepository.delete(receipt);
    }

    @Override
    public ReceiptDTO updateReceiptStatus(ReceiptKey receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId).orElseThrow(ReceiptNotFoundException::new);
        receipt.setPaymentStatus(PaymentStatus.COMPLETED);

        return mapper.toReceiptDTO(receiptRepository.save(receipt));
    }

    @Override
    public List<ReceiptDTO> findReceiptsByHead(Long headId) {
        Person head = personRepository.findById(headId).orElseThrow(() -> new PersonNotFoundException(headId));
        List<Receipt> receipts = receiptRepository.findAllByHead(head);
        if (receipts.isEmpty()) return new ArrayList<>();
        return receipts.stream().map(mapper::toReceiptDTO).toList();
    }

    @Override
    public List<ReceiptDTO> findReceiptsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        List<Receipt> receipts = receiptRepository.findAllByCategory(category);
        if (receipts.isEmpty()) return new ArrayList<>();
        return receipts.stream().map(mapper::toReceiptDTO).toList();
    }
}
