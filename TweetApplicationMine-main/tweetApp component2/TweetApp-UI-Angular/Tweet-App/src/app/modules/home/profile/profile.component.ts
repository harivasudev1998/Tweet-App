import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/core/services/api.service';
import { Tweets } from '../../model/tweets';
import { User } from '../../model/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  myTweets: Array<Tweets> =[];
  userName: string;
  user: User = <User>{};
  addBIOMessage: string = '';
  constructor(private _apiService: ApiService) {}

  ngOnInit(): void {
    this.userName = sessionStorage.getItem('userName');
    this.user = JSON.parse(sessionStorage.getItem('loggedInUser'));
    this.getMyAllTweets();
  }

  getMyAllTweets() {
    this._apiService.getAllMyTweets(this.userName).subscribe(
      (response) => {
        this.myTweets = response;
      },
      (err) => {}
    );
  }

  on() {
    document.getElementById('bioModal').style.display = 'block';
  }

  addBIO() {
    this.user.intro = this.addBIOMessage;
    this._apiService.addIntro(this.user).subscribe(
      (response: User) => {
        this.user = response;
        sessionStorage.setItem(
          'loggedInUser',
          JSON.stringify(response)
        );
      },
      (err) => {}
    );
  }
}
