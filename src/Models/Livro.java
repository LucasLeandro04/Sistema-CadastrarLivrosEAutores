/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author Lucas
 */
public class Livro {

    private int idLivro;
    private String titulo, editora;
    private LocalDate dataPublic;
    private Autor autor;

    @Override
    public String toString() {
        return "Livro{" + "autor=" + autor + '}';
    }
    
    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public LocalDate getDataPublic() {
        return dataPublic;
    }

    public void setDataPublic(LocalDate dataPublic) {
        this.dataPublic = dataPublic;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

}
