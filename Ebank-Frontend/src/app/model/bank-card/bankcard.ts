import {Status} from "../../enum/status";
import {CardType} from "../../enum/card-type";


export class BankCard {
  id: number;
  cardNumber: string;
  expirationDate: string;
  cardType: CardType;
  status: Status;
  blockRaison: string;
  accountId: number;

  constructor(
    id: number,
    cardNumber: string,
    expirationDate: string,
    cardType: CardType,
    status: Status,
    blockRaison: string,
    accountId: number
  ) {
    this.id = id;
    this.cardNumber = cardNumber;
    this.expirationDate = expirationDate;
    this.cardType = cardType;
    this.status = status;
    this.blockRaison = blockRaison;
    this.accountId = accountId;
  }
}
