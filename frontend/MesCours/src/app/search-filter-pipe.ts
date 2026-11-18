import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFilter',
  standalone: true
})
export class SearchFilterPipe implements PipeTransform {
  transform(list: any[], filterText: string): any[] {
    if (!list) return [];
    if (!filterText) return list; // champ vide : on affiche tout
    return list.filter(item =>
      item.intituleCour?.toLowerCase().includes(filterText.toLowerCase())
    );
  }
}