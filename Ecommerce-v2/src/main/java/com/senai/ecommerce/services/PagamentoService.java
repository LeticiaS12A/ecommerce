package com.senai.ecommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.ecommerce.entities.Pagamento;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.entities.StatusDoPedido;
import com.senai.ecommerce.repositories.PagamentoRepository;
import com.senai.ecommerce.repositories.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PagamentoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Transactional
    public void gerarPagamento(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));

        if (pedido.getStatus() == StatusDoPedido.PAGO) {
            throw new IllegalStateException("O pedido já está marcado como PAGO.");
        }

        // Atualiza o pedido para PAGO
        pedido.setStatus(StatusDoPedido.PAGO);
        pedido.setMomento(Instant.now());
        pedidoRepository.save(pedido);

        // Cria o pagamento associado
        Pagamento pagamento = new Pagamento(pedido);
        pagamentoRepository.save(pagamento);
    }
}
