/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pgxp.systika.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author escritorio
 */
@Entity
@Table(catalog = "systika", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arquivo.findAll", query = "SELECT a FROM Arquivo a"),
    @NamedQuery(name = "Arquivo.findById", query = "SELECT a FROM Arquivo a WHERE a.id = :id"),
    @NamedQuery(name = "Arquivo.findByHashfile", query = "SELECT a FROM Arquivo a WHERE a.hashfile = :hashfile"),
    @NamedQuery(name = "Arquivo.findByLocal", query = "SELECT a FROM Arquivo a WHERE a.local = :local"),
    @NamedQuery(name = "Arquivo.findByNome", query = "SELECT a FROM Arquivo a WHERE a.nome = :nome"),
    @NamedQuery(name = "Arquivo.findByDatamodificado", query = "SELECT a FROM Arquivo a WHERE a.datamodificado = :datamodificado"),
    @NamedQuery(name = "Arquivo.findByTamanho", query = "SELECT a FROM Arquivo a WHERE a.tamanho = :tamanho")})
public class Arquivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 512)
    private String hashfile;
    @Column(length = 1024)
    private String local;
    @Column(length = 512)
    private String nome;
    private Long datamodificado;
    private Long tamanho;

    public Arquivo() {
    }

    public Arquivo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashfile() {
        return hashfile;
    }

    public void setHashfile(String hashfile) {
        this.hashfile = hashfile;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDatamodificado() {
        return datamodificado;
    }

    public void setDatamodificado(Long datamodificado) {
        this.datamodificado = datamodificado;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
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
        if (!(object instanceof Arquivo)) {
            return false;
        }
        Arquivo other = (Arquivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pgxp.systika.domain.Arquivo[ id=" + id + " ]";
    }


}
