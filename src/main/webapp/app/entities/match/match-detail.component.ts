import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IMatch } from 'app/shared/model/match.model';
import { MatchService } from '../match/match.service';
import { IMatchResult } from '../../shared/model/matchResult.model';

@Component({
  selector: 'jhi-match-detail',
  templateUrl: './match-detail.component.html'
})
export class MatchDetailComponent implements OnInit {
  match: IMatch | null = null;

  constructor(private route: ActivatedRoute, protected matchService: MatchService) {}

  ngOnInit(): void {
    this.route.data.subscribe(({ match }) => (this.match = match));
  }
  deleteMatchResult(id: number): void {
    this.subscribeToSaveResponse(this.matchService.deleteMatchResult(id));
  }
  previousState(): void {
    window.history.back();
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMatchResult>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {}
}
