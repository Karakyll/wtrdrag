import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map'

@Injectable()
export class TrackService {

  constructor(public http:Http) { }

  getTracks(){
    return this.http.get('http://localhost:8082/tracks')
    .map(res => res.json());
  }

}
