/*
 * Copyright (C) 2018 rafael
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package br.com.rendti.livroapi.service;

import br.com.rendti.livroapi.repository.*;
import br.com.rendti.livroapi.model.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class BookService {
      @Autowired
    private BookRepository repository;
      
      
      @Autowired
    private AuthorRepository repositoryAuthor;
      
    public  Iterable<Book> findAll(){
        
      Iterable<Book> lista =   repository.findAll();
        
      return lista;
    }
    
    public Book save(Book obj){
        return repository.save(obj);
        
    }
    
    public void deleteById(long id){
        repository.deleteById(id);
    }
    public Optional<Book> findById(long id) {
        return repository.findById(id);
        
    }
    public Book localizaById(long id) throws Exception{
        Optional<Book> obj = repository.findById(id);
        
        if ( obj.isPresent() ){
            return obj.get();
        }else{
            throw new Exception ("Book não localizado");
        }
    }
    
    public void completeObj(Book newobj){
         if ( newobj.getAuthor() != null && newobj.getAuthor().getId() > 0 ){
                    Long id_author = newobj.getAuthor().getId();
                    Optional<Author> oAuthor = repositoryAuthor.findById(id_author);
                    
                    if ( oAuthor.isPresent() ){
                        
                               newobj.setAuthor( oAuthor.get()  );
                    }
                } 
    }
    
        public String msgAtualizacao = "";
    
        public Book getCompleteObj(Book newobj, Long id)  {
            
         
        Optional<Book> objOpt = this.findById(id); 
        Book obj = new Book();
            
        if ( objOpt.isPresent() ){
            obj = objOpt.get();
            this.msgAtualizacao = "Book atualizado com sucesso!";
        }else{
            this.msgAtualizacao = "ID de Book não existe, foi cadastrado um novo";
        }
        
        if ( newobj.getAuthor() == null ){
            this.msgAtualizacao = "Autor em estado nulo";
        }else{
            
            //this.msgAtualizacao = "ID de autor: " + newobj.getAuthor().getId().toString();
        }
            
         if ( newobj.getAuthor() != null && newobj.getAuthor().getId() > 0 ){
                    Long id_author = newobj.getAuthor().getId();
                    Optional<Author> oAuthor = repositoryAuthor.findById(id_author);
                    
                    if ( oAuthor.isPresent() ){
                        
                               obj.setAuthor( oAuthor.get()  );
                    }else{
                               this.msgAtualizacao = "Author não localizado no método";
                    }
         }
         
         
         obj.setAuthor(newobj.getAuthor() );
         obj.setTitle(newobj.getTitle() );
         obj.setIsbn(newobj.getIsbn() );
         obj.setStock(newobj.getStock() );
         obj.setDescription(newobj.getDescription() );
         
         return repository.save(obj);
    }
  
    
}
