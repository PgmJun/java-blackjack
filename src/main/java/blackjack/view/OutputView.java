package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.dto.gamer.GamerCardStateDto;
import blackjack.dto.gamer.PlayerStateDto;
import blackjack.dto.gamer.PlayersStateDto;
import blackjack.dto.profit.GamerProfitStateDto;
import blackjack.dto.profit.GamerProfitStatesDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    private static final OutputView INSTANCE = new OutputView();

    private static final String CARD_INFO_DELIMITER = ", ";
    private static final String PLAYER_NAME_DELIMITER = ", ";
    private static final String CARD_INFO_MESSAGE = "%s%s";
    private static final String PLAYER_CARD_INFO_MESSAGE = "%s카드: %s";

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printInitCardStatus(final Card dealerFirstCard, final PlayersStateDto playersStateDto) {
        printDealMessage(playersStateDto);

        printDealerInitCardsStatusMessage(dealerFirstCard);
        printPlayersInitCardsStatusMessage(playersStateDto);
    }

    private void printDealMessage(final PlayersStateDto playersStateDto) {
        System.out.println(String.format("%n딜러와 %s에게 %d장을 나누었습니다.",
                createPlayerNamesText(playersStateDto), Dealer.INIT_CARD_COUNT));
    }

    private void printDealerInitCardsStatusMessage(final Card dealerFirstCard) {
        System.out.println(String.format("딜러: %s", createCardInfoText(dealerFirstCard)));
    }

    private void printPlayersInitCardsStatusMessage(final PlayersStateDto playersStateDto) {
        for (PlayerStateDto playerStateDto : playersStateDto.infos()) {
            printCardsStatus(playerStateDto);
        }
        printLine();
    }

    private String createPlayerNamesText(final PlayersStateDto playersStateDto) {
        StringJoiner playerNameJoiner = new StringJoiner(PLAYER_NAME_DELIMITER);
        for (PlayerStateDto playerStateDto : playersStateDto.infos()) {
            playerNameJoiner.add(playerStateDto.name());
        }

        return playerNameJoiner.toString();
    }

    private String createCardInfoText(final Card card) {
        return String.format(CARD_INFO_MESSAGE, card.getRankName(), card.getSuitName());
    }

    public void printCardsStatus(final PlayerStateDto playerStateDto) {
        System.out.println(
                String.format(PLAYER_CARD_INFO_MESSAGE, playerStateDto.name(), createCardsInfoText(playerStateDto.cardState().cards())));
    }

    private String createCardsInfoText(final List<Card> cards) {
        StringJoiner cardInfoJoiner = new StringJoiner(CARD_INFO_DELIMITER);
        for (Card card : cards) {
            cardInfoJoiner.add(createCardInfoText(card));
        }

        return cardInfoJoiner.toString();
    }

    public void printDealerHitMessage() {
        System.out.println(String.format("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.", Dealer.MAX_HIT_SCORE));
    }

    //TODO: DTO 받도록 변경
    public void printTotalCardsStatus(final GamerCardStateDto dealerCardState, final PlayersStateDto playersStateDto) {
        printDealerTotalCardsStatus(dealerCardState);
        printPlayersTotalCardsStatus(playersStateDto);
    }

    private void printDealerTotalCardsStatus(final GamerCardStateDto dealerCardState) {
        printLine();
        System.out.println(String.format("딜러 카드: %s - 결과: %d",
                createCardsInfoText(dealerCardState.cards()), dealerCardState.score()));
    }

    private void printPlayersTotalCardsStatus(final PlayersStateDto playersStateDto) {
        for (PlayerStateDto playerStateDto : playersStateDto.infos()) {
            List<Card> cards = playerStateDto.cardState().cards();
            int score = playerStateDto.cardState().score();
            System.out.println(String.format("%s카드: %s - 결과: %d",
                    playerStateDto.name(), createCardsInfoText(cards), score));
        }
    }

    public void printTotalProfit(final GamerProfitStatesDto gamerProfitStatesDto) {
        System.out.println(String.format("%n## 최종 수익"));
        printDealerTotalProfit(gamerProfitStatesDto.dealerProfit());
        printPlayersTotalProfit(gamerProfitStatesDto.gamerProfitStateDtos());
    }

    private void printDealerTotalProfit(final BigDecimal dealerProfit) {
        System.out.println(String.format("딜러: %d", dealerProfit.intValue()));
    }

    private void printPlayersTotalProfit(final List<GamerProfitStateDto> gamerProfitStateDtos) {
        for (GamerProfitStateDto playerState : gamerProfitStateDtos) {
            System.out.println(String.format("%s: %d", playerState.name(), playerState.profit().intValue()));
        }
    }

    private void printLine() {
        System.out.println();
    }
}
