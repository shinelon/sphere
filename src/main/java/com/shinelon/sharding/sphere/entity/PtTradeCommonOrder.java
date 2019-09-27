package com.shinelon.sharding.sphere.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName PtTradeCommonOrder
 * @Author syq
 * @Date 2019/9/27 9:53
 **/
public class PtTradeCommonOrder {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 渠道代码
     */
    private String channelCode;

    /**
     * APP代码
     */
    private String appCode;

    /**
     * 产品代码
     */
    private String productCode;

    /**
     * 会员标识
     */
    private String vipUuid;

    /**
     * 商户编号
     */
    private String customerNo;

    /**
     * 核心平台订单编号
     */
    private String coreOrderNo;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;

    /**
     * 基础比例
     */
    private BigDecimal baseRate;

    /**
     * 计算积分
     */
    private Long calcPoints;

    /**
     * 是否同步账户信息
     */
    private Integer handleStatus;

    /**
     * 权益类型
     */
    private String interestsCode;
    /***
     * 累计值，不包含本次
     */
    private BigDecimal cumulative;
    /***
     * 本次使用基准值
     */
    private BigDecimal benchmark;

    /**
     * 超过基数金额
     */
    private BigDecimal interestsAmount;

    /**
     * 权益倍数
     */
    private BigDecimal interestsRate;

    /**
     * 额外积分
     */
    private Long interestsPoints;

    /**
     * 权益处理情况
     */
    private Integer interestsStatus;
    /***
     * 订单创建时间
     */
    private Date orderCreateTime;

    public String getAppCode() {
        return appCode;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public BigDecimal getBenchmark() {
        return benchmark;
    }

    public Long getCalcPoints() {
        return calcPoints;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public String getCoreOrderNo() {
        return coreOrderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BigDecimal getCumulative() {
        return cumulative;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getInterestsAmount() {
        return interestsAmount;
    }

    public String getInterestsCode() {
        return interestsCode;
    }

    public Long getInterestsPoints() {
        return interestsPoints;
    }

    public BigDecimal getInterestsRate() {
        return interestsRate;
    }

    public Integer getInterestsStatus() {
        return interestsStatus;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getVipUuid() {
        return vipUuid;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }

    public void setBenchmark(BigDecimal benchmark) {
        this.benchmark = benchmark;
    }

    public void setCalcPoints(Long calcPoints) {
        this.calcPoints = calcPoints;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public void setCoreOrderNo(String coreOrderNo) {
        this.coreOrderNo = coreOrderNo;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setCumulative(BigDecimal cumulative) {
        this.cumulative = cumulative;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInterestsAmount(BigDecimal interestsAmount) {
        this.interestsAmount = interestsAmount;
    }

    public void setInterestsCode(String interestsCode) {
        this.interestsCode = interestsCode;
    }

    public void setInterestsPoints(Long interestsPoints) {
        this.interestsPoints = interestsPoints;
    }

    public void setInterestsRate(BigDecimal interestsRate) {
        this.interestsRate = interestsRate;
    }

    public void setInterestsStatus(Integer interestsStatus) {
        this.interestsStatus = interestsStatus;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public void setVipUuid(String vipUuid) {
        this.vipUuid = vipUuid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
