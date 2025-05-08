package com.senai.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.services.PagamentoService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/gerar/{pedidoId}")
    public ResponseEntity<String> gerarPagamento(@PathVariable Long pedidoId) {
        try {
            pagamentoService.gerarPagamento(pedidoId);
            return ResponseEntity.ok("Pagamento criado e pedido marcado como PAGO.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Pedido não encontrado.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao gerar pagamento.");
        }
    }
}
