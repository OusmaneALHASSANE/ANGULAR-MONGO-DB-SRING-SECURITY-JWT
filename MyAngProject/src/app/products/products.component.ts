import { Component, OnInit } from '@angular/core';
import {CatalogueService} from '../catalogue.service';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
products;
  constructor(private catService: CatalogueService, private router: Router,
              private route: ActivatedRoute  ) {
    router.events.subscribe(event => {
              if (event instanceof NavigationEnd) {
                const url = atob(route.snapshot.params.url) ;
                this.getProducts(url);}
    });
  }
  ngOnInit() {}
getProducts(url) {
this.catService.getRessource(url)
  .subscribe(data => {
    this.products = data;
  }, error1 => {
    console.log(error1);
  });

}
}
