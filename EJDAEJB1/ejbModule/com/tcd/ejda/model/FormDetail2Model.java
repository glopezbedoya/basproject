package com.tcd.ejda.model;

import java.sql.Date;

public class FormDetail2Model {
	private String Item_no;
	private String doc_id;
	private double qty_cust_unit;
	private String unit_val_actual;
	private String unit_val_custom;
	private double total_value;
	private double export_rate;
	private double export_amount;
	private String other_tax_type;
	private double other_tax_rate;
	private double other_tax_amount;
	private Date Create_Date;
	private String Create_By;
	private Date update_Date;
	private String Update_by;
	
	//Form 3
	private String originCode;
	private double valuePerUnit;
	
	public String getItem_no() {
		return Item_no;
	}
	public void setItem_no(String itemNo) {
		Item_no = itemNo;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String docId) {
		doc_id = docId;
	}
	public double getQty_cust_unit() {
		return qty_cust_unit;
	}
	public void setQty_cust_unit(double qtyCustUnit) {
		qty_cust_unit = qtyCustUnit;
	}
	public String getUnit_val_actual() {
		return unit_val_actual;
	}
	public void setUnit_val_actual(String unitValActual) {
		unit_val_actual = unitValActual;
	}
	public String getUnit_val_custom() {
		return unit_val_custom;
	}
	public void setUnit_val_custom(String unitValCustom) {
		unit_val_custom = unitValCustom;
	}
	public double getTotal_value() {
		return total_value;
	}
	public void setTotal_value(double totalValue) {
		total_value = totalValue;
	}
	public double getExport_rate() {
		return export_rate;
	}
	public void setExport_rate(double exportRate) {
		export_rate = exportRate;
	}
	public double getExport_amount() {
		return export_amount;
	}
	public void setExport_amount(double exportAmount) {
		export_amount = exportAmount;
	}
	public String getOther_tax_type() {
		return other_tax_type;
	}
	public void setOther_tax_type(String otherTaxType) {
		other_tax_type = otherTaxType;
	}
	public double getOther_tax_rate() {
		return other_tax_rate;
	}
	public void setOther_tax_rate(double otherTaxRate) {
		other_tax_rate = otherTaxRate;
	}
	public double getOther_tax_amount() {
		return other_tax_amount;
	}
	public void setOther_tax_amount(double otherTaxAmount) {
		other_tax_amount = otherTaxAmount;
	}
	public Date getCreate_Date() {
		return Create_Date;
	}
	public void setCreate_Date(Date createDate) {
		Create_Date = createDate;
	}
	public String getCreate_By() {
		return Create_By;
	}
	public void setCreate_By(String createBy) {
		Create_By = createBy;
	}
	public Date getUpdate_Date() {
		return update_Date;
	}
	public void setUpdate_Date(Date updateDate) {
		update_Date = updateDate;
	}
	public String getUpdate_by() {
		return Update_by;
	}
	public void setUpdate_by(String updateBy) {
		Update_by = updateBy;
	}
	public String getOriginCode() {
		return originCode;
	}
	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}
	public double getValuePerUnit() {
		return valuePerUnit;
	}
	public void setValuePerUnit(double valuePerUnit) {
		this.valuePerUnit = valuePerUnit;
	}

	
}
