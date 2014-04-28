package com.bionic.bardakov.web.entities;

import java.io.Serializable;

/**
 * User: ${Bogdan}
 * Date: 29.03.14
 * Time: 20:39
 */
public class Account implements Serializable{
    private int id;
    private float money;
    private String status;
    private long accountNumber;
    private long cardNumber;

    public Account() {
    }


    public Account(float money, String status, long cardNumber, long accountNumber) {
        this.money = money;
        this.status = status;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public float getMoney() {
        return money;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountNumber != account.accountNumber) return false;
        if (cardNumber != account.cardNumber) return false;
        if (id != account.id) return false;
        if (Float.compare(account.money, money) != 0) return false;
        if (status != null ? !status.equals(account.status) : account.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (money != +0.0f ? Float.floatToIntBits(money) : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (accountNumber ^ (accountNumber >>> 32));
        result = 31 * result + (int) (cardNumber ^ (cardNumber >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", money=" + money +
                ", status='" + status + '\'' +
                ", accountNumber=" + accountNumber +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
