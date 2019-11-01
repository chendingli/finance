package com.chinasoft.mybatis.entity;

public class SysSpending {

    /**
     * 资金流水
     */
    private Double runn;
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

    public Double getRunn() {
        return runn;
    }

    public void setRunn(Double runn) {
        this.runn = runn;
    }

    @Override
    public String toString() {
        return "SysSpending{" +
                "runn=" + runn +
                ", sumTotal=" + sumTotal +
                ", adminExpense=" + adminExpense +
                ", clientExpense=" + clientExpense +
                ", projectExpense=" + projectExpense +
                '}';
    }
}
