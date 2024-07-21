import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {RegistrationComponent} from "./registration/registration.component";
import {LoginComponent} from "./login/login.component";
import {AccountListComponent} from "./account-list/account-list.component";
import {AccountFormComponent} from "./account-form/account-form.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RegistrationComponent, LoginComponent, AccountListComponent, AccountFormComponent, LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Ebank-Frontend';
}
