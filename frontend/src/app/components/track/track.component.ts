import { Component, OnInit } from '@angular/core';
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


  constructor(private trackService:TrackService) { }

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
