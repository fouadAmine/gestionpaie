package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.TypeConge;
import com.gestionpaie.gestionpaie.repository.TypeCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/typeConges")
public class TypeCongeResources {

    @Autowired
    TypeCongeRepository typeCongeRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<TypeConge> getAll(){
        return  typeCongeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public TypeConge get(@PathVariable Integer id){
        Optional<TypeConge> typeConge = typeCongeRepository.findById(id);
        TypeConge typeCongeEntity ;
        if(typeConge.isPresent()) {
            typeCongeEntity = typeConge.get();
            return typeCongeEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public List<TypeConge> add(@RequestBody final TypeConge typeConge){
        typeCongeRepository.save(typeConge);
        return typeCongeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public TypeConge update (@PathVariable Integer id,@Valid @RequestBody  TypeConge newTypeConge){
        Optional<TypeConge> typeConge = typeCongeRepository.findById(id);
        TypeConge typeCongeEntity = null;
        if(typeConge.isPresent()) {
            typeCongeEntity = typeConge.get();
            typeCongeEntity.setLibelle(newTypeConge.getLibelle());
            typeCongeEntity.setCode(newTypeConge.getCode());
            typeCongeRepository.save(typeCongeEntity);
        }
        return typeCongeEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<TypeConge> delete(@PathVariable Integer id){
        typeCongeRepository.deleteById(id);
        return typeCongeRepository.findAll();
    }
}
