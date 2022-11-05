package org.example.players.uno;

import org.example.game.uno.Deck;
import org.example.game.uno.Hand;
import org.example.cards.uno.Card;

import java.util.List;

public abstract class Player {

    protected String name;
    protected Hand hand;

    public abstract void nameHimself();

    public abstract Card showMatchedCard(Card top);

    public String getName() {
        return this.name;
    }

    public void drawCard(Deck deck) {
        if (this.hand == null) {
            this.hand = new Hand();
        }
        this.hand.addCard(deck.drawCard());
    }

    public int cardCount() {
        return this.hand.cardCount();
    }

    public boolean hasCardLeft() {
        return this.hand.cardCount() > 0;
    }

    protected List<Card> matchCards(Card target) {
        return this.hand.matchCards(target);
    }
}