import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  tareas: any[] = [];
  tarea: any;

  constructor(private appService: AppService) { }

  ngOnInit(): void {
   this.cargarTareas();
   this.resetTarea();
  }

  // Edita los datos de la tarea seleccionada
  edit(tarea: any){
    this.tarea = {...tarea};
  }


  // maneja la creacion de nueva tarea
  save(){
    if(this.tarea.id){
      this.appService.update(this.tarea.id, this.tarea)
        .subscribe(() => this.cargarTareas())
    }else{
      this.appService.create(this.tarea)
        .subscribe(() => this.cargarTareas())
    }
    this.resetTarea();
  }

  // Elimina la tarea recibida
  delete(tarea:any){
    let ok= confirm("Desea eliminar la tarea: " + tarea.nombre +"?")
    if(ok){
      this.appService.delete(tarea.id)
        .subscribe(() => this.cargarTareas());
    }
  }

  // recupera la lista de tareas desde la BD
  cargarTareas(){
    this.appService.getAll()
        .subscribe((data: any) => this.tareas = data);
  }

  // reinicia valores variable tarea
  resetTarea(){
    this.tarea = {
      id: null,
      nombre: "",
      completado: false
    }
  }

}
