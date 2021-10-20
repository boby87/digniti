package ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.RestController;

import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Metier.MetierOrangeMoney;
import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.Orangemoney;
import ftg.wesit.digniti.digniti.GestionPayement.Orangemoney.Model.ResultatOrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("orangepayment")
public class Orangepayment {

@Autowired
    MetierOrangeMoney metierOrangeMoney;

@PostMapping("collection")
    ResultatOrange do_payment(@RequestBody Orangemoney orangemoney){
        return metierOrangeMoney.do_payment(orangemoney);
    }
}
