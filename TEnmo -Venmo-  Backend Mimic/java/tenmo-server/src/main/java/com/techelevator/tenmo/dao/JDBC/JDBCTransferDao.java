package com.techelevator.tenmo.dao.JDBC;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.exception.InsufficientFundsException;
import com.techelevator.tenmo.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JDBCTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TransferDTO createTransfer(TransferDTO transfersDTO) {
        Account account = null;
        BigDecimal balance = null;
        String sql = "SELECT balance FROM accounts JOIN users ON accounts.user_id = users.user_id WHERE users.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transfersDTO.getUserFrom());
        if (results.next()) {
            balance = results.getBigDecimal("balance");
        }
        //if user has enough money in the account
        if (balance.compareTo(transfersDTO.getAmount()) >= 0){

            //get senders current balance
            String sql2 = "SELECT balance FROM accounts WHERE user_id = ?";
            SqlRowSet results2= jdbcTemplate.queryForRowSet(sql2, transfersDTO.getUserFrom());
            BigDecimal currentBalanceSender = null;
            BigDecimal currentBalanceReceiver = null;
            if (results2.next()){
                currentBalanceSender = results2.getBigDecimal("balance");
            }
            //subtract transfer amount from senders current balance, then update senders balance in DB
            BigDecimal updatedSenderBalance = currentBalanceSender.subtract(transfersDTO.getAmount());
            String sql3 = "UPDATE accounts SET balance =? WHERE user_id =?";
            jdbcTemplate.update(sql3, updatedSenderBalance, transfersDTO.getUserFrom());



            //get receivers current balance
            String sql4 = "SELECT balance FROM accounts WHERE user_id = ?";
            SqlRowSet results3= jdbcTemplate.queryForRowSet(sql2, transfersDTO.getUserTo());
            if (results3.next()){
                currentBalanceReceiver = results3.getBigDecimal("balance");
            }
            //add transfer amount to receivers balance, then update receivers balance in DB
            BigDecimal updatedReceiverBalance = currentBalanceReceiver.add(transfersDTO.getAmount());
            String sql5 = "UPDATE accounts SET balance =? WHERE user_id =?";
            jdbcTemplate.update(sql5, updatedReceiverBalance, transfersDTO.getUserTo());



            //when a transfer is received, add transfer details to the Transfer table in the DB
            String sql6 = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                    "VALUES (?, ?, (SELECT account_id FROM accounts WHERE user_id = ?), (SELECT account_id FROM accounts WHERE user_id = ?), ?)";
            jdbcTemplate.update(sql6, 2, 2, transfersDTO.getUserFrom(), transfersDTO.getUserTo(), transfersDTO.getAmount());
            return transfersDTO;
        }
        //throw new Exception("Insufficient funds!");
        return null; //error message
    }

    @Override
    public List<ViewTransfersDTO> getAllTransfersByID(int userId) {

        List<ViewTransfersDTO> transfersList = new ArrayList<>();
        String sql = "SELECT t.transfer_id ,t.amount, c.username AS user_to, d.username AS user_from FROM transfers t " +
        "JOIN accounts a ON t.account_from = a.account_id " +
        "JOIN accounts b ON t.account_to = b.account_id " +
        "JOIN users c ON a.user_id = c.user_id " +
        "JOIN users d ON b.user_id = d.user_id " +
        "WHERE a.user_id = ? OR b.user_id = ? ";
         SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
         while (results.next()){
             transfersList.add(mapResultsToViewTransferDTO(results));
         }
        return transfersList;
    }

    @Override
    public TransferDetailDTO findTransferByID(int transferId) {
        TransferDetailDTO transferDetailDTO = null;
        String sql = "SELECT t.transfer_id, ts.transfer_status_desc, tt.transfer_type_desc, amount, c.username AS user_to, d.username AS user_from  FROM transfers t " +
                    "JOIN accounts a ON t.account_from = a.account_id " +
                    "JOIN accounts b ON t.account_to = b.account_id " +
                    "JOIN users c ON a.user_id = c.user_id  " +
                    "JOIN users d ON b.user_id = d.user_id " +
                    "JOIN transfer_types tt ON tt.transfer_type_id = t.transfer_type_id " +
                    "JOIN transfer_statuses ts ON ts.transfer_status_id = t.transfer_status_id " +
                    "WHERE transfer_id = ? ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        while (results.next()){
            transferDetailDTO = mapResultsToTransferDetailDTO(results);
        }

        return transferDetailDTO;
    }


    private TransferDetailDTO mapResultsToTransferDetailDTO(SqlRowSet results){
        TransferDetailDTO transferDetailDTO = new TransferDetailDTO();
        transferDetailDTO.setTransferId(results.getInt("transfer_id"));
        transferDetailDTO.setTransferStatusDesc(results.getString("transfer_status_desc"));
        transferDetailDTO.setTransferTypeDesc(results.getString("transfer_type_desc"));
        transferDetailDTO.setAmount(results.getBigDecimal("amount"));
        transferDetailDTO.setUserTo(results.getString("user_to"));
        transferDetailDTO.setUserFrom(results.getString("user_from"));
        return transferDetailDTO;
    }

    private ViewTransfersDTO mapResultsToViewTransferDTO (SqlRowSet results){
        ViewTransfersDTO viewDTO = new ViewTransfersDTO();
        viewDTO.setAmount(results.getBigDecimal("amount"));
        viewDTO.setTransferId(results.getInt("transfer_id"));
        viewDTO.setUserFrom(results.getString("user_from"));
        viewDTO.setUserTo(results.getString("user_to"));
        return viewDTO;
    }




}
