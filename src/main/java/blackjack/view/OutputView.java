package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.dto.gamer.PlayerInfo;
import blackjack.dto.gamer.PlayerInfos;

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

    public void printInitCardStatus(final Card dealerFirstCard, final PlayerInfos playerInfos) {
        printDealMessage(playerInfos);

        printDealerInitCardsStatusMessage(dealerFirstCard);
        printPlayersInitCardsStatusMessage(playerInfos);
    }

    private void printDealMessage(final PlayerInfos playerInfos) {
        System.out.println(String.format("%n딜러와 %s에게 %d장을 나누었습니다.",
                createPlayerNamesText(playerInfos), Dealer.INIT_CARD_COUNT));
    }

    private void printDealerInitCardsStatusMessage(final Card dealerFirstCard) {
        System.out.println(String.format("딜러: %s", createCardInfoText(dealerFirstCard)));
    }

    private void printPlayersInitCardsStatusMessage(final PlayerInfos playerInfos) {
        for (PlayerInfo playerInfo : playerInfos.playerInfos()) {
            printCardsStatus(playerInfo);
        }
        printLine();
    }

    private String createPlayerNamesText(final PlayerInfos playerInfos) {
        StringJoiner playerNameJoiner = new StringJoiner(PLAYER_NAME_DELIMITER);
        for (PlayerInfo playerInfo : playerInfos.playerInfos()) {
            playerNameJoiner.add(playerInfo.name());
        }

        return playerNameJoiner.toString();
    }

    private String createCardInfoText(final Card card) {
        return String.format(CARD_INFO_MESSAGE, card.getRankName(), card.getSuitName());
    }

    public void printCardsStatus(final PlayerInfo playerInfo) {
        System.out.println(
                String.format(PLAYER_CARD_INFO_MESSAGE, playerInfo.name(), createCardsInfoText(playerInfo.cards())));
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

    public void printTotalCardsStatus(final Dealer dealer, final Players players) {
        printDealerTotalCardsStatus(dealer);
        printPlayersTotalCardsStatus(players);
    }

    private void printDealerTotalCardsStatus(final Dealer dealer) {
        printLine();
        System.out.println(String.format("딜러 카드: %s - 결과: %d",
                createCardsInfoText(dealer.getCards()), dealer.getScore()));
    }

    private void printPlayersTotalCardsStatus(final Players players) {
        for (Player player : players.getPlayers()) {
            System.out.println(String.format("%s카드: %s - 결과: %d",
                    player.getName(), createCardsInfoText(player.getCards()), player.getScore()));
        }
    }

    private void printLine() {
        System.out.println();
    }
}
