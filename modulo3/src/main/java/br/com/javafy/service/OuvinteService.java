package br.com.javafy.service;

import br.com.javafy.entity.Ouvinte;
import br.com.javafy.repository.OuvinteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OuvinteService {

    @Autowired
    OuvinteRepository ouvinteRepository;

    public Ouvinte create (Ouvinte ouvinte){
        ouvinteRepository.create(ouvinte);
        return ouvinte;
    }

    public List<Ouvinte> list () {
        return ouvinteRepository.list();
    }


}
