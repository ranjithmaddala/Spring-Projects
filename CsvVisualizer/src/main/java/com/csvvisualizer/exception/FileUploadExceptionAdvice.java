package com.csvvisualizer.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import com.csvvisualizer.api.CsvResponseMessenger;

@RestControllerAdvice
public class FileUploadExceptionAdvice{

  @SuppressWarnings("rawtypes")
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity handleMaxSizeException(MaxUploadSizeExceededException exc) {
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new CsvResponseMessenger("File too large!",""));
  }
}