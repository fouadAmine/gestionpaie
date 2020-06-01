package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.Periode;
import com.gestionpaie.gestionpaie.repository.PeriodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/periodes")
public class PeriodeResources {

    @Autowired
    PeriodeRepository periodeRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Periode> getAll(){
        return  periodeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Periode get(@PathVariable Integer id){
        Optional<Periode> periode = periodeRepository.findById(id);
        Periode periodeEntity  ;
        if(periode.isPresent()) {
            periodeEntity = periode.get();
            return periodeEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public List<Periode> add(@RequestBody final Periode periode){
        periodeRepository.save(periode);
        return periodeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Periode update (@PathVariable Integer id,@Valid @RequestBody  Periode newPeriode){
        Optional<Periode> periode = periodeRepository.findById(id);
        Periode periodeEntity = null;
        if(periode.isPresent()) {
            periodeEntity = periode.get();
            periodeEntity.setDateDebut(newPeriode.getDateDebut());
            periodeEntity.setDateFin(newPeriode.getDateFin());
            periodeEntity.setActive(newPeriode.getActive());
            periodeEntity.setCode(newPeriode.getCode());
            periodeRepository.save(periodeEntity);
        }
        return periodeEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<Periode> delete(@PathVariable Integer id){
        periodeRepository.deleteById(id);
        return periodeRepository.findAll();
    }
}
