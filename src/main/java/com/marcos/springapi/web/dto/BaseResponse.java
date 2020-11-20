package com.marcos.springapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BaseResponse implements Serializable {
    @JsonIgnore
    private HttpStatus status;
    private Object payload;
    private Object error;

    public BaseResponse(Object payload) {
        if (Objects.isNull(payload)) {
            status = HttpStatus.NOT_FOUND;
            this.error = "Object not Found";
        }
        else
            this.payload = payload;
    }

    public ResponseEntity<BaseResponse> Ok() {
        if (Objects.isNull(status))
            return ResponseEntity.ok(this);
        else
            return new ResponseEntity<BaseResponse>(this, status);
    }

    public ResponseEntity<BaseResponse> Created() {
        if (Objects.isNull(status))
            return new ResponseEntity(this, HttpStatus.CREATED);
        else
            return new ResponseEntity(this, status);
    }
}
