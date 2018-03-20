import { Component, OnInit } from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import { CarService } from '../../services/car.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {

  carId:number;
  mark:string;
  model:string;
  pilotFirstName:string;
  pilotLastName:string;
  power:number;
  torque:number;
  spec:string;
  sponsorId:number;

  private sub: any;
  
  constructor(private translate: TranslateService, private carService:CarService, private route: ActivatedRoute) {
    translate.setDefaultLang('en');
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.carService.getCarById(+params['id'])
      .subscribe(car => {
        this.carId = car.carId;
        this.mark = car.mark;
        this.model = car.model;
        this.pilotFirstName = car.pilotFirstName;
        this.pilotLastName = car.pilotLastName;
        this.power = car.power;
        this.torque = car.torque;
        this.spec = car.spec;
        this.sponsorId = car.sponsorId;
      })
    });
  }

}
