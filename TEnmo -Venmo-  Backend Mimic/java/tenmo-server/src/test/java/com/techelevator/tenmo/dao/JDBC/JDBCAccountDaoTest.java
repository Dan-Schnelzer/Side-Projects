package com.techelevator.tenmo.dao.JDBC;

import com.techelevator.tenmo.dao.JdbcUserDao;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class JDBCAccountDaoTest {
    private static SingleConnectionDataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private JDBCAccountDao accountDao;
    private JdbcUserDao userDao;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    @BeforeClass
    public static void setup() {
        dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/tenmo");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        dataSource.setAutoCommit(false);
    }

    @Before
    public void beforeSetUpAccount() {
        accountDao = new JDBCAccountDao(new JdbcTemplate(dataSource));

        //create users
        String sql = "INSERT INTO users (user_id, username, password_hash) " +
                "VALUES (9001, 'test1', 'aaa')";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql);

        sql= "INSERT INTO users (user_id, username, password_hash) " +
                "VALUES (9002, 'test2', 'aaa')";
        jdbcTemplate.update(sql);

        //create accounts
        sql = "INSERT INTO accounts (account_id, user_id, balance) " +
                "VALUES (8001, 9001, 500)";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql);

        sql = "INSERT INTO accounts (account_id, user_id, balance) " +
                "VALUES (8002, 9002, 100)";
        jdbcTemplate.update(sql);

        userDao = new JdbcUserDao(jdbcTemplate);


    }

    @Before
    public void beforeSetUp() {

    }


    @AfterClass
    public static void end() {
        dataSource.destroy();
    }

    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }


    @Test
    public void getBalance_returns_correct_balance() {
        BigDecimal actual = accountDao.getBalance("test1");
        BigDecimal expected=new BigDecimal(500).setScale(2, RoundingMode.HALF_DOWN);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getBalance_returns_null_for_invalid_name() {
        BigDecimal actual = accountDao.getBalance("test5");
        BigDecimal expected=null;

        Assert.assertEquals(expected, actual);
    }
}