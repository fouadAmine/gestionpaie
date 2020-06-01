package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.TypeAbsence;
import com.gestionpaie.gestionpaie.repository.TypeAbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/typeAbsences")
public class TypeAbsenceResources {

    @Autowired
    TypeAbsenceRepository typeAbsenceRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<TypeAbsence> getAll(){
        return  typeAbsenceRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public TypeAbsence get(@PathVariable Integer id){
        Optional<TypeAbsence> typeAbsence = typeAbsenceRepository.findById(id);
        TypeAbsence typeAbsenceEntity ;
        if(typeAbsence.isPresent()) {
            typeAbsenceEntity = typeAbsence.get();
            return typeAbsenceEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public List<TypeAbsence> add(@RequestBody final TypeAbsence typeAbsence){
        typeAbsenceRepository.save(typeAbsence);
        return typeAbsenceRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public TypeAbsence update (@PathVariable Integer id,@Valid @RequestBody  TypeAbsence newTypeAbsence){
        Optional<TypeAbsence> typeAbsence = typeAbsenceRepository.findById(id);
        TypeAbsence typeAbsenceEntity = null;
        if(typeAbsence.isPresent()) {
            typeAbsenceEntity = typeAbsence.get();
            typeAbsenceEntity.setCode(newTypeAbsence.getCode());
            typeAbsenceEntity.setLibelle(newTypeAbsence.getLibelle());
            typeAbsenceRepository.save(typeAbsenceEntity);
        }
        return typeAbsenceEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<TypeAbsence> delete(@PathVariable Integer id){
        typeAbsenceRepository.deleteById(id);
        return typeAbsenceRepository.findAll();
    }
}
