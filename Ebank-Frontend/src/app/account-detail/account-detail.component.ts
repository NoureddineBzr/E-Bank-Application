import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from "../service/account/account.service";
import {Account} from "../model/account/account";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  standalone: true,
  imports: [
    NgIf
  ],
  styleUrls: ['./account-detail.component.scss']
})
export class AccountDetailComponent implements OnInit {
  account: Account | undefined;

  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    const accountId = +this.route.snapshot.paramMap.get('id')!;
    this.loadAccount(accountId);
  }

  loadAccount(accountId: number): void {
    this.accountService.getAllAccounts(accountId).subscribe({
      next: (data) => this.account = data.find(acc => acc.id === accountId),
      error: (err) => console.error('Failed to load account', err),
      complete: () => console.log('Account loading completed.')
    });
  }
}
