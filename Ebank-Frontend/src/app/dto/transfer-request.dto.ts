import { BankName } from '../enum/bank-name';

export class TransferRequestDto {
  targetAccountNumber: string;
  rib: string;
  bankName: BankName;
  amount: number;
  description: string;

  constructor(targetAccountNumber: string, rib: string, bankName: BankName, amount: number, description: string) {
    this.targetAccountNumber = targetAccountNumber;
    this.rib = rib;
    this.bankName = bankName;
    this.amount = amount;
    this.description = description;
  }
}
