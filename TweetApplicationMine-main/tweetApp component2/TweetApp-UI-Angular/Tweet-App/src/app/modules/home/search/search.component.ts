import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/core/services/api.service';
import { User } from '../../model/user';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  users: Array<User> = [];
  searchKey: string;
  userName: string;
  constructor(private _apiService: ApiService) {}

  ngOnInit(): void {
    this.userName = sessionStorage.getItem('userName');
    this.getAllUsers();
  }

  search() {
    if (this.searchKey === '' || this.searchKey === null) {
      this.getAllUsers();
    } else {
      this.getSearchUsers();
    }
  }

  getAllUsers() {
    this._apiService.getAllUsers().subscribe(
      (response: Array<User>) => {
        this.users = response;
        this.users = this.users.filter(
          (data) => data.loginID !== this.userName
        );
      },
      (error) => {}
    );
  }

  getSearchUsers() {
    this._apiService.getSearchUsers(this.searchKey).subscribe(
      (response: Array<User>) => {
        this.users = response;
        this.users = this.users.filter(
          (data) => data.loginID !== this.userName
        );
      },
      (error) => {}
    );
  }
  dateDiff(firstDateString) {
    let firstDate = new Date(firstDateString);
    let secondDate = new Date().toISOString();
    let date2 = new Date(secondDate.toString());

    let firstDateInSeconds = firstDate.getTime() / 1000;
    let secondDateInSeconds = date2.getTime() / 1000;
    let difference = Math.abs(firstDateInSeconds - secondDateInSeconds);

    if (difference < 60) {
      return Math.floor(difference) + ' seconds';
    } else if (difference < 3600) {
      return Math.floor(difference / 60) + ' minutes';
    } else if (difference < 86400) {
      return Math.floor(difference / 3600) + ' hours';
    } else {
      return firstDate.toDateString();
    }
  }
}
