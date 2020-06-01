package com.gestionpaie.gestionpaie.resource;

import com.gestionpaie.gestionpaie.entities.Absence;
import com.gestionpaie.gestionpaie.entities.Employe;
import com.gestionpaie.gestionpaie.entities.TypeAbsence;
import com.gestionpaie.gestionpaie.repository.AbsenceRepository;
import com.gestionpaie.gestionpaie.repository.EmployeRepository;
import com.gestionpaie.gestionpaie.repository.TypeAbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/res/absences")
public class AbsenceResources {

    @Autowired
    AbsenceRepository absenceRepository;
    @Autowired
    TypeAbsenceRepository typeAbsenceRepository;
    @Autowired
    EmployeRepository employeRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Absence> getAll(){
        return  absenceRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Absence get(@PathVariable Integer id){
        Optional<Absence> absence = absenceRepository.findById(id);
        Absence absenceEntity ;
        if(absence.isPresent()) {
            absenceEntity = absence.get();
            return absenceEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public void add(@RequestBody final Absence absence){
        if(absence!=null && absence.getTypeAbsence()!=null && absence.getTypeAbsence().getId()!=null){
            TypeAbsence typeAbsence = typeAbsenceRepository.getOne(absence.getTypeAbsence().getId());
            absence.setTypeAbsence(typeAbsence);
        }
        if(absence!=null && absence.getEmploye()!=null && absence.getEmploye().getId()!=null){
            Employe employe = employeRepository.getOne(absence.getEmploye().getId());
            absence.setEmploye(employe);
        }
        absenceRepository.save(absence);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Absence update (@PathVariable Integer id,@Valid @RequestBody  Absence newAbsence){
        Optional<Absence> absence = absenceRepository.findById(id);
        Absence absenceEntity = null;
        if(absence.isPresent()) {

            absenceEntity = absence.get();
            absenceEntity.setEmploye(newAbsence.getEmploye());
            absenceEntity.setDateDebut(newAbsence.getDateDebut());
            absenceEntity.setDateFin(newAbsence.getDateFin());
            absenceEntity.setTypeAbsence(newAbsence.getTypeAbsence());
            if(newAbsence!=null && newAbsence.getTypeAbsence()!=null && newAbsence.getTypeAbsence().getId()!=null){
                TypeAbsence typeAbsence = typeAbsenceRepository.getOne(newAbsence.getTypeAbsence().getId());
                newAbsence.setTypeAbsence(typeAbsence);
            }
            if(newAbsence!=null && newAbsence.getEmploye()!=null && newAbsence.getEmploye().getId()!=null){
                Employe employe = employeRepository.getOne(newAbsence.getEmploye().getId());
                newAbsence.setEmploye(employe);
            }
            absenceRepository.save(absenceEntity);
        }
        return absenceEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public List<Absence> delete(@PathVariable Integer id){
        absenceRepository.deleteById(id);
        return absenceRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/byEmploye/{employe_id}")
    public Collection<Absence> getAbsenceByEmploye(@PathVariable Integer employe_id){
        Employe emp = employeRepository.getOne(employe_id);
        ArrayList<Absence> absences = new ArrayList<>(emp.getAbsences());
        for (Absence absence : absences){
            absence.setEmploye(null);
        }
        return absences;
    }

}
