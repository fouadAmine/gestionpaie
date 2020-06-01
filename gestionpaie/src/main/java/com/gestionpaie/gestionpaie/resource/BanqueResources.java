package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.Banque;
import com.gestionpaie.gestionpaie.repository.BanqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/banques")
public class BanqueResources {

    @Autowired
    BanqueRepository banqueRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Banque> getAll(){
        return  banqueRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Banque get(@PathVariable Integer id){
        Optional<Banque> banque = banqueRepository.findById(id);
        Banque banqueEntity ;
        if(banque.isPresent()) {
            banqueEntity = banque.get();
            return banqueEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public List<Banque> add(@RequestBody final Banque banque){
        banqueRepository.save(banque);
        return banqueRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Banque update (@PathVariable Integer id,@Valid @RequestBody  Banque newBanque){
        Optional<Banque> banque = banqueRepository.findById(id);
        Banque banqueEntity = null;
        if(banque.isPresent()) {
            banqueEntity = banque.get();
            banqueEntity.setCode(newBanque.getCode());
            banqueEntity.setLibelle(newBanque.getLibelle());
            banqueRepository.save(banqueEntity);
        }
        return banqueEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<Banque> delete(@PathVariable Integer id){
        banqueRepository.deleteById(id);
        return banqueRepository.findAll();
    }
}
