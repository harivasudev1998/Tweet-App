import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css'],
})
export class ContactUsComponent implements OnInit {
  
  constructor(public formBuilder:FormBuilder) {}

  query_form = this.formBuilder.group({
    message: ['', Validators.required],
    name: ['', Validators.required],
    email: ['', Validators.required],
  });
  ngOnInit(): void {}

  clear(){
    this.query_form.reset();
  }
}
