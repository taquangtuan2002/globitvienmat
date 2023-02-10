package com.globits.emr.dto;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import com.globits.core.dto.BaseObjectDto;
import com.globits.core.dto.CountryDto;
import com.globits.core.dto.EthnicsDto;
import com.globits.core.dto.PersonDto;
import com.globits.core.dto.ReligionDto;
import com.globits.emr.domain.Patient;
import com.globits.emr.dto.concept.ConceptTypeDto;

public class PatientDto extends BaseObjectDto{
	
	private UUID id;
	
	private String code;//Patient Code
	
	private Boolean voided;
	
	private String firstName;
	
	private String lastName;
	
	private String displayName;
		  
	private String shortName;
	  
	private Date birthDate;
	  
	private String birthPlace;
	  
	private String gender;
	  
	private Date startDate;
	  
	private Date endDate;
	  
	private String phoneNumber;
	  
	private String idNumber;
	  
	private String idNumberIssueBy;
	  
	private Date idNumberIssueDate;
	  
	private String email;
	
	private CountryDto nationality;
	
	private AdministrativeUnitDto nativeVillage;
	  
	private EthnicsDto ethnics;
  
	private ReligionDto religion;
	  
    private byte[] photo;
	  
	private Boolean photoCropped;
	  
	private String imagePath;
	  
//	private Set addresses;
		  
	private Long userId;
		  
	private Date communistYouthUnionJoinDate;
		  
	private Date communistPartyJoinDate;
	  
    private String carrer;
	  
	private Integer maritalStatus;
	
	public PatientDto() {
	}

	public PatientDto(Patient entity) {
		if(entity!=null) {
				this.id=entity.getId();
				this.code = entity.getCode();
				this.voided=entity.getVoided();
				this.firstName = entity.getFirstName();
				this.lastName = entity.getLastName();
				this.shortName=entity.getShortName();
				this.displayName=entity.getDisplayName();
				this.birthDate=entity.getBirthDate();
				this.carrer=entity.getCarrer();
				this.communistPartyJoinDate=entity.getCommunistPartyJoinDate();
				this.communistYouthUnionJoinDate=entity.getCommunistYouthUnionJoinDate();
				this.email=entity.getEmail();
				this.endDate=entity.getEndDate();
				this.gender=entity.getGender();
				this.phoneNumber=entity.getPhoneNumber();
				this.idNumber=entity.getIdNumber();
				this.idNumberIssueBy=entity.getIdNumberIssueBy();
				this.idNumberIssueDate=entity.getIdNumberIssueDate();
				this.imagePath=entity.getImagePath();
				this.photoCropped=entity.getPhotoCropped();
				this.photo=entity.getPhoto();
				this.maritalStatus=entity.getMaritalStatus();
//				this.addresses=entity.getAddress();
				if(entity.getNationality() != null) {
	            	this.nationality = new CountryDto();
	            	this.nationality.setId(entity.getNationality().getId());
	            }
				if(entity.getEthnics() != null) {
	            	this.ethnics = new EthnicsDto();
	            	this.ethnics.setId(entity.getEthnics().getId());
	            }
				if(entity.getReligion() != null) {
	            	this.religion = new ReligionDto();
	            	this.religion.setId(entity.getReligion().getId());
	            }
				if(entity.getNativeVillage() != null) {
	            	this.nativeVillage = new AdministrativeUnitDto();
	            	this.nativeVillage.setId(entity.getNativeVillage().getId());
	            }
				

//				if(entity.getId()!=null) {
//					this.id=entity.getId();
//				}
//				if(entity.getCode()!=null) {
//					this.code = entity.getCode();
//				}
//				if(entity.getVoided()!=null) {
//					this.voided=entity.getVoided();
//				}
//				if(entity.getFirstName()!=null) {
//					this.firstName = entity.getFirstName();
//				}
//				if(entity.getLastName()!=null) {
//					this.lastName = entity.getLastName();
//				}
//				if(entity.getShortName()!=null) {
//					this.shortName=entity.getShortName();
//				}
//				if(entity.getDisplayName()!=null) {
//					this.displayName=entity.getDisplayName();
//				}
//				if(entity.getBirthDate()!=null) {
//					this.birthDate=entity.getBirthDate();
//				}
//				if(entity.getCarrer()!=null) {
//					this.carrer=entity.getCarrer();
//				}
//				if(entity.getCommunistPartyJoinDate()!=null) {
//					this.communistPartyJoinDate=entity.getCommunistPartyJoinDate();
//				}
//				if(entity.getCommunistYouthUnionJoinDate()!=null) {
//					this.communistYouthUnionJoinDate=entity.getCommunistYouthUnionJoinDate();
//				}
//				if(entity.getEmail()!=null) {
//					this.email=entity.getEmail();
//				}
//				if(entity.getEndDate()!=null) {
//					this.endDate=entity.getEndDate();
//				}
//				if(entity.getGender()!=null) {
//					this.gender=entity.getGender();
//				}
//				if(entity.getPhoneNumber()!=null) {
//					this.phoneNumber=entity.getPhoneNumber();
//				}
//				if(entity.getIdNumber()!=null) {
//					this.idNumber=entity.getIdNumber();
//				}
//				if(entity.getIdNumberIssueBy()!=null) {
//					this.idNumberIssueBy=entity.getIdNumberIssueBy();
//				}
//				if(entity.getIdNumberIssueDate()!=null) {
//					this.idNumberIssueDate=entity.getIdNumberIssueDate();
//				}
//				if(entity.getImagePath()!=null) {
//					this.imagePath=entity.getImagePath();
//				}
			
		}
	
	}
	
	
	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

	public CountryDto getNationality() {
		return nationality;
	}

	public void setNationality(CountryDto nationality) {
		this.nationality = nationality;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Boolean getPhotoCropped() {
		return photoCropped;
	}

	public void setPhotoCropped(Boolean photoCropped) {
		this.photoCropped = photoCropped;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCommunistYouthUnionJoinDate() {
		return communistYouthUnionJoinDate;
	}

	public void setCommunistYouthUnionJoinDate(Date communistYouthUnionJoinDate) {
		this.communistYouthUnionJoinDate = communistYouthUnionJoinDate;
	}

	public Date getCommunistPartyJoinDate() {
		return communistPartyJoinDate;
	}

	public void setCommunistPartyJoinDate(Date communistPartyJoinDate) {
		this.communistPartyJoinDate = communistPartyJoinDate;
	}

	public String getCarrer() {
		return carrer;
	}

	public void setCarrer(String carrer) {
		this.carrer = carrer;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
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
	
	

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdNumberIssueBy() {
		return idNumberIssueBy;
	}

	public void setIdNumberIssueBy(String idNumberIssueBy) {
		this.idNumberIssueBy = idNumberIssueBy;
	}

	public Date getIdNumberIssueDate() {
		return idNumberIssueDate;
	}

	public void setIdNumberIssueDate(Date idNumberIssueDate) {
		this.idNumberIssueDate = idNumberIssueDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public boolean isVoided() {
		return voided;
	}


	public void setVoided(boolean voided) {
		this.voided = voided;
	}
	
}
