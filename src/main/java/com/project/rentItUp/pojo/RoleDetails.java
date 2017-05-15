package com.project.rentItUp.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="RoleDetails")
public class RoleDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleId")
	private long roleId;
	
	@Column(name="roleDescription", insertable=false, updatable=false)
    private String roleDescription;
	
	@OneToMany(mappedBy = "roleDetails")
	private Set<RoleMapping> roleMapping=new HashSet<RoleMapping>();

	public RoleDetails(){
		
	}
	public RoleDetails(long roleId, String roleDescription){
		this.roleId=roleId;
		this.roleDescription=roleDescription;
	}
    public long getRoleId() {
        return roleId;
    }

    public Set<RoleMapping> getRoleMapping() {
		return roleMapping;
	}

	public void setRoleMapping(Set<RoleMapping> roleMapping) {
		this.roleMapping = roleMapping;
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
}
