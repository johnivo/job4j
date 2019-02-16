package ru.job4j.bank;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $Id$
 *@since 15.02.2019
 */
public class BankTest {

    /**
     * Test addUser.
     */
    @Test
    public void whenAddUserThenSortedList() {
        Bank bank = new Bank();
        bank.addUser(new User("Max", "123"));
        bank.addUser(new User("Bob", "127"));
        int result = bank.getHashMap().size();
        assertThat(result, is(2));
    }

    /**
     * Test deleteUser.
     */
    @Test
    public void whenDeleteUserThenUpdateMap() {
        Bank bank = new Bank();
        User user1 = new User("Max", "123");
        User user2 = new User("Bob", "127");
        bank.addUser(user1);
        bank.addUser(user2);
        //System.out.println(bank);
        bank.deleteUser(user1);
        int result = bank.getHashMap().size();
        //System.out.println(bank);
        assertThat(result, is(1));
    }

    /**
     * Test addAccountToUser1.
     */
    @Test
    public void whenAddAccountToUserThenUpdateMap() {
        Bank bank = new Bank();
        User user1 = new User("Max", "122");
        bank.addUser(user1);
        //System.out.println(bank);
        bank.addAccountToUser(user1.getPassport(), new Account(150, "124"));
        bank.addAccountToUser(user1.getPassport(), new Account(180, "123"));
        //System.out.println(bank);
        double result = 0;
        for (Map.Entry<User, List<Account>> entry : bank.getHashMap().entrySet()) {
            result = entry.getValue().get(1).getValue();
        }
        assertThat(result, is(180.0));
    }

    /**
     * Test deleteAccountFromUser.
     */
    @Test
    public void whenDeleteAccountFromUserThenUpdateMap() {
        Bank bank = new Bank();
        User user1 = new User("Max", "123");
        bank.addUser(user1);
        Account account1 = new Account(150, "124");
        Account account2 = new Account(200, "123");
        Account account3 = new Account(180, "125");
        bank.addAccountToUser(user1.getPassport(), account1);
        bank.addAccountToUser(user1.getPassport(), account2);
        bank.addAccountToUser(user1.getPassport(), account3);
        bank.deleteAccountFromUser(user1.getPassport(), account2);
        //System.out.println(bank);
        double result = 0;
        for (Map.Entry<User, List<Account>> entry : bank.getHashMap().entrySet()) {
            result = entry.getValue().get(1).getValue();
        }
        assertThat(result, is(180.0));
    }

    /**
     * Test getUserAccounts.
     */
    @Test
    public void whenGetUserAccountsThenMap() {
        Bank bank = new Bank();
        User user1 = new User("Max", "123");
        bank.addUser(user1);
        Account account1 = new Account(150, "124");
        Account account2 = new Account(200, "123");
        Account account3 = new Account(180, "125");
        bank.addAccountToUser(user1.getPassport(), account1);
        bank.addAccountToUser(user1.getPassport(), account2);
        bank.addAccountToUser(user1.getPassport(), account3);
        bank.getUserAccounts(user1.getPassport());
        //System.out.println(bank.getUserAccounts(user1));
        int result = bank.getUserAccounts(user1.getPassport()).size();
        assertThat(result, is(3));
    }

    /**
     * Test transfer between one customer accounts.
     */
    @Test
    public void whenUserFromFirstAccountTransferToSecondAccountThenUpdateMap() {
        Bank bank = new Bank();
        User user = new User("Max", "123");
        bank.addUser(user);
        Account userAcc1 = new Account(150, "126");
        Account userAcc2 = new Account(90, "124");
        bank.addAccountToUser("123", userAcc1);
        bank.addAccountToUser("123", userAcc2);
        //System.out.println(bank);
        bank.transferMoney(user.getPassport(), userAcc2.getRequisites(), user.getPassport(), userAcc1.getRequisites(), 20);
        //System.out.println(bank);
        double result = userAcc2.getValue();
        assertThat(result, is(70.0));
    }

    /**
     * Test transfer between one customer accounts.
     */
    @Test
    public void whenAmountLargerThanValueThenFalse() {
        Bank bank = new Bank();
        User user = new User("Max", "000");
        bank.addUser(user);
        Account userAcc1 = new Account(50, "126");
        Account userAcc2 = new Account(90, "124");
        bank.addAccountToUser(user.getPassport(), userAcc1);
        bank.addAccountToUser(user.getPassport(), userAcc2);
        boolean result = bank.transferMoney("000", "126", "000", "124", 60.0);
        assertThat(result, is(false));
    }

    /**
     * Test transfer between customers.
     */
    @Test
    public void whenFirstUserFromFirstAccountTransferToSecondUserToThirdAccountThenUpdateMap() {
        Bank bank = new Bank();
        User user1 = new User("Max", "000");
        User user2 = new User("Nik", "111");
        bank.addUser(user1);
        bank.addUser(user2);
        Account user1Acc1 = new Account(150, "126");
        Account user2Acc1 = new Account(90, "124");
        Account user2Acc2 = new Account(200, "123");
        Account user2Acc3 = new Account(180, "125");
        bank.addAccountToUser(user1.getPassport(), user1Acc1);
        bank.addAccountToUser(user2.getPassport(), user2Acc1);
        bank.addAccountToUser(user2.getPassport(), user2Acc2);
        bank.addAccountToUser(user2.getPassport(), user2Acc3);
        System.out.println(bank);
        bank.transferMoney(user1.getPassport(), user1Acc1.getRequisites(),
                user2.getPassport(), user2Acc3.getRequisites(), 40.0);
        //bank.transferMoney("000", "126", "111", "125",40.0);
        System.out.println(bank);
        double result = user2Acc3.getValue();
        assertThat(result, is(220.0));
    }

    /**
     * Test transfer between customers.
     */
    @Test
    public void whenDestRequisiteFalseThenFalse() {
        Bank bank = new Bank();
        User user1 = new User("Max", "000");
        User user2 = new User("Nik", "111");
        bank.addUser(user1);
        bank.addUser(user2);
        Account user1Acc1 = new Account(150, "126");
        Account user2Acc1 = new Account(90, "124");
        bank.addAccountToUser(user1.getPassport(), user1Acc1);
        bank.addAccountToUser(user2.getPassport(), user2Acc1);
        boolean result = bank.transferMoney("000", "126", "111", "125", 40.0);
        assertThat(result, is(false));
    }
}