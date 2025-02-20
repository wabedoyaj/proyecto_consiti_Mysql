package com.tutorial.crud.controller;


import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.ProductoDto;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.service.ProductoService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = ("*"))// Permite solicitudes desde cualquier dominio
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public ResponseEntity<List<Producto>> findAll (){
        List<Producto> list = productoService.list();
        return  new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable("id") int id){
        if(!productoService.existById(id)){
            return new ResponseEntity<>(new Mensaje("El producto solicitado no existe"), HttpStatus.NOT_FOUND);
        }
        Producto producto = productoService.getOne(id).get();
        return  new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/detail-name/{name}")
    public ResponseEntity<?> getByNombre(@PathVariable("nombre") String nombre){
        if(!productoService.existByNombre(nombre)){
            return new ResponseEntity<Mensaje>(new Mensaje("El producto con nombre "+ nombre +" no existe"), HttpStatus.NOT_FOUND);
        }
        Producto producto = productoService.getByNombre(nombre).get();
        return  new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Mensaje> create(@RequestBody ProductoDto productoDto){
        if(StringUtils.isBlank(productoDto.getNombre())){
            return new ResponseEntity<Mensaje>(new Mensaje("El nombre del producto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.getPrecio() == null || productoDto.getPrecio() < 0) {
            return new ResponseEntity<>(new Mensaje("El precio del producto debe ser mayor a 0.0"), HttpStatus.BAD_REQUEST);
        }
        if(productoService.existByNombre(productoDto.getNombre())){
            return new ResponseEntity<Mensaje>(new Mensaje("El nombre " + productoDto.getNombre() + " ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
        }
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
        productoService.save(producto);
        return  new ResponseEntity<Mensaje>(new Mensaje("Producto creado con exito"), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id,@RequestBody ProductoDto productoDto){
        if(!productoService.existById(id)){
            return new ResponseEntity<Mensaje>(new Mensaje("El producto no existe"), HttpStatus.NOT_FOUND);
        }
        if(productoService.existByNombre(productoDto.getNombre())){
            return new ResponseEntity<Mensaje>(new Mensaje("El nombre " + productoDto.getNombre() + " ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(productoDto.getNombre())){
            return new ResponseEntity<Mensaje>(new Mensaje("El nombre del producto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.getPrecio() == null || productoDto.getPrecio() < 0) {
            return new ResponseEntity<>(new Mensaje("El precio del producto debe ser mayor a 0.0"), HttpStatus.BAD_REQUEST);
        }
        Producto producto = productoService.getOne(id).get();
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        productoService.save(producto);
        return  new ResponseEntity<Mensaje>(new Mensaje("Producto actualizado con exito"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") int id){
        if(!productoService.existById(id)) {
            return new ResponseEntity<Mensaje>(new Mensaje("El producto a eliminar no existe"), HttpStatus.NOT_FOUND);
        }
        productoService.delete(id);
        return  new ResponseEntity<Mensaje>(new Mensaje("Producto eliminado"), HttpStatus.OK);
    }

}
