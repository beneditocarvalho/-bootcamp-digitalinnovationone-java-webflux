package com.digitalinnovation.one.stockquotesapi;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends R2dbcRepository<Quote, Long> {
}