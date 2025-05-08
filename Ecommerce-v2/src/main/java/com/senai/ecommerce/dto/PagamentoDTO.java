package com.senai.ecommerce.dto;

import java.time.Instant;

import com.senai.ecommerce.entities.Pagamento;

public class PagamentoDTO {
	
	private Long id;
	private Instant momento;
	private Long pedidoId;
	
    public PagamentoDTO() {
	}

	public PagamentoDTO(Long id, Instant momento, Long pedido) {
		this.id = id;
		this.momento = Instant.now();
		this.pedidoId = pedido;
	}
	public PagamentoDTO(Pagamento pagamento) {
		id = pagamento.getId();
		momento = pagamento.getMomento();
		pedidoId = pagamento.getPedido().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

}
