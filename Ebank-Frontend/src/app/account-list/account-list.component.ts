import {Component, OnInit} from '@angular/core';
import { AccountService } from "../service/account/account.service";
import { Account } from "../model/account/account";
import {RouterLink, RouterOutlet} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-account-list',
  standalone: true,
  imports: [
    RouterLink,
    NgIf,
    NgForOf,
    RouterOutlet
  ],
  templateUrl: './account-list.component.html',
  styleUrl: './account-list.component.scss'
})

export class AccountListComponent implements OnInit {
  accounts: Account[] = [];
  userId: number = 1;

  constructor(private accountService: AccountService) {}

  ngOnInit(): void {
    this.loadAccounts();
  }

  loadAccounts(): void {
    this.accountService.getAllAccounts().subscribe({
      next: (data) => this.accounts = data,
      error: (err) => console.error('Failed to load accounts', err),
      complete: () => console.log('Account loading completed.')
    });
  }

  deleteAccount(accountId: number): void {
    this.accountService.deleteAccount(accountId).subscribe({
      next: () => this.loadAccounts(),
      error: (err) => console.error('Failed to delete account', err),
      complete: () => console.log('Account deletion completed.')
    });
  }

}
