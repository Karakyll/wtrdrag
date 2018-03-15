import { Component, OnInit } from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import { TrackService } from '../../services/track.service';

@Component({
  selector: 'app-track',
  templateUrl: './track.component.html',
  styleUrls: ['./track.component.css']
})
export class TrackComponent implements OnInit {

  trackId:number;
  trackName:string;
  trackCountry:string;
  tracks:Track[];
  
  constructor(private translate: TranslateService, private trackService:TrackService) {
    translate.setDefaultLang('en');
  }

  ngOnInit() {
    this.trackService.getTracks().subscribe((tracks) => {
      this.tracks = tracks;
    });
  }

}

interface Track{
  trackId:number;
  trackName:string;
  trackCountry:string;
}
