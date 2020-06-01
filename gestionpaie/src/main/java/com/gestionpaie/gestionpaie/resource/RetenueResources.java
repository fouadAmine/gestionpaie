package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.Retenue;
import com.gestionpaie.gestionpaie.repository.RetenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/retenues")
public class RetenueResources {

    @Autowired
    RetenueRepository retenueRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Retenue> getAll(){
        return  retenueRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Retenue get(@PathVariable Integer id){
        Optional<Retenue> retenue = retenueRepository.findById(id);
        Retenue retenueEntity ;
        if(retenue.isPresent()) {
            retenueEntity = retenue.get();
            return retenueEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public List<Retenue> add(@RequestBody final Retenue retenue){
        retenueRepository.save(retenue);
        return retenueRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Retenue update (@PathVariable Integer id,@Valid @RequestBody  Retenue newRetenue){
        Optional<Retenue> retenue = retenueRepository.findById(id);
        Retenue retenueEntity = null;
        if(retenue.isPresent()) {
            retenueEntity = retenue.get();
            retenueEntity.setCode(newRetenue.getCode());
            retenueEntity.setLibelle(newRetenue.getLibelle());
            retenueEntity.setDeduireDeSalaireImposable(newRetenue.getDeduireDeSalaireImposable());
            retenueEntity.setDeduireDeSalaireNet(newRetenue.getDeduireDeSalaireNet());
            retenueRepository.save(retenueEntity);
        }
        return retenueEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<Retenue> delete(@PathVariable Integer id){
        retenueRepository.deleteById(id);
        return retenueRepository.findAll();
    }
}
