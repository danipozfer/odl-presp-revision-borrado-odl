package com.santalucia.cdc;

import static com.santalucia.cdc.infrastructure.mybatis.primary.PersonasDynamicSqlSupport.nidenper;
import static com.santalucia.cdc.infrastructure.mybatis.primary.PersonasDynamicSqlSupport.personas;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mybatis.dynamic.sql.SqlBuilder.count;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlMergeMode;

import com.santalucia.arq.ams.componentes.database.properties.DatasourceProperties;
import com.santalucia.cdc.infrastructure.mybatis.primary.PersonasMapper;

@SpringBootTest
@SpringBatchTest
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql(scripts = {"/sql/schemas/personas-schema.sql"}, config = @SqlConfig(dataSource = DatasourceProperties.PRIMARY_DATASOURCE, transactionManager = DatasourceProperties.PRIMARY_TRANSACTION_MANAGER))
class BatchCursorTest {

  @Autowired
  private SqlSessionFactory sqlSessionFactory;

  @Test
  @Sql(scripts = { "/sql/data/personas-data.sql"}, config = @SqlConfig(dataSource = DatasourceProperties.PRIMARY_DATASOURCE, transactionManager = DatasourceProperties.PRIMARY_TRANSACTION_MANAGER))
  @DisplayName("Lectura de DB retorna correctamente el resultado")
  void assert_that_statements_return_correct_results() throws Exception {

    assertThat(selectAllPersonas()).isEqualTo(7L);

    Optional<Personas> optionalPersona = selectPersonaById();

    assertThat(optionalPersona).isPresent();

    Personas persona = optionalPersona.orElse(Personas.builder().build());

    assertThat(persona.getNidenper()).isEqualTo(1117L);
    assertThat(persona.getXsexo()).isEqualTo("H");
  }


  private Optional<Personas> selectPersonaById() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      PersonasMapper personMapper = sqlSession.getMapper(PersonasMapper.class);

      SelectStatementProvider selectStatement = SelectDSL.select(personas.allColumns())
          .from(personas)
          .where(nidenper, isEqualTo(1117L))
          .build()
          .render(RenderingStrategies.MYBATIS3);

      return personMapper.selectOne(selectStatement);
    }
  }

  private long selectAllPersonas() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      PersonasMapper personMapper = sqlSession.getMapper(PersonasMapper.class);

      SelectStatementProvider selectStatement = SelectDSL.select(count())
        .from(personas)
        .build()
        .render(RenderingStrategies.MYBATIS3);

      return personMapper.count(selectStatement);
    }
  }
}
