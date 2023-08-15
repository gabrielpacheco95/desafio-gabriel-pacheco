/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caixa.da.lanchonete.js;

/**
 *
 * @author gabri
 */
public class CaixaDaLanchonete {
 import ItemCardapio.java;

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

    calcularValorDaCompra(metodoDePagamento, itens) {
        let total = 0;

        for (const item in itens) {
            if (this.cardapio[item]) {
                total += this.cardapio[item].valor * itens[item];
            }
        }

        if (total === 0) {
            return "Não há itens no carrinho de compra!";
        } else if (total < 0) {
            return "Quantidade inválida!";
        }

        if (itens["chantily"] && !itens["cafe"]) {
            return "Item extra não pode ser pedido sem o principal";
        }
        if (itens["queijo"] && !itens["sanduiche"]) {
            return "Item extra não pode ser pedido sem o principal";
        }

        if (metodoDePagamento === "dinheiro") {
            total *= (1 - this.descontoDinheiro);
        } else if (metodoDePagamento === "credito") {
            total *= (1 + this.acrescimoCredito);
        }

        return total.toFixed(2);
    }
}

export default CaixaDaLanchonete;
   }


