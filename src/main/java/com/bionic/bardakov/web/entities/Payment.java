package com.bionic.bardakov.web.entities;

import java.io.Serializable;

/**
 * User: ${Bogdan}
 * Date: 29.03.14
 * Time: 20:39
 */
public class Payment implements Serializable {
    private int id;
    private float sum;
    private long paymentFromAccount;

    public Payment(){

    }

    public Payment( float sum, long paymentFromAccount) {
        this.sum = sum;
        this.paymentFromAccount = paymentFromAccount;
    }

    public int getId() {
        return id;
    }

    public float getSum() {
        return sum;
    }

    public long getPaymentFromAccount() {
        return paymentFromAccount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public void setPaymentFromAccount(long paymentFromAccount) {
        this.paymentFromAccount = paymentFromAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (paymentFromAccount != payment.paymentFromAccount) return false;
        if (Float.compare(payment.sum, sum) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sum != +0.0f ? Float.floatToIntBits(sum) : 0);
        result = 31 * result + (int) (paymentFromAccount ^ (paymentFromAccount >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", sum=" + sum +
                ", paymentFromAccount=" + paymentFromAccount +
                '}';
    }
}
