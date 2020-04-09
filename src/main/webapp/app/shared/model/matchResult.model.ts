import { IMatch } from 'app/shared/model/match.model';
import { IUser } from 'app/core/user/user.model';

export interface IMatchResult {
  id?: number;
  match?: IMatch;
  user?: IUser;
  team?: string;
  kill?: number;
  death?: number;
  assist?: number;
  roundsWin?: number;
  roundLoss?: number;
  damage?: number;
}

export class MatchResult implements IMatchResult {
  constructor(
    id?: number,
    match?: IMatch,
    user?: IUser,
    team?: string,
    kill?: number,
    death?: number,
    assist?: number,
    roundsWin?: number,
    roundLoss?: number,
    damage?: number
  ) {}
}
