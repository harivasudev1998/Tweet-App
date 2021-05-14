import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { NavigationComponent } from './navigation/navigation.component';
import { TweetComponent } from './tweet/tweet.component';
import { SearchComponent } from './search/search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProfileComponent } from './profile/profile.component';
import { PostTweetComponent } from './post-tweet/post-tweet.component';
import { ContactUsComponent } from './contact-us/contact-us.component';


@NgModule({
  declarations: [HomeComponent, NavigationComponent, TweetComponent, SearchComponent, ProfileComponent, PostTweetComponent, ContactUsComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class HomeModule { }
