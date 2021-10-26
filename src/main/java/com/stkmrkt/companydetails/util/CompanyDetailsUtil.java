package com.stkmrkt.companydetails.util;

import static com.stkmrkt.companydetails.constants.Constants.COMPANY_SERVICE_ERROR_XML_FILE_PATH;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.stkmrkt.companydetails.model.CompanyServiceErrors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompanyDetailsUtil {

	public HttpHeaders getHttpHeaders() {
		log.debug("Generating headers for API Call");
		HttpHeaders httpHeaders = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(acceptableMediaTypes);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}

	public String getResponseId() {
		long responseId = System.currentTimeMillis();
		log.info("Response ID : {}", responseId);
		return String.valueOf(responseId);
	}

	public Object convertFromXMLToObject() {
		try {
			JAXBContext context = JAXBContext.newInstance(CompanyServiceErrors.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			InputStream stream = this.getClass().getResourceAsStream(COMPANY_SERVICE_ERROR_XML_FILE_PATH);
			return unmarshaller.unmarshal(stream);
		}catch (Exception e) {
			log.error("Exception occurred during unmarshalling the error xml file : {}",e);
			return null;
		}
	}
}
