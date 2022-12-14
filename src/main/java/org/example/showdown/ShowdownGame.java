package org.example.showdown;

import org.example.showdown.cards.ShowdownCard;
import org.example.Game;
import org.example.showdown.players.ShowdownAiPlayer;
import org.example.showdown.players.ShowdownHand;
import org.example.showdown.players.ShowdownPlayer;

import java.util.Map;
import java.util.stream.Collectors;

public class ShowdownGame extends Game<ShowdownCard, ShowdownHand, ShowdownPlayer, ShowdownDeck> {

    private final static int TOTAL_ROUNDS = 13;

    private int round = 0;

    public ShowdownGame() {
        super(new ShowdownDeck(), 4);
    }

    @Override
    protected void addOneAiPlayer() {
        this.players.add(new ShowdownAiPlayer());
    }

    @Override
    protected boolean isNotReachDrawCardLimit() {
        return this.deck.hasCardLeft();
    }

    @Override
    protected void prepareBeforeFirstRound() {}

    @Override
    protected boolean isGameNotFinished() {
        return round < TOTAL_ROUNDS;
    }

    @Override
    protected void playOneRound() {
        eachPlayersShowsOneCard();
        Map<ShowdownCard, ShowdownPlayer> revealedCards = revealAllShowedCards();
        ShowdownPlayer winner = findWinner(revealedCards);
        winnerAddOnePoint(winner);
        proceedToNextRound();
    }

    @Override
    protected void findGameWinner() {
        this.winner = this.players.stream()
                .reduce((player1, player2) -> player1.getPoints() - player2.getPoints() > 0 ? player1 : player2)
                .orElseThrow();
    }

    private void eachPlayersShowsOneCard() {
        this.players.forEach(ShowdownPlayer::showCard);
    }

    private Map<ShowdownCard, ShowdownPlayer> revealAllShowedCards() {
        return this.players.stream()
                .collect(Collectors.toMap(ShowdownPlayer::revealCard, player -> player));
    }

    private ShowdownPlayer findWinner(Map<ShowdownCard, ShowdownPlayer> revealedCards) {
        return revealedCards
                .entrySet()
                .stream()
                .reduce((entry1, entry2) -> entry1.getKey().compare(entry2.getKey()) > 0 ? entry1 : entry2)
                .map(Map.Entry::getValue)
                .orElseThrow();
    }

    private void winnerAddOnePoint(ShowdownPlayer winner) {
        winner.addPoint();
        System.out.println("Winner of this round is " + winner.getName());
        System.out.println();
    }

    private void proceedToNextRound() {
        this.round++;
    }
}
