package com.kopring.boilerplate.common.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaConfig {
    @Bean
    fun matchMemberTopic(): NewTopic = TopicBuilder.name("match-member-topic").partitions(1).replicas(1).build()

}