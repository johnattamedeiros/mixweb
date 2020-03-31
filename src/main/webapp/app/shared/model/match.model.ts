export interface IMatch {
  id?: number;
  iduser?: number;
}

export class Match implements IMatch {
  constructor(public id?: number, public iduser?: number) {}
}
