package tn.CodeCommanders.controllers;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class CategoryAddedEvent extends Event {
    public static final EventType<CategoryAddedEvent> CATEGORY_ADDED = new EventType<>(Event.ANY, "CATEGORY_ADDED");

    public CategoryAddedEvent() {
        super(CATEGORY_ADDED);
    }
}