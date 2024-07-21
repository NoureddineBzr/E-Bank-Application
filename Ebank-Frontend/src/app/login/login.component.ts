import { Component } from '@angular/core';
import { AuthService } from "../service/auth/auth-service.service";
import {Router, RouterOutlet} from '@angular/router';
import { LoginUserDto } from "../dto/login-user.dto";
import {FormsModule} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
    imports: [
        FormsModule,
        RouterOutlet
    ],
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    const loginUser:LoginUserDto = new LoginUserDto(this.email, this.password);
    this.authService.login(loginUser).subscribe(
      {
        next: (response) => {
          console.log('Login successful:', response);
          localStorage.setItem('token', response.token);
          console.log('Token expires in:', response.expiresIn);
          this.router.navigate(['/accounts']);
        },
        error: (err) => {
          console.error('Login failed:', err);
        },
        complete: () => {
          console.log('You are login successfully.');
        }
      }
    );
  }
}
