package org.camunda.community.benchmarks;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@Deployment(resources = "classpath:bpmn/*.bpmn")
class BenchmarkApplication  {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BenchmarkApplication.class, args);

        // Trigger here to make sure it happens AFTER the ApplicationContext is ready
        context.getBean(ProcessDeployer.class).autoDeploy();
        context.getBean(JobWorker.class).startWorkers();
    }

}