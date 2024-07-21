import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account } from '../../model/account/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private apiUrl = 'http://localhost:8080/api/account';

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  getAllAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(`${this.apiUrl}/all`, { headers: this.getHeaders() });
  }

  addAccount(account: Account): Observable<Account> {
    return this.http.post<Account>(`${this.apiUrl}/add`, account, { headers: this.getHeaders() });
  }

  updateAccount(accountId: number, account: Account): Observable<Account> {
    return this.http.put<Account>(`${this.apiUrl}/update/${accountId}`, account, { headers: this.getHeaders() });
  }

  deleteAccount(accountId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${accountId}`, { headers: this.getHeaders() });
  }

  closeAccount(accountId: number, raisonClosing: string): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/close/${accountId}`, { raisonClosing }, { headers: this.getHeaders() });
  }

  getAccountBalance(accountId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/balance/${accountId}`, { headers: this.getHeaders() });
  }
}
