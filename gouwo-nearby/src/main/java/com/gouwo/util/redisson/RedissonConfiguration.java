package com.gouwo.util.redisson;

import com.gouwo.pojo.RedissonProperties;
import io.micrometer.core.instrument.util.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: asky
 * @Date: 2021/3/5 18:25
 * @Desc:
 */
@Configuration
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonConfiguration {

    @Autowired
    private RedissonProperties redissonProperties;

    /**
     * 单机模式自动装配
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "redisson.address")
    RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redissonProperties.getAddress())
                .setTimeout(redissonProperties.getTimeout())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize())
                .setDatabase(redissonProperties.getDatabase());;

        if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }

        return Redisson.create(config);
    }

//    /**
//     * 哨兵模式自动装配
//     *
//     * @return
//     */
//    @Bean
//    @ConditionalOnProperty(name = "redisson.master-name")
//    RedissonClient redissonSentinel() {
//        Config config = new Config();
//        SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress(redissonProperties.getSentinelAddresses())
//                .setMasterName(redissonProperties.getMasterName())
//                .setTimeout(redissonProperties.getTimeout())
//                .setMasterConnectionPoolSize(redissonProperties.getMasterConnectionPoolSize())
//                .setSlaveConnectionPoolSize(redissonProperties.getSlaveConnectionPoolSize());
//
//        if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
//            serverConfig.setPassword(redissonProperties.getPassword());
//        }
//        return Redisson.create(config);
//    }

    /**
     * 装配locker类，并将实例注入到RedissonLockUtil中
     *
     * @return
     */
    @Bean
    DistributedLocker distributedLocker(RedissonClient redissonClient) {
        RedissonDistributedLocker locker = new RedissonDistributedLocker(redissonClient);
        RedissonLockUtil.setLocker(locker);
        return locker;
    }

}