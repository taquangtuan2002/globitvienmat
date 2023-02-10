package com.globits.emr.service.impl;

import com.globits.core.dto.PersonDto;
import com.globits.core.utils.CommonUtils;
import com.globits.emr.domain.concept.Concept;
import com.globits.emr.domain.concept.ConceptAnswer;
import com.globits.emr.dto.EncounterTypeDto;
import com.globits.emr.dto.PersonAttributeTypeDto;
import com.globits.emr.dto.VisitTypeDto;
import com.globits.emr.dto.concept.ConceptDatatypeDto;
import com.globits.emr.dto.concept.ConceptTypeDto;
import com.globits.emr.service.*;
import com.globits.emr.utils.ConceptUtil;
import com.globits.security.domain.Role;
import com.globits.security.dto.RoleDto;
import com.globits.security.dto.UserDto;
import com.globits.security.service.RoleService;
import com.globits.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class SetupDataServiceImpl implements SetupDataService {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private ConceptDatatypeService conceptDatatypeService;
	@Autowired
	private ConceptTypeService conceptTypeService;
	@Autowired
	private ConceptService conceptService;
	@Autowired
	private VisitTypeService visitTypeService;
	@Autowired
	private EncounterTypeService encounterTypeService;

	@Autowired
	private PersonAttributeTypeService personAttributeTypeService;

	@Override
	public void setupEmrData() {
		try {
			createRoles();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		// create user
		createAdminUser();

		// create concept datatype
		createConceptDataType();
		// create concept type
		createConceptType();
		// create visit type
		createVisitType();
		// create encounttype
		createEncounterType();
		// create personAttributeType
		createPersonAttributeType();
		// create concept
		createConcept();
	}

	private void createAdminUser() {
		UserDto userDto = userService.findByUsername(com.globits.core.Constants.USER_ADMIN_USERNAME);
		if (CommonUtils.isNotNull(userDto)) {
			return;
		}

		userDto = new UserDto();
		userDto.setUsername(com.globits.core.Constants.USER_ADMIN_USERNAME);
		userDto.setPassword("admin");
		userDto.setEmail("admin@globits.net");
		userDto.setActive(true);
		userDto.setDisplayName("Admin User");

		Role role = roleService.findByName(com.globits.core.Constants.ROLE_ADMIN);

		userDto.getRoles().addAll(Arrays.asList(new RoleDto(role)));

		PersonDto person = new PersonDto();
		person.setGender("M");
		person.setFirstName("Admin");
		person.setLastName("User");
		person.setDisplayName("Admin User");

		userDto.setPerson(person);

		try {
			userService.save(userDto);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void createRoleIfNotExist(String roleName) {

		if (CommonUtils.isEmpty(roleName)) {
			return;
		}

		Role role = roleService.findByName(roleName);

		if (CommonUtils.isNotNull(role)) {
			return;
		}

		if (role == null) {
			role = new Role();
			role.setName(roleName);
		}

		try {
			roleService.save(role);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void createRoles() throws XMLStreamException {

		List<String> roleNames = new ArrayList<>();

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream in = getClass().getClassLoader().getResourceAsStream("sys-roles.xml");
		XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in, "UTF-8");

		streamReader.nextTag();
		streamReader.nextTag();

		while (streamReader.hasNext()) {
			if (streamReader.isStartElement()) {
				switch (streamReader.getLocalName()) {
				case "name": {
					roleNames.add(streamReader.getElementText());
					break;
				}
				}
			}
			streamReader.next();
		}

		streamReader.close();

		for (String roleName : roleNames) {
			createRoleIfNotExist(roleName);
		}
	}

	private void createConceptDataType() {
		// Numeric
		ConceptDatatypeDto conceptData1 = new ConceptDatatypeDto();
		conceptData1.setName("numeric");
		conceptData1.setCode("numeric");
		conceptData1.setDescription("numeric");
		if (!conceptDatatypeService.checkCode(null, conceptData1.getCode())) {
			conceptDatatypeService.saveOrUpdate(null, conceptData1);
		}
		// coded
		ConceptDatatypeDto conceptData2 = new ConceptDatatypeDto();
		conceptData2.setName("coded");
		conceptData2.setCode("coded");
		conceptData2.setDescription("coded");
		if (!conceptDatatypeService.checkCode(null, conceptData2.getCode())) {
			conceptDatatypeService.saveOrUpdate(null, conceptData2);
		}
		// Date
		ConceptDatatypeDto conceptData3 = new ConceptDatatypeDto();
		conceptData3.setName("date");
		conceptData3.setCode("date");
		conceptData3.setDescription("date");
		if (!conceptDatatypeService.checkCode(null, conceptData3.getCode())) {
			conceptDatatypeService.saveOrUpdate(null, conceptData3);
		}
		// boolean
		ConceptDatatypeDto conceptData4 = new ConceptDatatypeDto();
		conceptData4.setName("boolean");
		conceptData4.setCode("boolean");
		conceptData4.setDescription("boolean");
		if (!conceptDatatypeService.checkCode(null, conceptData4.getCode())) {
			conceptDatatypeService.saveOrUpdate(null, conceptData4);
		}
		// Text
		ConceptDatatypeDto conceptData5 = new ConceptDatatypeDto();
		conceptData4.setName("text");
		conceptData4.setCode("text");
		conceptData4.setDescription("text");
		if (!conceptDatatypeService.checkCode(null, conceptData4.getCode())) {
			conceptDatatypeService.saveOrUpdate(null, conceptData4);
		}

	}

	private void createConceptType() {
		// labtest
		ConceptTypeDto conceptType = new ConceptTypeDto();
		conceptType.setName("Labtest");
		conceptType.setCode("labtest");
		conceptType.setDescription("Labtest");
		if (!conceptTypeService.checkCode(null, conceptType.getCode())) {
			conceptTypeService.saveOrUpdate(null, conceptType);
		}
		// diagnostic
		ConceptTypeDto conceptType1 = new ConceptTypeDto();
		conceptType1.setName("diagnostic");
		conceptType1.setCode("diagnostic");
		conceptType1.setDescription("diagnostic");
		if (!conceptTypeService.checkCode(null, conceptType1.getCode())) {
			conceptTypeService.saveOrUpdate(null, conceptType1);
		}
		// Symptom
		ConceptTypeDto conceptType2 = new ConceptTypeDto();
		conceptType2.setName("symptom");
		conceptType2.setCode("symptom");
		conceptType2.setDescription("symptom");
		if (!conceptTypeService.checkCode(null, conceptType2.getCode())) {
			conceptTypeService.saveOrUpdate(null, conceptType2);
		}
		// encounter
		ConceptTypeDto conceptType3 = new ConceptTypeDto();
		conceptType3.setName("encounter");
		conceptType3.setCode("encounter");
		conceptType3.setDescription("encounter");
		if (!conceptTypeService.checkCode(null, conceptType3.getCode())) {
			conceptTypeService.saveOrUpdate(null, conceptType3);
		}
		// misc
		ConceptTypeDto conceptType4 = new ConceptTypeDto();
		conceptType4.setName("misc");
		conceptType4.setCode("misc");
		conceptType4.setDescription("misc");
		if (!conceptTypeService.checkCode(null, conceptType4.getCode())) {
			conceptTypeService.saveOrUpdate(null, conceptType4);
		}
		// drug
		ConceptTypeDto conceptType5 = new ConceptTypeDto();
		conceptType5.setName("drug");
		conceptType5.setCode("durg");
		conceptType5.setDescription("durg");
		if (!conceptTypeService.checkCode(null, conceptType5.getCode())) {
			conceptTypeService.saveOrUpdate(null, conceptType5);
		}
		// Specimen
		ConceptTypeDto conceptType6 = new ConceptTypeDto();
		conceptType6.setName("specimen");
		conceptType6.setCode("specimen");
		conceptType6.setDescription("specimen");
		if (!conceptTypeService.checkCode(null, conceptType6.getCode())) {
			conceptTypeService.saveOrUpdate(null, conceptType6);
		}
	}

	private void createVisitType() {
		// Tái Khám
		VisitTypeDto visitType = new VisitTypeDto();
		visitType.setName("re_examination");
		visitType.setCode("re_examination");
		visitType.setDescription("re_examination");
		if (!visitTypeService.checkCode(null, visitType.getCode())) {
			visitTypeService.saveOrUpdate(visitType);
		}
		// khám lần đầu
		VisitTypeDto visitType1 = new VisitTypeDto();
		visitType1.setName("first_examination");
		visitType1.setCode("first_examination");
		visitType1.setDescription("first_examination");
		if (!visitTypeService.checkCode(null, visitType1.getCode())) {
			visitTypeService.saveOrUpdate(visitType1);
		}
		// khám từ xa
		VisitTypeDto visitType2 = new VisitTypeDto();
		visitType2.setName("remote_examination");
		visitType2.setCode("remote_examination");
		visitType2.setDescription("remote_examination");
		if (!visitTypeService.checkCode(null, visitType2.getCode())) {
			visitTypeService.saveOrUpdate(visitType2);
		}
	}

	private void createPersonAttributeType() {
		// BHYT
		PersonAttributeTypeDto personAttributeTypeDto = new PersonAttributeTypeDto();
		personAttributeTypeDto.setName("bhyt");
		personAttributeTypeDto.setCode("bhyt");
		personAttributeTypeDto.setDescription("bhyt");
		if (!personAttributeTypeService.checkCode(null, personAttributeTypeDto.getCode())) {
			personAttributeTypeService.saveOrUpdate(personAttributeTypeDto);
		}
		// registration code
		PersonAttributeTypeDto personAttributeTypeDto1 = new PersonAttributeTypeDto();
		personAttributeTypeDto.setName("registration_code");
		personAttributeTypeDto.setCode("registration_code");
		personAttributeTypeDto.setDescription("registration_code");
		if (!personAttributeTypeService.checkCode(null, personAttributeTypeDto.getCode())) {
			personAttributeTypeService.saveOrUpdate(personAttributeTypeDto);
		}

	}

	private void createEncounterType() {
		// thăm khám
		EncounterTypeDto encounterTypeDto = new EncounterTypeDto();
		encounterTypeDto.setName("visit");
		encounterTypeDto.setCode("visit");
		encounterTypeDto.setDescription("visit");
		if (!encounterTypeService.checkCode(null, encounterTypeDto.getCode())) {
			encounterTypeService.saveOrUpdate(encounterTypeDto);
		}
		// Chuyển phòng khám
		EncounterTypeDto encounterTypeDto1 = new EncounterTypeDto();
		encounterTypeDto1.setName("change_clinic");
		encounterTypeDto1.setCode("change_clinic");
		encounterTypeDto1.setDescription("change_clinic");
		if (!encounterTypeService.checkCode(null, encounterTypeDto1.getCode())) {
			encounterTypeService.saveOrUpdate(encounterTypeDto1);
		}
		// Đo thi lực
		EncounterTypeDto encounterTypeDto2 = new EncounterTypeDto();
		encounterTypeDto2.setName("visual_acuity_measurement");
		encounterTypeDto2.setCode("visual_acuity_measurement");
		encounterTypeDto2.setDescription("visual_acuity_measurement");
		if (!encounterTypeService.checkCode(null, encounterTypeDto2.getCode())) {
			encounterTypeService.saveOrUpdate(encounterTypeDto2);
		}
		// Đo khúc xạ
		EncounterTypeDto encounterTypeDto3 = new EncounterTypeDto();
		encounterTypeDto3.setName("vefraction_measurement");
		encounterTypeDto3.setCode("refraction_measurement");
		encounterTypeDto3.setDescription("refraction_measurement");
		if (!encounterTypeService.checkCode(null, encounterTypeDto3.getCode())) {
			encounterTypeService.saveOrUpdate(encounterTypeDto3);
		}
		// Đo kính
		EncounterTypeDto encounterTypeDto4 = new EncounterTypeDto();
		encounterTypeDto4.setName("measuring_glasses");
		encounterTypeDto4.setCode("measuring_glasses");
		encounterTypeDto4.setDescription("measuring_glasses");
		if (!encounterTypeService.checkCode(null, encounterTypeDto4.getCode())) {
			encounterTypeService.saveOrUpdate(encounterTypeDto4);
		}
	}

	private void createConcept() {
		//trieuchung
		this.createSymtomConcept();
		// kham thuc the
		this.createPhysicalExaminationConcept();
		//Tien su benh
		this.createMedicalHistoryConcept();
		//Dau hieu sinh ton
		this.createVitalSigns_EyeSight_EyePressureConcept();
	}

	public void setConceptParent(String code, String name, Concept conceptParent) {
		conceptParent.setCode(code);
		conceptParent.setName(name);
		conceptParent.setConceptDataType(conceptDatatypeService.getConceptDatatypeByCode(ConceptUtil.CONCEPT_DATATYPE_CODED));
		conceptParent.setConceptType(conceptTypeService.getConceptTypeByCode(ConceptUtil.CONCEPT_TYPE_ENCOUNTER));
		conceptService.saveConcept(null, conceptParent);
	}
	public void setConceptQuestionTrieuChung(String code, String name, Concept conceptParent) {
		conceptParent.setCode(code);
		conceptParent.setName(name);
		conceptParent.setConceptDataType(conceptDatatypeService.getConceptDatatypeByCode(ConceptUtil.CONCEPT_DATATYPE_CODED));
		conceptParent.setConceptType(conceptTypeService.getConceptTypeByCode(ConceptUtil.CONCEPT_TYPE_ENCOUNTER));
		conceptParent.setConceptAnswers(new HashSet<ConceptAnswer>());
		conceptService.saveConcept(null, conceptParent);
	}

	public void setConceptChild(String code, String name, Concept conceptParent, Concept conceptchild) {
		conceptchild.setCode(code);
		conceptchild.setName(name);
		conceptchild.setParent(conceptParent);
		conceptchild.setConceptDataType(conceptDatatypeService.getConceptDatatypeByCode(ConceptUtil.CONCEPT_DATATYPE_TEXT));
		conceptchild.setConceptType(conceptTypeService.getConceptTypeByCode(ConceptUtil.CONCEPT_TYPE_ENCOUNTER));
		conceptService.saveConcept(null, conceptchild);
	}

	public void setConceptQuestion(String code, String name, Concept conceptParent, Concept conceptQuestion) {
		conceptQuestion.setCode(code);
		conceptQuestion.setName(name);
		conceptQuestion.setParent(conceptParent);
		conceptQuestion.setConceptDataType(
				conceptDatatypeService.getConceptDatatypeByCode(ConceptUtil.CONCEPT_DATATYPE_CODED));
		conceptQuestion.setConceptType(conceptTypeService.getConceptTypeByCode(ConceptUtil.CONCEPT_TYPE_ENCOUNTER));
		conceptQuestion.setConceptAnswers(new HashSet<>());
	}
	
	public void setNewConceptAnswer(String code, String name, Concept conceptQuestion) {
		Concept answer = new Concept();
		ConceptAnswer conceptAnswer = new ConceptAnswer();
		answer.setCode(code);
		answer.setName(name);
		answer.setConceptDataType(conceptDatatypeService.getConceptDatatypeByCode(ConceptUtil.CONCEPT_DATATYPE_TEXT));
		answer.setConceptType(conceptTypeService.getConceptTypeByCode(ConceptUtil.CONCEPT_TYPE_ENCOUNTER));
		answer = conceptService.saveConcept(null, answer);
		conceptAnswer.setAnswerConcept(answer);
		conceptAnswer.setConcept(conceptQuestion);
		conceptQuestion.getConceptAnswers().add(conceptAnswer);
	}
	private void createVitalSigns_EyeSight_EyePressureConcept() {
		// create parent dau hieu sinh ton
		Concept concept3 = new Concept();
		setConceptParent("vee-0003", "DHST-TL-NA", concept3);
		Concept vee = conceptService.getConceptByCode("vee-0003");
		// create child
		this.createThiLucVitalSigns_EyeSight_EyePressureConcept(vee);
		this.createKhucXaVitalSigns_EyeSight_EyePressureConcept(vee);
		this.createNhanApVitalSigns_EyeSight_EyePressureConcept(vee);
		this.createColorTestVitalSigns_EyeSight_EyePressureConcept(vee);
		this.createDauHieuSinhTonVitalSigns_EyeSight_EyePressureConcept(vee);
	}
	private void createDauHieuSinhTonVitalSigns_EyeSight_EyePressureConcept(Concept parent) {
		Concept concept3_05 = new Concept();
		setConceptChild("vee-000305", "Dấu hiệu sinh tồn", parent, concept3_05);
		Concept dauHieuSinhTon = conceptService.getConceptByCode("vee-000305");
		Concept concept3_1 = new Concept();
		//TEXT or NUMERIC
		setConceptChild("vee-00030501", "Cân nặng", dauHieuSinhTon, concept3_1);
		conceptService.saveConcept(null, concept3_1);
	}

	private void createColorTestVitalSigns_EyeSight_EyePressureConcept(Concept parent) {
		Concept concept3_04 = new Concept();
		setConceptChild("vee-000304", "Color Test", parent, concept3_04);
		Concept colorTest = conceptService.getConceptByCode("vee-000304");
		Concept concept3_1 = new Concept();
		setConceptChild("vee-00030401", "Color Test mắt phải", colorTest, concept3_1);
		conceptService.saveConcept(null, concept3_1);
		setConceptChild("vee-00030402", "Color Test mắt trái", colorTest, concept3_1);
		conceptService.saveConcept(null, concept3_1);
	}

	private void createNhanApVitalSigns_EyeSight_EyePressureConcept(Concept parent) {
		Concept concept3_03 = new Concept();
		setConceptChild("vee-000303", "Nhãn áp", parent, concept3_03);
		Concept nhanAp = conceptService.getConceptByCode("vee-000303");
		// create concept question
		Concept concept3_1 = new Concept();
		setConceptQuestion("vee-00030301", "Tính phí nhãn áp", nhanAp, concept3_1);
		setNewConceptAnswer("vee-0003030101", "Tính phí đo NA", concept3_1);
		setNewConceptAnswer("vee-0003030102", "Tính phí nhỏ giãn", concept3_1);
		conceptService.saveConcept(null, concept3_1);
		Concept concept3_2 = new Concept();
		setConceptChild("vee-00030302", "Nhãn áp mắt phải", nhanAp, concept3_2);
		setConceptChild("vee-00030303", "Nhãn áp mắt trái", nhanAp, concept3_2);
		conceptService.saveConcept(null, concept3_2);
		
	}

	private void createKhucXaVitalSigns_EyeSight_EyePressureConcept(Concept parent) {
		Concept concept3_02 = new Concept();
		setConceptChild("vee-000302", "Khúc xạ", parent, concept3_02);
		Concept khucXa = conceptService.getConceptByCode("vee-000302");
		// create concept question
		Concept concept3_1 = new Concept();
		setConceptChild("vee-00030201", "Loại tật khúc xạ mắt phải", khucXa, concept3_1);
		Concept concept3_2 = new Concept();
		setConceptChild("vee-00030202", "Kính điều chỉnh mắt phải", khucXa, concept3_2);
		Concept concept3_3 = new Concept();
		setConceptChild("vee-00030203", "Loại tật khúc xạ mắt mắt trái", khucXa, concept3_3);
		Concept concept3_4 = new Concept();
		setConceptChild("vee-00030204", "Kính điều chỉnh mắt trái", khucXa, concept3_4);
		// save
		conceptService.saveConcept(null, concept3_1);
		conceptService.saveConcept(null, concept3_2);
		conceptService.saveConcept(null, concept3_3);
		conceptService.saveConcept(null, concept3_4);
	}

	private void createThiLucVitalSigns_EyeSight_EyePressureConcept(Concept parent) {
		Concept concept3_01 = new Concept();
		setConceptChild("vee-000301", "Thị lực", parent, concept3_01);
		Concept thiLuc = conceptService.getConceptByCode("vee-000301");
		// create concept question
		Concept concept3_1 = new Concept();
		setConceptChild("vee-00030101", "Thị lực không kính mắt phải", thiLuc, concept3_1);
		Concept concept3_2 = new Concept();
		setConceptChild("vee-00030102", "Thị lực có kính mắt phải", thiLuc, concept3_2);
		Concept concept3_3 = new Concept();
		setConceptChild("vee-00030103", "Thị lực không kính mắt trái", thiLuc, concept3_3);
		Concept concept3_4 = new Concept();
		setConceptChild("vee-00030104", "Thị lực có kính mắt trái", thiLuc, concept3_4);
		Concept concept3_5 = new Concept();
		setConceptChild("vee-00030105", "Thị lực hai mắt có kính", thiLuc, concept3_5);
		Concept concept3_6 = new Concept();
		setConceptChild("vee-00030106", "Ghi chú", thiLuc, concept3_6);
		// save
		conceptService.saveConcept(null, concept3_1);
		conceptService.saveConcept(null, concept3_2);
		conceptService.saveConcept(null, concept3_3);
		conceptService.saveConcept(null, concept3_4);
		conceptService.saveConcept(null, concept3_5);
		conceptService.saveConcept(null, concept3_6);
	}

	private void createMedicalHistoryConcept() {
		// create parent tien su benh
		Concept concept2 = new Concept();
		setConceptParent("mh-0002", "Tiền sử bệnh", concept2);
		Concept medicalHistory = conceptService.getConceptByCode("mh-0002");
		// create child
		Concept concept2_1 = new Concept();
		setConceptChild("mh-000201", "Tiền sử cá nhân", medicalHistory, concept2_1);
		conceptService.saveConcept(null, concept2_1);
		Concept concept2_2 = new Concept();
		setConceptChild("mh-000202", "Tiền sử gia đình", medicalHistory, concept2_2);
		conceptService.saveConcept(null, concept2_2);
		Concept concept2_3 = new Concept();
		setConceptChild("mh-000203", "Thuốc đã sử dụng", medicalHistory, concept2_3);
		conceptService.saveConcept(null, concept2_3);
		Concept concept2_4 = new Concept();
		setConceptChild("mh-000204", "Dị ứng", medicalHistory, concept2_4);
		conceptService.saveConcept(null, concept2_4);
		Concept concept2_5 = new Concept();
		setConceptQuestion("mh-000205", "Yếu tố nguy cơ", medicalHistory, concept2_5);
		setNewConceptAnswer("mh-00020501", "Hút thuốc lá", concept2_5);
		setNewConceptAnswer("mh-00020502", "Đường huyết cao", concept2_5);
		setNewConceptAnswer("mh-00020503", "Cao huyết áp", concept2_5);
		setNewConceptAnswer("mh-00020504", "Nguy cơ khác", concept2_5);
		setNewConceptAnswer("mh-00020505", "Không có", concept2_5);
		conceptService.saveConcept(null, concept2_5);
	}
	private void createSymtomConcept() {
		//create parent triệu chứng
		Concept conceptP = new Concept();
		setConceptParent("st-0","Triệu chứng", conceptP);
		Concept trieuChung = conceptService.getConceptByCode("st-0");
		
		Concept conceptC2 = new Concept();
		setConceptChild("st-001","Lý do đến khám",trieuChung, conceptC2);
		
		Concept conceptC1 = new Concept();
		setConceptQuestion("st-002", "Triệu chứng đến khám", trieuChung, conceptC1);
//		Concept trieuChungDenKham = conceptService.getConceptByCode("st-002");
		
		setNewConceptAnswer("st-00201","Triệu chứng",conceptC1);
		setNewConceptAnswer("st-00202","Giảm thị lực đột ngột", conceptC1);
		setNewConceptAnswer("st-00203","Nhìn mờ vào ban đêm", conceptC1);
		setNewConceptAnswer("st-00204","Cay mắt", conceptC1);
		setNewConceptAnswer("st-00205","Giảm thị lực thoáng qua", conceptC1);
		setNewConceptAnswer("st-00206","Nhìn thấy hai hình", conceptC1);
		setNewConceptAnswer("st-00207","Chảy nước mắt", conceptC1);
		setNewConceptAnswer("st-00208","Giảm thị lực từ từ", conceptC1);
		setNewConceptAnswer("st-00209","Nhức đầu", conceptC1);
		setNewConceptAnswer("st-00210","Co giật mi", conceptC1);
		setNewConceptAnswer("st-00211","Mắt đổ nghèn", conceptC1);
		setNewConceptAnswer("st-00212","Nôn buồn nôn", conceptC1);
		setNewConceptAnswer("st-00213","Cộm xồm", conceptC1);
		setNewConceptAnswer("st-00214","Mắt nhắm không kín", conceptC1);
		setNewConceptAnswer("st-00215","Sợ ánh sáng", conceptC1);
		setNewConceptAnswer("st-00216","Dị vật/chấn thương", conceptC1);
		setNewConceptAnswer("st-00217","Mờ mắt", conceptC1);
		setNewConceptAnswer("st-00218","Sưng mi mắt", conceptC1);
		setNewConceptAnswer("st-00219","Đau nhức mắt nhẹ", conceptC1);
		setNewConceptAnswer("st-00220","Mờ mắt khi nhìn gần", conceptC1);
		setNewConceptAnswer("st-00221","Sụp mi", conceptC1);
		setNewConceptAnswer("st-00222","Đau nhức mắt nhiều", conceptC1);
		setNewConceptAnswer("st-00223","Mờ mắt khi nhìn xa", conceptC1);
		setNewConceptAnswer("st-00224","Thấy lóa sáng", conceptC1);
		setNewConceptAnswer("st-00225","Đau nhức mắt vừa", conceptC1);
		setNewConceptAnswer("st-00226","Mỏi mắt", conceptC1);
		setNewConceptAnswer("st-00227","Thấy quầng tan sắc", conceptC1);
		setNewConceptAnswer("st-00228","Đỏ mắt", conceptC1);
		setNewConceptAnswer("st-00229","Ngứa mắt", conceptC1);
		setNewConceptAnswer("st-00230","Thấy rèm che", conceptC1);
	    conceptService.saveConcept(null, conceptC1);
	}
	
	private void createPhysicalExaminationConcept() {
		// create parent kham thuc the
		Concept concept1 = new Concept();
		setConceptParent("pe-0000", "Khám thực thể", concept1);
		Concept physicalExamination = conceptService.getConceptByCode("pe-0000");
		// create child
		// Lệ bộ
		this.createLeboConcept(physicalExamination);
		// Mi mắt
		this.createMiMatConcept(physicalExamination);
		// Kết mạc
		this.createKetMacConcept(physicalExamination);
		// Giác mạc
		this.createGiacMacConcept(physicalExamination);
		// Củng mạc
		this.createCungMacConcept(physicalExamination);
		// Tiền phòng
		this.createTienPhongConcept(physicalExamination);
		// Mống mắt
		this.createMongMatConcept(physicalExamination);
		// Đồng tử
		this.createDongTuConcept(physicalExamination);
		// Thủy tinh thể
		this.createThuyTinhTheConcept(physicalExamination);
		// Thủy tinh dịch
		this.createThuyTinhDichConcept(physicalExamination);
        // Đáy mắt
        this.createDayMatConcept(physicalExamination);
		// Địa thị
		this.createDiaThiConcept(physicalExamination);
		// Võng mạc
		this.createVongMacConcept(physicalExamination);
		// Hoàng điểm
		this.createHoangDiemConcept(physicalExamination);
		//Hệ mạch
		this.createHeMachConcept(physicalExamination);
		// Chu biên
		this.createChuBienConcept(physicalExamination);
		//Nhãn cầu 
		this.createNhanCauConcept(physicalExamination);
		//Vận nhãn
		this.createVanNhanConcept(physicalExamination);
		//Hốc mắt 
		this.createHocMatConcept(physicalExamination);
		//Thể mi
		this.createTheMiConcept(physicalExamination);
		
	}
	
	public void createLeboConcept(Concept parent) {
		// create parent le bo
		Concept concept1 = new Concept();
		setConceptChild("pe-000001", "Lệ bộ", parent, concept1);
		Concept LeBo = conceptService.getConceptByCode("pe-000001");
		// create child
		this.createNhoGianDongTuLeBoConcept(LeBo);
		this.createBinhThuongLeBoConcept(LeBo);
		this.createLeQuanLeBoConcept(LeBo);
		this.createTuyenLeLeBoConcept(LeBo);
		this.createLeDaoLeBoConcept(LeBo);
		this.createGhichuLeBoConcept(LeBo);
	}

	public void createNhoGianDongTuLeBoConcept(Concept parent) {
		// create concept question
		Concept concept1_1 = new Concept();
		setConceptQuestion("pe-00000101", "Nhỏ giãn đồng tử", parent, concept1_1);
		// create concept answer
		setNewConceptAnswer("pe-0000010101", "Nhỏ giãn đồng tử mắt phải", concept1_1);
		setNewConceptAnswer("pe-0000010102", "Nhỏ giãn đồng tử mắt trái", concept1_1);
		// save
		conceptService.saveConcept(null, concept1_1);
	}

	public void createBinhThuongLeBoConcept(Concept parent) {
		// create concept question
		Concept concept1_2 = new Concept();
		setConceptQuestion("pe-00000102", "Bình thường", parent, concept1_2);
		// create concept answer
		setNewConceptAnswer("pe-0000010201", "Bình thường mắt phải", concept1_2);
		setNewConceptAnswer("pe-0000010202", "Bình thường mắt trái", concept1_2);
		// save
		conceptService.saveConcept(null, concept1_2);
	}

	public void createLeQuanLeBoConcept(Concept parent) {
		// create concept question
		Concept concept1_3 = new Concept();
		setConceptQuestion("pe-00000103", "Lệ Quản", parent, concept1_3);
		// create concept answer
		setNewConceptAnswer("pe-0000010301", "Bơm lệ quản nước thoát không tốt mắt phải", concept1_3);
		setNewConceptAnswer("pe-0000010302", "Trào lệ quản đối diện mắt phải", concept1_3);
		setNewConceptAnswer("pe-0000010303", "Trào lệ quản tại chỗ mắt phải", concept1_3);
		setNewConceptAnswer("pe-0000010304", "Bơm lệ quản nước thoát không tốt mắt trái",concept1_3);
		setNewConceptAnswer("pe-0000010305", "Trào lệ quản đối diện mắt trái", concept1_3);
		setNewConceptAnswer("pe-0000010306", "Trào lệ quản tại chỗ mắt trái", concept1_3);
		// save
		conceptService.saveConcept(null, concept1_3);
	}

	public void createTuyenLeLeBoConcept(Concept parent) {
		// create concept question
		Concept concept1_4 = new Concept();
		setConceptQuestion("pe-00000104", "Tuyến lệ", parent, concept1_4);
		// create concept answer
		setNewConceptAnswer("pe-0000010401", "Viêm tuyến lệ mắt phải", concept1_4);
		setNewConceptAnswer("pe-0000010402", "Viêm tuyến lệ mắt trái", concept1_4);
		// save
		conceptService.saveConcept(null, concept1_4);
	}

	public void createLeDaoLeBoConcept(Concept parent) {
		// create concept question
		Concept concept1_5 = new Concept();
		setConceptQuestion("pe-00000105", "Lệ Đạo", parent, concept1_5);
		// create concept answer
		setNewConceptAnswer("pe-0000010501", "Viêm lệ đạo mắt phải", concept1_5);
		setNewConceptAnswer("pe-0000010502", "Hẹp lệ đạo mắt phải", concept1_5);
		setNewConceptAnswer("pe-0000010503", "Chảy nước mắt sống phải", concept1_5);
		setNewConceptAnswer("pe-0000010504", "Viêm lệ đạo mắt trái", concept1_5);
		setNewConceptAnswer("pe-0000010505", "Hẹp lệ đạo mắt trái", concept1_5);
		setNewConceptAnswer("pe-0000010506", "Chảy nước mắt sống trái", concept1_5);
		// save
		conceptService.saveConcept(null, concept1_5);
	}

	public void createGhichuLeBoConcept(Concept parent) {
		// create Ghi chu mat trai
		Concept concept1 = new Concept();
		setConceptChild("pe-00000106", "Ghi chú mắt phải", parent, concept1);
		Concept concept2 = new Concept();
		setConceptChild("pe-00000107", "Ghi chú mắt trái", parent, concept2);
		// save
		conceptService.saveConcept(null, concept1);
		conceptService.saveConcept(null, concept2);
	}

	public void createMiMatConcept(Concept parent) {
		// create parent mi mat
		Concept concept2 = new Concept();
		setConceptChild("pe-000002", "Mi mắt", parent, concept2);
		Concept MiMat = conceptService.getConceptByCode("pe-000002");
		// create child
		this.createNhoGianDongTuMiMatConcept(MiMat);
		this.createBinhThuongMiMatConcept(MiMat);
		this.createBatThuongBoMiMiMatConcept(MiMat);
		this.createLongMiMiMatConcept(MiMat);
		this.createDangViemMiMatConcept(MiMat);
		this.createTinhTrangKhacMiMatConcept(MiMat);
		this.createDaMiMiMatConcept(MiMat);
		this.createDoSupMiMiMatConcept(MiMat);
		this.createMGDMiMatConcept(MiMat);
		this.createGhiChuMiMatConcept(MiMat);
	}

	public void createNhoGianDongTuMiMatConcept(Concept parent) {
		// create concept question
		Concept concept2_1 = new Concept();
		setConceptQuestion("pe-00000201", "Nhỏ giãn đồng tử", parent, concept2_1);
		// create concept answer
		setNewConceptAnswer("pe-0000020101", "Mắt phải", concept2_1);
		setNewConceptAnswer("pe-0000020102", "Mắt trái", concept2_1);
		// save
		conceptService.saveConcept(null, concept2_1);
	}

	public void createBinhThuongMiMatConcept(Concept parent) {
		// create concept question
		Concept concept2_2 = new Concept();
		setConceptQuestion("pe-00000202", "Bình thường", parent, concept2_2);
		// create concept answer
		setNewConceptAnswer("pe-0000020201", "Bình thường mắt phải", concept2_2);
		setNewConceptAnswer("pe-0000020202", "Bình thường mắt trái", concept2_2);
		// save
		conceptService.saveConcept(null, concept2_2);
	}

	public void createBatThuongBoMiMiMatConcept(Concept parent) {
		// create concept question
		Concept concept2_3 = new Concept();
		setConceptQuestion("pe-00000203", "Bất thường bờ mi", parent, concept2_3);
		// create concept answer
		setNewConceptAnswer("pe-0000020301", "Phù mắt phải", concept2_3);
		setNewConceptAnswer("pe-0000020302", "Sẹo da mi mắt phải", concept2_3);
		setNewConceptAnswer("pe-0000020303", "Sưng nề mắt phải", concept2_3);
		setNewConceptAnswer("pe-0000020304", "Sụp mi mắt phải", concept2_3);
		setNewConceptAnswer("pe-0000020305", "Khuyết mi mắt phải", concept2_3);
		setNewConceptAnswer("pe-0000020306", "Hở mi mắt phải", concept2_3);
		setNewConceptAnswer("pe-0000020307", "Lật mi mắt phải", concept2_3);
		setNewConceptAnswer("pe-0000020308", "Trễ mi mắt phải", concept2_3);
		setNewConceptAnswer("pe-0000020309", "Phù mắt trái", concept2_3);
		setNewConceptAnswer("pe-0000020310", "Sẹo da mi mắt trái", concept2_3);
		setNewConceptAnswer("pe-0000020311", "Sưng nề mắt trái", concept2_3);
		setNewConceptAnswer("pe-0000020312", "Sụp mi mắt trái", concept2_3);
		setNewConceptAnswer("pe-0000020313", "Khuyết mi mắt trái", concept2_3);
		setNewConceptAnswer("pe-0000020314", "Hở mi mắt trái", concept2_3);
		setNewConceptAnswer("pe-0000020315", "Lật mi mắt trái", concept2_3);
		setNewConceptAnswer("pe-0000020316", "Trễ mi mắt trái", concept2_3);
		// save
		conceptService.saveConcept(null, concept2_3);
	}

	public void createLongMiMiMatConcept(Concept parent) {
		// create concept question
		Concept concept2_4 = new Concept();
		setConceptQuestion("pe-00000204","Lông mi", parent, concept2_4);
		// create concept answer
		setNewConceptAnswer("pe-0000020401","Mi trên mắt trái", concept2_4);
		setNewConceptAnswer("pe-0000020402","Mi dưới mắt trái", concept2_4);
		setNewConceptAnswer("pe-0000020403","Quặm mắt trái", concept2_4);
		setNewConceptAnswer("pe-0000020404","Lông xiêu mắt trái", concept2_4);
		setNewConceptAnswer("pe-0000020405","Mi trên mắt phải", concept2_4);
		setNewConceptAnswer("pe-0000020406","Mi dưới mắt phải", concept2_4);
		setNewConceptAnswer("pe-0000020407","Quặm mắt phải", concept2_4);
		setNewConceptAnswer("pe-0000020408","Lông xiêu mắt phải", concept2_4);
		// save
		conceptService.saveConcept(null, concept2_4);
	}
	
	public void createDangViemMiMatConcept(Concept parent) {
		// create concept question
		Concept concept2_5 = new Concept();
		setConceptQuestion("pe-00000205","Dạng viêm", parent, concept2_5);
		// create concept answer
		setNewConceptAnswer("pe-0000020501","Chắp mắt phải", concept2_5);
		setNewConceptAnswer("pe-0000020502","Lẹo mắt phải", concept2_5);
		setNewConceptAnswer("pe-0000020503","U mi mắt phải", concept2_5);
		setNewConceptAnswer("pe-0000020504","Chắp mắt trái", concept2_5);
		setNewConceptAnswer("pe-0000020505","Lẹo mắt trái", concept2_5);
		setNewConceptAnswer("pe-0000020506","U mi mắt trái", concept2_5);
		// save
		conceptService.saveConcept(null, concept2_5);
	}
	
	public void createTinhTrangKhacMiMatConcept(Concept parent) {
		// create concept question
		Concept concept2_6 = new Concept();
		setConceptQuestion("pe-00000206","Tình trạng khác", parent, concept2_6);
		// create concept answer
		setNewConceptAnswer("pe-0000020601","Thoái hóa mắt phải", concept2_6);
		setNewConceptAnswer("pe-0000020602","Nhiễm sinh trùng mắt phải", concept2_6);
		setNewConceptAnswer("pe-0000020603","Thoái hóa mắt trái", concept2_6);
		setNewConceptAnswer("pe-0000020604","Nhiễm sinh trùng mắt trái", concept2_6);
		// save
		conceptService.saveConcept(null, concept2_6);
	}
	
	public void createDaMiMiMatConcept(Concept parent) {
		// create concept question
		Concept concept2_7 = new Concept();
		setConceptQuestion("pe-00000207","Da mi", parent, concept2_7);
		// create concept answer
		setNewConceptAnswer("pe-0000020701","Dư da mi mắt phải", concept2_7);
		setNewConceptAnswer("pe-0000020702","Nốt ruồi mắt phải", concept2_7);
		setNewConceptAnswer("pe-0000020703","Dư da mi mắt trái", concept2_7);
		setNewConceptAnswer("pe-0000020704","Nốt ruồi mắt trái", concept2_7);
		// save
		conceptService.saveConcept(null, concept2_7);
	}
	
	public void createDoSupMiMiMatConcept(Concept parent) {
		// create concept question
		Concept concept2_8 = new Concept();
		setConceptQuestion("pe-00000208","Độ sụp mi", parent, concept2_8);
		// create concept answer
		setNewConceptAnswer("pe-0000020801","Độ 1 mắt phải", concept2_8);
		setNewConceptAnswer("pe-0000020802","Độ 2 mắt phải", concept2_8);
		setNewConceptAnswer("pe-0000020803","Độ 3 mắt phải", concept2_8);
		setNewConceptAnswer("pe-0000020804","Độ 1 mắt trái", concept2_8);
		setNewConceptAnswer("pe-0000020805","Độ 2 mắt trái", concept2_8);
		setNewConceptAnswer("pe-0000020806","Độ 3 mắt trái", concept2_8);
		// save
		conceptService.saveConcept(null, concept2_8);
	}
	
	public void createMGDMiMatConcept(Concept parent) {
		// create concept question
		Concept concept2_9 = new Concept();
		setConceptQuestion("pe-00000209","MGD", parent, concept2_9);
		// create concept answer
		setNewConceptAnswer("pe-0000020901","Stage 1 mắt trái", concept2_9);
		setNewConceptAnswer("pe-0000020902","Stage 2 mắt trái", concept2_9);
		setNewConceptAnswer("pe-0000020903","Stage 3 mắt trái", concept2_9);
		setNewConceptAnswer("pe-0000020904","Stage 4 mắt trái", concept2_9);
		setNewConceptAnswer("pe-0000020905","Plus disease mắt trái", concept2_9);
		setNewConceptAnswer("pe-0000020906","Stage 1 mắt phải", concept2_9);
		setNewConceptAnswer("pe-0000020907","Stage 2 mắt phải", concept2_9);
		setNewConceptAnswer("pe-0000020908","Stage 3 mắt phải", concept2_9);
		setNewConceptAnswer("pe-0000020909","Stage 4 mắt phải", concept2_9);
		setNewConceptAnswer("pe-0000020910","Plus disease mắt phải", concept2_9);
		// save
		conceptService.saveConcept(null, concept2_9);
	}
	
	public void createGhiChuMiMatConcept(Concept parent) {
		// create
		Concept concept9_10 = new Concept();
		setConceptChild("pe-00000210","Ghi chú mắt trái", parent, concept9_10);
		Concept concept9_11 = new Concept();
		setConceptChild("pe-00000211","Ghi chú mắt phải", parent, concept9_11);
		// save
		conceptService.saveConcept(null, concept9_10);
		conceptService.saveConcept(null, concept9_11);
	}

	private void createKetMacConcept(Concept parent) {
		// create parent 
		Concept concept3 = new Concept();
		setConceptChild("pe-000003", "Kết mạc", parent, concept3);
		Concept ketMac = conceptService.getConceptByCode("pe-000003");
		// create child
		this.createNhoGianDongTuKetMacConcept(ketMac);
		this.createHongBinhthuongKetMacConcept(ketMac);
		this.createBatthuongKetMacConcept(ketMac);
		this.createBongDoKetMacConcept(ketMac);
		this.createHinhThaiCuongTuKetMacConcept(ketMac);
		this.createBatThuongCungDoKetMacConcept(ketMac);
		this.createViemKetmacKetMacConcept(ketMac);
		this.createMongKetMacConcept(ketMac);
		this.createBatThuongKhacOKetmacKetMacConcept(ketMac);
		this.createGhiChuKetMacConcept(ketMac);
	}
	
	private void createNhoGianDongTuKetMacConcept(Concept parent) {
		// create concept question
		Concept concept3_1 = new Concept();
		setConceptQuestion("pe-00000301", "Nhỏ giãn đồng tử", parent, concept3_1);
		// create concept answer
		setNewConceptAnswer("pe-0000030101", "Mắt phải", concept3_1);
		setNewConceptAnswer("pe-0000030102", "Mắt trái", concept3_1);
		// save
		conceptService.saveConcept(null, concept3_1);
	}
	
	private void createHongBinhthuongKetMacConcept(Concept parent) {
		// create concept question
		Concept concept3_2 = new Concept();
		setConceptQuestion("pe-00000302", "Hồng/Bình thường", parent, concept3_2);
		// create concept answer
		setNewConceptAnswer("pe-0000030201", "Hồng/Bình thường mắt phải", concept3_2);
		setNewConceptAnswer("pe-0000030202", "Hồng/Bình thường mắt trái", concept3_2);
		// save
		conceptService.saveConcept(null, concept3_2);
	}
	
	private void createBatthuongKetMacConcept(Concept parent) {
		// create concept question
		Concept concept3_3 = new Concept();
		setConceptQuestion("pe-00000303", "Bất thường kết mạc", parent, concept3_3);
		// create concept answer
		setNewConceptAnswer("pe-0000030301", "Cương tụ mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030302", "Phù nề mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030303", "Xuất huyết mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030304", "Sừng hóa mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030305", "Nhú mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030306", "Sẹo mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030307", "Hột mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030308", "U mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030309", "Cặn lắng mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030310", "Thoái hóa mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030311", "Cương tụ rìa mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030312", "Khô mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030313", "Cương tụ mắt trái", concept3_3);
		setNewConceptAnswer("pe-0000030314", "Phù nề mắt trái", concept3_3);
		setNewConceptAnswer("pe-0000030315", "Xuất huyết mắt trái", concept3_3);
		setNewConceptAnswer("pe-0000030316", "Sừng hóa mắt trái", concept3_3);
		setNewConceptAnswer("pe-0000030317", "Nhú mắt trái", concept3_3);
		setNewConceptAnswer("pe-0000030318", "Sẹo mắt trái", concept3_3);
		setNewConceptAnswer("pe-0000030319", "Hột mắt trái", concept3_3);
		setNewConceptAnswer("pe-0000030320", "U mắt phải", concept3_3);
		setNewConceptAnswer("pe-0000030321", "Cặn lắng mắt trái", concept3_3);
		setNewConceptAnswer("pe-0000030322", "Thoái hóa mắt trái", concept3_3);
		setNewConceptAnswer("pe-0000030323", "Cương tụ rìa mắt trái", concept3_3);
		setNewConceptAnswer("pe-0000030324", "Khô mắt trái", concept3_3);
		// save
		conceptService.saveConcept(null, concept3_3);
	}
	
	private void createBongDoKetMacConcept(Concept parent) {
		Concept concept3_4 = new Concept();
		setConceptQuestion("pe-00000304", "Bọng dò", parent, concept3_4);
		// create concept answer
		setNewConceptAnswer("pe-0000030401", "Dẹt mắt phải", concept3_4);
		setNewConceptAnswer("pe-0000030402", "Xơ mắt phải", concept3_4);
		setNewConceptAnswer("pe-0000030403", "Mỏng mắt phải", concept3_4);
		setNewConceptAnswer("pe-0000030404", "Quá phát mắt phải", concept3_4);
		setNewConceptAnswer("pe-0000030405", "Dẹt mắt trái", concept3_4);
		setNewConceptAnswer("pe-0000030406", "Xơ mắt trái", concept3_4);
		setNewConceptAnswer("pe-0000030407", "Mỏng mắt trái", concept3_4);
		setNewConceptAnswer("pe-0000030408", "Quá phát mắt trái", concept3_4);
		// save
		conceptService.saveConcept(null, concept3_4);
	}
	
	private void createHinhThaiCuongTuKetMacConcept(Concept parent) {
		Concept concept3_5 = new Concept();
		setConceptQuestion("pe-00000305", "Hình thái cương tụ", parent, concept3_5);
		// create concept answer
		setNewConceptAnswer("pe-0000030501", "Tỏa lan mắt phải", concept3_5);
		setNewConceptAnswer("pe-0000030502", "Ở rìa mắt phải", concept3_5);
		setNewConceptAnswer("pe-0000030503", "Ở kết mạc nhãn cầu mắt phải", concept3_5);
		setNewConceptAnswer("pe-0000030504", "Tỏa lan mắt trái", concept3_5);
		setNewConceptAnswer("pe-0000030505", "Ở rìa mắt trái", concept3_5);
		setNewConceptAnswer("pe-0000030506", "Ở kết mạc nhãn cầu mắt trái", concept3_5);
		// save
		conceptService.saveConcept(null, concept3_5);
	}

	private void createBatThuongCungDoKetMacConcept(Concept parent) {
		Concept concept3_6 = new Concept();
		setConceptQuestion("pe-00000306", "Bất thường cùng đồ", parent, concept3_6);
		// create concept answer
		setNewConceptAnswer("pe-0000030601", "Cạn mắt phải", concept3_6);
		setNewConceptAnswer("pe-0000030602", "Dính mắt phải", concept3_6);
		setNewConceptAnswer("pe-0000030603", "Cạn mắt trái", concept3_6);
		setNewConceptAnswer("pe-0000030604", "Dính mắt trái", concept3_6);
		// save
		conceptService.saveConcept(null, concept3_6);
	}
	
	private void createViemKetmacKetMacConcept(Concept parent) {
		Concept concept3_7 = new Concept();
		setConceptQuestion("pe-00000307", "Bất thường cùng đồ", parent, concept3_7);
		// create concept answer
		setNewConceptAnswer("pe-0000030701", "Tiết tố mủ mắt phải", concept3_7);
		setNewConceptAnswer("pe-0000030702", "Tiết tố trong mắt phải", concept3_7);
		setNewConceptAnswer("pe-0000030703", "Giả mạc mắt phải", concept3_7);
		setNewConceptAnswer("pe-0000030704", "Cấu dính mắt phải", concept3_7);
		setNewConceptAnswer("pe-0000030705", "Tiết tố mủ mắt trái", concept3_7);
		setNewConceptAnswer("pe-0000030706", "Tiết tố trong mắt trái", concept3_7);
		setNewConceptAnswer("pe-0000030707", "Giả mạc mắt trái", concept3_7);
		setNewConceptAnswer("pe-0000030708", "Cấu dính mắt trái", concept3_7);
		// save
		conceptService.saveConcept(null, concept3_7);
	}
	
	private void createMongKetMacConcept(Concept parent) {
		Concept concept3_8 = new Concept();
		setConceptQuestion("pe-00000308", "Mộng", parent, concept3_8);
		// create concept answer
		setNewConceptAnswer("pe-0000030801", "Mộng thịt mắt phải", concept3_8);
		setNewConceptAnswer("pe-0000030802", "Mộng mỡ mắt phải", concept3_8);
		setNewConceptAnswer("pe-0000030803", "Góc trong mắt phải", concept3_8);
		setNewConceptAnswer("pe-0000030804", "Góc ngoài mắt phải", concept3_8);
		setNewConceptAnswer("pe-0000030805", "Độ 1 mắt phải", concept3_8);
		setNewConceptAnswer("pe-0000030806", "Độ 2 mắt phải", concept3_8);
		setNewConceptAnswer("pe-0000030807", "Độ 3 mắt phải", concept3_8);
		setNewConceptAnswer("pe-0000030808", "Độ 4 mắt phải", concept3_8);
		setNewConceptAnswer("pe-0000030809", "Mộng thịt mắt trái", concept3_8);
		setNewConceptAnswer("pe-0000030810", "Mộng mỡ mắt trái", concept3_8);
		setNewConceptAnswer("pe-0000030811", "Góc trong mắt trái", concept3_8);
		setNewConceptAnswer("pe-0000030812", "Góc ngoài mắt trái", concept3_8);
		setNewConceptAnswer("pe-0000030813", "Độ 1 mắt trái", concept3_8);
		setNewConceptAnswer("pe-0000030814", "Độ 2 mắt trái", concept3_8);
		setNewConceptAnswer("pe-0000030815", "Độ 3 mắt trái", concept3_8);
		setNewConceptAnswer("pe-0000030816", "Độ 4 mắt trái", concept3_8);
		// save
		conceptService.saveConcept(null, concept3_8);
	} 
	
	private void createBatThuongKhacOKetmacKetMacConcept(Concept parent) {
		Concept concept3_9 = new Concept();
		setConceptQuestion("pe-00000309", "Bất thường khác ở kết mạc", parent, concept3_9);
		// create concept answer
		setNewConceptAnswer("pe-0000030901", "Nhiễm giun chỉ mắt phải", concept3_9);
		setNewConceptAnswer("pe-0000030902", "Nhiễm trùng mắt phải", concept3_9);
		setNewConceptAnswer("pe-0000030903", "Nhuộm màu kết mạc mắt phải", concept3_9);
		setNewConceptAnswer("pe-0000030904", "Nhiễm giun chỉ mắt trái", concept3_9);
		setNewConceptAnswer("pe-0000030905", "Nhiễm trùng mắt trái", concept3_9);
		setNewConceptAnswer("pe-0000030906", "Nhuộm màu kết mạc mắt trái", concept3_9);
		// save
		conceptService.saveConcept(null, concept3_9);
	}
	
	private void createGhiChuKetMacConcept(Concept parent) {
		Concept concept3_10 = new Concept();
		setConceptChild("pe-00000310", "Ghi chú mắt phải", parent, concept3_10);
		Concept concept3_11 = new Concept();
		setConceptChild("pe-00000311", "Ghi chú mắt trái", parent, concept3_11);
		// save
		conceptService.saveConcept(null, concept3_10);
		conceptService.saveConcept(null, concept3_11);
	}
	
	public void createGiacMacConcept(Concept parent) {
		Concept concept4 = new Concept();
		setConceptChild("pe-000004", "Giác mạc", parent, concept4);
		Concept giacMac = conceptService.getConceptByCode("pe-000004");
		// Create child
		this.createNhoGianDongTuGiacMac(giacMac);
		this.createTrongGiacMac(giacMac);
		this.createBatThuongKichThuocVaHinhDang(giacMac);
		this.createMatTruocGiacMac(giacMac);
		this.createBatThuongCamGiacGiacMac(giacMac);
		this.createTonThuongGiacMac(giacMac);
		this.createTanMachGiacMac(giacMac);
		this.createMucDoTanMachGiacMac(giacMac);
		this.createVungRiaGiacMac(giacMac);
		this.createBoTonThuongGiacMac(giacMac);
		this.createBatThuongBieuMoGiacMac(giacMac);
		this.createMucDoThamLauGiacMac(giacMac);
		this.createViTriThamLauGiacMac(giacMac);
		this.createGhiChuGiacMac(giacMac);
	}

	public void createNhoGianDongTuGiacMac(Concept parent) {
		Concept concept4_1 = new Concept();
		setConceptQuestion("pe-00000401", "Nhỏ giãn đồng tử", parent, concept4_1);
		setNewConceptAnswer("pe-0000040101", "Mắt phải", concept4_1);
		setNewConceptAnswer("pe-0000040102", "Mắt trái", concept4_1);
		conceptService.saveConcept(null, concept4_1);
	}

	public void createTrongGiacMac(Concept parent) {
		Concept concept4_2 = new Concept();
		setConceptQuestion("pe-00000402", "Trong", parent, concept4_2);
		setNewConceptAnswer("pe-0000040201", "Trong mắt phải", concept4_2);
		setNewConceptAnswer("pe-0000040202", "Trong mắt trái", concept4_2);
		conceptService.saveConcept(null, concept4_2);
	}

	public void createBatThuongKichThuocVaHinhDang(Concept parent) {
		Concept concept4_3 = new Concept();
		setConceptQuestion("pe-00000403", "Bất thường kích thước và hình dáng", parent, concept4_3);
		setNewConceptAnswer("pe-0000040301", "To mắt phải", concept4_3);
		setNewConceptAnswer("pe-0000040302", "Nhỏ mắt phải", concept4_3);
		setNewConceptAnswer("pe-0000040303", "Hình nón mắt phải", concept4_3);
		setNewConceptAnswer("pe-0000040304", "To mắt trái", concept4_3);
		setNewConceptAnswer("pe-0000040305", "Nhỏ mắt trái", concept4_3);
		setNewConceptAnswer("pe-0000040306", "Hình nón mắt trái", concept4_3);
		conceptService.saveConcept(null, concept4_3);
	}

	public void createMatTruocGiacMac(Concept parent) {
		Concept concept4_4 = new Concept();
		setConceptQuestion("pe-00000404", "Mặt trước giác mạc", parent, concept4_4);
		setNewConceptAnswer("pe-0000040401", "Viêm mắt phải", concept4_4);
		setNewConceptAnswer("pe-0000040402", "Loét mắt phải", concept4_4);
		setNewConceptAnswer("pe-0000040403", "Viêm kết-giác-mạc mắt phải", concept4_4);
		setNewConceptAnswer("pe-0000040404", "Sẹo mắt phải", concept4_4);
		setNewConceptAnswer("pe-0000040405", "Đục mắt phải", concept4_4);
		setNewConceptAnswer("pe-0000040406", "SPK mắt phải", concept4_4);
		setNewConceptAnswer("pe-0000040407", "Viêm GM chấm nông mắt phải", concept4_4);
		setNewConceptAnswer("pe-0000040408", "Pigment KPs mắt phải", concept4_4);
		setNewConceptAnswer("pe-0000040409", "Viêm mắt trái", concept4_4);
		setNewConceptAnswer("pe-0000040410", "Loét mắt trái", concept4_4);
		setNewConceptAnswer("pe-0000040411", "Viêm kết-giác-mạc mắt trái", concept4_4);
		setNewConceptAnswer("pe-0000040412", "Sẹo mắt trái", concept4_4);
		setNewConceptAnswer("pe-0000040413", "Đục mắt trái", concept4_4);
		setNewConceptAnswer("pe-0000040414", "SPK mắt trái", concept4_4);
		setNewConceptAnswer("pe-0000040415", "Viêm GM chấm nông mắt trái", concept4_4);
		setNewConceptAnswer("pe-0000040416", "Pigment KPs mắt trái", concept4_4);
		conceptService.saveConcept(null, concept4_4);
	}

	public void createBatThuongCamGiacGiacMac(Concept parent) {
		Concept concept4_5 = new Concept();
		setConceptQuestion("pe-00000405", "Bất thường cảm giác giác mạc", parent, concept4_5);
		setNewConceptAnswer("pe-0000040501", "Mất mắt phải", concept4_5);
		setNewConceptAnswer("pe-0000040502", "Mất giảm mắt phải", concept4_5);
		setNewConceptAnswer("pe-0000040503", "Mất mắt trái", concept4_5);
		setNewConceptAnswer("pe-0000040504", "Mất giảm mắt trái", concept4_5);
		conceptService.saveConcept(null, concept4_5);
	}

	public void createTonThuongGiacMac(Concept parent) {
		Concept concept4_6 = new Concept();
		setConceptQuestion("pe-00000406", "Tổn thương giác mạc", parent, concept4_6);
		setNewConceptAnswer("pe-0000040601", "Dọa thủng mắt phải", concept4_6);
		setNewConceptAnswer("pe-0000040602", "Thủng mắt phải", concept4_6);
		setNewConceptAnswer("pe-0000040603", "Kẹt mống mắt mắt phải", concept4_6);
		setNewConceptAnswer("pe-0000040604", "Sẹo giác mạc mắt phải", concept4_6);
		setNewConceptAnswer("pe-0000040605", "Loạn dưỡng mắt phải", concept4_6);
		setNewConceptAnswer("pe-0000040606", "Dọa thủng mắt trái", concept4_6);
		setNewConceptAnswer("pe-0000040607", "Thủng mắt trái", concept4_6);
		setNewConceptAnswer("pe-0000040608", "Kẹt mống mắt mắt trái", concept4_6);
		setNewConceptAnswer("pe-0000040609", "Sẹo giác mạc mắt trái", concept4_6);
		setNewConceptAnswer("pe-0000040610", "Loạn dưỡng mắt trái", concept4_6);
		conceptService.saveConcept(null, concept4_6);
	}

	public void createTanMachGiacMac(Concept parent) {
		Concept concept4_7 = new Concept();
		setConceptQuestion("pe-00000407", "Tân mạch giác mạc", parent, concept4_7);
		setNewConceptAnswer("pe-0000040701", "Nông, hướng tâm mắt phải", concept4_7);
		setNewConceptAnswer("pe-0000040702", "Ly tâm mắt phải", concept4_7);
		setNewConceptAnswer("pe-0000040703", "Sâu mắt phải", concept4_7);
		setNewConceptAnswer("pe-0000040704", "Nông, hướng tâm mắt trái", concept4_7);
		setNewConceptAnswer("pe-0000040705", "Ly tâm mắt trái", concept4_7);
		setNewConceptAnswer("pe-0000040706", "Sâu mắt trái", concept4_7);
		conceptService.saveConcept(null, concept4_7);
	}

	public void createMucDoTanMachGiacMac(Concept parent) {
		Concept concept4_8 = new Concept();
		setConceptQuestion("pe-00000408", "Mức độ tân mạch", parent, concept4_8);
		setNewConceptAnswer("pe-0000040801", "1/3 chu vi mắt phải", concept4_8);
		setNewConceptAnswer("pe-0000040802", "1/3 đến 2/3 chu vi mắt phải", concept4_8);
		setNewConceptAnswer("pe-0000040803", "1/3 chu vi mắt phải", concept4_8);
		setNewConceptAnswer("pe-0000040804", "1/3 chu vi mắt trái", concept4_8);
		setNewConceptAnswer("pe-0000040805", "1/3 đến 2/3 chu vi mắt trái", concept4_8);
		setNewConceptAnswer("pe-0000040806", "Hơn 2/3 chu vi mắt trái", concept4_8);
		conceptService.saveConcept(null, concept4_8);
	}

	public void createVungRiaGiacMac(Concept parent) {
		Concept concept4_9 = new Concept();
		setConceptQuestion("pe-00000409", "Vùng rìa tâm mạch", parent, concept4_9);
		setNewConceptAnswer("pe-0000040901", "Thoái hóa giá mắt phải", concept4_9);
		setNewConceptAnswer("pe-0000040902", "Lắng đọng Can-xi mắt phải", concept4_9);
		setNewConceptAnswer("pe-0000040903", "Thoái hóa giá mắt trái", concept4_9);
		setNewConceptAnswer("pe-0000040904", "Lắng đọng Can-xi mắt trái", concept4_9);
		conceptService.saveConcept(null, concept4_9);
	}

	public void createBoTonThuongGiacMac(Concept parent) {
		Concept concept4_10 = new Concept();
		setConceptQuestion("pe-00000410", "Bờ tổn thương", parent, concept4_10);
		setNewConceptAnswer("pe-0000041001", "Nham nhở mắt phải", concept4_10);
		setNewConceptAnswer("pe-0000041002", "Trơ nhẵn mắt phải", concept4_10);
		setNewConceptAnswer("pe-0000041003", "Đào rãnh mắt phải", concept4_10);
		setNewConceptAnswer("pe-0000041004", "Nham nhở mắt trái", concept4_10);
		setNewConceptAnswer("pe-0000041005", "Trơ nhẵn mắt trái", concept4_10);
		setNewConceptAnswer("pe-0000041006", "Đào rãnh mắt trái", concept4_10);
		conceptService.saveConcept(null, concept4_10);
	}

	public void createBatThuongBieuMoGiacMac(Concept parent) {
		Concept concept4_11 = new Concept();
		setConceptQuestion("pe-00000411", "Bất thường biểu mô", parent, concept4_11);
		setNewConceptAnswer("pe-0000041101", "Tổn thương dạng chấm mắt phải", concept4_11);
		setNewConceptAnswer("pe-0000041102", "Thoái hóa dải băng mắt phải", concept4_11);
		setNewConceptAnswer("pe-0000041103", "Lắng đọng thuốc mắt phải", concept4_11);
		setNewConceptAnswer("pe-0000041104", "Haze mắt phải", concept4_11);
		setNewConceptAnswer("pe-0000041105", "Tổn thương dạng chấm mắt trái", concept4_11);
		setNewConceptAnswer("pe-0000041106", "Thoái hóa dải băng mắt trái", concept4_11);
		setNewConceptAnswer("pe-0000041107", "Lắng đọng thuốc mắt trái", concept4_11);
		setNewConceptAnswer("pe-0000041108", "Haze mắt trái", concept4_11);
		conceptService.saveConcept(null, concept4_11);
	}

	public void createMucDoThamLauGiacMac(Concept parent) {
		Concept concept4_12 = new Concept();
		setConceptQuestion("pe-00000412", "Mức độ thẩm lậu", parent, concept4_12);
		setNewConceptAnswer("pe-0000041201", "Nông mắt phải", concept4_12);
		setNewConceptAnswer("pe-0000041202", "Sâu mắt phải", concept4_12);
		setNewConceptAnswer("pe-0000041203", "Rất sâu mắt phải", concept4_12);
		setNewConceptAnswer("pe-0000041204", "Nông mắt trái", concept4_12);
		setNewConceptAnswer("pe-0000041205", "Sâu mắt trái", concept4_12);
		setNewConceptAnswer("pe-0000041206", "Rất sâu mắt trái", concept4_12);
		conceptService.saveConcept(null, concept4_12);
	}

	public void createViTriThamLauGiacMac(Concept parent) {
		Concept concept4_13 = new Concept();
		setConceptQuestion("pe-00000413", "Vị trí thẩm lậu", parent, concept4_13);
		setNewConceptAnswer("pe-0000041301", "Khu trú mắt phải", concept4_13);
		setNewConceptAnswer("pe-0000041302", "Lan tỏa mắt phải", concept4_13);
		setNewConceptAnswer("pe-0000041303", "Nhiều ổ tổn thương mắt phải", concept4_13);
		setNewConceptAnswer("pe-0000041304", "Khu trú mắt trái", concept4_13);
		setNewConceptAnswer("pe-0000041305", "Lan tỏa mắt trái", concept4_13);
		setNewConceptAnswer("pe-0000041306", "Nhiều ổ tổn thương mắt trái", concept4_13);
		conceptService.saveConcept(null, concept4_13);
	}

	public void createGhiChuGiacMac(Concept parent) {
		Concept concept4_14 = new Concept();
		setConceptChild("pe-00000414", "Ghi chú mắt phải", parent, concept4_14);
		Concept concept4_15 = new Concept();
		setConceptChild("pe-00000415", "Ghi chú mắt phải", parent, concept4_15);
		conceptService.saveConcept(null, concept4_14);
		conceptService.saveConcept(null, concept4_15);
	}

	
	private void createCungMacConcept(Concept parent) {
		// create parent cung mac
		Concept concept5 = new Concept();
		setConceptChild("pe-000005", "Củng mạc", parent, concept5);
		Concept cungMac = conceptService.getConceptByCode("pe-000005");
		// create chill
		this.createNhoGianDongTuCungMacConcept(cungMac);
		this.createBinhThuongCungMacConcept(cungMac);
		this.createDangViemCungmacCungMacConcept(cungMac);
		this.createMucDoViemCungMacConcept(cungMac);
		this.createViTriViemCungMacConcept(cungMac);
		this.createBatThuongCungmacCungMacConcept(cungMac);
		this.createDaPhauThuatCungMacConcept(cungMac);
		this.createGhiChuCungMacConcept(cungMac);
	}

	private void createNhoGianDongTuCungMacConcept(Concept parent) {
		// create concept question
		Concept concept5_1 = new Concept();
		setConceptQuestion("pe-00000501", "Nhỏ giãn đồng tử", parent, concept5_1);
		// create concept answer
		setNewConceptAnswer("pe-0000050101", "Mắt phải", concept5_1);
		setNewConceptAnswer("pe-0000050102", "Mắt trái", concept5_1);
		// save
		conceptService.saveConcept(null, concept5_1);
	}
	private void createBinhThuongCungMacConcept(Concept parent) {
		// create concept question
		Concept concept5_2 = new Concept();
		setConceptQuestion("pe-00000502", "Bình thường", parent, concept5_2);
		// create concept answer
		setNewConceptAnswer("pe-0000050201", "Bình thường mắt phải", concept5_2);
		setNewConceptAnswer("pe-0000050202", "Bình thường mắt trái", concept5_2);
		// save
		conceptService.saveConcept(null, concept5_2);
	}
	private void createDangViemCungmacCungMacConcept(Concept parent) {
		// create concept question
		Concept concept5_3 = new Concept();
		setConceptQuestion("pe-00000503", "Dạng viêm củng mạc", parent, concept5_3);
		// create concept answer
		setNewConceptAnswer("pe-0000050301", "Nốt mắt phải",concept5_3);
		setNewConceptAnswer("pe-0000050302", "Lan tỏa mắt phải", concept5_3);
		setNewConceptAnswer("pe-0000050303", "Áp-xe mắt phải", concept5_3);
		setNewConceptAnswer("pe-0000050304", "Hoại tử mắt phải", concept5_3);
		
		setNewConceptAnswer("pe-0000050305", "Nốt mắt trái", concept5_3);
		setNewConceptAnswer("pe-0000050306", "Lan tỏa mắt trái", concept5_3);
		setNewConceptAnswer("pe-0000050307", "Áp-xe mắt trái", concept5_3);
		setNewConceptAnswer("pe-0000050308", "Hoại tử mắt trái", concept5_3);
		// save
		conceptService.saveConcept(null, concept5_3);
		
	}
	
	private void createMucDoViemCungMacConcept(Concept parent) {
		// create concept question
		Concept concept5_4 = new Concept();
		setConceptQuestion("pe-00000504", "Mức độ viêm", parent, concept5_4);
		// create concept answer
		setNewConceptAnswer("pe-0000050401", "Nông mắt phải", concept5_4);
		setNewConceptAnswer("pe-0000050402", "Sâu mắt phải", concept5_4);
		// Mat trai
		setNewConceptAnswer("pe-0000050403", "Nông mắt trái", concept5_4);
		setNewConceptAnswer("pe-0000050404", "Sâu mắt trái", concept5_4);
		// save
		conceptService.saveConcept(null, concept5_4);
	}
	
	private void createViTriViemCungMacConcept(Concept parent) {
		// create concept question
		Concept concept5_5 = new Concept();
		setConceptQuestion("pe-00000505", "Vị trí viêm", parent, concept5_5);
		// create concept answer
		setNewConceptAnswer("pe-0000050501", "Viêm thượng củng mạc mắt phải", concept5_5);
		// Mat trai
		setNewConceptAnswer("pe-0000050502", "Viêm thượng củng mạc mắt trái", concept5_5);
		// save
		conceptService.saveConcept(null, concept5_5);
	}

	
	private void createBatThuongCungmacCungMacConcept(Concept parent) {
		// create concept question
		Concept concept5_6 = new Concept();
		setConceptQuestion("pe-00000506", "Bất thường củng mạc", parent, concept5_6);
		// create concept answer
		setNewConceptAnswer("pe-0000050601", "Giãn lồi mắt phải", concept5_6);
		setNewConceptAnswer("pe-0000050602", "Tiêu mỏng mắt phải", concept5_6);
		setNewConceptAnswer("pe-0000050603", "Sẹo mổ mắt phải", concept5_6);
		// Mat trai
		setNewConceptAnswer("pe-0000050604", "Giãn lồi mắt trái", concept5_6);
		setNewConceptAnswer("pe-0000050605", "Tiêu mỏng mắt trái", concept5_6);
		setNewConceptAnswer("pe-0000050606", "Sẹo mổ mắt trái", concept5_6);
		// save
		conceptService.saveConcept(null, concept5_6);
	}

	private void createDaPhauThuatCungMacConcept(Concept parent) {
		// create concept question
		Concept concept5_7 = new Concept();
		setConceptQuestion("pe-00000507", "Đã phẫu thuật", parent, concept5_7);
		// create concept answer
		setNewConceptAnswer("pe-0000050701", "Cắt bè CGM mắt phải", concept5_7);
		setNewConceptAnswer("pe-0000050702", "Cắt bè + CCH mắt phải", concept5_7);
		setNewConceptAnswer("pe-0000050703", "Cắt CMS mắt phải", concept5_7);
		setNewConceptAnswer("pe-0000050704", "Cắt CMS + CCH mắt phải", concept5_7);
		setNewConceptAnswer("pe-0000050705", "Đặt van dẫn lưu mắt phải", concept5_7);
		setNewConceptAnswer("pe-0000050706", "Quang đông thể mi mắt phải", concept5_7);
		setNewConceptAnswer("pe-0000050707", "Lạnh đông thể mi mắt phải", concept5_7);
		setNewConceptAnswer("pe-0000050708", "Sửa sẹo bọng mắt phải", concept5_7);
		setNewConceptAnswer("pe-0000050709", "Kẹt củng mạc mắt phải", concept5_7);
		setNewConceptAnswer("pe-0000050710", "Laser tạo hình bè mắt phải", concept5_7);
		// Mat trai
		setNewConceptAnswer("pe-0000050711", "Cắt bè CGM mắt trái", concept5_7);
		setNewConceptAnswer("pe-0000050712", "Cắt bè + CCH mắt trái", concept5_7);
		setNewConceptAnswer("pe-0000050713", "Cắt CMS mắt trái", concept5_7);
		setNewConceptAnswer("pe-0000050714", "Cắt CMS + CCH mắt trái", concept5_7);
		setNewConceptAnswer("pe-0000050715", "Đặt van dẫn lưu mắt trái", concept5_7);
		setNewConceptAnswer("pe-0000050716", "Quang đông thể mi mắt trái", concept5_7);
		setNewConceptAnswer("pe-0000050717", "Lạnh đông thể mi mắt trái", concept5_7);
		setNewConceptAnswer("pe-0000050718", "Sửa sẹo bọng mắt trái", concept5_7);
		setNewConceptAnswer("pe-0000050719", "Kẹt củng mạc mắt trái", concept5_7);
		setNewConceptAnswer("pe-0000050720", "Laser tạo hình bè mắt trái", concept5_7);
		// save
		conceptService.saveConcept(null, concept5_7);
	}

	private void createGhiChuCungMacConcept(Concept parent) {
		// create concept question
		Concept concept5_8 = new Concept();
		setConceptChild("pe-00000508", "Ghi chú mắt phải", parent, concept5_8);
		Concept concept5_9 = new Concept();
		setConceptChild("pe-00000509", "Ghi chú mắt trái", parent, concept5_9);
		// save
		conceptService.saveConcept(null, concept5_8);
		conceptService.saveConcept(null, concept5_9);
	}
	//concept6 start
	public void createTienPhongConcept(Concept parent) {
		Concept concept6 = new Concept();
		setConceptChild("pe-000006", "Tiền phòng", parent, concept6);
		Concept tienPhong = conceptService.getConceptByCode("pe-000006");
		//Create child
		this.createNhoGianDongTuTienPhong(tienPhong);
		this.createACTienPhong(tienPhong);
		this.createGonioscopyTienPhong(tienPhong);
		this.createACCellienPhong(tienPhong);
		this.createSoiGocTienPhong(tienPhong);
		this.createGhiChuTienPhong(tienPhong);
	}
	
	public void createNhoGianDongTuTienPhong(Concept parent) {
		Concept concept6_1 = new Concept();
		setConceptQuestion("pe-00000601", "Nhỏ giãn đồng tử", parent, concept6_1);
		setNewConceptAnswer("pe-0000060101", "Mắt phải", concept6_1);
		setNewConceptAnswer("pe-0000060102", "Mắt trái", concept6_1);
		conceptService.saveConcept(null, concept6_1);
	}

	public void createACTienPhong(Concept parent) {
		Concept concept6_2 = new Concept();
		setConceptQuestion("pe-00000602", "AC", parent, concept6_2);
		setNewConceptAnswer("pe-0000060201", "Deep & clear mắt phải", concept6_2);
		setNewConceptAnswer("pe-0000060202", "Cell/flare mắt phải", concept6_2);
		setNewConceptAnswer("pe-0000060203", "Shallow măt phải", concept6_2);
		setNewConceptAnswer("pe-0000060204", "Fibrin(+) mắt phải", concept6_2);
		setNewConceptAnswer("pe-0000060205", "Deep & clear mắt trái", concept6_2);
		setNewConceptAnswer("pe-0000060206", "Cell/flare mắt trái", concept6_2);
		setNewConceptAnswer("pe-0000060207", "Shallow măt trái", concept6_2);
		setNewConceptAnswer("pe-0000060208", "Fibrin(+) mắt trái", concept6_2);
		conceptService.saveConcept(null, concept6_2);
	}

	public void createGonioscopyTienPhong(Concept parent) {
		Concept concept6_3 = new Concept();
		setConceptQuestion("pe-00000603", "Gonioscopy", parent, concept6_3);
		setNewConceptAnswer("pe-0000060301", "I mắt phải", concept6_3);
		setNewConceptAnswer("pe-0000060302", "II mắt phải", concept6_3);
		setNewConceptAnswer("pe-0000060303", "III mắt phải", concept6_3);
		setNewConceptAnswer("pe-0000060304", "IV mắt phải", concept6_3);
		setNewConceptAnswer("pe-0000060305", "PAS mắt phải", concept6_3);
		setNewConceptAnswer("pe-0000060306", "I mắt trái", concept6_3);
		setNewConceptAnswer("pe-0000060307", "II mắt trái", concept6_3);
		setNewConceptAnswer("pe-0000060308", "III mắt trái", concept6_3);
		setNewConceptAnswer("pe-0000060309", "IV mắt trái", concept6_3);
		setNewConceptAnswer("pe-0000060310", "PAS mắt trái", concept6_3);
		conceptService.saveConcept(null, concept6_3);
	}

	public void createACCellienPhong(Concept parent) {
		Concept concept6_4 = new Concept();
		setConceptQuestion("pe-00000604", "AC Cell", parent, concept6_4);
		setNewConceptAnswer("pe-0000060401", "0.5+ mắt phải", concept6_4);
		setNewConceptAnswer("pe-0000060402", "1+ mắt phải", concept6_4);
		setNewConceptAnswer("pe-0000060403", "2+ mắt phải", concept6_4);
		setNewConceptAnswer("pe-0000060404", "3+ mắt phải", concept6_4);
		setNewConceptAnswer("pe-0000060405", "0.5+ mắt trái", concept6_4);
		setNewConceptAnswer("pe-0000060406", "1+ mắt trái", concept6_4);
		setNewConceptAnswer("pe-0000060407", "2+ mắt trái", concept6_4);
		setNewConceptAnswer("pe-0000060408", "3+ mắt trái", concept6_4);
		conceptService.saveConcept(null, concept6_4);
	}

	public void createSoiGocTienPhong(Concept parent) {
		Concept concept6_5 = new Concept();
		setConceptQuestion("pe-00000605", "Soi góc tiền phòng phải mắt phải", parent, concept6_5);
		Concept concept6_6 = new Concept();
		setConceptQuestion("pe-00000606", "Soi góc tiền phòng trái mắt phải", parent, concept6_6);
		Concept concept6_7 = new Concept();
		setConceptQuestion("pe-00000607", "Soi góc tiền phòng trên mắt phải", parent, concept6_7);
		Concept concept6_8 = new Concept();
		setConceptQuestion("pe-00000608", "Soi góc tiền phòng dưới mắt phải", parent, concept6_8);
		Concept concept6_9 = new Concept();
		setConceptQuestion("pe-00000609", "Soi góc tiền phòng phải mắt trái", parent, concept6_9);
		Concept concept6_10 = new Concept();
		setConceptQuestion("pe-00000610", "Soi góc tiền phòng trái mắt trái", parent, concept6_10);
		Concept concept6_11 = new Concept();
		setConceptQuestion("pe-00000611", "Soi góc tiền phòng trên mắt trái", parent, concept6_11);
		Concept concept6_12 = new Concept();
		setConceptQuestion("pe-00000612", "Soi góc tiền phòng dưới mắt trái", parent, concept6_12);

		conceptService.saveConcept(null, concept6_5);
		conceptService.saveConcept(null, concept6_6);
		conceptService.saveConcept(null, concept6_7);
		conceptService.saveConcept(null, concept6_8);
		conceptService.saveConcept(null, concept6_9);
		conceptService.saveConcept(null, concept6_10);
		conceptService.saveConcept(null, concept6_11);
		conceptService.saveConcept(null, concept6_12);
	}

	public void createGhiChuTienPhong(Concept parent) {
		Concept concept6_13 = new Concept();
		setConceptChild("pe-00000613", "Ghi chú mắt phải", parent, concept6_13);
		Concept concept6_14 = new Concept();
		setConceptChild("pe-00000614", "Ghi chú mắt trái", parent, concept6_14);

		conceptService.saveConcept(null, concept6_13);
		conceptService.saveConcept(null, concept6_14);
	}
	//concept6 end
	
	//concept7 start
	public void createMongMatConcept(Concept parent) {
		Concept concept7 = new Concept();
		setConceptChild("pe-000007", "Mống mắt", parent, concept7);
		Concept mongMat = conceptService.getConceptByCode("pe-000007");
		// Create child
		this.createNhoGianDongTuMongMat(mongMat);
		this.createBinhThuongMongMat(mongMat);
		this.createBatThuongMongMat(mongMat);
		this.createDaThucHienMongMat(mongMat);
		this.createGhichuMongMat(mongMat);
	}

	public void createNhoGianDongTuMongMat(Concept parent) {
		Concept concept7_1 = new Concept();
		setConceptQuestion("pe-00000701", "Nhỏ giãn đồng tử", parent, concept7_1);
		// Create concept answer
		setNewConceptAnswer("pe-0000070101", "Mắt phải", concept7_1);
		setNewConceptAnswer("pe-0000070102", "Mắt trái", concept7_1);
		conceptService.saveConcept(null, concept7_1);
	}

	public void createBinhThuongMongMat(Concept parent) {
		Concept concept7_2 = new Concept();
		setConceptQuestion("pe-00000702", "Bình thường", parent, concept7_2);
		// Create concept answer
		setNewConceptAnswer("pe-0000070201", "Mắt phải", concept7_2);
		setNewConceptAnswer("pe-0000070202", "Mắt trái", concept7_2);
		conceptService.saveConcept(null, concept7_2);
	}

	public void createBatThuongMongMat(Concept parent) {
		Concept concept7_3 = new Concept();
		setConceptQuestion("pe-00000703", "Bất thường", parent, concept7_3);
		// Create concept answer
		setNewConceptAnswer("pe-0000070301", "Xơ teo mắt phải",concept7_3);
		setNewConceptAnswer("pe-0000070302", "Cương tụ mắt phải", concept7_3);
		setNewConceptAnswer("pe-0000070303", "Tân mạch mắt phải", concept7_3);
		setNewConceptAnswer("pe-0000070304", "Phòi mắt phải", concept7_3);
		setNewConceptAnswer("pe-0000070305", "Kẹt mắt phải", concept7_3);
		setNewConceptAnswer("pe-0000070306", "Khuyết mắt phải", concept7_3);
		setNewConceptAnswer("pe-0000070307", "Bussaca mắt phải", concept7_3);
		setNewConceptAnswer("pe-0000070308", "Koepper mắt phải", concept7_3);
		setNewConceptAnswer("pe-0000070309", "Xơ teo mắt trái", concept7_3);
		setNewConceptAnswer("pe-0000070310", "Cương tụ mắt trái", concept7_3);
		setNewConceptAnswer("pe-0000070311", "Tân mạch mắt trái", concept7_3);
		setNewConceptAnswer("pe-0000070312", "Phòi mắt trái", concept7_3);
		setNewConceptAnswer("pe-0000070313", "Kẹt mắt trái", concept7_3);
		setNewConceptAnswer("pe-0000070314", "Khuyết mắt trái", concept7_3);
		setNewConceptAnswer("pe-0000070315", "Bussaca mắt trái", concept7_3);
		setNewConceptAnswer("pe-0000070316", "Koepper mắt trái", concept7_3);
		conceptService.saveConcept(null, concept7_3);
	}

	public void createDaThucHienMongMat(Concept parent) {
		Concept concept7_4 = new Concept();
		setConceptQuestion("pe-00000704", "Đã thực hiện", parent, concept7_4);
		// Create concept answer
		setNewConceptAnswer("pe-0000070401", "Laser mống mắt ngoại vi mắt phải", concept7_4);
		setNewConceptAnswer("pe-0000070402", "Laser tạo hình mống mắt mắt phải", concept7_4);
		setNewConceptAnswer("pe-0000070403", "Laser mống mắt ngoại vi mắt trái", concept7_4);
		setNewConceptAnswer("pe-0000070404", "Laser tạo hình mống mắt mắt trái", concept7_4);
		conceptService.saveConcept(null, concept7_4);
	}

	public void createGhichuMongMat(Concept parent) {
		Concept concept7_6 = new Concept();
		Concept concept7_7 = new Concept();
		setConceptChild("pe-00000705", "Ghi chú mắt phải", parent, concept7_6);
		setConceptChild("pe-00000706", "Ghi chú mắt trái", parent, concept7_7);
		conceptService.saveConcept(null, concept7_6);
		conceptService.saveConcept(null, concept7_7);
	}
	//concept7 end
	
	//concept8 start
	public void createDongTuConcept(Concept parent) {
		Concept concept8 = new Concept();
		setConceptChild("pe-000008", "Đồng tử", parent, concept8);
		Concept dongTu = conceptService.getConceptByCode("pe-000008");
		// Create child
		this.createNhoGianDongTuDongTu(dongTu);
		this.createBinhThuongDongTu(dongTu);
		this.createBatThuongDongTu(dongTu);
		this.createBatThuongPhanXaDongTu(dongTu);
		this.createGhiChuDongTu(dongTu);
	}

	public void createNhoGianDongTuDongTu(Concept parent) {
		Concept concept8_1 = new Concept();
		setConceptQuestion("pe-00000801", "Nhỏ giãn đồng tử", parent, concept8_1);
		setNewConceptAnswer("pe-0000080101", "Mắt phải", concept8_1);
		setNewConceptAnswer("pe-0000080102", "Mắt trái", concept8_1);
		conceptService.saveConcept(null, concept8_1);
	}

	public void createBinhThuongDongTu(Concept parent) {
		Concept concept8_2 = new Concept();
		setConceptQuestion("pe-00000802", "Bình thường", parent, concept8_2);
		setNewConceptAnswer("pe-0000080201", "Phản xạ(+) mắt phải", concept8_2);
		setNewConceptAnswer("pe-0000080202", "Phản xạ(+) mắt trái", concept8_2);
		conceptService.saveConcept(null, concept8_2);
	}

	public void createBatThuongDongTu(Concept parent) {
		Concept concept8_3 = new Concept();
		setConceptQuestion("pe-00000803", "Bất thường", parent, concept8_3);
		setNewConceptAnswer("pe-0000080301", "Méo mắt phải", concept8_3);
		setNewConceptAnswer("pe-0000080302", "Dính mắt phải", concept8_3);
		setNewConceptAnswer("pe-0000080303", "Màng đồng tử mắt phải", concept8_3);
		setNewConceptAnswer("pe-0000080304", "Méo mắt trái", concept8_3);
		setNewConceptAnswer("pe-0000080305", "Dính mắt trái", concept8_3);
		setNewConceptAnswer("pe-0000080306", "Màng đồng tử mắt trái", concept8_3);
		conceptService.saveConcept(null, concept8_3);
	}

	public void createBatThuongPhanXaDongTu(Concept parent) {
		Concept concept8_4 = new Concept();
		setConceptQuestion("pe-00000804", "Bất thường phản xạ", parent, concept8_4);
		setNewConceptAnswer("pe-0000080401", "Kém mắt phải", concept8_4);
		setNewConceptAnswer("pe-0000080402", "Mất mắt phải", concept8_4);
		setNewConceptAnswer("pe-0000080403", "Phản xạ(-) mắt phải", concept8_4);
		setNewConceptAnswer("pe-0000080404", "Kém mắt trái", concept8_4);
		setNewConceptAnswer("pe-0000080405", "Mất mắt trái", concept8_4);
		setNewConceptAnswer("pe-0000080406", "Phản xạ(-) mắt trái", concept8_4);
		conceptService.saveConcept(null, concept8_4);
	}

	public void createGhiChuDongTu(Concept parent) {
		Concept concept8_5 = new Concept();
		setConceptChild("pe-00000805", "Ghi chú mắt phải", parent, concept8_5);
		Concept concept8_6 = new Concept();
		setConceptChild("pe-00000806", "Ghi chú mắt trái", parent, concept8_6);
		conceptService.saveConcept(null, concept8_5);
		conceptService.saveConcept(null, concept8_6);
	}
	//concept8 end
	
	private void createThuyTinhTheConcept(Concept parent) {
		// create parent thuy tinh the
		Concept concept9 = new Concept();
		setConceptChild("pe-000009", "Thủy tinh thể", parent, concept9);
		Concept thuyTinhThe = conceptService.getConceptByCode("pe-000009");
		// create child
		this.createNhoGianDongTuThuyTinhTheConcept(thuyTinhThe);
		this.createBinhThuongThuyTinhTheConcept(thuyTinhThe);
		this.createHinhThaiDucThuyTinhTheConcept(thuyTinhThe);
		this.createDoDucThuyTinhTheConcept(thuyTinhThe);
		this.createIOLThuyTinhTheConcept(thuyTinhThe);
		this.createViTriDatIOLThuyTinhTheConcept(thuyTinhThe);
		this.createDayChangZinnThuyTinhTheConcept(thuyTinhThe);
		this.createAnhDongTuThuyTinhTheConcept(thuyTinhThe);
		this.createDucBaoSauThuyTinhTheConcept(thuyTinhThe);
		this.createTonThuongKhacThuyTinhTheConcept(thuyTinhThe);
		this.createGhiChuThuyTinhTheConcept(thuyTinhThe);
	}

	private void createNhoGianDongTuThuyTinhTheConcept(Concept parent) {
		// create concept question
		Concept concept9_1 = new Concept();
		setConceptQuestion("pe-00000901", "Nhỏ giãn đồng tử", parent, concept9_1);
		// create concept answer
		setNewConceptAnswer("pe-0000090101", "Mắt phải", concept9_1);
		setNewConceptAnswer("pe-0000090102", "Mắt trái", concept9_1);
		// save
		conceptService.saveConcept(null, concept9_1);
	}
	
	private void createBinhThuongThuyTinhTheConcept(Concept parent) {
		// create concept question
		Concept concept9_2 = new Concept();
		setConceptQuestion("pe-00000902", "Bình thường", parent, concept9_2);
		// create concept answer
		setNewConceptAnswer("pe-0000090201", "Bình thường mắt phải", concept9_2);
		setNewConceptAnswer("pe-0000090202", "Bình thường mắt trái", concept9_2);
		// save
		conceptService.saveConcept(null, concept9_2);
	}
	
	private void createHinhThaiDucThuyTinhTheConcept(Concept parent) {
		// create concept question
		Concept concept9_3 = new Concept();
		setConceptQuestion("pe-00000903", "Hình thái đục", parent, concept9_3);
		// create concept answer
		setNewConceptAnswer("pe-0000090301", "Nhân mắt phải", concept9_3);
		setNewConceptAnswer("pe-0000090302", "Vỏ mắt phải", concept9_3);
		setNewConceptAnswer("pe-0000090303", "Dưới bao mắt phải", concept9_3);
		setNewConceptAnswer("pe-0000090304", "Toàn bộ mắt phải", concept9_3);
		setNewConceptAnswer("pe-0000090305", "Giả tróc bao mắt phải", concept9_3);
		setNewConceptAnswer("pe-0000090306", "Canxi hóa mắt phải", concept9_3);
		setNewConceptAnswer("pe-0000090307", "Lệch T3 mắt phải", concept9_3);
		setNewConceptAnswer("pe-0000090308", "Đục trắng sữa mắt phải", concept9_3);
		setNewConceptAnswer("pe-0000090309", "Nhân mắt trái", concept9_3);
		setNewConceptAnswer("pe-0000090310", "Vỏ mắt trái", concept9_3);
		setNewConceptAnswer("pe-0000090311", "Dưới bao mắt trái", concept9_3);
		setNewConceptAnswer("pe-0000090312", "Toàn bộ mắt trái", concept9_3);
		setNewConceptAnswer("pe-0000090313", "Giả tróc bao mắt trái", concept9_3);
		setNewConceptAnswer("pe-0000090314", "Canxi hóa mắt trái", concept9_3);
		setNewConceptAnswer("pe-0000090315", "Lệch T3 mắt trái", concept9_3);
		setNewConceptAnswer("pe-0000090316", "Đục trắng sữa mắt trái", concept9_3);
		// save
		conceptService.saveConcept(null, concept9_3);
	}
	
	private void createDoDucThuyTinhTheConcept(Concept parent) {
		// create concept question
		Concept concept9_4 = new Concept();
		setConceptQuestion("pe-00000904", "Độ đục", parent, concept9_4);
		// create concept answer
		setNewConceptAnswer("pe-0000090401", "Độ 1 mắt phải", concept9_4);
		setNewConceptAnswer("pe-0000090402", "Độ 2 mắt phải", concept9_4);
		setNewConceptAnswer("pe-0000090403", "Độ 3 mắt phải", concept9_4);
		setNewConceptAnswer("pe-0000090404", "Độ 4 mắt phải", concept9_4);
		setNewConceptAnswer("pe-0000090405", "Độ 5 mắt phải", concept9_4);
		setNewConceptAnswer("pe-0000090406", "Độ 1 mắt trái", concept9_4);
		setNewConceptAnswer("pe-0000090407", "Độ 2 mắt trái", concept9_4);
		setNewConceptAnswer("pe-0000090408", "Độ 3 mắt trái", concept9_4);
		setNewConceptAnswer("pe-0000090409", "Độ 4 mắt trái", concept9_4);
		setNewConceptAnswer("pe-0000090410", "Độ 5 mắt trái", concept9_4);
		// save
		conceptService.saveConcept(null, concept9_4);
	}
	
	private void createIOLThuyTinhTheConcept(Concept parent) {
		// create concept question
		Concept concept9_5 = new Concept();
		setConceptQuestion("pe-00000905", "IOL", parent, concept9_5);
		// create concept answer
		setNewConceptAnswer("pe-0000090501", "Đục bao sau(PCO) mắt phải", concept9_5);
		setNewConceptAnswer("pe-0000090502", "Lệch nhiều mắt phải", concept9_5);
		setNewConceptAnswer("pe-0000090503", "Rách bao sau mắt phải", concept9_5);
		setNewConceptAnswer("pe-0000090504", "Hơi lệch mắt phải", concept9_5);
		setNewConceptAnswer("pe-0000090505", "Chính tâm mắt phải", concept9_5);
		setNewConceptAnswer("pe-0000090506", "Đã đặt IOL mắt phải", concept9_5);
		setNewConceptAnswer("pe-0000090507", "Đục bao sau(PCO) mắt trái", concept9_5);
		setNewConceptAnswer("pe-0000090508", "Lệch nhiều mắt trái", concept9_5);
		setNewConceptAnswer("pe-0000090509", "Rách bao sau mắt trái", concept9_5);
		setNewConceptAnswer("pe-0000090510", "Hơi lệch mắt trái", concept9_5);
		setNewConceptAnswer("pe-0000090511", "Chính tâm mắt trái", concept9_5);
		setNewConceptAnswer("pe-0000090512", "Đã đặt IOL mắt trái", concept9_5);
		// save
		conceptService.saveConcept(null, concept9_5);
	}
	
	private void createViTriDatIOLThuyTinhTheConcept(Concept parent) {
		// create concept question
		Concept concept9_6 = new Concept();
		setConceptQuestion("pe-00000906", "Vị trí đặt IOL", parent, concept9_6);
		// create concept answer
		setNewConceptAnswer("pe-0000090601", "Tiền phòng mắt phải", concept9_6);
		setNewConceptAnswer("pe-0000090602", "Lệch nhiều mắt phải", concept9_6);
		setNewConceptAnswer("pe-0000090603", "Tiền phòng mắt trái", concept9_6);
		setNewConceptAnswer("pe-0000090604", "Hậu phòng mắt trái", concept9_6);
		// save
		conceptService.saveConcept(null, concept9_6);
	}
	
	private void createDayChangZinnThuyTinhTheConcept(Concept parent) {
		// create concept question
		Concept concept9_7 = new Concept();
		setConceptQuestion("pe-00000907", "Dây chằng Zinn", parent, concept9_7);
		// create concept answer
		setNewConceptAnswer("pe-0000090701", "Yếu mắt phải", concept9_7);
		setNewConceptAnswer("pe-0000090702", "Đứt mắt phải", concept9_7);
		setNewConceptAnswer("pe-0000090703", "Yếu mắt trái", concept9_7);
		setNewConceptAnswer("pe-0000090704", "Đứt mắt trái", concept9_7);
		// save
		conceptService.saveConcept(null, concept9_7);
	}
	
	private void createAnhDongTuThuyTinhTheConcept(Concept parent) {
		// create concept question
		Concept concept9_8 = new Concept();
		setConceptQuestion("pe-00000908", "Ánh đồng tử", parent, concept9_8);
		// create concept answer
		setNewConceptAnswer("pe-0000090801", "Hồng mắt phải", concept9_8);
		setNewConceptAnswer("pe-0000090802", "Xám mắt phải", concept9_8);
		setNewConceptAnswer("pe-0000090803", "Không soi được mắt phải", concept9_8);
		setNewConceptAnswer("pe-0000090804", "Hồng mắt trái", concept9_8);
		setNewConceptAnswer("pe-0000090805", "Xám mắt trái", concept9_8);
		setNewConceptAnswer("pe-0000090806", "Không soi được mắt trái", concept9_8);
		// save
		conceptService.saveConcept(null, concept9_8);
	}
	
	private void createDucBaoSauThuyTinhTheConcept(Concept parent) {
		// create concept question
		Concept concept9_9 = new Concept();
		setConceptQuestion("pe-00000909", "Đục bao sau", parent, concept9_9);
		// create concept answer
		setNewConceptAnswer("pe-0000090901", "Độ 1 mắt phải", concept9_9);
		setNewConceptAnswer("pe-0000090902", "Độ 2 mắt phải", concept9_9);
		setNewConceptAnswer("pe-0000090903", "Độ 3 mắt phải", concept9_9);
		setNewConceptAnswer("pe-0000090904", "Độ 1 mắt trái", concept9_9);
		setNewConceptAnswer("pe-0000090905", "Độ 2 mắt trái", concept9_9);
		setNewConceptAnswer("pe-0000090906", "Độ 3 mắt trái", concept9_9);
		// save
		conceptService.saveConcept(null, concept9_9);
	}
	
	private void createTonThuongKhacThuyTinhTheConcept(Concept parent) {
		// create concept question
		Concept concept9_10 = new Concept();
		setConceptQuestion("pe-00000910", "Tổn thương khác", parent, concept9_10);
		// create concept answer
		setNewConceptAnswer("pe-0000091001", "Có tổn thương mắt phải", concept9_10);
		setNewConceptAnswer("pe-0000091002", "Không có mắt phải", concept9_10);
		setNewConceptAnswer("pe-0000091003", "Có tổn thương mắt trái", concept9_10);
		setNewConceptAnswer("pe-0000091004", "Không có mắt trái", concept9_10);
		// save
		conceptService.saveConcept(null, concept9_10);
	}
	
	private void createGhiChuThuyTinhTheConcept(Concept parent) {
		// create concept 
		Concept concept9_11 = new Concept();
		setConceptChild("pe-00000911", "Ghi chú mắt phải", parent, concept9_11);
		Concept concept9_12 = new Concept();
		setConceptChild("pe-00000912", "Ghi chú mắt trái", parent, concept9_12);
		// save
		conceptService.saveConcept(null, concept9_11);
		conceptService.saveConcept(null, concept9_12);
	}

	private void createThuyTinhDichConcept(Concept parent) {
		// create parent
		Concept concept1 = new Concept();
		setConceptChild("pe-000010", "Thủy tinh dịch", parent, concept1);
		Concept thuyTinhDich = conceptService.getConceptByCode("pe-000010");
		// create child
		this.createNhoGianDongTuThuyTinhDichConcept(thuyTinhDich);
		this.createBinhThuongThuyTinhDichConcept(thuyTinhDich);
		this.createBatThuongThuyTinhDichConcept(thuyTinhDich);
		this.createGhiChuThuyTinhDichConcept(thuyTinhDich);
	}
	
	private void createNhoGianDongTuThuyTinhDichConcept(Concept thuyTinhDich) {
		// create concept question
		Concept concept10_1 = new Concept();
		setConceptQuestion("pe-00001001", "Nhỏ giãn đồng tử", thuyTinhDich, concept10_1);
		// create concept answer
		setNewConceptAnswer("pe-0000100101", "Mắt phải", concept10_1);
		setNewConceptAnswer("pe-0000100102", "Mắt trái", concept10_1);
		// save
		conceptService.saveConcept(null, concept10_1);
	}
	private void createBinhThuongThuyTinhDichConcept(Concept thuyTinhDich) {
		// create concept question
		Concept concept10_2 = new Concept();
		setConceptQuestion("pe-00001002", "Bình thường", thuyTinhDich, concept10_2);
		// create concept answer
		setNewConceptAnswer("pe-0000100201", "Bình thường mắt phải", concept10_2);
		setNewConceptAnswer("pe-0000100202", "Bình thường mắt trái", concept10_2);
		// saves
		conceptService.saveConcept(null, concept10_2);
	}
	
	private void createBatThuongThuyTinhDichConcept(Concept thuyTinhDich) {
		// create concept question
		Concept concept10_3 = new Concept();
		setConceptQuestion("pe-00001003", "Bất thường Thủy Tinh Dịch", thuyTinhDich, concept10_3);
		// create concept answer
		setNewConceptAnswer("pe-0000100301", "Đục mắt phải", concept10_3);
		setNewConceptAnswer("pe-0000100302", "Xuất huyết mắt phải", concept10_3);
		setNewConceptAnswer("pe-0000100303", "Bóng pha lê thể sau mắt phải", concept10_3);
		setNewConceptAnswer("pe-0000100304", "Đục mắt trái", concept10_3);
		setNewConceptAnswer("pe-0000100305", "Xuất huyết mắt trái", concept10_3);
		setNewConceptAnswer("pe-0000100306", "Bóng pha lê thể sau mắt trái", concept10_3);
		// save
		conceptService.saveConcept(null, concept10_3);
	}
	
	public void createGhiChuThuyTinhDichConcept(Concept parent) {
		// create
		Concept concept10_4 = new Concept();
		setConceptChild("pe-00001004","Ghi chú mắt phải", parent, concept10_4);
		Concept concept10_5 = new Concept();
		setConceptChild("pe-00001005","Ghi chú mắt trái", parent, concept10_5);
		// save
		conceptService.saveConcept(null, concept10_4);
		conceptService.saveConcept(null, concept10_5);
	}
	
	//concept11 start
	public void createDayMatConcept(Concept parent) {
		Concept concept11 = new Concept();
		setConceptChild("pe-000011", "Đáy mắt", parent, concept11);
		Concept dayMat = conceptService.getConceptByCode("pe-000011");
		// Create child
		this.createNhoGianDongTuDayMat(dayMat);
		this.createBinhThuongDayMat(dayMat);
		this.createBatThuongDayMat(dayMat);
		this.createGhiChuDayMat(dayMat);
	}

	public void createNhoGianDongTuDayMat(Concept parent) {
		Concept concept11_1 = new Concept();
		setConceptQuestion("pe-00001101", "Nhỏ giãn đồng tử", parent, concept11_1);
		setNewConceptAnswer("pe-0000110101", "Mắt phải", concept11_1);
		setNewConceptAnswer("pe-0000110102", "Mắt trái", concept11_1);
		conceptService.saveConcept(null, concept11_1);
	}

	public void createBinhThuongDayMat(Concept parent) {
		Concept concept11_2 = new Concept();
		setConceptQuestion("pe-00001102", "Bình thường", parent, concept11_2);
		setNewConceptAnswer("pe-0000110201", "Bình thường mắt phải", concept11_2);
		setNewConceptAnswer("pe-0000110202", "Bình thường mắt trái", concept11_2);
		conceptService.saveConcept(null, concept11_2);
	}

	public void createBatThuongDayMat(Concept parent) {
		Concept concept11_3 = new Concept();
		setConceptQuestion("pe-00001103", "Bất thường", parent, concept11_3);
		setNewConceptAnswer("pe-0000110301", "Bất thường mắt phải", concept11_3);
		setNewConceptAnswer("pe-0000110302", "Drusen mắt phải", concept11_3);
		setNewConceptAnswer("pe-0000110303", "Bất thường mắt trái", concept11_3);
		setNewConceptAnswer("pe-0000110304", "Drusen mắt trái", concept11_3);
		conceptService.saveConcept(null, concept11_3);
	}

	public void createGhiChuDayMat(Concept parent) {
		Concept concept11_4 = new Concept();
		Concept concept11_5 = new Concept();
		setConceptChild("pe-00001104", "Ghi chú mắt phải", parent, concept11_4);
		setConceptChild("pe-00001105", "Ghi chú mắt trái", parent, concept11_5);
		conceptService.saveConcept(null, concept11_4);
		conceptService.saveConcept(null, concept11_5);
	}
	//concept11 end

	//concept12 start
	public void createVongMacConcept(Concept parent) {
		Concept concept12 = new Concept();
		setConceptChild("pe-000012", "Võng mạc", parent, concept12);
		Concept vongMac = conceptService.getConceptByCode("pe-000012");
		// Create child
		this.createNhoGianDongTuVongMac(vongMac);
		this.createBinhThuongVongMac(vongMac);
		this.createThaiHoaVongMac(vongMac);
		this.createVongMacVongMac(vongMac);
		this.createXuatHuyetVongMacVongMac(vongMac);
		this.createXuatTrietVongMac(vongMac);
		this.createThanhLichVongMac(vongMac);
		this.createBieuMoSacToVongMac(vongMac);
		this.createHacMacVongMac(vongMac);
		this.createHacVongMacVongMac(vongMac);
		this.createMachMauVongMac(vongMac);
		this.createDongMachVongMac(vongMac);
		this.createGhiChuVongMac(vongMac);
	}

	public void createNhoGianDongTuVongMac(Concept parent) {
		Concept concept12_1 = new Concept();
		setConceptQuestion("pe-00001201", "Nhỏ giãn đồng tử", parent, concept12_1);
		setNewConceptAnswer("pe-0000120101", "Mắt phải", concept12_1);
		setNewConceptAnswer("pe-0000120102", "Mắt trái", concept12_1);
		conceptService.saveConcept(null, concept12_1);
	}

	public void createBinhThuongVongMac(Concept parent) {
		Concept concept12_2 = new Concept();
		setConceptQuestion("pe-00001202", "Bình thường", parent, concept12_2);
		setNewConceptAnswer("pe-0000120201", "Bình thường mắt phải", concept12_2);
		setNewConceptAnswer("pe-0000120202", "Bình thường mắt trái", concept12_2);
		conceptService.saveConcept(null, concept12_2);
	}

	public void createThaiHoaVongMac(Concept parent) {
		Concept concept12_3 = new Concept();
		setConceptQuestion("pe-00001203", "Thái hóa võng mạc", parent, concept12_3);
		setNewConceptAnswer("pe-0000120301", "Trung tâm mắt phải", concept12_3);
		setNewConceptAnswer("pe-0000120302", "Ngoại biên mắt phải", concept12_3);
		setNewConceptAnswer("pe-0000120303", "Trung tâm mắt trái", concept12_3);
		setNewConceptAnswer("pe-0000120304", "Ngoại biên mắt trái", concept12_3);
		conceptService.saveConcept(null, concept12_3);
	}

	public void createVongMacVongMac(Concept parent) {
		Concept concept12_4 = new Concept();
		setConceptQuestion("pe-00001204", "Võng mạc", parent, concept12_4);
		setNewConceptAnswer("pe-0000120401", "Bong mắt phải", concept12_4);
		setNewConceptAnswer("pe-0000120402", "Rách mắt phải", concept12_4);
		setNewConceptAnswer("pe-0000120403", "Tách lớp mắt phải", concept12_4);
		setNewConceptAnswer("pe-0000120404", "Bong thanh dịch mắt phải", concept12_4);
		setNewConceptAnswer("pe-0000120405", "Loạn dưỡng mắt phải", concept12_4);
		setNewConceptAnswer("pe-0000120406", "Thoái hóa mắt phải", concept12_4);
		setNewConceptAnswer("pe-0000120407", "Xuất huyết mắt phải", concept12_4);
		setNewConceptAnswer("pe-0000120408", "Xuất tiết mắt phải", concept12_4);
		setNewConceptAnswer("pe-0000120409", "Màng trước VM(ERM) mắt phải", concept12_4);
		setNewConceptAnswer("pe-0000120410", "Bong mắt trái", concept12_4);
		setNewConceptAnswer("pe-0000120411", "Rách mắt trái", concept12_4);
		setNewConceptAnswer("pe-0000120412", "Tách lớp mắt trái", concept12_4);
		setNewConceptAnswer("pe-0000120413", "Bong thanh dịch mắt trái", concept12_4);
		setNewConceptAnswer("pe-0000120414", "Loạn dưỡng mắt trái", concept12_4);
		setNewConceptAnswer("pe-0000120415", "Thoái hóa mắt trái", concept12_4);
		setNewConceptAnswer("pe-0000120416", "Xuất huyết mắt trái", concept12_4);
		setNewConceptAnswer("pe-0000120417", "Xuất tiết mắt trái", concept12_4);
		setNewConceptAnswer("pe-0000120418", "Màng trước VM(ERM) mắt trái", concept12_4);
		conceptService.saveConcept(null, concept12_4);
	}

	public void createXuatHuyetVongMacVongMac(Concept parent) {
		Concept concept12_5 = new Concept();
		setConceptQuestion("pe-00001205", "Xuất huyết võng mạc", parent, concept12_5);
		setNewConceptAnswer("pe-0000120501", "Nông mắt phải", concept12_5);
		setNewConceptAnswer("pe-0000120502", "Sâu mắt phải", concept12_5);
		setNewConceptAnswer("pe-0000120503", "Nông mắt trái", concept12_5);
		setNewConceptAnswer("pe-0000120504", "Sâu mắt trái", concept12_5);
		conceptService.saveConcept(null, concept12_5);
	}

	public void createXuatTrietVongMac(Concept parent) {
		Concept concept12_6 = new Concept();
		setConceptQuestion("pe-00001206", "Xuất triết", parent, concept12_6);
		setNewConceptAnswer("pe-0000120601", "Cứng mắt phải", concept12_6);
		setNewConceptAnswer("pe-0000120602", "Dạng bông mắt phải", concept12_6);
		setNewConceptAnswer("pe-0000120603", "Cứng mắt trái", concept12_6);
		setNewConceptAnswer("pe-0000120604", "Dạng bông mắt trái", concept12_6);
		conceptService.saveConcept(null, concept12_6);
	}

	public void createThanhLichVongMac(Concept parent) {
		Concept concept12_7 = new Concept();
		setConceptQuestion("pe-00001207", "Thanh lịch", parent, concept12_7);
		setNewConceptAnswer("pe-0000120701", "Bong mắt phải", concept12_7);
		setNewConceptAnswer("pe-0000120702", "Bong mắt trái", concept12_7);
		conceptService.saveConcept(null, concept12_7);
	}

	public void createBieuMoSacToVongMac(Concept parent) {
		Concept concept12_8 = new Concept();
		setConceptQuestion("pe-00001208", "Biểu mô sắc tố", parent, concept12_8);
		setNewConceptAnswer("pe-0000120801", "Bong mắt phải", concept12_8);
		setNewConceptAnswer("pe-0000120802", "Bong mắt trái", concept12_8);
		conceptService.saveConcept(null, concept12_8);
	}

	public void createHacMacVongMac(Concept parent) {
		Concept concept12_9 = new Concept();
		setConceptQuestion("pe-00001209", "Hắc mạc", parent, concept12_9);
		setNewConceptAnswer("pe-0000120901", "Viêm khu trú mắt phải", concept12_9);
		setNewConceptAnswer("pe-0000120902", "Viêm lan tỏa mắt phải", concept12_9);
		setNewConceptAnswer("pe-0000120903", "Viêm thể mi sau mắt phải", concept12_9);
		setNewConceptAnswer("pe-0000120904", "Viêm khác mắt phải", concept12_9);
		setNewConceptAnswer("pe-0000120905", "Thoái hóa mắt phải", concept12_9);
		setNewConceptAnswer("pe-0000120906", "Loạn dưỡng mắt phải", concept12_9);
		setNewConceptAnswer("pe-0000120907", "Xuất huyết mắt phải", concept12_9);
		setNewConceptAnswer("pe-0000120908", "Rách mắt phải", concept12_9);
		setNewConceptAnswer("pe-0000120909", "Bong mắt phải", concept12_9);
		setNewConceptAnswer("pe-0000120910", "Viêm khu trú mắt trái", concept12_9);
		setNewConceptAnswer("pe-0000120911", "Viêm lan tỏa mắt trái", concept12_9);
		setNewConceptAnswer("pe-0000120912", "Viêm thể mi sau mắt trái", concept12_9);
		setNewConceptAnswer("pe-0000120913", "Viêm khác mắt trái", concept12_9);
		setNewConceptAnswer("pe-0000120914", "Thoái hóa mắt trái", concept12_9);
		setNewConceptAnswer("pe-0000120915", "Loạn dưỡng mắt trái", concept12_9);
		setNewConceptAnswer("pe-0000120916", "Xuất huyết mắt trái", concept12_9);
		setNewConceptAnswer("pe-0000120917", "Rách mắt trái", concept12_9);
		setNewConceptAnswer("pe-0000120918", "Bong mắt trái", concept12_9);
		conceptService.saveConcept(null, concept12_9);
	}

	public void createHacVongMacVongMac(Concept parent) {
		Concept concept12_10 = new Concept();
		setConceptQuestion("pe-00001210", "Hắc võng mạc", parent, concept12_10);
		setNewConceptAnswer("pe-0000121001", "Sẹo mắt phải", concept12_10);
		setNewConceptAnswer("pe-0000121002", "Thái hóa mắt phải", concept12_10);
		setNewConceptAnswer("pe-0000121003", "Viêm mắt phải", concept12_10);
		setNewConceptAnswer("pe-0000121004", "Sẹo mắt trái", concept12_10);
		setNewConceptAnswer("pe-0000121005", "Thái hóa mắt trái", concept12_10);
		setNewConceptAnswer("pe-0000121006", "Viêm mắt trái", concept12_10);
		conceptService.saveConcept(null, concept12_10);
	}

	public void createMachMauVongMac(Concept parent) {
		Concept concept12_11 = new Concept();
		setConceptQuestion("pe-00001211", "Mạch máu võng mạc", parent, concept12_11);
		setNewConceptAnswer("pe-0000121101", "Biến đổi mắt phải", concept12_11);
		setNewConceptAnswer("pe-0000121102", "Tăng sinh mắt phải", concept12_11);
		setNewConceptAnswer("pe-0000121103", "Loạn dưỡng mắt phải", concept12_11);
		setNewConceptAnswer("pe-0000121104", "Biến đổi mắt trái", concept12_11);
		setNewConceptAnswer("pe-0000121105", "Tăng sinh mắt trái", concept12_11);
		setNewConceptAnswer("pe-0000121106", "Loạn dưỡng mắt trái", concept12_11);
		conceptService.saveConcept(null, concept12_11);
	}

	public void createDongMachVongMac(Concept parent) {
		Concept concept12_12 = new Concept();
		setConceptQuestion("pe-00001212", "Động mạch võng mạc", parent, concept12_12);
		setNewConceptAnswer("pe-0000121201", "Tắc thoáng qua mắt phải", concept12_12);
		setNewConceptAnswer("pe-0000121202", "Tắc trung tâm mắt phải", concept12_12);
		setNewConceptAnswer("pe-0000121203", "Tắc thoáng qua mắt trái", concept12_12);
		setNewConceptAnswer("pe-0000121204", "Tắc trung tâm mắt trái", concept12_12);
		conceptService.saveConcept(null, concept12_12);
	}

	public void createGhiChuVongMac(Concept parent) {
		Concept concept12_13 = new Concept();
		setConceptChild("pe-00001213", "Ghi chú mắt phải", parent, concept12_13);
		Concept concept12_14 = new Concept();
		setConceptChild("pe-00001214", "Ghi chú mắt trái", parent, concept12_14);
		conceptService.saveConcept(null, concept12_13);
		conceptService.saveConcept(null, concept12_14);
	}
	
	private void createDiaThiConcept(Concept parent) {
		Concept concept13 = new Concept();
		setConceptChild("pe-000013", "Đĩa thị", parent, concept13);
		Concept diaThi = conceptService.getConceptByCode("pe-000013");
		//create chill
		this.createNhoGianDongTuDiaThiConcept(diaThi);
		this.createBinhThuongDiaThiConcept(diaThi);
		this.createBatThuongGaiDiaThiConcept(diaThi);
		this.createCDDiaThiConcept(diaThi);
		this.createNotchingDiaThiConcept(diaThi);
		this.createTanMachGaiDiaThiConcept(diaThi);
		this.createBatThuongKhacDiaThiConcept(diaThi);
		this.createGhiChuDiaThiConcept(diaThi);
	}
	
	private void createNhoGianDongTuDiaThiConcept(Concept parent) {
		Concept concept13_1 = new Concept();
		setConceptQuestion("pe-00001301", "Nhỏ giãn đồng tử", parent, concept13_1);
		setNewConceptAnswer("pe-0000130101", "Mắt phải", concept13_1);
		setNewConceptAnswer("pe-0000130102", "Mắt trái", concept13_1);
		// save
		conceptService.saveConcept(null, concept13_1);
	}
	
	private void createBinhThuongDiaThiConcept(Concept parent) {
		Concept concept13_2 = new Concept();
		setConceptQuestion("pe-00001302", "Bình thường", parent, concept13_2);
		setNewConceptAnswer("pe-0000130201", "Bình thường mắt phải", concept13_2);
		setNewConceptAnswer("pe-0000130202", "Bình thường mắt trái", concept13_2);
		// save
		conceptService.saveConcept(null, concept13_2);
	}
	
	private void createBatThuongGaiDiaThiConcept(Concept parent) {
		Concept concept13_3 = new Concept();
		setConceptQuestion("pe-00001303", "Bất thường gai", parent, concept13_3);
		setNewConceptAnswer("pe-0000130301", "Lõm teo gai mắt phải", concept13_3);
		setNewConceptAnswer("pe-0000130302", "Phù gai mắt phải", concept13_3);
		setNewConceptAnswer("pe-0000130303", "Bạc màu gai mắt phải", concept13_3);
		setNewConceptAnswer("pe-0000130304", "Thoái hóa cạnh gai mắt phải", concept13_3);
		setNewConceptAnswer("pe-0000130305", "ISN't bất thường phải", concept13_3);
		setNewConceptAnswer("pe-0000130306", "Lõm teo gai mắt trái", concept13_3);
		setNewConceptAnswer("pe-0000130307", "Phù gai mắt trái", concept13_3);
		setNewConceptAnswer("pe-0000130308", "Bạc màu gai mắt trái", concept13_3);
		setNewConceptAnswer("pe-0000130309", "Thoái hóa cạnh gai mắt trái", concept13_3);
		setNewConceptAnswer("pe-0000130310", "ISN't bất thường trái", concept13_3);
		// save
		conceptService.saveConcept(null, concept13_3);
	}
	
	private void createCDDiaThiConcept(Concept parent) {
		Concept concept13_4 = new Concept();
		setConceptQuestion("pe-00001304", "C/D", parent, concept13_4);
		setNewConceptAnswer("pe-0000130401", "0.1 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130402", "0.2 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130403", "0.3 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130404", "0.4 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130405", "0.5 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130406", "0.6 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130407", "0.7 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130408", "0.8 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130409", "0.9 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130410", "0.95 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130411", "0.99 mắt phải", concept13_4);
		setNewConceptAnswer("pe-0000130412", "0.1 mắt trái", concept13_4);
		setNewConceptAnswer("pe-0000130413", "0.2 mắt trái", concept13_4);
		setNewConceptAnswer("pe-0000130414", "0.3 mắt trái", concept13_4);
		setNewConceptAnswer("pe-0000130415", "0.4 mắt trái", concept13_4);
		setNewConceptAnswer("pe-0000130416", "0.5 mắt trái", concept13_4);
		setNewConceptAnswer("pe-0000130417", "0.6 mắt trái", concept13_4);
		setNewConceptAnswer("pe-0000130418", "0.7 mắt trái", concept13_4);
		setNewConceptAnswer("pe-0000130419", "0.8 mắt trái", concept13_4);
		setNewConceptAnswer("pe-0000130420", "0.9 mắt trái", concept13_4);
		setNewConceptAnswer("pe-0000130421", "0.95 mắt trái", concept13_4);
		setNewConceptAnswer("pe-0000130422", "0.99 mắt trái", concept13_4);
		// save
		conceptService.saveConcept(null, concept13_4);
	}
	
	private void createNotchingDiaThiConcept(Concept parent) {
		Concept concept13_5 = new Concept();
		setConceptQuestion("pe-00001305", "Notching", parent, concept13_5);
		setNewConceptAnswer("pe-0000130501", "1h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130502", "2h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130503", "3h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130504", "4h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130505", "5h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130506", "6h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130507", "7h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130508", "8h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130509", "9h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130510", "10h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130511", "11h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130512", "12h mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130513", "Notching bất thường mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130514", "Bình thường mắt phải", concept13_5);
		setNewConceptAnswer("pe-0000130515", "1h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130516", "2h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130517", "3h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130518", "4h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130519", "5h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130520", "6h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130521", "7h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130522", "8h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130523", "9h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130524", "10h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130525", "11h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130526", "12h mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130527", "Notching bất thường mắt trái", concept13_5);
		setNewConceptAnswer("pe-0000130528", "Bình thường mắt trái", concept13_5);
		// save
		conceptService.saveConcept(null, concept13_5);
	}
	
	private void createTanMachGaiDiaThiConcept(Concept parent) {
		Concept concept13_6 = new Concept();
		setConceptQuestion("pe-00001306", "Tân mạch gai", parent, concept13_6);
		setNewConceptAnswer("pe-0000130601", "1/4 gai mắt phải", concept13_6);
		setNewConceptAnswer("pe-0000130602", "1/4 đến 1/2 gai mắt phải", concept13_6);
		setNewConceptAnswer("pe-0000130603", "Hơn 1/2 gai mắt phải", concept13_6);
		setNewConceptAnswer("pe-0000130604", "1/4 gai mắt trái", concept13_6);
		setNewConceptAnswer("pe-0000130605", "1/4 đến 1/2 gai mắt trái", concept13_6);
		setNewConceptAnswer("pe-0000130606", "Hơn 1/2 gai mắt trái", concept13_6);
		// save
		conceptService.saveConcept(null, concept13_6);
	}
	
	private void createBatThuongKhacDiaThiConcept(Concept parent) {
		Concept concept13_7 = new Concept();
		setConceptQuestion("pe-00001307", "Bất thường khác", parent, concept13_7);
		setNewConceptAnswer("pe-0000130701", "Không soi được mắt phải", concept13_7);
		setNewConceptAnswer("pe-0000130702", "Không soi được mắt trái", concept13_7);
		// save
		conceptService.saveConcept(null, concept13_7);
	}
	
	private void createGhiChuDiaThiConcept(Concept parent) {
		Concept concept13_8 = new Concept();
		setConceptChild("pe-00001308", "Ghi chú mắt phải", parent, concept13_8);
		Concept concept13_9 = new Concept();
		setConceptChild("pe-00001308", "Ghi chú mắt trái", parent, concept13_9);
		// save
		conceptService.saveConcept(null, concept13_8);
		conceptService.saveConcept(null, concept13_9);
	}
	//concept14 start
	public void createHoangDiemConcept(Concept parent) {
		Concept concept14 = new Concept();
		setConceptChild("pe-000014", "Hoàng điểm", parent, concept14);
		Concept hoangDiem = conceptService.getConceptByCode("pe-000014");
		// Create child
		this.createNhoGianDongTuHoangDiem(hoangDiem);
		this.createBinhThuongHoangDiem(hoangDiem);
		this.createBatThuongHoangDiem(hoangDiem);
		this.createPhuHoangDiem(hoangDiem);
		this.createLoHoangDiem(hoangDiem);
		this.createDrusenHoangDiem(hoangDiem);
		this.createGhiChuHoangDiem(hoangDiem);
	}

	public void createNhoGianDongTuHoangDiem(Concept parent) {
		Concept concept14_1 = new Concept();
		setConceptQuestion("pe-00001401", "Nhỏ giãn đồng tử", parent, concept14_1);
		setNewConceptAnswer("pe-0000140101", "Mắt phải", concept14_1);
		setNewConceptAnswer("pe-0000140102", "Mắt trái", concept14_1);
		conceptService.saveConcept(null, concept14_1);
	}

	public void createBinhThuongHoangDiem(Concept parent) {
		Concept concept14_2 = new Concept();
		setConceptQuestion("pe-00001402", "Bình thường", parent, concept14_2);
		setNewConceptAnswer("pe-0000140201", "Bình thường mắt phải", concept14_2);
		setNewConceptAnswer("pe-0000140202", "Bình thường mắt trái", concept14_2);
		conceptService.saveConcept(null, concept14_2);
	}

	public void createBatThuongHoangDiem(Concept parent) {
		Concept concept14_3 = new Concept();
		setConceptQuestion("pe-00001403", "Bất thường hoàng điểm", parent, concept14_3);
		setNewConceptAnswer("pe-0000140301", "Mất ánh hoàng điểm mắt phải", concept14_3);
		setNewConceptAnswer("pe-0000140302", "Sẹo hoàng điểm mắt phải", concept14_3);
		setNewConceptAnswer("pe-0000140303", "Xuất huyết vùng hoàng điểm mắt phải", concept14_3);
		setNewConceptAnswer("pe-0000140304", "Xuất tiết gần hoàng điểm mắt phải", concept14_3);
		setNewConceptAnswer("pe-0000140305", "Teo hoàng điểm mắt phải", concept14_3);
		setNewConceptAnswer("pe-0000140306", "EP change mắt phải", concept14_3);
		setNewConceptAnswer("pe-0000140307", "Mất ánh hoàng điểm mắt trái", concept14_3);
		setNewConceptAnswer("pe-0000140308", "Sẹo hoàng điểm mắt trái", concept14_3);
		setNewConceptAnswer("pe-0000140309", "Xuất huyết vùng hoàng điểm mắt trái", concept14_3);
		setNewConceptAnswer("pe-0000140310", "Xuất tiết gần hoàng điểm mắt trái", concept14_3);
		setNewConceptAnswer("pe-0000140311", "Teo hoàng điểm mắt trái", concept14_3);
		setNewConceptAnswer("pe-0000140312", "EP change mắt trái", concept14_3);
		conceptService.saveConcept(null, concept14_3);
	}

	public void createPhuHoangDiem(Concept parent) {
		Concept concept14_4 = new Concept();
		setConceptQuestion("pe-00001404", "Phù", parent, concept14_4);
		setNewConceptAnswer("pe-0000140401", "Dạng nang(CME) mắt phải", concept14_4);
		setNewConceptAnswer("pe-0000140402", "Tỏa lan mắt phải", concept14_4);
		setNewConceptAnswer("pe-0000140403", "Dạng nang(CME) mắt trái", concept14_4);
		setNewConceptAnswer("pe-0000140404", "Tỏa lan mắt trái", concept14_4);
		conceptService.saveConcept(null, concept14_4);
	}

	public void createLoHoangDiem(Concept parent) {
		Concept concept14_5 = new Concept();
		setConceptQuestion("pe-00001405", "Lỗ", parent, concept14_5);
		setNewConceptAnswer("pe-0000140501", "Lỗ lớp mắt phải", concept14_5);
		setNewConceptAnswer("pe-0000140502", "Giả lỗ mắt phải", concept14_5);
		setNewConceptAnswer("pe-0000140503", "Lỗ lớp mắt trái", concept14_5);
		setNewConceptAnswer("pe-0000140504", "Giả lỗ mắt trái", concept14_5);
		conceptService.saveConcept(null, concept14_5);
	}

	public void createDrusenHoangDiem(Concept parent) {
		Concept concept14_6 = new Concept();
		setConceptQuestion("pe-00001406", "Drusen", parent, concept14_6);
		setNewConceptAnswer("pe-0000140601", "Drusen vùng hoàng điểm mắt phải", concept14_6);
		setNewConceptAnswer("pe-0000140602", "Drusen rải rác mắt phải", concept14_6);
		setNewConceptAnswer("pe-0000140603", "Drusen vùng hoàng điểm mắt trái", concept14_6);
		setNewConceptAnswer("pe-0000140604", "Drusen rải rác mắt trái", concept14_6);
		conceptService.saveConcept(null, concept14_6);
	}

	public void createGhiChuHoangDiem(Concept parent) {
		Concept concept14_7 = new Concept();
		setConceptChild("pe-00001407", "Ghi chú mắt phải", parent, concept14_7);
		Concept concept14_8 = new Concept();
		setConceptChild("pe-00001408", "Ghi chú mắt trái", parent, concept14_8);
		conceptService.saveConcept(null, concept14_7);
		conceptService.saveConcept(null, concept14_8);
	}
	//concept14 end
	
	//concept15
	private void createHeMachConcept(Concept parent) {
		Concept concept15=new Concept();
		setConceptChild("pe-000015", "Hệ mạch", parent, concept15);
		Concept heMach=conceptService.getConceptByCode("pe_000015");
		//create chill
		this.createNhoGianDongTuHeMach(heMach);
		this.createBinhThuongHeMach(heMach);
		this.createTacDongMachHeMach(heMach);
		this.createTacTinhMachHeMach(heMach);
		this.createBatThuongHeMach(heMach);
		this.createTanMachHeMach(heMach);
		this.createTanMachHacMachHeMach(heMach);
		this.createGhiChuHeMach(heMach);
	}

	private void createNhoGianDongTuHeMach(Concept heMach) {
		Concept concept15_1 = new Concept();
		setConceptQuestion("pe-00001501", "Nhỏ giãn đồng tử", heMach, concept15_1);
		// create concept answer
		setNewConceptAnswer("pe-0000150101", "Mắt phải", concept15_1);
		setNewConceptAnswer("pe-0000150102", "Mắt trái", concept15_1);
		// save
		conceptService.saveConcept(null, concept15_1);
	}

	private void createBinhThuongHeMach(Concept heMach) {
		// create concept question
		Concept concept15_2 = new Concept();
		setConceptQuestion("pe-00001502", "Bình thường", heMach, concept15_2);
		// create concept answer
		setNewConceptAnswer("pe-0000150201", "Bình thường mắt phải", concept15_2);
		setNewConceptAnswer("pe-0000150202", "Bình thường mắt trái", concept15_2);
		// save
		conceptService.saveConcept(null, concept15_2);
	}

	private void createTacDongMachHeMach(Concept heMach) {
		// create concept question
	    Concept concept15_3 = new Concept();
		setConceptQuestion("pe-00001503", "Tắc động mạch", heMach, concept15_3);
		// create concept answer
		setNewConceptAnswer("pe-0000150301", "Trung tâm mắt phải", concept15_3);
		setNewConceptAnswer("pe-0000150302", "Nhánh mắt phải", concept15_3);
		setNewConceptAnswer("pe-0000150303", "MiVm mắt phải", concept15_3);
		setNewConceptAnswer("pe-0000150304", "Trung tâm mắt trái", concept15_3);
		setNewConceptAnswer("pe-0000150305", "Nhánh mắt trái", concept15_3);
		setNewConceptAnswer("pe-0000150306", "MiVm mắt trái", concept15_3);
		// save
		conceptService.saveConcept(null, concept15_3);
	}

	private void createTacTinhMachHeMach(Concept heMach) {
		// create concept question
	    Concept concept15_4 = new Concept();
		setConceptQuestion("pe-00001504", "Tắc tĩnh mặch", heMach, concept15_4);
		// create concept answer
		setNewConceptAnswer("pe-0000150401", "Trung tâm mắt phải", concept15_4);
		setNewConceptAnswer("pe-0000150402", "Nhánh mắt phải", concept15_4);	
		setNewConceptAnswer("pe-0000150403", "Trung tâm mắt trái", concept15_4);
		setNewConceptAnswer("pe-0000150404", "Nhánh mắt trái", concept15_4);
		// save
		conceptService.saveConcept(null, concept15_4);		
		
	}

	private void createBatThuongHeMach(Concept heMach) {
		// create concept question
	    Concept concept15_5 = new Concept();
		setConceptQuestion("pe-00001505", "Bất thường", heMach, concept15_5);
		// create concept answer
		setNewConceptAnswer("pe-0000150501", "Phù mắt phải", concept15_5);
		setNewConceptAnswer("pe-0000150502", "Thiếu máu mắt phải", concept15_5);
		setNewConceptAnswer("pe-0000150503", "Viêm mao mạch mắt phải", concept15_5);
		setNewConceptAnswer("pe-0000150504", "Phù mắt trái", concept15_5);
		setNewConceptAnswer("pe-0000150505", "Thiếu máu mắt trái", concept15_5);
		setNewConceptAnswer("pe-0000150506", "Viêm mao mạch mắt trái", concept15_5);
		// save
		conceptService.saveConcept(null, concept15_5);		
	}

	private void createTanMachHeMach(Concept heMach) {
		// create concept question
	    Concept concept15_6 = new Concept();
		setConceptQuestion("pe-00001506", "Tân mạch", heMach, concept15_6);
		// create concept answer
		setNewConceptAnswer("pe-0000150601", "Tân mạch võng mạc mắt phải", concept15_6);
		setNewConceptAnswer("pe-0000150602", "Tân mạch hắc mạc mắt phải", concept15_6);	
		setNewConceptAnswer("pe-0000150603", "Tân mạch võng mạc mắt trái", concept15_6);
		setNewConceptAnswer("pe-0000150604", "Tân mạch hắc mạc mắt trái", concept15_6);
		// save
		conceptService.saveConcept(null, concept15_6);		
		
	}

	private void createTanMachHacMachHeMach(Concept heMach) {
		// create concept question
	    Concept concept15_7 = new Concept();
		setConceptQuestion("pe-00001507", "Tân mạch hắc mạch", heMach, concept15_7);
		// create concept answer
		setNewConceptAnswer("pe-0000150701", "Dưới hoàng điểm mắt phải", concept15_7);
		setNewConceptAnswer("pe-0000150702", "Ngoài hoàng điểm mắt phải", concept15_7);	
		setNewConceptAnswer("pe-0000150703", "Dưới hoàng điểm mắt trái", concept15_7);
		setNewConceptAnswer("pe-0000150704", "Ngoài hoàng điểm mắt trái", concept15_7);
		// save
		conceptService.saveConcept(null, concept15_7);		
		
	}
	private void createGhiChuHeMach(Concept heMach) {
		Concept concept15_8 = new Concept();
		setConceptChild("pe-00001508", "Ghi chú mắt phải", heMach, concept15_8);
		Concept concept15_9 = new Concept();
		setConceptChild("pe-00001509", "Ghi chú mắt trái", heMach, concept15_9);
		conceptService.saveConcept(null, concept15_8);
		conceptService.saveConcept(null, concept15_9);		
	}

	//concept16
	private void createChuBienConcept(Concept parent) {
		Concept concept16 = new Concept();
		setConceptChild("pe-000016", "Chu biên", parent, concept16);
		Concept chubien=conceptService.getConceptByCode("pe-000016");
		//create child
		this.createNhoGianDongTuChuBien(chubien);
		this.createBinhThuongChuBienConcept(chubien);
		this.createBatThuongChuBienConcept(chubien);
		this.createGhiChuChuBienConcept(chubien);
	}
	private void createNhoGianDongTuChuBien(Concept chubien) {
		Concept concept16_1 = new Concept();
		setConceptQuestion("pe-00001601", "Nhỏ giãn đồng tử", chubien, concept16_1);
		// create concept answer
		setNewConceptAnswer("pe-0000160101", "Mắt phải", concept16_1);
		setNewConceptAnswer("pe-0000160102", "Mắt trái", concept16_1);
		// save
		conceptService.saveConcept(null, concept16_1);
	}
	private void createBinhThuongChuBienConcept(Concept parent) {
		// create concept question
		Concept concept16_2 = new Concept();
		setConceptQuestion("pe-00001602", "Bình thường", parent, concept16_2);
		// create concept answer
		setNewConceptAnswer("pe-0000160201", "Bình thường mắt phải", concept16_2);
		setNewConceptAnswer("pe-0000160202", "Bình thường mắt trái", concept16_2);
		// save
		conceptService.saveConcept(null, concept16_2);
	}
	private void createBatThuongChuBienConcept(Concept parent) {
		// create concept question
		Concept concept16_3 = new Concept();
		setConceptQuestion("pe-00001603", "Bình thường", parent, concept16_3);
		// create concept answer
		setNewConceptAnswer("pe-0000160301", "Attached mắt phải", concept16_3);
		setNewConceptAnswer("pe-0000160302", "Detached mắt phải", concept16_3);
		setNewConceptAnswer("pe-0000160303", "Drusen mắt phải", concept16_3);
		setNewConceptAnswer("pe-0000160304", "Attached mắt trái", concept16_3);
		setNewConceptAnswer("pe-0000160305", "Detached mắt trái", concept16_3);
		setNewConceptAnswer("pe-0000160306", "Drusen mắt trái", concept16_3);
		// save
		conceptService.saveConcept(null, concept16_3);
	}
	private void createGhiChuChuBienConcept(Concept parent) {
		Concept concept16_4 = new Concept();
		setConceptChild("pe-00001604", "Ghi chú mắt phải", parent, concept16_4);
		Concept concept16_5 = new Concept();
		setConceptChild("pe-00001605", "Ghi chú mắt trái", parent, concept16_5);
		conceptService.saveConcept(null, concept16_4);
		conceptService.saveConcept(null, concept16_5);
	}
	
	private void createNhanCauConcept(Concept parent) {
		Concept concept17=new Concept();
		setConceptChild("pe-000017", "Nhãn cầu", parent, concept17);
		Concept nhanCau=conceptService.getConceptByCode("pe-000017");
		//create child
		this.createNhoGianDongTuNhanCauConcept(nhanCau);
		this.createBinhThuongNhanCauConcept(nhanCau);
		this.createHinhThaiLacNhanCauConcept(nhanCau);
		this.createRungGiatNhanCauConcept(nhanCau);
		this.createSongThiNhanCauConcept(nhanCau);
		this.createGhiChuNhanCau(nhanCau);
	}
	
	private void createNhoGianDongTuNhanCauConcept(Concept parent) {
		Concept concept17_1 = new Concept();
		setConceptQuestion("pe-00001701", "Nhỏ giãn đồng tử", parent, concept17_1);
		// create concept answer
		setNewConceptAnswer("pe-0000170101", "Mắt phải", concept17_1);
		setNewConceptAnswer("pe-0000170102", "Mắt trái", concept17_1);
		// save
		conceptService.saveConcept(null, concept17_1);
	}
	private void createBinhThuongNhanCauConcept(Concept parent) {
		// create concept question
		Concept concept17_2 = new Concept();
		setConceptQuestion("pe-00001702", "Bình thường", parent, concept17_2);
		// create concept answer
		setNewConceptAnswer("pe-0000170201", "Bình thường mắt phải", concept17_2);
		setNewConceptAnswer("pe-0000170202", "Bình thường mắt trái", concept17_2);
		// save
		conceptService.saveConcept(null, concept17_2);
	}
	private void createHinhThaiLacNhanCauConcept(Concept parent) {
		Concept concept17_3=new Concept();
		setConceptQuestion("pe-00001703", "Hình thái lác", parent, concept17_3);
		//create anser
		setNewConceptAnswer("pe-0000170301", "Lác trong mắt phải", concept17_3);
		setNewConceptAnswer("pe-0000170302", "Lác ngoài mắt phải", concept17_3);
		setNewConceptAnswer("pe-0000170303", "Lác chéo mắt phải", concept17_3);
		setNewConceptAnswer("pe-0000170304", "Lác trong mắt trái", concept17_3);
		setNewConceptAnswer("pe-0000170305", "Lác ngoài mắt trá", concept17_3);
		setNewConceptAnswer("pe-0000170306", "Lác chéo mắt trái", concept17_3);
		conceptService.saveConcept(null, concept17_3);
	}
	private void createRungGiatNhanCauConcept(Concept parent) {
		Concept concept17_4=new Concept();
		setConceptQuestion("pe-00001704", "Rung giật nhãn cầu", parent, concept17_4);
		//create anser
		setNewConceptAnswer("pe-0000170401", "Mắt phải", concept17_4);
		setNewConceptAnswer("pe-0000170402", "Mắt trái", concept17_4);
		conceptService.saveConcept(null, concept17_4);
	}
	private void createSongThiNhanCauConcept(Concept parent) {
		Concept concept17_5=new Concept();
		setConceptQuestion("pe-00001705", "Song thị", parent, concept17_5);
		//create anser
		setNewConceptAnswer("pe-0000170501", "Mắt phải", concept17_5);
		setNewConceptAnswer("pe-0000170502", "Mắt trái", concept17_5);
		conceptService.saveConcept(null, concept17_5);
	}
	private void createGhiChuNhanCau(Concept parent) {
		Concept concept17_6=new Concept();
		setConceptChild("pe-00001706","Ghi chú mắt phải", parent, concept17_6);
		Concept concept17_7=new Concept();
		setConceptChild("pe-00001707","Ghi chú mắt trái", parent, concept17_7);
		conceptService.saveConcept(null, concept17_6);
		conceptService.saveConcept(null, concept17_7);
	}

	private void createVanNhanConcept(Concept parent) {
		//create parent 
		Concept concept18 = new Concept();
		setConceptChild("pe-000018", "Vận nhãn", parent, concept18);
		Concept vanNhan = conceptService.getConceptByCode("pe-000018");
		//create child
		this.createNhoGianDongTuVanNhanConcept(vanNhan);
		this.createBinhThuongVanNhanConcept(vanNhan);
		this.createBatThuongVanNhanConcept(vanNhan);
		this.createGhiChuVanNhanConcept(vanNhan);
	}

	private void createNhoGianDongTuVanNhanConcept(Concept parent) {
		// create concept question
		Concept concept18_1 = new Concept();
		setConceptQuestion("pe-00001801", "Nhỏ giãn đồng tử", parent, concept18_1);
		// create concept answer
		setNewConceptAnswer("pe-0000180101", "Mắt phải", concept18_1);
		setNewConceptAnswer("pe-0000180102", "Mắt trái", concept18_1);
		// save
		conceptService.saveConcept(null, concept18_1);
	}

	private void createBinhThuongVanNhanConcept(Concept parent) {
		// create concept question
		Concept concept18_2 = new Concept();
		setConceptQuestion("pe-00001802", "Bình thường", parent, concept18_2);
		// create concept answer
		setNewConceptAnswer("pe-0000180201", "Bình thường mắt phải", concept18_2);
		setNewConceptAnswer("pe-0000180202", "Bình thường mắt trái", concept18_2);
		// save
		conceptService.saveConcept(null, concept18_2);
	}
	
	private void createBatThuongVanNhanConcept(Concept parent) {
		// create concept question
		Concept concept18_3 = new Concept();
		setConceptQuestion("pe-00001803", "Bất thường", parent, concept18_3);
		// create concept answer
		setNewConceptAnswer("pe-0000180301", "Bình thường mắt phải", concept18_3);
		setNewConceptAnswer("pe-0000180302", "Bình thường mắt trái", concept18_3);
		// save
		conceptService.saveConcept(null, concept18_3);
	}
	
	private void createGhiChuVanNhanConcept(Concept parent) {
		// create concept question
		Concept concept18_4 = new Concept();
		setConceptChild("pe-00001804", "Ghi chú mắt phải", parent, concept18_4);
		Concept concept18_5 = new Concept();
		setConceptChild("pe-00001805", "Ghi chú mắt trái", parent, concept18_5);
		// save
		conceptService.saveConcept(null, concept18_4);
		conceptService.saveConcept(null, concept18_5);
	}
	
	private void createHocMatConcept(Concept parent) {
		//create parent 
		Concept concept19 = new Concept();
		setConceptChild("pe-000019", "Hốc mắt", parent, concept19);
		Concept hocMat = conceptService.getConceptByCode("pe-000019");
		//create child
		this.createNhoGianDongTuHocMatConcept(hocMat);
		this.createBinhThuongHocMatConcept(hocMat);
		this.createBatThuongHocMatConcept(hocMat);
		this.createGhiChuHocMatConcept(hocMat);
	}

	private void createNhoGianDongTuHocMatConcept(Concept parent) {
		// create concept question
		Concept concept19_1 = new Concept();
		setConceptQuestion("pe-00001901", "Nhỏ giãn đồng tử hốc mắt", parent, concept19_1);
		// create concept answer
		setNewConceptAnswer("pe-0000190101", "Nhỏ giãn đồng tử hốc mắt mắt phải", concept19_1);
		setNewConceptAnswer("pe-0000190102", "Nhỏ giãn đồng tử hốc mắt mắt trái", concept19_1);
		// save
		conceptService.saveConcept(null, concept19_1);
	}

	private void createBinhThuongHocMatConcept(Concept parent) {
		// create concept question
		Concept concept19_2 = new Concept();
		setConceptQuestion("pe-00001902", "Bình thường", parent, concept19_2);
		// create concept answer
		setNewConceptAnswer("pe-0000190201", "Bình thường mắt phải", concept19_2);
		setNewConceptAnswer("pe-0000190202", "Bình thường mắt trái", concept19_2);
		// save
		conceptService.saveConcept(null, concept19_2);
	}
	
	private void createBatThuongHocMatConcept(Concept parent) {
		// create concept question
		Concept concept19_3 = new Concept();
		setConceptQuestion("pe-00001903", "Bình thường", parent, concept19_3);
		// create concept answer
		setNewConceptAnswer("pe-0000190301", "Bất thường mắt phải", concept19_3);
		setNewConceptAnswer("pe-0000190302", "Bất thường mắt trái", concept19_3);
		// save
		conceptService.saveConcept(null, concept19_3);
	}
	
    private void createGhiChuHocMatConcept(Concept parent) {
    	// create concept question
		Concept concept19_4 = new Concept();
		setConceptChild("pe-00001904", "Ghi chú mắt phải", parent, concept19_4);
		Concept concept19_5 = new Concept();
		setConceptChild("pe-00001905", "Ghi chú mắt trái", parent, concept19_5);
		// save
    	conceptService.saveConcept(null, concept19_4);
    	conceptService.saveConcept(null, concept19_5);
    }
    
    private void createTheMiConcept(Concept parent) {
		//create parent 
		Concept concept20 = new Concept();
		setConceptChild("pe-000020", "Thể mi", parent, concept20);
		Concept theMi = conceptService.getConceptByCode("pe-000020");
		//create child
		this.createNhoGianDongTuTheMiConcept(theMi);
	    this.createViTriViemTheMiConcept(theMi);
	    this.createGhiChuTheMiConcept(theMi);
    }
    private void createNhoGianDongTuTheMiConcept(Concept parent) {
    	// create concept question
    	Concept concept20_1 = new Concept();
    	setConceptQuestion("pe-00002001", "Nhỏ giãn đồng tử", parent, concept20_1);
    	// create concept answer
    	setNewConceptAnswer("pe-0000200101", "Nhỏ giãn đồng tử mắt phải", concept20_1);
    	setNewConceptAnswer("pe-0000200102", "Nhỏ giãn đồng tử mắt trái", concept20_1);
    	// save
    	conceptService.saveConcept(null, concept20_1);
    }	
    private void createViTriViemTheMiConcept(Concept parent) {
    	// create concept question
    	Concept concept20_2 = new Concept();
    	setConceptQuestion("pe-00002002","Vị trí viêm" , parent, concept20_2);
    	// create concept answer
    	setNewConceptAnswer("pe-0000200201", "Viêm thể mi sau mắt phải", concept20_2);
    	setNewConceptAnswer("pe-0000200202", "Mống mắt-thể mi mắt phải", concept20_2);
    	setNewConceptAnswer("pe-0000200203", "Viêm thể mi sau mắt trái", concept20_2);
    	setNewConceptAnswer("pe-0000200204", "Mống mắt-thể mi mắt trái", concept20_2);
    	// save
    	conceptService.saveConcept(null, concept20_2);
    }
    private void createGhiChuTheMiConcept(Concept parent) {
    	Concept concept20_3 = new Concept();
    	setConceptChild("pe-00002003","Ghi chú mắt phải", parent, concept20_3);
    	Concept concept20_4 = new Concept();
    	setConceptChild("pe-00002004", "Ghi chú mắt trái", parent, concept20_4);
    	// save
    	conceptService.saveConcept(null, concept20_3);
    	conceptService.saveConcept(null, concept20_4);
    }
	
}
