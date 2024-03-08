package org.camunda.community.benchmarks.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.community.benchmarks.JobWorker;
import org.camunda.community.benchmarks.ProcessDeployer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/process")
@RequiredArgsConstructor
public class StartController {

	@Autowired
	JobWorker jobWorker;

	@Autowired
	ProcessDeployer processDeployer;

	@PostMapping(value = "/start")
	public void startBenchmark() {
		processDeployer.autoDeploy();
		jobWorker.startWorkers();
	}
}
