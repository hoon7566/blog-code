package com.hoony.example.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableMBeanExport
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.config.EnableIntegrationManagement

@EnableIntegration
@Configuration
@EnableIntegrationManagement
@EnableMBeanExport
class MyIntegrationConfig(
) : Log {
}