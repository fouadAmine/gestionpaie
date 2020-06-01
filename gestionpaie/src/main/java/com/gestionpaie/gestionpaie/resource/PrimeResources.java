package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.Prime;
import com.gestionpaie.gestionpaie.repository.PrimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/primes")
public class PrimeResources {

    @Autowired
    PrimeRepository primeRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Prime> getAll(){
        return  primeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Prime get(@PathVariable Integer id){
        Optional<Prime> prime = primeRepository.findById(id);
        Prime primeEntity ;
        if(prime.isPresent()) {
            primeEntity = prime.get();
            return primeEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public List<Prime> add(@RequestBody final Prime prime){
        primeRepository.save(prime);
        return primeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Prime update (@PathVariable Integer id,@Valid @RequestBody  Prime newPrime){
        Optional<Prime> prime = primeRepository.findById(id);
        Prime primeEntity = null;
        if(prime.isPresent()) {
            primeEntity = prime.get();
            primeEntity.setLibelle(newPrime.getLibelle());
            primeEntity.setCode(newPrime.getCode());
            primeEntity.setImposableIR(newPrime.getImposableIR());
            primeEntity.setImposableCnss(newPrime.getImposableCnss());
            primeRepository.save(primeEntity);
        }
        return primeEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<Prime> delete(@PathVariable Integer id){
        primeRepository.deleteById(id);
        return primeRepository.findAll();
    }
}
