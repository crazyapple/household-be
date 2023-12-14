package mwg.web.backend.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIErrorResponse {

    private int statusCode;

    private String error;

    private String message;

}
