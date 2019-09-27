package com.shinelon.sharding.sphere.config.ds.sharding;


import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName DataSourceConfig
 * @Author syq
 * @Date 2019/7/22 14:23
 **/
@Configuration
@MapperScan("com.shinelon.sharding.sphere.mapper*")
public class PointsDataSourceConfig {
    private static final String DEV_MODEL = "dev";
    private static final Logger logger = LoggerFactory.getLogger(PointsDataSourceConfig.class);
    @Value("${points.datasource.dbModel}")
    private String dbModel;
    @Value("${points.datasource.dsName}")
    private String dsName;

    @Bean("points")
    @Primary
    @ConfigurationProperties(prefix = "points.datasource")
    public DataSource dataSourceInit() {
        logger.info("dataSourceConfi dbModel:{}==dsName:{}", dbModel, dsName);

        if (DEV_MODEL.equalsIgnoreCase(dbModel)) {
            // 开发环境
            return DataSourceBuilder.create().type(HikariDataSource.class).build();
        } else {
            // 生产 or 测试环境
//            DruidPooledDataSource dataSource = null;
//            try {
//                dataSource = new DruidPooledDataSource();
//                dataSource.setDataSourceName(dsName);
//            } catch (Exception e) {
//                logger.error("&&&&&&&&&&&&&&&&&&&&&&" + e.getMessage(), e);
//            }
//            logger.info("#################### points mysqlDatasource create success! ####################");
//            return dataSource;
            return null;
        }
    }

    @Bean("pointsJdbcTemplate")
    @Primary
    public JdbcTemplate getJdbcTemplate(@Qualifier("pointsSharding") DataSource dataSource) throws SQLException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setQueryTimeout(20);
        return jdbcTemplate;
    }
    @Bean("pointsPlatformTransactionManager")
    @Primary
    public PlatformTransactionManager txManager(@Qualifier("pointsSharding") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean("pointsSharding")
    public DataSource getDataSource(@Qualifier("points") DataSource dataSource) throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        String logicTable="pt_trade_common_order";
        List<String> actualDataNodesList= new ArrayList(5);
        actualDataNodesList.add("points.pt_trade_common_order_201909");
        actualDataNodesList.add("points.pt_trade_common_order_2019$->{10..12}");
        actualDataNodesList.add("points.pt_trade_common_order_20200$->{1..9}");
        actualDataNodesList.add("points.pt_trade_common_order_2020$->{10..12}");
        String actualDataNodes = actualDataNodesList.stream().collect(Collectors.joining(","));
        TableRuleConfiguration result = new TableRuleConfiguration(logicTable, actualDataNodes);
        shardingRuleConfig.getTableRuleConfigs().add(result);
        shardingRuleConfig.getBindingTableGroups().add("pt_trade_common_order");
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new HintShardingStrategyConfiguration(new DateHintShardingAlgorithm()));
        Map<String, DataSource> dsMap = new HashMap<>();
        dsMap.put("points",dataSource);
        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");
        return ShardingDataSourceFactory.createDataSource(dsMap, shardingRuleConfig, properties);
    }

    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    @Bean("pointsSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("pointsSharding") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean fb = new MybatisSqlSessionFactoryBean();
        fb.setDataSource(dataSource);
        fb.setTypeAliasesPackage("com.shinelon.sharding.sphere.entity");
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // mybatisConfiguration.setLogImpl(Slf4jImpl.class);
        fb.setConfiguration(mybatisConfiguration);
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/points/*.xml"));
        fb.setPlugins(new Interceptor[] { optimisticLockerInterceptor(), paginationInterceptor() });
        MetaObjectHandler metaObjectHandler = new ComMetaObjectHandler();
        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        globalConfig.setMetaObjectHandler(metaObjectHandler);
        fb.setGlobalConfig(globalConfig);
        // BaseJdbcLogger
        return fb.getObject();
    }
}
