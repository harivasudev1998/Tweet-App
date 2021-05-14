import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/core/services/api.service';
import { TweetUser } from '../../model/tweet-user';
import { Tweets } from '../../model/tweets';
import { User } from '../../model/user';

@Component({
  selector: 'app-post-tweet',
  templateUrl: './post-tweet.component.html',
  styleUrls: ['./post-tweet.component.css'],
})
export class PostTweetComponent implements OnInit {
  tweet: string = '';
  constructor(private _apiService: ApiService) {}
  isLiked: boolean = true;
  tweets: Array<Tweets>;
  user: User = <User>{};
  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem('loggedInUser'));
    this.getAllTweets();
  }

  tweetPost() {
    if (this.tweet !== null) {
      let tweetByUser: Tweets = <Tweets>{};
      let tweetUser: TweetUser = <TweetUser>{};
      tweetUser.loginID = this.user.loginID;
      tweetUser.firstName = this.user.firstName;
      tweetUser.lastName = this.user.lastName;
      tweetUser.gender = this.user.gender;
      tweetByUser.tweet = this.tweet;

      tweetByUser.tweetUserObject = tweetUser;
      this._apiService.postTweet(tweetByUser).subscribe((response: Tweets) => {
        this.tweet = null;
        this.getAllTweets();
      });
    }
  }

  getAllTweets() {
    this._apiService.getAllTweets().subscribe(
      (response) => {
        this.tweets = response;
      },
      (err) => {
        this.isLiked = true;
      }
    );
  }
}
