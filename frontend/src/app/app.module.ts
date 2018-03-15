import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';


import { AppComponent } from './app.component';
import { UserComponent } from './components/user/user.component';
import { AboutComponent } from './components/about/about.component';
import { SponsorComponent } from './components/sponsor/sponsor.component';
import { CarComponent } from './components/car/car.component';
import { TrackComponent } from './components/track/track.component';
import { RaceComponent } from './components/race/race.component';
import { CarDetailsComponent } from './components/car-details/car-details.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';

import { SponsorService } from './services/sponsor.service';
import { CarService } from './services/car.service';
import { TrackService } from './services/track.service';
import { RaceService } from './services/race.service';




const appRoutes: Routes = [
  {path:'', component:AboutComponent},
  {path:'users', component:UserComponent},
  {path:'about', component:AboutComponent},
  {path:'sponsors', component:SponsorComponent},
  {path:'cars', component:CarComponent},
  {path:'cars/:id', component:CarDetailsComponent},
  {path:'tracks', component:TrackComponent},
  {path:'races', component:RaceComponent},
  {path:'login', component:LoginComponent},
  {path:'signup', component:SignupComponent}
]


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AboutComponent,
    SponsorComponent,
    CarComponent,
    TrackComponent,
    RaceComponent,
    CarDetailsComponent,
    LoginComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
      }
  })
  ],
  providers: [
    SponsorService,
    CarService,
    TrackService,
    RaceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
