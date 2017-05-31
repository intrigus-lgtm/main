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
/*
    Unless explicitly acquired and licensed from Licensor under another license, the contents of
    this file are subject to the Reciprocal Public License ("RPL") Version 1.5, or subsequent
    versions as allowed by the RPL, and You may not copy or use this file in either source code
    or executable form, except in compliance with the terms and conditions of the RPL
    All software distributed under the RPL is provided strictly on an "AS IS" basis, WITHOUT
    WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, AND LICENSOR HEREBY DISCLAIMS ALL SUCH
    WARRANTIES, INCLUDING WITHOUT LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE, QUIET ENJOYMENT, OR NON-INFRINGEMENT. See the RPL for specific language
    governing rights and limitations under the RPL.
    
    http://opensource.org/licenses/RPL-1.5
    
    Copyright 2012-2017 Open Justice Broker Consortium

*/

$(document).ready(
		function() {
			var table = $('#inmate-table').DataTable({
				select : {
					style : "single",
					blurable : true
				},
				info : false,
				lengthChange : false,
				data : demodata,
				"columns" : [ {
					"data" : "personLastName",
					"title" : "Last Name"
				}, {
					"data" : "personFirstName",
					"title" : "First Name"
				}, {
					"data" : "personDOBString",
					"title" : "Date of Birth"
				}, {
					"data" : "bookingNumber",
					"title" : "Booking Number"
				}, {
					"data" : "nameNumber",
					"title" : "Name Number"
				} ]
			});
			table.on('select', function(e, dt, type, indexes) {
				var rowData = table.rows(indexes[0]).data()["0"];
				$("#selected-patient-name")
						.text(
								rowData.personLastName + ", "
										+ rowData.personFirstName);
			})
			table.draw();

		});