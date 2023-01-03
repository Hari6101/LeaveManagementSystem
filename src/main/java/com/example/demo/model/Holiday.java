package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "holiday")
public class Holiday {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hid;
	 
	 @Column(unique=true)
	private Date dates;
	private String occasion;
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public Date getDates() {
		return dates;
	}
	public void setDates(Date dates) {
		this.dates = dates;
	}
	public String getOccasion() {
		return occasion;
	}
	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}
	@Override
	public String toString() {
		return "Holiday [hid=" + hid + ", dates=" + dates + ", occasion=" + occasion + "]";
	}
	
	

//	public Holiday(String occasion, Date date) {
//		super();
//		
//		this.dates = date;
//		this.occasion = occasion;
//	}
//	public Date getDate() {
//		return dates;
//	}
//	public void setDate(Date date) {
//		this.dates = date;
//	}
//	
//
//	public String getOccasion() {
//		return occasion;
//	}
//	public void setOccasion(String occasion) {
//		this.occasion = occasion;
//	}
//	@Override
//	public String toString() {
//		return "Holidays [ date=" + dates + ", occasion=" + occasion + "]";
//	}
	
}