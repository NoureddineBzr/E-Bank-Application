import { Transaction } from '../transaction/transaction';
import { BankName } from '../../enum/bank-name';
import {Account} from "../account/account";


export class Beneficiary {
  id: number;
  name: string;
  rib: string;
  bankName: BankName;
  account: Account;
  transactions: Transaction[];

  constructor(
    id: number,
    name: string,
    rib: string,
    bankName: BankName,
    account: Account,
    transactions: Transaction[] = []
  ) {
    this.id = id;
    this.name = name;
    this.rib = rib;
    this.bankName = bankName;
    this.account = account;
    this.transactions = transactions;
  }
}
