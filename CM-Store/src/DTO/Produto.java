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
public class Produto {
    private int cod;
    private int cod_fab;
    private int est;
    private String cod_bar;
    private double lucro;
    private double pf;
    private double pv;
    private String nome;
    private String apr;

    /**
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    /**
     * @return the cod_fab
     */
    public int getCod_fab() {
        return cod_fab;
    }

    /**
     * @param cod_fab the cod_fab to set
     */
    public void setCod_fab(int cod_fab) {
        this.cod_fab = cod_fab;
    }

    /**
     * @return the est
     */
    public int getEst() {
        return est;
    }

    /**
     * @param est the est to set
     */
    public void setEst(int est) {
        this.est = est;
    }

    /**
     * @return the cod_bar
     */
    public String getCod_bar() {
        return cod_bar;
    }

    /**
     * @param cod_bar the cod_bar to set
     */
    public void setCod_bar(String cod_bar) {
        this.cod_bar = cod_bar;
    }

    /**
     * @return the lucro
     */
    public double getLucro() {
        return lucro;
    }

    /**
     * @param lucro the lucro to set
     */
    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    /**
     * @return the pf
     */
    public double getPf() {
        return pf;
    }

    /**
     * @param pf the pf to set
     */
    public void setPf(double pf) {
        this.pf = pf;
    }

    /**
     * @return the pv
     */
    public double getPv() {
        return pv;
    }

    /**
     * @param pv the pv to set
     */
    public void setPv(double pv) {
        this.pv = pv;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the apr
     */
    public String getApr() {
        return apr;
    }

    /**
     * @param apr the apr to set
     */
    public void setApr(String apr) {
        this.apr = apr;
    }

    
}
