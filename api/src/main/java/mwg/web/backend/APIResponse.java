package mwg.web.backend;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse<T> {

    private int statusCode;
    private String message;
    private T metadata;
}
