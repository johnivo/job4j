package ru.job4j.crud.servlet;

import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Вспомогательный класс, реализует Dispatch pattern.+
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 16.11.2019
 */
public class DispatchAction {

    /**
     * Dispatch contains entries "action=function"
     */
    private final Map<String, Function<User, Boolean>> dispatch = new HashMap<>();

    /**
     * Dispatch contains entries "action=function"
     */
    private final Validate service;

    public DispatchAction(Validate service) {
        this.service = service;
    }

    /**
     * Handler to add.
     * @return handler.
     */
    public Function<User, Boolean> add() {
        return user -> service.add(user);
    }

    /**
     * Handler to update.
     * @return handler.
     */
    public Function<User, Boolean> update() {
        return user -> service.update(user, user.getId());
    }

    /**
     * Handler to delete.
     * @return handler.
     */
    public Function<User, Boolean> delete() {
        return user -> service.delete(user.getId());
    }

    /**
     * Init's dispatch.
     * @return current object.
     */
    public DispatchAction init() {
        this.load("add", this.add());
        this.load("update", this.update());
        this.load("delete", this.delete());
        return this;
    }

    /**
     * Load action and handler.
     * @param action Action.
     * @param handler Handler.
     */
    public void load(String action, Function<User, Boolean> handler) {
        this.dispatch.put(action, handler);
    }

    /**
     * Sent entry to dispatch.
     * @param action
     * @param user
     * @return true if it finds in a dispatch.
     */
    public boolean sent(String action, User user) {
        return this.dispatch.get(
                action
        ).apply(user);
    }

}
