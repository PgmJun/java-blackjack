package blackjack.domain.result;

import blackjack.domain.gamer.Player;
import blackjack.dto.profit.GamerProfitStateDto;
import blackjack.dto.profit.GamerProfitStatesDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamerProfits {
    private final Map<Player, BigDecimal> playerProfits;
    private final BigDecimal dealerProfit;

    public GamerProfits(final Map<Player, BigDecimal> playerProfits, BigDecimal dealerProfit) {
        this.playerProfits = new HashMap<>(playerProfits);
        this.dealerProfit = dealerProfit;
    }

    public GamerProfitStatesDto getGamerProfitStates() {
        List<GamerProfitStateDto> profitState = new ArrayList<>();
        for (Map.Entry<Player, BigDecimal> entry : playerProfits.entrySet()) {
            profitState.add(new GamerProfitStateDto(entry.getKey().getName(), entry.getValue()));
        }

        return new GamerProfitStatesDto(profitState, dealerProfit);
    }

    public BigDecimal getDealerProfit() {
        return dealerProfit;
    }

    @Override
    public String toString() {
        return "GamerProfits{" +
                "playerProfits=" + playerProfits +
                ", dealerProfit=" + dealerProfit +
                '}';
    }
}
