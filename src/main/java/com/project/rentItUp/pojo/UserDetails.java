package com.project.rentItUp.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="UserDetails")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userId", unique=true)
	private long userId;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="contactNo")
	private long contactNo;
	
	@Column(name="password")
	private String password;
	
	@Column(name="address")
	private String address;
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy = "user")
//	    private List<ProductDetails> product;
	
	@Transient
	private long roleId;
	
	@Transient
	private String roleDescription;
	
	
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Set<ProductReviews> getProductReviewMapping() {
		return productReviewMapping;
	}

	public void setProductReviewMapping(Set<ProductReviews> productReviewMapping) {
		this.productReviewMapping = productReviewMapping;
	}

	@OneToMany(mappedBy = "userDetails")
	private Set<RoleMapping> roleMapping=new HashSet<RoleMapping>();
	
	@OneToMany(mappedBy = "userID")
	private Set<ProductReviews> productReviewMapping=new HashSet<ProductReviews>();
	//@OneToMany(mappedBy="user")
	//private Set<ProductDetails> productsOwned;
	
	public Set<RoleMapping> getRoleMapping() {
		return roleMapping;
	}

	public void setRoleMapping(Set<RoleMapping> roleMapping) {
		this.roleMapping = roleMapping;
	}

	UserDetails(){
		
	}
	
	public UserDetails(String userName, String email, long contactNo, String password, String address)
	{
		this.userName=userName;
		this.email=email;
		this.contactNo=contactNo;
		this.password=password;
		this.address=address;
	}
	
    public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String uesrName) {
        this.userName = uesrName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
