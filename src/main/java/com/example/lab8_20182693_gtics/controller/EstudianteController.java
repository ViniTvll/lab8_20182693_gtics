package com.example.lab8_20182693_gtics.controller;


import com.example.lab8_20182693_gtics.entity.Estudiante;
import com.example.lab8_20182693_gtics.repository.EstudianteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    final EstudianteRepository estudianteRepository;

    public EstudianteController(EstudianteRepository estudianteRepository) {this.estudianteRepository = estudianteRepository;}


    //LISTAR
    @GetMapping(value = {"/list", ""})
    public List<Estudiante> listaEstudiantes() {
        return estudianteRepository.findAll();
    }


    //OBTENER
    @GetMapping(value = "/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarEstudainte(@PathVariable("id") String idStr) {


        try {
            int id = Integer.parseInt(idStr);
            Optional<Estudiante> byId = estudianteRepository.findById(id);

            HashMap<String, Object> respuesta = new HashMap<>();

            if (byId.isPresent()) {
                respuesta.put("result", "ok");
                respuesta.put("estudiante", byId.get());
            } else {
                respuesta.put("result", "no existe");
            }
            return ResponseEntity.ok(respuesta);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    // CREAR
    @PostMapping(value = {"", "/"})
    public ResponseEntity<HashMap<String, Object>> guardarEstudiante(
            @RequestBody Estudiante estudiante,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        estudianteRepository.save(estudiante);
        if (fetchId) {
            responseJson.put("id", estudiante.getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    // ACTUALIZAR
    @PutMapping(value = {"", "/"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizar(Estudiante estudianteRecibido) {

        HashMap<String, Object> rpta = new HashMap<>();

        if (estudianteRecibido.getId() != null && estudianteRecibido.getId() > 0) {

            Optional<Estudiante> byId = estudianteRepository.findById(estudianteRecibido.getId());
            if (byId.isPresent()) {
                Estudiante estudianteFromDb = byId.get();



                if (estudianteRecibido.getGpa() != null)
                    estudianteFromDb.setGpa(estudianteRecibido.getGpa());


                estudianteRepository.save(estudianteFromDb);
                rpta.put("result", "ok");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("result", "error");
                rpta.put("msg", "El ID del estudiante enviado no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("result", "error");
            rpta.put("msg", "debe enviar un estudiante con ID");
            return ResponseEntity.badRequest().body(rpta);
        }
    }

    //BORRAR
    @DeleteMapping("")
    public ResponseEntity<HashMap<String, Object>> borrar(@RequestParam("id") String idStr){

        try{
            int id = Integer.parseInt(idStr);

            HashMap<String, Object> rpta = new HashMap<>();

            Optional<Estudiante> byId = estudianteRepository.findById(id);
            if(byId.isPresent()){
                estudianteRepository.deleteById(id);
                rpta.put("result","ok");
            }else{
                rpta.put("result","no ok");
                rpta.put("msg","el ID enviado no existe");
            }

            return ResponseEntity.ok(rpta);
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
