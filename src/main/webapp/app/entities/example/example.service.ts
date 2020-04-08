import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IExample } from 'app/shared/model/example.model';

type EntityResponseType = HttpResponse<IExample>;
type EntityArrayResponseType = HttpResponse<IExample[]>;

@Injectable({ providedIn: 'root' })
export class ExampleService {
  public resourceUrl = SERVER_API_URL + 'api/examples';

  constructor(protected http: HttpClient) {}

  create(example: IExample): Observable<EntityResponseType> {
    return this.http.post<IExample>(this.resourceUrl, example, { observe: 'response' });
  }

  update(example: IExample): Observable<EntityResponseType> {
    return this.http.put<IExample>(this.resourceUrl, example, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IExample>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IExample[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
