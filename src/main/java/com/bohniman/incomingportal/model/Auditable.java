package com.bohniman.incomingportal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * MasterAuditable
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
// @JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters =
// true)
public abstract class Auditable {

	@CreatedBy
	@Column(nullable = false, updatable = false)
	@Size(max = 80)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	@CreatedDate
	private Date createdAt;

	@LastModifiedBy
	@Size(max = 80)
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@LastModifiedDate
	private Date updatedAt;

	public Auditable() {
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}