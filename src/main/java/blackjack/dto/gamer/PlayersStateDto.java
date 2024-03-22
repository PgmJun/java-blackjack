package blackjack.dto.gamer;

import blackjack.domain.gamer.Player;

import java.util.List;

public record PlayersStateDto(List<PlayerStateDto> infos) {

    public static PlayersStateDto from(final List<Player> players) {
        return new PlayersStateDto(players.stream()
                .map(PlayerStateDto::from)
                .toList());
    }
}
