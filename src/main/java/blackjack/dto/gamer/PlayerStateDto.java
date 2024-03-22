package blackjack.dto.gamer;

import blackjack.domain.gamer.Player;

public record PlayerStateDto(String name, GamerCardStateDto cardState) {

    public static PlayerStateDto from(final Player player) {
        return new PlayerStateDto(player.getName(), player.cardStatus());
    }
}
