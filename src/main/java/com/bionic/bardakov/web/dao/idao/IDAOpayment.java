package com.bionic.bardakov.web.dao.idao;

import com.bionic.bardakov.web.entities.Payment;

/**
 * User: ${Bogdan}
 * Date: 29.03.14
 * Time: 20:36
 */
public interface IDAOpayment {
    public void add(Payment payment);
    public void update(Payment payment);
    Payment[] findWherePaymentFromAccountEquals(long paymentFromAccount) throws Exception;
}
