import { Component, OnInit } from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import { CarService } from '../../services/car.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {

  carId:number;
  mark:string;
  model:string;
  pilotFirstName:string;
  pilotLastName:string;
  power:number;
  torque:number;
  spec:string;
  sponsorId:number;
  cars:Car[];

  submitted:boolean;

  constructor(private translate: TranslateService, private carService:CarService, private router: Router) {
    translate.setDefaultLang('en');
  }

  ngOnInit() {
    this.carService.getCars().subscribe((cars) => {
      this.cars = cars;
    });
  }

  onClick(car){
    this.router.navigate(['/cars', car.carId])
  }

  deleteCar(car){
    this.carService.deleteCar(car).subscribe((ok) => {
      for(let i = 0; i < this.cars.length; i++){
        if(this.cars[i] == car){
          this.cars.splice(i, 1);
        }
      }
    });
  }

  addNewCar(){
    this.carService.addCar(new Car(
      this.mark,
      this.model,
      this.pilotFirstName,
      this.pilotLastName,
      this.power,
      this.torque,
      this.spec,
      this.sponsorId
    )).subscribe(car => {
      console.log(car);
      this.cars.push(car);
    });
  }

  onSubmit() { this.submitted = !this.submitted; }

}

class Car{

  constructor(
    public mark:string,
    public model:string,
    public pilotFirstName:string,
    public pilotLastName:string,
    public power:number,
    public torque:number,
    public spec:string,
    public sponsorId:number,
    public carId?:number
  ){};
 
}
