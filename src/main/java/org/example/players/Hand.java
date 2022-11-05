package org.example.players;

import org.example.cards.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand<T extends Card<T>> {

    protected final List<T> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(T card) {
        this.cards.add(card);
    }
}
