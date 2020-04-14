import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMatch } from '../../shared/model/match.model';
import { User } from '../../core/user/user.model';
import { MatchService } from './match.service';
import { UserService } from '../../core/user/user.service';
import { IMatchResult, MatchResult } from '../../shared/model/matchResult.model';

@Component({
  selector: 'jhi-match-result-update',
  templateUrl: './match-result-update.component.html'
})
export class MatchResultUpdateComponent implements OnInit {
  isSaving = false;
  match: IMatch | null = null;
  users: User[] | null = null;
  page!: number;
  predicate!: string;
  ascending!: boolean;

  editForm = this.fb.group({
    idUser: [null, [Validators.required]],
    team: [null, [Validators.required]],
    kill: [null, [Validators.required, Validators.pattern('^[0-9]*$')]],
    death: [null, [Validators.required, Validators.pattern('^[0-9]*$')]],
    assist: [null, [Validators.required, Validators.pattern('^[0-9]*$')]],
    damage: [null, [Validators.required, Validators.pattern('^[0-9]*$')]],
    roundsWin: [null, [Validators.required, Validators.pattern('^[0-9]*$')]],
    roundsLoss: [null, [Validators.required, Validators.pattern('^[0-9]*$')]]
  });

  constructor(
    protected matchService: MatchService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ match }) => (this.match = match));
    this.loadUsers();
  }
  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const matchResult = this.createFromForm();
    this.subscribeToSaveResponse(this.matchService.createMatchResult(matchResult));
  }

  private createFromForm(): IMatchResult {
    if (this.match) {
      return {
        ...new MatchResult(),
        idUser: parseInt(this.editForm.get(['idUser'])!.value, 10),
        idMatch: this.match.id,
        team: this.editForm.get(['team'])!.value,
        kill: this.editForm.get(['kill'])!.value,
        death: this.editForm.get(['death'])!.value,
        assist: this.editForm.get(['assist'])!.value,
        damage: this.editForm.get(['damage'])!.value,
        roundsWin: this.editForm.get(['roundsWin'])!.value,
        roundsLoss: this.editForm.get(['roundsLoss'])!.value
      };
    } else {
      return {
        ...new MatchResult(),
        idUser: parseInt(this.editForm.get(['idUser'])!.value, 10),
        team: this.editForm.get(['team'])!.value,
        kill: this.editForm.get(['kill'])!.value,
        death: this.editForm.get(['death'])!.value,
        assist: this.editForm.get(['assist'])!.value,
        damage: this.editForm.get(['damage'])!.value,
        roundsWin: this.editForm.get(['roundsWin'])!.value,
        roundsLoss: this.editForm.get(['roundsLoss'])!.value
      };
    }
  }
  private loadUsers(): void {
    this.userService.querySelect().subscribe((res: HttpResponse<User[]>) => this.onSuccess(res.body));
  }
  private onSuccess(users: User[] | null): void {
    this.users = users;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMatchResult>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  private sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    return result;
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
