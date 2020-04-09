import { IMatchResult } from 'app/shared/model/matchResult.model';
export interface IMatch {
  id?: number;
  name?: string;
  map?: string;
  type?: string;
  matchDate?: Date;
  matchResults?: IMatchResult;
}

export class Match implements IMatch {
  constructor(
    public id?: number,
    public name?: string,
    public type?: string,
    public map?: string,
    public matchDate?: Date,
    public matchResults?: IMatchResult
  ) {}
}
