package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.Banque;
import com.gestionpaie.gestionpaie.entities.Fonction;
import com.gestionpaie.gestionpaie.repository.BanqueRepository;
import com.gestionpaie.gestionpaie.repository.FonctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/fonctions")
public class FonctionResources {

    @Autowired
    FonctionRepository fonctionRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Fonction> getAll(){
        return  fonctionRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Fonction get(@PathVariable Integer id){
        Optional<Fonction> fonction = fonctionRepository.findById(id);
        Fonction fonctionEntity ;
        if(fonction.isPresent()) {
            fonctionEntity = fonction.get();
            return fonctionEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public List<Fonction> add(@RequestBody final Fonction fonction){
        fonctionRepository.save(fonction);
        return fonctionRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Fonction update (@PathVariable Integer id,@Valid @RequestBody  Fonction newFonction){
        Optional<Fonction> fonction = fonctionRepository.findById(id);
        Fonction fonctionEntity = null;
        if(fonction.isPresent()) {
            fonctionEntity = fonction.get();
            fonctionEntity.setCode(fonctionEntity.getCode());
            fonctionEntity.setLibelle(newFonction.getLibelle());
            fonctionRepository.save(fonctionEntity);
        }
        return fonctionEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<Fonction> delete(@PathVariable Integer id){
        fonctionRepository.deleteById(id);
        return fonctionRepository.findAll();
    }
}
