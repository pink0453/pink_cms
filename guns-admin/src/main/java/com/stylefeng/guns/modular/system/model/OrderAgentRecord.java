package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author pinkman
 * @since 2018-09-20
 */
@TableName("rp_pay_order_agent_record")
public class OrderAgentRecord extends Model<OrderAgentRecord> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("PRICE")
    private Integer price;
    @TableField("PRODUCT_ID")
    private String productId;
    @TableField("PRODUCT_AMOUNT")
    private Integer productAmount;
    @TableField("PRODUCT_NAME")
    private String productName;
    @TableField("AMOUNT")
    private Integer amount;
    @TableField("USER_ID")
    private Integer userId;
    @TableField("MY_CODE")
    private String myCode;
    @TableField("NICKNAME")
    private String nickname;
    @TableField("GAME_TYPE")
    private String gameType;
    @TableField("AGENT_ID")
    private String agentId;
    @TableField("AGENT_GAME_ID")
    private String agentGameId;
    @TableField("FROM")
    private String from;
    @TableField("STATUS")
    private Integer status;
    @TableField("REMARK")
    private String remark;
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableField("EDIT_TIME")
    private Date editTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMyCode() {
        return myCode;
    }

    public void setMyCode(String myCode) {
        this.myCode = myCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentGameId() {
        return agentGameId;
    }

    public void setAgentGameId(String agentGameId) {
        this.agentGameId = agentGameId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OrderAgentRecord{" +
        "id=" + id +
        ", price=" + price +
        ", productId=" + productId +
        ", productAmount=" + productAmount +
        ", productName=" + productName +
        ", amount=" + amount +
        ", userId=" + userId +
        ", myCode=" + myCode +
        ", nickname=" + nickname +
        ", gameType=" + gameType +
        ", agentId=" + agentId +
        ", agentGameId=" + agentGameId +
        ", from=" + from +
        ", status=" + status +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", editTime=" + editTime +
        "}";
    }
}
