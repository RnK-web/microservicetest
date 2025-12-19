import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductList } from './product-list';
import {ProductService} from '../service/product/product-service';
import {of} from 'rxjs';

describe('ProductList', () => {
  let component: ProductList;
  let fixture: ComponentFixture<ProductList>;
  let mockProductService: any;

  beforeEach(async () => {
    mockProductService = {
      getProducts: vi.fn().mockReturnValue(of([
        { id: 1, name: 'Product 1', price: 100 }
      ]))
    };
    await TestBed.configureTestingModule({
      imports: [ProductList],
      providers: [
        {provide: ProductService, useValue: mockProductService}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductList);
    component = fixture.componentInstance;
    component.service =
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display products from service', () => {
    expect(component.products()).toEqual([
      { id: 1, name: 'Product 1', price: 100 }
    ]);
  });

  it('should call getProducts on init', () => {
    expect(mockProductService.getProducts).toHaveBeenCalled();
  });
});
