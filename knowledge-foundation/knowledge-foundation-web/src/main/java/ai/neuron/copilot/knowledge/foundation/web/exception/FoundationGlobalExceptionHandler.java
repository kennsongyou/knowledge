package ai.neuron.copilot.knowledge.foundation.web.exception;

import ai.neuron.copilot.knowledge.foundation.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.Locale;


@Order
@RequiredArgsConstructor
@RestControllerAdvice
public class FoundationGlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> handleBusinessException(
            BusinessException ex) {

        Locale locale = LocaleContextHolder.getLocale();
        String detail = messageSource.getMessage(ex.getErrorCode().messageKey(), ex.getMessageArgs(), locale);
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setType(URI.create("urn:error:" + ex.getErrorCode().code()));
        problem.setTitle("Business Error");
        problem.setDetail(detail);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
