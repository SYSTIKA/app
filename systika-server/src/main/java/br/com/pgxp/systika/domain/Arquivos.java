/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pgxp.systika.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(catalog = "systika", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arquivos.findAll", query = "SELECT a FROM Arquivos a"),
    @NamedQuery(name = "Arquivos.findById", query = "SELECT a FROM Arquivos a WHERE a.id = :id"),
    @NamedQuery(name = "Arquivos.findByHash", query = "SELECT a FROM Arquivos a WHERE a.hash = :hash"),
    @NamedQuery(name = "Arquivos.findByLocal", query = "SELECT a FROM Arquivos a WHERE a.local = :local"),
    @NamedQuery(name = "Arquivos.findByMetadata", query = "SELECT a FROM Arquivos a WHERE a.metadata = :metadata")})
public class Arquivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Size(max = 300)
    @Column(length = 300)
    private String hash;
    @Size(max = 1024)
    @Column(length = 1024)
    private String local;
    @Size(max = 2048)
    @Column(length = 2048)
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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
