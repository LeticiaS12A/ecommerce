package com.senai.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.services.RelatorioService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("relatorios")
public class RelatorioController {
	
	@Autowired
	RelatorioService relatorioService;
	
	@GetMapping
	public ResponseEntity<String> gerarRelatoriosPDF(@RequestParam String caminho) throws JRException{
		relatorioService.gerarRelatorio(caminho);
		
		return ResponseEntity.ok("Relatorio gerado com sucesso" + caminho);
	}

}
