package com.chinasoft.mybatis.entity;


public class SysLetters{
   /**
     * 合同总数量
     */
    private Integer number;
    /**
     * 合同总金额
     */
    private Double smoney;
    /**
     * 已回款
     */
    private Double backMoney;
    /**
     * 待回款
     */
    private Double leftMoney;

    /**
     * 支出总金额
     */
    private Double sumTotal;
    /**
     *行政总支出
     */
    private Double adminExpense;

    /**
     * 客户关怀总支出
     */
    private Double clientExpense;


    /**
     * 项目总支出
     */
    private Double projectExpense;


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getSmoney() {
        return smoney;
    }

    public void setSmoney(Double smoney) {
        this.smoney = smoney;
    }

    public Double getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(Double backMoney) {
        this.backMoney = backMoney;
    }

    public Double getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(Double leftMoney) {
        this.leftMoney = leftMoney;
    }

    public Double getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(Double sumTotal) {
        this.sumTotal = sumTotal;
    }

    public Double getAdminExpense() {
        return adminExpense;
    }

    public void setAdminExpense(Double adminExpense) {
        this.adminExpense = adminExpense;
    }

    public Double getClientExpense() {
        return clientExpense;
    }

    public void setClientExpense(Double clientExpense) {
        this.clientExpense = clientExpense;
    }

    public Double getProjectExpense() {
        return projectExpense;
    }

    public void setProjectExpense(Double projectExpense) {
        this.projectExpense = projectExpense;
    }

    @Override
    public String toString() {
        return "SysLetters{" +
                "number=" + number +
                ", smoney=" + smoney +
                ", backMoney=" + backMoney +
                ", leftMoney=" + leftMoney +
                ", sumTotal=" + sumTotal +
                ", adminExpense=" + adminExpense +
                ", clientExpense=" + clientExpense +
                ", projectExpense=" + projectExpense +
                '}';
    }
}
