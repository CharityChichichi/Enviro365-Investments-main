package com.enviro.assessment.grad001.charitychichichi.controllers.restApi;

import com.enviro.assessment.grad001.charitychichichi.domain.Address;
import com.enviro.assessment.grad001.charitychichichi.domain.Investor;
import com.enviro.assessment.grad001.charitychichichi.domain.Product;
import com.enviro.assessment.grad001.charitychichichi.exceptions.ResourceNotFound;
import com.enviro.assessment.grad001.charitychichichi.service.InvestorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/investors" ,produces = "application/json")
public class InvestorRestController {

    private final InvestorService investorService;

    public InvestorRestController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @GetMapping
    public ResponseEntity<List<Investor>> findAll(){
        return ResponseEntity.ok(this.investorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getInvestor(@PathVariable("id") Long id) throws ResourceNotFound {

        Optional<Investor> investorOptional = investorService.findById(id);

        if(!investorOptional.isPresent()){
            return ResponseEntity
                    .notFound()
                    .build(); // or throw Resource not found exception
        }
        return ResponseEntity
                .ok(investorOptional.get());
    }

    @PostMapping
    public ResponseEntity savEntity(@RequestBody Investor investor){
        if(investor.getAddress()!=null){
            investor.getAddress().setInvestor(investor);
        }

      if(investor.getProducts()!=null){
          investor.getProducts().forEach(d-> d.setInvestor(investor));
      }

        Investor investor1 = investorService.save(investor);
        return ResponseEntity.created(URI.create("/investors/"+investor1.getId())).body("Investor saved successfully");
    }

    @DeleteMapping("/{investorId}")
    public ResponseEntity delete(@PathVariable("investorId") Long investorId){
        Optional<Investor> investorOptional = investorService.findById(investorId);

        if(!investorOptional.isPresent()){
            return ResponseEntity
                    .notFound()
                    .build(); // or throw Resource not found exception
        }
        investorService.deleteById(investorId);
        return ResponseEntity.ok().body("Deleted successfully");
    }

    @PatchMapping("/{id}")
    public Investor updateInvestor(@PathVariable Long id, @RequestBody Investor investorUpdates) throws ResourceNotFound {
        // Retrieve the existing investor object from a database
        Investor existingInvestor = investorService.findById(id).orElseThrow(() -> new ResourceNotFound("Investor of this id "+id+" Not found"));

        // Update the existing investor object with the new values
        existingInvestor.setName(investorUpdates.getName());
        existingInvestor.setSurname(investorUpdates.getSurname());
        existingInvestor.setDob(investorUpdates.getDob());
        existingInvestor.setAddress(investorUpdates.getAddress());
        existingInvestor.setEmail(investorUpdates.getEmail());
        existingInvestor.setNumber(investorUpdates.getNumber());
        existingInvestor.setAge(investorUpdates.getAge());
        existingInvestor.setProducts(investorUpdates.getProducts());
        existingInvestor.setId(id);

        // Return the updated investor object
        return investorService.save(existingInvestor);
    }
}
