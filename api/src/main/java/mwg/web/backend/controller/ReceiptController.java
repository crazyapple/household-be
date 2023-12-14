package mwg.web.backend.controller;

import mwg.web.backend.APIResponse;
import mwg.web.backend.composite.ReceiptKey;
import mwg.web.backend.dto.ReceiptDTO;
import mwg.web.backend.impl.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/receipts")
    public ResponseEntity<APIResponse<List<ReceiptDTO>>> getAllReceipts() {
        APIResponse<List<ReceiptDTO>> response = APIResponse.<List<ReceiptDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all receipts successfully!")
                .metadata(receiptService.getAllReceipts())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/receipts")
    public ResponseEntity<APIResponse<ReceiptDTO>> addReceipt(@RequestBody ReceiptKey receiptId) {
        ReceiptDTO receipt = receiptService.addReceipt(receiptId);

        APIResponse<ReceiptDTO> response = APIResponse.<ReceiptDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Receipt is created successfully!")
                .metadata(receipt)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/receipts")
    public ResponseEntity<APIResponse<String>> deleteReceipt(@RequestBody ReceiptKey receiptId) {
        receiptService.deleteReceipt(receiptId);

        APIResponse<String> response = APIResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Receipt is deleted successfully!")
                .metadata("DELETED")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/receipts")
    public ResponseEntity<APIResponse<ReceiptDTO>> updateReceipt(@RequestBody ReceiptKey receiptId) {
        ReceiptDTO receipt = receiptService.updateReceiptStatus(receiptId);

        APIResponse<ReceiptDTO> response = APIResponse.<ReceiptDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Category is updated successfully!")
                .metadata(receipt)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/receipts/head/{head}")
    public ResponseEntity<APIResponse<List<ReceiptDTO>>> findReceiptsByHead(@PathVariable(name = "head") Long headId) {
        APIResponse<List<ReceiptDTO>> response = APIResponse.<List<ReceiptDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Get all receipts with head #%d successfully!", headId))
                .metadata(receiptService.findReceiptsByHead(headId))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/receipts/category/{category}")
    public ResponseEntity<APIResponse<List<ReceiptDTO>>> findReceiptsByCategory(@PathVariable(name = "category") Long categoryId) {
        APIResponse<List<ReceiptDTO>> response = APIResponse.<List<ReceiptDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Get all receipts with category #%d successfully!", categoryId))
                .metadata(receiptService.findReceiptsByCategory(categoryId))
                .build();

        return ResponseEntity.ok(response);
    }
}
