import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExample } from 'app/shared/model/example.model';

@Component({
  selector: 'jhi-example-detail',
  templateUrl: './example-detail.component.html'
})
export class ExampleDetailComponent implements OnInit {
  example: IExample | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ example }) => (this.example = example));
  }

  previousState(): void {
    window.history.back();
  }
}
