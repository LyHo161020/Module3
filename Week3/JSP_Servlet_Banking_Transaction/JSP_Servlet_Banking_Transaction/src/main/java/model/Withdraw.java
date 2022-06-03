package model;

import java.util.Date;

public class Withdraw {
    private int id;
    private Date createdAt;
    private int createBy;
    private boolean deleted;
    private Date updatedAt;
    private int updateBy;
    private int customerId;
    private long transactionAmount;


    public Withdraw(){
    }

    public Withdraw(int id, Date createdAt, int createBy, boolean deleted, Date updatedAt, int updateBy, int customerId, long transactionAmount) {
        this.id = id;
        this.createdAt = createdAt;
        this.createBy = createBy;
        this.deleted = deleted;
        this.updatedAt = updatedAt;
        this.updateBy = updateBy;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }

    public Withdraw(int customerId, long transactionAmount) {
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
