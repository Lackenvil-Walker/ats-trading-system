/*@RestController @RequestMapping("/reference")
class RefDataController {
    record Index(String code,String ccy,String dayCount){}
    @GetMapping("/indices") List<Index> indices(){ return List.of(new Index("USD-LIBOR-3M","USD","ACT/360")); }
}*/


package com.ats.refdata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reference")
public class RefDataController {

    public record Index(String code, String ccy, String dayCount) {}

    @GetMapping("/indices")
    public List<Index> indices() {
        return List.of(new Index("USD-LIBOR-3M", "USD", "ACT/360"));
    }
}
