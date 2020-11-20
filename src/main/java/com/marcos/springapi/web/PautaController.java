package com.marcos.springapi.web;

import com.marcos.springapi.data.domain.Pauta;
import com.marcos.springapi.service.PautaService;
import com.marcos.springapi.web.dto.BaseResponse;
import com.marcos.springapi.web.dto.CreatePauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PautaController {

    @Autowired
    PautaService pautaService;

    @PostMapping(path = "pautas")
    public ResponseEntity<BaseResponse> createPauta(@Valid @RequestBody CreatePauta body) {
        Pauta pauta = pautaService.createPauta(body.getDescricao());
        return new BaseResponse(pauta).created();
    }
}
