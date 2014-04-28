package com.bionic.bardakov.web.entities;

import java.io.Serializable;

/**
 * User: ${Bogdan}
 * Date: 29.03.14
 * Time: 20:39
 */
public class Card implements Serializable {
    private int cardId;
    private long cardNumber;
    private long userId;

    public Card(){

    }

    public Card(long cardNumber, long userId) {

        this.cardNumber = cardNumber;
        this.userId = userId;
    }

    public int getCardId() {
        return cardId;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (cardId != card.cardId) return false;
        if (cardNumber != card.cardNumber) return false;
        if (userId != card.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cardId;
        result = 31 * result + (int) (cardNumber ^ (cardNumber >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardNumber=" + cardNumber +
                ", userId=" + userId +
                '}';
    }
}
