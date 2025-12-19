import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {OrderCreationRequest} from '../../models/order-creation-request';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {OrderCreationResponse} from '../../models/order-creation-response';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  httpClient = inject(HttpClient)

  createOrder(request: OrderCreationRequest) : Observable<OrderCreationResponse> {
    return this.httpClient.post<OrderCreationResponse>(environment.backendEndpointUrl + environment.orderApiPath, request)
  }
}
