package com.globits.emr.dto.ieh;

import com.globits.core.dto.BaseObjectDto;
import com.globits.core.dto.CountryDto;
import com.globits.core.dto.ProfessionDto;
import com.globits.emr.domain.Patient;
import com.globits.emr.domain.PersonAttribute;
import com.globits.emr.dto.AdministrativeUnitDto;
import com.globits.emr.repository.PersonAttributeRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public class IehPatientDto extends BaseObjectDto {
    private UUID id;
    private String patientCode;//Patient Code
    private String displayName;
    private Date birthDate;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private String email;
    private CountryDto country;
    private String healthInsuranceNumber;
    private String registrationCode;
    private AdministrativeUnitDto currentWard; // Xã địa chỉ hiện tại
    private AdministrativeUnitDto currentDistrict; // Huyện địa chỉ hiện tại
    private AdministrativeUnitDto currentProvince; // Tỉnh địa chỉ hiện tại
    private AdministrativeUnitDto currentRegion; // Khu vực địa chỉ hiện tại
    private AdministrativeUnitDto permanentWard; // Xã địa chỉ thường trú
    private AdministrativeUnitDto permanentDistrict; // Xã địa chỉ thường trú
    private AdministrativeUnitDto permanentProvince; // Xã địa chỉ thường trú
    private String currentDetailResidence;
    private String permanentDetailResidence;
    private ProfessionDto profession; // nghề nghiệp

    public IehPatientDto(){

    }

	public IehPatientDto(Patient entity){
		this.id = entity.getId();
		this.patientCode = entity.getCode();
		this.displayName = entity.getDisplayName();
		this.birthDate = entity.getBirthDate();
		if(entity.getBirthDate()!=null) {
			DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
		    int d1 = Integer.parseInt(formatter.format(birthDate));                            
		    int d2 = Integer.parseInt(formatter.format(new Date()));                          
		    this.age = (d2 - d1) / 10000;
		}
		this.gender = entity.getGender();
		this.phoneNumber = entity.getPhoneNumber();
		this.email = entity.getEmail();
		if(entity.getNationality() != null) {
        	this.country = new CountryDto();
        	this.country.setId(entity.getNationality().getId());
        }
		if(entity.getCarrer() != null) {
        	this.profession = new ProfessionDto();
        	this.profession.setName(entity.getCarrer());
        }
		if(entity.getCurrentResidence() != null) {
        	this.currentWard = new AdministrativeUnitDto();
        	this.currentWard.setId(entity.getCurrentResidence().getId());
        	if(entity.getCurrentResidence().getParent() != null) {
            	this.currentDistrict = new AdministrativeUnitDto();
            	this.currentDistrict.setId(entity.getCurrentResidence().getParent().getId());
            	if(entity.getCurrentResidence().getParent().getParent() != null) {
                	this.currentProvince = new AdministrativeUnitDto();
                	this.currentProvince.setId(entity.getCurrentResidence().getParent().getParent().getId());
                    	this.currentDetailResidence=
                    			entity.getCurrentResidence().getName()
                    			+","+entity.getCurrentResidence().getParent().getName()
                    			+","+entity.getCurrentResidence().getParent().getParent().getName()
                    			;
                }
            }
    		
        }
		
		if(entity.getPermanentResidence() != null) {
        	this.permanentWard = new AdministrativeUnitDto();
        	this.permanentWard.setId(entity.getPermanentResidence().getId());
        	if(entity.getPermanentResidence().getParent() != null) {
            	this.permanentDistrict = new AdministrativeUnitDto();
            	this.permanentDistrict.setId(entity.getPermanentResidence().getParent().getId());
            	if(entity.getPermanentResidence().getParent().getParent() != null) {
                	this.permanentProvince = new AdministrativeUnitDto();
                	this.permanentProvince.setId(entity.getPermanentResidence().getParent().getParent().getId());
                	this.permanentDetailResidence=
                			entity.getPermanentResidence().getName()
                			+","+entity.getPermanentResidence().getParent().getName()
                			+","+entity.getPermanentResidence().getParent().getParent().getName()
                			;
                }
            }
        }
	
//		this.healthInsuranceNumber = healthInsuranceNumber;
//		this.registrationCode = registrationCode;
//		this.currentRegion = currentRegion;

    }
	
//	public IehPatientDto(Patient entity, PersonAttribute entityPA){
//		this.id = entity.getId();
//		this.patientCode = entity.getCode();
//		this.displayName = entity.getDisplayName();
//		this.birthDate = entity.getBirthDate();
//		if(entity.getBirthDate()!=null) {
//			DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
//		    int d1 = Integer.parseInt(formatter.format(birthDate));                            
//		    int d2 = Integer.parseInt(formatter.format(new Date()));                          
//		    this.age = (d2 - d1) / 10000;
//		}
//		this.gender = entity.getGender();
//		this.phoneNumber = entity.getPhoneNumber();
//		this.email = entity.getEmail();
//		if(entity.getNationality() != null) {
//        	this.country = new CountryDto();
//        	this.country.setId(entity.getNationality().getId());
//        }
//		if(entity.getCarrer() != null) {
//        	this.profession = new ProfessionDto();
//        	this.profession.setName(entity.getCarrer());
//        }
//		if(entity.getCurrentResidence() != null) {
//        	this.currentWard = new AdministrativeUnitDto();
//        	this.currentWard.setId(entity.getCurrentResidence().getId());
//        	if(entity.getCurrentResidence().getParent() != null) {
//            	this.currentDistrict = new AdministrativeUnitDto();
//            	this.currentDistrict.setId(entity.getCurrentResidence().getParent().getId());
//            	if(entity.getCurrentResidence().getParent().getParent() != null) {
//                	this.currentProvince = new AdministrativeUnitDto();
//                	this.currentProvince.setId(entity.getCurrentResidence().getParent().getParent().getId());
//                    	this.currentDetailResidence=
//                    			entity.getCurrentResidence().getName()
//                    			+","+entity.getCurrentResidence().getParent().getName()
//                    			+","+entity.getCurrentResidence().getParent().getParent().getName()
//                    			;
//                }
//            }
//    		
//        }
//		
//		if(entity.getPermanentResidence() != null) {
//        	this.permanentWard = new AdministrativeUnitDto();
//        	this.permanentWard.setId(entity.getPermanentResidence().getId());
//        	if(entity.getPermanentResidence().getParent() != null) {
//            	this.permanentDistrict = new AdministrativeUnitDto();
//            	this.permanentDistrict.setId(entity.getPermanentResidence().getParent().getId());
//            	if(entity.getPermanentResidence().getParent().getParent() != null) {
//                	this.permanentProvince = new AdministrativeUnitDto();
//                	this.permanentProvince.setId(entity.getPermanentResidence().getParent().getParent().getId());
//                	this.permanentDetailResidence=
//                			entity.getPermanentResidence().getName()
//                			+","+entity.getPermanentResidence().getParent().getName()
//                			+","+entity.getPermanentResidence().getParent().getParent().getName()
//                			;
//                }
//            }
//        }
//	
//		if(entityPA!=null) {
//			if(entityPA.getPersonAttributeType()!=null) {
//				if(entityPA.getValue()!=null) {
//					if(entityPA.getPersonAttributeType().getCode()=="BHYT") {
//					this.setHealthInsuranceNumber(entityPA.getValue());
//					}
//					if(entityPA.getPersonAttributeType().getCode()=="registration_code") {
//						this.setRegistrationCode(entityPA.getValue());
//					}
//				}
//			}
//			}
//
////		this.currentRegion = currentRegion;
//
//    }
	
	public IehPatientDto(Patient entity, PersonAttribute entityPA, PersonAttribute entityPA1){
		this.id = entity.getId();
		this.patientCode = entity.getCode();
		this.displayName = entity.getDisplayName();
		this.birthDate = entity.getBirthDate();
		if(entity.getBirthDate()!=null) {
			DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
		    int d1 = Integer.parseInt(formatter.format(birthDate));                            
		    int d2 = Integer.parseInt(formatter.format(new Date()));                          
		    this.age = (d2 - d1) / 10000;
		}
		this.gender = entity.getGender();
		this.phoneNumber = entity.getPhoneNumber();
		this.email = entity.getEmail();
		if(entity.getNationality() != null) {
        	this.country = new CountryDto();
        	this.country.setId(entity.getNationality().getId());
        }
		if(entity.getCarrer() != null) {
        	this.profession = new ProfessionDto();
        	this.profession.setName(entity.getCarrer());
        }
		if(entity.getCurrentResidence() != null) {
        	this.currentWard = new AdministrativeUnitDto();
        	this.currentWard.setId(entity.getCurrentResidence().getId());
        	if(entity.getCurrentResidence().getParent() != null) {
            	this.currentDistrict = new AdministrativeUnitDto();
            	this.currentDistrict.setId(entity.getCurrentResidence().getParent().getId());
            	if(entity.getCurrentResidence().getParent().getParent() != null) {
                	this.currentProvince = new AdministrativeUnitDto();
                	this.currentProvince.setId(entity.getCurrentResidence().getParent().getParent().getId());
                    	this.currentDetailResidence=
                    			entity.getCurrentResidence().getName()
                    			+","+entity.getCurrentResidence().getParent().getName()
                    			+","+entity.getCurrentResidence().getParent().getParent().getName()
                    			;
                }
            }
    		
        }
		
		if(entity.getPermanentResidence() != null) {
        	this.permanentWard = new AdministrativeUnitDto();
        	this.permanentWard.setId(entity.getPermanentResidence().getId());
        	if(entity.getPermanentResidence().getParent() != null) {
            	this.permanentDistrict = new AdministrativeUnitDto();
            	this.permanentDistrict.setId(entity.getPermanentResidence().getParent().getId());
            	if(entity.getPermanentResidence().getParent().getParent() != null) {
                	this.permanentProvince = new AdministrativeUnitDto();
                	this.permanentProvince.setId(entity.getPermanentResidence().getParent().getParent().getId());
                	this.permanentDetailResidence=
                			entity.getPermanentResidence().getName()
                			+","+entity.getPermanentResidence().getParent().getName()
                			+","+entity.getPermanentResidence().getParent().getParent().getName()
                			;
                }
            }
        }
	//
		if(entityPA!=null) {
		if(entityPA.getPersonAttributeType()!=null&&entityPA.getPerson()!=null) {
			if(entityPA.getValue()!=null&&entityPA.getPerson().getId()==entity.getId()) {
				this.setHealthInsuranceNumber(entityPA.getValue());
			}
		}
		}
		if(entityPA1!=null) {
		if(entityPA1.getPersonAttributeType()!=null&&entityPA1.getPerson()!=null) {
			if(entityPA1.getValue()!=null&&entityPA1.getPerson().getId()==entity.getId()) {
				this.setRegistrationCode(entityPA1.getValue());
			}
		}
		}
//		this.currentRegion = currentRegion;

    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public AdministrativeUnitDto getCurrentWard() {
        return currentWard;
    }

    public void setCurrentWard(AdministrativeUnitDto currentWard) {
        this.currentWard = currentWard;
    }

    public AdministrativeUnitDto getCurrentDistrict() {
        return currentDistrict;
    }

    public void setCurrentDistrict(AdministrativeUnitDto currentDistrict) {
        this.currentDistrict = currentDistrict;
    }

    public AdministrativeUnitDto getCurrentProvince() {
        return currentProvince;
    }

    public void setCurrentProvince(AdministrativeUnitDto currentProvince) {
        this.currentProvince = currentProvince;
    }

    public AdministrativeUnitDto getCurrentRegion() {
        return currentRegion;
    }

    public void setCurrentRegion(AdministrativeUnitDto currentRegion) {
        this.currentRegion = currentRegion;
    }

    public AdministrativeUnitDto getPermanentWard() {
        return permanentWard;
    }

    public void setPermanentWard(AdministrativeUnitDto permanentWard) {
        this.permanentWard = permanentWard;
    }

    public AdministrativeUnitDto getPermanentDistrict() {
        return permanentDistrict;
    }

    public void setPermanentDistrict(AdministrativeUnitDto permanentDistrict) {
        this.permanentDistrict = permanentDistrict;
    }

    public AdministrativeUnitDto getPermanentProvince() {
        return permanentProvince;
    }

    public void setPermanentProvince(AdministrativeUnitDto permanentProvince) {
        this.permanentProvince = permanentProvince;
    }

    public String getCurrentDetailResidence() {
        return currentDetailResidence;
    }

    public void setCurrentDetailResidence(String currentDetailResidence) {
        this.currentDetailResidence = currentDetailResidence;
    }

    public String getPermanentDetailResidence() {
        return permanentDetailResidence;
    }

    public void setPermanentDetailResidence(String permanentDetailResidence) {
        this.permanentDetailResidence = permanentDetailResidence;
    }

    public ProfessionDto getProfession() {
        return profession;
    }

    public void setProfession(ProfessionDto profession) {
        this.profession = profession;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
}
