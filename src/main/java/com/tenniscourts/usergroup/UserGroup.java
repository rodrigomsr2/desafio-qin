package com.tenniscourts.usergroup;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.tenniscourts.config.persistence.BaseEntity;
import com.tenniscourts.permission.Permission;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserGroup extends BaseEntity<Long> {

	private static final long serialVersionUID = 2204544603217947356L;

	private String name;
	
	@ManyToMany
    @JoinTable(name="user_group_permission", joinColumns=
    {@JoinColumn(name="user_group_id")}, inverseJoinColumns=
      {@JoinColumn(name="permission_id")})
	private List<Permission> permissions;
	
}
