package com.digitalinnovation.one.stockquotesapi;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/quotes")
public class QuoteController {
    private final QuoteRepository quoteRepository;

    public Flux<Quote> getAll(){
        return  quoteRepository.findAll();
    }
}