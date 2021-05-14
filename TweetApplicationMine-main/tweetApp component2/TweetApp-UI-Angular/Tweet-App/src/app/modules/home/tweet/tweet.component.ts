import { Component, Input, OnInit } from '@angular/core';
import { ApiService } from 'src/app/core/services/api.service';
import { Likes } from '../../model/likes';
import { TweetReply } from '../../model/tweet-reply';
import { TweetUser } from '../../model/tweet-user';
import { Tweets } from '../../model/tweets';
import { User } from '../../model/user';

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css'],
})
export class TweetComponent implements OnInit {
  tweet: string = '';
  constructor(private _apiService: ApiService) {}
  isLiked: boolean = true;
  userName: string;

  @Input() tweetsArray: Array<Tweets>;

  tweetReply: string = '';
  editTweet: string = '';
  editTweetIndex: number;

  tweetObject: Tweets = <Tweets>{};
  tweetObjectToBeEdited: Tweets = <Tweets>{};
  tweetUserObject: TweetUser = <TweetUser>{};
  user: User = <User>{};

  ngOnInit(): void {
    this.userName = sessionStorage.getItem('userName');
    this.user = JSON.parse(sessionStorage.getItem('loggedInUser'));
  }

  tweetPost() {
    if (this.tweet !== null) {
      let tweetByUser: Tweets = <Tweets>{};

      let tweetUser: TweetUser = <TweetUser>{};
      tweetUser.loginID = this.user.loginID;
      tweetUser.firstName = this.user.firstName;
      tweetUser.lastName = this.user.lastName;
      tweetUser.gender = this.user.gender;
      tweetByUser.tweetUserObject = tweetUser;
      tweetByUser.tweet = this.tweet;

      this._apiService.postTweet(tweetByUser).subscribe((response: Tweets) => {
        this.tweet = null;
      });
    }
  }

  dateDiff(firstDateString) {
    let firstDate = new Date(firstDateString);
    let secondDate = new Date().toISOString();
    let date2 = new Date(secondDate.toString());

    let firstDateInSeconds = firstDate.getTime() / 1000;
    let secondDateInSeconds = date2.getTime() / 1000;
    let difference = Math.abs(firstDateInSeconds - secondDateInSeconds);

    if (difference < 60) {
      return Math.floor(difference) + ' seconds ago';
    } else if (difference < 3600) {
      return Math.floor(difference / 60) + ' minutes ago';
    } else if (difference < 86400) {
      return Math.floor(difference / 3600) + ' hours ago';
    } else {
      return firstDate.toDateString();
    }
  }

  likeTweet(i: number, id: string) {
    let likeCount = 0;
    if (
      this.tweetsArray[i].likes &&
      this.tweetsArray[i].likes.likedBy.includes(this.userName)
    ) {
      likeCount = -1;
    } else {
      likeCount = 1;
    }
    this._apiService.likeToTweet(id, this.userName, likeCount).subscribe(
      (response) => {
        if (response) {
          this.tweetsArray[i] = response;
        }
      },
      (err) => {
        this.isLiked = true;
      }
    );
  }

  sendData(tweet: Tweets) {
    this.tweetObject = tweet;
    this.tweetUserObject = tweet.tweetUserObject;
  }

  on() {
    document.getElementById('exampleModalCenter').style.display = 'block';
  }

  tweetReplyCall(tweetId: string) {
    let repliedTweet: TweetReply = <TweetReply>{};

    repliedTweet.username = this.userName;
    repliedTweet.firstName = this.user.firstName;
    repliedTweet.lastName = this.user.lastName;
    repliedTweet.gender = this.user.gender;
    repliedTweet.reply = this.tweetReply;

    this._apiService.replyToTweet(tweetId, repliedTweet).subscribe(
      (response: Tweets) => {
        this.tweetObject.replies = response.replies;
        this.tweetReply = '';
      },
      (err) => {}
    );
  }

  onEdit(i: number, tweetObj: Tweets) {
    document.getElementById('editTweetModal').style.display = 'block';
    this.editTweet = tweetObj.tweet;
    this.tweetObjectToBeEdited = tweetObj;
    this.editTweetIndex = i;
  }

  edit() {
    this.tweetObjectToBeEdited.tweet = this.editTweet;
    this._apiService.editTweet(this.tweetObjectToBeEdited).subscribe(
      (response) => {
        if (response) {
          this.tweetsArray[this.editTweetIndex] = response;
        }
      },
      (err) => {}
    );
  }

  delete(id: number) {
    this._apiService.deleteTweet(this.tweetsArray[id].id).subscribe(
      (response) => {
        if (response) {
          this.getAllTweets();
        }
      },
      (err) => {
        this.isLiked = true;
      }
    );
  }

  getAllTweets() {
    this._apiService.getAllTweets().subscribe(
      (response) => {
        this.tweetsArray = response;
      },
      (err) => {
        this.isLiked = true;
      }
    );
  }
  
}
