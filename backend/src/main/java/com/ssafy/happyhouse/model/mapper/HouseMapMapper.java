package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {
	public List<SidoGugunCodeDto> getSido() throws SQLException;
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
}
