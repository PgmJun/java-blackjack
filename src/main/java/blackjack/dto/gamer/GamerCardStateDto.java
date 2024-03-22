package blackjack.dto.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public record GamerCardStateDto(List<Card> cards, int score) {
}
