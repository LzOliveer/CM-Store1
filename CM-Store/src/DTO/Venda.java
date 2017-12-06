/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author luizo
 */
public class Venda {
    private int cod_venda;
    private int cod_prod;
    private int cod_cli;
    private int qtd;
    private String dt;
    private String forma_pgt;

    /**
     * @return the cod_venda
     */
    public int getCod_venda() {
        return cod_venda;
    }

    /**
     * @param cod_venda the cod_venda to set
     */
    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    /**
     * @return the cod_prod
     */
    public int getCod_prod() {
        return cod_prod;
    }

    /**
     * @param cod_prod the cod_prod to set
     */
    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    /**
     * @return the cod_cli
     */
    public int getCod_cli() {
        return cod_cli;
    }

    /**
     * @param cod_cli the cod_cli to set
     */
    public void setCod_cli(int cod_cli) {
        this.cod_cli = cod_cli;
    }

    /**
     * @return the qtd
     */
    public int getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    /**
     * @return the dt
     */
    public String getDt() {
        return dt;
    }

    /**
     * @param dt the dt to set
     */
    public void setDt(String dt) {
        this.dt = dt;
    }

    /**
     * @return the forma_pgt
     */
    public String getForma_pgt() {
        return forma_pgt;
    }

    /**
     * @param forma_pgt the forma_pgt to set
     */
    public void setForma_pgt(String forma_pgt) {
        this.forma_pgt = forma_pgt;
    }
    
}
