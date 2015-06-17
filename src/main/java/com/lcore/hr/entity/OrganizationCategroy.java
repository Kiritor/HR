package com.lcore.hr.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.lcore.hr.core.entity.Root;

@Entity
@Table(name = "Organization_categroy")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class OrganizationCategroy extends Root{
	private String name;
	private Boolean isEnable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

}
