import { Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegistrationComponent} from "./registration/registration.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AccountListComponent} from "./account-list/account-list.component";
import {AccountFormComponent} from "./account-form/account-form.component";

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'accounts', component: AccountListComponent },
  { path: 'account-form', component: AccountFormComponent }
];
