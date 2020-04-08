export interface IMatch {
  id?: number;
  name?: string;
  map?: string;
  matchDate?: Date;
}

export class Match implements IMatch {
  constructor(public id?: number, public name?: string, public map?: string, public matchDate?: Date) {}
}
