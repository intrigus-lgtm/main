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
package org.ojbc.adapters.analyticaldatastore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.ojbc.adapters.analyticaldatastore.dao.model.CodeTable;
import org.ojbc.adapters.analyticaldatastore.dao.model.KeyValue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Data access implementations of all Firearms database code tables.
 * @since November 29, 2012
 */
@Repository
public class CodeTableDAOImpl implements CodeTableDAO
{

	private JdbcTemplate jdbcTemplate;
	
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public List<KeyValue> retrieveCodeDescriptions(CodeTable codeTable) {
		String sql = null;

		switch (codeTable) {
		
			case County:
			case PersonRace:
			case PersonSex:
			case InvolvedDrug: 
			case IncidentType: 
			case AssessedNeed: 
			case PretrialService: 
				sql = "SELECT * FROM " + codeTable.name();
				return jdbcTemplate.query(sql, new KeyValueRowMapper());
			default:
				return null;
		}
	}

	public class KeyValueRowMapper implements RowMapper<KeyValue> {
		public KeyValue mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new KeyValue(rs.getLong(1), rs.getString(2));
		}
	}


}
