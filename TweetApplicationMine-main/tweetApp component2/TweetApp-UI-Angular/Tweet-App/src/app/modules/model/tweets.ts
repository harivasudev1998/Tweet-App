import { Likes } from './likes';
import { TweetReply } from './tweet-reply';
import { TweetUser } from './tweet-user';

export interface Tweets {
  id: string;

  tweet: string;

  tweetUserObject: TweetUser;

  likes: Likes;

  timeOfPost: Date;

  tags: Array<String>;

  replies: Array<TweetReply>;
}
