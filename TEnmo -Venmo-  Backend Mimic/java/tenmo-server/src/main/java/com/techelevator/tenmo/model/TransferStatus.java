package com.techelevator.tenmo.model;

public class TransferStatus {

    private long transferStatusId;
    private String transferStatusDesc;

    public long getTransferStatusId() {
        return transferStatusId;
    }


    public String getTransferStatusDesc() {
        return transferStatusDesc;
    }

    public void setTransferStatusDesc(String transferStatusDesc) {
        this.transferStatusDesc = transferStatusDesc;
    }
}
