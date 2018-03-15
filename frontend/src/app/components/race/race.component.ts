import { Component, OnInit } from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import { RaceService } from '../../services/race.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-race',
  templateUrl: './race.component.html',
  styleUrls: ['./race.component.css']
})
export class RaceComponent implements OnInit {

  raceId:number;
  carId:number;
  trackId:number;
  dateTime:string;
  date:string;
  time:string;
  reactionTime:number;
  elapsedTime:number;
  finishSpeed:number;
  races:Race[];

  submitted:boolean;
  
  constructor(private translate: TranslateService, private raceService:RaceService) {
    translate.setDefaultLang('en');
  }

  ngOnInit() {
    this.raceService.getRaces().subscribe((races) => {
      this.races = races;
    });
  }

  deleteRace(race){
    this.raceService.deleteRace(race).subscribe((ok) => {
      for(let i = 0; i < this.races.length; i++){
        if(this.races[i] == race){
          this.races.splice(i, 1);
        }
      }
    }); 
  }

  addNewRace(){
    this.raceService.addRace(new Race(
      this.carId,
      this.trackId,
      this.date + ' ' + this.time,
      this.reactionTime,
      this.elapsedTime,
      this.finishSpeed
    )).subscribe((race) => {
      this.races.push(race);
    });
  }

  onSubmit() { this.submitted = !this.submitted; }

}

class Race {

  constructor(
    public carId:number,
    public trackId:number,
    public dateTime:string,
    public reactionTime:number,
    public elapsedTime:number,
    public finishSpeed:number,
    public raceId?:number 
  ){ }
 
}
