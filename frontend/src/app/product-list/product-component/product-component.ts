import {Component, inject, input, signal, WritableSignal} from '@angular/core';
import {Product} from '../../models/product';
import {OrderService} from '../../service/order/order-service';
import {OrderCreationRequest} from '../../models/order-creation-request';
import {CommandComponent} from '../command-component/command-component';

@Component({
  selector: 'app-product-component',
  imports: [
    CommandComponent
  ],
  templateUrl: './product-component.html',
  styleUrl: './product-component.css',
})
export class ProductComponent {
  product = input.required<Product>()
  service = inject(OrderService)
  orderCreationSuccess: WritableSignal<boolean|null> = signal(null)

  command(quantity: number, product: Product) {
    const request: OrderCreationRequest = {productId: product.id, quantity: quantity, price: product.price}
    this.service.createOrder(request)
      .subscribe({
        next: () => this.orderCreationSuccess.set(true),
        error: () => this.orderCreationSuccess.set(false)
      })
  }
}
