package com.project.rentItUp.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderItemDetails")
public class OrderItemDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="itemId")
	private long itemId;
	
	@ManyToOne(cascade=CascadeType.ALL)
    private OrderDetails orderId;
	
	@OneToOne
    @JoinColumn(name = "productId")
    private ProductDetails productId;
    
    @Column(name="quantity")
    private int quantity;
    
    @Column(name="itemAmount")
    private float itemAmount;

    public OrderItemDetails()
    {
    	
    }
    public OrderItemDetails(OrderDetails order, ProductDetails product, int quantity, float itemAmount)
    {
    	this.orderId=order;
    	this.productId=product;
    	this.quantity=quantity;
    	this.itemAmount=itemAmount;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public ProductDetails getProductId() {
        return productId;
    }

    public OrderDetails getOrderId() {
		return orderId;
	}

	public void setOrderId(OrderDetails orderId) {
		this.orderId = orderId;
	}

	public void setProductId(ProductDetails productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return itemAmount;
    }

    public void setPrice(float itemAmount) {
        this.itemAmount = itemAmount;
    }
    
}
