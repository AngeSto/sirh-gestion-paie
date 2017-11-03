package dev.paie.service;



import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.paie.entite.Grade;

public class GradeMapper implements RowMapper <Grade>{

	@Override
	public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
		Grade grade = new Grade();
		grade.setId(rs.getInt("ID"));
		grade.setCode(rs.getString("Code"));
		grade.setNbHeuresBase(rs.getBigDecimal("nbHeuresDeBase"));
		grade.setTauxBase(rs.getBigDecimal("TauxBase"));

		return grade;
	}

}