export interface IMatchResult {
  id?: number;
  idMatch?: number;
  idUser?: number;
  team?: string;
  kill?: number;
  death?: number;
  assist?: number;
  roundsWin?: number;
  roundsLoss?: number;
  damage?: number;
}

export class MatchResult implements IMatchResult {
  constructor(
    public id?: number,
    public idMatch?: number,
    public idUser?: number,
    public team?: string,
    public kill?: number,
    public death?: number,
    public assist?: number,
    public roundsWin?: number,
    public roundsLoss?: number,
    public damage?: number
  ) {}
}
