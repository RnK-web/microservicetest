import {inject, Injectable} from '@angular/core';
import {Product} from '../../models/product';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  httpClient: HttpClient = inject(HttpClient)

  getProducts() : Observable<Array<Product>>  {
    return this.httpClient.get<Array<Product>>(environment.backendEndpointUrl + environment.productApiPath);
  }
}
