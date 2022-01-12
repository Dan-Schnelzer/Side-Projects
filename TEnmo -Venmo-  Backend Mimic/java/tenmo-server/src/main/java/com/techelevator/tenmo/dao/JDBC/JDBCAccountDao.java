package com.techelevator.tenmo.dao.JDBC;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.DecimalFormat;

@Component
public class JDBCAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JDBCAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public BigDecimal getBalance(String name) {
        Account account = new Account();
        String sql = "SELECT balance FROM accounts JOIN users ON accounts.user_id = users.user_id WHERE users.username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name );
        if (results.next()){
            account.setBalance(results.getBigDecimal("balance"));
        }
        return account.getBalance();
    }


    //create helper method
    private Account mapResultsToAccount (SqlRowSet results){
        Account account = new Account();
        account.setAccountId(results.getInt("account_id"));
        account.setUserId(results.getInt("user_id"));
        account.setBalance(results.getBigDecimal("balance"));

        return account;
    }

}
