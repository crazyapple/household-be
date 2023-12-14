package mwg.web.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import mwg.web.backend.composite.ReceiptKey;
import mwg.web.backend.enums.PaymentStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "receipt")
public class Receipt {

    @EmbeddedId
    private ReceiptKey receiptId;

    @ManyToOne
    @MapsId("headId")
    @JoinColumn(name = "head_id")
    private Person head;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "complete_time")
    private LocalDateTime completeTime;

}
