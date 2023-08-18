package com.santalucia.cdc.infrastructure.mybatis.primary;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class PersonasDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    public static final Personas personas = new Personas();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.nidenper")
    public static final SqlColumn<Long> nidenper = personas.nidenper;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.nidpeaux")
    public static final SqlColumn<Long> nidpeaux = personas.nidpeaux;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.xtperso")
    public static final SqlColumn<String> xtperso = personas.xtperso;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.xtdoiden")
    public static final SqlColumn<String> xtdoiden = personas.xtdoiden;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.ndoiden")
    public static final SqlColumn<String> ndoiden = personas.ndoiden;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.dnombre")
    public static final SqlColumn<String> dnombre = personas.dnombre;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.dapell1")
    public static final SqlColumn<String> dapell1 = personas.dapell1;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.dapell2")
    public static final SqlColumn<String> dapell2 = personas.dapell2;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.fnacimie")
    public static final SqlColumn<Date> fnacimie = personas.fnacimie;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.xsexo")
    public static final SqlColumn<String> xsexo = personas.xsexo;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.cnaciona")
    public static final SqlColumn<String> cnaciona = personas.cnaciona;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.cagencia")
    public static final SqlColumn<Short> cagencia = personas.cagencia;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.xnivddpe")
    public static final SqlColumn<String> xnivddpe = personas.xnivddpe;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: personas.fregilog")
    public static final SqlColumn<Date> fregilog = personas.fregilog;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: personas")
    public static final class Personas extends AliasableSqlTable<Personas> {
        public final SqlColumn<Long> nidenper = column("nidenper", JDBCType.DECIMAL);

        public final SqlColumn<Long> nidpeaux = column("nidpeaux", JDBCType.DECIMAL);

        public final SqlColumn<String> xtperso = column("xtperso", JDBCType.CHAR);

        public final SqlColumn<String> xtdoiden = column("xtdoiden", JDBCType.CHAR);

        public final SqlColumn<String> ndoiden = column("ndoiden", JDBCType.CHAR);

        public final SqlColumn<String> dnombre = column("dnombre", JDBCType.CHAR);

        public final SqlColumn<String> dapell1 = column("dapell1", JDBCType.CHAR);

        public final SqlColumn<String> dapell2 = column("dapell2", JDBCType.CHAR);

        public final SqlColumn<Date> fnacimie = column("fnacimie", JDBCType.DATE);

        public final SqlColumn<String> xsexo = column("xsexo", JDBCType.CHAR);

        public final SqlColumn<String> cnaciona = column("cnaciona", JDBCType.CHAR);

        public final SqlColumn<Short> cagencia = column("cagencia", JDBCType.SMALLINT);

        public final SqlColumn<String> xnivddpe = column("xnivddpe", JDBCType.CHAR);

        public final SqlColumn<Date> fregilog = column("fregilog", JDBCType.TIMESTAMP);

        public Personas() {
            super("personas", Personas::new);
        }
    }
}
