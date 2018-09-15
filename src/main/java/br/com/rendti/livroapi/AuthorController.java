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
package br.com.rendti.livroapi;

import br.com.rendti.livroapi.model.*;
import br.com.rendti.livroapi.service.*;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rafael
 */
@Controller
public class AuthorController {
    
     @Autowired
    private AuthorService service;
    
         
      @RequestMapping(value = "/authors",  
                method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
      @GetMapping
      @ResponseBody
    public Iterable<Author> get() {
      
        Iterable<Author> results = service.findAll();
        
        return results;
    }
     
    
      @RequestMapping(value = "/authors_test",  
                method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
      @GetMapping
      @ResponseBody
    public HashMap<String, Object> getTeste() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "1");
        
        Iterable<Author> results = service.findAll();
        
        map.put("results", results);
        map.put("total", service.getTotal());
        return map;
    }
   
    @ResponseBody
    @RequestMapping(value = "/authors/{id}",  
                method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> getAuthorById(HttpServletRequest request, @PathVariable("id") Long id){
        
         HashMap<String, Object> map = new HashMap<>();
         
         try{

                        Author results = service.localizaById(id);

                         map.put("code", "1");
                         map.put("results", results);
         }catch(Exception exp){
                         map.put("code", "0");
                         map.put("results", exp.getMessage());
                         map.put("id", id);  
         }
        return map;
    }
    
    @ResponseBody
    @RequestMapping(value = "/authors", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
    Author newAuthor(@RequestBody Author newobj) {
		return service.save(newobj);
    }
    
    @ResponseBody
    @PutMapping("/authors/{id}")
    public HashMap<String, Object> save(@RequestBody Author newObj, @PathVariable("id") Long id){
        
         HashMap<String, Object> map = new HashMap<>();
         
         if ( newObj.getName() == ""){
             
             
               map.put("code", "0");
               map.put("results", "Nome vazio!");
               return map;
         }
         
         try{

                        Author obj = service.localizaById(id);
                        obj.setName(newObj.getName());

                         map.put("code", "1");
                         map.put("msg","atualizado com sucesso!");
                         map.put("results", service.save(obj));
         }catch(Exception exp){
               //Não localizei, então vou salvar um novo..
               Author obj = new Author();
               obj.setName(newObj.getName());
             
               Author result = service.save(obj);
               
               map.put("code", "2");
               map.put("results", result);
         }
        return map;
    }
    
    @ResponseBody
    @RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE )
    public HashMap<String, Object> deleteAuthor(@PathVariable Long id) {
        
         HashMap<String, Object> map = new HashMap<>();
        
                try{

                        Author results = service.localizaById(id);
                        
                        service.deleteById(id);
                
                         map.put("code", "1");
                         map.put("msg","excluído com sucesso!");
                        
                }catch(Exception exp){
                    
                         map.put("code", "0");
                         map.put("msg",exp.getMessage());
                }
        
	
        return map;
    }
    
    
}
