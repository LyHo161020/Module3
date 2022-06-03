package model;

import java.util.Date;

public class TransferInfo {
    private int id;
    private int sendId;
    private String sendName;
    private int repId;
    private String repName;
    private Date createAt;
    private boolean deleted;
    private Date updatedAt;
    private int fees;
    private int feesAmount;
    private long transactionAmount;
    private long transferAmount;

    public TransferInfo(){
    }

    public TransferInfo(int id, int sendId, String sendName, int repId, String repName, Date createAt, boolean deleted, Date updatedAt, int fees, int feesAmount, long transactionAmount, long transferAmount) {
        this.id = id;
        this.sendId = sendId;
        this.sendName = sendName;
        this.repId = repId;
        this.repName = repName;
        this.createAt = createAt;
        this.deleted = deleted;
        this.updatedAt = updatedAt;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
        this.transferAmount = transferAmount;
    }

    public TransferInfo(int id, int sendId, String sendName, int repId, String repName, long transactionAmount, int fees, int feesAmount) {
        this.id = id;
        this.sendId = sendId;
        this.sendName = sendName;
        this.repId = repId;
        this.repName = repName;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public int getRepId() {
        return repId;
    }

    public void setRepId(int repId) {
        this.repId = repId;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
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
}
