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
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class BookController {
    
     @Autowired
    private BookService service;
         
     @Autowired
    private AuthorService serviceAuthor;
    
        @RequestMapping(value = "/",  
             method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public String indexmsg(){

                      return "Verifique métodos /authors e /books";
       }        
         
      @ResponseBody  
      @RequestMapping(value = "/books",  
                method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //find all method
        public Iterable<Book> get() {

            Iterable<Book> results = service.findAll();

            return results;
        }
     

        @ResponseBody //insert method
        @RequestMapping(value = "/books", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
        Book newBook(@RequestBody Book newobj) {

                  service.completeObj(newobj);

                  return service.save(newobj);
        }
        
        
        

        @ResponseBody
        @PutMapping("/books/{id}")  //Update method
        public HashMap<String, Object> save(@RequestBody Book newObj, @PathVariable("id") Long id){

             HashMap<String, Object> map = new HashMap<>();

             if ( newObj.getTitle() == ""){


                   map.put("code", "0");
                   map.put("results", "Título vazio!");
                   return map;
             }

             Book obj = service.getCompleteObj(newObj, id);

             map.put("code", "1");
             map.put("msg", service.msgAtualizacao);
             map.put("results", service.save(obj));

            return map;
        }



        @ResponseBody   //Find By id method
        @RequestMapping(value = "/books/{id}",  
                    method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public HashMap<String, Object> getById(HttpServletRequest request, @PathVariable("id") Long id){

             HashMap<String, Object> map = new HashMap<>();

             try{

                            Book results = service.localizaById(id);

                             map.put("code", "1");
                             map.put("results", results);
             }catch(Exception exp){
                             map.put("code", "0");
                             map.put("results", exp.getMessage());
                             map.put("id", id);  
             }
            return map;
        }

    
        @ResponseBody   //Delete method
        @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE )
        public HashMap<String, Object> deleteAuthor(@PathVariable Long id) {

             HashMap<String, Object> map = new HashMap<>();

                    try{

                            Book results = service.localizaById(id);

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
