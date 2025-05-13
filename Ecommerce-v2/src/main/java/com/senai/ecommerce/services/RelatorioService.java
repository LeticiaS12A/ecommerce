package com.senai.ecommerce.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.ecommerce.dto.RelatorioPedidoDTO;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.repositories.PedidoRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	public void gerarRelatorio(String caminho) throws JRException {
		// Busca todos os pedidos do banco de dados
		List<Pedido> pedidos = pedidoRepository.findAll();

		//Convertendo a lista de pedidos para relatorioPedidosDTO
		List<RelatorioPedidoDTO> escanor = pedidos.stream().map(RelatorioPedidoDTO::new)
				.collect(Collectors.toList());

		//Instanciei a lista de relatorios e passou tudo para escanor
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(escanor);

		//Criei o titulo
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("titulo", "Relatórios de Pedidos");

		//falando da onde ele vai puxar o molde e criar um relatoria da forma do molde
		JasperReport jasperReport = JasperCompileManager
				.compileReport(getClass().getResourceAsStream("/relatorios/relatorio_pedidos.jrxml"));

		//Ele mostra o relatorio
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

		//Ele transforma em pdf
		JasperExportManager.exportReportToPdfFile(jasperPrint, caminho);

	}

}
