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

INSERT into FEDERAL_RAPBACK_SUBSCRIPTION 
     (REQUEST_SENT_TIMESTAMP, RESPONSE_RECIEVED_TIMESTAMP, TRANSACTION_CONTROL_REFERENCE_IDENTIFICATION, PATH_TO_REQUEST_FILE,SUBSCRIPTION_CATEGORY_CODE, SID, 
     TRANSACTION_STATUS_TEXT, STATE_SUBSCRIPTION_ID, TRANSACTION_CATEGORY_CODE_REQUEST, TRANSACTION_CATEGORY_CODE_RESPONSE, FBI_SUBSCRIPTION_ID)
	values ('2017-12-12', '2017-12-13', '9999999', '/some/path/to/requestFile', 'CS', 'SID123', 'This is an FBI error', '62729', 'RBSCRM', 'RBSR', 'FBISUB789');
INSERT into FEDERAL_RAPBACK_SUBSCRIPTION values (2, '59c4496305b342b79aebca21e85f6250', 'RBSCRM', 'RBSR', '/tmp/ojb/adapter/FbiEbts/output/fbiSubscriptions/requests/FBI_Subscription_Request_59c4496305b342b79aebca21e85f6250.xml'
	'/tmp/ojb/adapter/FbiEbts/output/fbiSubscriptions/responses/FBI_Subscription_Response_59c4496305b342b79aebca21e85f6250.xml', 
	'CI', '62729', '123457', 'A1643126', 'MATCH MADE AGAINST SUBJECTS FINGERPRINTS ON 05/01/94. PLEASE NOTIFY SUBMITTING STATE IF MATCH RESULTS', {ts '2018-04-12 11:21:10.22'}, {ts '2018-04-12 11:21:10.372'});	
	
INSERT into FEDERAL_RAPBACK_SUBSCRIPTION_ERRORS 
       (FEDERAL_RAPBACK_SUBSCRIPTION_ID, ERROR_REPORTED, STATE_SUBSCRIPTION_ID, ERROR_RESOLVED)
        		values (1, true, '62729', false);	
        		
INSERT into FEDERAL_RAPBACK_NOTIFICATION 
        (PATH_TO_NOTIFICATION_FILE, STATE_SUBSCRIPTION_ID, RAPBACK_EVENT_TEXT, ORIGINAL_IDENTIFIER, UPDATED_IDENTIFIER, TRANSACTION_TYPE, NOTIFICATION_RECIEVED_TIMESTAMP)
    		values ('/tmp/path/toNotificationFile', '62729', 'Rapback event text', '123', '456', 'NOTIFICATION_MATCHING_SUBSCRIPTION', CURRENT_TIMESTAMP());
        		
INSERT into TRIGGERING_EVENTS_JOINER
		(FEDERAL_RAPBACK_NOTIFICATION_ID, TRIGGERING_EVENTS_ID)
        	values (1, 1);       		
INSERT into TRIGGERING_EVENTS_JOINER
		(FEDERAL_RAPBACK_NOTIFICATION_ID, TRIGGERING_EVENTS_ID)
        	values (1, 2);       		