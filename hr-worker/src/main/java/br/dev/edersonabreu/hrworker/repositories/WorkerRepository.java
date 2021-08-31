package br.dev.edersonabreu.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.edersonabreu.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}