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
package org.ojbc.web.portal.controllers.config;

import javax.annotation.Resource;

import org.ojbc.web.SubscriptionQueryInterface;
import org.ojbc.web.SubscriptionSearchInterface;
import org.ojbc.web.SubscriptionInterface;
import org.ojbc.web.SubscriptionValidationInterface;
import org.ojbc.web.UnsubscriptionInterface;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"subscriptions"})
public class SubscriptionsControllerConfigBrokered implements SubscriptionsControllerConfigInterface {

	
	@Resource(name="subscriptionSearchRequestProcessor")
	SubscriptionSearchInterface subscriptionSearchInterface;
	
	@Resource(name="subscriptionQueryRequestProcessor")
	SubscriptionQueryInterface subscriptionQueryInterface;
	
	@Resource(name="subscriptionRequestProcessor")
	SubscriptionInterface subscriptionSubscribeInterface; 

	@Resource(name="unsubscriptionRequestProcessor")
	UnsubscriptionInterface unsubscriptionSubscribeInterface; 
	
	@Resource(name ="subscriptionValidationRequestProcessor")
	SubscriptionValidationInterface subscriptionValidationInterface;

	
	@Override
	public SubscriptionSearchInterface getSubscriptionSearchBean() {		
		return subscriptionSearchInterface;
	}

	@Override
	public SubscriptionQueryInterface getSubscriptionQueryBean(){
		return subscriptionQueryInterface;
	}
	
	@Override
	public SubscriptionInterface getSubscriptionSubscribeBean() {
		return subscriptionSubscribeInterface;
	}

	@Override
	public UnsubscriptionInterface getUnsubscriptionBean() {
		return unsubscriptionSubscribeInterface;
	}

	@Override
	public SubscriptionValidationInterface getSubscriptionValidationBean() {
		return subscriptionValidationInterface;
	}
	
}
