package com.gestionpaie.gestionpaie.resource;


import com.gestionpaie.gestionpaie.dtos.PrimeDto;
import com.gestionpaie.gestionpaie.dtos.RetenueDto;
import com.gestionpaie.gestionpaie.dtos.RevenueDto;
import com.gestionpaie.gestionpaie.dtos.RevenueList;
import com.gestionpaie.gestionpaie.entities.*;
import com.gestionpaie.gestionpaie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/res/revenues")
public class RevenueResources {


    @Autowired
    RevenueRepository revenueRepository;
    @Autowired
    EmployeRepository employeRepository;
    @Autowired
    PeriodeRepository periodeRepository;
    @Autowired
    PrimeRepository primeRepository;
    @Autowired
    RevenuePrimeRepository revenuePrimeRepository;
    @Autowired
    RetenueRepository retenueRepository ;
    @Autowired
    RevenueRetenueRepository revenueRetenueRepository;



    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/all")
    public List<Revenue> getAll(){
        List<Revenue> revenues = revenueRepository.findAll();
        for (Revenue revenue : revenues){
            revenue.setPrimes(null);
            revenue.setRetenues(null);
            Employe emp = new Employe();
            emp.setNom(revenue.getEmploye().getNom());
            emp.setPrenom(revenue.getEmploye().getPrenom());
            emp.setMatricule(revenue.getEmploye().getMatricule());
            revenue.setEmploye(emp);
            Periode prd = new Periode();
            prd.setDateDebut(revenue.getPeriode().getDateDebut());
            prd.setDateFin(revenue.getPeriode().getDateFin());
            revenue.setPeriode(prd);
        }
        return  revenues;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/{id}")
    public Revenue get(@PathVariable Integer id){
        Optional<Revenue> revenue = revenueRepository.findById(id);
        Revenue revenueEntity ;
        if(revenue.isPresent()) {
            revenueEntity = revenue.get();
            return revenueEntity;
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/add")
    public void add(@RequestBody final Revenue revenue){
        if(revenue != null) {
            Set<RevenuePrime> revenuePrimes = revenue.getPrimes();
            Set<RevenueRetenue> revenueRetenues = revenue.getRetenues();

            revenue.setPrimes(null);
            revenue.setRetenues(null);

            if (revenue.getEmploye() != null && revenue.getEmploye().getId() != null) {
                Employe employe = employeRepository.getOne(revenue.getEmploye().getId());
                revenue.setEmploye(employe);
            }
            if (revenue.getPeriode() != null && revenue.getPeriode().getId() != null) {
                Periode periode = periodeRepository.getOne(revenue.getPeriode().getId());
                revenue.setPeriode(periode);
            }

            revenueRepository.save(revenue);


            if (revenuePrimes != null) {
                for (RevenuePrime revenuePrime : revenuePrimes) {
                    if (revenuePrime.getPrime().getId() != null) {
                        Prime prime = primeRepository.getOne(revenuePrime.getPrime().getId());
                        revenuePrime.setPrime(prime);
                        revenuePrime.setRevenue(revenue);
                        revenuePrime.setId(new RevenuePrimeId(prime.getId(),revenue.getId()));
                        revenuePrimeRepository.save(revenuePrime);
                    }
                }
            }
            if (revenueRetenues != null) {
                for (RevenueRetenue revenueRetenue : revenueRetenues) {
                    if (revenueRetenue.getRetenue().getId() != null) {
                        Retenue retenue = retenueRepository.getOne(revenueRetenue.getRetenue().getId());
                        revenueRetenue.setRetenue(retenue);
                        revenueRetenue.setRevenue(revenue);
                        revenueRetenue.setId(new RevenueRetenueId(retenue.getId(),revenue.getId()));
                        revenueRetenueRepository.save(revenueRetenue);
                    }
                }


            }
        }
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/update/{id}")
    public Revenue update (@PathVariable Integer id,@Valid @RequestBody  Revenue newRevenue){
        Optional<Revenue> revenue = revenueRepository.findById(id);
        Revenue revenueEntity = null;
        if(revenue.isPresent()) {
            revenueEntity.setNbrJoursCongePaye(newRevenue.getNbrJoursCongePaye());
            revenueEntity.setNbrJoursTravaille(newRevenue.getNbrJoursTravaille());
            revenueEntity.setNbrJoursFerie(newRevenue.getNbrJoursFerie());
            revenueEntity.setNbrJoursCongePaye(newRevenue.getNbrJoursCongePaye());
            if(newRevenue!=null && newRevenue.getEmploye()!=null && newRevenue.getEmploye().getId()!=null){
                Employe employe = employeRepository.getOne(newRevenue.getEmploye().getId());
                newRevenue.setEmploye(employe);
            }
            revenueRepository.save(revenueEntity);
        }
        return revenueEntity;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Integer id){
        revenueRepository.deleteById(id);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/byEmploye/{employeId}/{periodeId}")
    public RevenueList getRevenueByEmploye(
            @PathVariable Integer employeId,
            @PathVariable Integer periodeId
    ){
        Employe employe = employeRepository.getOne(employeId);
        Periode periode = periodeRepository.getOne(periodeId);

        List<Revenue> revenues = revenueRepository.findByEmployeAndPeriode(employe,periode);
        RevenueList revenueList = new RevenueList();

        Employe emp = new Employe();
        if(revenues != null && revenues.size()>0){
            emp.setId(revenues.get(0).getEmploye().getId());
            emp.setMatricule(revenues.get(0).getEmploye().getMatricule());
            emp.setNom(revenues.get(0).getEmploye().getNom());
            emp.setPrenom(revenues.get(0).getEmploye().getPrenom());
            emp.setSalairedeBase(revenues.get(0).getEmploye().getSalairedeBase());
            emp.setNbrEnfants(revenues.get(0).getEmploye().getNbrEnfants());
            emp.setSituation(revenues.get(0).getEmploye().getSituation());
            emp.setNbrDeduction(revenues.get(0).getEmploye().getNbrDeduction());
            emp.setNumCnss(revenues.get(0).getEmploye().getNumCnss());
            emp.setCin(revenues.get(0).getEmploye().getCin());
            emp.setAdresse(revenues.get(0).getEmploye().getAdresse());
            emp.setModeReglement(revenues.get(0).getEmploye().getModeReglement());
            emp.setService(revenues.get(0).getEmploye().getService());
            emp.setFonction(revenues.get(0).getEmploye().getFonction());
            emp.setDateDentree(revenues.get(0).getEmploye().getDateDentree());
            emp.setDatedeNaissance(revenues.get(0).getEmploye().getDatedeNaissance());

            Periode prd = new Periode();
            prd.setId(revenues.get(0).getPeriode().getId());
            prd.setDateDebut(revenues.get(0).getPeriode().getDateDebut());
            prd.setDateFin(revenues.get(0).getPeriode().getDateFin());

            revenueList.setPeriode(prd);
            revenueList.setEmploye(emp);

            for (Revenue revenue : revenues){
                RevenueDto revenueDto = new RevenueDto();
                revenueDto.setNbrJoursTravaille(revenue.getNbrJoursTravaille());
                revenueDto.setNbrJoursCongePaye(revenue.getNbrJoursCongePaye());
                revenueDto.setNbrJoursFerie(revenue.getNbrJoursFerie());

                Set<PrimeDto> primes = new HashSet<>();
                for (RevenuePrime revenuePrime : revenue.getPrimes()){
                    PrimeDto primeDto = new PrimeDto();
                    Prime prime = primeRepository.getOne(revenuePrime.getId().getPrimeId());
                    primeDto.setId(prime.getId());
                    primeDto.setMontant(revenuePrime.getMontant());
                    primeDto.setLibelle(prime.getLibelle());
                    primes.add(primeDto);

                }
                Set<RetenueDto> retenues = new HashSet<>();
                for (RevenueRetenue revenueRetenue : revenue.getRetenues()){
                    RetenueDto retenueDto = new RetenueDto();
                    Retenue retenue = retenueRepository.getOne(revenueRetenue.getId().getRetenueId());
                    retenueDto.setId(retenue.getId());
                    retenueDto.setMontant(revenueRetenue.getMontant());
                    retenueDto.setLibelle(retenue.getLibelle());
                    retenues.add(retenueDto);

                }
                revenueDto.setPrimes(primes);
                revenueDto.setRetenues(retenues);

                revenueList.getRevenues().add(revenueDto);

            }

        }

        return revenueList ;
    }
}
