package com.tutorial.crud.service;

import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductoService {

    @Autowired
    private  ProductoRepository productoRepository;

    public List<Producto> list() {
        return productoRepository.findAll();
    }
    public Optional<Producto> getOne(int id) {
        return productoRepository.findById(id);
    }
    public Optional<Producto> getByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);    }

    public void save(Producto producto) {
        productoRepository.save(producto);
    }
    public void delete(int id) {
        productoRepository.deleteById(id);
    }
    public boolean existById(int id) {
        return productoRepository.existsById(id);
    }
    public boolean existByNombre(String nombre) {
        return productoRepository.existByNombre(nombre);
    }
}
