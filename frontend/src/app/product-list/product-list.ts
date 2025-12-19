import {Component, inject, OnInit, signal} from '@angular/core';
import {ProductService} from '../service/product/product-service';
import {Product} from '../models/product';
import {ProductComponent} from './product-component/product-component';

@Component({
  selector: 'app-product-list',
  imports: [
    ProductComponent
  ],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductList implements OnInit{
  service : ProductService = inject(ProductService)
  products = signal<Array<Product>>(new Array<Product>());

  ngOnInit() {
    this.service.getProducts().subscribe(resp => this.products.set(resp))
  }
}
