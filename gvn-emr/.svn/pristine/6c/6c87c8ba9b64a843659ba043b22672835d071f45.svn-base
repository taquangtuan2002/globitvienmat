package com.globits.resourceserver.emr.listeners;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.globits.emr.service.SetupDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.globits.core.dto.PersonDto;
import com.globits.core.utils.CommonUtils;
import com.globits.security.domain.Role;
import com.globits.security.dto.RoleDto;
import com.globits.security.dto.UserDto;
import com.globits.security.service.RoleService;
import com.globits.security.service.UserService;

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {

	private static boolean eventFired = false;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private SetupDataService setupEmrDataService;
	
	@Autowired
	private Environment env;
//	@Autowired
//	private ResourceDefService resDefService;

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (eventFired) {
			return;
		}

		logger.info("Application started.");

		eventFired = true;

		setupEmrDataService.setupEmrData();

	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

}
