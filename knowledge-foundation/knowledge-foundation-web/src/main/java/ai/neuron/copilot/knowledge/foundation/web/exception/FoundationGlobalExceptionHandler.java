package ai.neuron.copilot.knowledge.foundation.web.exception;

import ai.neuron.copilot.knowledge.foundation.core.exception.BaseException;
import ai.neuron.copilot.knowledge.foundation.core.exception.ErrorCode;
import ai.neuron.copilot.knowledge.foundation.core.exception.FoundationCoreErrorCode;
import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotExistException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.Locale;


@Order
@RequiredArgsConstructor
@RestControllerAdvice
public class FoundationGlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ResourceNotExistException.class)
    public ProblemDetail handleResourceNotExist(ResourceNotExistException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String detail = messageSource.getMessage(ex.getErrorCode().messageKey(), ex.getMessageArgs(), locale);
        return buildProblemDetail(ex.getErrorCode(), HttpStatus.NOT_FOUND, detail);
    }

    @ExceptionHandler(BaseException.class)
    public ProblemDetail handleBase(BaseException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String detail = messageSource.getMessage(ex.getErrorCode().messageKey(), ex.getMessageArgs(), locale);
        return buildProblemDetail(ex.getErrorCode(), HttpStatus.INTERNAL_SERVER_ERROR, detail);
    }

    private static ProblemDetail buildProblemDetail(ErrorCode errorCode, HttpStatus httpStatus, String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
        problemDetail.setType(URI.create("urn:error:" + errorCode.code()));
        problemDetail.setTitle(errorCode.code());
        problemDetail.setDetail(detail);
        return problemDetail;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldErrors().getFirst();
        String detail = fieldError.getDefaultMessage();
        return buildProblemDetail(FoundationCoreErrorCode.INVALID_ARGUMENT, HttpStatus.BAD_REQUEST, detail);
    }

}
