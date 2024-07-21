import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Beneficiary } from '../../model/beneficiary/beneficiary';

@Injectable({
  providedIn: 'root'
})
export class BeneficiaryService {
  private apiUrl = 'http://localhost:8080/api/beneficiary';

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  constructor(private http: HttpClient) { }

  getAllBeneficiaries(accountId: number): Observable<Beneficiary[]> {
    return this.http.get<Beneficiary[]>(`${this.apiUrl}/all/${accountId}`, { headers: this.getHeaders() });
  }

  addBeneficiary(accountId: number, beneficiary: Beneficiary): Observable<Beneficiary> {
    return this.http.post<Beneficiary>(`${this.apiUrl}/add/${accountId}`, beneficiary, { headers: this.getHeaders() });
  }

  updateBeneficiary(beneficiaryId: number, beneficiary: Beneficiary): Observable<Beneficiary> {
    return this.http.put<Beneficiary>(`${this.apiUrl}/update/${beneficiaryId}`, beneficiary, { headers: this.getHeaders() });
  }

  deleteBeneficiary(beneficiaryId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${beneficiaryId}`, { headers: this.getHeaders() });
  }
}
