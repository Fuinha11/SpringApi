package com.marcos.springapi.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marcos.springapi.data.domain.Associado;
import com.marcos.springapi.exception.JavaException;
import com.marcos.springapi.exception.UnableToVoteException;
import com.marcos.springapi.exception.ValidationServiceUnavailable;
import com.marcos.springapi.service.ExternalValidationService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Objects;

@Service
public class ExternalValidationServiceImpl implements ExternalValidationService {

    private RestTemplate restTemplate = new RestTemplate();
    final String validationUri = "https://user-info.herokuapp.com/users/";

    @Override
    public Boolean isAbleToVote(Associado associado) throws ValidationServiceUnavailable {
        ValidationResponse result = restTemplate.getForObject(validationUri.concat(associado.getCpf()), ValidationResponse.class);
        if (Objects.isNull(result))
            throw new ValidationServiceUnavailable();
        return result.status.equals(ValidationStatus.ABLE_TO_VOTE);
    }

    @NoArgsConstructor
    public static class ValidationResponse implements Serializable {
        public ValidationStatus status;
    }

    public enum ValidationStatus {
        ABLE_TO_VOTE,
        UNABLE_TO_VOTE
    }
}
