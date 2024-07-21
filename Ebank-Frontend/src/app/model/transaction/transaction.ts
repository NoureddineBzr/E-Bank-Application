import { Account } from '../account/account';
import { Beneficiary } from '../beneficiary/beneficiary';
import {TransferType} from "../../enum/transfer-type";
import {TransactionType} from "../../enum/transaction-type";

export class Transaction {
  id: number;
  transactionDate: Date;
  amount: number;
  transactionType: TransactionType;
  description: string;
  transferType: TransferType;
  targetAccountNumber: string;
  account: Account;
  beneficiary: Beneficiary;

  constructor(
    id: number,
    transactionDate: Date,
    amount: number,
    transactionType: TransactionType,
    description: string,
    transferType: TransferType,
    targetAccountNumber: string,
    account: Account,
    beneficiary: Beneficiary
  ) {
    this.id = id;
    this.transactionDate = transactionDate;
    this.amount = amount;
    this.transactionType = transactionType;
    this.description = description;
    this.transferType = transferType;
    this.targetAccountNumber = targetAccountNumber;
    this.account = account;
    this.beneficiary = beneficiary;
  }
}
