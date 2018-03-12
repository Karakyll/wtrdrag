import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class RaceService {

  constructor(public http:Http) { }

  getRaces(){
    return this.http.get('http://localhost:8082/races')
    .map(res => res.json());
  }

  deleteRace(race){
    return this.http.delete('http://localhost:8082/races/' + race.raceId);
  }

  addRace(race){
    return this.http.post('http://localhost:8082/races', race)
    .map(res => res.json());
  }

}
