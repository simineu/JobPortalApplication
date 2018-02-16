package com.simi.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="apply_table")
public class Apply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, nullable = false)
	private long ID;
	
	@Column(name ="resume", length=10000)
	private String resume;
	
	@Column(name="person_id")
	private long personApply;
	
	@Column(name="job_id")
	private long jobPostApply;
	
	@Column(name = "jobID")
	private String jobID;
	
	@Column(name ="firstName")
	private String firstName;
	
	@Column(name ="lastName")
	private String lastName;
	
	@Column(name ="jobTitle")
	private String jobTitle;
	
	@Column(name = "companyName")
	private String companyName;
	
	@Column(name ="location")
	private String location;
	
	@Column(name ="type")
	private String type;
	
	@Column(name = "description")
	private String description;
	
	@Column(name ="duration")
	private String duration;
	
	@Column(name = "noOfPosition")
	private String noOfPosition;
	
	@Column(name = "postedOn")
	private String postedOn;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public long getPersonApply() {
		return personApply;
	}

	public void setPersonApply(long personApply) {
		this.personApply = personApply;
	}

	public long getJobPostApply() {
		return jobPostApply;
	}

	public void setJobPostApply(long jobPostApply) {
		this.jobPostApply = jobPostApply;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getNoOfPosition() {
		return noOfPosition;
	}

	public void setNoOfPosition(String noOfPosition) {
		this.noOfPosition = noOfPosition;
	}

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	
	
	

}
