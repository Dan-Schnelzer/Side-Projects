package com.techelevator.tenmo.dao.JDBC;

import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.*;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.lang.reflect.AccessibleObject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JDBCTransferDaoTest {

    private static SingleConnectionDataSource dataSource;
    private  JdbcTemplate jdbcTemplate;
    private JdbcUserDao userDao;
    private JDBCTransferDao transferDao;
    private JDBCAccountDao accountDao;
    private static final TransferDetailDTO transfer1= new TransferDetailDTO(1001, "Approved", "Send", new BigDecimal(200).setScale(2, RoundingMode.HALF_DOWN), "test1", "test2");
    private static final TransferDetailDTO transfer2= new TransferDetailDTO(1003, "Approved", "Send", new BigDecimal(25).setScale(2, RoundingMode.HALF_DOWN), "test1", "test2");


    @BeforeClass
    public static void setup(){
        dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/tenmo");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        dataSource.setAutoCommit(false);
    }

    @Before
    public void beforeSetUp(){
        transferDao = new JDBCTransferDao(new JdbcTemplate(dataSource));

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
        jdbcTemplate.update(sql);

        sql = "INSERT INTO accounts (account_id, user_id, balance) " +
                "VALUES (8002, 9002, 100)";
        jdbcTemplate.update(sql);

        //create transfers
        sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (1001, 2, 2, 8001, 8002, 200)";
        jdbcTemplate.update(sql);

        sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (1002, 2, 2, 8002, 8001, 50)";
        jdbcTemplate.update(sql);

        userDao = new JdbcUserDao(jdbcTemplate);


    }

    @AfterClass
    public static void end(){
        dataSource.destroy();
    }

    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }


    //This method takes a Transfer DTO to create a Transfer. Transfers are viewed using the ViewTransferDTO object.
    //We had trouble finding out the transferId once a new transfer was created. Therefore, we didn't know what transferId to search for
    //to get the ViewTransferDTO.
    @Test
    public void createTransfer_() {
//        TransferDTO testTransfer = new TransferDTO(9001, 9002, new BigDecimal(25));
//
//       TransferDTO test = transferDao.createTransfer(testTransfer);
//        System.out.println("SAMPLE SOUT :" + test);
//
//        TransferDetailDTO actual = transferDao.findTransferByID(1003);
//        TransferDTO expected = testTransfer;
//
//        assertTransferDetailsMatch(expected, actual);
//
//        //assertTransferDetailsMatch(transfer2, actual);
//        Assert.assertEquals(transfer2.getTransferStatusDesc(), actual.getTransferStatusDesc());
//        Assert.assertEquals(transfer2.getTransferTypeDesc(), actual.getTransferTypeDesc());
//        //Assert.assertEquals(expected.getAmount(), actual.getAmount());
//        Assert.assertEquals(transfer2.getUserTo(), actual.getUserTo());
//        Assert.assertEquals(transfer2.getUserFrom(), actual.getUserFrom());

    }

    //sample test to ensure mock data is working
    @Test
    public void getUserIDBYName (){
        int actual = userDao.findIdByUsername("test1");

        Assert.assertEquals(9001, actual);

    }


    @Test
    public void getAllTransfersByID_returns_2_for_9001 (){
        List<ViewTransfersDTO> actualList = transferDao.getAllTransfersByID(9001);
        Assert.assertEquals(2, actualList.size());
    }

    @Test
    public void getAllTransfersByID_returns_0_for_invalid_id (){
        List<ViewTransfersDTO> actualList = transferDao.getAllTransfersByID(90);
        Assert.assertEquals(0, actualList.size());
    }

    @Test
    public void findTransferByID_input_1001_returns_transfer1(){
        TransferDetailDTO actual = transferDao.findTransferByID(1001);
        assertTransferDetailsMatch(transfer1, actual);
    }

    @Test
    public void findTransferByID_input_11_returns_null(){
        TransferDetailDTO actual = transferDao.findTransferByID(11);
        Assert.assertEquals(null, actual);
    }




    //Helper Methods are below:
    private void assertTransfersMatch (Transfers expected, Transfers actual){
        Assert.assertEquals(expected.getTransferId(), actual.getTransferId());
        Assert.assertEquals(expected.getTransferTypeId(), actual.getTransferTypeId());
        Assert.assertEquals(expected.getTransferStatusId(), actual.getTransferStatusId());
        Assert.assertEquals(expected.getAccountFrom(), actual.getAccountFrom());
        Assert.assertEquals(expected.getAccountTo(), actual.getAccountTo());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
    }

    private void assertAccountsMatch (Account expected, Account actual){
        Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getBalance(), actual.getBalance());
    }

    private void assertTransferDetailsMatch (TransferDetailDTO expected, TransferDetailDTO actual){
        Assert.assertEquals(expected.getTransferId(), actual.getTransferId());
        Assert.assertEquals(expected.getTransferStatusDesc(), actual.getTransferStatusDesc());
        Assert.assertEquals(expected.getTransferTypeDesc(), actual.getTransferTypeDesc());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
        Assert.assertEquals(expected.getUserTo(), actual.getUserTo());
        Assert.assertEquals(expected.getUserFrom(), actual.getUserFrom());


    }

}