package com.example.BackendCRUDSBMDBANG.controller;

import com.example.BackendCRUDSBMDBANG.model.Tarea;
import com.example.BackendCRUDSBMDBANG.repo.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    /***
     * Solicitud GET - Listar
     * @return listado de tareas almacenadas en la BD
     */
    @GetMapping("")
    List<Tarea> index(){
        return tareaRepository.findAll();
    }

    /***
     * Solicitud POST - Crear nuevo
     * @param tarea datos de la nueva tarea a crear y almacenar en la DB
     * @return nueva tarea creada en DB
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Tarea create(@RequestBody Tarea tarea){
        return tareaRepository.save(tarea);
    }

    /***
     * Solicitud PUT - Update
     * @param id identificador del registro a actualizar
     * @param tarea nuevos datos a almacenar
     * @return resultado de la actualizacion
     */
    @PutMapping("/{id}")
    Tarea update(@PathVariable String id, @RequestBody Tarea tarea){
        Tarea tareaFromDB = tareaRepository
                                .findById(id)
                                .orElseThrow(RuntimeException::new);
        tareaFromDB.setNombre(tarea.getNombre());
        tareaFromDB.setCompletado(tarea.isCompletado());

        return tareaRepository.save(tareaFromDB);

    }

    /***
     * Solicitud DELETE - Eliminar
     * @param id identificador del registro a borrar
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        Tarea tareaFromDB = tareaRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
        tareaRepository.delete(tareaFromDB);
    }

}
