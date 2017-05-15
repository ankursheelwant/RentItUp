package com.project.rentItUp.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProductReviews")
public class ProductReviews {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reviewId")
	private long reviewId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
    private UserDetails userID;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="productId")
    private ProductDetails productDetails;
	
	@Column(name="reviewDate")
    private Date date;

    public UserDetails getUserID() {
		return userID;
	}

	public void setUserID(UserDetails userID) {
		this.userID = userID;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
