package com.marcos.springapi.web;

import com.marcos.springapi.data.domain.Pauta;
import com.marcos.springapi.exception.MissingFieldException;
import com.marcos.springapi.service.PautaService;
import com.marcos.springapi.web.dto.BaseResponse;
import com.marcos.springapi.web.dto.CreatePauta;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/api")
public class PautaController {

    @Autowired
    PautaService pautaService;

    @PostMapping(path = "/pautas")
    public ResponseEntity<BaseResponse<Pauta>> createPauta(@NotNull @RequestBody CreatePauta body) {
        BaseResponse response;
        try {

            if (Strings.isBlank(body.getDescricao()))
                throw new MissingFieldException("decricao");

            Pauta pauta = pautaService.createPauta(body.getDescricao());
            response = new BaseResponse(pauta);
        } catch (Exception e) {
            response = new BaseResponse(e);
        }

        return response.created();
    }
}
