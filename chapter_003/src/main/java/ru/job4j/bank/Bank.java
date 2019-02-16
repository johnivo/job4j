package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Bank.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @version $Id$
 * @since 15.02.2019
 */
public class Bank {

    /**
     * dataBase база данных банка.
     */
    private Map<User, List<Account>> dataBase = new HashMap<>();

    /**
     * Геттер к базе.
     */
    public Map<User, List<Account>> getHashMap() {
        return this.dataBase;
    }

    /**
     * Метод добавляет клиента.
     *
     * @param user клиент.
     */
    public void addUser(User user) {
        //this.dataBase.put(user, new ArrayList<>());
        this.dataBase.putIfAbsent(user, getUserAccounts(user.getPassport()));
    }

    /**
     * Метод удаляет клиента.
     *
     * @param user клиент.
     */
    public void deleteUser(User user) {
        this.dataBase.remove(user);
    }

    /**
     * Метод добавляет счет клиенту.
     *
     * @param passport номер паспорта.
     * @param account банковский счет.
     */
    public void addAccountToUser(String passport, Account account) {
        if (!dataBase.isEmpty()) {
            for (Map.Entry<User, List<Account>> entry : this.dataBase.entrySet()) {
                if (entry.getKey().getPassport().equals(passport)) {
                    entry.getValue().add(account);
                    break;
                }
            }
        }
    }

    /**
     * Метод удаляет у клиента один счет.
     *
     * @param passport номер паспорта.
     * @param account банковский счет.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        if (!dataBase.isEmpty()) {
            for (Map.Entry<User, List<Account>> entry : this.dataBase.entrySet()) {
                if (entry.getKey().getPassport().equals(passport)) {
                    entry.getValue().remove(account);
                    break;
                }
            }
        }
    }

    /**
     * Метод возвращает список счетов для клиента.
     *
     * @param passport номер паспорта.
     * @return список счетов.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> accountsList = new ArrayList<>();
        if (!dataBase.isEmpty()) {
            for (Map.Entry<User, List<Account>> entry : this.dataBase.entrySet()) {
                if (entry.getKey().getPassport().equals(passport)) {
                    accountsList = entry.getValue();
                    break;
                }
            }
        }
        return accountsList;
    }

    /**
     * Метод проверяет наличие счета у клиента.
     *
     * @param passport номер паспорта.
     * @param requisite номер банковского счета.
     * @return true or false.
     */
    public Account getActualAccount(String passport, String requisite) {
        Account result = null;
        for (Account account : getUserAccounts(passport)) {
            if (account.getRequisites().equals(requisite)) {
                result = account;
            }
        }
        return result;
    }

    /**
     * Метод проверяет возможность и осуществляет перевод с одного счета на другой.
     *
     * @param srcPassport номер паспорта отправителя.
     * @param srcRequisite счет списания.
     * @param destPassport номер паспорта получателя.
     * @param destRequisite счет назначения.
     * @param amount сумма перевода.
     * @return true or false статус перевода.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String destPassport, String destRequisite, double amount) {
        Account src = getActualAccount(srcPassport, srcRequisite);
        Account dest = getActualAccount(destPassport, destRequisite);
        boolean success = false;
        if (src != null && amount > 0 && amount <= src.getValue() && dest != null) {
            success = true;
            src.setValue(src.getValue() - amount);
            dest.setValue(dest.getValue() + amount);
        }
        return success;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Bank{accounts=")
                .append(dataBase)
                .append("}")
                .toString();
    }

    //List<Account> list = this.dataBase.get(getUserAccounts(passport));
     //Account result = null;
     //for (Account account : list) {
     //if (list.get(list.indexOf(requisite)) != null) {
     //result = account;
     //}
     //}
     //return result;
}