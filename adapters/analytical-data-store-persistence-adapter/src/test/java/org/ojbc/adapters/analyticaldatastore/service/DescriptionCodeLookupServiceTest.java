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
package org.ojbc.adapters.analyticaldatastore.service;

import static junit.framework.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ojbc.adapters.analyticaldatastore.dao.model.CodeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:META-INF/spring/dao.xml",
		"classpath:META-INF/spring/properties-context.xml"
		})
@DirtiesContext
public class DescriptionCodeLookupServiceTest {
	//private final Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private DescriptionCodeLookupService descriptionCodeLookupService;
	
	@Before
	public void setUp() throws Exception {
		assertNotNull(descriptionCodeLookupService);
	}

	@Test
	public void test(){
		assertEquals(Long.valueOf(1), descriptionCodeLookupService.retrieveCode(CodeTable.PersonRace, "A"));
		assertEquals(Long.valueOf(2), descriptionCodeLookupService.retrieveCode(CodeTable.PersonSex, "F"));
		assertNull(descriptionCodeLookupService.retrieveCode(CodeTable.PersonSex, "W"));
		assertEquals(Long.valueOf(3), descriptionCodeLookupService.retrieveCode(CodeTable.County, "Caledonia"));
		assertEquals(Long.valueOf(5), descriptionCodeLookupService.retrieveCode(CodeTable.AssessedNeed, "substance abuse"));
	}

}
