import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Login} from './login/login';
import {ProductList} from './product-list/product-list';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Login, ProductList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
