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
public class Estoque {
    private String cod;
    private String codBar;
    private String nome;
    private String est_at;
    private String est_nv;

    /**
     * @return the cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @return the codBar
     */
    public String getCodBar() {
        return codBar;
    }

    /**
     * @param codBar the codBar to set
     */
    public void setCodBar(String codBar) {
        this.codBar = codBar;
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
     * @return the est_at
     */
    public String getEst_at() {
        return est_at;
    }

    /**
     * @param est_at the est_at to set
     */
    public void setEst_at(String est_at) {
        this.est_at = est_at;
    }

    /**
     * @return the est_nv
     */
    public String getEst_nv() {
        return est_nv;
    }

    /**
     * @param est_nv the est_nv to set
     */
    public void setEst_nv(String est_nv) {
        this.est_nv = est_nv;
    }
}
