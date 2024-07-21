import { Transaction } from '../transaction/transaction';
import { BankCard } from '../bank-card/bankcard';
import { Beneficiary } from '../beneficiary/beneficiary';
import {AccountType} from "../../enum/account-type";


export class Account {
  id: number;
  accountNumber: string;
  accountType: AccountType;
  balance: number;
  dateCreation: Date;
  accountClosed: boolean;
  raisonClosing: string;
  userId: number;
  transactions: Transaction[];
  bankCards: BankCard[];
  beneficiaries: Beneficiary[];

  constructor(
    id: number,
    accountNumber: string,
    accountType: AccountType,
    balance: number,
    dateCreation: Date,
    accountClosed: boolean,
    raisonClosing: string,
    userId: number,
    transactions: Transaction[] = [],
    bankCards: BankCard[] = [],
    beneficiaries: Beneficiary[] = []
  ) {
    this.id = id;
    this.accountNumber = accountNumber;
    this.accountType = accountType;
    this.balance = balance;
    this.dateCreation = dateCreation;
    this.accountClosed = accountClosed;
    this.raisonClosing = raisonClosing;
    this.userId = userId;
    this.transactions = transactions;
    this.bankCards = bankCards;
    this.beneficiaries = beneficiaries;
  }
}
