/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caixa.da.lanchonete.js;

/**
 *
 * @author gabriel almeida pacheco
 */
public class ItemCardapio {
  import ItemCardapio from './ItemCardapio.js';

class CaixaDaLanchonete {
    constructor() {
        this.cardapio = {
            "cafe": new ItemCardapio("Café", 3.00),
            "chantily": new ItemCardapio("Chantily (extra do Café)", 1.50),
            "suco": new ItemCardapio("Suco Natural", 6.20),
            "sanduiche": new ItemCardapio("Sanduíche", 6.50),
            "queijo": new ItemCardapio("Queijo (extra do Sanduíche)", 2.00),
            "salgado": new ItemCardapio("Salgado", 7.25),
            "combo1": new ItemCardapio("1 Suco e 1 Sanduíche", 9.50),
            "combo2": new ItemCardapio("1 Café e 1 Sanduíche", 7.50)
        };

        this.descontoDinheiro = 0.05;
        this.acrescimoCredito = 0.03;
    }

    calcularValorDaCompra(formaDePagamento, itens) {
        const itensSelecionados = {};

        for (const item of itens) {
            const [codigo, quantidade] = item.split(',');
            if (this.cardapio[codigo]) {
                itensSelecionados[codigo] = (itensSelecionados[codigo] || 0) + parseInt(quantidade);
            }
        }

        let total = 0;

        for (const codigo in itensSelecionados) {
            total += this.cardapio[codigo].valor * itensSelecionados[codigo];
        }

        if (total === 0) {
            return "Não há itens no carrinho de compra!";
        } else if (total < 0) {
            return "Quantidade inválida!";
        }

        if (itensSelecionados["chantily"] && !itensSelecionados["cafe"]) {
            return "Item extra não pode ser pedido sem o principal";
        }
        if (itensSelecionados["queijo"] && !itensSelecionados["sanduiche"]) {
            return "Item extra não pode ser pedido sem o principal";
        }

        if (formaDePagamento === "dinheiro") {
            total *= (1 - this.descontoDinheiro);
        } else if (formaDePagamento === "credito") {
            total *= (1 + this.acrescimoCredito);
        }

        return `R$ ${total.toFixed(2).replace('.', ',')}`;
    }
}

export default CaixaDaLanchonete;


