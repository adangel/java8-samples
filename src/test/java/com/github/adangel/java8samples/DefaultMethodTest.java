package com.github.adangel.java8samples;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class DefaultMethodTest {

    public static class BudgetAccount {
        private String name;
        private BigDecimal expenditure;
        public BudgetAccount(String name, BigDecimal expenditure) { this.name = name; this.expenditure = expenditure; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public BigDecimal getExpenditure() { return expenditure; }
        public void setExpenditure(BigDecimal expenditure) { this.expenditure = expenditure; }
        public String toString() { return name + "(" + expenditure + ")"; }
    }

    public static class BudgetAccountComparator implements Comparator<BudgetAccount> {
        @Override
        public int compare(BudgetAccount o1, BudgetAccount o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    List<BudgetAccount> accounts = Arrays.asList(new BudgetAccount("Science", new BigDecimal("100")),
            new BudgetAccount("Fiction", new BigDecimal("150")));

    @Test
    public void comparingComparator() {
        accounts.sort(new BudgetAccountComparator());
        System.out.println(accounts);
    }

    @Test
    public void comparingLambda() {
        accounts.sort(Comparator.comparing(account -> account.getName()));
        System.out.println(accounts);
    }

    @Test
    public void comparingReferences() {
        accounts.sort(Comparator.comparing(BudgetAccount::getName).reversed()
                .thenComparing(BudgetAccount::getExpenditure));
        System.out.println(accounts);
    }
}
