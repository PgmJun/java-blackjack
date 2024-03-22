package blackjack.dto.profit;

import java.math.BigDecimal;
import java.util.List;

public record GamerProfitStatesDto(List<GamerProfitStateDto> gamerProfitStateDtos, BigDecimal dealerProfit) {
}
