package com.marcos.springapi.service.impl;

import com.marcos.springapi.data.domain.Pauta;
import com.marcos.springapi.data.repository.PautaRepository;
import com.marcos.springapi.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    PautaRepository pautaRepository;

    @Override
    public Pauta createPauta(String description) {
        return pautaRepository.save(new Pauta(description));
    }
}
