package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.Service;
import com.gestionpaie.gestionpaie.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/services")
public class ServiceResources {
    @Autowired
    ServiceRepository serviceRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Service> getAll(){
        return  serviceRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Service get(@PathVariable Integer id){
        Optional<Service> service = serviceRepository.findById(id);
        Service serviceEntity ;
        if(service.isPresent()) {
            serviceEntity = service.get();
            return serviceEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public List<Service> add(@RequestBody final Service service){
        serviceRepository.save(service);
        return serviceRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Service update (@PathVariable Integer id,@Valid @RequestBody  Service newService){
        Optional<Service> service = serviceRepository.findById(id);
        Service serviceEntity = null;
        if(service.isPresent()) {
            serviceEntity = service.get();
            serviceEntity.setCode(newService.getCode());
            serviceEntity.setLibelle(newService.getLibelle());
            serviceRepository.save(serviceEntity);
        }
        return serviceEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<Service> delete(@PathVariable Integer id){
        serviceRepository.deleteById(id);
        return serviceRepository.findAll();
    }
}
