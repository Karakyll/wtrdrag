import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map'

@Injectable()
export class CarService {

  constructor(public http:Http) { }

  getCars(){
    return this.http.get('http://localhost:8082/cars')
    .map(res => res.json());
  }

  getCarById(id){
    return this.http.get('http://localhost:8082/cars/' + id)
    .map(res => res.json());
  }

  deleteCar(car){
    return this.http.delete('http://localhost:8082/cars/' + car.carId);
  }

  addCar(car){
    console.log(car);
    return this.http.post('http://localhost:8082/cars', car)
    .map(res => res.json());
  }

}
