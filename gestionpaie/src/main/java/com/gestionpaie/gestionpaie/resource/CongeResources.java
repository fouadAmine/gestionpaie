package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.Conge;
import com.gestionpaie.gestionpaie.entities.Employe;
import com.gestionpaie.gestionpaie.entities.TypeConge;
import com.gestionpaie.gestionpaie.repository.CongeRepository;
import com.gestionpaie.gestionpaie.repository.EmployeRepository;
import com.gestionpaie.gestionpaie.repository.TypeCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/conges")
public class CongeResources {

    @Autowired
    CongeRepository congeRepository;
    @Autowired
    TypeCongeRepository typeCongeRepository;
    @Autowired
    EmployeRepository employeRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Conge> getAll(){
        return  congeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Conge get(@PathVariable Integer id){
        Optional<Conge> conge = congeRepository.findById(id);
        Conge congeEntity ;
        if(conge.isPresent()) {
            congeEntity = conge.get();
            return congeEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public void add(@RequestBody final Conge conge){
        if(conge!=null && conge.getTypeConge()!=null && conge.getTypeConge().getId()!=null){
            TypeConge typeConge = typeCongeRepository.getOne(conge.getTypeConge().getId());
            conge.setTypeConge(typeConge);
        }
        if(conge!=null && conge.getEmploye()!=null && conge.getEmploye().getId()!=null){
            Employe employe = employeRepository.getOne(conge.getEmploye().getId());
            conge.setEmploye(employe);
        }
        congeRepository.save(conge);

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Conge update (@PathVariable Integer id,@Valid @RequestBody  Conge newConge){
        Optional<Conge> conge = congeRepository.findById(id);
        Conge congeEntity = null;
        if(conge.isPresent()) {
            congeEntity = conge.get();
            congeEntity.setEmploye(newConge.getEmploye());
            congeEntity.setDateDebut(newConge.getDateDebut());
            congeEntity.setDateFin(newConge.getDateFin());
            congeEntity.setTypeConge(newConge.getTypeConge());
            if(newConge!=null && newConge.getTypeConge()!=null && newConge.getTypeConge().getId()!=null){
                TypeConge typeConge = typeCongeRepository.getOne(newConge.getTypeConge().getId());
                newConge.setTypeConge(typeConge);
            }
            if(newConge!=null && newConge.getEmploye()!=null && newConge.getEmploye().getId()!=null){
                Employe employe = employeRepository.getOne(newConge.getEmploye().getId());
                newConge.setEmploye(employe);
            }
            congeRepository.save(congeEntity);
        }
        return congeEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<Conge> delete(@PathVariable Integer id){
        congeRepository.deleteById(id);
        return congeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/byEmploye/{employe_id}")
    public Collection<Conge> getCongeByEmploye(@PathVariable Integer employe_id){
        Employe emp = employeRepository.getOne(employe_id);
        ArrayList<Conge> conges = new ArrayList<>(emp.getConges());
        for (Conge conge : conges){
            conge.setEmploye(null);
        }
        return conges;
    }
}
