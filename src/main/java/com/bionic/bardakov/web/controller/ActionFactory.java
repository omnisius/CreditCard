/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.bardakov.web.controller;

import com.bionic.bardakov.web.commands.*;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 * User: ${Bogdan}
 * Date: 07.04.14
 * Time: 17:09
 */
class ActionFactory {
    private static ActionFactory instance = null;
    HashMap<String, ActionCommand> commands = new HashMap<String, ActionCommand>();

    private ActionFactory() {
        commands.put("login", new LoginCommand());
        commands.put("lock account", new AccountLockCommand());
        commands.put("unlock account", new AccountUnlockCommand());
        commands.put("update account", new AccountUpdateCommand());
        commands.put("make an admin", new AdminMakeCommand());
        commands.put("registration", new GoToRegistrationCommand());
        commands.put("get new card", new NewCardGetCommand());
        commands.put("add new user", new NewUserAddCommand());
        commands.put("change password", new PasswordChangeCommand());
        commands.put("make payment", new PaymentMakeCommand());
        commands.put("show your cards", new ViewYourCardsCommand());
        commands.put("to account", new GoToAccountCommand());
        commands.put("go to change password", new GoToChangePasswordCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("go to update account", new GoToAccountUpdateCommand());
        commands.put("money", new ViewMoneyCommand());
        commands.put("back to account", new BackToAccountCommand());
        commands.put("go to payment", new GoToMakePayment());
        commands.put("go to unlock account", new GoToAccountUnlockCommand());
        commands.put("local", new LocalChangeCommand());
        commands.put("back to main", new BackToMainCommand());
    }

    public ActionCommand getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        ActionCommand command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static ActionFactory getInstance() {
        if (instance == null) {
            instance = new ActionFactory();
        }
        return instance;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}