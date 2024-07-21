import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from '../../model/transaction/transaction';
import { TransferRequestDto } from '../../dto/transfer-request.dto';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private apiUrl = 'http://localhost:8080/api/transaction';

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  constructor(private http: HttpClient) { }

  getAllTransactions(accountId: number): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.apiUrl}/all/${accountId}`, { headers: this.getHeaders() });
  }

  createInternalTransaction(accountId: number, transferRequest: TransferRequestDto): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/internal/${accountId}`, transferRequest, { headers: this.getHeaders() });
  }

  createExternalTransaction(accountId: number, transferRequest: TransferRequestDto): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/external/${accountId}`, transferRequest, { headers: this.getHeaders() });
  }
}
