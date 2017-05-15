package com.project.rentItUp.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ProductDetails")
//@PrimaryKeyJoinColumn(name="productOwner")
public class ProductDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="productId")
	private long productId;
	
	@Column(name="productName")  
    private String productName;
	
	@Column(name="productDescription")
    private String productDescription;
	
	@Column(name="price")
    private float price;
	
	@Column(name="category")
	private String category;
	
	//@JoinColumn(name="userId")
	@ManyToOne (cascade=CascadeType.ALL)
    private UserDetails user;
	
	@Column(name="isAvailable")
    private boolean isAvailable;

	@Column(name="isApproved")
	private boolean isApproved;
	
	@Transient
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	@OneToMany(mappedBy = "productDetails")
	private Set<ProductReviews> productReviewMapping=new HashSet<ProductReviews>();
	

	public ProductDetails(){
		
	}
	public ProductDetails(UserDetails user, String name, String description, float price, String category, boolean available, boolean approved){
		this.user=user;
		this.productName=name;
		this.productDescription=description;
		this.price=price;
		this.category=category;
		this.isAvailable=available;
		this.isApproved=approved;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Set<ProductReviews> getProductReviewMapping() {
		return productReviewMapping;
	}

	public void setProductReviewMapping(Set<ProductReviews> productReviewMapping) {
		this.productReviewMapping = productReviewMapping;
	}

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public UserDetails getUserId() {
		return user;
	}

	public void setUserId(UserDetails userId) {
		this.user = userId;
	}

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
