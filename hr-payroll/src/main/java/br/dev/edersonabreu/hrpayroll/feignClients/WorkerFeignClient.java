package br.dev.edersonabreu.hrpayroll.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.dev.edersonabreu.hrpayroll.entities.Worker;

@Component
@FeignClient(name = "hr-worker", path = "/workers")
public interface WorkerFeignClient {

	@GetMapping
	ResponseEntity<List<Worker>> findAll();
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Worker> findById(@PathVariable(value = "id") Long id);
}
