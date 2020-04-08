import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IExample, Example } from 'app/shared/model/example.model';
import { ExampleService } from './example.service';
import { ExampleComponent } from './example.component';
import { ExampleDetailComponent } from './example-detail.component';
import { ExampleUpdateComponent } from './example-update.component';

@Injectable({ providedIn: 'root' })
export class ExampleResolve implements Resolve<IExample> {
  constructor(private service: ExampleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExample> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((example: HttpResponse<Example>) => {
          if (example.body) {
            return of(example.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Example());
  }
}

export const exampleRoute: Routes = [
  {
    path: '',
    component: ExampleComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Examples'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ExampleDetailComponent,
    resolve: {
      example: ExampleResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Examples'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ExampleUpdateComponent,
    resolve: {
      example: ExampleResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Examples'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ExampleUpdateComponent,
    resolve: {
      example: ExampleResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Examples'
    },
    canActivate: [UserRouteAccessService]
  }
];
