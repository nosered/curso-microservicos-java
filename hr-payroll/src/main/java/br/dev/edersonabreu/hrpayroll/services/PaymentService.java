package br.dev.edersonabreu.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.dev.edersonabreu.hrpayroll.entities.Payment;
import br.dev.edersonabreu.hrpayroll.entities.Worker;

@Service
public class PaymentService {
	
	@Value(value = "${hr-worker.host}")
	private String workerHost;
	
	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(Long workerId, Integer days) {
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", workerId.toString());
		
		String uri = new StringBuilder()
							.append(workerHost)
							.append("/workers")
							.append("/{id}")
							.toString();
		
		Worker worker = restTemplate.getForObject(uri, Worker.class, uriVariables);
		
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}
