package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Card;

public interface CardRepository {
    List<Card> findAll();
    Optional<Card> findById(int id);
    Card create(Card card);
}
