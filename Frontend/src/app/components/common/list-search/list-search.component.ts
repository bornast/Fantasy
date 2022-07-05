import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'list-search',
  templateUrl: './list-search.component.html',
  styleUrls: ['./list-search.component.css']
})
export class ListSearchComponent implements OnInit {

	@Input() searchTxt: string;
	@Output() search = new EventEmitter();

	constructor() {}

	ngOnInit() {}

	searchEmit() {
        let searchTxt = this.searchTxt;
		this.search.emit(searchTxt);
	}

}
