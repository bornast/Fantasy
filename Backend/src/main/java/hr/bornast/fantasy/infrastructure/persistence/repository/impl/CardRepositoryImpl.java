package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.CardRepository;
import hr.bornast.fantasy.domain.model.Card;
import hr.bornast.fantasy.infrastructure.persistence.entity.CardEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.CardEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {

    private final CardEntityRepository cardRepository;
    private final ModelMapper mapper;

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll().stream().map(x -> mapper.map(x, Card.class)).toList();
    }

    @Override
    public Optional<Card> findById(int id) {
        return cardRepository.findById(id).map(x -> mapper.map(x, Card.class));
    }

    @Override
    public Card create(Card card) {
        return mapper.map(cardRepository.save(mapper.map(card, CardEntity.class)), Card.class);
    }

}
