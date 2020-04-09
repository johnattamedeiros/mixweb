export class Account {
  constructor(
    public activated: boolean,
    public authorities: string[],
    public email: string,
    public name: string,
    public steamUrl: string,
    public langKey: string,
    public lastName: string,
    public login: string,
    public imageUrl: string
  ) {}
}
