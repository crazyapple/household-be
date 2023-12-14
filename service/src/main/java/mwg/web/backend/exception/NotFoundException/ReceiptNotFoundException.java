package mwg.web.backend.exception.NotFoundException;

public class ReceiptNotFoundException extends NotFoundException {
    public ReceiptNotFoundException() {
        super("Receipt not found");
    }
}
