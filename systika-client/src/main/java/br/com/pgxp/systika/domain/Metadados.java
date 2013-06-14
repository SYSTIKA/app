/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pgxp.systika.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author escritorio
 */
@Entity
@Table(catalog = "systika", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metadados.findAll", query = "SELECT m FROM Metadados m"),
    @NamedQuery(name = "Metadados.findById", query = "SELECT m FROM Metadados m WHERE m.id = :id"),
    @NamedQuery(name = "Metadados.findByMeta", query = "SELECT m FROM Metadados m WHERE m.meta = :meta")})
public class Metadados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 1024)
    private String meta;
    private Long idarquivo;

    public Metadados() {
    }

    public Metadados(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Long getIdarquivo() {
        return idarquivo;
    }

    public void setIdarquivo(Long idarquivo) {
        this.idarquivo = idarquivo;
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
        if (!(object instanceof Metadados)) {
            return false;
        }
        Metadados other = (Metadados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pgxp.systika.domain.Metadados[ id=" + id + " ]";
    }
    
}
