package model;

import java.util.Date;

public class Transfer {
    private int id;
    private Date createAt;
    private int createBy;
    private boolean deleted;
    private Date updatedAt;
    private int updatedBy;
    private int fees;
    private int feesAmount;
    private long transactionAmount;
    private long transferAmount;
    private int repId;
    private int senderId;

    public Transfer(){
    }

    public Transfer(int id, Date createAt, int createBy, boolean deleted, Date updatedAt, int updatedBy, int fees, int feesAmount, long transactionAmount, long transferAmount, int repId, int senderId) {
        this.id = id;
        this.createAt = createAt;
        this.createBy = createBy;
        this.deleted = deleted;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
        this.transferAmount = transferAmount;
        this.repId = repId;
        this.senderId = senderId;
    }


    public Transfer(int senderId, int repId, long transferAmount) {
        this.transferAmount = transferAmount;
        this.repId = repId;
        this.senderId = senderId;

    }

    public Transfer(int id, int senderId, String sendName, int repId, String repName, long transactionAmount, int fees, int feesAmount) {
        this.id = id;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
        this.repId = repId;
        this.senderId = senderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public int getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(int feesAmount) {
        this.feesAmount = feesAmount;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public long getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(long transferAmount) {
        this.transferAmount = transferAmount;
    }

    public int getRepId() {
        return repId;
    }

    public void setRepId(int repId) {
        this.repId = repId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}
