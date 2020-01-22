import { Component, OnInit } from '@angular/core';
import {CatalogueService} from '../catalogue.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  constructor(private catService: CatalogueService, private router: Router) { }
categories;
  currentCategory;

  ngOnInit() {
    this.catService.getAllCategories()
      .subscribe(data => {
        this.categories = data;
      }, error1 => {
        console.log(error1);
      });
  }

  onGetProducts(cat) {
    this.currentCategory = cat;
    const url = cat._links.products.href;
    this.router.navigateByUrl('/products/' + btoa(url) );
  }
}
