import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../model/user/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/user';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/all`);
  }

  addUser(user: User): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/add`, user, this.httpOptions);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  updateUser(id: number, user: User): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/update/${id}`, user, this.httpOptions);
  }

  getAuthenticatedUser(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/me`);
  }
}
