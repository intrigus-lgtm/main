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

//import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.transform.sax.SAXSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
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
		String xml = FileUtils.readFileToString(new File( "src/test/resources/xmlInstances/arrestDocument/ArrestDocument.xml"));
		String xslt = FileUtils.readFileToString(new File("src/main/resources/xslt/arrestDocumentToNotification.xsl"));

		transformAndValidate(xslt, xml,"output/Notification.xml", null);
	}

	@Test
	public void arrestDocumentMultipleChargesTransform() throws Exception{
		String xml = FileUtils.readFileToString(new File( "src/test/resources/xmlInstances/arrestDocument/arrestMultipleCharges.xml"));
		String xslt = FileUtils.readFileToString(new File("src/main/resources/xslt/arrestDocumentToNotification.xsl"));

		transformAndValidate(xslt, xml,"output/notificationMultipleCharges.xml", null);
		
	}

	@Test
	public void arrestDocumentBookingDateOnlyNoTime() throws Exception{
		String xml = FileUtils.readFileToString(new File( "src/test/resources/xmlInstances/arrestDocument/ArrestDocumentBookingDateOnly.xml"));
		String xslt = FileUtils.readFileToString(new File("src/main/resources/xslt/arrestDocumentToNotification.xsl"));

		transformAndValidate(xslt, xml,"output/NotificationBookingDateOnly.xml", null);
		
	}


	@SuppressWarnings("unchecked")
	private void transformAndValidate(String xslPath, String inputXmlPath, String expectedHtmlPath, Map<String,Object> params) throws IOException {
		List<String> expectedXml = IOUtils.readLines(new ClassPathResource("xmlInstances/"+expectedHtmlPath).getInputStream(),
		        org.apache.commons.lang.CharEncoding.UTF_8);
		
		String convertResult = unit.transform(createSource(inputXmlPath), createSource(xslPath),params);
		
		assertLinesEquals(expectedXml, convertResult);

	}

	private SAXSource createSource(String xml) {
		InputSource inputSource = new InputSource(new ByteArrayInputStream(xml.getBytes()));
		inputSource.setEncoding(org.apache.commons.lang.CharEncoding.UTF_8);
		return new SAXSource(inputSource);
	}
	
	private void assertLinesEquals(List<String> expectedHtml, String convertedResult) {
		String[] split = convertedResult.split("\n");

		try {
			for (int i = 0; i < split.length; i++) {
				//assertThat("Line " + (i + 1) + " didn't match", split[i].trim(), is(expectedHtml.get(i).trim()));				
				assertEquals("Line " + (i + 1) + " didn't match",expectedHtml.get(i).trim(),split[i].trim());
			}
		} catch (AssertionError e) {

			System.out.println("----------------------------------------------");
			System.out.println(convertedResult);
			System.out.println("----------------------------------------------");
			throw e;
		}
	}
	
}
