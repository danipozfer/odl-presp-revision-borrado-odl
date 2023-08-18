package com.santalucia.cdc.infrastructure.mybatis.primary;

import static com.santalucia.cdc.infrastructure.mybatis.primary.PersonasDynamicSqlSupport.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface PersonasMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Personas>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    BasicColumn[] selectList = BasicColumn.columnList(nidenper, nidpeaux, xtperso, xtdoiden, ndoiden, dnombre, dapell1, dapell2, fnacimie, xsexo, cnaciona, cagencia, xnivddpe, fregilog);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PersonasResult", value = {
        @Result(column="nidenper", property="nidenper", jdbcType=JdbcType.DECIMAL),
        @Result(column="nidpeaux", property="nidpeaux", jdbcType=JdbcType.DECIMAL),
        @Result(column="xtperso", property="xtperso", jdbcType=JdbcType.CHAR),
        @Result(column="xtdoiden", property="xtdoiden", jdbcType=JdbcType.CHAR),
        @Result(column="ndoiden", property="ndoiden", jdbcType=JdbcType.CHAR),
        @Result(column="dnombre", property="dnombre", jdbcType=JdbcType.CHAR),
        @Result(column="dapell1", property="dapell1", jdbcType=JdbcType.CHAR),
        @Result(column="dapell2", property="dapell2", jdbcType=JdbcType.CHAR),
        @Result(column="fnacimie", property="fnacimie", jdbcType=JdbcType.DATE),
        @Result(column="xsexo", property="xsexo", jdbcType=JdbcType.CHAR),
        @Result(column="cnaciona", property="cnaciona", jdbcType=JdbcType.CHAR),
        @Result(column="cagencia", property="cagencia", jdbcType=JdbcType.SMALLINT),
        @Result(column="xnivddpe", property="xnivddpe", jdbcType=JdbcType.CHAR),
        @Result(column="fregilog", property="fregilog", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Personas> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PersonasResult")
    Optional<Personas> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, personas, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, personas, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    default int insert(Personas row) {
        return MyBatis3Utils.insert(this::insert, row, personas, c ->
            c.map(nidenper).toProperty("nidenper")
            .map(nidpeaux).toProperty("nidpeaux")
            .map(xtperso).toProperty("xtperso")
            .map(xtdoiden).toProperty("xtdoiden")
            .map(ndoiden).toProperty("ndoiden")
            .map(dnombre).toProperty("dnombre")
            .map(dapell1).toProperty("dapell1")
            .map(dapell2).toProperty("dapell2")
            .map(fnacimie).toProperty("fnacimie")
            .map(xsexo).toProperty("xsexo")
            .map(cnaciona).toProperty("cnaciona")
            .map(cagencia).toProperty("cagencia")
            .map(xnivddpe).toProperty("xnivddpe")
            .map(fregilog).toProperty("fregilog")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    default int insertMultiple(Collection<Personas> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, personas, c ->
            c.map(nidenper).toProperty("nidenper")
            .map(nidpeaux).toProperty("nidpeaux")
            .map(xtperso).toProperty("xtperso")
            .map(xtdoiden).toProperty("xtdoiden")
            .map(ndoiden).toProperty("ndoiden")
            .map(dnombre).toProperty("dnombre")
            .map(dapell1).toProperty("dapell1")
            .map(dapell2).toProperty("dapell2")
            .map(fnacimie).toProperty("fnacimie")
            .map(xsexo).toProperty("xsexo")
            .map(cnaciona).toProperty("cnaciona")
            .map(cagencia).toProperty("cagencia")
            .map(xnivddpe).toProperty("xnivddpe")
            .map(fregilog).toProperty("fregilog")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    default int insertSelective(Personas row) {
        return MyBatis3Utils.insert(this::insert, row, personas, c ->
            c.map(nidenper).toPropertyWhenPresent("nidenper", row::getNidenper)
            .map(nidpeaux).toPropertyWhenPresent("nidpeaux", row::getNidpeaux)
            .map(xtperso).toPropertyWhenPresent("xtperso", row::getXtperso)
            .map(xtdoiden).toPropertyWhenPresent("xtdoiden", row::getXtdoiden)
            .map(ndoiden).toPropertyWhenPresent("ndoiden", row::getNdoiden)
            .map(dnombre).toPropertyWhenPresent("dnombre", row::getDnombre)
            .map(dapell1).toPropertyWhenPresent("dapell1", row::getDapell1)
            .map(dapell2).toPropertyWhenPresent("dapell2", row::getDapell2)
            .map(fnacimie).toPropertyWhenPresent("fnacimie", row::getFnacimie)
            .map(xsexo).toPropertyWhenPresent("xsexo", row::getXsexo)
            .map(cnaciona).toPropertyWhenPresent("cnaciona", row::getCnaciona)
            .map(cagencia).toPropertyWhenPresent("cagencia", row::getCagencia)
            .map(xnivddpe).toPropertyWhenPresent("xnivddpe", row::getXnivddpe)
            .map(fregilog).toPropertyWhenPresent("fregilog", row::getFregilog)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    default Optional<Personas> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, personas, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    default List<Personas> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, personas, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    default List<Personas> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, personas, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, personas, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    static UpdateDSL<UpdateModel> updateAllColumns(Personas row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(nidenper).equalTo(row::getNidenper)
                .set(nidpeaux).equalTo(row::getNidpeaux)
                .set(xtperso).equalTo(row::getXtperso)
                .set(xtdoiden).equalTo(row::getXtdoiden)
                .set(ndoiden).equalTo(row::getNdoiden)
                .set(dnombre).equalTo(row::getDnombre)
                .set(dapell1).equalTo(row::getDapell1)
                .set(dapell2).equalTo(row::getDapell2)
                .set(fnacimie).equalTo(row::getFnacimie)
                .set(xsexo).equalTo(row::getXsexo)
                .set(cnaciona).equalTo(row::getCnaciona)
                .set(cagencia).equalTo(row::getCagencia)
                .set(xnivddpe).equalTo(row::getXnivddpe)
                .set(fregilog).equalTo(row::getFregilog);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Personas row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(nidenper).equalToWhenPresent(row::getNidenper)
                .set(nidpeaux).equalToWhenPresent(row::getNidpeaux)
                .set(xtperso).equalToWhenPresent(row::getXtperso)
                .set(xtdoiden).equalToWhenPresent(row::getXtdoiden)
                .set(ndoiden).equalToWhenPresent(row::getNdoiden)
                .set(dnombre).equalToWhenPresent(row::getDnombre)
                .set(dapell1).equalToWhenPresent(row::getDapell1)
                .set(dapell2).equalToWhenPresent(row::getDapell2)
                .set(fnacimie).equalToWhenPresent(row::getFnacimie)
                .set(xsexo).equalToWhenPresent(row::getXsexo)
                .set(cnaciona).equalToWhenPresent(row::getCnaciona)
                .set(cagencia).equalToWhenPresent(row::getCagencia)
                .set(xnivddpe).equalToWhenPresent(row::getXnivddpe)
                .set(fregilog).equalToWhenPresent(row::getFregilog);
    }
}
