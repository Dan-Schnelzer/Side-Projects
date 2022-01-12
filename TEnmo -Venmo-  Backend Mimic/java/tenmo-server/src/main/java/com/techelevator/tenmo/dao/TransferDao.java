package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.TransferDetailDTO;
import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.ViewTransfersDTO;

import java.util.List;

public interface TransferDao {
   TransferDTO createTransfer (TransferDTO transfers) throws Exception;

   List<ViewTransfersDTO> getAllTransfersByID(int userId);

   TransferDetailDTO findTransferByID(int transferId);

}
