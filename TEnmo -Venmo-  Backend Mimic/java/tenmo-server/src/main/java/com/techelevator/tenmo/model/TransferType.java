package com.techelevator.tenmo.model;

public class TransferType {

    private long transferTypeId;
    private String transferTypeDesc;


    public long getTransferTypeId() {
        return transferTypeId;
    }


    public String getTransferTypeDesc() {
        return transferTypeDesc;
    }

    public void setTransferTypeDesc(String transferTypeDesc) {
        this.transferTypeDesc = transferTypeDesc;
    }
}
