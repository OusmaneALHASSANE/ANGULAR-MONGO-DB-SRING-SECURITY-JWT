import { Component, OnInit } from '@angular/core';
import {CatalogueService} from '../catalogue.service';

@Component({
  selector: 'app-admin-categories',
  templateUrl: './admin-categories.component.html',
  styleUrls: ['./admin-categories.component.css']
})
export class AdminCategoriesComponent implements OnInit {
categories;
mode = 'list';
currentCategorie;

  constructor(private  catService: CatalogueService) { }

  ngOnInit() {
  this.onGetAllCategories();
  }
onGetAllCategories() {
  this.catService.getAllCategories()
.subscribe(data => {
  this.categories = data;
}, error1 => {
  console.log(error1);});
}
  onDeleteCat(c) {
    const  cat = confirm('Etes vous sure?');
    if  (!cat) { return; }
    this.catService.deleteRessource(c._links.self.href)
  .subscribe(data => {
    this.mode = 'list';
    this.onGetAllCategories();
  }, error1 => {
    console.log(error1);
  });

  }
  onNewCategory() {
 this.mode = 'new-cat';
  }
  onSaveCat(data) {
    const  url = this.catService.host + '/categories';
    this.catService.postRessource(url, data)
      .subscribe(data => {
        this.mode = 'list';
        this.onGetAllCategories();
      }, error1 => {
        console.log(error1);
      });
  }
  onEditCat(c: any) {
    this.catService.getRessource(c._links.self.href)
      .subscribe(data => {
  this.currentCategorie = data;
  this.mode = 'edit-cat';
      }, error1 => {
        console.log(error1);
      });
  }
  onUpdateCat(data: any) {
    this.catService.putRessource(this.currentCategorie._links.self.href, data)
      .subscribe(data => {
        this.mode = 'list';
        this.onGetAllCategories();
      }, error1 => {
        console.log(error1);
      });
  }
}
