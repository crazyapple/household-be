package mwg.web.backend.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
public class ReceiptKey implements Serializable {
    @Column(name = "head_id")
    private Long headId;

    @Column(name = "category_id")
    private Long categoryId;
}
