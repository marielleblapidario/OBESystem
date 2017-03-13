/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mariellelapidario
 */
public class Log {
    private int log_no;
    private int txn_no;
    private int userID;
    private String txn_time;
    private String txn_type;
    private String tbl_name;
    private String fld_name;
    private String bfr_value;
    private String ftr_value;

    private String userName;

    /**
     * @return the log_no
     */
    public int getLog_no() {
        return log_no;
    }

    /**
     * @param log_no the log_no to set
     */
    public void setLog_no(int log_no) {
        this.log_no = log_no;
    }

    /**
     * @return the txn_no
     */
    public int getTxn_no() {
        return txn_no;
    }

    /**
     * @param txn_no the txn_no to set
     */
    public void setTxn_no(int txn_no) {
        this.txn_no = txn_no;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the txn_time
     */
    public String getTxn_time() {
        return txn_time;
    }

    /**
     * @param txn_time the txn_time to set
     */
    public void setTxn_time(String txn_time) {
        this.txn_time = txn_time;
    }

    /**
     * @return the txn_type
     */
    public String getTxn_type() {
        return txn_type;
    }

    /**
     * @param txn_type the txn_type to set
     */
    public void setTxn_type(String txn_type) {
        this.txn_type = txn_type;
    }

    /**
     * @return the tbl_name
     */
    public String getTbl_name() {
        return tbl_name;
    }

    /**
     * @param tbl_name the tbl_name to set
     */
    public void setTbl_name(String tbl_name) {
        this.tbl_name = tbl_name;
    }

    /**
     * @return the fld_name
     */
    public String getFld_name() {
        return fld_name;
    }

    /**
     * @param fld_name the fld_name to set
     */
    public void setFld_name(String fld_name) {
        this.fld_name = fld_name;
    }

    /**
     * @return the bfr_value
     */
    public String getBfr_value() {
        return bfr_value;
    }

    /**
     * @param bfr_value the bfr_value to set
     */
    public void setBfr_value(String bfr_value) {
        this.bfr_value = bfr_value;
    }

    /**
     * @return the ftr_value
     */
    public String getFtr_value() {
        return ftr_value;
    }

    /**
     * @param ftr_value the ftr_value to set
     */
    public void setFtr_value(String ftr_value) {
        this.ftr_value = ftr_value;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
