/*package tn.CodeCommanders.controllers;

import jdk.jfr.Event;
import jdk.jfr.EventType;
import tn.CodeCommanders.stock.Stock;

public class CardDeletedEvent extends Event {


    public static final EventType<CardDeletedEvent> CARD_DELETED =
            new EventType<>(Event.ANY, "CARD_DELETED");

    private final Stock stock;

    public CardDeletedEvent(Stock stock) {
        super(CARD_DELETED);
        this.stock = stock ;
    }

    public Stock getStock() {
        return stock;
    }
}*/