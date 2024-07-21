export class User {
  id: number;
  fullName: string;
  phone: string;
  address: string;
  city: string;
  email: string;
  password: string;

  constructor(id: number, fullName: string, phone: string, address: string, city: string, email: string, password: string) {
    this.id = id;
    this.fullName = fullName;
    this.phone = phone;
    this.address = address;
    this.city = city;
    this.email = email;
    this.password = password;
  }
}
