package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author pinkman
 * @since 2018-09-28
 */
@TableName("agent_drawings_record")
public class DrawingsRecord extends Model<DrawingsRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 提现金额
     */
    private float amount;
    /**
     * 手续费
     */
    private float commision;
    /**
     * 费率
     */
    private Integer rate;
    /**
     * 提现用户
     */
    private Integer userId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 提现时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 银行卡绑定姓名
     */
    private String name;
    /**
     * 银行卡账号
     */
    private String bankCard;
    /**
     * 银行卡类型
     */
    private Integer bankType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getCommision() {
		return commision;
	}

	public void setCommision(float commision) {
		this.commision = commision;
	}

	public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public Integer getBankType() {
        return bankType;
    }

    public void setBankType(Integer bankType) {
        this.bankType = bankType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DrawingsRecord{" +
        "id=" + id +
        ", amount=" + amount +
        ", commision=" + commision +
        ", rate=" + rate +
        ", userId=" + userId +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", type=" + type +
        ", name=" + name +
        ", bankCard=" + bankCard +
        ", bankType=" + bankType +
        "}";
    }
}
