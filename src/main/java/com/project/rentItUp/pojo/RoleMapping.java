package com.project.rentItUp.pojo;

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
@Table(name="RoleMapping")
public class RoleMapping {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleMappingId")
	private long roleMappingId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="roleId")
	private RoleDetails roleDetails;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private UserDetails userDetails;
	
	public RoleMapping(){
		
	}
	
	public RoleMapping(UserDetails user, RoleDetails role){
		this.userDetails=user;
		this.roleDetails=role;
	}
	public long getRoleMappingId() {
		return roleMappingId;
	}

	public void setRoleMappingId(long roleMappingId) {
		this.roleMappingId = roleMappingId;
	}

	public RoleDetails getRoleDetails() {
		return roleDetails;
	}

	public void setRoleDetails(RoleDetails roleDetails) {
		this.roleDetails = roleDetails;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	
}
