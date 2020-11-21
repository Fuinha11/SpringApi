package com.marcos.springapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcos.springapi.exception.CustomException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BaseResponse<T> implements Serializable {

    @JsonIgnore
    private HttpStatus status;

    @ApiModelProperty(value = "Payload desejado em caso de sucesso")
    private T payload;

    @ApiModelProperty(value = "Menssagem de erro em caso de falha")
    private String errorMessage;

    public BaseResponse(Object payload) {
        if (Objects.isNull(payload)) {
            this.status = HttpStatus.NOT_FOUND;
            this.errorMessage = "Object not Found";
        } else if (payload instanceof CustomException) {
            this.status = ((CustomException) payload).getStatus();
            this.errorMessage = ((CustomException) payload).getMessage();
        } else if (payload instanceof Throwable) {
            this.status = HttpStatus.INTERNAL_SERVER_ERROR;
            this.errorMessage = ((Throwable) payload).getMessage();
        } else
            this.payload = (T) payload;
    }

    public ResponseEntity<BaseResponse> ok() {
        if (Objects.isNull(status))
            return ResponseEntity.ok(this);
        else
            return new ResponseEntity<BaseResponse>(this, status);
    }

    public ResponseEntity<BaseResponse> created() {
        if (Objects.isNull(status))
            return new ResponseEntity(this, HttpStatus.CREATED);
        else
            return new ResponseEntity(this, status);
    }
}
