package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.Banque;
import com.gestionpaie.gestionpaie.entities.Employe;
import com.gestionpaie.gestionpaie.entities.Fonction;
import com.gestionpaie.gestionpaie.entities.Service;
import com.gestionpaie.gestionpaie.repository.BanqueRepository;
import com.gestionpaie.gestionpaie.repository.EmployeRepository;
import com.gestionpaie.gestionpaie.repository.FonctionRepository;
import com.gestionpaie.gestionpaie.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/employes")
public class EmployeResources {

    @Autowired
    EmployeRepository employeRepository;
    @Autowired
    FonctionRepository fonctionRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    BanqueRepository banqueRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Employe> getAll(){
        List<Employe> employes=employeRepository.findAll();
        for (Employe employe:employes){
            employe.setRevenues(null);
            employe.setJournalsDePaie(null);
        }
        return employes;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Employe get(@PathVariable Integer id){
        Optional<Employe> employe = employeRepository.findById(id);
        Employe employeEntity ;
        if(employe.isPresent()) {
            employeEntity = employe.get();
            return employeEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public  void add(@RequestBody  Employe employe){
        if(employe!=null && employe.getService()!=null && employe.getService().getId()!=null){
            Service service = serviceRepository.getOne(employe.getService().getId());
            employe.setService(service);
        }
        if(employe!=null && employe.getFonction()!=null && employe.getFonction().getId()!=null) {
            Fonction fonction = fonctionRepository.getOne(employe.getFonction().getId());
            employe.setFonction(fonction);
        }
        if(employe!=null && employe.getBanque()!=null && employe.getBanque().getId()!=null) {
            Banque banque = banqueRepository.getOne(employe.getBanque().getId());
            employe.setBanque(banque);
        }

        employeRepository.save(employe);
        //return employeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Employe update (@PathVariable Integer id,@Valid @RequestBody  Employe newEmploye){
        Optional<Employe> employe = employeRepository.findById(id);
        Employe employeEntity = null;
        if(employe.isPresent()) {
            employeEntity = employe.get();
            employeEntity.setNom(newEmploye.getNom());
            employeEntity.setPrenom(newEmploye.getPrenom());
            employeEntity.setMatricule(newEmploye.getMatricule());
            employeEntity.setCin(newEmploye.getCin());
            employeEntity.setSalairedeBase(newEmploye.getSalairedeBase());
            employeEntity.setSexe(newEmploye.getSexe());
            employeEntity.setSituation(newEmploye.getSituation());
            employeEntity.setDatedeNaissance(newEmploye.getDatedeNaissance());
            employeEntity.setEmail(newEmploye.getEmail());
            employeEntity.setCarteSejour(newEmploye.getCarteSejour());
            employeEntity.setAdresse(newEmploye.getAdresse());
            employeEntity.setAgence(newEmploye.getAgence());
            employeEntity.setNumCnss(newEmploye.getNumCnss());
            employeEntity.setCategorie(newEmploye.getCategorie());
            employeEntity.setDateAnciennete(newEmploye.getDateAnciennete());
            employeEntity.setDateDentree(newEmploye.getDateDentree());
            employeEntity.setDateSortie(newEmploye.getDateSortie());
            employeEntity.setIdentFiscale(newEmploye.getIdentFiscale());
            employeEntity.setModeReglement(newEmploye.getModeReglement());
            employeEntity.setN_PPR(newEmploye.getN_PPR());
            employeEntity.setNbrDeduction(newEmploye.getNbrDeduction());
            employeEntity.setNbrEnfants(newEmploye.getNbrEnfants());
            employeEntity.setNumCompteBanquaire(newEmploye.getNumCompteBanquaire());
            employeEntity.setStatus(newEmploye.getStatus());
            employeEntity.setTauxFraisPro(newEmploye.getTauxFraisPro());
            employeEntity.setTelephone(newEmploye.getTelephone());
            employeEntity.setTypedePaie(newEmploye.getTypedePaie());
            //TODO SET FUNCTION
            employeEntity.setFonction(newEmploye.getFonction());
            //TODO SET banque
            employeEntity.setBanque(newEmploye.getBanque());
            //TODO SET service
            employeEntity.setService(newEmploye.getService());
            employeEntity.setIR(newEmploye.isIR());
            employeEntity.setCnss(newEmploye.isCnss());

            employeRepository.save(employeEntity);
        }
        return employeEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<Employe> delete(@PathVariable Integer id){
        employeRepository.deleteById(id);
        return employeRepository.findAll();
    }
}
