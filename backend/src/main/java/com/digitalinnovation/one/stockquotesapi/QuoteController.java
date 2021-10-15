package com.digitalinnovation.one.stockquotesapi;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class QuoteController {
    private final QuoteRepository quoteRepository;

    public Flux<Quote> getAll(){
        return  quoteRepository.findAll();
    }

    public Mono<Quote> getLastQuote(){
        return  quoteRepository.findAll().last();
    }
}