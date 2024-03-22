package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GameResults {
    private final Map<Player, GameResult> gameResults;

    public GameResults(final Map<Player, GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public GameResults(final Players players, final Dealer dealer) {
        Map<Player, GameResult> gameResults = new HashMap<>();
        for (final Player player : players.getPlayers()) {
            gameResults.put(player, GameResult.ofPlayer(player, dealer));
        }

        this.gameResults = gameResults;
    }

    public GamerProfits calculateGamerProfits() {
        Map<Player, BigDecimal> playerProfits = new HashMap<>();
        BigDecimal dealerProfit = BigDecimal.ZERO;

        for (Map.Entry<Player, GameResult> entry : gameResults.entrySet()) {
            BigDecimal playerProfit = entry.getKey().calculateProfit(entry.getValue());
            playerProfits.put(entry.getKey(), playerProfit);
            dealerProfit = dealerProfit.subtract(playerProfit);
        }

        return new GamerProfits(playerProfits, dealerProfit);
    }

    @Override
    public String toString() {
        return "GameResults{" +
                "gameResults=" + gameResults +
                '}';
    }
}
