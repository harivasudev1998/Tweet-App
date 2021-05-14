import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/core/services/api.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
  constructor(private _apiService: ApiService, private _router: Router) {}

  ngOnInit(): void {}

  logout() {
    let userName = sessionStorage.getItem('userName');
    this._apiService.logout(userName).subscribe((response) => {
      if (response) {
        sessionStorage.clear();
        this._router.navigateByUrl('/login');
      }
    });
  }
}
