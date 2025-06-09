import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';
import {ProductService} from '../services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css',
  imports: [
    NgForOf,
    NgIf
  ],
  standalone: true
})
export class ProductsComponent implements OnInit{
  products : any ;
  constructor(private productService : ProductService) {
  }
  ngOnInit() : void {
    this.getAllProducts();
  }

  getAllProducts() : void {
    this.productService.getAllProducts().subscribe({
      next : resp =>{
        this.products = resp;
      },
      error : err => {
        console.log(err)
      }
    })
  }

  handleDelete(product: any) {
    let v = confirm('Êtes vous sûre de vouloir supprimer ?');
    if (v == true){
      this.productService.deleteProduct(product).subscribe({
        next : value => {
          this.getAllProducts();
        },
        error : err => {
          console.log(err)
        }
      });
      this.getAllProducts();
    }
  }

}
