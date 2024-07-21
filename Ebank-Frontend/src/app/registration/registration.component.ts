import { Component } from '@angular/core';
import { AuthService } from "../service/auth/auth-service.service";
import {Router, RouterOutlet} from '@angular/router';
import { RegisterUserDto } from "../dto/register-user.dto";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  standalone: true,
  imports: [
    FormsModule,
    RouterOutlet
  ],
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent {
  email: string = '';
  password: string = '';
  fullName: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    const registerUser : RegisterUserDto = new RegisterUserDto(this.email, this.password, this.fullName);
    this.authService.signup(registerUser).subscribe(
      {
        next: (response) => {
          console.log('Registration successful:', response);
          this.router.navigate(['/login']);
        },
        error: (err) => {
          console.error('Registration failed:', err);
        },
        complete: () => {
          console.log('Registration process completed.');
        }
      }
    );
  }
}
