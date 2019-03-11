package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        this.dataBase.putIfAbsent(user, new ArrayList<>());
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
            //for (User user : this.dataBase.keySet()) {
            //    if (user.getPassport().equals(passport) && (getActualAccount(passport, account.getRequisites()) == null)) {
            //        this.dataBase.get(user).add(account);
            //        break;
            //    }
            //}
            this.dataBase.entrySet()
                    .stream()
                    .filter(
                            e -> e.getKey().getPassport().contains(passport)
                    )
                    .collect(Collectors.toMap(
                            e -> e.getKey(),
                            e -> e.getValue().add(account))
                    );
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
            //for (User user : this.dataBase.keySet()) {
            //    if (getActualAccount(passport, account.getRequisites()) != null) {
            //        this.dataBase.get(user).remove(account);
            //        break;
            //    }
            //}
            this.dataBase.entrySet()
                    .stream()
                    .filter(
                            e -> e.getKey().getPassport().contains(passport)
                    )
                    .collect(Collectors.toMap(
                            e -> e.getKey(),
                            e -> e.getValue().remove(account))
                    );
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
            //for (Map.Entry<User, List<Account>> entry : this.dataBase.entrySet()) {
            //    if (entry.getKey().getPassport().equals(passport)) {
            //        accountsList = entry.getValue();
            //        break;
            //    }
            //}
            accountsList = this.dataBase.entrySet()
                    .stream()
                    .filter(
                            e -> e.getKey().getPassport().contains(passport)
                    )
                    .map(
                            e -> e.getValue()
                    )
                    .collect(Collectors.toList()).get(0);
        }
        return accountsList;
    }

    /**
     * Метод проверяет наличие счета у клиента.
     *
     * @param passport номер паспорта.
     * @param requisite номер банковского счета.
     * @return result возвращает счет или null.
     */
    public Account getActualAccount(String passport, String requisite) {
        //Account result = null;
        //for (Account account : getUserAccounts(passport)) {
        //    if (account.getRequisites().equals(requisite)) {
        //        result = account;
        //    }
        //}
        Account result = getUserAccounts(passport)
                .stream()
                .filter(
                        e -> e.getRequisites().equals(requisite)
                )
                .findFirst()
                .orElse(null);
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
        //boolean success = false;
        //if (src != null && amount > 0 && amount <= src.getValue() && dest != null) {
        //    success = true;
        //    src.setValue(src.getValue() - amount);
        //    dest.setValue(dest.getValue() + amount);
        //}
        //return success;
        return src.transfer(src, dest, amount);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Bank{accounts=")
                .append(dataBase)
                .append("}")
                .toString();
    }
}