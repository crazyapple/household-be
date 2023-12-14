package mwg.web.backend.dto;

import lombok.*;
import mwg.web.backend.enums.PaymentStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiptDTO {

    private Long headId;

    private Long categoryId;

    private PaymentStatus paymentStatus;

    private LocalDateTime completeTime;

}
