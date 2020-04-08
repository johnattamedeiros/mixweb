import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMatch, Match } from 'app/shared/model/match.model';
import { MatchService } from './match.service';

@Component({
  selector: 'jhi-match-update',
  templateUrl: './match-update.component.html'
})
export class MatchUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    name: [null, [Validators.required]],
    map: [null, [Validators.required]],
    matchDate: [new Date(), []]
  });

  constructor(protected matchService: MatchService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {}
  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const match = this.createFromForm();
    this.subscribeToSaveResponse(this.matchService.create(match));
  }

  private createFromForm(): IMatch {
    const matchDateAdjust = new Date(this.editForm.get(['matchDate'])!.value);
    matchDateAdjust.setHours(matchDateAdjust.getHours() - 3);
    return {
      ...new Match(),
      name: this.editForm.get(['name'])!.value,
      map: this.editForm.get(['map'])!.value,
      matchDate: matchDateAdjust
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMatch>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
