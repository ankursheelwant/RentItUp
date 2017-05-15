package com.project.rentItUp.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="UserRatings")
public class UserRatings {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ratingId", unique=true)
	private long ratingId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private UserDetails userId;
    
    @Column(name="description")
    private String description;
    
    @Column(name="rating")
    private int rating;

    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }

    public UserDetails getUserId() {
        return userId;
    }

    public void setUserId(UserDetails userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
