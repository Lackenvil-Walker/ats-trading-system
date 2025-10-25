package com.ats.swaps.api;

import com.ats.swaps.domain.SwapTrade;
import com.ats.swaps.repo.SwapTradeRepo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/swaps")
public class SwapsController {

    private final SwapTradeRepo repo;

    public SwapsController(SwapTradeRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    public SwapTrade create(@RequestBody SwapTrade t) {
        t.setId(UUID.randomUUID());
        t.setStatus("DRAFT");
        return repo.save(t);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SwapTrade> one(@PathVariable UUID id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<SwapTrade> all() {
        return repo.findAll();
    }
}
