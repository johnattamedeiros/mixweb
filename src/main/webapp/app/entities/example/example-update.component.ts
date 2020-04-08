import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IExample, Example } from 'app/shared/model/example.model';
import { ExampleService } from './example.service';

@Component({
  selector: 'jhi-example-update',
  templateUrl: './example-update.component.html'
})
export class ExampleUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    age: []
  });

  constructor(protected exampleService: ExampleService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ example }) => {
      this.updateForm(example);
    });
  }

  updateForm(example: IExample): void {
    this.editForm.patchValue({
      id: example.id,
      name: example.name,
      age: example.age
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const example = this.createFromForm();
    if (example.id !== undefined) {
      this.subscribeToSaveResponse(this.exampleService.update(example));
    } else {
      this.subscribeToSaveResponse(this.exampleService.create(example));
    }
  }

  private createFromForm(): IExample {
    return {
      ...new Example(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      age: this.editForm.get(['age'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExample>>): void {
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
