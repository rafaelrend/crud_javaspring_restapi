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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class AuthorService {
      @Autowired
    private AuthorRepository repository;
    
      @Autowired
     private JdbcTemplate JdbcTemplate;
      
    public  Iterable<Author> findAll(){
        
        
      Iterable<Author> lista =   repository.findAll();
        
      return lista;
    }
    
    public Author save(Author convidado){
        return repository.save(convidado);
    }
    
    public void deleteById(long id){
        repository.deleteById(id);
       
    }
    
    public int getTotal(){
          String query = "select count(*) from author e where 1 = 1 ";
            // you will always get a single result
          int total =   JdbcTemplate.queryForObject(query, int.class);
            
          return total;
        
    }
    
    public Optional<Author> findById(long id) {
        return repository.findById(id);
        
    }
    public Author localizaById(long id) throws Exception{
        Optional<Author> obj = repository.findById(id);
        
        if ( obj.isPresent() ){
            return obj.get();
        }else{
            throw new Exception ("Autor não localizado");
        }
    }
}
