package mwg.web.backend;

import mwg.web.backend.composite.ReceiptKey;
import mwg.web.backend.dto.ReceiptDTO;

import java.util.List;

public interface IReceiptService {

    List<ReceiptDTO> getAllReceipts();

    ReceiptDTO addReceipt(ReceiptKey receiptId);

    void deleteReceipt(ReceiptKey receiptId);

    ReceiptDTO updateReceiptStatus(ReceiptKey receiptId);

    List<ReceiptDTO> findReceiptsByHead(Long headId);

    List<ReceiptDTO> findReceiptsByCategory(Long categoryId);

}
