import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginComponent } from 'src/app/modules/login/login.component';
import { stringify } from '@angular/compiler/src/util';
import { LoginUser } from 'src/app/modules/model/login-user';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { User } from 'src/app/modules/model/user';
import { Tweets } from 'src/app/modules/model/tweets';
import { Likes } from 'src/app/modules/model/likes';
import { TweetReply } from 'src/app/modules/model/tweet-reply';
import { ResetUser } from 'src/app/modules/model/reset-password';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private _httpService: HttpClient) {}

  login(loginuser: LoginUser): Observable<User> {
    let loginURl = environment.userUrl + '/login';
    return this._httpService.post<User>(loginURl, loginuser);
  }

  reset(resetUser: LoginUser): Observable<User> {
    let resetURl = environment.userUrl + '/forgot';
    return this._httpService.put<User>(resetURl, resetUser);
  }

  register(registrationUser: User): Observable<User> {
    let registrationURl = environment.userUrl + '/register';
    return this._httpService.post<User>(registrationURl, registrationUser);
  }

  addIntro(userIntro: User): Observable<User> {
    let registrationURl = environment.userUrl + '/addBIO';
    return this._httpService.put<User>(registrationURl, userIntro);
  }

  getSearchUsers(username: string): Observable<Array<User>> {
    let searchURL = environment.userUrl + '/search/' + username;
    return this._httpService.get<Array<User>>(searchURL);
  }

  getAllUsers(): Observable<Array<User>> {
    let searchURL = environment.userUrl + '/all';
    return this._httpService.get<Array<User>>(searchURL);
  }

  getAllTweets(): Observable<Array<Tweets>> {
    let getAllTweetURl = environment.tweetUrl + '/allTweets';
    return this._httpService.get<Array<Tweets>>(getAllTweetURl);
  }

  getAllMyTweets(loginID: string): Observable<Array<Tweets>> {
    let getAllMyTweetURl = environment.tweetUrl + '/allUserTweets/' + loginID;
    return this._httpService.get<Array<Tweets>>(getAllMyTweetURl);
  }

  likeToTweet(
    tweetId: string,
    userName: string,
    likeCount: number
  ): Observable<Tweets> {
    let likeObj: Likes = <Likes>{};
    likeObj.count = likeCount;
    likeObj.likedBy = [userName];
    let likeTweetURl = environment.tweetUrl + '/like/' + tweetId;
    return this._httpService.put<Tweets>(likeTweetURl, likeObj);
  }

  postTweet(tweet: Tweets): Observable<Tweets> {
    let tweetUrl = environment.tweetUrl;
    return this._httpService.post<Tweets>(tweetUrl, tweet);
  }

  editTweet(tweet: Tweets): Observable<Tweets> {
    let editTweetUrl = environment.tweetUrl + '/edit';
    return this._httpService.put<Tweets>(editTweetUrl, tweet);
  }

  deleteTweet(tweetId: string): Observable<Boolean> {
    let editTweetUrl = environment.tweetUrl + '/delete/' + tweetId;
    return this._httpService.delete<Boolean>(editTweetUrl);
  }

  replyToTweet(tweetId: string, tweetReply: TweetReply): Observable<Tweets> {
    let tweetUrl = environment.tweetUrl + '/' + tweetId + '/reply';
    return this._httpService.put<Tweets>(tweetUrl, tweetReply);
  }

  logout(userName: string) {
    let logOutUrl = environment.userUrl + '/logout/' + userName;
    return this._httpService.put(logOutUrl, null, {
      responseType: 'text',
    });
  }
}
