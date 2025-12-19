import {Component, output, signal} from '@angular/core';

@Component({
  selector: 'app-command-component',
  imports: [],
  templateUrl: './command-component.html',
  styleUrl: './command-component.css',
})
export class CommandComponent {
  productCount= signal(1)
  command = output<number>()

  decrementProductCount(){
    this.productCount.update(count => Math.max(count - 1, 1))
  }

  incrementProductCount(){
    this.productCount.update(count => count + 1)
  }

  orderProduct(quantity: number) {
    this.command.emit(quantity)
  }
}
