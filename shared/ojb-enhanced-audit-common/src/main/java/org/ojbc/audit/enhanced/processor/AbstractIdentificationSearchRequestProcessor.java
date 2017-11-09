/*
 * Unless explicitly acquired and licensed from Licensor under another license, the contents of
 * this file are subject to the Reciprocal Public License ("RPL") Version 1.5, or subsequent
 * versions as allowed by the RPL, and You may not copy or use this file in either source code
 * or executable form, except in compliance with the terms and conditions of the RPL
 *
 * All software distributed under the RPL is provided strictly on an "AS IS" basis, WITHOUT
 * WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, AND LICENSOR HEREBY DISCLAIMS ALL SUCH
 * WARRANTIES, INCLUDING WITHOUT LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, QUIET ENJOYMENT, OR NON-INFRINGEMENT. See the RPL for specific language
 * governing rights and limitations under the RPL.
 *
 * http://opensource.org/licenses/RPL-1.5
 *
 * Copyright 2012-2017 Open Justice Broker Consortium
 */
package org.ojbc.audit.enhanced.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ojbc.audit.enhanced.dao.model.IdentificationSearchRequest;
import org.ojbc.util.xml.XmlUtils;
import org.w3c.dom.Document;

public abstract class AbstractIdentificationSearchRequestProcessor {

	private static final Log log = LogFactory.getLog(AbstractIdentificationSearchRequestProcessor.class);
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public abstract void auditIdentificationSearchRequest(Exchange exchange, @Body Document document);
	
	IdentificationSearchRequest processIdentificationSearchRequest(Document document) throws Exception
	{
		XmlUtils.printNode(document);
		
		IdentificationSearchRequest identificationSearchRequest = new IdentificationSearchRequest();

        String givenName = XmlUtils.xPathStringSearch(document, 
        		"/oirs-req-doc:OrganizationIdentificationResultsSearchRequest/nc30:Person/nc30:PersonName/nc30:PersonGivenName");
        
        if(StringUtils.isNotBlank(givenName)){
        	identificationSearchRequest.setFirstName(givenName);
        }

        String surName = XmlUtils.xPathStringSearch(document, 
        		"/oirs-req-doc:OrganizationIdentificationResultsSearchRequest/nc30:Person/nc30:PersonName/nc30:PersonSurName");
        
        if(StringUtils.isNotBlank(surName)){
        	identificationSearchRequest.setLastName(surName);
        }

        String identificationResultStatusCode = XmlUtils.xPathStringSearch(document, 
        		"/oirs-req-doc:OrganizationIdentificationResultsSearchRequest/oirs-req-ext:IdentificationResultStatusCode");
        
        if(StringUtils.isNotBlank(identificationResultStatusCode)){
        	identificationSearchRequest.setIdentificationResultsStatus(identificationResultStatusCode);
        }

        String otn = XmlUtils.xPathStringSearch(document, 
        		"/oirs-req-doc:OrganizationIdentificationResultsSearchRequest/nc30:Person/oirs-req-ext:IdentifiedPersonTrackingIdentification/nc30:IdentificationID");
        
        if(StringUtils.isNotBlank(otn)){
        	identificationSearchRequest.setOtn(otn);
        }

        String startDate = XmlUtils.xPathStringSearch(document, 
        		" /oirs-req-doc:OrganizationIdentificationResultsSearchRequest/oirs-req-ext:IdentificationReportedDateRange/nc30:StartDate/nc30:Date");
        
        if(StringUtils.isNotBlank(startDate)){
        	identificationSearchRequest.setReportedFromDate(LocalDate.parse(startDate, formatter));
        }

        String endDate = XmlUtils.xPathStringSearch(document, 
        		" /oirs-req-doc:OrganizationIdentificationResultsSearchRequest/oirs-req-ext:IdentificationReportedDateRange/nc30:EndDate/nc30:Date");
        
        if(StringUtils.isNotBlank(endDate)){
        	identificationSearchRequest.setReportedToDate(LocalDate.parse(endDate, formatter));
        }
        
        //TODO: Process Reason code in a seperate table
        
        
        return identificationSearchRequest;
	}

}