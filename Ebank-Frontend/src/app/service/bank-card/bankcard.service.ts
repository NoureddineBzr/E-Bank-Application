import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BankCard } from '../../model/bank-card/bankcard';

@Injectable({
  providedIn: 'root'
})
export class BankCardService {
  private apiUrl = 'http://localhost:8080/api/bankCard';

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  constructor(private http: HttpClient) { }

  getAllBankCards(accountId: number): Observable<BankCard[]> {
    return this.http.get<BankCard[]>(`${this.apiUrl}/all/${accountId}`, { headers: this.getHeaders() });
  }

  createBankCard(accountId: number, bankCard: BankCard): Observable<BankCard> {
    return this.http.post<BankCard>(`${this.apiUrl}/add/${accountId}`, bankCard, { headers: this.getHeaders() });
  }

  activateOrDeactivateBankCard(bankCardId: number, activate: boolean): Observable<BankCard> {
    return this.http.put<BankCard>(`${this.apiUrl}/activate/${bankCardId}?activate=${activate}`, null, { headers: this.getHeaders() });
  }

  blockBankCard(bankCardId: number, blockRaison: string): Observable<BankCard> {
    return this.http.put<BankCard>(`${this.apiUrl}/block/${bankCardId}`, { blockRaison }, { headers: this.getHeaders() });
  }
}
