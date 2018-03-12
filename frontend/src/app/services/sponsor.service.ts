import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map'

@Injectable()
export class SponsorService {

  constructor(public http:Http) { }

   getSponsors(){
     return this.http.get('http://localhost:8082/sponsors')
     .map(res => res.json());
   }

}
