package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.Employe;
import com.gestionpaie.gestionpaie.entities.Pret;
import com.gestionpaie.gestionpaie.repository.EmployeRepository;
import com.gestionpaie.gestionpaie.repository.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/prets")
public class PretResources {

    @Autowired
    PretRepository pretRepository;
    @Autowired
    EmployeRepository employeRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Pret> getAll(){
        return  pretRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Pret get(@PathVariable Integer id){
        Optional<Pret> pret = pretRepository.findById(id);
        Pret pretEntity ;
        if(pret.isPresent()) {
            pretEntity = pret.get();
            return pretEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public void add(@RequestBody final Pret pret){
        pretRepository.save(pret);
        if(pret!=null && pret.getEmploye()!=null && pret.getEmploye().getId()!=null){
            Employe employe = employeRepository.getOne(pret.getEmploye().getId());
            pret.setEmploye(employe);
        }

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Pret update (@PathVariable Integer id,@Valid @RequestBody  Pret newPret){
        Optional<Pret> pret = pretRepository.findById(id);
        Pret pretEntity = null;
        if(pret.isPresent()) {
            pretEntity = pret.get();
            pretEntity.setLibelle(newPret.getLibelle());
            pretEntity.setMensualite(newPret.getMensualite());
            pretEntity.setMontant(newPret.getMontant());
            pretEntity.setDerniereEcheance(newPret.getDerniereEcheance());
            pretEntity.setPremiereEcheance(newPret.getPremiereEcheance());
            pretEntity.setEmploye(newPret.getEmploye());
            if(newPret!=null && newPret.getEmploye()!=null && newPret.getEmploye().getId()!=null){
                Employe employe = employeRepository.getOne(newPret.getEmploye().getId());
                newPret.setEmploye(employe);
            }
            pretRepository.save(pretEntity);
        }
        return pretEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<Pret> delete(@PathVariable Integer id){
        pretRepository.deleteById(id);
        return pretRepository.findAll();
    }
    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/byEmploye/{employe_id}")
    public Collection<Pret> getPretByEmploye(@PathVariable Integer employe_id){
        Employe emp = employeRepository.getOne(employe_id);
        ArrayList<Pret> prets = new ArrayList<>(emp.getPrets());
        for (Pret pret : prets){
            pret.setEmploye(null);
        }
        return prets;
    }
}
