package com.project.rentItUp.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderDetails")
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderId")
	private long orderId;
    
	@Column(name="orderDate")
    private Date date;
	
	@ManyToOne(cascade=CascadeType.ALL)
    private UserDetails user;
	
	@Column(name="orderAmount")
	private float orderAmount;
	
	@Column(name="orderConfirmed")
	private boolean orderConfirmed;

	public OrderDetails(){
		
	}
	
	public OrderDetails(UserDetails user, float amount, Date date, boolean orderConfirmed){
		this.user=user;
		this.date=date;
		this.orderAmount=amount;
		this.orderConfirmed=orderConfirmed;

	}
    public float getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(float orderAmount) {
		this.orderAmount = orderAmount;
	}

	public boolean isOrderConfirmed() {
		return orderConfirmed;
	}

	public void setOrderConfirmed(boolean orderConfirmed) {
		this.orderConfirmed = orderConfirmed;
	}

	public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
