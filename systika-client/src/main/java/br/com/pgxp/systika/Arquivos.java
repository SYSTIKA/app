/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pgxp.systika;

import java.io.Serializable;


/**
 *
 * @author 70744416353
 */

public class Arquivos implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String hashfile;
    private String local;
    private String metadata;

    public Arquivos() {
    }

    public Arquivos(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashFile() {
        return hashfile;
    }

    public void setHashFile(String hash) {
        this.hashfile = hash;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arquivos)) {
            return false;
        }
        Arquivos other = (Arquivos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pgxp.systika.domain.Arquivos[ id=" + id + " ]";
    }

}
