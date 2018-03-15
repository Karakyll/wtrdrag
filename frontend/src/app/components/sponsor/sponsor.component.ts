import { Component, OnInit } from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
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
  
  constructor(private translate: TranslateService, private sponsorService:SponsorService) {
    translate.setDefaultLang('en');
  }

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
