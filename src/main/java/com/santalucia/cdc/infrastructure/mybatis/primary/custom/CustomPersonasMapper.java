package com.santalucia.cdc.infrastructure.mybatis.primary.custom;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface CustomPersonasMapper {

  @Select("select * from personas limit #{limit}")
  List<Personas> getPersonasData(Integer limit);
}
