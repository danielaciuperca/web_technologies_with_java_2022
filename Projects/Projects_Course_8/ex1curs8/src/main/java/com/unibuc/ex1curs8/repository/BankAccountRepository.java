package com.unibuc.ex1curs8.repository;

import com.unibuc.ex1curs8.model.BankAccount;
import com.unibuc.ex1curs8.model.BankAccountType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BankAccountRepository {

    private JdbcTemplate jdbcTemplate;

    public BankAccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createBankAccount(BankAccount bankAccount) {
        String sql = "insert into bankaccounts values (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, null, bankAccount.getAccountNumber(), bankAccount.getBalance(),
                bankAccount.getType().toString(), bankAccount.getCardNumber());
    }

    public Optional<BankAccount> getBankAccount(Long id) {
        String sql = "select * from bankaccounts ba where ba.id = ?";

        RowMapper<BankAccount> mapper = (resultSet, rowNum) ->
             new BankAccount(resultSet.getLong("id"),
                    resultSet.getString("accountNumber"),
                    resultSet.getDouble("balance"),
                    BankAccountType.valueOf(resultSet.getString("type")),
                    resultSet.getString("cardNumber"));

        List<BankAccount> bankAccounts = jdbcTemplate.query(sql, mapper, id);
        if(bankAccounts != null && !bankAccounts.isEmpty()) {
            return Optional.of(bankAccounts.get(0));
        } else {
            return Optional.empty();
        }
    }
}
