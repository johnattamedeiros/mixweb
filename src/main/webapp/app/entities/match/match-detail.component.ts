import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMatch } from 'app/shared/model/match.model';

@Component({
  selector: 'jhi-match-detail',
  templateUrl: './match-detail.component.html'
})
export class MatchDetailComponent implements OnInit {
  match: IMatch | null = null;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.data.subscribe(({ match }) => (this.match = match));
  }

  previousState(): void {
    window.history.back();
  }
}
