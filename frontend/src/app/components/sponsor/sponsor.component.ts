import { Component, OnInit } from '@angular/core';
import { SponsorService } from '../../services/sponsor.service';

@Component({
  selector: 'app-sponsor',
  templateUrl: './sponsor.component.html',
  styleUrls: ['./sponsor.component.css']
})
export class SponsorComponent implements OnInit {

  sponsorId:number;
  sponsorName:string;
  sponsorSlogan:string;
  sponsors:Sponsor[];

  constructor(private sponsorService:SponsorService) { }

  ngOnInit() {
    this.sponsorService.getSponsors().subscribe((sponsors) => {
      this.sponsors = sponsors;
    });
  }

}

interface Sponsor{
  sponsorId:number,
  sponsorName:string,
  sponsorSlogan:string
}
