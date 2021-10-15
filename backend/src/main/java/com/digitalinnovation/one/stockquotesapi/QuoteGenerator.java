package com.digitalinnovation.one.stockquotesapi;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class QuoteGenerator {
    private final QuoteRepository repository;

    @PostConstruct
    public void init() {
        Flux.generate(() -> {
            Quote initialQuote = initialQuote();
            return Tuples.of(initialQuote, createNewQuote(initialQuote));
                }, (state, sink) -> {
                    sink.next(state.getT1());
                    return Tuples.of(state.getT2(), createNewQuote(state.getT1()));
                })
                .delaySubscription(Duration.ofMillis(3000))
                .delayElements(Duration.ofMillis(1000))
                .subscribe(log::info);
    }

    private Quote createNewQuote(Quote previousQuote) {
        var newQuote = Quote.builder()
                .symbol(previousQuote.getSymbol())
                .openValue(previousQuote.getOpenValue() + new RandomDataGenerator().nextUniform(-0.1, 0.1))
                .closeValue(previousQuote.getCloseValue() + new RandomDataGenerator().nextUniform(-0.1, 0.1))
                .timestamp(LocalDateTime.now())
                .build();
        repository.save(newQuote).subscribe(log::info);
        return newQuote;
    }

    private Quote initialQuote() {
        var quote = Quote.builder()
                .openValue(0.2)
                .closeValue(0.2)
                .symbol("TESTE")
                .timestamp(LocalDateTime.now())
                .build();
        repository.save(quote).subscribe(log::info);
        return quote;
    }
}