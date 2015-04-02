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
 * Copyright 2012-2015 Open Justice Broker Consortium
 */
package org.ojbc.xslt;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.transform.sax.SAXSource;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ojbc.util.xml.XmlUtils;
import org.xml.sax.InputSource;

public class ArrestDocumentTransformerServiceTest {

	XsltTransformerService unit;

	@Before
	public void setup() {
		unit = new XsltTransformerService();
	}
	
	@After
	public void tearDown() {
		unit = null;
	}	

	@Test
	public void arrestDocumentTransform() throws Exception{
				
		String xml = XmlUtils.getRootNodeAsString("src/test/resources/xmlInstances/arrestDocument/ArrestDocument.xml");
				
		String xslt = FileUtils.readFileToString(new File("src/main/resources/xslt/arrestDocumentToNotification.xsl"));

		transformAndValidate(xslt, xml,"src/test/resources/xmlInstances/output/Notification.xml", null);
	}

	@Test
	public void arrestDocumentMultipleChargesTransform() throws Exception{
		
		String xml = XmlUtils.getRootNodeAsString("src/test/resources/xmlInstances/arrestDocument/arrestMultipleCharges.xml"); 
												
		String xslt = FileUtils.readFileToString(new File("src/main/resources/xslt/arrestDocumentToNotification.xsl"));

		transformAndValidate(xslt, xml,"src/test/resources/xmlInstances/output/notificationMultipleCharges.xml", null);		
	}

	@Test
	public void arrestDocumentBookingDateOnlyNoTime() throws Exception{
		
		String xml = XmlUtils.getRootNodeAsString("src/test/resources/xmlInstances/arrestDocument/ArrestDocumentBookingDateOnly.xml"); 						
		
		String xslt = FileUtils.readFileToString(new File("src/main/resources/xslt/arrestDocumentToNotification.xsl"));

		transformAndValidate(xslt, xml,"src/test/resources/xmlInstances/output/NotificationBookingDateOnly.xml", null);		
	}


	@SuppressWarnings("unchecked")
	private void transformAndValidate(String xslPath, String inputXmlPath, String expectedResultsFilePath, 
			Map<String,Object> params) throws Exception {
				
		String sXmlRoot = XmlUtils.getRootNodeAsString(expectedResultsFilePath);
		
		String transformedXml = unit.transform(createSource(inputXmlPath), createSource(xslPath), params);		
		
		Diff diff = new Diff(sXmlRoot, transformedXml);
		
		DetailedDiff detailedDiff = new DetailedDiff(diff);
		
		List<Difference> difList = detailedDiff.getAllDifferences();
		
		assertEquals(detailedDiff.toString(), 0, difList.size());		
	}

	private SAXSource createSource(String xml) {
		InputSource inputSource = new InputSource(new ByteArrayInputStream(xml.getBytes()));
		inputSource.setEncoding(org.apache.commons.lang.CharEncoding.UTF_8);
		return new SAXSource(inputSource);
	}
	
}

