
package br.com.ab.model;

/**
 *
 * @author drink
 */
public class Cidade {
    
    private long id;
    private String nome;    
    private Estado estado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
    
    
}
