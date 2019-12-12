/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.geoloca;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class ControladorUsuario {
    
  @Autowired
  RepoUsuario repoUsuario;
    
    
    @GetMapping("/usuario")
    public List<Usuario>  obtenerUsuarios(){
        //Simulamos que obtenemos un usuario
       
        
        return repoUsuario.findAll();
   }
 //Metodo POST para guardar un Usuario
    @PostMapping("/usuario")
    public Estatus guardarUsuario(@RequestBody String json)
            throws Exception{
        //Mapeamos el objeto llegado
        ObjectMapper maper=new ObjectMapper();
     Usuario u=   maper.readValue(json, Usuario.class);
       //Ya despues de mapear guardamo!
       repoUsuario.save(u);
       //Generamos el estatus
       Estatus e=new Estatus();
       e.setMensaje("Usuario ya se guardooo!!");
       e.setSuccess(true);
        return e;
    }
    @GetMapping("/usuario/nombre")
    public List<Usuario> buscarPorNombre(@RequestBody String json)throws Exception{
        ObjectMapper maper=new ObjectMapper();
        Usuario u=maper.readValue(json, Usuario.class);
      return  repoUsuario.findByNombre(u.getNombre());
    }
    
    
    
}
